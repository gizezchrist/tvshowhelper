package tracker.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.omg.CORBA.portable.OutputStream;
import org.w3c.tidy.Tidy;

public class asdasd extends Thread {

	
	
	public static void main(String[] args) throws InterruptedException, IOException{
		
		Tidy tidy = new Tidy();
		
		
		BufferedReader in = null;        	
		//URL url = new URL("http://www.google.pt");
		
			//URL url = new URL("http://www.xdccfinder.com/results.php?kw=arrow_s01e18_720p");
		URL url = new URL("https://w3dt.net/tools/textbrowser/?host=http://www.xdccfinder.com/results.php?kw=arrow_s01e18_720p&submit=Browse&clean_opt=1");
    	HttpURLConnection  urlc;
        SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sslsocket = (SSLSocket) ssf.createSocket("somehost.dk", 3049);
    		
    		InputStream inputstream = sslsocket.getInputStream();
          urlc = (HttpURLConnection) url.openConnection();
          urlc.setDoOutput(true);
          urlc.addRequestProperty("user-agent", "Firefox");
          in = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
          String inputLine;

          while ((inputLine = in.readLine()) != null){
              System.out.println(inputLine);
          }
          
          Socket s = ssf.createSocket("https://w3dt.net/tools/textbrowser/?host=http://www.xdccfinder.com/results.php?kw=arrow_s01e18_720p&submit=Browse&clean_opt=1", 5432);

          OutputStream outs = (OutputStream) s.getOutputStream();
          
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
