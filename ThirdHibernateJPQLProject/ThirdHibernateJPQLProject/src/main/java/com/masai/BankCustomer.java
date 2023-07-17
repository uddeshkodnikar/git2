package com.masai;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BankCustomer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private double annualIncome;
	
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "city", column = @Column(name = "home_city")),
			@AttributeOverride(name = "state", column = @Column(name = "home_state")),
			@AttributeOverride(name = "zipcode", column = @Column(name = "home_zipcode"))		
		}
	)
	private Address homeAddress;
	
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "city", column = @Column(name = "office_city")),
			@AttributeOverride(name = "state", column = @Column(name = "office_state")),
			@AttributeOverride(name = "zipcode", column = @Column(name = "office_zipcode"))		
		}
	)
	private Address officeAddress;

	public BankCustomer() {
		super();
	}

	public BankCustomer(String name, double annualIncome, Address homeAddress, Address officeAddress) {
		super();
		this.name = name;
		this.annualIncome = annualIncome;
		this.homeAddress = homeAddress;
		this.officeAddress = officeAddress;
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

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Address getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}
}

