package com.masai.dao;

import java.math.BigDecimal;

import com.masai.entity.Book;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public interface BookDAO {
	public void addBook(Book book) throws SomethingWentWrongException;
	public Book getBookById(int id) throws SomethingWentWrongException, NoRecordFoundException;
	public void updateBookPrice(int id, BigDecimal price) throws SomethingWentWrongException, NoRecordFoundException;
	public void deleteBookById(int id) throws SomethingWentWrongException, NoRecordFoundException;
}
