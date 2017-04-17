package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SQLConnector {

	private Connection connection = null;

	public SQLConnector(String path, String admin, String password) {
		try {
		    this.connection =  DriverManager.getConnection(path,admin,password);
		} catch (SQLException e) {
		    System.err.println(e);
		}
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	public void example() {
		try { 
		    System.out.println("Creating statement...");
		    Statement stmt = connection.createStatement();
		    String sql;
		    sql = "SELECT * FROM test;";
		    ResultSet rs = stmt.executeQuery(sql);
		    while(rs.next()){
		         // Retrieve by column name
		         int age = rs.getInt("age");
		         String name = rs.getString("name");

		         // Display values
		         System.out.print("Age: " + age);
		         System.out.println(", Name: " + name);
		      }
		      // Clean-up environment
		      rs.close();
		      stmt.close();
		      connection.close();
		} catch (SQLException e) {
		    System.err.println(e);
		} finally {
		      try{
		         if(connection!=null)
		            connection.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }	
		}
	}
}
