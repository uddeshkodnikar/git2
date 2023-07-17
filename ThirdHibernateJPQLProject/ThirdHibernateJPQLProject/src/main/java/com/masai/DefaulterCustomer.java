package com.masai;


import java.util.Set;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OrderBy;

@Entity
public class DefaulterCustomer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private double annualIncome;
	
	@Embedded
	@ElementCollection(fetch = FetchType.EAGER)
	//@ElementCollection
	@OrderBy("state DESC, city ASC, zipcode ASC")
	@JoinTable(name = "defaulter_address", joinColumns = @JoinColumn(name = "defaulter_customer_id"))
	private Set<Address> addressSet;

	public DefaulterCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DefaulterCustomer(String name, double annualIncome, Set<Address> addressSet) {
		super();
		this.name = name;
		this.annualIncome = annualIncome;
		this.addressSet = addressSet;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}

	public Set<Address> getAddressSet() {
		return addressSet;
	}

	public void setAddressSet(Set<Address> addressSet) {
		this.addressSet = addressSet;
	}
	
	
}

