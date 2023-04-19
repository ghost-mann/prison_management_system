import java.sql.*;

public class connection
{
	public Connection con = null;
	public Statement stmt = null;
	public ResultSet rs = null;
	
	public void open()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:prison_management");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
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