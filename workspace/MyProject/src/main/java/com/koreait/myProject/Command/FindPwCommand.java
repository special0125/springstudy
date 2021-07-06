package com.koreait.myProject.Command;

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

import com.koreait.myProject.dao.UserDAO;
import com.koreait.myProject.dto.User;
import com.koreait.myProject.util.AuthCode;

public class FindPwCommand {

	@Autowired
	private JavaMailSender mailSender;
	
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String authCode = null;
		
		User user = new User();
		user.setId(id);
		user.setEmail(email);
		
		UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
		User findUser = userDAO.findUser(user);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (findUser != null) {
			MimeMessage message = mailSender.createMimeMessage();
			try {
				message.setHeader("Content-Type", "text/plain; charset=utf8");
				message.setFrom(new InternetAddress("ykt3401@gmail.com", "관리자"));
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
				message.setSubject("인증 요청 메일입니다.");
				authCode = AuthCode.getAuthCode(6);
				message.setText("인증번호는" + authCode + "입니다.");
			}catch (Exception e) {
				e.printStackTrace();
			}
			mailSender.send(message);
			resultMap.put("authCode", authCode);
			resultMap.put("findUser", findUser);
			resultMap.put("status", 200);
		} else {
			resultMap.put("status", 500);
			resultMap.put("message", "아이디와 이메일을 확인하세요.");
		}
		
		return resultMap;
		
	}

}
