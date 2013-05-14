package tracker.cli;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import uriSchemeHandler.CouldNotOpenUriSchemeHandler;
import uriSchemeHandler.URISchemeHandler;



public class TVShowHelper extends JFrame{

	static JPanel panelF;
	static JPanel panelXDCC,panelPB;
	static JPanel panelL;
	static JPanel panelLF;
	static JTextField lateral;
	static JFrame frame;
	/**
	 * @param argse
	 * @throws IOException d
	 */
	public static void main(String[] args)  throws IOException  {
		frame= new JFrame("TVshow Helper");
		
		JFrame frameLOADING=new JFrame("TVshow Helper");
		JTextField loading= new JTextField("LOADING");
		loading.setEditable(false);
	    loading.setHorizontalAlignment(JTextField.CENTER);
		frameLOADING.add(loading,BorderLayout.CENTER);
		frameLOADING.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frameLOADING.setSize(new Dimension(200,100));
		frameLOADING.setLocationRelativeTo(null);
		frameLOADING.setVisible(true);
		

		frame= new JFrame("TVshow Helper");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		// frame.setLayout(new GridLayout(1, 2));
		addComponents(frame.getContentPane());

		lateral = new JTextField("Welcome");
		lateral.setEditable(false);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frameLOADING.dispose();
	}


	private static void addComponents(Container pane) throws NumberFormatException, IOException{

		int j=0;
		JTextField textfield;
		JPanel panelB;
		JPanel tmp;
		String inputLine;
		ArrayList<ShowStats> seriesList = new ArrayList<ShowStats>();


		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		//Put the JComboBox in a JPanel to get a nicer look.
		JPanel comboBoxPane = new JPanel(); //use FlowLayout

		//String comboBoxItems[] = { "oi", "oi2" };
		JComboBox<String> cb = new JComboBox<String>();
		cb.setEditable(false);
		cb.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent e) {
				CardLayout cl = (CardLayout)(panelF.getLayout());
				cl.show(panelF, (String)e.getItem());
			}
		});
		comboBoxPane.add(cb);


		BufferedReader in = new BufferedReader(new FileReader("test.txt"));
		panelF = new JPanel(new CardLayout());

		/* List of TVshows */
		while ((inputLine = in.readLine()) != null){
			seriesList.add(new ShowStats(inputLine.substring(0,inputLine.indexOf("|")),Integer.parseInt(inputLine.substring(inputLine.indexOf("|")+1))));
			panelB = new JPanel();
			panelB.setLayout(new BoxLayout(panelB,BoxLayout.Y_AXIS));

			int k=0;
			textfield = new JTextField();
			textfield.setText(seriesList.get(j).getShowName());
			textfield.setEditable(false);

			/* List of Episodes */
			while(k<=(seriesList.get(j).getTotalEpisodes()-1)){

				textfield = new JTextField();
				textfield.setEditable(false);
				textfield.setText("\nEpisode "+(k+1)+" => "+seriesList.get(j).getShowDates().get(k));
				k++;

				if(k<10)
					textfield.setName(seriesList.get(j).getShowName().replaceAll(" ", ".")+".s0"+seriesList.get(j).getSeason()+"e0"+k+".720p");
				else
					textfield.setName(seriesList.get(j).getShowName().replaceAll(" ", ".")+".s0"+seriesList.get(j).getSeason()+"e"+k+".720p");
				textfield.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						JTextField tmp = (JTextField) evt.getSource();

						try {
							Piratebayinfo pb =new Piratebayinfo(tmp.getName());
							XDCCinfo xdcc =new XDCCinfo(tmp.getName());
							if(pb.getFileList().size()>0)
								pbPanel(pb.getFileList());
							else
								pbPanel(null);

							if(xdcc.getFileList().size()>0)
								xdccPanel(xdcc.getFileList());
							else
								xdccPanel(null);



							frame.pack();

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (URISyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (CouldNotOpenUriSchemeHandler e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (KeyManagementException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});
				panelB.add(textfield,""+k);
			}

			JScrollPane scroll = new JScrollPane(panelB);
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setPreferredSize(new Dimension(225,400));

			cb.addItem(seriesList.get(j).getShowName());
			panelF.add(scroll,seriesList.get(j).getShowName()); 
			j++;
		}
		tmp=new JPanel();
		tmp.setLayout(new BoxLayout(tmp,BoxLayout.Y_AXIS));
		tmp.add(comboBoxPane);
		tmp.add(panelF);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(tmp,c);
		
		c.gridx = 1;		
		pane.add(lateralPane(),c);
	}


	private static JComponent lateralPane() throws NumberFormatException, IOException{
		panelLF=new JPanel();
		panelXDCC=new JPanel();
		panelPB=new JPanel();
		panelL=new JPanel(new CardLayout());
		panelLF.setLayout(new BoxLayout(panelLF,BoxLayout.Y_AXIS));

		
		JPanel comboBoxPaneLateral = new JPanel(); //use FlowLayout
		JComboBox<String> cbLateral = new JComboBox<String>();  
		cbLateral.setEditable(false);
		cbLateral.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent e) {
				CardLayout clLateral = (CardLayout)(panelL.getLayout());
				clLateral.show(panelL, (String)e.getItem());
			}
		});
		comboBoxPaneLateral.add(cbLateral);

		xdccPanel(null);
		pbPanel(null);

		cbLateral.addItem("Pirate Bay");
		cbLateral.addItem("xWeasel");
		panelL.add(panelPB,"Pirate Bay");
		panelL.add(panelXDCC,"xWeasel");
		panelLF.add(comboBoxPaneLateral, BorderLayout.PAGE_START);
		panelLF.add(panelL, BorderLayout.CENTER);
		return panelLF;
	}

	private static void xdccPanel(ArrayList<XDCCfile> fileList){
		panelXDCC.removeAll();
		panelXDCC.setLayout(new BoxLayout(panelXDCC,BoxLayout.Y_AXIS));

		if(fileList==null){
			JTextField origin = new JTextField("XDCC");
			origin.setEditable(false);
			panelXDCC.add(origin);
		}
		else{
			JPanel tmp = new JPanel();
			tmp.setLayout(new BoxLayout(tmp,BoxLayout.Y_AXIS));
			JTextField origin;
			for(int i=0;i<fileList.size();i++){

				origin = new JTextField();
				origin.setText(fileList.get(i).getFilename());
				//origin.setToolTipText(\);
				JTextField desc = new JTextField();
				desc.setEditable(false);
				Font font1 = new Font("Dialog.plain", 0, 10);
				desc.setFont(font1);
				desc.setText(fileList.get(i).getFilesize()+" | "+fileList.get(i).getChannel()+" | "+fileList.get(i).getDW()+" | "+fileList.get(i).getID());
				origin.setName(fileList.get(i).getLink());
				origin.setEditable(false);

				origin.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						JTextField tmp = (JTextField) evt.getSource();
						StringSelection stringSelection = new StringSelection (tmp.getName());
						Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
						clpbrd.setContents (stringSelection, null);

					}});

				tmp.add(origin);
				tmp.add(desc);

			}


			JScrollPane scroll = new JScrollPane(tmp);  
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

			scroll.setPreferredSize(new Dimension(400, 100));
			panelXDCC.add(scroll);

		}


	}

	private static void pbPanel(ArrayList<PBfile> fileList){

		panelPB.removeAll();
		panelPB.setLayout(new BoxLayout(panelPB,BoxLayout.Y_AXIS));


		if(fileList==null){
			JTextField origin = new JTextField("PB");
			origin.setEditable(false);
			panelPB.add(origin);
		}
		else{
			JPanel tmp = new JPanel();
			tmp.setLayout(new BoxLayout(tmp,BoxLayout.Y_AXIS));
			JTextField origin;
			for(int i=0;i<fileList.size();i++){

				origin = new JTextField();
				origin.setText(fileList.get(i).getFilename());
				//origin.setToolTipText(fileList.get(i).getFilesize()+" | "+fileList.get(i).getSeeders()+" | "+fileList.get(i).getLeechers());
				JTextField desc = new JTextField();
				desc.setEditable(false);
				Font font1 = new Font("Dialog.plain", 0, 10);
				desc.setFont(font1);
				desc.setText(fileList.get(i).getFilesize()+" | "+fileList.get(i).getSeeders()+" | "+fileList.get(i).getLeechers()+" | "+fileList.get(i).getID());
				origin.setName(fileList.get(i).getMagnetLink());
				origin.setEditable(false);

				origin.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						JTextField tmp = (JTextField) evt.getSource();

						URI magnetLinkUri = null;
						try {
							magnetLinkUri = new URI(tmp.getName());
						} catch (URISyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						URISchemeHandler uriSchemeHandler = new URISchemeHandler();
						try {
							uriSchemeHandler.open(magnetLinkUri);
						} catch (CouldNotOpenUriSchemeHandler e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}});

				tmp.add(origin);
				tmp.add(desc);
				
			}


			JScrollPane scroll = new JScrollPane(tmp);  
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

			scroll.setPreferredSize(new Dimension(225, 400));
			panelPB.add(scroll);
			

		}
	}

}
