package com.projectTask.multichatapp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface Encryption {
	
	public static String passwordEncrypt(String plainPassword) throws NoSuchAlgorithmException{
		String encryptedPassword = null ;
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(plainPassword.getBytes()) ;
		byte[] encrypt = messageDigest.digest() ;
		encryptedPassword = new String(encrypt) ;
		//System.out.println("Encrypted Password :- "+encryptedPassword) ;
 		return encryptedPassword ;
		
	}
	

}
