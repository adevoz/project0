package BankApplicationMaven.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Driver {

	//replace with your login information
	private static String url = "jdbc:postgresql://postgres.cjk7zjrxbarc.us-east-2.rds.amazonaws.com:5432/postgres?";
	
	private static String user = "adevoz";
	
	private static String password = "myoldpassword";
	
	
	
	//JDBC - java DataBase Connectivity
	//JDBC is a series of Interfaces
	//We will be using PostgreSQL's implementation of JDBC
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("got into conneciton");
			String sql = "insert into flashcards";
			
			Statement stmt = con.createStatement();
			
			stmt.execute(sql);
			
			System.out.println("Able to connect");
		} catch (SQLException e) {
			System.out.println("Unable to reach Database");
			e.printStackTrace();
		}
	}

}
