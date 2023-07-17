package com.masai;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

public class CustomerMain {
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
			Address address = new Address("Jaipur", "Rajasthan", 302002);
			Customer customer = new Customer("ABC", 20, address);
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
			
			Customer customer = em.find(Customer.class, 1);
			
			System.out.println("Name: " + customer.getName() + " Annual Income: " + customer.getAnnualIncome());
			
			Address address = customer.getAddress();
			
			System.out.println("City: " + address.getCity() + ", State: " + address.getState() + ", Zipcode: " + address.getZipcode());
			
		}catch(IllegalStateException | IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void main(String[] args) {
		//addCustomer();
		viewCustomerDetails();
	}
}