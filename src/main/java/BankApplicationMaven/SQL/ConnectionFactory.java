package BankApplicationMaven.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static String url = "jdbc:postgresql://postgres.cjk7zjrxbarc.us-east-2.rds.amazonaws.com:5432/postgres?";
	
	private static String user = "adevoz";
	
	private static String password = "1thatpure1";
	
	private static ConnectionFactory cf = null;

	private ConnectionFactory() {
		
	}
	public synchronized static ConnectionFactory getConnectionFactory() {
		if (cf == null) {
			cf = new ConnectionFactory();
		}
		return cf;
	}

	public Connection createConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("Unable to make connection to DataBase");
			e.printStackTrace();
		}
		return conn;
	}
}