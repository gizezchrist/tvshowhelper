package chat;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Secondary extends Thread implements ActionListener{
	private JTextField textfield1;
	private Update update;
	private JFrame frame;
	
	public Secondary(Update update){
		this.update=update;
	}


	public void run(){
		
		
			System.out.println("server");
			Server sv;
				try {
					sv = new Server(update);
					sv.start();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

	}




	@Override
	public void actionPerformed(ActionEvent arg0) {

	//	textOUT.setText(textfield1.getText());
		System.out.println("action");
	}
	
}



class Server extends Thread{
	public Server(Update update) throws InterruptedException, IOException
	{
		System.out.println("server");
		//update.hey("server entered the room",0);
		
		String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket;
        if(update.player==1)
        	welcomeSocket = new ServerSocket(6789);
        else
        	welcomeSocket = new ServerSocket(6788);
        
         while(true)
         {
        	 
             System.out.println("waiting for client");

            Socket connectionSocket = welcomeSocket.accept();
            
            BufferedReader inFromClient =
               new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
    		update.hey(clientSentence,0);
           /* capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capitalizedSentence);*/
         }
	}
}
