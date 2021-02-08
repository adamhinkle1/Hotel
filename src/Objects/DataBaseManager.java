package Objects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DataBaseManager {
	private static Connection c = null;
	private static Statement stmt = null;
	private static DataBaseManager db = null;
	private DataBaseManager() {  //constructor, establishes connection to database.
	     try {
			Class.forName("org.sqlite.JDBC");
		    c = DriverManager.getConnection("jdbc:sqlite:GUEST.");
		    stmt = c.createStatement();
		    createTables();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static DataBaseManager getInstance() {
		if (db == null) {
			db = new DataBaseManager();
		}
		return db;
	}
	public List<String []> findGuestByName(String name) {
		List<String []> guests = new ArrayList<String []>();
		String sqlSelect = "SELECT g.NAME, g.PHONE, g.EMAIL, c.CARDNUM, c.EXPDATE, c.CVV "
				+ "FROM GUEST g "
				+ "INNER JOIN CARD c  ON "
				+ "G.CARDNUM = C.CARDNUM "
	    		 + "WHERE NAME = '" + name + "';";
		try {
			ResultSet  results = stmt.executeQuery(sqlSelect);
			while (results.next()) {
				String [] row = {results.getString("NAME"),results.getString("PHONE"),results.getString("EMAIL"),results.getString("CARDNUM"),
						results.getString("EXPDATE"),results.getString("CVV")};
				guests.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return guests;
		
	}
	public void insertGuest(String name, String phone, String email, String cardnum, String expdate, String cvv) {
	     try {
	 		String sqlInsert = "INSERT OR REPLACE INTO GUEST  (NAME, PHONE, EMAIL, CARDNUM) "
		     		+ "VALUES ('" + name + "', '" + phone + "', '" + email + "', '" + cardnum + "');";
			stmt.executeUpdate(sqlInsert);
			sqlInsert = "INSERT OR REPLACE INTO CARD (CARDNUM, EXPDATE, CVV) "
					+ "VALUES ('" + cardnum + "', '" + expdate + "', '" + cvv + "');";
			stmt.executeUpdate(sqlInsert);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insertTransaction(String email, String total, String date, int ID, int open) {
	     try {
	 		String sqlInsert = "INSERT OR REPLACE INTO TRANSACTIONS  (EMAIL, TOTAL, DATE, ID, OPEN) "
		     		+ "VALUES ('" + email + "', '" + total + "', '" + date + "', '" + ID + "', '" + open + "');";
			stmt.executeUpdate(sqlInsert);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String [] findGuestByEmail(String email) {
		String [] results = new String [6];
		String sqlSelect = "SELECT g.NAME, g.PHONE, g.EMAIL, c.CARDNUM, c.EXPDATE, c.CVV "
				+ "FROM GUEST g "
				+ "INNER JOIN CARD c  ON "
				+ "G.CARDNUM = C.CARDNUM "
	    		 + "WHERE EMAIL = '" + email + "';";
	     try {
			ResultSet result = stmt.executeQuery(sqlSelect);
			if (result.isClosed()) {
				return new String [] {"No Results Found","","","","",""};
			}
			results[0] = result.getString("NAME");
			results[1] = result.getString("PHONE");
			results[2] = result.getString("EMAIL");
			results[3] = result.getString("CARDNUM");
			results[4] = result.getString("EXPDATE");
			results[5] = result.getString("CVV");
 			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	public List<String[]> queryAllTransactionsByEmail(String email) {
		List <String []> list = new ArrayList<String []> ();
		String sqlSelect = "select * "
				+ "from transactions "
				+ "where email = '" + email + "';";
		try {
			ResultSet  results = stmt.executeQuery(sqlSelect);
			while (results.next()) {
				String [] row = {results.getString("EMAIL"),results.getString("TOTAL"),results.getString("DATE")
						,Integer.toString(results.getInt("ID")),Integer.toString(results.getInt("OPEN"))};
				list.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public List<String[]> queryOpenTransactionsByEmail(String email) {
		List <String []> list = new ArrayList<String []> ();
		String sqlSelect = "SELECT * "
				+ "from transactions "
				+ "where EMAIL = '" + email + "' "
				+ "AND OPEN = 1;";
		try {
			ResultSet  results = stmt.executeQuery(sqlSelect);
			while (results.next()) {
				String [] row = {results.getString("EMAIL"),results.getString("TOTAL"),results.getString("DATE")
						,Integer.toString(results.getInt("ID")),Integer.toString(results.getInt("OPEN"))};
				list.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}	
	public String [] getTransaction(String transNumber) {
		String [] results = new String [5];
		String sqlSelect = "SELECT * "
				+ "from transactions "
				+ "where ID = '" + transNumber + "'; ";
		try {
			ResultSet result = stmt.executeQuery(sqlSelect);
			if (result.isClosed()) {
				return new String [] {"No Results Found","","","","",""};
			}
			results[0] = result.getString("EMAL");
			results[1] = result.getString("TOTAL");
			results[2] = result.getString("DATE");
			results[3] = Integer.toString(result.getInt("ID"));
			results[4] = Integer.toString(result.getInt("OPEN"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	public void createTables() {
	  try {
	     String sql = "CREATE TABLE IF NOT EXISTS CARD "
	     		+ " (CARDNUM			TEXT	PRIMARY KEY NOT NULL, "
	     		+ " EXPDATE 			TEXT	NOT NULL, "
	     		+ " CVV 				TEXT	NOT NULL);";
	     stmt.executeUpdate(sql);
	     
	     sql = "CREATE TABLE IF NOT EXISTS GUEST " +
                    " (NAME         TEXT    NOT NULL, " + 
                    " PHONE         VARCHAR(20)    NOT NULL, " + 
                    " EMAIL         TEXT	PRIMARY KEY NOT NULL, " + 
                    " CARDNUM		TEXT		NOT NULL,"
                    + "FOREIGN KEY (CARDNUM) "
                    + "REFERENCES CARD (CARDNUM));"; 
	     stmt.executeUpdate(sql);

	     sql = "CREATE TABLE IF NOT EXISTS TRANSACTIONS "
	     		+ "(EMAIL 		VARCHAR(20) 	NOT NULL, "
	     		+ "TOTAL 		TEXT	NOT NULL, "
	     		+ "DATE			TEXT	NOT NULL, "
	     		+ "ID 			INT		PRIMARY KEY NOT NULL,"
	     		+ "OPEN 		INT		NOT NULL,  "
	     		+ "FOREIGN KEY (EMAIL) "
	     		+ "REFERENCES GUEST (EMAIL)); ";
	     stmt.executeUpdate(sql);
	     stmt.close();
	  } catch ( SQLException e ) {
	     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	     System.exit(0);
	  }
	}		
}
