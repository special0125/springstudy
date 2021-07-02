package com.koreait.myProject.util;

public class AuthCode {

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
