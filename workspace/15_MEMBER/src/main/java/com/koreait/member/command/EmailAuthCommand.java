package com.koreait.member.command;

import java.util.HashMap;
import java.util.Map;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;

import com.koreait.member.util.SecurityUtils;

public class EmailAuthCommand {

	@Autowired
	private JavaMailSender mailSender;
	
	public Map<String, String> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String email = request.getParameter("email");  // 인증번호를 받는 사람 이메일
		String authCode = null;
		// MumeMessage 클래스
		// 이메일을 작성하는 클래스
		MimeMessage message = mailSender.createMimeMessage();
		try {
			message.setHeader("Content-Type", "text/plain; charset=utf-8");
			message.setFrom(new InternetAddress("ykt3401@gmail.com", "관리자"));  // 보내는 사람
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));  // 받는 사람
			message.setSubject("인증 요청 메일입니다.");
			authCode = SecurityUtils.getAuthCode(6);
			message.setText("인증번호는" + authCode + "입니다.");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		// 이메일 보내기
		mailSender.send(message);
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("authCode", authCode);
		
		return resultMap;
		
		
	}
}
