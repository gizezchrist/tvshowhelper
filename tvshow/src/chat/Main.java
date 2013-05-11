package chat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

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
	static JTextField box;
	static Update update;

	public static void main(String[] args) {

		// sec.start();ds

		frame = new JFrame();
		frame.setLayout(new GridLayout(2, 1));
		JTextField textfield1 = new JTextField("11111");
		JTextField textfield2 = new JTextField("2222");
		box = new JTextField("Write Here");

		textfield1.setEditable(false);
		textfield2.setEditable(false);
		// frame.setLayout(new GridLayout(1,1));

		box.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					String ip="localhost";
					update.sendSocket(box.getText(),ip);
					update.hey(box.getText(), 0);
				} catch (InterruptedException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		box.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseClicked(java.awt.event.MouseEvent evt) {

			JTextField tmp= (JTextField) evt.getSource();
			if(tmp.getText().equals("Write Here"))
					tmp.setText("");
			}
			
		});

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		for (int i = 0; i < 35; i++)
			panel.add(new JTextField("oi" + i));

		scroll = new JScrollPane(panel);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(225, 200));
		scroll.getVerticalScrollBar().addAdjustmentListener(
				new java.awt.event.AdjustmentListener() {

					@Override
					public void adjustmentValueChanged(AdjustmentEvent arg0) {
						arg0.getAdjustable().setValue(arg0.getAdjustable().getMaximum());
						
					}

				});
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("state your number");
		update = new Update(panel,sc.nextInt());
		Secondary in = new Secondary(update);
		in.start();

		frame.add(scroll);
		frame.add(box);
		frame.pack();
		frame.setVisible(true);

	}

}