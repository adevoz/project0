package BankApplicationMaven.BankApplicationMaven;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
enum JobTitle{
	NORMAL, ADMIN
}

public class Employee extends Person {
	private JobTitle jobStatus;
	public Employee(String fName, String lName, String username, String password, JobTitle jobTitle) {
		// TODO Auto-generated constructor stub
		super(fName, lName, username, password);
		this.jobStatus = jobTitle;
	}
	// Methods for Employee
	
	// Method to view Balances
	// input: accountNumber
	// output: String of balance
/*	public String viewBalances(long accountNumber, ArrayList<BankAccount> Accounts){
		
		return null;
	}*/

	public JobTitle getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(JobTitle jobStatus) {
		this.jobStatus = jobStatus;
	}

	// Method
	// input: accountNumber
	// output: Person to display
	public Person viewPersonal(long accountNumber, ArrayList<BankAccount> Accounts) {
		return null;
	}
	
	public void printOptions() {
		System.out.println("Current Options:");
		System.out.println("1: View Balance");
		System.out.println("2: View Personal");
		System.out.println("3: View Pending Application");
		if (this.jobStatus.equals((JobTitle.ADMIN))) {
			System.out.println("4: Withdraw");
			System.out.println("5: Depositing");
			System.out.println("6: Transfers");
			System.out.println("7: Canceling accounts");
		}
		System.out.println("0: Return to Main Menu");
	}
	public void viewAccountBalance(BankAccount account) {
		
		if (account == null) {
			System.out.println("Account not Found!");
			return;
		}
		System.out.println("Current Balance: " + account.getCurrentAmmount());
	}
	public void viewPersonalInfo(ArrayList<Customer> customerList, String userToFind) {
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getUsername().equals(userToFind)){
				System.out.println("First Name:" + customerList.get(i).getfName());
				System.out.println("Last Name" + customerList.get(i).getlName());
			}
		}
	}
	public BankAccount withdraw(BankAccount bankAccounts, double amount) {
		BankAccount noChange = bankAccounts;
		if (bankAccounts == null) {
			System.out.println("Account not Found!");
			return noChange;
		}
		bankAccounts.withdrawing(amount);
		System.out.println("New Balance: " + bankAccounts.getCurrentAmmount());
		return bankAccounts;
	}
	public BankAccount deposit(BankAccount bankAccountTo, double amount) {
		BankAccount noChange = bankAccountTo;
		if (bankAccountTo == null) {
			System.out.println("Account not Found!");
			return noChange;
		}
		bankAccountTo.depositing(amount);
		System.out.println("New Balance: " + bankAccountTo.getCurrentAmmount());
		return bankAccountTo;
	}
	public ArrayList<BankAccount> Transferto(ArrayList<BankAccount> accountList, long accountNumberFrom, double amount){
		long accountNumberTo;
		int accountFromIndex = -1;
		ArrayList<BankAccount> noChange = accountList;
		System.out.print("Please Enter Account Number to Transfer:");
		Scanner reader = new Scanner(System.in);
		accountNumberTo  = reader.nextLong();
		int accountToIndex = -1;
		for (int i = 0; i < accountList.size(); i++)
		{
			if (accountList.get(i).getAccountNumber() == accountNumberTo) {
				accountToIndex = i;
			}
		}
		if (accountToIndex == -1) {
			System.out.println("Account Number TO not found!");
			return noChange;
		}
		for (int i = 0; i < accountList.size(); i++)
		{
			if (accountList.get(i).getAccountNumber() == accountNumberFrom) {
				accountFromIndex = i;
			}
		}
		if (accountFromIndex == -1) {
			System.out.println("Account Number FROM not found!");
			return noChange;
		}
		boolean checkWithdraw = accountList.get(accountFromIndex).withdrawing(amount);
		boolean checkDeposit = accountList.get(accountToIndex).depositing(amount);
		if (!checkWithdraw && !checkDeposit)
		{
			System.out.println("Transfer Failed");
			return noChange;
		}
		System.out.println("New Balance: " + accountList.get(accountFromIndex).getCurrentAmmount() +
							"Account Number " + accountList.get(accountFromIndex).getAccountNumber());
		System.out.println("New Balance: " + accountList.get(accountToIndex).getCurrentAmmount() +
							"Account Number " + accountList.get(accountToIndex).getAccountNumber());
		System.out.println("Transfer Approved!");
		return accountList;
	}
	public ArrayList<BankAccount> delete(ArrayList<BankAccount> bankAccounts, long AccountNumber)
	{
		int accountIndex = -1;
		ArrayList<BankAccount> noChange = bankAccounts;
		for (int i = 0; i < bankAccounts.size(); i++) {
			if (bankAccounts.get(i).getAccountNumber() == AccountNumber) {
				accountIndex = i;
			}
		}
		if (accountIndex < 0) {
			System.out.println("Account not Found!");
			return noChange;
		}
		System.out.println("Deleting Account all funds will be lost!");
		bankAccounts.remove(accountIndex);
		return bankAccounts;
	}
}

