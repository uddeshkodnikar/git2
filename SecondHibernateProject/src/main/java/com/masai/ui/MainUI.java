package com.masai.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import com.masai.entity.Book;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;
import com.masai.service.BookService;
import com.masai.service.BookServiceImpl;


// UI(Presentation Layer<----------->Service Layer(Business Layer)<-----------> Persistence Layer(Dao Layer)
public class MainUI {
	static void addBook(Scanner sc) {
		System.out.print("Enter title ");
		String title = sc.nextLine();
		System.out.print("Enter author ");
		String author = sc.nextLine();
		System.out.print("Enter price ");
		BigDecimal price = new BigDecimal(sc.nextDouble());
		sc.nextLine();
		System.out.print("Enter publishing date ");
		LocalDate publishDate = LocalDate.parse(sc.next());
		
		//Create of object of BookService
		BookService bookService = new BookServiceImpl();
		try {
			//A new Entity object is created here
			Book book = new Book(title, author, price, publishDate);
			bookService.addBook(book);
			System.out.println("Book Added Successfully");
			
		}catch(SomethingWentWrongException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	static void findBookById(Scanner sc) {
		System.out.print("Enter id of book");
		int id = sc.nextInt();
		//Create of object of BookService
		BookService bookService = new BookServiceImpl();
		try {
			Book book = bookService.getBookById(id);
			System.out.println(book);
		}catch(SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	static void updateBookPriceById(Scanner sc) {
		System.out.print("Enter id of book");
		int id = sc.nextInt();
		System.out.print("Enter price ");
		BigDecimal price = new BigDecimal(sc.nextDouble());
		//Create of object of BookService
		BookService bookService = new BookServiceImpl();
		try {
			bookService.updateBookPrice(id, price);
			System.out.println("Book price updated successfully");
		}catch(SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	static void deleteBookById(Scanner sc) {
		System.out.print("Enter id of book");
		int id = sc.nextInt();
		//Create of object of BookService
		BookService bookService = new BookServiceImpl();
		try {
			bookService.deleteBookById(id);
			System.out.println("Book has deleted successfully");
		}catch(SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do {
			System.out.println("1. Add Book");
			System.out.println("2. View Book By id");
			System.out.println("3. update Book price By id");
			System.out.println("4. Delete Book By id");
			System.out.println("0. Exit");
			System.out.print("Enter Selection ");
			choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
				case 1:
					addBook(sc);
					break;
				case 2:
					findBookById(sc);
					break;
				case 3:
					updateBookPriceById(sc);
					break;
				case 4:
					deleteBookById(sc);
					break;
				case 0:
					System.out.println("Thanks for using our services");
					break;
				default:
					System.out.println("Invalid selection, try again");
			}
		}while(choice != 0);
		sc.close();
	}
}
