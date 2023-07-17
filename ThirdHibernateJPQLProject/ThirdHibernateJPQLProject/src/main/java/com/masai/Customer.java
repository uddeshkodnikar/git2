package com.masai;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private double annualIncome;
	
	@Embedded
	private Address address;


	public Customer() {
		super();
	}
	
	public Customer(String name, int annualIncome, Address address) {
		this.name = name;
		this.annualIncome = annualIncome;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getAnnualIncome() {
		return annualIncome;
	}

	public Address getAddress() {
		return address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
