import javax.swing.*;
import java.awt.Component;
import java.awt.*;
import java.util.Calendar;
import java.awt.event.*;
import java.sql.*;
import java.lang.*;

import java.awt.Color;

public class warden extends JFrame implements ActionListener
{
	connection cn = new connection();
   	JLabel lblwardentitle, lblseperator, lblwardenid, lblprisonername, lblentrancedate, lblgender, lblcellsharing, lblranking;
	JButton btnfirst, btnlast, btnprevious, btnnext, btnadd, btnfind, btndelete, btnsave, btnCancel,btnSearch;
	JTextField txtwardenid, txtwardenname;
	int global_id = 0;
	JComboBox cboeday, cboemonth, cboeyear, cboranking;
	String str [] = new String[31] ;
	int coloumn = 70, counter, row = 100;

	ButtonGroup cell_group = new ButtonGroup();
	JRadioButton rndmale = new JRadioButton("Male");	
	JRadioButton rndfemale = new JRadioButton("Female");	
	
	Font fnCambria = new Font("Cambria",Font.BOLD,35);
	Font fnCambria2 = new Font("Cambria",Font.BOLD,16);
	ResultSet results=null;
 	Statement stmt = null;
	Connection con = null;
		
	int id = 0;
	int counter_check = 0;



	public warden()		//.............Constructor for prisoner class................
	{
	
		setLayout(null);
		setTitle("PMS");
		setResizable(false);
		setVisible(true);
		setLocation(400,10);	
		setSize(750,700);
		
		
	
	
		lblwardentitle = new JLabel("__________WARDEN___________");
	
		lblwardenid  = new JLabel("Unique ID : ");
		lblprisonername  = new JLabel("Warden Name : ");
		lblentrancedate  = new JLabel("Joining Date : ");
			
		lblgender = new JLabel("Gender : ");
	
		add(lblwardentitle);
	
		lblwardentitle.setBounds(160,10,500,50);
		lblwardentitle.setFont(fnCambria);
		lblwardentitle.setForeground(Color.orange);
		
		getContentPane().setBackground(Color.white);
		
		lblranking = new JLabel("Ranking : ");
	

		JLabel lblprisoner [] = {lblwardenid, lblprisonername, lblentrancedate,lblgender,lblranking};		

		
		for(counter = 0 ; counter < 5 ; counter++)
		{
			add(lblprisoner[counter]);
			lblprisoner[counter].setBounds(200,coloumn,140,40);
			lblprisoner[counter].setFont(fnCambria2);
			coloumn = coloumn + 50;
			
		}	

	btnlast = new JButton("Last");
	btnprevious = new JButton("Previous");
	btnnext = new JButton("Next");
	btnfirst = new JButton("First");

	btnadd = new JButton("New");

	btnsave = new JButton("Save");
	btnfind = new JButton("Find");
	btndelete = new JButton("Delete");
	btnCancel = new JButton("Cancel");	
	btnSearch = new JButton("Search");
	
	
	//________________________________________________________________________________________________
	JButton btnprisoner []= {btnfirst,  btnprevious, btnnext, btnlast, btnadd, btnfind, btnSearch, btndelete, btnsave, btnCancel};
	
	row = 125;
	for (counter = 0 ; counter < 4 ; counter++)
	{
			add(btnprisoner[counter]);
			btnprisoner[counter].setBounds(row, 510, 120, 30);
			btnprisoner[counter].addActionListener(this);
			btnprisoner[counter].setBackground(Color.white);
			row = row + 122;
	}

	row = 5;
	for (counter = 4 ; counter < 10 ; counter++)
	{
			add(btnprisoner[counter]);
			btnprisoner[counter].setBounds(row, 550, 120, 30);
			btnprisoner[counter].addActionListener(this);
			btnprisoner[counter].setBackground(Color.white);
			row = row + 122;
	}
	
	coloumn = 80;
	
	
	cboeday = new JComboBox();
	cboemonth= new JComboBox();
	cboeyear= new JComboBox();
	cboranking = new JComboBox();

	for (counter= 1; counter <= 5; counter ++)
	{
		String s = new Integer(counter).toString();
		cboranking.addItem(s);
	}
	
	for (counter= 1; counter <= 31; counter ++)
	{
		String s = new Integer(counter).toString();
		cboeday.addItem(s);
	}

	for (counter = 1; counter <= 12 ; counter ++)
	{
		String s = new Integer(counter).toString();
		cboemonth.addItem(s);
	}
	

	for (counter = 2011; counter <= 2040 ; counter ++)
	{
		String s = new Integer(counter).toString();
		cboeyear.addItem(s);
	}
		

	//___________________________________________________________________________________________________________

	JComboBox chk_prisoner [] = { cboeday, cboemonth, cboeyear}; 

	row = 340;
	for(counter = 0; counter < 3 ; counter ++)
	{
		add(chk_prisoner[counter]);
		chk_prisoner[counter].setBounds(row,180,55,20);
		chk_prisoner[counter].setBackground(Color.white);
		row = row + 60;
	}
		add(cboranking);
		cboranking.setBounds(340,280, 55, 20);
	
			
	//________________________________________________________________________________________________________

	txtwardenid = new JTextField();
	txtwardenname = new JTextField();

	add( txtwardenid);
	add(txtwardenname);

	txtwardenid.setBounds(340,80,180,25);
	txtwardenname.setBounds(340,130,180,25);
	
	cell_group.add(rndmale);
  	cell_group.add(rndfemale);
	add(rndmale);
	add(rndfemale);	
	
	rndmale.setBounds(340,230, 70, 20);
	rndmale.setBackground(Color.white);
	rndfemale.setBounds(420,230, 70, 20);
	rndfemale.setBackground(Color.white);
	cn.open();
	fill_fields();
	
	}
public void actionPerformed(ActionEvent ae)
     	{
	try
		{	
			if(ae.getActionCommand() == "New")		//___________checks if the button pressed on the form is "New"_______________
	 		{
				clear_fields();
				cboranking.setEnabled(true);
				fill_id();
						
			}

			if(ae.getActionCommand() == "First")		//___________checks if the button pressed on the form is "New"_______________
	 		{
				if(cn.rs.first())
				{
					fill_details();
				}
						
			}
			
			if(ae.getActionCommand() == "Previous")		//___________checks if the button pressed on the form is "New"_______________
	 		{
				if (cn.rs.isFirst() )
				{
					JOptionPane.showMessageDialog(null,"This is the first record");
				}
				else if(cn.rs.previous())
				{
					fill_details();
				}
						
			}
			
			if(ae.getActionCommand() == "Next")		//___________checks if the button pressed on the form is "New"_______________
	 		{
				if (cn.rs.isLast() )
				{
					JOptionPane.showMessageDialog(null,"This is the last record");
				}
				else if(cn.rs.next())
				{
					fill_details();
				}
						
			}

			if(ae.getActionCommand() == "Last") 	
	 		{
				if(cn.rs.last())
				{
					fill_details();
					
				}
				
			}
		
			if(ae.getActionCommand() == "New") 	
	 		{
				buttons_disable();
				btnsave.setEnabled(true);
				btnCancel.setEnabled(true);
				
			}

			if(ae.getActionCommand() == "Delete") 	
	 		{
				String str = "Do you want to delete this prisoner record with Id -" + txtwardenid.getText() + " ?";
				int confirmation_counter = delete_confirmation (str);
 		
				if ( confirmation_counter == 0 )
				{
					cn.stmt.executeUpdate("UPDATE warden SET status = '" + "deleted" + "'  WHERE warden_id = '" + txtwardenid.getText()+ "' ");	
		
					JOptionPane.showMessageDialog(null,"Warden record deleted successfully");
			
					fill_fields();
				}
				
				
			}


			if(ae.getActionCommand() == "Find") 	//_________ >| represents the last record in the result set fetched______
	 		{
				clear_fields();
				control_disable();
				buttons_disable();
				btnSearch.setEnabled(true);
				btnCancel.setEnabled(true);
				
				
				txtwardenid.setText("");
				txtwardenid.setEnabled(true);
			
				
			}
			
			if(ae.getActionCommand() == "Search")
	 		{
				
				cn.rs = cn.stmt.executeQuery("SELECT * from warden WHERE warden_id =  '" + txtwardenid.getText() + "'");
				
				if (cn.rs.next())
				{
					fill_details();
				}
				else	
				{
					JOptionPane.showMessageDialog(null,"No record match found");
				}
				
			}
			
			if(ae.getActionCommand() == "Cancel") 	
	 		{
				control_disable();
				fill_fields();
			}

			if(ae.getActionCommand() == "Save") 	
	 		{
				String warden_name = txtwardenname.getText();
				
				int edat, emon, eyr, rdat, rmon, ryr;
				
				edat = cboeday.getSelectedIndex();
				emon = cboemonth.getSelectedIndex();
				eyr = cboeyear.getSelectedIndex();

				if (warden_name.length() == 0)
				{
					JOptionPane.showMessageDialog(null,"Warden Name is Mandatory");	
				}
				else if (warden_name.length() > 25)
				{
					JOptionPane.showMessageDialog(null,"Warden name cannot exceed 25 characters");
				}
				else
				{
					int temp_id;	 // variable used to update the value in prison_id table
		
					String gender = "No";
					
					if(rndmale. isSelected())	
					{
						gender= "Male";
					}
					else if(rndfemale. isSelected())
					{
						gender = "Female";
					}
					
					
					cn.stmt.executeUpdate("INSERT INTO warden VALUES('" + txtwardenid.getText() + "','" + warden_name + "','" + cboeday.getSelectedItem() + "','" + cboemonth.getSelectedItem() + "','" + cboeyear.getSelectedItem() + "', '" + cboranking.getSelectedItem() + "','" + gender +"','" + "working" + "'  ) ");

					temp_id = global_id;

					cn.stmt.executeUpdate("UPDATE prison_id SET warden_id = '" + temp_id +"'");

					JOptionPane.showMessageDialog(null,"Warden record saved successfully");
		
					fill_fields();
	
				}
				
			}
		}
	catch (Exception e)
	{
		System.out.println(e.toString());
	}
}
public void fill_fields()
	{
		try
		{
			cn.rs = cn.stmt.executeQuery("SELECT * from warden WHERE status = '" + "working" + "' ");
			control_disable();
			if(cn.rs.next())		
			{
					fill_details();	
			}
	
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}

public void fill_details() throws Exception
	{
		txtwardenid.setText(cn.rs.getString("warden_id"));

		txtwardenname.setText(cn.rs.getString("warden_name"));
	
		cboeday.setSelectedItem(cn.rs.getString("joining_day"));
	
		cboemonth.setSelectedItem(cn.rs.getString("joining_month"));
	
		cboeyear.setSelectedItem(cn.rs.getString("joining_year"));
	
		if(cn.rs.getString("gender").equals("Male"))
		{
			rndmale.setSelected(true);
		}
		else if(cn.rs.getString("gender").equals("Female"))
		{
			rndfemale.setSelected(true);
		}
		cboranking.setSelectedItem(cn.rs.getString("level"));
	}
public void control_disable()
	{
		btnsave.setEnabled(false);
		btnCancel.setEnabled(false);
		btnSearch.setEnabled(false);
		txtwardenid.setEnabled(false);
		txtwardenname.setEnabled(false);
		cboeday.setEnabled(false);		
		cboemonth.setEnabled(false);
		cboeyear.setEnabled(false);
		rndmale.setEnabled(false);
		rndfemale.setEnabled(false);
		navigation_enable();
		btnadd.setEnabled(true);
		btnfind.setEnabled(true);
		btndelete.setEnabled(true);
		cboranking.setEnabled(false);
		
	}	
public void navigation_enable()
	{
		btnfirst.setEnabled(true);
		btnprevious.setEnabled(true);	
		btnnext.setEnabled(true);
		btnlast.setEnabled(true);

	}

public void buttons_disable()
	{
		JButton btn[] = {btnfirst, btnlast, btnprevious, btnnext, btnadd, btnfind, btnSearch, btndelete, btnsave, btnCancel};
		for (int i = 0; i < 10; i++)
		{
			btn[i].setEnabled(false);
		}
	}
public void buttons_enable()
	{
		JButton btn[] = {btnfirst, btnlast, btnprevious, btnnext, btnadd, btnfind,btnSearch, btndelete, btnsave, btnCancel};
		for (int i = 0; i < 10; i++)
		{
			btn[i].setEnabled(true);
		}
	}
public static int delete_confirmation(String str)
	 {
    
		int confirmation = JOptionPane.showConfirmDialog((Component) null, str, "Delete Confirmation", JOptionPane.OK_CANCEL_OPTION);

   		return confirmation;
	  }
public void clear_fields()
{
	JComboBox cbo[] = {cboeday, cboemonth, cboeyear};
	
	for(int i = 0 ; i < 3 ; i++)
	{
		cbo[i].setSelectedIndex(0);
		cbo[i].setEnabled(true);
	}
	txtwardenid.setText("");
	txtwardenname.setText("");
	txtwardenname.setEnabled(true);
	
	JRadioButton rnd[] = {rndmale, rndfemale};
	
	for(int i = 0 ; i < 2 ; i++)
	{
		rnd[i].setEnabled(true);
	}
	rndmale.setSelected(true);
}

public void fill_id() throws Exception
{
	int warden_id;

	cn.rs = cn.stmt.executeQuery("SELECT * from prison_id");	
			
	if(cn.rs.next())
	{
		warden_id= Integer.parseInt(cn.rs.getString("warden_id"));				
		
		warden_id = warden_id + 1;		
		global_id = warden_id;	
		String ward_id = new Integer(warden_id).toString();				
			
		ward_id = ward_id;
		txtwardenid.setText(ward_id);		
			
		
	}
	
}
	
public long calculate_days(int temp_y,int  temp_m,int  temp_d, int  temp_y2,int  temp_m2,int  temp_d2)		//_________Member function will return value of long type_________
	{
		Calendar calender_1 = Calendar.getInstance();		// _____Creating an object of the calender class defined in java.lang package__________

		Calendar calender_2 = Calendar.getInstance();
 				
		calender_1.set(temp_y, temp_m, temp_d);			// ______Setting the values to calender object_____________

		calender_2.set(temp_y2, temp_m2, temp_d2);
		
		long milliseconds1 = calender_1.getTimeInMillis();
  		
		long milliseconds2 = calender_2.getTimeInMillis();
		
		long diff = milliseconds2 - milliseconds1;
  	
		long diffDays = diff / (24 * 60 * 60 * 1000);
		System.out.println(diffDays);
		return diffDays;
	}

	
}


