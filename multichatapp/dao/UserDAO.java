package com.projectTask.multichatapp.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.projectTask.multichatapp.DTO.UserDTO;
import com.projectTask.multichatapp.utils.Encryption;

public class UserDAO {
	public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException, Exception {
		Connection con = null ;
		PreparedStatement pstmt  = null;
		ResultSet rs = null ;
		final String SQL = "select userid from users where userid=? and password=?" ;
		try {
			con = CommonDAO.createConnection() ;
			pstmt = con.prepareStatement(SQL) ;
			pstmt.setString(1,userDTO.getUserid());
			String encryptedpwd = Encryption.passwordEncrypt(new String(userDTO.getPassword())) ;
			pstmt.setString(2, encryptedpwd);
			rs = pstmt.executeQuery(); 
			return rs.next() ;
		}finally {
			if(rs!= null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.isClosed() ;
 			}
		}
	}

	
	public int add(UserDTO userDTO) throws ClassNotFoundException, SQLException, Exception {
		System.out.println("Rec "+userDTO.getUserid()+" "+userDTO.getPassword());
		Connection connection = null;
		Statement stmt = null ; // query
		try { // Guarded region
		connection = CommonDAO.createConnection();// step 1 :- connection create
		// step 2 we do a query
		stmt = connection.createStatement();
		int record = stmt.executeUpdate("insert into users (userid,password) values('"+userDTO.getUserid()+"','"+Encryption.passwordEncrypt(new String(userDTO.getPassword()))+"')") ;
		return record; 
		}
		finally { // Always execute
			if(stmt != null) {
		stmt.close();
		}
			if(connection != null) {
		connection.close();
			}
		}
		
	}
}
