package tracker.cli;

import uriSchemeHandler.*;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Piratebayinfo {
	ArrayList<PBfile> fileList;

	public ArrayList<PBfile> getFileList(){
		return fileList;
	}
	
	public Piratebayinfo(String name) throws IOException, URISyntaxException, CouldNotOpenUriSchemeHandler {
      //  Desktop.getDesktop().browse(new URI("http://www.google.pt"));	
        int index=0;
        int nresults=0,counter=0;
        boolean results=false;

        	BufferedReader in = null;
            URL url = new URL("http://thepiratebay.sx/search/"+name+"/0/7/208 ");
            URLConnection urlc;
            urlc = url.openConnection();
            urlc.addRequestProperty("user-agent", "Firefox");
            in = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
            String inputLine;
            fileList = new ArrayList<PBfile>();
            
            while ((inputLine = in.readLine()) != null){
            	
            	
				if(inputLine.contains("Displaying")){
            		results=true;
            		nresults=Integer.parseInt(inputLine.substring(inputLine.indexOf("to ")+3,inputLine.indexOf(" (approx")));
            		for(int i=0;i<nresults;i++)
            			fileList.add(new PBfile(i));
				}
            	
            	
            	 if(inputLine.contains("<div class=\"detName\">") && results){
            	 
            		/* Filename */
            		fileList.get(counter).setFilename(inputLine.substring(nthOccurrence(inputLine, '/', 3)+1,nthOccurrence(inputLine,'"',4)));
            		inputLine = in.readLine();
            		inputLine = in.readLine();
            		/* magnetlink */
            		fileList.get(counter).setMagnetLink(inputLine.substring(nthOccurrence(inputLine, '"', 1)+1,nthOccurrence(inputLine,'"',2)));
            		/* filesize */
            		inputLine = in.readLine();
            		//fileList.get(counter).setFilesize(inputLine.substring(inputLine.indexOf("Size"),nthOccurrence(inputLine,',',1)));
            		//System.out.println(inputLine.substring(inputLine.indexOf("Size")+5,inputLine.indexOf("iB")+2));
            		fileList.get(counter).setFilesize(inputLine.substring(inputLine.indexOf("Size")+5,inputLine.indexOf("iB")+2).replaceAll("&nbsp;", " "));
            		/* seeders */
            		for(int i=0;i<2;i++) inputLine = in.readLine();
            		fileList.get(counter).setSeeders(inputLine.substring(nthOccurrence(inputLine, '>', 1)+1,nthOccurrence(inputLine,'<',2)));
            		/* leechers */
            		inputLine = in.readLine();
            		fileList.get(counter).setLeechers(inputLine.substring(nthOccurrence(inputLine, '>', 1)+1,nthOccurrence(inputLine,'<',2)));
            		
            		counter++;
            	}
            	
            }
        
        
	for(int i=0;i<fileList.size();i++)
		System.out.println(fileList.get(i).getFilename()+"|"+fileList.get(i).getMagnetLink()+"|"+fileList.get(i).getFilesize()+"|"+fileList.get(i).getSeeders()+"|"+fileList.get(i).getLeechers());
	
	
	}

	
	
		
	
	

	 public static int nthOccurrence(String s, char c, int occurrence) {
	     return nthOccurrence(s, 0, c, 0, occurrence);
	 }

	 public static int nthOccurrence(String s, int from, char c, int curr, int expected) {
	     final int index = s.indexOf(c, from);
	     if(index == -1) return -1;
	     return (curr + 1 == expected) ? index : 
	         nthOccurrence(s, index + 1, c, curr + 1, expected);
	 }


}
