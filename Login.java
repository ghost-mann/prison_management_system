import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends JFrame implements ActionListener {

    JButton btnlogin,btnsignup;
    JLabel title,username,password;

    JTextField usertxt;
    JPasswordField passtxt;

    int coloumn = 70, counter;
    ResultSet results=null;
    Statement stmt = null;
    Connection con = null;




    //constructor
    Login()
    {

        setLayout(null);
        setTitle("Kenya Prison Management System");
        setResizable(false);
        setVisible(true);
        setLocation(300, 20);
        setSize(750, 650);


        title = new JLabel("Kenya Prisons");
        username = new JLabel("Enter Username");
        password = new JLabel("Enter Password");

        //title config
        add(title);
        title.setBounds(160, 10, 500, 50);

        //label arrangement and configuration
        JLabel lblprison[] = {title,username,password};


        for (counter = 0; counter < 3; counter++) {
            add(lblprison[counter]);
            lblprison[counter].setBounds(160, coloumn, 140, 40);
            coloumn = coloumn + 50;

        }

        //textfield arrangement and configuration


        usertxt = new JTextField(50);
        add(usertxt);
        usertxt.setBounds(300,130,180,25);


        passtxt = new JPasswordField(50);
        add(passtxt);
        passtxt.setBounds(300,180,180,25);




        //button placement and config
        btnlogin = new JButton("Login");
        add(btnlogin);
        btnlogin.setBounds(300,430,80,25);


        btnsignup = new JButton("Sign Up");
        add(btnsignup);
        btnsignup.setBounds(400,430,80,25);











    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
