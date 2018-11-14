package BankApplicationMaven.BankApplicationMaven;

import java.io.Serializable;
import java.util.ArrayList;

enum Status
{ 
    PENDING, APPROVED, DECLINED; 
}

enum Applicationtype
{
	SINGLE, JOINT
}

public class Application implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8726380544769476115L;
	private long applicationNumber;
	private static  int applicationCount = 0;
	private Status applicationStatus;
	private Applicationtype applicationType;
	private ArrayList<String> userList = new ArrayList<String>();
	
	public Application(Applicationtype applicationType, ArrayList<String> users) {
		super();
		this.applicationNumber = Application.applicationCount;
		applicationCount ++;
		this.applicationStatus = Status.PENDING;
		this.applicationType = applicationType;
		this.userList.addAll(users);
	}
	
	
	public Applicationtype getApplicationType() {
		return applicationType;
	}


	public void setApplicationType(Applicationtype applicationType) {
		this.applicationType = applicationType;
	}


	public ArrayList<String> getUserList() {
		return userList;
	}


	public void setUserList(ArrayList<String> userList) {
		this.userList = userList;
	}


	// getters / setters
	public long getApplicationNumber() {
		return applicationNumber;
	}
	public void setApplicationNumber(long applicationNumber) {
		this.applicationNumber = applicationNumber;
	}
	public Status getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(Status applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public void printInfo() {
		System.out.println(this.applicationNumber);
		System.out.println(this.applicationStatus);
		System.out.println(this.applicationType);
		System.out.println(this.userList);
	}
}
