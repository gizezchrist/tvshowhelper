package chat;

import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Update {

	private JPanel panel1;
	public Update(JPanel panel1){

		this.panel1=panel1;
	}

	public synchronized void hey(String hey, int i) throws InterruptedException
	{
		Scanner sc = new Scanner (System.in);
		if(i==1)
			while(true){
				Thread.sleep(1000);
				panel1.add(new JTextField(hey));
				panel1.revalidate();
				panel1.repaint();
				
				System.out.println("adding"+i);
				i++;
				if(sc.nextInt()==0)
					break;
				
			}
		else{

			panel1.add(new JTextField(hey));
			panel1.revalidate();
			panel1.repaint();
		}

	}


}
