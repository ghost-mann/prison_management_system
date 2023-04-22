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
   	JLabel lblprisonertitle,lblprisonerid, lblprisonername, lblgender, lblentrancedate, lblreleasedate, lblcrime,lblpunishment;
	JButton btnsave,btncancel;
	JTextField txtprisonerid,txtprisonername,txtgender,txtentry,txtexit,txtcrime,txtpunishment;


	String str [] = new String[31] ;
	int coloumn = 70;
	int counter;


	//creates a group in which you can add radio buttons.


  	Font fnCambria = new Font("Cambria",Font.BOLD,35);
	Font fnCambria2 = new Font("Cambria",Font.BOLD,16);
	ResultSet results=null;
 	Statement stmt = null;
	Connection con = null;





	//Constructor for prisoner class
	public prisoner()		//Constructor for prisoner class
	{

		setLayout(null);
		setTitle("Kenya Prison Management System");
		setResizable(false);
		setVisible(true);
		setLocation(300, 20);
		setSize(750, 650);


		//label
		lblprisonertitle = new JLabel("PRISONER");

		lblprisonerid = new JLabel("Unique ID : ");

		lblprisonername = new JLabel("Prisoner Name : ");

		lblgender = new JLabel("Gender");

		lblentrancedate = new JLabel("Entrance Date: ");


		lblreleasedate = new JLabel("Release Date: ");


		lblcrime = new JLabel("Crime Committed: ");


		lblpunishment = new JLabel("Punishment: ");



		//prisoner title configuration
		add(lblprisonertitle);

		lblprisonertitle.setBounds(160, 10, 500, 50);
		lblprisonertitle.setFont(fnCambria);

		getContentPane().setBackground(Color.white);

		//label arrangement and configuration
		JLabel lblprisoner[] = {lblprisonerid, lblprisonername,lblgender, lblentrancedate, lblreleasedate,lblcrime,lblpunishment};


		for (counter = 0; counter < 7; counter++) {
			add(lblprisoner[counter]);
			lblprisoner[counter].setBounds(160, coloumn, 140, 40);
			lblprisoner[counter].setFont(fnCambria2);
			coloumn = coloumn + 50;

		}


		//textfield arrangement and configuration


		txtprisonerid = new JTextField(50);
		add(txtprisonerid);
		txtprisonerid.setBounds(300,80,180,25);


		txtprisonername = new JTextField(50);
		add(txtprisonername);
		txtprisonername.setBounds(300,130,180,25);

		txtgender = new JTextField(50);
		add(txtgender);
		txtgender.setBounds(300,180,180,25);

		txtentry = new JTextField(50);
		add(txtentry);
		txtentry.setBounds(300,230,180,25);

		txtexit = new JTextField(50);
		add(txtexit);
		txtexit.setBounds(300,280,180,25);

		txtcrime = new JTextField(50);
		add(txtcrime);
		txtcrime.setBounds(300,330,180,25);

		txtpunishment = new JTextField(50);
		add(txtpunishment);
		txtpunishment.setBounds(300,380,180,25);



		//button placement and config
		btnsave = new JButton("Save");
		add(btnsave);
		btnsave.setBounds(300,430,80,25);


		btncancel = new JButton("Cancel");
		add(btncancel);
		btncancel.setBounds(400,430,80,25);









	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}






	


	



