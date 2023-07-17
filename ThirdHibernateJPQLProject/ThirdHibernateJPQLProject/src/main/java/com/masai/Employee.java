package com.masai;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQueries({
	    @NamedQuery(name="findEmployeeByDesignation", query="SELECT e From Employee e WHERE e.designation IN(:designation)"),
	    @NamedQuery(name="FindEmployeeByNamePattern", query="SELECT e FROM Employee e WHERE e.name LIKE :name")
})
@NamedNativeQuery(name="FindAllEmployee", query ="SELECT * FROM employee")
public class Employee {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="emp_name",length = 50, nullable = false)
	private String name;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	private int salary;
	private String designation;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String name, Gender gender, int salary, String designation) {
		super();
		this.name = name;
		this.gender = gender;
		this.salary = salary;
		this.designation = designation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", gender=" + gender + ", salary=" + salary + ", designation="
				+ designation + "]";
	}
     
	
}
