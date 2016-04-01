package HarshPackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;




/**
 * This class is used to delete all the information of the current session.
 * 
 * 
 * */
public class HDrop
{
	public static void main(String args[])
	{
		

		final String CONNECTION_PATH = "jdbc:oracle:thin:@dagobah.engr.scu.edu:1521:db11g"; // TODO
		final String USERNAME = "hpandya";
		final String PASSWORD = "9773306761a";
		try
		{
			//Use the driver before establishing connection with the database.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("success driver");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e);
		}
		
		try
		{
			// SEtup connection with the database.
			Connection conn = DriverManager.getConnection (CONNECTION_PATH,USERNAME, PASSWORD);
			
			System.out.println("Connection to database established");
			
			try
			{
				Statement stmt = conn.createStatement();
		
				stmt.executeUpdate(" DROP TABLE AUTHORISBN");
				stmt.executeUpdate(" DROP TABLE titles");
				stmt.executeUpdate(" DROP TABLE publishers");
				stmt.executeUpdate(" DROP TABLE authors");
				stmt.executeUpdate(" DROP sequence incrementer_seq");
				stmt.executeUpdate("DROP sequence incrementer_pub");
			    stmt.close();
			    conn.close();
			    
			}
			catch(SQLException e)
			{
					System.out.println(e);
					e.printStackTrace();
			}
			finally{
					conn.close();
			}
			
		}
		
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		
	}
}