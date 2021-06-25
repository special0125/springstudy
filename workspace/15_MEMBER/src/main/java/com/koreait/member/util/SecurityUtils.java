package com.koreait.member.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class SecurityUtils {
	
	// 크로스 사이트 스크립트(XXS)
	// 스크립트 입력을 무력화
	public static String xxs(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		return str;
	}
	
	
	// 암호화 : java
	public static String sha256(String str) {
		// java : java.sequrity

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		}catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// md.digest() 암호화 된 바이트 배열
		StringBuilder sb = new StringBuilder();
		for (byte b : md.digest()) {
			sb.append(String.format("%02x", b));  // 16진수 표기법 변환  X: 대문자, x: 소문자
		}
		return sb.toString();
	}
	
	// 암호화 : commons-codec 디펜던시
	public static String encodeBase64(String str) {
		// commons-codec
		// Base64
		// Base64.encodeBase64(pw.getBytes()) : 암호화된 바이트 배열
		/*
		StringBuilder sb = new StringBuilder();
		for (byte b : Base64.encodeBase64(str.getBytes())) {
			sb.append(String.format("%02x", b));  // 16진수 표기법 변환
		}
		String encodedPw = sb.toString();
		// System.out.println("암호화 : " + encodedPw);
		*/
		return new String(Base64.encodeBase64(str.getBytes()));
	}
	
	// 복호화: 
	public static String decodeBase64(String str) {
		return new String(Base64.decodeBase64(str.getBytes()));
	}
	
	// 인증코드 생성
	public static String getAuthCode(int length) {
		String authCode = "";
		char[] characters = {
				'A', 'B', 'C', 'D', 'E',
				'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O',
				'P', 'Q', 'R', 'S', 'T',
				'U', 'V', 'W', 'X', 'Y',
				'Z', '0', '1', '2', '3',
				'4', '5', '6', '7', '8',
				'9', '!', '$', '?', '&',
		};
		for (int i = 0; i < length; i++) {
			authCode += characters[(int)(Math.random() * characters.length)];
		}
		return authCode;
	}
}
