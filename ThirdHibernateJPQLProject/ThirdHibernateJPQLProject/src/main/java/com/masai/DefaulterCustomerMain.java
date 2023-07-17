package com.masai;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

public class DefaulterCustomerMain {
	static EntityManagerFactory emf;
	
	static {
		emf = Persistence.createEntityManagerFactory("hasAConnect");
	}
	
	static void addDefaulterCustomer() {
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = emf.createEntityManager();
			//Create an object of Address
			Address officeAddress = new Address("Jaipur", "Rajasthan", 302002);
			Address homeAddress = new Address("Sikar", "Rajasthan", 303002);
			
			DefaulterCustomer customer = new DefaulterCustomer("Anu", 15, new HashSet<>());
			
			customer.getAddressSet().add(officeAddress);
			customer.getAddressSet().add(homeAddress);
			
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
			
			DefaulterCustomer customer = em.find(DefaulterCustomer.class, 1);
			
			System.out.println("Name: " + customer.getName() + " Annual Income: " + customer.getAnnualIncome());
			
			Set<Address> addressSet = customer.getAddressSet();
			
			for(Address address: addressSet) {
				
			System.out.println("City: " + address.getCity() + ", State: " + address.getState() + ", Zipcode: " + address.getZipcode());
			
			}
		}catch(IllegalStateException | IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void main(String[] args) {
		//addDefaulterCustomer();
		viewCustomerDetails();
	}
}
