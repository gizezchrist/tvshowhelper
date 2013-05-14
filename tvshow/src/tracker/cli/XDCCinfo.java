package tracker.cli;

import uriSchemeHandler.*;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class XDCCinfo {
	ArrayList<XDCCfile> fileList;
	
	public ArrayList<XDCCfile> getFileList(){
		return fileList;
	}

	
	public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException, CouldNotOpenUriSchemeHandler, KeyManagementException, NoSuchAlgorithmException{
		XDCCinfo xdcc= new XDCCinfo("Arrow.s01e13.720p");
		
	//String inputLine="Arrow.S01E13.720p.HDTV.X264-DIMENSION.tar... more Abjects    #beast-xdcc 1.1G 7.21  #81  ";
		
	
	
	//inputLine="Game.of.Thrones.S03E07.720p.HDTV.x264-EVOLVE.mkv... more info  Abjects #beast-xdcc 1.5G 22.16 #95"  
		//inputLine="Bot Nick: Beast-Gin-Tv ";                                        
	
		//System.out.println("dw:"+	inputLine.substring(inputLine.lastIndexOf(":")+2));
	//System.out.println(inputLine.substring(nthOccurrence(inputLine,' ',6)+1,nthOccurrence(inputLine,' ',7)));
		
	}
	
	public XDCCinfo(String name) throws IOException, URISyntaxException, CouldNotOpenUriSchemeHandler, KeyManagementException, NoSuchAlgorithmException {



TrustManager trm = new X509TrustManager() {
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    public void checkClientTrusted(X509Certificate[] certs, String authType) {

    }

    public void checkServerTrusted(X509Certificate[] certs, String authType) {
    }
};
SSLContext sc = SSLContext.getInstance("SSL");
sc.init(null, new TrustManager[] { trm }, null);
HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		//  Desktop.getDesktop().browse(new URI("http://www.google.pt"));	
        int index=0;
        int nresults=0,counter=0;
        boolean results=false;
        fileList = new ArrayList<XDCCfile>();
        	BufferedReader in = null;        	
        	name=name.replaceAll(" ",".");
        	System.out.println("name:"+name);
        	URL url = new URL("https://w3dt.net/tools/textbrowser/?host=http://www.xdccfinder.com/results.php?kw="+name+"&submit=Browse&clean_opt=1");
        	HttpURLConnection urlc;
            urlc = (HttpURLConnection) url.openConnection();
            urlc.setDoOutput(true);
            urlc.addRequestProperty("user-agent", "Firefox");
            in = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
            String inputLine;
            fileList = new ArrayList<XDCCfile>();
            boolean check=false;
            while ((inputLine = in.readLine()) != null){

            	if(inputLine.contains("Description"))
            		check=true;
            	if(check && inputLine.toLowerCase().contains(name.toLowerCase()) && inputLine.contains("Abjects"))        {   		
            		fileList.add(new XDCCfile(counter));      
            			
            			String bot = null, num;
            		Scanner sca = new Scanner(inputLine);
            		
            		String str = inputLine.substring(0, nthOccurrence(inputLine,' ',1)).replace("...","");
            		System.out.println("str:" + str);
            		fileList.get(counter).setFilename(str);
            		


            		String str1=inputLine.substring(nthOccurrence(inputLine,'#',1));
            		fileList.get(counter).setChannel(str1.substring(0,nthOccurrence(str1,' ', 1)));
            		
            		int ctr=0;

      

            		Scanner scanner = new Scanner (str1);
            		scanner.next();
            		String oi=scanner.next();
            		fileList.get(counter).setFilesize(oi);
            		oi=scanner.next();
            		fileList.get(counter).setDW(oi);

            		num=inputLine.substring(inputLine.lastIndexOf("#"));

            		do{
                		inputLine = in.readLine();
            		}while(!inputLine.contains("Bot"));
            		
            		                                         
            		bot=inputLine.substring(inputLine.lastIndexOf(":")+1).replaceAll(" ", "");
            		
        			fileList.get(counter).setLink("/msg "+bot+" xdcc send "+num);
        			
        			counter++;

            		

            		
            	//	fileList.get(counter).setLink();

            		/*
            		fileList.get(counter).setLink();
            		
            		fileList.get(counter).setFilename();
            		
            		fileList.get(counter).setChannel();
            		
            		for(int i=0;i<2;i++)
            			inputLine = in.readLine();
            			
            		fileList.get(counter).setFilesize();
            		
            		for(int i=0;i<3;i++)
            			inputLine = in.readLine();
            		
            		fileList.get(counter).setDW(); */           		

            	}
            	
            	/*
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

            	}*/
            	
            }}
        
        

	

	
	
		
	
	

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
