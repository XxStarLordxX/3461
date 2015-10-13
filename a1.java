package a1;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;




public class a1
{
	public static void main(String[] args)
	{
		DemoSwingFrame frame = new DemoSwingFrame();
		frame.setTitle("York Parking Permit Kiosk");
		frame.pack();

		// put the frame in the middle of the display
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

		frame.setVisible(true);
	}
}

// -----------------------------
// define the application window
// -----------------------------

class DemoSwingFrame extends JFrame implements ActionListener, FocusListener
{
	private static final long serialVersionUID = 1L;
	
	private static final Dimension TEXTFIELD_SIZE = new Dimension(300, 35);
	
	private ArrayList<String> SNs = new ArrayList<String>();
	private ArrayList<String> PINs = new ArrayList<String>();
	private ArrayList<String> FNs = new ArrayList<String>();
	private ArrayList<String> GNs = new ArrayList<String>();
	private ArrayList<String> status = new ArrayList<String>();
	
	private ArrayList<String> companies = new ArrayList<String>();
	

	private GridBagConstraints c = new GridBagConstraints();
	private JButton p1_startButton;
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private JPanel p4;
	private JPanel p5;

	private JButton p2_Next = new JButton("Next");
	private JButton p2_1 = new JButton("1");
	private JButton p2_2 = new JButton("2");
	private JButton p2_3 = new JButton("3");
	private JButton p2_4 = new JButton("4");
	private JButton p2_5 = new JButton("5");
	private JButton p2_6 = new JButton("6");
	private JButton p2_7 = new JButton("7");
	private JButton p2_8 = new JButton("8");
	private JButton p2_9 = new JButton("9");
	private JButton p2_0 = new JButton("0");
	private JButton p2_Clear = new JButton("Clear");
	private JButton p2_C = new JButton("<--");
	private StringBuffer SN = new StringBuffer("Please Enter Your Student Number      ");
	private StringBuffer PIN = new StringBuffer("Please Enter Your PIN");
	private JTextField p2_SNInput = new JTextField(SN.toString());
	private JTextField p2_PINInput = new JTextField(PIN.toString());
	private JLabel p2_SN_error = new JLabel();
	private JLabel p2_PIN_error = new JLabel();
	private boolean isSN = false;
	private boolean isPIN = false;
	
	private JButton p3_Next = new JButton("Next");
	private JButton p3_Back = new JButton("Back");
    private JButton[][] button  = new JButton[20][20];
    private static final String[][] p3_key = {
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "<--"}, 
        {"q", "w", "e", "r", "t", "y","u", "i", "o", "p", }, 
        {"Caps", "a", "s", "d", "f", "g", "h", "j", "k","l"}, 
        {"Clear", "z","x", "c", "v", "b", "n", "m", ".", "@"}};
    private Boolean isEmail = false;
    private Boolean isUpperCase = false;
	private StringBuffer email = new StringBuffer("Please Enter Your Email Address");
	private JTextField p3_email = new JTextField(email.toString());
	
	private JButton p4_Next = new JButton("Next");
	private JButton p4_Back = new JButton("Back");
	
	
	
	private String step_template_head = "<html><b><h1 style="
			+ "\"font-size:25px;margin-left: 20px; margin-right: 20px; margin-top: 20px; margin-bottom: 20px\">" ;
	private String step_template_tail = "</h1></b></html>";
	private ImageIcon logo1 = new ImageIcon("images/logo1.png");
	private ImageIcon logo2 = new ImageIcon("images/logo2.png");

	// -----------
	// constructor
	// -----------

	public DemoSwingFrame()
	{
		 String line;
		 BufferedReader br1, br2;
		try {
			br1 = new BufferedReader(new FileReader("databases/students.txt"));
			while((line = br1.readLine()) != null){
				String[] temp = line.split(",");
				SNs.add(temp[0].trim());
				PINs.add(temp[1].trim());
				FNs.add(temp[2].trim());
				GNs.add(temp[3].trim());
				status.add(temp[4].trim());
			}
			br2 = new BufferedReader(new FileReader("databases/companies.txt"));
			while((line = br2.readLine()) != null){
				line = line.trim();
				line = "<html><h4 style="
						+ "\"font-size:10px;margin-left: 5px; margin-right: 5px; margin-top: 5px; margin-bottom: 5px\">"
						+ line
						+ "</h4></html>";
				companies.add(line);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
// -------------------------------------------------------------------------------------------------------
//									 		The first panel.
//-------------------------------------------------------------------------------------------------------

		JPanel p1_subP1 = new JPanel();
		JLabel p1_yorkuParking;
		JLabel p1_pic;
		
		
		// -------------------------------
		// create and configure components
		// -------------------------------


		p1_yorkuParking = new JLabel("<html><b>" + "<h1 style="
				+ "\"font-size:45px;margin-left: 50px; margin-right: 50px; margin-top: 0px; margin-bottom: 50px\"" 
				+ "<font color = \"Black\">Y</font>" 
				+ "<font color = \"Black\">ork</font>"
				+ "<font color = \"Red\">U </font>"
				+ "<font color = \"Red\">P</font>"
				+ "<font color = \"Black\">arking</font>" + "</h1></b></html>");
		p1_yorkuParking.setHorizontalAlignment(SwingConstants.CENTER);
		
		p1_pic = new JLabel(logo1);
		
		p1_startButton = new JButton("<html> <h1 style=\"color:white;"
				+ "font-size:20px;margin-left: 30px; margin-right: 30px; margin-top: 50px; margin-bottom: 50px"
				+ "\">Start</h1></html>");
				
		p1_startButton.setPreferredSize(new Dimension(400, 60));
		p1_startButton.setBackground(Color.RED);
		p1_startButton.setFocusPainted(false);

		// -------------
		// add listeners
		// -------------

		p1_startButton.addActionListener(this);
		this.addWindowListener(new WindowCloser());

		// ------------------
		// arrange components
		// ------------------

		// put components in a panel

		p1 = new JPanel();
		p1.setBackground(Color.WHITE);
		p1.setBorder(BorderFactory.createEmptyBorder(100, 100, 10, 100));
		p1.setLayout(new GridLayout(3, 1));
		
		
		
		
		p1_subP1.add(p1_startButton);
		p1_subP1.setBackground(Color.WHITE);
		p1_subP1.add(p1_startButton);
		

		p1.add(p1_pic);
		p1.add(p1_yorkuParking);
		p1.add(p1_subP1);
		
		p1.setPreferredSize(new Dimension(800, 600));

		// make the panel this extended JFrame's content pane

		this.setContentPane(p1);
		
		
		
// -------------------------------------------------------------------------------------------------------
//										 The second panel.
// -------------------------------------------------------------------------------------------------------
		
		JPanel p2_subP1 = new JPanel(new GridLayout(4, 1));
		p2_subP1.setBackground(Color.WHITE);
		JPanel p2_subP2 = new JPanel(new GridLayout(4, 3));
		JLabel p2_pic;
		JLabel p2_step = new JLabel(step_template_head + "Step 1: " +
		"Personal Information" + step_template_tail);
		
		p2_1.setPreferredSize(new Dimension(40, 45));
		
		p2 = new JPanel(new GridBagLayout());
		p2.setBackground(Color.WHITE);
		p2.setPreferredSize(p1.getPreferredSize());
		
		// The logo at top of the panel;
		p2_pic = new JLabel(new ImageIcon(logo2.getImage().getScaledInstance(400, 144, Image.SCALE_SMOOTH)));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		c.insets = new Insets(0, 0, 0, 0);
		p2.add(p2_pic, c);
		c.insets = new Insets(0, 0, 0, 0);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 4;
		p2.add(p2_step, c);
		
		// The bottom-left sub-panel
		p2_SNInput.setPreferredSize(new Dimension(250, 35));
		p2_subP1.add(p2_SNInput);
		p2_subP1.add(p2_SN_error);
		p2_subP1.add(p2_PINInput);
		p2_subP1.add(p2_PIN_error);
		
		
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.weightx = 0.5;
		c.insets = new Insets(0, 120, 0, 0);
		p2.add(p2_subP1, c);
		c.insets = new Insets(0, 0, 0, 0);
		
		p2_SNInput.addFocusListener(this);
		p2_PINInput.addFocusListener(this);
		
		// The bottom-right sub-panel.
		p2_subP2.add(p2_1);
		p2_1.addActionListener(this);
		p2_subP2.add(p2_2);
		p2_2.addActionListener(this); 	
		p2_subP2.add(p2_3);
		p2_3.addActionListener(this);
		p2_subP2.add(p2_4);
		p2_4.addActionListener(this);
		p2_subP2.add(p2_5);
		p2_5.addActionListener(this);
		p2_subP2.add(p2_6);
		p2_6.addActionListener(this);
		p2_subP2.add(p2_7);
		p2_7.addActionListener(this);
		p2_subP2.add(p2_8);
		p2_8.addActionListener(this);
		p2_subP2.add(p2_9);
		p2_9.addActionListener(this);
		p2_subP2.add(p2_Clear);
		p2_Clear.addActionListener(this);
		p2_subP2.add(p2_0);
		p2_0.addActionListener(this);
		p2_subP2.add(p2_C);
		p2_C.addActionListener(this);
		
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 2;
		c.weightx = 0.5;
		c.insets = new Insets(0, 0, 0, 70);
		p2.add(p2_subP2, c);
		c.insets = new Insets(0, 0, 0, 0);
		
		p2_Next.addActionListener(this);
		p2_Next.setPreferredSize(TEXTFIELD_SIZE);
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 4;
		c.weightx = 0.5;
		c.insets = new Insets(30,0,0,0);
		p2.add(p2_Next, c);
		
		c.insets = new Insets(0,0,0,0);
		
		
// -------------------------------------------------------------------------------------------------------
//		 										The third panel.
//-------------------------------------------------------------------------------------------------------
		
		JPanel p3_subP1 = new JPanel(new GridLayout(2, 1));
		p3_subP1.setBackground(Color.WHITE);
		JPanel p3_subP2 = new JPanel(new GridLayout(4, 1));
		JPanel p3_subP3 = new JPanel();
		JLabel p3_pic;
		JLabel p3_step = new JLabel(step_template_head + "Step 2: " +
		"Email Address (optional)" + step_template_tail);
		JLabel p3_info_label = new JLabel("(Or you can skip this step by simply clicking on \"Next\")");

		
		p3 = new JPanel(new GridBagLayout());
		p3.setBackground(Color.WHITE);
		p3.setPreferredSize(p2.getPreferredSize());
		
		// The logo at top of the panel;
		p3_pic = new JLabel(new ImageIcon(logo2.getImage().getScaledInstance(400, 144, Image.SCALE_SMOOTH)));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		p3.add(p3_pic, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 4;
		p3.add(p3_step, c);

		// The bottom-left sub-panel
		p3_email.setPreferredSize(TEXTFIELD_SIZE);
		p3_email.addFocusListener(this);
		
		p3_subP1.add(p3_email);
		p3_info_label.setForeground(Color.RED);
		p3_subP1.add(p3_info_label);
		
		p3_Next.setPreferredSize(TEXTFIELD_SIZE);
		p3_Next.addActionListener(this);
		p3_Back.setPreferredSize(TEXTFIELD_SIZE);
		p3_Back.addActionListener(this);
		
		
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.weightx = 0.5;
		p3.add(p3_subP1, c);


		
		JPanel[] p3_rows = new JPanel[6];
        for (int row = 0; row < p3_key.length; row++) {
        	p3_rows[row] = new JPanel();
            for (int column = 0; column < p3_key[row].length; column++) {
                button[row][column] = new JButton(p3_key[row][column]);
                button[row][column].addActionListener(this);
                p3_rows[row].add(button[row][column]);
                p3_rows[row].setBackground(Color.WHITE);
            }
            p3_subP2.add(p3_rows[row]);
        }
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 4;
		c.weightx = 0.5;;
		p3.add(p3_subP2, c);
		
		p3_subP3.setBackground(Color.WHITE);
		p3_subP3.add(p3_Back);
		p3_subP3.add(p3_Next);
		
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 4;
		c.weightx = 0.5;
		c.insets = new Insets(20,0,0,0);
		p3.add(p3_subP3, c);
		c.insets = new Insets(0,0,0,0);
		
// -------------------------------------------------------------------------------------------------------
//				 								The forth panel.
//-------------------------------------------------------------------------------------------------------
		
		JPanel p4_subP1 = new JPanel();
		JPanel p4_subP2 = new JPanel();
		JLabel p4_pic;
		JLabel p4_step = new JLabel(step_template_head + "Step 3: " +
		"Insurance Information" + step_template_tail);
		JList<JLabel> p4_list = new JList(companies.toArray());
		
		for(int i = 0; i < companies.size(); i++){
			JLabel temp = new JLabel(companies.get(i));
			p4_list.add(temp);
		}
		
		
		p4_list.setSelectionBackground(Color.LIGHT_GRAY);
		p4_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//p4_list.setBackground(Color.GRAY);
		p4_list.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		p4_list.setSelectionBackground(new Color(0, 128, 255));
		p4_list.setSelectionForeground(Color.WHITE);
		
		p4 = new JPanel(new GridBagLayout());
		p4.setBackground(Color.WHITE);
		p4.setPreferredSize(p1.getPreferredSize());
		
		// The logo at top of the panel;
		p4_pic = new JLabel(new ImageIcon(logo2.getImage().getScaledInstance(400, 144, Image.SCALE_SMOOTH)));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		p4.add(p4_pic, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		p4.add(p4_step, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		p4.add(p4_list, c);
		
		p4_Next.setPreferredSize(TEXTFIELD_SIZE);
		p4_Next.addActionListener(this);
		p4_Back.setPreferredSize(TEXTFIELD_SIZE);
		p4_Back.addActionListener(this);
		
		p4_subP2.setBackground(Color.WHITE);
		p4_subP2.add(p4_Back);
		p4_subP2.add(p4_Next);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.weightx = 0.5;
		c.insets = new Insets(20,0,0,0);
		
		p4.add(p4_subP2, c);
		
		// -------------------------------------------------------------------------------------------------------
		//			The fifth panel.
		//-------------------------------------------------------------------------------------------------------
		
		JPanel p5_subP1 = new JPanel();
		JPanel p5_subP2 = new JPanel();
		JLabel p5_pic;
		JLabel p5_step = new JLabel(step_template_head + "Step 3: " +
		"Insurance Information" + step_template_tail);
		
		p5 = new JPanel(new GridBagLayout());
		p5.setBackground(Color.WHITE);
		p5.setPreferredSize(p1.getPreferredSize());
		
		// The logo at top of the panel;
		p5_pic = new JLabel(new ImageIcon(logo2.getImage().getScaledInstance(400, 144, Image.SCALE_SMOOTH)));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		p5.add(p5_pic, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		p5.add(p5_step, c);

		// The bottom-left sub-panel
		p3_email.setPreferredSize(TEXTFIELD_SIZE);
		p3_email.addFocusListener(this);
		
		p3_subP1.add(p3_email);
		p3_info_label.setForeground(Color.RED);
		p3_subP1.add(p3_info_label);
		
		p3_Next = new JButton("Next");
		p3_Next.setPreferredSize(TEXTFIELD_SIZE);
		p3_Next.addActionListener(this);
		p3_Back.setPreferredSize(TEXTFIELD_SIZE);
		p3_Back.addActionListener(this);
		
		
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.weightx = 0.5;
		p3.add(p3_subP1, c);


		
		JPanel[] p5_rows = new JPanel[6];
        for (int row = 0; row < p3_key.length; row++) {
        	p5_rows[row] = new JPanel();
            for (int column = 0; column < p3_key[row].length; column++) {
                button[row][column] = new JButton(p3_key[row][column]);
                button[row][column].addActionListener(this);
                p3_rows[row].add(button[row][column]);
                p3_rows[row].setBackground(Color.WHITE);
            }
            p3_subP2.add(p3_rows[row]);
        }
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 4;
		c.weightx = 0.5;;
		p3.add(p3_subP2, c);
		
		p3_subP3.setBackground(Color.WHITE);
		p3_subP3.add(p3_Back);
		p3_subP3.add(p3_Next);
		
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 4;
		c.weightx = 0.5;
		c.insets = new Insets(20,0,0,0);
		p3.add(p3_subP3, c);
		c.insets = new Insets(0,0,0,0);
	
	}

	// -------------------------------
	// implement ActionListener method
	// -------------------------------

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		
		if (ae.getSource() == p1_startButton){
			this.remove(p1);
			this.setContentPane(p2);
			this.pack();
		}
		
		
		/*
		 * Panel 2 buttons
		 */
		else if(ae.getSource() == p2_Next){

			if(SNs.contains(p2_SNInput.getText()) && 
					PINs.get(SNs.indexOf(p2_SNInput.getText())).equals(p2_PINInput.getText()) &&
					status.get(SNs.indexOf(p2_SNInput.getText())).equals("ok")){
				p2_SN_error.setText("");
				p2_PIN_error.setText("");
				this.remove(p2);
				this.setContentPane(p3);
				this.pack();
			}
			else if(!SNs.contains(p2_SNInput.getText())){
				p2_SN_error.setText("*Invalid Student Number!");
				p2_SN_error.setForeground(Color.RED);
				p2_PIN_error.setText("");
				Toolkit.getDefaultToolkit().beep();

			}
			else if(!PINs.get(SNs.indexOf(p2_SNInput.getText())).equals(p2_PINInput.getText())){
				p2_PIN_error.setText("*Incorrect PIN!");
				p2_PIN_error.setForeground(Color.RED);
				p2_SN_error.setText("");
				Toolkit.getDefaultToolkit().beep();
			}
			else if(!status.get(SNs.indexOf(p2_SNInput.getText())).equals("ok")){
				p2_SN_error.setText("*Sorry, you have outstanding parking fines. Can not issue parking permit.");
				p2_SN_error.setForeground(Color.RED);
				p2_PIN_error.setText("");
				Toolkit.getDefaultToolkit().beep();
			}
			
		}
		else if(this.getContentPane().equals(p2) && ((JButton)ae.getSource()).getText() != "Clear" && 
				((JButton)ae.getSource()).getText() != "<--"){
			if(isSN && !isPIN){
				SN.append(((JButton)ae.getSource()).getText());
				p2_SNInput.setText(SN.toString());
			}
			else if(!isSN && isPIN){
				PIN.append(((JButton)ae.getSource()).getText());
				p2_PINInput.setText(PIN.toString());
			}
			
		}
		else if(ae.getSource() == p2_Clear){
			if(isSN && !isPIN){
				SN.delete(0, SN.length());
				p2_SNInput.setText(SN.toString());
			}
			else if(!isSN && isPIN){
				PIN.delete(0, PIN.length());
				p2_PINInput.setText(PIN.toString());
			}
			
		}
		else if(ae.getSource() == p2_C){
			if(isSN && !isPIN){
				if(SN.length() != 0)
					SN.deleteCharAt(SN.length() - 1);
				p2_SNInput.setText(SN.toString());
			}
			else if(!isSN && isPIN){
				if(PIN.length() != 0)
						PIN.deleteCharAt(PIN.length() - 1);
				p2_PINInput.setText(PIN.toString());
			}
			
		}
		/*
		 * Panel 3 buttons
		 */
		else if(ae.getSource() == p3_Next){
			this.remove(p3);
			this.setContentPane(p4);
			this.pack();
		}
		else if(ae.getSource() == p3_Back){
			this.remove(p3);
			this.setContentPane(p2);
			this.pack();
		}
		else if(this.getContentPane().equals(p3)){
			String text = ((JButton)ae.getSource()).getText();
			if(!(text.equals("Caps") ||
				text.equals("<--")	||
				text.equals("Clear") ||
				text.equals("Back") ||
				text.equals("Next")) && isEmail){
				email.append(text);
				p3_email.setText(email.toString());
				
			}
			else if(text.equals("Clear")){
				
				email.delete(0, email.length());
				p3_email.setText(email.toString());
				
				
			}
			else if(text.equals("<--")){
				
				if(email.length() != 0)
					email.deleteCharAt(email.length() - 1);
				p3_email.setText(email.toString());

			}
			else if(text.equals("Caps")){
				isUpperCase = !isUpperCase;
				if(isUpperCase){
					for (int row = 0; row < p3_key.length; row++) {
			            for (int column = 0; column < p3_key[row].length; column++) {
			            	String temp = button[row][column].getText();
			                if(temp.matches("[a-z]")){
			            		
			            		button[row][column].setText(temp.toUpperCase());
			                }
			                else if(temp.matches("[0-9]")){
			                	
			                }
			                	
			            }
			        }
				}
				else{
					for (int row = 0; row < p3_key.length; row++) {
			            for (int column = 0; column < p3_key[row].length; column++) {
			            	String temp = button[row][column].getText();
			                if(temp.matches("[A-Z]")){
			            		
			            		button[row][column].setText(temp.toLowerCase());
			                }
			                else if(temp.matches("[0-9]")){
			                	
			                }
			                	
			            }
			        }
				}
			}
		}
		
		/*
		 * Panel4 Buttons
		 */
		
		else if(ae.getSource().equals(p4_Back)){
			this.remove(p4);
			this.setContentPane(p3);
			this.pack();
		}
		else if(ae.getSource().equals(p4_Next)){
			this.remove(p4);
			this.setContentPane(p5);
			this.pack();
		}
			
	}
	@Override
	public void focusGained(FocusEvent fe) {

		if(fe.getSource() == p2_SNInput){
			isSN = true;
			isPIN = false;
			if(SN.toString().equals("Please Enter Your Student Number      ")){
				SN.delete(0, SN.length());
				p2_SNInput.setText(SN.toString());
			}
		}
		else if(fe.getSource() == p2_PINInput){
			isPIN = true;
			isSN = false;
			if(PIN.toString().equals("Please Enter Your PIN")){
				PIN.delete(0, PIN.length());
				p2_PINInput.setText(PIN.toString());
			}
		}
		else if(fe.getSource() == p3_email){
			isEmail = true;
			if(email.toString().equals("Please Enter Your Email Address")){
				email.delete(0, email.length());
				p3_email.setText(email.toString());
			}
		}
	}
	
	@Override
	public void focusLost(FocusEvent fe) {
		// TODO Auto-generated method stub
		
	}

	// -------------
	// other methods
	// -------------

	// (no other methods in this demo program)

	// -------------
	// inner classes
	// -------------

	// Note: WindowAdapter implements WindowListener
	


	private class WindowCloser extends WindowAdapter
	{
		@Override
		public void windowClosing(WindowEvent event)
		{
			System.exit(0);
		}
	}

}