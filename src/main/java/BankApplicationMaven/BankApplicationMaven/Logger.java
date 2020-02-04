package BankApplicationMaven.BankApplicationMaven;

import java.time.temporal.Temporal;

public class Logger {
	String user;
	String transaction;
	Temporal timeOf;
	
	public Logger(String user, String transaction, Temporal timeOf) {
		super();
		this.user = user;
		this.transaction = transaction;
		this.timeOf = timeOf;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTransaction() {
		return transaction;
	}
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	public Temporal getTimeOf() {
		return timeOf;
	}
	public void setTimeOf(Temporal timeOf) {
		this.timeOf = timeOf;
	}
}
