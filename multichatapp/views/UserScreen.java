package com.projectTask.multichatapp.views;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.projectTask.multichatapp.DTO.UserDTO;
import com.projectTask.multichatapp.dao.UserDAO;
import com.projectTask.multichatapp.utils.UserInfo;

public class UserScreen extends JFrame {
	private JTextField useridtxt;
	private JPasswordField passwordField; 

	
	public static void main(String[] args) {
		
	UserScreen window = new UserScreen();
				
	}
	UserDAO userDAO = new UserDAO() ;
	private void doLogin() {
		String userid = useridtxt.getText();
		char[] password = passwordField.getPassword();
		
		UserDTO userDTO = new UserDTO(userid,password) ;	
		try {
			String message = "" ;
			if(userDAO.isLogin(userDTO)) {
				//JOptionPane.showMessageDialog(this,"Welcome "+userid);
				message = "Welcome "+userid ;
				UserInfo.USER_NAME = userid;
				JOptionPane.showMessageDialog(this,message) ;
				setVisible(false);
				dispose();//it removes the login screen also from the memory
				DashBoard dashBoard = new DashBoard(message);
				dashBoard.setVisible(true);
			}else {
				message = "Invalid userid or password";
				JOptionPane.showMessageDialog(this,message) ;
			}
			//JOptionPane.showMessageDialog(this,message) ;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	private void register() {
		String userid = useridtxt.getText();
		char[] password = passwordField.getPassword();
		//UserDAO userDAO = new UserDAO() ;
		UserDTO userDTO = new UserDTO(userid,password) ;
		try {
		int result = userDAO.add(userDTO);
		if(result > 0) {
			JOptionPane.showMessageDialog(this, "Register Scuccessfully");
			//System.out.println("Record Added") ;
		}else {
			//System.out.println("Record Not Added") ;
			JOptionPane.showMessageDialog(this, "Register Scuccessfully");
		}
		}
		catch(ClassNotFoundException | SQLException ex) {
			System.out.println("DB Issue......") ;
			ex.printStackTrace();
		}
		catch(Exception ex) {
			System.out.println("Some Generic Exception Raised.....");
			ex.printStackTrace();// where is the exception 
		}
		System.out.println("userid"+userid+"password"+password);
		
	}

	/**
	 * Create the application.
	 */
	public UserScreen() {
		setResizable(false);
		setTitle("LOGIN");
		getContentPane().setBackground(Color.WHITE);
		
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(254, 30, 200, 100);
		getContentPane().add(lblNewLabel);
		
		useridtxt = new JTextField();
		useridtxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		useridtxt.setBounds(351, 150, 264, 35);
		getContentPane().add(useridtxt);
		useridtxt.setColumns(10);
		
		JLabel useridlb = new JLabel("User Id ");
		useridlb.setFont(new Font("Tahoma", Font.BOLD, 18));
		useridlb.setBounds(156, 150, 125, 29);
		getContentPane().add(useridlb);
		
		JLabel pwdlbl = new JLabel("Password");
		pwdlbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		pwdlbl.setBounds(156, 207, 125, 29);
		getContentPane().add(pwdlbl);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(351, 207, 264, 35);
		getContentPane().add(passwordField);
		
		JButton loginbt = new JButton("LOG IN");
		loginbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin() ;
			}
		});
		loginbt.setFont(new Font("Tahoma", Font.BOLD, 24));
		loginbt.setBounds(174, 290, 157, 44);
		getContentPane().add(loginbt);
		
		JButton registerbt = new JButton("Register");
		registerbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		registerbt.setFont(new Font("Tahoma", Font.BOLD, 24));
		registerbt.setBounds(376, 290, 157, 44);
		getContentPane().add(registerbt);
		setBackground(Color.WHITE);
		setSize(833, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true) ;
		
	}
}