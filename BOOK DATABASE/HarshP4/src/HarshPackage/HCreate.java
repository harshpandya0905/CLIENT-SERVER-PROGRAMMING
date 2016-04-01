package HarshPackage;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class HCreate 
{

	private static final String CONNECTION_PATH = "jdbc:oracle:thin:@dagobah.engr.scu.edu:1521:db11g";
	private static final String USERNAME = "hpandya";
	private static final String PASSWORD = "9773306761a";

	
	// From here the program starts. Firstly it initializes the tables with dummy values
	
	public static void main(String args[]) {
		try {
			
			
			// It will help in loading the JDBC Driver class
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Loaded JDBC server");
		} 
			catch (ClassNotFoundException e) 
		{
			System.out.println("Cant load driver." + e);
		}

		try
		{
			// It helps in starting JDBC connection with the user
			Connection conn = DriverManager.getConnection(CONNECTION_PATH,USERNAME, PASSWORD);

			System.out.println("Connection Successfull!");

			try 
			{
				Statement statement = conn.createStatement();

				//This statement is used to call functions to create tables
				makeTables(statement);

				// Create sequences that will help in the auto increment.
				String incrementer_seq = "CREATE SEQUENCE incrementer_seq START WITH 1 INCREMENT BY 1";
				String incrementer_pub = "CREATE SEQUENCE incrementer_pub START WITH 1 INCREMENT BY 1";

				String sequenceName = "incrementer_seq.nextval";
				String publisherSequence = "incrementer_pub.nextval";

				statement.executeUpdate(incrementer_seq);
				statement.executeUpdate(incrementer_pub);

				// Initializing the tables.

				initializeTables(statement, sequenceName, publisherSequence);

				System.out.println("Select any one choice from the the following MENU");
				boolean loop = true;

				while (loop) 
				{
					try
					{
						System.out.println("1.) Display all authors");
						System.out.println("2.) Display all publishers");
						System.out.println("3.) Update the author info.");
						System.out.println("4.) Add new Author info.");
						System.out.println("5.) Add new publisher info.");
						System.out.println("6.) Update the publisher info.");
						System.out.println("7.) Display all books by publisher.");
						System.out.println("8.) Add a new title by an author.");
						System.out.println("9.) Exit.");
						System.out.print(" Now What is Your choice: ");
						BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

						String choiceString = br.readLine();
						;

						int ch = Integer.parseInt(choiceString);

						switch (ch) {
						case 1:
							// Display all authors.
							showAllAuthors(statement);

							break;

						case 2:
							// Show all publishers
							showAllPublishers(statement);
							break;

						case 3:
							// Update the author info
							editAuthorInformation(statement);
							break;

						case 4:
							// Add new author to the author table.
							addAuthorInformation(statement, sequenceName);
							break;

						case 5:
							// Add new publisher information to the publisher table.
							addPublisherInformation(statement,
									publisherSequence);
							break;

						case 6:
							// Update the publisher information
							editPublisherInformation(statement);
							break;

						case 7:
							// Display all the books of a specific publisher
							showAllBooks(statement);
							break;

						case 8:
							// Add a new title for an author.
							addTitleToAuthor(statement, publisherSequence);
							break;

						case 9:
							System.out.println("Application Exiting");
							loop = false;

						}
					} 
					catch (NumberFormatException ne)
					{
						System.out.println(ne.getMessage()+ " not a number! try again.");
						System.exit(0);
					}

				}

				statement.close();
			} 
			catch (Exception e) 
			{
				System.out.println(e);
				e.printStackTrace();
			}
			finally 
			{
				conn.close();
			}
		}
		catch (Exception e) 
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	
	 // This function creates tables that the program will work with
	
	private final static void makeTables(Statement statement) 
	{
		try 
		{

			// Creation of authors
			String authorsTable = "CREATE TABLE authors (authorID INTEGER not null primary key, firstName CHAR(20) not null, lastName CHAR(20) not null)";

			int retVal = statement.executeUpdate(authorsTable);
			if (retVal == 0)
				System.out.println("Authors table Created");

			// Creation of publishers
			String publishersTable = "CREATE TABLE publishers ( publisherID INTEGER not null primary key , publisherName CHAR(100) not null)";

			retVal = statement.executeUpdate(publishersTable);
			if (retVal == 0)
			System.out.println("Publishers table is Created");

			// Creation of publishers.
			String titlesTable = "CREATE TABLE titles ("+ "isbn CHAR(10) not null primary key,"
					+ "title VARCHAR(500) not null,"
					+ "editionNumber INTEGER not null,"
					+ "copyright CHAR(4) not null,"
					+ "publisherID INTEGER not null,"
					+ "price NUMBER(8,-2) not null,"
					+ "FOREIGN KEY(publisherID) REFERENCES publishers(publisherID) ON DELETE CASCADE)";

			retVal = statement.executeUpdate(titlesTable);
			if (retVal == 0)
				System.out.println("Titles table is Created");

			// Create the authorISBN table.
			String authorISBNTable = "CREATE TABLE authorISBN ("
					+ "authorID INTEGER not null,"
					+ "isbn CHAR(10) not null,"
					+ "FOREIGN KEY(authorID) REFERENCES authors(authorID) ON DELETE CASCADE,"
					+ "FOREIGN KEY(isbn) REFERENCES titles(isbn) ON DELETE CASCADE)";

			retVal = statement.executeUpdate(authorISBNTable);
			if (retVal == 0)
				System.out.println("Created -- AuthorISBN table\n");

		} catch (SQLException e) 
		{
			System.out.println(e);
			e.printStackTrace();
		}

	}

	
	 // This Function provides 15 entries each per tables Authors, Publishers, Titles and authorISBN.

	private final static void initializeTables(Statement statement,String sequence, String publisherSequence) 
	{
       try{

			// insert values in the authors table. - 15 rows.
			statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'JAY','CLOSEY')");
			
			statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'LIAM','SAON')");
			
			statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'SIAM','SHULR')");
			
			statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'JURY','RANT')");
			
			statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'LELY','PICH')");
			
			statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'EMELIEY','TRAND')");
			
			statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'FRED','LSON')");
			
			statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'ZACOB','HERAD')");
			
			statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'TOM','JACKSON')");
			
			statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'ANDREW','MCHALL')");
			
			statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'LISON','DELA')");
			
			statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'BIL','HARDER')");
			
			statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'TEFFIE','LOW')");
			
			statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'ROZINA','AYLIE')");
			
			statement.executeUpdate("INSERT INTO authors(authorID, firstName, lastName) VALUES ("
							+ sequence + ",'NATALIE','HIG')");

			// insert values in the publisher table - 15 rows.
			
			statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'PRINSTON')");
			
			statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence
							+ ",'MIT REVIEW')");
			
			statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'TATA MCGRAW HILL')");
			
			statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'BIRLA')");
			
			statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'DATA PUBLISHING')");
			
			statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'L&T')");
			
			statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'WASHINGTON POST')");
			
			statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'SANTA CLARA PRESS')");
			
			statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'INDIANA')");
			
			statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'NYU PUBLISHING')");
			
			statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'ASIAN BOOKS')");
			
			statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'INDIAN EXPRESS')");
			
			statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",' TIMES OF INDIA')");
			
			statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",'SF EXRESS')");
			
			statement.executeUpdate("INSERT INTO publishers(publisherID, publisherName) VALUES ("
							+ publisherSequence + ",' PRESS')");

			// insert values in the titles table.

			
			statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price) VALUES ('0020GHSFD9','CODING IN C', 1,'2007',2,80.00)");
			
			statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price) VALUES ('4000RTQ8O9','CLASSICS', 1,'2009',3,90.00)");
			
			statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price) VALUES ('0900GHSFD9','FUNCTION', 1,'2003',2,90.00)");
			
			statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price) VALUES ('2000ZAQUD9','TECHNICAL WRITING', 3,'2005',3,220.00)");
			
			statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price) VALUES ('300120SFD9','ENGLISH', 2,'2010',3,510.00)");
			
			statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price) VALUES ('B67543HJGU','FICTION', 3,'2005',15,710.00)");
			
			statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price) VALUES ('5000GIOSF9','SCIENCE', 1,'2013',4,98.00)");
			
			statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price) VALUES ('0000GHSFH8','PROGRAMMING', 1,'2005',1,150.00)");
			
			statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price) VALUES ('600090HSFD','ENGLISH', 2,'2008',5,110.00)");
			
			statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price) VALUES ('90076HJGJK','NATUREBOOK', 1,'2009',8,70.00)");
			
			statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price) VALUES ('7000GHS897','SCIENCE GOVERNMENT', 2,'2010',6,90.00)");
			
			statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price) VALUES ('8000GKIUY0','MAGNET', 2,'2011',7,610.00)");
			
			statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price) VALUES ('1000GHSFD9','CODING IN C', 2,'2006',2,160.00)");
			
			statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price) VALUES ('A067098JGF','FICTION', 2,'2009',10,120.00)");
			
			statement.executeUpdate("INSERT INTO titles(isbn,title,editionNumber,copyright,publisherID,price) VALUES ('0000GHSFD9','PROGRAMMING', 2,'2005',1,140.00)");

			
			// insert values in the authorISBN table

			statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (15,'0000GHSFD9')");
			
			statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (12,'90076HJGJK')");
			
			statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (2,'0020GHSFD9')");
			
			statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (3,'1000GHSFD9')");
			
			statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (13,'A067098JGF')");
			
			statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (6,'300120SFD9')");
			
			statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (1,'0000GHSFH8')");
			
			statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (7,'4000RTQ8O9')");
			
			statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (8,'5000GIOSF9')");
			
			statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (9,'600090HSFD')");
			
			statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (10,'7000GHS897')");
			
			statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (11,'8000GKIUY0')");
			
			statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (4,'0900GHSFD9')");
			
			statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (5,'2000ZAQUD9')");
			
			statement.executeUpdate("INSERT INTO authorISBN(authorID,isbn) VALUES (14,'B67543HJGU')");

       
	}
	catch(SQLException e)
	{
		System.out.println(e);
		e.printStackTrace();
	}
   }

	
	 // Displays all the Authors.
	 
	private final static void showAllAuthors(Statement stmt) 
	{
		try 
		{
			ResultSet resultSet = stmt
					.executeQuery("SELECT * FROM authors ORDER BY lastName ASC,firstName ASC");
			System.out.println("Showing All Authors");
			System.out.println("----------------------");
			System.out.println("LAST NAME\t\tFIRST NAME\t\tAUTHOR-ID");

			while (resultSet.next()) 
			{
				System.out.println(resultSet.getString("lastName") + "\t"
						+ resultSet.getString("firstName") + "\t"
						+ resultSet.getInt("authorID"));
			}
			System.out.println();

		}
		catch (SQLException e) 
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	
	// Displays all publishers
	
	private final static void showAllPublishers(Statement stmt) 
	{
		try
		{
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM publishers");
			System.out.println("Showing All Publishers");
			System.out.println("------------------------");
			System.out.println("PUBLISHER-ID\tPUBLISHER NAME");

			while (resultSet.next())
			{
				System.out.println(resultSet.getInt("publisherID") + "\t\t"+ resultSet.getString("publisherName"));

			}
			System.out.println();
		}
		catch (SQLException e) 
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	
	//  This Function allows to edit author information.
	
	private final static void editAuthorInformation(Statement stmt)
	{
		boolean isFailure = true;
		try 
		{
			System.out.println("Enter the author ID: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String id = null;
			try 
			{
				id = br.readLine();

			}
			catch (IOException e)
			{
				System.out.println("Error!");
				System.exit(1);
			}

			System.out.println("Enter first name: ");
			br = new BufferedReader(new InputStreamReader(System.in));
			String fname = null;

			try
			{
				fname = br.readLine();

			}
			catch (IOException e)
			{
				System.out.println("Error!");
				System.exit(1);
			}

			System.out.println("Enter last name: ");
			br = new BufferedReader(new InputStreamReader(System.in));
			String lname = null;

			try 
			{
				lname = br.readLine();

			}
			catch (IOException e)
			{
				System.out.println("Error!");
				System.exit(1);
			}

			// UPdating the record.

			String updateAuthor = "UPDATE authors SET firstName = '" + fname+ "', lastName = '" + lname + "' WHERE authorID = " + id;
			int updated = stmt.executeUpdate(updateAuthor);

			if (updated > 0)
			{
				System.out.println("Author information updated successfully");
				System.out.println();
			}
			else
			{
				System.out.println(" Author not found - Update Failed.");
				System.out.println();
				isFailure = true;
			}
			
			if (isFailure == false) 
			{
				System.out.println("After update Author table is:");
				System.out.println();
				showAllAuthors(stmt);
				System.out.println();
			}
		} 
		catch (SQLException e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	
	//  This function adds author information.
	  
	 
	private final static void addAuthorInformation(Statement stmt,String sequenceName)
	{
		

		try 
		{
			System.out.println("Enter the details of the author to be added");

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Enter first name:");
			br = new BufferedReader(new InputStreamReader(System.in));
			String fname = null;

			try
			{
				fname = br.readLine();

			} catch (IOException e)
			{
				System.out.println("Error!");
				System.exit(1);
			}

			System.out.println("Enter last name: ");
			br = new BufferedReader(new InputStreamReader(System.in));
			String lname = null;

			try
			{
				lname = br.readLine();

			}
			catch (IOException e)
			{
				System.out.println("Error!");
				System.exit(1);
			}

			// Insert the record.
			String insertAuthor = "INSERT INTO authors(authorID, firstName, LastName) values ("+ sequenceName + ", '" + fname + "','" + lname + "')";
			int updated = stmt.executeUpdate(insertAuthor);

			if (updated == 1) 
			{
				System.out.println("Author information added successfully");
				System.out.println();
			}
			else
			{
				System.out.println("Falied to add information.");
				System.out.println();

			}

			System.out.println("THE AUTHOR TABLE AFTER INSERT");
			System.out.println();
			showAllAuthors(stmt);
			System.out.println();

		} 
		catch (SQLException e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	
	//  This function adds publisher information.
	  
	 
	private final static void addPublisherInformation(Statement stmt,String publisherSequence)
	{
		try
		{

			System.out.println("Enter the details of the publisher to be added to the table: ");

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Enter publisher name: ");
			br = new BufferedReader(new InputStreamReader(System.in));
			String publisherName = null;

			try 
			{
				publisherName = br.readLine();

			}
			catch (IOException e)
			{
				System.out.println("Error!");
				System.exit(1);
			}

			// Insert the record.
			String insertPublisher = "INSERT INTO publishers(publisherID, publisherName) VALUES ("+ publisherSequence + ", '" + publisherName + "')";
			int updated = stmt.executeUpdate(insertPublisher);

			if (updated == 1) 
			{
				System.out.println("Publisher information added successfully");
				System.out.println();
			}
			else
			{
				System.out.println("Publisher information add - FAILED !");
				System.out.println();
			}

			System.out.println("THE Publisher table after insert");
			System.out.println();
			showAllPublishers(stmt);
			System.out.println();
		} 
		catch (SQLException e) 
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	
	//  This function changes the publisher information.
	 
	private final static void editPublisherInformation(Statement st)
	{
		boolean failed = false;
		try 
		{

			System.out.println("Enter the publisher ID whose information needs to be modified: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String id = null;
			try 
			{
				id = br.readLine();

			}
			catch (IOException e)
			{
				System.out.println("Error!");
				System.exit(1);
			}

			System.out.println("Enter publisher name: ");
			br = new BufferedReader(new InputStreamReader(System.in));
			String publisherName = null;

			try
			{
				publisherName = br.readLine();

			}
			catch (IOException e)
			{
				System.out.println("Error!");
				System.exit(1);
			}

			String updatePublisher = "UPDATE publishers SET publisherName = '"+ publisherName + "' WHERE publisherID = " + id;

			int updated = st.executeUpdate(updatePublisher);

			if (updated > 0)
			{
				System.out
						.println("Publisher information updated successfully");
				System.out.println();
			} else 
			{
				System.out.println("Update failed !! The publisher ID not found in the table");
				System.out.println();
				failed = true;
			}
			if (failed == false)
			{
				System.out.println("THE PUBLISHER TABLE AFTER UPDATE");
				System.out.println();
				showAllPublishers(st);
				System.out.println();
			}
		} 
		catch (SQLException e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	
	  
	//  This displays all books takes in statement as input
	 
	private final static void showAllBooks(Statement stmt)
	{
		try 
		{

			System.out.println("Enter the publisher ID and get all books.");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String id = null;
			try 
			{
				id = br.readLine();

			}
			catch (IOException e)
			{
				System.out.println("Error!");
				System.exit(1);
			}

			String displayBooks = "SELECT title,isbn,copyright FROM titles WHERE publisherID = '"+ id + "' ORDER BY title ASC";
			ResultSet rs = stmt.executeQuery(displayBooks);

			System.out.println("BOOKS BY PUBLISHER");
			System.out.println("-----------------------------------------------");
			System.out.println("TITLE    \t\t  ISBN    \t\t    YEAR");

			while (rs.next())
			{
				System.out.println(rs.getString("title") + "\t"+ rs.getString("isbn") + "\t" + rs.getInt("copyright"));

			}
			System.out.println();
		} 
		catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	
	//  This function takes in a created SQL statement and a publisher sequence as string and adds the new title to the authors.
	  
	  private final static void addTitleToAuthor(Statement st,String publisherSequence) 
	  {
               boolean failed = false;
               try
               {
                   
                   System.out.println("Enter the author ID to add a title");
                   
                   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                   String id = null;
                   try 
                   {
                       id = br.readLine();
                       
                   } catch (IOException e)
                   {
                       System.out.println("Error!");
                       System.exit(1);
                   }
                   
                   
                   String check = "SELECT * FROM authors WHERE authorID = '" + id + "' ";
                   ResultSet rs = st.executeQuery(check);
                   
                   if(rs.next())
                   {
                       System.out.println("Enter the  title OF BOOK");
                       
                       br = new BufferedReader(new InputStreamReader(System.in));
                       String title = null;
                       try 
                       {
                           title = br.readLine();
                           
                       }
                       catch (IOException e)
                       {
                           System.out.println("Error!");
                           System.exit(1);
                       }
                       
                       System.out.println("Enter the  isbn");
                       
                       br = new BufferedReader(new InputStreamReader(System.in));
                       String isbn = null;
                       try
                       {
                           isbn = br.readLine();
                           
                       }
                       catch (IOException e) 
                       {
                           System.out.println("Error!");
                           System.exit(1);
                       }
                       
                       
                       System.out.println("Enter the edition number");
                       
                       br = new BufferedReader(new InputStreamReader(System.in));
                       String number = null;
                       try
                       {
                           number = br.readLine();
                       }
                       catch (IOException e)
                       {
                           System.out.println("Error!");
                           System.exit(1);
                       }
                       
                       System.out.println("Enter the copyright YEAR");
                       
                       br = new BufferedReader(new InputStreamReader(System.in));
                       String copyright = null;
                       try
                       {
                           copyright = br.readLine();
                       }
                       catch (IOException e)
                       {
                           System.out.println("Error!");
                           System.exit(1);
                       }
                       
                       
                       System.out.println("Enter the publisher ID");
                       br = new BufferedReader(new InputStreamReader(System.in));
                       String publisher = null;
                       try
                       {
                           publisher = br.readLine();
                       }
                       catch (IOException e)
                       {
                           System.out.println("Error!");
                           System.exit(1);
                       }
                       
                       System.out.println("Enter the price");
                       br = new BufferedReader(new InputStreamReader(System.in));
                       String price = null;
                       try
                       {
                           price = br.readLine();
                       }
                       catch (IOException e)
                       {
                           System.out.println("Error!");
                           System.exit(1);
                       }
                       
                       try
                       {
                           String insertTitle = "INSERT INTO titles(isbn, title, editionNumber, copyright, publisherID, price) values ('"+isbn + "', '"
                           + title + "', " 
                           +number+",' " +copyright+"'," 
                           +publisher+ ","+price+")";
                          int updated = st.executeUpdate(insertTitle);
                          if(updated == 1)
                        {
                          System.out.println("Title information added successfully");
                          System.out.println();
                        }
                        else
                         {
                         System.out.println("Title information add - FAILED !");
                         System.out.println();
                         }
                         }
                         catch(SQLException e)
                        {
                         // Error if the author does not exist.
                         if(e.getErrorCode() == 1)
                         {
                                                                                         
                        System.out.println(" ISBN already exist.");
                        addTitleToAuthor(st,publisherSequence);
                                                                                         
                         }
                         // Error if the publisher id does not exist in the publisher table.
                         if(e.getErrorCode() == 2291)
                        {
                                                                                         
                        System.out.println(" Publisher id not present.Enter the publisher details first");
                        System.out.println();
                        failed = true;
                                                                                                            
                    }
             }
                                                                                                            
                   if(failed == false)
                         {
               // Insert record in the authorISBN table.
                    String insertIsbn = "INSERT INTO authorISBN(authorID, isbn) values ("+id + ", '" + isbn + "')";
                          int updated = st.executeUpdate(insertIsbn);
                                                                                                                
                   if(updated == 1)
                               {
                   System.out.println("Title isbn information added successfully in the authorISBN table");
                   System.out.println(); 
                   }
            
                   }
                   }
                       else
                   {
                   System.out.println(" Author does not exist");
                   addTitleToAuthor(st,publisherSequence);
     
                      }
                                                                                                                                            
               }
                   catch(SQLException e)
              {
                   System.out.println("There was a problem with the entry please check and try again.");
                   return;
              }
         }
	}


