package com.capgemini.homework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.JPasswordField;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.capgemini.homework.session.CustomSession;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textName;
	private JPasswordField textPassword;
	private JLabel lblPleaseEnterThe;
	private JLabel lblId;
	private JTextField textId;
	ApplicationContext ctx = null;
	CustomSession session;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		ctx = new ClassPathXmlApplicationContext("spring.xml");
		session = (CustomSession)ctx.getBean("customSession");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblName = new JLabel("Name");

		JLabel lblPassword = new JLabel("Password");

		textName = new JTextField();
		textName.setColumns(10);

		textPassword = new JPasswordField();
		textPassword.setColumns(10);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_cap?"+"user=root&password=root");
					System.out.println("connection successful");
					Statement statement = connect.createStatement();
					ResultSet rs = statement.executeQuery("select * from student");
					while(rs.next()){
						System.out.println(rs.getString("id"));                         
						if(textId.getText().equals(rs.getString("id"))  &&  textName.getText().equals( rs.getString("name") ) &&  Arrays.equals ( textPassword.getPassword(),  rs.getString("passwrd").toCharArray() )   ){
							CustomSession.id = rs.getString("id");
							CustomSession.name = rs.getString("name");
							try {
								Next frame = new Next();
								frame.setVisible(true);
								setVisible(false);
							} catch (Exception ed) {
								System.out.println(ed);
							}	
							break;
						}
					}
					lblPleaseEnterThe.setText("Please Enter the Username and password correctly");
					lblPleaseEnterThe.setForeground(Color.red);
				}catch (Exception et) {
					System.out.println(et);
				}
			}
		});

		JLabel lblLibraryManagementSystem = new JLabel("Library Management System");
		lblLibraryManagementSystem.setFont(new Font("Tahoma", Font.PLAIN, 26));

		lblPleaseEnterThe = new JLabel("Please Enter the Name and password");
		
		lblId = new JLabel("ID");
		
		textId = new JTextField();
		textId.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(113)
							.addComponent(lblLibraryManagementSystem))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(257)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(30)
									.addComponent(lblPleaseEnterThe))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(102)
									.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPassword)
										.addComponent(lblName)
										.addComponent(lblId))
									.addGap(56)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textId, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
										.addComponent(textName)
										.addComponent(textPassword, 116, 116, Short.MAX_VALUE))))))
					.addGap(299))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(lblLibraryManagementSystem)
					.addGap(36)
					.addComponent(lblPleaseEnterThe)
					.addGap(77)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(textId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(textName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(textPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(116, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
