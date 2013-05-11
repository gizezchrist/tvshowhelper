package chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Update {

	private JPanel panel1;
	public int player;
	public Update(JPanel panel1, int player){

		this.panel1=panel1;
		this.player=player;
	}

	public synchronized void hey(String hey, int i) throws InterruptedException
	{
	
			panel1.add(new JTextField(hey));
			panel1.revalidate();
			panel1.repaint();
	
	}

	public void sendSocket(String sentence,String ip) throws UnknownHostException, IOException {
		  
		  String modifiedSentence;
		  //BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
		  Socket clientSocket;
		  
		  if(this.player==1)
			  clientSocket = new Socket(ip, 6788);
	        else
	  		  clientSocket = new Socket(ip, 6789);
		  
		  DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		  //BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		  //sentence = inFromUser.readLine();
		  outToServer.writeBytes(player+sentence + " | from client");
		  
		  System.out.println("CLIENTGONNACLOSE");
		  clientSocket.close();
		
	}


}
