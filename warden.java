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
   	JLabel lblwardentitle,lblwardenid, lblwardenname,lblgender,lblentry,lblexit,lblprison;
	JButton Btnsave,BtnCancel;
	JTextField txtwardenid, txtwardenname,txtGender,txtEntry,txtExit,txtprison;



	int coloumn = 70, counter;


	
	Font fnCambria = new Font("Cambria",Font.BOLD,35);
	Font fnCambria2 = new Font("Cambria",Font.BOLD,16);
	ResultSet results=null;
 	Statement stmt = null;
	Connection con = null;
		





	//constructor
	public warden()
	{
		//jframe configurations and adjustments
		setLayout(null);
		setTitle("Kenya Prison Management System");
		setResizable(false);
		setVisible(true);
		setLocation(400,10);	
		setSize(750,700);
		
		
	
		//label configurations
		lblwardentitle = new JLabel("WARDEN");
		add(lblwardentitle);
	
		lblwardentitle.setBounds(160,10,500,50);
		lblwardentitle.setFont(fnCambria);
		lblwardentitle.setForeground(Color.green);
		
		getContentPane().setBackground(Color.white);


		lblwardenid  = new JLabel("Unique ID : ");
		lblwardenname  = new JLabel("Warden Name: ");
		lblgender = new JLabel("Gender: ");
		lblentry = new JLabel("Start date : ");
		lblexit = new JLabel("End date : ");
		lblprison= new JLabel("Prison Name : ");




	

		JLabel lblprisoner [] = {lblwardenid,lblwardenname,lblgender,lblentry,lblexit,lblprison};

		
		for(counter = 0 ; counter < 6 ; counter++)
		{
			add(lblprisoner[counter]);
			lblprisoner[counter].setBounds(200,coloumn,140,40);
			lblprisoner[counter].setFont(fnCambria2);
			coloumn = coloumn + 50;
			
		}

		//textfield arrangement and configuration


		txtwardenid = new JTextField(50);
		add(txtwardenid);
		txtwardenid.setBounds(300,80,180,25);


		txtwardenname = new JTextField(50);
		add(txtwardenname);
		txtwardenname.setBounds(300,130,180,25);

		txtGender = new JTextField(50);
		add(txtGender);
		txtGender.setBounds(300,180,180,25);



		txtEntry = new JTextField(50);
		add(txtEntry);
		txtEntry.setBounds(300,230,180,25);


		txtExit = new JTextField(50);
		add(txtExit);
		txtExit.setBounds(300,280,180,25);


		txtprison = new JTextField(50);
		add(txtprison);
		txtprison.setBounds(300,330,180,25);




		//buttons arrangement and configuration
		Btnsave = new JButton("Save");
		add(Btnsave);
		Btnsave.setBounds(300,430,80,25);


		BtnCancel = new JButton("Cancel");
		add(BtnCancel);
		BtnCancel.setBounds(400,430,80,25);












	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}


