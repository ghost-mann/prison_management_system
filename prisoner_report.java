//_________Impoting Java Packages___________

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.Color;


public class prisoner_report extends JFrame		//___________Class is defined to print all the records of the prisoner table__________
{
	JLabel report_title,lbl_id, lbl_name, lbl_gender, lbl_age,lbl_exit,lbl_crime,lbl_punishment;
	
	String prisoner_id, prisoner_name,  entrance_date, release_date, security_level, cell_sharing;

	Font f1= new Font("Cambria",Font.BOLD,30);	// ___________Creating the objects of teh FONT class for better layout___________
	Font f2= new Font("Cambria",Font.BOLD,16);
	Font f3= new Font("Cambria",Font.BOLD,13);	
	
	connection cn = new connection();
	
		
	public prisoner_report()
			//constructor
	{
		//jframe configuration and placement
		setSize(800,500);
		setLocation(400,120);
		setTitle("Kenya Prison Management System");

		setLayout(null);
		setVisible(true);
		setResizable(false);
		//Setting the background color of the form
		getContentPane().setBackground(Color.white);



		report_title= new JLabel("Prisoner Report");
		lbl_id= new JLabel("Unique Id");
		lbl_name= new JLabel("Prisoner Name");
		lbl_gender= new JLabel("  Gender");
		lbl_age= new JLabel("Age");
		lbl_exit= new JLabel("Exit");
		lbl_crime = new JLabel("Crime");
		lbl_punishment = new JLabel("Punishment");


		add(report_title);
		report_title.setBounds(220,10,490,40);
		report_title.setFont(f1);
		report_title.setForeground(Color.RED);


		JLabel Prisoner_Label [] = {lbl_id, lbl_name, lbl_gender, lbl_age, lbl_exit,lbl_crime,lbl_punishment};

		int row = 30;

		for (int  i  = 0 ;  i < 7 ; i ++)
		{
			add(Prisoner_Label[i]);
			Prisoner_Label[i].setBounds(row, 50,300,40);
			Prisoner_Label[i].setFont(f2);
			row = row + 110;
		}


		int inc = 70;






		}




	}

