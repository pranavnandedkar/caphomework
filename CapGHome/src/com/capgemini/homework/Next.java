package com.capgemini.homework;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.capgemini.homework.dao.JdbcDao;
import com.capgemini.homework.entities.Book;
import com.capgemini.homework.entities.Borrower;
import com.capgemini.homework.session.CustomSession;

import java.awt.Component;

import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Next extends JFrame {
	ApplicationContext ctx = null;
	JdbcDao dao = null;
	
	private JPanel contentPane;
	private JLabel lblName;
	private JLabel lblId;
	private JLabel lblSurname;
	private JLabel lblAddress;
	private JLabel lblIdfetch;
	private JLabel lblPhone;
	private JLabel lblNamefetch;
	private JLabel lblSurnamefetch;
	private JLabel lblAddressfetch;
	private JLabel lblPhonefetch;

	private Borrower borrower = null;
	private Book book = null;
	private JList list;
	DefaultListModel model;
	private JLabel lblPleaseEnterThe;
	private JTextField textCheckOut;
	private JTabbedPane tabbedPane_3;
	private JTextField txtId;
	private JTextField txtAuthor;
	private JTextField txtIsbn;
	private JTextField txtPrice;
	private JTextField txtEdition;
	   /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Next frame = new Next();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Borrower getBorrower(String id){
	
		return borrower;
	}
	
	Book getBook(int id){
		
		return book;
	}
	
	
	public void setInfoFrame(){
		
		Borrower b1 = dao.getInfo(CustomSession.id);
		lblIdfetch.setText(b1.getId());
		lblAddressfetch.setText(b1.getAddress());
		lblNamefetch.setText(b1.getName());
		lblPhonefetch.setText(b1.getPhone());
		lblSurnamefetch.setText(b1.getSurname());
	}
	
	public void showBooks(){
		model.removeAllElements();
		model.addElement("id-author-isbn-price-available");
		Book[] books = dao.getBooks();
		int i = 0;
		while(books[i]!=null){
			System.out.println(books[i].getId()+books[i].getAuthor());
			model.addElement(" " + books[i].getId()+" - "+books[i].getAuthor()+ " - " + books[i].getIsbn() + " - " + books[i].getPrice() + " - " + books[i].getAvailable() );
			i++;
			System.out.println(i);
		}
		
	}
	
	public void addBook(String[] details){
		dao.addBook(details);
	}
	
	public void checkout(String id){
		dao.checkOut(id);
		
	}
	
	/**
	 * Create the frame.
	 */
	public Next() {
		
		ctx = new ClassPathXmlApplicationContext("spring.xml");
		dao = ctx.getBean("jdbcDao",JdbcDao.class);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JTabbedPane tabbedPane_info = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("INFO", null, tabbedPane_info, null);
		
		JPanel panel = new JPanel();
		tabbedPane_info.addTab("", null, panel, null);
		
		lblName = new JLabel("Name");
		
		lblId = new JLabel("ID");
		
		lblSurname = new JLabel("Surname");
		
		lblAddress = new JLabel("Address");
		
		lblPhone = new JLabel("Phone");
		
		lblIdfetch = new JLabel("Id_fetch");
		
		lblNamefetch = new JLabel("Name_fetch");
		
		lblSurnamefetch = new JLabel("Surname_fetch");
		
		lblAddressfetch = new JLabel("Address_Fetch");
		
		lblPhonefetch = new JLabel("phone_fetch");
		
		
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(71)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPhone)
						.addComponent(lblAddress)
						.addComponent(lblSurname)
						.addComponent(lblId)
						.addComponent(lblName))
					.addGap(100)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIdfetch)
						.addComponent(lblNamefetch)
						.addComponent(lblSurnamefetch)
						.addComponent(lblAddressfetch)
						.addComponent(lblPhonefetch))
					.addContainerGap(468, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(71)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(lblIdfetch))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(lblNamefetch))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSurname)
						.addComponent(lblSurnamefetch))
					.addGap(29)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddress)
						.addComponent(lblAddressfetch))
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhone)
						.addComponent(lblPhonefetch))
					.addContainerGap(141, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JTabbedPane tabbedPane_AvlBooks = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Books ", null, tabbedPane_AvlBooks, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane_AvlBooks.addTab("Available", null, panel_1, null);
		model = new DefaultListModel();
		list = new JList(model);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			showBooks();
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 604, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(list, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(39)
							.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_AvlBooks.addTab("check out", null, tabbedPane_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane_1.addTab("", null, panel_2, null);
		
		lblPleaseEnterThe = new JLabel("PLEASE ENTER THE BOOK ID TO CHECK OUT");
		
		textCheckOut = new JTextField();
		textCheckOut.setColumns(10);
		
		JButton btnCheckOut = new JButton("Check Out");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String id = textCheckOut.getText();
			checkout(id);
				
		  }
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(42)
							.addComponent(lblPleaseEnterThe))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(191)
							.addComponent(btnCheckOut, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(222)
							.addComponent(textCheckOut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(405, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPleaseEnterThe)
					.addGap(18)
					.addComponent(textCheckOut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(53)
					.addComponent(btnCheckOut, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(190, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_AvlBooks.addTab("Add Book", null, tabbedPane_3, null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane_3.addTab("", null, panel_4, null);
		
		JLabel lblId_1 = new JLabel("id");
		
		JLabel lblAuthor = new JLabel("author");
		
		JLabel lblIsbn = new JLabel("isbn");
		
		JLabel lblPrice = new JLabel("price");
		
		JLabel lblEdition = new JLabel("edition");
		
		txtId = new JTextField();
		txtId.setText("id");
		txtId.setColumns(10);
		
		txtAuthor = new JTextField();
		txtAuthor.setText("author");
		txtAuthor.setColumns(10);
		
		txtIsbn = new JTextField();
		txtIsbn.setText("isbn");
		txtIsbn.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setText("price");
		txtPrice.setColumns(10);
		
		txtEdition = new JTextField();
		txtEdition.setText("edition");
		txtEdition.setColumns(10);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
			String[] bookinfo  = {txtId.getText(),txtAuthor.getText(),txtEdition.getText(),txtIsbn.getText(),txtPrice.getText(),"YES" };
			addBook(bookinfo);
			}
		});
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(47)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(lblId_1)
						.addComponent(lblAuthor)
						.addComponent(lblIsbn)
						.addComponent(lblPrice)
						.addComponent(lblEdition))
					.addGap(57)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtEdition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtIsbn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAuthor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(473, Short.MAX_VALUE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblId_1))
					.addGap(26)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAuthor)
						.addComponent(txtAuthor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIsbn)
						.addComponent(txtIsbn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrice)
						.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEdition)
						.addComponent(txtEdition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(65, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("History", null, tabbedPane_2, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane_2.addTab("New tab", null, panel_3, null);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(11)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tabbedPane_info, tabbedPane, panel, lblName, tabbedPane_AvlBooks, panel_1, tabbedPane_1, panel_2, tabbedPane_2, panel_3}));
		
		setInfoFrame();
		showBooks();
//		dao.destruct();	
	}
}
