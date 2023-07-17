package com.masai.service;

import java.math.BigDecimal;

import com.masai.dao.BookDAO;
import com.masai.dao.BookDAOImpl;
import com.masai.entity.Book;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public class BookServiceImpl implements BookService {
	@Override
	public void addBook(Book book) throws SomethingWentWrongException {
		BookDAO bookDAO = new BookDAOImpl();
		bookDAO.addBook(book);
	}

	@Override
	public Book getBookById(int id) throws SomethingWentWrongException, NoRecordFoundException {
		BookDAO bookDAO = new BookDAOImpl();
		return bookDAO.getBookById(id);
		
	}

	@Override
	public void updateBookPrice(int id, BigDecimal price) throws SomethingWentWrongException, NoRecordFoundException {
		BookDAO bookDAO = new BookDAOImpl();
		bookDAO.updateBookPrice(id, price);

	}

	@Override
	public void deleteBookById(int id) throws SomethingWentWrongException, NoRecordFoundException {
		BookDAO bookDAO = new BookDAOImpl();
		bookDAO.deleteBookById(id);
	}
}
