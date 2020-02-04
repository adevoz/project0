package BankApplicationMaven.BankApplicationMaven;

import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends Person{
	
	public Customer(String fName, String lName, String username, String password) {
		super(fName, lName, username, password);
		// TODO Auto-generated constructor stub
	}
	// Method to update balance
	// input: long amount
	// output: 
	public boolean withdraw(double amount, long accountNumber){
		if (amount <= 0)
			return false;
		return false;
	}
	// Method to update balance
	// input: long amount
	// output: new amount
	public boolean deposit(double amount, long accountNumber){
		if (amount < 0)
			return false;
					
		return false;
	}
	// Method to update balance
	// input: long amount long fromAccount long toAccount
	// output: if processed
	public ArrayList<BankAccount> Transferto(ArrayList<BankAccount> accountList, int availableAccount, double amount){
		long accountNumberTo;
		ArrayList<BankAccount> noChange = accountList;
		System.out.print("Please Enter Account Number to Transfer:");
		Scanner reader = new Scanner(System.in);
		accountNumberTo  = reader.nextLong();
		int account = -1;
		for (int i = 0; i < accountList.size(); i++)
		{
			if (accountList.get(i).getAccountNumber() == accountNumberTo) {
				account = i;
			}
		}
		if (account == -1) {
			System.out.println("Account Number not found!");
			return noChange;
		}
		boolean checkTransfer = accountList.get(account).depositing(amount);
		if (!checkTransfer)
		{
			System.out.println("Transfer Failed");
			return noChange;
		}
		System.out.println("Transfer Approved!");
		accountList.get(availableAccount).withdrawing(amount);
		System.out.println("New Balance From Account:  " + accountList.get(availableAccount).currentAmount);
		System.out.println("New Balance To Account:  " + accountList.get(account).currentAmount);
		return accountList;
	}
	
	// Method to update balance
	// input: long amount
	// output: if processed
	public boolean createApplication() {
		return false;
	}
}
