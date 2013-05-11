package chat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class Main {

	static JScrollPane scroll;
	static JFrame frame;
	static JPanel panel;
	static  JTextField box;
	static Update update;
	public static void main(String[] args){
		
		
		//sec.start();ds
		
		  frame = new JFrame();
		  frame.setLayout(new GridLayout(2,1));
		  JTextField textfield1 = new JTextField("11111");
		  JTextField textfield2 = new JTextField("2222");
		 box = new JTextField("Write Here");
		 
		  textfield1.setEditable(false);
		  textfield2.setEditable(false);
//		  frame.setLayout(new GridLayout(1,1));

		  box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
 
            	try {
					update.hey(box.getText(),0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
          	  JScrollBar vert=scroll.getVerticalScrollBar();
    		  /* Don't know why, but its necessary to initate value to a number != 0 before attributing maximum*/
          	  
    		  vert.setValue(vert.getMaximum());	
    		  scroll.setVerticalScrollBar(vert);            	
            	
    		  scroll.revalidate();
          	  scroll.repaint();

          	
}
        });
		  //in.start();
		  
		  panel = new JPanel();
		  panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		  
		  for(int i=0;i<35;i++)
			  panel.add(new JTextField("oi"+i));
		  
		  scroll = new JScrollPane(panel);
		  scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		  scroll.setPreferredSize(new Dimension(225,200));
		  
		  JScrollBar vert=scroll.getVerticalScrollBar();
		  /* Don't know why, but its necessary to initate value to a number != 0 before attributing maximum */
		  vert.setValue(1);
		  vert.setValue(vert.getMaximum());
		  scroll.setVerticalScrollBar(vert);
		  //vertical.setValue( vertical.getMaximum() );
		update = new Update(panel);
		  Secondary in = new Secondary(update);
		  in.start();
		  
	      frame.add(scroll);
	      frame.add(box);
	      frame.pack();
	      frame.setVisible(true);
		
	}
	
}
