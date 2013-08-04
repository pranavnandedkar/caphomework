package com.capgemini.homework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.capgemini.homework.entities.*;

@Component 
public class JdbcDao {

	@Autowired
	private DataSource dataSource;

	public Borrower getBorrowerForBookId(String id){
		Borrower borrower = null;
		Connection con;		
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("Select * from checkout where book_id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				try {
					borrower = new Borrower();
					PreparedStatement ps2 = con.prepareStatement("Select * from info where idstudent = ?");
					ps2.setString(1, rs.getString("borrower_id"));
					ResultSet rs2 = ps2.executeQuery();
					if(rs2.next()){
						borrower.setId(rs2.getString("idstudent"));
						borrower.setAddress(rs2.getString("Address"));
						borrower.setName(rs2.getString("name"));
						borrower.setSurname(rs2.getString("surname"));
						borrower.setPhone(rs2.getString("phone"));
					}else{
						System.out.println("No Entry found");
					}
					rs2.close();
					ps2.close();
				} catch (SQLException e) {
					System.out.println("SQL Exception:"+e);
				}
				System.out.println("ans>>>"+rs.getString("borrower_id"));
			}
			System.out.println("got the borrower");

			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			System.out.println(">>"+e+"<<");	
		}
		return borrower;
	}
	
	
	public void checkOut(String id){
		Connection con;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("Update books set available = 'NO' where idbooks = ?");
			ps.setString(1, id);
			ps.executeUpdate();
			ps.close();
			con.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}

	public void addBook(String[] details){
		
		Connection con;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO books values(?,?,?,?,?,?)");
			ps.setString(1, details[0]);
			ps.setString(2, details[1]);
			ps.setString(3, details[2]);
			ps.setString(4, details[3]);
			ps.setString(5, details[4]);
			ps.setString(6, details[5]);
			
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		
	}
	public Book[] getBooks(){
		Connection con;	
		Book books[] = new Book[40];
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("Select * from books");
			ResultSet rs = ps.executeQuery();
			int i = 0;
			while(rs.next()){
				System.out.println(">>>");
				books[i] = new Book();
				books[i].setId(rs.getString("idbooks"));
				books[i].setAuthor(rs.getString("author"));
				books[i].setEdition(rs.getString("edition"));
				books[i].setIsbn(rs.getString("isbn"));
				books[i].setPrice(rs.getString("price"));
				books[i].setAvailable(rs.getString("available"));		
			    i++;
			    System.out.println(">>>");
			}
			rs.close();
			ps.close();
			con.close();		
		}catch(Exception e){
			System.out.println(e);
		}
		return books;
	}
	
	public Borrower getInfo(String id){
		Connection con;	
		Borrower bor = null;
		
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("Select * from info where idstudent = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				try {
					bor = new Borrower();
					bor.setAddress(rs.getString("Address"));
					bor.setName(rs.getString("name"));
					bor.setSurname(rs.getString("surname"));
					bor.setPhone(rs.getString("phone"));
					bor.setId(rs.getString("idstudent"));
					
				}catch(Exception ex){
					System.out.println(ex);
					}
				}else{
					System.out.println("No Entry found");
				}
			rs.close();
			ps.close();
			con.close();

		}catch(Exception e){
			System.out.println(e);
		}
		return bor;
	}
	
	public Book getBookFromBookId(String id){
		
		Connection con;	
		Book book = null;
		
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("Select * from books where idbooks = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				try {
					book = new Book();
					book.setAuthor(rs.getString("author"));
					book.setEdition("edition");
					book.setId(id);
					book.setIsbn(rs.getString("isbn"));
					book.setPrice(rs.getString("price"));
				}catch(Exception ex){
					System.out.println(ex);
					}
				}else{
					System.out.println("No Entry found");
				}
			rs.close();
			ps.close();
			con.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return book;
	}
	@Lazy
	public void destruct(){
		
		System.out.println("destroying");
	}
	/**
	 * @return the dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
