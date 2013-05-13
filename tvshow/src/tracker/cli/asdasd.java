package tracker.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class asdasd extends Thread {

	
	
	public static void main(String[] args) throws InterruptedException, IOException{
		
		BufferedReader in = null;        	
    	URL url = new URL("http://www.xdccfinder.com/results.php?kw=arrow_s01e18_720p");
    	//        URL url = new URL("https://w3dt.net/tools/textbrowser/?host=http://www.xdccfinder.com/results.php?kw=arrow_s01e18_720p&submit=Browse&clean_opt=1");

    	HttpURLConnection  urlc;
        urlc = (HttpURLConnection) url.openConnection();
      //	  urlc.setRequestMethod("POST");
        urlc.setDoOutput(true);
       // urlc.addRequestProperty("user-agent", "Firefox");
    	urlc.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
       // urlc.setRequestProperty("Accept", "text/html; charset=utf-8");
        
        //urlc.setRequestProperty("Accept-Language", "en-us,en;q=0.5");
        urlc.setAllowUserInteraction(true);
        in = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
        String inputLine;
        
        

        while ((inputLine = in.readLine()) != null){
        	System.out.println(inputLine);
        }
	}
	
	public asdasd (asdasd hell) throws InterruptedException{

		for(int i=0;i<5;i++){
			System.out.println("hi");
			sleep(1000);
		}
		hell.notify("oi");
		
	}
	
	public void notify(String str) {
		System.out.println(str);		
	}

	public asdasd() throws InterruptedException{
		System.out.println("HdAAAAI");
		asdasd var= new asdasd(this);
			
		var.start();
		join(0);
		System.out.println("2");
		var.start();
		join(0);
		System.out.println("HAAAAI");

		}
	
	public void run(){

	}
		
	
}
