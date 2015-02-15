package com.noSpysHere.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

import javax.servlet.http.HttpSession;

public class Utils {

	public static String getKnockCode(String username) {
		String knockCode = "5555";
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(username.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			String hexknockCode = bigInt.toString(16).substring(0, Math.min(knockCode.length(), 4));
			String code = "";
			for(char r: hexknockCode.toCharArray()){
				code = code + Integer.toString(Character.digit(r, 16)%4);
			}
			knockCode = code;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return knockCode;
	}
	
	
	public static void spyCodeStuff(HttpSession session, String code, String username) {
		if(session.getAttribute("isASpy")==null){
			session.setAttribute("isASpy", 0);
		}
		if(session.getAttribute("spyCode")==null){
			LinkedList<String> codes = new LinkedList<String>();
			codes.add(code);
			session.setAttribute("spyCode", codes);
		}else{
			@SuppressWarnings("unchecked")
			LinkedList<String> codes = (LinkedList<String>) session.getAttribute("spyCode");
			if(codes.size()==4){
				codes.pop();
			}
			codes.add(code);
			session.setAttribute("spyCode", codes);
			if(codes.size()==4){
				String knockcode = "";
				for(String c: codes){
					knockcode = knockcode + c;
				}
				System.out.println("Current KnockCode" + knockcode);
				if(knockcode.equals(getKnockCode(username))){
					session.setAttribute("isASpy", 1);
				}
			}
		}
	}
}
