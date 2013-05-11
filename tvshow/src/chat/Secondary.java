package chat;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		
			try {
				update.hey("oi",1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  /*frame = new JFrame("OKAPA");
			
		  textfield1 = new JTextField("1111");

		  textfield1.addActionListener(this);
      
		  frame.setLayout(new GridLayout(1,2));
		  
		  frame.add(textfield1);
	      frame.pack();
	      frame.setVisible(true);*/	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	//	textOUT.setText(textfield1.getText());
		System.out.println("action");
	}
	
}
