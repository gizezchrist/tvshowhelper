package tracker.cli;
import java.net.*; 
import java.util.ArrayList;
import java.io.*; 

public class ShowStats { 
  	private String showID;
	private ArrayList<String> showDates;
	private String showName;
	private int totalEpisodes;
	private int season;
	

	
    public ShowStats(String showName,int season) { 
    	this.totalEpisodes=-1;
    	this.season=season;
    	this.showName = showName;
    	showID=getShowID(showName);
    	showDates=getShowDates(showID,this.season);
    } 
    
    
    private ArrayList<String> getShowDates(String showID, int season) {
    	/* http://www.imdb.com/title/tt1405406/episodes?season=4 */
    	
        int counter=0;
        ArrayList<String> list = new ArrayList<String>();


        BufferedReader in = null;
        
        
        try {
            URL url = new URL("http://www.imdb.com/title/"+showID+"/episodes?season="+season);
            URLConnection urlc;
            urlc = url.openConnection();
            urlc.addRequestProperty("user-agent", "Firefox");
            in = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
            String inputLine;

            
            while ((inputLine = in.readLine()) != null){
             
            	if(inputLine.contains("<meta itemprop=\"numberofEpisodes\"")){            		
            		totalEpisodes =   Integer.parseInt(inputLine.substring(inputLine.indexOf("content=\"")+9,inputLine.indexOf("\"/>")));
            		
            	}
            	
            	if(inputLine.contains("<div class=\"airdate\">")){ 
            		inputLine = in.readLine();
            		list.add(inputLine.replaceAll(" ", ""));
            		counter++;
            	}
            		
            	if(counter==totalEpisodes)
            		break;
            	
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return list;    
    }

    private String getShowID(String showName) {
    	String showID = null;
        int flag=0;
        BufferedReader in = null;
        String tmp_showName = showName.replaceAll(" ", "+");
        
        
        try {
            URL url = new URL("http://www.imdb.com/find?q="+tmp_showName+"&s=all");
            URLConnection urlc;
            urlc = url.openConnection();
            urlc.addRequestProperty("user-agent", "Firefox");
            in = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null){
             
            	if(inputLine.contains("<h3 class=\"findSectionHeader\">"))
            		if(inputLine.contains("</a>Titles</h3>"))
            			flag=1;
            		else{
            			break;
            		}
            	
            	if (flag==1 && inputLine.contains("findResult odd") && inputLine.contains(showName) &&  inputLine.contains("/title/")) {
                    showID=inputLine.substring((inputLine.indexOf("/title/"))+7, inputLine.indexOf("/?ref_"));
                    break;
            	}
            	
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        
		if (flag==0)
        	System.out.println("Not Found");
 
			return showID;
    
    }

    public int getTotalEpisodes(){
		return totalEpisodes;
	}
    public int getSeason(){
		return season;
	}
    
    
    
    public ArrayList<String> getShowDates(){
		return showDates;
	}
    public String getShowName(){
		return showName;
	}
	public String getShowID(){
		return showID;
	}
} 