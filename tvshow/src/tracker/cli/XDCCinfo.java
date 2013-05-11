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

public class XDCCinfo {
	ArrayList<XDCCfile> fileList;
	
	public ArrayList<XDCCfile> getFileList(){
		return fileList;
	}

	public XDCCinfo(String name) throws IOException, URISyntaxException, CouldNotOpenUriSchemeHandler {
      //  Desktop.getDesktop().browse(new URI("http://www.google.pt"));	
        int index=0;
        int nresults=0,counter=0;
        boolean results=false;
        fileList = new ArrayList<XDCCfile>();
        	BufferedReader in = null;        	
        	URL url = new URL("http://www.xweasel.org/Search.php?Description="+name);
            URLConnection urlc;
            urlc = url.openConnection();
            urlc.setDoOutput(true);
            urlc.addRequestProperty("user-agent", "Firefox");
            in = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
            String inputLine;
            fileList = new ArrayList<XDCCfile>();

            while ((inputLine = in.readLine()) != null){
            	
            	
            	
            	if(inputLine.contains("OnClick=\"Show()\"")&& inputLine.contains("Abjects")){
            		fileList.add(new XDCCfile(counter));
            		
            		fileList.get(counter).setLink("/msg "+
            										inputLine.substring(nthOccurrence(inputLine,'\'',7)+1, nthOccurrence(inputLine,'\'',8))+
            										" xdcc send "+"#"+
            										inputLine.substring(nthOccurrence(inputLine,'\'',11)+1, nthOccurrence(inputLine,'\'',12)));
            		
            		fileList.get(counter).setFilename(inputLine.substring(nthOccurrence(inputLine,'\'',1)+1, nthOccurrence(inputLine,'\'',2)));
            		
            		fileList.get(counter).setChannel(inputLine.substring(nthOccurrence(inputLine,'\'',5)+1, nthOccurrence(inputLine,'\'',6)));
            		
            		for(int i=0;i<2;i++)
            			inputLine = in.readLine();
            			
            		fileList.get(counter).setFilesize(inputLine.substring(nthOccurrence(inputLine,'>',1)+1, nthOccurrence(inputLine,'<',2)).replaceAll("&nbsp;", " "));
            		
            		for(int i=0;i<3;i++)
            			inputLine = in.readLine();
            		
            		fileList.get(counter).setDW(inputLine.substring(nthOccurrence(inputLine,'>',1)+1, nthOccurrence(inputLine,'<',2)).replaceAll("&nbsp;", " "));            		
            		counter++;

            	}
            	
            }
        
        

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
