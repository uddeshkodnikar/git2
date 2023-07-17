package com.masai;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

/**
 * Hello world!
 *
 */
public class App 
{
	
	static EntityManagerFactory emf;
	
	static {
		emf = Persistence.createEntityManagerFactory("jpqlConnect");
	}
	
	static void getEmployeeNamesInUpperCase() {
		//Question: Print All Employees Names in UPPER CASE in descending Order of Names
		
		try(EntityManager em = emf.createEntityManager()) {
			
			//Native SQL: SELECT UPPER(emp_name) FROM employee;
			//JPQL: SELECT UPPER(e.name) FROM Employee e;
	
			String upperQuery = "SELECT UPPER(e.name) FROM Employee e ORDER BY e.name DESC";
	        
			Query query = em.createQuery(upperQuery);
			
			List<String> empsList = query.getResultList();
			
			for(String str : empsList) {
				System.out.print(str+" ");
			}
			System.out.println();
			
		} catch (IllegalStateException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		
   }
	
	static void printMaximumSalary() {
		//Question: Print Maximum Salary Employee Details
		
           try(EntityManager em = emf.createEntityManager()) {
			
			//Native SQL: SELECT MAX(salary) FROM employee;
			//JPQL: SELECT MAX(salary) FROM Employee e;
	
			String maxSalaryQuery = "SELECT MAX(e.salary) FROM Employee e";	        
			Query query = em.createQuery(maxSalaryQuery);
			
			int maxSalary = (int)query.getSingleResult();
			
			System.out.println("Max Salary is: "+maxSalary);
			
		} catch (IllegalStateException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		
		
	}
	
	static void printEmployeeDetailsForSalaryRange(Scanner sc) {
		//Question: Print Employee details for given salary range.
		
		System.out.print("Enter Employee Start Salary ");
		int start = Integer.parseInt(sc.nextLine());
		
		System.out.print("Enter Employee End Salary ");
		int end = Integer.parseInt(sc.nextLine());
		
		//Native SQL: SELECT * FROM employee WHERE salary BETWEEN start AND end;
		//JPQL: SELECT e FROM Employee e WHERE e.salary BETWEEN :start AND :end;
		
		try(EntityManager em = emf.createEntityManager();) {
			
			String salaryRangeQuery = "SELECT e FROM Employee e WHERE e.salary BETWEEN :start AND :end";
			
			Query query = em.createQuery(salaryRangeQuery);
			
			query.setParameter("start", start);
			query.setParameter("end", end);
			
			List<Employee> empList = query.getResultList();
			
			if(empList.size()>0) {
				for(Employee emp : empList) {
					System.out.println(emp);
				}
			}else {
				System.out.println("There is no such Employeee.");
			}
			
			
			
		} catch (IllegalStateException | IllegalArgumentException e) {
			 System.out.println(e.getMessage());
		}
		
		
	}
	
	static void printEmployeeCountDesignationWise() {
		//Question: Print Number of Employee Designation Wise
		
		//Native SQL: SELECT designation, count(emp_name) from employee group by designation;
		// JPQL: SELECT e.designation, count(e.name) FROM Employee e GROUP BY e.designation;
		
		
			try(EntityManager em = emf.createEntityManager();) {
			
			String employeeCountByDesignationQuery = "SELECT e.designation, COUNT(e.name) FROM Employee e GROUP BY e.designation";
			
			Query query = em.createQuery(employeeCountByDesignationQuery);
			
			List<Object[]> objList = query.getResultList();
			
			
			for(Object[] obj : objList) {
				System.out.println("[Designation]: "+ obj[0]+" [Count]:"+obj[1]);
			
				}
			
		} catch (IllegalStateException | IllegalArgumentException e) {
			 System.out.println(e.getMessage());
		}
		
		
	}
	
	
	static void promoteEmployee() {
		//Question: Promote Employee On the Basis of Criteria
		//Employee Salary = 35000 and designation = SR. Clear where Employee id 1207
		
		EntityManager em = null;
		EntityTransaction et = null;
		
		try {
			
			//Native SQL: UPDATE employee SET designation ="Sr. Clear", salary=35000 WHERE id = 1207;
			//JPQL: UPDATE Employee SET designation :designation, salary :salary WHERE id = :id;
			
			em = emf.createEntityManager();
			
			String promoteEmployeeQuery ="UPDATE Employee SET designation = :designation, salary = :salary WHERE id = :id";
			Query query = em.createQuery(promoteEmployeeQuery);
			query.setParameter("designation", "Sr. Clerk");
			query.setParameter("salary", 35000);
			query.setParameter("id", 1207);
		
			et = em.getTransaction();
			et.begin();
			
			query.executeUpdate();
			
			et.commit();
			
			System.out.println("Employee promoted successfully.");
			
		} catch (IllegalStateException | IllegalArgumentException | PersistenceException e) {
			et.rollback();
			System.out.println(e.getMessage());
		}finally {
			em.close();
		}
		
		
		
	}
	
	static void deleteEmployeeById(Scanner sc) {
		//Question: Delete Employee By Employee Id
		
		System.out.print("Enter Employee Id ");
		int id = Integer.parseInt(sc.nextLine());
		
		EntityManager em = null;
		EntityTransaction et = null;
		
		try {
			
			//Native SQL: DELETE FROM employee WHERE id = 12;
			//JPQL: DELETE FROM Employee e WHERE e.id =:id;
			
			em = emf.createEntityManager();
			
			String deleteQuery ="DELETE FROM Employee e WHERE e.id =:id";
			Query query = em.createQuery(deleteQuery);
			query.setParameter("id", id);
		
			et = em.getTransaction();
			et.begin();
			
			query.executeUpdate();
			
			et.commit();
			System.out.println("Employee deleted successfully.");
			
		} catch (IllegalStateException | IllegalArgumentException | PersistenceException e) {
			et.rollback();
			System.out.println(e.getMessage());
		}finally {
			em.close();
		}
		
	}
	
	static void printEmployeeByDesignation() {
		
		//native query: SQL query that is being written with the help of database table.
		//named query : jpql query that is being writthen with the help of Entity class.
		
			try(EntityManager em = emf.createEntityManager();) {
		
			Query query = em.createNamedQuery("findEmployeeByDesignation", Employee.class);
			
			List<String> designationList = new ArrayList<>();
			
			designationList.add("Technical Writer");
			designationList.add("Proof Reader");
			
			query.setParameter("designation", designationList);
			
			
			List<Employee> empList = query.getResultList();
			
			if(empList.size()>0) {
				for(Employee emp : empList) {
					System.out.println(emp);
				}
			}else {
				System.out.println("There is no such Employeee.");
			}
			
			
			
		} catch (IllegalStateException | IllegalArgumentException e) {
			 System.out.println(e.getMessage());
		}
		
	}
	
	
	static void printEmployeeDetailsForNamePattern(Scanner sc) {
	
		System.out.print("Enter Name Any character to Find Employee ");
		String ch = sc.nextLine();
		
		try(EntityManager em = emf.createEntityManager();) {
			
			Query query = em.createNamedQuery("FindEmployeeByNamePattern", Employee.class);
		
			query.setParameter("name", "%"+ch+"%");
			
			
			List<Employee> empList = query.getResultList();
			
			if(empList.size()>0) {
				for(Employee emp : empList) {
					System.out.println(emp);
				}
			}else {
				System.out.println("There is no such Employeee.");
			}
			
			
			
		} catch (IllegalStateException | IllegalArgumentException e) {
			 System.out.println(e.getMessage());
		}
		
	}
	
	static void printAllEmployeeDetails() {
		
			try(EntityManager em = emf.createEntityManager();) {
			
			Query query = em.createNamedQuery("FindAllEmployee");
		
			List<Object[]> empList = (List<Object[]>)query.getResultList();
			
			for(Object[] ob: empList) {
				System.out.println(ob[0]+" ,  "+ ob[1]+" ,  "+ob[2]+" ,  "+ob[3]+" ,  "+ob[4]);
			}
			
		} catch (IllegalStateException | IllegalArgumentException e) {
			 System.out.println(e.getMessage());
		}
		
		
	}
	
	

    public static void main( String[] args )
    {
    	Scanner sc = new Scanner(System.in);
		
		int choice =0;
		
		do {
			System.out.println("=====================================");
			System.out.println("1. Get Employee Names in Upper Case");
			System.out.println("2. Print Employee Maximum Salary");
			System.out.println("3. Print Employee Details For Salary Range");
			System.out.println("4. Print Employee Count Designation Wise ");
			System.out.println("5. Promote Employee ");
			System.out.println("6. Delete Employee By Id ");
			System.out.println("7. Print Employee By Designation ");
			System.out.println("8. Print Employee Details for Name Pattern ");
			System.out.println("9. Print All Employee Details ");
			System.out.println("0. Exit");
			System.out.println("=====================================");
			System.out.print("Enter your choice: ");
			choice = Integer.parseInt(sc.nextLine());
			
			switch(choice){
			case 1: 
				//Question 1: Print All Employees Names in UPPER CASE
				getEmployeeNamesInUpperCase();
				break;
			case 2: 
				printMaximumSalary();
				break;
			case 3:
			    printEmployeeDetailsForSalaryRange(sc);
				break;
			case 4:
			    printEmployeeCountDesignationWise();
				break;
			case 5:
			    promoteEmployee();
				break;
			case 6:
			    deleteEmployeeById(sc);
				break;
			case 7:
			    printEmployeeByDesignation();
				break;
			case 8:
			    printEmployeeDetailsForNamePattern(sc);
				break;
			case 9:
			    printAllEmployeeDetails();
				break;
			case 0:
				 System.out.println("Thank  you for using");
				 break;
			default:
				System.out.println("Invalid Input");
				
			}
			
		}while(choice!=0);
		
		sc.close();
		
    }
}
