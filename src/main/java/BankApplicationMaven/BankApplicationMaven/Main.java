package BankApplicationMaven.BankApplicationMaven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import BankApplicationMaven.SQL.ConnectionFactory;
import BankApplicationMaven.SQL.DAO;
import BankApplicationMaven.SQL.DAOimplentation;

public class Main {
	final static DAOimplentation customerDAO = new DAOimplentation();
	//final static //Logger //Logger = org.apache.log4j.//Logger.getLogger("BankLogger");
	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		//BasicConfigurator.configure();
		ArrayList<Employee> EmployeeList = new ArrayList<Employee>();
		ArrayList<Customer> CustomerList = new ArrayList<Customer>();
		ArrayList<BankAccount> AccountList = new ArrayList<BankAccount>();
		ArrayList<Application> ApplicationList = new ArrayList<Application>();
 		// reading current text files for objects in database
		boolean mainSystem = true;
		while (mainSystem) {
		
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
				Person tempPerson = customerDAO.checkLogin(Username, Password);
				String adminStatus = customerDAO.checkEmployment(Username);
				if (adminStatus.equals("Y"))
				{
					employeeLogin = new Employee(tempPerson.getfName(), tempPerson.getlName(), tempPerson.getUsername(), tempPerson.getPassword(), JobTitle.ADMIN);
					break;
				}
				else
				{
					employeeLogin = new Employee(tempPerson.getfName(), tempPerson.getlName(), tempPerson.getUsername(), tempPerson.getPassword(), JobTitle.NORMAL);
					break;
				}
				
			}
			
			else if (loginType.equals("User")) {
				Person currentLogin = customerDAO.checkLogin(Username, Password);
				customerLogin = new Customer(currentLogin.getfName(), currentLogin.getlName(), currentLogin.getUsername(), currentLogin.getPassword());
				break;
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
		customerDAO.createEmployee(newEmployee, admin);
		Logger newLog = new Logger(newEmployee.getUsername(), "Creating New Employee" , LocalDateTime.now());
		customerDAO.createLogger(newLog);
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
		Logger newLog = new Logger(newCustomer.getUsername(), "Customer Creation", LocalDateTime.now());
		customerDAO.createLogger(newLog);
		customerDAO.createCustomer(newCustomer);
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
		BankAccount customerAccount = null;
		customerAccount = customerDAO.grabAccount(customerLogin.getUsername());
		String currentchoice = reader.next();
		switch (currentchoice) {
			case "1":
				if (customerAccount == null) {
					System.out.println("No Account Available");
					break;
				}
				System.out.println(customerLogin.getfName() + customerLogin.getlName());
				System.out.println("Current Balance: " + customerAccount.getCurrentAmmount());
				Logger newLog = new Logger(customerLogin.getUsername(), "Check Balance", LocalDateTime.now());
				customerDAO.createLogger(newLog);
				break;
			case "2":
				if (customerAccount == null) {
					System.out.println("No Account Available");
					break;
				}
				System.out.println("Please Enter Amount to Deposit: ");
				Double deposit = reader.nextDouble();
				boolean approval = customerAccount.depositing(deposit);
				if (approval) {
					System.out.println("Request Processed");
					customerDAO.updateAccount(customerAccount);
				}
				else
					System.out.println("Invalid Input");
				
				System.out.println("New Balance: " + customerAccount.getCurrentAmmount());
				Logger newLog1 = new Logger(customerLogin.getUsername(), "Deposit" , LocalDateTime.now());
				customerDAO.createLogger(newLog1);
				break;
			case "3":
				if (customerAccount == null) {
					System.out.println("No Account Available");
					break;
				}
				System.out.println("Please Enter Amount to Transfer ");
				Double transferAmount = reader.nextDouble();
				System.out.println("Please Enter Account Number to Tranfer");
				int accountTransferTo = reader.nextInt();
				BankAccount tempAccountTransfer = customerDAO.grabAccountwithNumber(accountTransferTo);
				if (tempAccountTransfer == null)
				{
					System.out.println("Invalid Account Number");
					break;
				}
				boolean checkDeposit = customerAccount.withdrawing(transferAmount);
				boolean checkWithdraw = tempAccountTransfer.depositing(transferAmount);
				if (checkDeposit && checkWithdraw) {
					customerDAO.updateAccount(tempAccountTransfer);
					customerDAO.updateAccount(customerAccount);
					System.out.println("Transfer Processed!");
				}
				Logger newLog2 = new Logger(customerLogin.getUsername(), "Transfer", LocalDateTime.now());
				customerDAO.createLogger(newLog2);
				break;
			case "4":
				if (customerAccount == null) {
					System.out.println("No Account Available");
					break;
				}
				System.out.println("Please Enter Amount to Withdraw: ");
				Double withdraw = reader.nextDouble();
				boolean withdrawApproval = customerAccount.withdrawing(withdraw);
				if (withdrawApproval) {
					System.out.println("Request Processed");
					customerDAO.updateAccount(customerAccount);
				}
				else
					System.out.println("Invalid Input");
				//Logger.info("Transfer Request by" + customerLogin.getfName() + withdrawApproval); 
				System.out.println("New Balance: " + customerAccount.getCurrentAmmount());
				Logger newLog3 = new Logger(customerLogin.getUsername(), "Withdraw", LocalDateTime.now());
				customerDAO.createLogger(newLog3);
				break;
			case "5":
				System.out.println("Enter type of Account Type: single/joint");
				String currentChoice = reader.next();
				ArrayList<String> usernames = new ArrayList<String>();
				usernames.add(Username);
				Application newApplication = null;
				Logger newLog9 = new Logger(customerLogin.getUsername(), "Application Created",  LocalDateTime.now());
				customerDAO.createLogger(newLog9);
				if (currentChoice.equals("joint")) {
					System.out.println("Enter other user");
					String secondUser = reader.next();
					usernames.add(secondUser);
					customerDAO.createApplication(usernames);
					System.out.println("Application in PENDING");
					break;
				}
				else if(currentChoice.equals("single")) {
					customerDAO.createApplication(usernames);
					System.out.println("Application in PENDING");
					break;
				}
				System.out.print("Application not sent INVALID INPUT!");
				//Logger.info("Application Request by" + customerLogin.getfName()); 
				break;
			case "0":
				refreshDatabase(EmployeeList,CustomerList, AccountList, ApplicationList);
				//Logger.info("System Database refresh");
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
			BankAccount tempBankAccount = customerDAO.grabAccountwithNumber((int) accountNumber);
			if (tempBankAccount == null)
			{
				System.out.println("Invalid Account Number!");
				break;
			}
			employeeLogin.viewAccountBalance(tempBankAccount);
			Logger newLog = new Logger(employeeLogin.getUsername(), "View Account", LocalDateTime.now());
			customerDAO.createLogger(newLog);
			break;
		case "2":
			System.out.println("Enter Username: ");
			String personToFind = reader.next();
			employeeLogin.viewPersonalInfo(CustomerList, personToFind);
			Logger newLog3 = new Logger(employeeLogin.getUsername(), "View User", LocalDateTime.now());
			customerDAO.createLogger(newLog3);
			break;
		case "3":
			ApplicationList = customerDAO.readApplications();
			for (int i = 0; i < ApplicationList.size() ; i++) {
				if (ApplicationList.get(i).getApplicationStatus() == Status.PENDING) {
					ApplicationList.get(i).printInfo();
					System.out.println("Will you approve Y/N?");
					currentchoice = reader.next();
					if (currentchoice.equals("Y")){
						System.out.println("Application Approved opening Account");
						ApplicationList.get(i).setApplicationStatus(Status.APPROVED);
						if (ApplicationList.get(i).getApplicationType() == (Applicationtype.JOINT)) {
							customerDAO.updateApplication((int) ApplicationList.get(i).getApplicationNumber(), "Y");
							customerDAO.createBankAccount(ApplicationList.get(i).getUserList());
							Logger newLog4 = new Logger(employeeLogin.getUsername(), "Application Approved", LocalDateTime.now());
							customerDAO.createLogger(newLog4);
						}
						else if (ApplicationList.get(i).getApplicationType() == (Applicationtype.SINGLE)){
							customerDAO.updateApplication((int) ApplicationList.get(i).getApplicationNumber(), "Y");
							customerDAO.createBankAccount(ApplicationList.get(i).getUserList());
							//Logger.info("Employee approved single account");
						}
					}
					else if(currentchoice.equals("N")) {
						System.out.println("Application Decline!");
						customerDAO.updateApplication((int) ApplicationList.get(i).getApplicationNumber(), "N");
						ApplicationList.get(i).setApplicationStatus(Status.DECLINED);
						//Logger.info("Employee" + employeeLogin.getfName() + "declined single account");
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
			long accountNumberWithdraw = reader.nextLong();
			System.out.println("Enter Amount");
			double amount = reader.nextDouble();
			BankAccount tempBankAccountWithdraw = customerDAO.grabAccountwithNumber((int) accountNumberWithdraw);
			if (tempBankAccountWithdraw == null)
			{
				System.out.println("Invalid Account Number!");
				break;
			}
			BankAccount update = employeeLogin.withdraw(tempBankAccountWithdraw, amount);
			customerDAO.updateAccount(update);
			Logger newLog5 = new Logger(employeeLogin.getUsername(), "Employee Withdraw", LocalDateTime.now());
			customerDAO.createLogger(newLog5);
			break;
		case "5":
			System.out.println("Enter Account Number");
			long accountNumberDeposit = reader.nextLong();
			System.out.println("Enter Amount");
			double amountToDeposit = reader.nextDouble();
			BankAccount tempBankAccountDeposit = customerDAO.grabAccountwithNumber((int) accountNumberDeposit);
			if (tempBankAccountDeposit == null)
			{
				System.out.println("Invalid Account Number!");
				break;
			}
			BankAccount updateDeposit = employeeLogin.deposit(tempBankAccountDeposit, amountToDeposit);
			customerDAO.updateAccount(updateDeposit);
			Logger newLog6 = new Logger(employeeLogin.getUsername(), "Empoloyee Deposit", LocalDateTime.now());
			customerDAO.createLogger(newLog6);
			break;
		case "6":
			System.out.println("Enter Account Number from: ");
			long fromAccountNumber = reader.nextLong(); 
			System.out.println("Enter Amount to Transfer");
			int amountToTransfer = reader.nextInt();
			System.out.println("Enter Account Number to: ");
			long toAccountNumber = reader.nextLong();
			BankAccount tempBankAccountTransferFrom = customerDAO.grabAccountwithNumber((int) fromAccountNumber);
			BankAccount tempBankAccountTransferTo = customerDAO.grabAccountwithNumber((int) toAccountNumber);
			if (tempBankAccountTransferFrom == null || tempBankAccountTransferTo == null)
			{
				System.out.println("Invalid Accounts!");
				break;
			}
			boolean checkDeposit = tempBankAccountTransferFrom.withdrawing(amountToTransfer);
			boolean checkWithdraw = tempBankAccountTransferTo.depositing(amountToTransfer);
			if (checkDeposit && checkWithdraw)
			{
				System.out.println("Transfer Succes");
				customerDAO.updateAccount(tempBankAccountTransferFrom);
				customerDAO.updateAccount(tempBankAccountTransferTo);
			}
			else {
				System.out.println("Withdraw/Deposit Failure!");
			}
			Logger newLog10 = new Logger(employeeLogin.getUsername(), "Employee Transfer", LocalDateTime.now());
			customerDAO.createLogger(newLog10);
			break;
		case "7":
			System.out.println("Enter Account Number to Delete: ");
			long accountDelete = reader.nextLong();
			AccountList = employeeLogin.delete(AccountList, accountDelete);
			//Logger.info("Employee" + employeeLogin.getfName() + "Deleted Account");
			break;
		case"0":
			refreshDatabase(EmployeeList,CustomerList, AccountList, ApplicationList);
			//Logger.info("System Refresh");
			return false;
		}
		refreshDatabase(EmployeeList,CustomerList, AccountList, ApplicationList);
		return true;
	  }
	public static String MainOptions(ArrayList<Employee> EmployeeList, ArrayList<Customer> CustomerList) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Revature Banking Systems Main Menu");
		System.out.println("1: User Login");
		System.out.println("2: Employee Login");
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