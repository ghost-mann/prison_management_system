//_____________________Importing Java Packages____________

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.sql.*;


//.................report class to display the report of warden..............

public class report extends JFrame
{
	JLabel report_title,lblshow, lbl_id, lbl_name, lbl_joining,  lbl_level, lbl_share,name;
	
	String warden_id, warden_name,  joining_date, security_level, Gender;
	connection cn = new connection();

	Font f1= new Font("Cambria",Font.BOLD,30);			
	Font f2= new Font("Cambria",Font.BOLD,17);
	Font f3= new Font("Cambria",Font.BOLD,14);
		
	public report()
	{
		setSize(700,500);
		setLocation(400,120);
		setTitle("Prisoner Management System");
		setLayout(null);
		setVisible(true);
		setResizable(false);
		getContentPane().setBackground(Color.white);
		lblshow = new JLabel ("____________________________________________________________________________________________________");
		
		

		report_title= new JLabel("____________Warden Report___________");
		report_title.setForeground(Color.orange);
		
		lbl_id= new JLabel("Warden Id");
		lbl_name= new JLabel("Warden Name");
		lbl_joining= new JLabel("    Joining Date");		
		lbl_level = new JLabel("       Security Level");
		lbl_share= new JLabel("                Gender");

		add(report_title);
		report_title.setBounds(120,10,470,40);
		report_title.setFont(f1);
		
		
		JLabel warden_Label [] = {lbl_id, lbl_name, lbl_joining, lbl_level, lbl_share};

		int row = 30;
	
		for (int  i  = 0 ;  i < 5 ; i ++)
		{
			add(warden_Label[i]);
			warden_Label[i].setBounds(row, 50,300,40);
			warden_Label[i].setFont(f2);
			row = row + 120;
		}
		
		
		int inc = 80;
		
		try
		{
			cn.open();
			cn.rs = cn.stmt.executeQuery("Select * from warden WHERE status = '" + "working" + "' order by warden_id");
			
			while(cn.rs.next())
			{
				warden_id = " " + cn.rs.getString("warden_id");
				warden_name = "  " + cn.rs.getString("warden_name");
				joining_date = "   " + cn.rs.getString("joining_day");
				joining_date = joining_date + "/" + cn.rs.getString("joining_month");
				joining_date = joining_date + "/" + cn.rs.getString("joining_year");

				security_level  = "            " + cn.rs.getString("level") + " Star";
				Gender = "                  " ;
				Gender  = Gender + cn.rs.getString("gender");


				lbl_id= new JLabel(warden_id);
				lbl_name= new JLabel(warden_name);
				lbl_joining= new JLabel(joining_date);
				lbl_level= new JLabel(security_level);
				lbl_share = new JLabel(Gender);
			
				JLabel warden_Label2 [] = {lbl_id, lbl_name, lbl_joining,  lbl_level, lbl_share};

				row = 50;
		
				for (int  i  = 0 ;  i < 2 ; i ++)
				{
					add(warden_Label2[i]);
					warden_Label2[i].setBounds(row, inc,140,40);
					warden_Label2[i].setFont(f3);
					row = row + 105;
				}
				
				row = row + 30;
				for (int  i  = 2 ;  i < 5 ; i ++)
				{
					add(warden_Label2[i]);
					warden_Label2[i].setBounds(row, inc,140,40);
					warden_Label2[i].setFont(f3);
					row = row + 120;
				}
		
				inc = inc + 30;
			}

		//lblshow = new JLabel("________________________________________________________________________");
		add(lblshow);
		lblshow.setBounds(10,370,780,40);
		lblshow.setForeground(Color.orange);
			
		name = new JLabel("           Created By Anonymous");
		add(name);
		name.setBounds(280,420,780,40);
		name.setForeground(Color.orange);
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	
		
	}
		

	
}