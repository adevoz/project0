package BankApplicationMaven.BankApplicationMaven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;



public class Main {
	final static Logger Logger = org.apache.log4j.Logger.getLogger(Main.class);
	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		
		ArrayList<Employee> EmployeeList = new ArrayList<Employee>();
		ArrayList<Customer> CustomerList = new ArrayList<Customer>();
		ArrayList<BankAccount> AccountList = new ArrayList<BankAccount>();
		ArrayList<Application> ApplicationList = new ArrayList<Application>();
 		// reading current text files for objects in database
		boolean mainSystem = true;
		while (mainSystem) {
		try {
			FileInputStream fi = new FileInputStream("myCustomers.txt");
			ObjectInputStream oi = new ObjectInputStream(fi);
			boolean cont = true;
			while (cont) {
				System.out.println("Reading Input");
				if (fi.available() != 0) {
					CustomerList = (ArrayList<Customer>) oi.readObject();
					System.out.println("Customer Added");

				}
				else {
					cont = false;
					oi.close();
					fi.close();
				}
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Finished Input");
		}
		
		try {
			FileInputStream fi = new FileInputStream("myEmployees.txt");
			ObjectInputStream oi = new ObjectInputStream(fi);
			boolean cont = true;
			while (cont) {
				if (fi.available() != 0)
					EmployeeList = (ArrayList<Employee>) oi.readObject();
				else {
					cont = false;
					oi.close();
					fi.close();
				}
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Finished Input");
		}
		
		try {
			FileInputStream fi = new FileInputStream("Accounts.txt");
			ObjectInputStream oi = new ObjectInputStream(fi);
			boolean cont = true;
			while (cont) {
				if (fi.available() != 0)
					AccountList = (ArrayList<BankAccount>) oi.readObject();
				else {
					cont = false;
					oi.close();
					fi.close();
				}
			}
		}
		
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Finished Input");
		}
		try {
			FileInputStream fi = new FileInputStream("myApplications.txt");
			ObjectInputStream oi = new ObjectInputStream(fi);
			boolean cont = true;
			while (cont == true) {
				if (fi.available() != 0)
					ApplicationList = (ArrayList<Application>) oi.readObject();
				else {
					cont = false;
					oi.close();
					fi.close();
				}
			}
		}
		
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Finished Input");
		}
		
		
		String loginType = MainOptions(EmployeeList, CustomerList);
		if (loginType == null) {
			System.exit(0);
		}
		Scanner reader = new Scanner(System.in);
		
		boolean system = true;
		
		String Username = null;
		String Password = null;
		Employee employeeLogin = null;
		Customer customerLogin = null;
		
		while (system){
			
			System.out.println("Username: ");
			Username = reader.next();
			System.out.println("Password: ");
			Password = reader.next();
			
			System.out.println("Checking for User");
	
			if (loginType.equals("Employee")) {
				Employee currentLogin = null;
				for(int i = 0; i < EmployeeList.size(); i++) {
					if ((EmployeeList.get(i).getUsername().equals(Username)) &&
							EmployeeList.get(i).getPassword().equals(Password)) {
						currentLogin = EmployeeList.get(i);
						employeeLogin = currentLogin;
						system = false;
					}	
				}
				if (currentLogin == null) {
					System.out.println("Username or Password Incorrect try again");
				}
			}
			
			else if (loginType.equals("User")) {
				Customer currentLogin = null;
				for(int i = 0; i < CustomerList.size(); i++) {
					if ((CustomerList.get(i).getUsername().equals(Username)) &&
							CustomerList.get(i).getPassword().equals(Password)) {
						currentLogin = CustomerList.get(i);
						customerLogin = currentLogin;
						system = false;
					}	
				}
				if (currentLogin == null) {
					System.out.println("Username or Password Incorrect try again");
					System.exit(0);
				}
		}
	  }
			system = true;
			while(system) {
			if (loginType.equals("Employee"))
			{
				system = EmployeeOptions(EmployeeList,
						CustomerList,
						AccountList,
						ApplicationList, Username, employeeLogin);
			}
			else if(loginType.equals("User")) {
					system = CustomerOptions(EmployeeList,
					CustomerList,
					AccountList,
					ApplicationList, Username, customerLogin);
				}
			}
		}
}
	public static ArrayList<Employee> inputEmployee(ArrayList<Employee> EmployeeList) {
		System.out.println("Please Input First Name, Last Name, username, password: ");
		Scanner reader = new Scanner(System.in);
		String[] newEmployeeInfo = reader.nextLine().split(", ");
		System.out.println("Admin Y/N?");
		JobTitle tempJobStatus;
		String admin = reader.next();
		switch (admin) {
			case "Y":
				tempJobStatus = JobTitle.ADMIN;
				break;
			default:
				tempJobStatus = JobTitle.NORMAL;
				break;
		}
		Employee newEmployee = new Employee(newEmployeeInfo[0], newEmployeeInfo[1], newEmployeeInfo[2], newEmployeeInfo[3], tempJobStatus);
		EmployeeList.add(newEmployee);
		try {
			FileOutputStream f = new FileOutputStream("myEmployees.txt");
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(EmployeeList);
			f.close();
			o.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
		return EmployeeList;
	}
	public static ArrayList<Application> inputApplication(ArrayList<Application> ApplicationList, Application newApplication) {
		try {
			FileOutputStream f = new FileOutputStream("myApplications.txt");
			ObjectOutputStream o = new ObjectOutputStream(f);
			ApplicationList.add(newApplication);
			o.writeObject(ApplicationList);
			f.close();
			o.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
		return ApplicationList;
	}
	public static ArrayList<BankAccount> inputBank(ArrayList<BankAccount> AccountList, BankAccount newBankAccount) {
		try {
			FileOutputStream f = new FileOutputStream("Accounts.txt");
			ObjectOutputStream o = new ObjectOutputStream(f);
			AccountList.add(newBankAccount);
			o.writeObject(AccountList);
			f.close();
			o.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
		return AccountList;
	}
	public static ArrayList<Customer> inputCustomer(ArrayList<Customer> CustomerList) {
		System.out.println("Please Input First Name, Last Name, username, password: ");
		Scanner reader = new Scanner(System.in);
		String[] newCustomerInfo = reader.nextLine().split(", ");
		Customer newCustomer = new Customer(newCustomerInfo[0], newCustomerInfo[1], newCustomerInfo[2], newCustomerInfo[3]);
		try {
			FileOutputStream f = new FileOutputStream("myCustomers.txt");
			ObjectOutputStream o = new ObjectOutputStream(f);
			CustomerList.add(newCustomer);
			o.writeObject(CustomerList);
			f.close();
			o.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
		return CustomerList;
	}
	public static void refreshDatabase(ArrayList<Employee> EmployeeList,
	ArrayList<Customer> CustomerList,
	ArrayList<BankAccount> AccountList,
	ArrayList<Application> ApplicationList) {
			try {
				FileOutputStream f = new FileOutputStream("myEmployees.txt");
				ObjectOutputStream o = new ObjectOutputStream(f);
				o.writeObject(EmployeeList);
				f.close();
				o.close();
			}
			catch (FileNotFoundException e) {
				System.out.println("File not found");
			} catch (IOException e) {
				System.out.println("Error initializing stream");
			}
				try {
					FileOutputStream f = new FileOutputStream("myApplications.txt");
					ObjectOutputStream o = new ObjectOutputStream(f);
					o.writeObject(ApplicationList);
					f.close();
					o.close();
				}
				catch (FileNotFoundException e) {
					System.out.println("File not found");
				} catch (IOException e) {
					System.out.println("Error initializing stream");
				}
					try {
						FileOutputStream f = new FileOutputStream("myCustomers.txt");
						ObjectOutputStream o = new ObjectOutputStream(f);
						o.writeObject(CustomerList);
						f.close();
						o.close();
					}
					catch (FileNotFoundException e) {
						System.out.println("File not found");
					} catch (IOException e) {
						System.out.println("Error initializing stream");
					}
						try {
							FileOutputStream f = new FileOutputStream("Accounts.txt");
							ObjectOutputStream o = new ObjectOutputStream(f);
							o.writeObject(AccountList);
							f.close();
							o.close();
						}
						catch (FileNotFoundException e) {
							System.out.println("File not found");
						} catch (IOException e) {
							System.out.println("Error initializing stream");
						
		return;
		}
	  }
	public static boolean  CustomerOptions(ArrayList<Employee> EmployeeList,
			ArrayList<Customer> CustomerList,
			ArrayList<BankAccount> AccountList,
			ArrayList<Application> ApplicationList, String Username, Customer customerLogin) {
		System.out.println("Current Customer Options:");
		System.out.println("1: View Balances");
		System.out.println("2: Deposit");
		System.out.println("3: TransferFunds");
		System.out.println("4: Withdraw");
		System.out.println("5: Create Account");
		System.out.println("0: Return to Main Menu");
		Scanner reader = new Scanner(System.in);
		int availableAccount = -1;
		for (int i = 0; i < AccountList.size(); i++) {
			if (AccountList.get(i).getAllowedUsers().contains(Username)) {
				availableAccount = i;
			}
		}
		String currentchoice = reader.next();
		switch (currentchoice) {
			case "1":
				if (availableAccount < 0) {
					System.out.println("No Account Available");
					break;
				}
				System.out.println("Current Balance: " + AccountList.get(availableAccount).getCurrentAmmount());
				break;
			case "2":
				if (availableAccount < 0) {
					System.out.println("No Account Available");
					break;
				}
				System.out.println("Please Enter Amount to Deposit: ");
				Double deposit = reader.nextDouble();
				if (AccountList.get(availableAccount).depositing(deposit)) {
					System.out.print("Request Processed");
				}
				else
					System.out.print("Invalid Input");
				break;
			case "3":
				if (availableAccount < 0) {
					System.out.println("No Account Available");
					break;
				}
				System.out.print("Please Enter Amount to Transfer ");
				Double transferAmount = reader.nextDouble();
				AccountList = customerLogin.Transferto(AccountList, availableAccount, transferAmount);
				break;
			case "4":
				if (availableAccount < 0) {
					System.out.println("No Account Available");
					break;
				}
				System.out.println("Please Enter Amount to Withdraw: ");
				Double withdraw = reader.nextDouble();
				AccountList.get(availableAccount).withdrawing(withdraw);
				if (AccountList.get(availableAccount).withdrawing(withdraw)) {
					System.out.print("Request Processed");
				}
				else
					System.out.print("Invalid Input");
				break;
			case "5":
				System.out.println("Enter type of Account Type: single/joint");
				String currentChoice = reader.next();
				ArrayList<String> usernames = new ArrayList<String>();
				usernames.add(Username);
				Application newApplication = null;
				if (currentChoice.equals("joint")) {
					System.out.println("Enter other user");
					String secondUser = reader.next();
					usernames.add(secondUser);
					newApplication = new Application(Applicationtype.JOINT, usernames);
					ApplicationList = inputApplication(ApplicationList, newApplication);
				}
				else {
					newApplication = new Application(Applicationtype.SINGLE, usernames);
					ApplicationList = inputApplication(ApplicationList, newApplication);
				}
				System.out.println("Application in PENDING");
				break;
			case "0":
				refreshDatabase(EmployeeList,CustomerList, AccountList, ApplicationList);
				return false;
		}
		refreshDatabase(EmployeeList,CustomerList, AccountList, ApplicationList);
		return true;
	}
	public static boolean EmployeeOptions(ArrayList<Employee> EmployeeList,
			ArrayList<Customer> CustomerList,
			ArrayList<BankAccount> AccountList,
			ArrayList<Application> ApplicationList, String Username, Employee employeeLogin) {
		employeeLogin.printOptions();
		Scanner reader = new Scanner(System.in);
		String currentchoice = reader.next();
		switch (currentchoice) {
		case "1":
			System.out.println("Enter Account Number: ");
			long accountNumber = reader.nextLong();
			employeeLogin.viewAccountBalance(AccountList, accountNumber);
			break;
		case "2":
			System.out.println("Enter Username: ");
			String personToFind = reader.next();
			employeeLogin.viewPersonalInfo(CustomerList, personToFind);
		case "3":
			for (int i = 0; i < ApplicationList.size() ; i++) {
				if (ApplicationList.get(i).getApplicationStatus() == Status.PENDING) {
					ApplicationList.get(i).printInfo();
					System.out.println("Will you approve Y/N?");
					currentchoice = reader.next();
					if (currentchoice.equals("Y")){
						System.out.println("Application Approved opening Account");
						ApplicationList.get(i).setApplicationStatus(Status.APPROVED);
						if (ApplicationList.get(i).getApplicationType() == (Applicationtype.JOINT)) {
							BankAccount tempBank = new BankAccount (AccountType.JOINT, ApplicationList.get(i).getUserList());
							AccountList = inputBank(AccountList, tempBank);
						}
						else if (ApplicationList.get(i).getApplicationType() == (Applicationtype.SINGLE)){
							BankAccount tempBank = new BankAccount (AccountType.SINGLE, ApplicationList.get(i).getUserList());
							AccountList = inputBank(AccountList, tempBank);
						}
					}
					else 
						System.out.println("Still in PENDING status");
				}
				else if (ApplicationList.size() == 0)
					System.out.println("No Applications Pending!");
			}
			break;
		case "4":
			System.out.println("Enter Account Number");
			long accountNumberDeposit = reader.nextLong();
			System.out.println("Enter Amount");
			double amount = reader.nextDouble();
			AccountList = employeeLogin.withdraw(AccountList, accountNumberDeposit, amount);
			break;
		case "5":
			System.out.println("Enter Account Number");
			long accountNumberWithdraw = reader.nextLong();
			System.out.println("Enter Amount");
			double amountToDeposit = reader.nextDouble();
			AccountList = employeeLogin.deposit(AccountList, accountNumberWithdraw, amountToDeposit);
			break;
		case "6":
			System.out.println("Enter Account Number from: ");
			long fromAccountNumber = reader.nextLong(); 
			System.out.println("Enter Amount to Transfer");
			int amountToTransfer = reader.nextInt();
			AccountList = employeeLogin.Transferto(AccountList, fromAccountNumber, amountToTransfer);
			break;
		case "7":
			System.out.println("Enter Account Number to Delete: ");
			long accountDelete = reader.nextLong();
			AccountList = employeeLogin.delete(AccountList, accountDelete);
			break;
		case"0":
			refreshDatabase(EmployeeList,CustomerList, AccountList, ApplicationList);
			return false;
		}
		refreshDatabase(EmployeeList,CustomerList, AccountList, ApplicationList);
		return true;
	  }
	public static String MainOptions(ArrayList<Employee> EmployeeList, ArrayList<Customer> CustomerList) {
		Scanner reader = new Scanner(System.in);
		System.out.println("1: User Login");
		System.out.println("2: Employe Login");
		System.out.println("3: New Customer");
		System.out.println("4: New Employee");
		System.out.println("0: System Exit");
		System.out.println("Please specify login type: ");
		String loginType = reader.nextLine();
		switch (loginType) {
		case "1":
			System.out.println("User Login");
			loginType = "User";
			break;
		case "2":
			System.out.println("Employee Login");
			loginType = "Employee";
			break;
		case "3":
			System.out.println("New Customer");
			CustomerList = inputCustomer(CustomerList);
			loginType = "User";
			break;
		case "4":
			EmployeeList = inputEmployee(EmployeeList);
			loginType = "Employee";
			break;
		case "0":
			return null;
		default:
			System.out.println("Invalid Input");
			break;
		}
		return loginType;
	}
}