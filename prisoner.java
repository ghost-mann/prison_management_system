import javax.swing.*;
import java.awt.Component;
import java.awt.*;
import java.util.Calendar;
import java.awt.event.*;
import java.sql.*;
import java.lang.*;
import java.awt.Color;

public class prisoner extends JFrame implements ActionListener
{
	connection cn = new connection();
   	JLabel lblprisonertitle,lblseperator, lblprisonerid, lblprisonername, lblentrancedate, lblreleasedate, lblsecuritylevel, lblcellsharing, lblcurdate;
	JButton btnfirst, btnlast, btnprevious, btnnext, btnadd, btnfind, btncheckrelease, btndelete, btnsave, btnCancel,btnSearch,btnrelease;
	JTextField txtprisonerid, txtprisonername;
	
	JComboBox cboeday, cboemonth, cboeyear,  cborday, cbormonth, cboryear, cbocday, cbocmonth, cbocyear, cboslevel;
	String str [] = new String[31] ;
	int coloumn = 70, counter, row = 100;

	ButtonGroup cell_group = new ButtonGroup();
	JRadioButton rndyes = new JRadioButton("Yes");	
	JRadioButton rndno = new JRadioButton("No");	
	
	ButtonGroup sec_group = new ButtonGroup();
	JRadioButton rndhigh = new JRadioButton("High");	
	JRadioButton rndlow = new JRadioButton("Low");	
	
  	Font fnCambria = new Font("Cambria",Font.BOLD,35);
	Font fnCambria2 = new Font("Cambria",Font.BOLD,16);
	ResultSet results=null;
 	Statement stmt = null;
	Connection con = null;
		
	int id = 0;
	int counter_check = 0;



	public prisoner()		//.............Constructor for prisoner class................
	{
	
		setLayout(null);
		setTitle("Prison Management System");
		setResizable(false);
		setVisible(true);
		setLocation(300,20);	
		setSize(750,650);
		
		
	
	
		lblprisonertitle = new JLabel("__________PRISONER___________");
	
		lblprisonerid  = new JLabel("Prisoner ID : ");
	
		lblprisonername  = new JLabel("Prisoner Name : ");
	
		lblentrancedate  = new JLabel("Entrance Date : ");
	
		lblreleasedate  = new JLabel("Release Date : ");		
	
		lblsecuritylevel = new JLabel("Security Level : ");
	
		lblcellsharing = new JLabel("Cell Sharing : ");
	
		lblcurdate = new JLabel("Current Date");
	
		add(lblprisonertitle);
	
		lblprisonertitle.setBounds(160,10,500,50);
		lblprisonertitle.setFont(fnCambria);
		lblprisonertitle.setForeground(Color.orange);
		
		getContentPane().setBackground(Color.white);
		
	

		JLabel lblprisoner [] = {lblprisonerid, lblprisonername, lblentrancedate, lblreleasedate, lblsecuritylevel, lblcellsharing,lblcurdate};		

		
		for(counter = 0 ; counter < 7 ; counter++)
		{
			add(lblprisoner[counter]);
			lblprisoner[counter].setBounds(160,coloumn,140,40);
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
	btncheckrelease = new JButton("Check Release");
	btndelete = new JButton("Delete");
	btnCancel = new JButton("Cancel");	
	btnSearch = new JButton("Search");
	btnrelease = new JButton("Release");
	
	//________________________________________________________________________________________________
	JButton btnprisoner []= {btnfirst,  btnprevious, btnnext, btnlast, btnadd, btnfind, btncheckrelease, btndelete, btnsave, btnCancel,btnSearch,btnrelease};
	
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
	for (counter = 10 ; counter < 12 ; counter++)
	{
			add(btnprisoner[counter]);
			btnprisoner[counter].setBounds(490, coloumn, 120, 30);
			btnprisoner[counter].addActionListener(this);
			btnprisoner[counter].setBackground(Color.white);
			coloumn = coloumn + 40;
	}
	
	cboeday = new JComboBox();
	cborday = new JComboBox();
	cbormonth= new JComboBox();
	cboemonth= new JComboBox();
	cboeyear= new JComboBox();
	cboryear= new JComboBox();
	cbocmonth= new JComboBox();
	cbocyear= new JComboBox();
	cbocday = new JComboBox();
	

	for (counter= 1; counter <= 31; counter ++)
	{
		String s = new Integer(counter).toString();
		cboeday.addItem(s);
		cborday.addItem(s);
		cbocday.addItem(s);
	}

	for (counter = 1; counter <= 12 ; counter ++)
	{
		String s = new Integer(counter).toString();
		cboemonth.addItem(s);
		cbormonth.addItem(s);
		cbocmonth.addItem(s);
	}
	

	for (counter = 2011; counter <= 2040 ; counter ++)
	{
		String s = new Integer(counter).toString();
		cboeyear.addItem(s);
		cboryear.addItem(s);
		cbocyear.addItem(s);
	}
		

	//___________________________________________________________________________________________________________

	JComboBox chk_prisoner [] = { cboeday, cboemonth, cboeyear, cborday, cbormonth, cboryear,cbocday, cbocmonth, cbocyear}; 

	row = 300;
	for(counter = 0; counter < 3 ; counter ++)
	{
		add(chk_prisoner[counter]);
		chk_prisoner[counter].setBounds(row,180,55,20);
		chk_prisoner[counter].setBackground(Color.white);
		row = row + 60;
	}
	
	row = 300;
	
	for(counter = 3; counter < 6 ; counter ++)
	{
		add(chk_prisoner[counter]);
		chk_prisoner[counter].setBounds(row,230,55,20);
		chk_prisoner[counter].setBackground(Color.white);
		row = row + 60;
	}
	
	row = 300;

	for(counter = 6; counter < 9 ; counter ++)
	{
		add(chk_prisoner[counter]);
		chk_prisoner[counter].setBounds(row,380,55,20);
		chk_prisoner[counter].setBackground(Color.white);
		row = row + 60;
	}
		
	//________________________________________________________________________________________________________

	txtprisonerid = new JTextField();
	txtprisonername = new JTextField();

	add( txtprisonerid);
	add(txtprisonername);

	txtprisonerid.setBounds(300,80,180,25);
	txtprisonername.setBounds(300,130,180,25);
	
	cell_group.add(rndyes);
  	cell_group.add(rndno);
	add(rndyes);
	add(rndno);	
	
	sec_group.add(rndhigh);
	sec_group.add(rndlow);

	add(rndhigh);
	add(rndlow);
	rndhigh.setBackground(Color.white);
	rndlow.setBackground(Color.white);

	rndhigh.setBounds(300, 280,70,20);
	rndlow.setBounds(380, 280,70,20);

	rndyes.setBounds(300,330, 70, 20);
	rndyes.setBackground(Color.white);
	rndno.setBounds(380,330, 40, 20);
	rndno.setBackground(Color.white);
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
				String str = "Do you want to delete this prisoner record with Id -" + txtprisonerid.getText() + " ?";
				int confirmation_counter = delete_confirmation (str);
 		
				if ( confirmation_counter == 0 )
				{
					cn.stmt.executeUpdate("UPDATE prisoner SET status = '" + "deleted" + "'  WHERE prisoner_id = '" + txtprisonerid.getText()+ "' ");	
		
					JOptionPane.showMessageDialog(null,"Prisoner is successfully deleted");
			
					fill_fields();
				}
				
				
			}


			if(ae.getActionCommand() == "Check Release") 	//_________ >| represents the last record in the result set fetched______
	 		{
				buttons_disable();
				btnrelease.setEnabled(true);
				btnCancel.setEnabled(true);
				cbocday.setEnabled(true);
				cbocmonth.setEnabled(true);
				cbocyear.setEnabled(true);
				
			}
			
			if(ae.getActionCommand() == "Find") 	//_________ >| represents the last record in the result set fetched______
	 		{
				clear_fields();
				control_disable();
				buttons_disable();
				btnSearch.setEnabled(true);
				btnCancel.setEnabled(true);
				
				
				txtprisonerid.setText("");
				txtprisonerid.setEnabled(true);
			
				
			}
			
			if(ae.getActionCommand() == "Release")
	 		{

				int e_date, e_month, e_year, r_date, r_month, r_year, c_date, c_month, c_year;
				
				e_date = cboeday.getSelectedIndex();
				e_month = cboemonth.getSelectedIndex();
				e_year  = cboeyear.getSelectedIndex();
			
				r_date = cborday.getSelectedIndex();
				r_month = cbormonth.getSelectedIndex();
				r_year  = cboryear.getSelectedIndex();
				
				c_date = cbocday.getSelectedIndex();
				c_month = cbocmonth.getSelectedIndex();
				c_year = cbocyear.getSelectedIndex();
			
				if (c_year < e_year || ((c_year == e_year) && (c_month < e_month)) || ((c_year == e_year) && (c_month == e_month) && (c_date < e_date)))
				{
					JOptionPane.showMessageDialog(null,"Current Date cannot be less than Entrance date");
				}
				else
				{
					long left;

					String rel = new String("released");
					
					System.out.println(c_year + 2011  + " " + c_month    + " " +  c_date + 1  + " " + r_year + 2011  + " " +  r_month  + " " + r_date + 1);
					
					left = calculate_days(c_year + 2011, c_month + 1  , c_date + 1, r_year + 2011, r_month + 1  ,  r_date + 1);

			  		if (left > 7)	
					{	
						JOptionPane.showMessageDialog(null,"Prisoner has to server more than 7 days");	
					}
					else
					{
						cn.stmt.executeUpdate("UPDATE prisoner SET status = '" + rel + "'  WHERE prisoner_id = '" + txtprisonerid.getText()+ "' ");	
		
						JOptionPane.showMessageDialog(null,"Prisoner is released");
							
					}


					
				}
			}

			
			if(ae.getActionCommand() == "Search")
	 		{
				
				cn.rs = cn.stmt.executeQuery("SELECT * from prisoner WHERE prisoner_id =  '" + txtprisonerid.getText() + "'");
				
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
				String prisoner_name = txtprisonername.getText();
				
				int edat, emon, eyr, rdat, rmon, ryr;
				
				edat = cboeday.getSelectedIndex();
				emon = cboemonth.getSelectedIndex();
				eyr = cboeyear.getSelectedIndex();

				rdat =  cborday.getSelectedIndex();
				rmon = cbormonth.getSelectedIndex();
				ryr = cboryear.getSelectedIndex();
				
				if (prisoner_name.length() == 0)
				{
					JOptionPane.showMessageDialog(null,"Prisoner name is missing");	
				}
				else if ( prisoner_name.length() > 25)
				{
					JOptionPane.showMessageDialog(null,"Prisoner name cannot exceed 25 characters");
				}
				else if (( edat == rdat ) && ( emon == rmon ) && (eyr == ryr))	//_____Check the condition whether the entrance date choosen and the releasing date choosen are not same________
				{	
					JOptionPane.showMessageDialog(null,"Date of entrance and release cannot be same");
				}
	
				else if ((eyr > ryr) || ((eyr == ryr) && (emon > rmon)) || ((eyr == ryr) && (emon == rmon) && (edat > rdat))) 
				{
					JOptionPane.showMessageDialog(null,"Release date cannot be before entrance date");
				}
				else
				{
					int temp_id;	 // variable used to update the value in prison_id table
		
					String sharing = "No";
					String security = "High";
				
					if(rndyes. isSelected())	
					{
						sharing= "Yes";
					}
					else if(rndno. isSelected())
					{
						sharing = "No";
					}
					
					if(rndhigh. isSelected())	
					{
						security = "High";
					}
					else if(rndlow. isSelected())
					{
						security= "Low";
					}
			
					cn.stmt.executeUpdate("INSERT INTO prisoner VALUES('" + txtprisonerid.getText() + "','" + prisoner_name + "','" + cboeday.getSelectedItem() + "','" + cboemonth.getSelectedItem() + "','" + cboeyear.getSelectedItem() + "','" + cborday.getSelectedItem() + "','" + cbormonth.getSelectedItem() + "','" + cboryear.getSelectedItem() + "', '" + security + "','" + sharing +"' ,'" + "in" + "' ) ");

					temp_id = Integer.parseInt(txtprisonerid.getText());

					cn.stmt.executeUpdate("UPDATE prison_id SET prisoner_id = '" + temp_id +"'");

					JOptionPane.showMessageDialog(null,"Prisoner record saved successfully");
		
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
			cn.rs = cn.stmt.executeQuery("SELECT * from prisoner WHERE status = '" + "in" + "' ");
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
		txtprisonerid.setText(cn.rs.getString("prisoner_id"));

		txtprisonername.setText(cn.rs.getString("prisoner_name"));
	
		cboeday.setSelectedItem(cn.rs.getString("regis_date"));
	
		cboemonth.setSelectedItem(cn.rs.getString("regis_month"));
	
		cboeyear.setSelectedItem(cn.rs.getString("regis_year"));
	
		cborday.setSelectedItem(cn.rs.getString("release_date"));
	
		cbormonth.setSelectedItem(cn.rs.getString("release_month"));
	
		cboryear.setSelectedItem(cn.rs.getString("release_year"));
	
		if(cn.rs.getString("high_security").equals("High"))
		{
			rndhigh.setSelected(true);
		}
		else if(cn.rs.getString("high_security").equals("Low"))
		{
			rndlow.setSelected(true);
		}
		
		if(cn.rs.getString("cell_sharing").equals("Yes"))
		{
			rndyes.setSelected(true);
		}
		else if(cn.rs.getString("cell_sharing").equals("No"))
		{
			rndno.setSelected(true);
		}

	}
public void control_disable()
	{
		btnsave.setEnabled(false);
		btnCancel.setEnabled(false);
		btnSearch.setEnabled(false);
		btnrelease.setEnabled(false);
		txtprisonerid.setEnabled(false);
		txtprisonername.setEnabled(false);
		cboeday.setEnabled(false);		
		cboemonth.setEnabled(false);
		cboeyear.setEnabled(false);
		cborday.setEnabled(false);
		cbormonth.setEnabled(false);
		cboryear.setEnabled(false);
		cbocday.setEnabled(false);
		cbocmonth.setEnabled(false);
		cbocyear.setEnabled(false);
		rndyes.setEnabled(false);
		rndno.setEnabled(false);
		rndhigh.setEnabled(false);
		rndlow.setEnabled(false);
		navigation_enable();
		btnadd.setEnabled(true);
		btnfind.setEnabled(true);
		btncheckrelease.setEnabled(true);
		btndelete.setEnabled(true);
		
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
		JButton btn[] = {btnfirst, btnlast, btnprevious, btnnext, btnadd, btnfind, btncheckrelease, btndelete, btnsave, btnCancel,btnSearch,btnrelease};
		for (int i = 0; i < 12; i++)
		{
			btn[i].setEnabled(false);
		}
	}
public void buttons_enable()
	{
		JButton btn[] = {btnfirst, btnlast, btnprevious, btnnext, btnadd, btnfind, btncheckrelease, btndelete, btnsave, btnCancel,btnSearch,btnrelease};
		for (int i = 0; i < 12; i++)
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
	JComboBox cbo[] = {cboeday, cboemonth, cboeyear,  cborday, cbormonth, cboryear};
	
	for(int i = 0 ; i < 6 ; i++)
	{
		cbo[i].setSelectedIndex(0);
		cbo[i].setEnabled(true);
	}
	txtprisonerid.setText("");
	txtprisonername.setText("");
	txtprisonername.setEnabled(true);
	
	JRadioButton rnd[] = {rndhigh, rndlow, rndyes, rndno};
	
	for(int i = 0 ; i < 4 ; i++)
	{
		rnd[i].setEnabled(true);
	}
	rndhigh.setSelected(true);
	rndyes.setSelected(true);
}

public void fill_id() throws Exception
{
	int prisoner_id;

	cn.rs = cn.stmt.executeQuery("SELECT * from prison_id");	
			
	if(cn.rs.next())
	{
		prisoner_id= Integer.parseInt(cn.rs.getString("prisoner_id"));				
		
		prisoner_id = prisoner_id + 1;		
			
		String pris_id = new Integer(prisoner_id).toString();				
			
		txtprisonerid.setText(pris_id);		
			
		
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


