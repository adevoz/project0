package BankApplicationMaven.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BankApplicationMaven.BankApplicationMaven.Application;
import BankApplicationMaven.BankApplicationMaven.BankAccount;
import BankApplicationMaven.BankApplicationMaven.Customer;
import BankApplicationMaven.BankApplicationMaven.Employee;
import BankApplicationMaven.BankApplicationMaven.Logger;
import BankApplicationMaven.BankApplicationMaven.Person;

public class DAOimplentation implements DAO{
	private static Connection conn = ConnectionFactory
			.getConnectionFactory().createConnection(); 
	
	@Override
	public void createCustomer(Customer fc) {

		String sql = "insert into Person (firstname, lastname, username, pass) values('" 
		+ fc.getfName() + "','" 
		+ fc.getlName() + "','"
		+ fc.getUsername() + "','"
		+ fc.getPassword() + "')";
		
		try {
			conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Failed to add Customerto DB");
			e.printStackTrace();
		}
		
		
	}
	
	public void createLogger(Logger fc) {
		String sql = "insert into logger (transactiontype, userID, transactiontime) values('"
				+ fc.getTransaction() + "','"
				+ fc.getUser() + "','"
				+ fc.getTimeOf().toString() + "')";
		try {
			conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Failed on logger");
			e.printStackTrace();
		}
	}

	public void createEmployee(Employee fc, String adminstatus) {
		String sql = "insert into Person (firstname, lastname, username, pass) values('" 
				+ fc.getfName() + "','" 
				+ fc.getlName() + "','"
				+ fc.getUsername() + "','"
				+ fc.getPassword() + "')";
				
				try {
					conn.createStatement().executeUpdate(sql);
				} catch (SQLException e) {
					System.out.println("Failed to add Customerto DB");
					e.printStackTrace();
				}
		
		String sql2 = "INSERT INTO BankEmployee(PersonID, Admin) values("
				+ "(SELECT ID FROM PERSON WHERE username = '" + fc.getUsername() + "')"
				+ "," + "'" + adminstatus + "');";
		
				try {
					conn.createStatement().executeUpdate(sql2);
				} catch (SQLException e) {
					System.out.println("Failed to add Customerto DB");
					e.printStackTrace();
				}
	}
	
	public void createBankAccount(ArrayList<String> userList) {
		String tempType = "N";
		String sql;
		String sql2;
		String sql3 = null;
		if (userList.size() > 1) {
			tempType = "Y";
			sql = "INSERT INTO BankAccount(balance, joint, user1, user2) values("
					+ 0 + ",'" + tempType + "','" + userList.get(0) + "','" + userList.get(1) + "');";
			sql2 = "INSERT INTO BankCustomer(PersonID, BankID) values("
					+ "(SELECT ID FROM Person WHERE username = '" + userList.get(0) + "'),"
					+ "(SELECT accountnumber FROM BankAccount WHERE user1 = '" + userList.get(0) + "'"
					+ "OR user2 = '" + userList.get(0) + "'));";
			sql3 = "INSERT INTO BankCustomer(PersonID, BankID) values("
					+ "(SELECT ID FROM Person WHERE username = '" + userList.get(1) + "'),"
					+ "(SELECT accountnumber FROM BankAccount WHERE user1 = '" + userList.get(1) + "'"
					+ "OR user2 = '" + userList.get(1) + "'));";
		}
		else {
		sql = "INSERT INTO BankAccount(balance, joint, user1) values("
					+ 0 + ",'" + tempType + "','" + userList.get(0) + "');";
		sql2 = "INSERT INTO BankCustomer(PersonID, BankID) values("
				+ "(SELECT ID FROM Person WHERE username = '" + userList.get(0) + "'),"
				+ "(SELECT accountnumber FROM BankAccount WHERE user1 = '" + userList.get(0) + "'"
				+ "OR user2 = '" + userList.get(0) + "'));";
		}
		try {
			conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Failed to add Account");
			e.printStackTrace();
		}
		
		try {
			conn.createStatement().executeUpdate(sql2);
		} catch (SQLException e) {
			System.out.println("Failed to BankCustomer");
			e.printStackTrace();
		}
		if (!(sql3==null)) {
			try {
				conn.createStatement().executeUpdate(sql3);
			} catch (SQLException e) {
				System.out.println("Failed on Joint add");
				e.printStackTrace();
			}
		}
	}
	@Override
	public void deleteCustomer(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCustomer(Customer fc) {
		
		String sql = "update flash_card set answer = ? where question = ?";
		
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, fc.getfName());
			preparedStmt.setString(2, fc.getlName());
			preparedStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Failed to update flash card");
			e.printStackTrace();
		}

	}

	@Override
	public Person readCustomer(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from person where ID = " + id + ";";
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
		}
		catch (SQLException e){
			System.out.println("Failed to retrieve all Person from Database");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Application> readApplications() {
		String sql = "select * from Application";
		ArrayList<Application> fcList = new ArrayList<>();
		ArrayList<String> fcUser = new ArrayList<String>();
		Application tempApplication = null;
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while(rs.next()){
				String rstemp = rs.getString(4);
				if (! (rstemp == null))
				{
					fcUser.add(rs.getString(3));
					fcUser.add(rs.getString(4));
				}
				else {
					fcUser.add(rs.getString(3));
				}
				tempApplication = new Application(fcUser);
				fcList.add(tempApplication);
			}
		} catch (SQLException e) {
			System.out.println("Failed to retrieve all flash cards from DataBase");
			e.printStackTrace();
		}
		return fcList;
	}
	public Person checkLogin(String username , String password) {
		Person tempPerson = null;
		try {
			
			String sql = "SELECT * FROM PERSON WHERE username = ? and pass = ?";
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, password);
			ResultSet results = preparedStmt.executeQuery();
			if (results.next())
			{
				tempPerson = new Person(results.getString(2), results.getString(3), results.getString(4), results.getString(5));
				return tempPerson;
			}
			return tempPerson;
		} catch (SQLException e) {
			System.out.println("failed to check username/password");
			e.printStackTrace();
		}
		return tempPerson;
	}
	public String checkEmployment(String username) {
		try {
			
			String sql = "SELECT ID FROM PERSON WHERE username = ? ";
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, username);
			ResultSet results = preparedStmt.executeQuery();
			String sql2 = "SELECT admin FROM BankEmployee WHERE PersonID = ? ";
			PreparedStatement preparedStmt2 = conn.prepareStatement(sql2);
			if (results.next()) {
				preparedStmt2.setLong(1, results.getInt(1));
				ResultSet result = preparedStmt2.executeQuery();
				if (result.next())
					return result.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("failed to check username/password2");
			e.printStackTrace();
		}
			return "N";
	}
	public void createApplication(ArrayList<String> Users) {
		int count = Users.size();
		if (count > 1) {
			String sql = "insert into Application (status, user1, user2) values(" 
					+ "'PENDING'" + ",'" 
					+ Users.get(0)  + "','"
					+ Users.get(1)  + "');";
					try {
						conn.createStatement().executeUpdate(sql);
					} catch (SQLException e) {
						System.out.println("Failed to add Customerto DB");
						e.printStackTrace();
					}
		}
		else {
		String sql = "insert into Application (status, user1) values(" 
				+ "'PENDING'" + ",'" 
				+ Users.get(0)  + "')";
				
				try {
					conn.createStatement().executeUpdate(sql);
				} catch (SQLException e) {
					System.out.println("Failed to add Customerto DB");
					e.printStackTrace();
				}
				
		}
	}
	public void updateApplication(int ID , String approval) {
		String sql = null;
		if (approval.equals("Y")) {
			sql = "UPDATE Application SET status = 'APPROVED'"
					+ "WHERE applicationnumber=" + ID + ";";
		}
		else{
			sql = "UPDATE Application SET status = 'DENIED'"
					+ "WHERE applicationnumber=" + ID + ";";
		}
		try {
			conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Failed on updateApplication");
			e.printStackTrace();
		}
	}
	@Override
	public List<Application> readAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}
	public BankAccount grabAccount(String user) {
		BankAccount tempAccount = null;
		String sql = "SELECT * FROM BankAccount WHERE user1 = ? OR user2 = ?";
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, user);
			preparedStmt.setString(2, user);
			ResultSet results = preparedStmt.executeQuery();
			if (results.next()) {
				ArrayList<String> User = new ArrayList<String>();
				String user2 = results.getString(4);
				if (! (user2 == null)) {
					User.add(user2);
				}
				User.add(results.getString(3));
				tempAccount = new BankAccount(results.getInt(1), results.getInt(2), results.getString(3), User);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
			//e.printStackTrace();
		}
		return tempAccount;
	}
	public void updateAccount(BankAccount bankUpdate) {
		String sql = "UPDATE BankAccount SET balance = ? WHERE accountnumber = ?;";
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setInt(1, (int) bankUpdate.getCurrentAmmount());
			preparedStmt.setInt(2, (int) bankUpdate.getAccountNumber());
			preparedStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Account update failed check database!");
			e.printStackTrace();
		}
	}
	public BankAccount grabAccountwithNumber(int accountNum) {
		BankAccount tempAccount = null;
		String sql = "SELECT * FROM BankAccount WHERE accountnumber = ?";
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setInt(1, accountNum);
			ResultSet results = preparedStmt.executeQuery();
			if (results.next()) {
				ArrayList<String> User = new ArrayList<String>();
				String user2 = results.getString(4);
				if (! (user2 == null)) {
					User.add(user2);
				}
				User.add(results.getString(3));
				tempAccount = new BankAccount(results.getInt(1), results.getInt(2), results.getString(3), User);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempAccount;
	}
}
