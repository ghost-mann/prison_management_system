//_________Impoting Java Packages___________

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.Color;


public class prisoner_report extends JFrame		//___________Class is defined to print all the records of the prisoner table__________
{
	JLabel report_title, lblshow, name, lbl_id, lbl_name, lbl_entrance, lbl_release, lbl_level, lbl_share;
	
	String prisoner_id, prisoner_name,  entrance_date, release_date, security_level, cell_sharing;
	
	Font f1= new Font("Cambria",Font.BOLD,30);	// ___________Creating the objects of teh FONT class for better layout___________
	Font f2= new Font("Cambria",Font.BOLD,16);
	Font f3= new Font("Cambria",Font.BOLD,13);	
	
	connection cn = new connection();
	
		
	public prisoner_report()		// ___________Constructor of the prisoner_report class___________
	{
		setSize(800,500);
		setLocation(400,120);
		setTitle("Prisoner Management System");
		
		setLayout(null);
		setVisible(true);
		setResizable(false);
		getContentPane().setBackground(Color.white);		//__________Setting the background color of the form_______

		

		report_title= new JLabel("_________Prisoner Report____________");
		lbl_id= new JLabel("Prisoner Id");
		lbl_name= new JLabel("Prisoner Name");
		lbl_entrance= new JLabel("  Entrance Date");
		lbl_release= new JLabel("   Releasing Date");
		lbl_level = new JLabel("     Security Level");
		lbl_share= new JLabel("            Cell Sharing");
		
		add(report_title);
		report_title.setBounds(220,10,490,40);
		report_title.setFont(f1);
		report_title.setForeground(Color.orange);
		
		
		JLabel Prisoner_Label [] = {lbl_id, lbl_name, lbl_entrance, lbl_release, lbl_level, lbl_share};

		int row = 30;
	
		for (int  i  = 0 ;  i < 6 ; i ++)
		{
			add(Prisoner_Label[i]);
			Prisoner_Label[i].setBounds(row, 50,300,40);
			Prisoner_Label[i].setFont(f2);
			row = row + 120;
		}
		
		
		int inc = 70;
		
		try
		{
			cn.open();
			cn.rs = cn.stmt.executeQuery("Select * from prisoner  WHERE status = '" + "in" + "' order by prisoner_id ");
			
			while(cn.rs.next())
			{
				prisoner_id = "     " +  cn.rs.getString("prisoner_id");
				prisoner_name = cn.rs.getString("prisoner_name");
				
				entrance_date = cn.rs.getString("regis_date");		//_____Concatinating the Entrance Date to display in dd/mm/yyyy format____
				entrance_date = entrance_date + "/" + cn.rs.getString("regis_month");
				entrance_date = entrance_date + "/" + cn.rs.getString("regis_year");


				release_date = "  " + cn.rs.getString("release_date");
				release_date = release_date + "/" + cn.rs.getString("release_month");
				release_date = release_date + "/" + cn.rs.getString("release_year");

				security_level  = "   " + cn.rs.getString("high_security");
				cell_sharing = "                  " ;
				cell_sharing  = cell_sharing + cn.rs.getString("cell_sharing");


				lbl_id= new JLabel(prisoner_id);
				lbl_name= new JLabel(prisoner_name);
				lbl_entrance= new JLabel(entrance_date);
				lbl_release = new JLabel(release_date);
				lbl_level= new JLabel(security_level);
				lbl_share = new JLabel(cell_sharing);
			
				JLabel Prisoner_Label2 [] = {lbl_id, lbl_name, lbl_entrance, lbl_release, lbl_level, lbl_share};

				row = 50;
		
				for (int  i  = 0 ;  i < 2 ; i ++)
				{
					add(Prisoner_Label2[i]);
					Prisoner_Label2[i].setBounds(row, inc,140,40);
					Prisoner_Label2[i].setFont(f3);
					row = row + 105;
				}
				
				row = row + 30;
				for (int  i  = 2 ;  i < 6 ; i ++)
				{
					add(Prisoner_Label2[i]);
					Prisoner_Label2[i].setBounds(row, inc,140,40);
					Prisoner_Label2[i].setFont(f3);
					row = row + 120;
				}
		
				inc = inc + 25;
			}
		lblshow = new JLabel("_______________________________________________________________________________________________________________");
		add(lblshow);
		lblshow.setBounds(10,370,780,40);
		lblshow.setForeground(Color.orange);
			
		name = new JLabel("Created By Venkatesh Kumar");
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