import javax.swing.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.awt.*;
import java.awt.Color;
public class menu_screen extends JFrame implements ActionListener
{
	//.............Navigational Buttons............

	JButton btnprison, btnwarden, btncheck_prison, btnwarden_report;
	JLabel Title,pimg, name;
	Font fnt_first;
	Icon imgprisoner, imgwarden, report, imgprison;
	int i, x = 50;
	
	// Constructor for menu_screen class
	menu_screen()			
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setLayout(null);
		setResizable(false);
		setTitle("Kenya Prison Management System");
		setLocation(300,20);
		getContentPane().setBackground(Color.white);
		setIconImage (getToolkit().getImage ("prisoner.png"));
		
		imgprisoner = new ImageIcon("handcuffs.png");
		imgwarden = new ImageIcon("police.png");
		report = new ImageIcon("edit.png");

		imgprison = new ImageIcon("Kenya-Prisons.jpg");
		//Font Initialising 
		fnt_first = new Font("San-Serif",Font.BOLD,30);
		
		// Initialising the Buttons And Label
		btnprison = new JButton("Prisoner",imgprisoner);			
		btnwarden = new JButton("Warden",imgwarden);
		btncheck_prison = new JButton("Prisoner Report",report);
		btnwarden_report = new JButton("Warden Report",report);
		
		Title = new JLabel("Kenya Prison Managment System");
		add(Title);
		Title.setBounds(120,15,600,40);
		Title.setFont(fnt_first);
		Title.setForeground(Color.red);
		

		
		JButton btn_main [] = {btnprison,btnwarden,btncheck_prison,btnwarden_report}; 
		for (int i = 0 ; i < 4 ; i++)
		{
			add(btn_main[i]);
			btn_main[i].setBounds(x, 90, 160, 40);
			btn_main[i].addActionListener(this);
			btn_main[i].setBackground(Color.white);
			x = x + 170;
		}
		
		pimg = new JLabel(imgprison);
		add(pimg);
		pimg.setBounds(85,140,600,400);
		

		
		name = new JLabel("Created By Austin Oketch and Steven Ochan");
		add(name);
		name.setBounds(250,530,780,40);
		name.setForeground(Color.red);
		
	}
public void actionPerformed(ActionEvent ae)
     	{
		if(ae.getActionCommand() == "Prisoner")
	 		{
				prisoner p1 = new prisoner();
				
			}

		if(ae.getActionCommand() == "Warden")
	 		{
				warden p1 = new warden();
				
			}

		if(ae.getActionCommand() == "Prisoner Report")
	 		{
				prisoner_report p1 = new prisoner_report();
				
			}

		if(ae.getActionCommand() == "Warden Report")
	 		{
				report p1 = new report();
				
			}
	}


}