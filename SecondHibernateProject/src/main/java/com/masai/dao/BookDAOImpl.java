package com.masai.dao;

import java.math.BigDecimal;

import com.masai.entity.Book;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;
import com.masai.utility.EMUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;

public class BookDAOImpl implements BookDAO {

	@Override
	public void addBook(Book book) throws SomethingWentWrongException {
		EntityManager em  = null;
		EntityTransaction et = null;
		
		try {
			
			em = EMUtils.getEntityManager();
			
			et = em.getTransaction();
			et.begin();		
			em.persist(book);
			et.commit();

		} catch (PersistenceException e) {
			et.rollback();
			throw new SomethingWentWrongException("Unable to add book, try again later");
			
		}finally {
			em.close();
		}
		
		
	}

	@Override
	public Book getBookById(int id) throws SomethingWentWrongException, NoRecordFoundException {
		
		Book book = null;
		
		EntityManager em  = null;
		EntityTransaction et = null;
		
		try {
			//Life-Cycle phase: Transient State
			em = EMUtils.getEntityManager();
			book = em.find(Book.class, id); // Geeting Book Entity object From Database table 
			//Life-Cycle phase: managed state
			if(book==null) {
				throw new NoRecordFoundException("Invalid Book Id");
			}
			
			
		} catch (PersistenceException e) {
			throw new SomethingWentWrongException("Unable to get book, try again later");
			
		}finally {
			em.close();
		}
		//Life-Cycle phase: detached
		return book;
	}

	@Override
	public void updateBookPrice(int id, BigDecimal price) throws SomethingWentWrongException, NoRecordFoundException {
		//Life-Cycle phase: Transient state
		Book book = getBookById(id);
		//Life-Cycle phase: Detached state
		
		EntityManager em  = null;
		EntityTransaction et = null;
		
		try {
			
			//Life-Cycle phase: Transient state
			em = EMUtils.getEntityManager();
			et = em.getTransaction();
			
			et.begin();
			
			book.setPrice(price);
			em.merge(book);
			//Life-Cycle phase: Merged state
            
			et.commit();
			
		} catch (PersistenceException e) {
			et.rollback();
			throw new SomethingWentWrongException("Unable to update book price, try again later");
			
		}finally {
			em.close();
			//Life-Cycle phase: Detached state
		}
		
	}

	@Override
	public void deleteBookById(int id) throws SomethingWentWrongException, NoRecordFoundException {
		
		//Life-Cycle phase: Transient state
		Book book = getBookById(id);
		//Life-Cycle phase: Detached state
		
		EntityManager em  = null;
		EntityTransaction et = null;
		
		try {
			
			em = EMUtils.getEntityManager();
			
			et = em.getTransaction();
			
			et.begin();
			
			book = em.merge(book); //Life-Cycle phase: Marged state
			em.remove(book); // //Life-Cycle phase: removed state
			
			et.commit();
			
			
		} catch (PersistenceException e) {
			et.rollback();
			throw new SomethingWentWrongException("Unable to add book, try again later");
			
		}finally {
			em.close();
		}
		
	}

}
