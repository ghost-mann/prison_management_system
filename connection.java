import java.sql.*;

public class connection
{
    public connection con = null;
    public Statement stmt = null;
    public ResultSet rs = null;

    public void open()
    {
        try
        {
            Class.forName("com.mysql.jdbc.driver");
            con = (connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/prison","root","lovesosa");

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void close()
    {
        try
        {
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}