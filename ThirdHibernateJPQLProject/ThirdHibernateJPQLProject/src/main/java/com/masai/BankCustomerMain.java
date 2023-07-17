package com.masai;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

public class BankCustomerMain {
	static EntityManagerFactory emf;
	
	static {
		emf = Persistence.createEntityManagerFactory("hasAConnect");
	}
	
	static void addCustomer() {
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = emf.createEntityManager();
			//Create an object of Address
			Address officeAddress = new Address("Jaipur", "Rajasthan", 302002);
			Address homeAddress = new Address("Sikar", "Rajasthan", 303002);
			
			BankCustomer customer = new BankCustomer("Satya", 8, homeAddress, officeAddress);
			et = em.getTransaction();
			et.begin();
			em.persist(customer);
			et.commit();
		}catch(IllegalStateException | PersistenceException ex) {
			et.rollback();
			System.out.println(ex.getMessage());
		}finally {
			em.close();			
		}
	}
	
	static void viewCustomerDetails() {
		try(EntityManager em = emf.createEntityManager()){
			
			BankCustomer customer = em.find(BankCustomer.class, 1);
			
			System.out.println("Name: " + customer.getName() + " Annual Income: " + customer.getAnnualIncome());
			
			Address homeAddress = customer.getHomeAddress();
			
			System.out.println("City: " + homeAddress.getCity() + ", State: " + homeAddress.getState() + ", Zipcode: " + homeAddress.getZipcode());
			
			Address officeAddress = customer.getOfficeAddress();
			
			System.out.println("City: " + officeAddress.getCity() + ", State: " + officeAddress.getState() + ", Zipcode: " + officeAddress.getZipcode());
			
		}catch(IllegalStateException | IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void main(String[] args) {
		//addCustomer();
		viewCustomerDetails();
	}
}

