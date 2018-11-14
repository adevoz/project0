package BankApplicationMaven.BankApplicationMaven;

import java.io.Serializable;
import java.util.ArrayList;

enum AccountType
{
	SINGLE, JOINT
}

public class BankAccount implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3301978597013220164L;
	double currentAmount;
	AccountType accountType;
	ArrayList<String> allowedUsers;
	static long tempAccountNumber = 0;
	long accountNumber;
	public BankAccount(AccountType accountType, ArrayList<String> joinList) {
		super();
		this.accountType = accountType;
		this.allowedUsers = new ArrayList<String>();
		this.allowedUsers.addAll(joinList);
		BankAccount.tempAccountNumber ++;
		this.accountNumber = tempAccountNumber;
	}
	
//	public BankAccount(AccountType accountType, String user) {
//		super();
//		this.currentAmount = 0;
//		this.accountType = accountType;
//		this.allowedUsers.add(user);
//		BankAccount.tempAccountNumber ++;
//		this.accountNumber = tempAccountNumber;
//		tempAccountNumber++;
//	}
	
	public double getCurrentAmmount() {
		return currentAmount;
	}

	public void setCurrentAmmount(float currentAmount) {
		this.currentAmount = currentAmount;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public ArrayList<String> getAllowedUsers() {
		return allowedUsers;
	}

	public void setAllowedUsers(ArrayList<String> allowedUsers) {
		this.allowedUsers = allowedUsers;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public boolean withdrawing(double amount) {
		if (amount < 0)
			return false;
		else if ((this.currentAmount - amount) < 0)
			return false;
		else 
			this.currentAmount = this.currentAmount - amount;
		return true;
	}
	public boolean depositing(double amount) {
		if (amount < 0)
			return false;
		else
			this.currentAmount = this.currentAmount + amount;
		return true;
	}
}
