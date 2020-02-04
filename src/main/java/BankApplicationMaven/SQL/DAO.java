package BankApplicationMaven.SQL;


import java.util.List;

import BankApplicationMaven.BankApplicationMaven.Application;
import BankApplicationMaven.BankApplicationMaven.Customer;
import BankApplicationMaven.BankApplicationMaven.Person;

//DAO is used to handle all of our CRUD operations with the DB
public interface DAO {

	void updateCustomer(Customer fc);

	void deleteCustomer(int id);

	void createCustomer(Customer fc);

	List<Application> readAllCustomers();

	Person readCustomer(int id);

	List<Application> readApplications();

}
