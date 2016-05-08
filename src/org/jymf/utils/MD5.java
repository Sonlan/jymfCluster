package org.jymf.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	private static MD5 md5 = null;
	private final String ENCODING = "UTF-8";
	
	private MD5(){};
	
	public static MD5 getInstance(){
		if(md5 == null)
			md5 = new MD5();
		return md5;
	}
	
	/**
	 * encrypt "password"
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public String encrypt(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] bytes = md.digest(password.getBytes(ENCODING));
		BigInteger bint = new BigInteger(1, bytes);
		return bint.toString(16);
		
	}
	/**
	 * compare password
	 * @param inPassword - get from http request
	 * @param dbPassword - get from DB(encrypted)
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public boolean compare(String inPassword,String dbPassword) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		if(encrypt(inPassword).equals(dbPassword))	
			return true;	
		return false;
	}
}
