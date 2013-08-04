package com.capgemini.homework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.capgemini.homework.dao.JdbcDao;
import com.capgemini.homework.entities.Book;
import com.capgemini.homework.entities.Borrower;

public class DaoImpl {

	
	
	public static void main(String[] args) {
	ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	JdbcDao dao = ctx.getBean("jdbcDao",JdbcDao.class);

	Borrower b1 = dao.getBorrowerForBookId("1");
	System.out.println(b1.getName());
	
	Book book = dao.getBookFromBookId("111");
	System.out.println(book.getAuthor());
	}
}
