import java.sql.*;

public class connection
{
	public Connection con = null;
	public Statement stmt = null;
	public ResultSet rs = null;
	
	public void open()
	{
		String url = "jdbc:mysql://localhost:3306/prison";
		String user_name = "root";
		String password = "lovesosa";
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection(url,user_name,password);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);


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