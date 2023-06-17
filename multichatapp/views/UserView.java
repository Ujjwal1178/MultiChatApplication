package com.projectTask.multichatapp.views;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UserView extends JFrame {
           int counter;
	//int counter  ;
	public UserView(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500) ;
		setResizable(false); 
		setTitle("Login") ;
		setLocationRelativeTo(null) ;
		//setLocation(500,150) ;
		JLabel welcome = new JLabel("Login") ;
		welcome.setFont(new Font("Verdana",Font.BOLD,40));
		Container container = this.getContentPane() ; 
		container.setLayout(null) ;
		welcome.setBounds(100,70,200,60) ;
		container.add(welcome) ;
		JButton button = new JButton("Count") ;  // source
		button.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent event) {
				counter++ ;
				welcome.setText("Count"+counter) ;
			}
		});
		button.setBounds(100,300,200,50) ;
		container.add(button) ;
		
		setVisible(true) ;
	} 
	public static void main(String[] arg) {
		UserView userview = new UserView() ;
	}
}
