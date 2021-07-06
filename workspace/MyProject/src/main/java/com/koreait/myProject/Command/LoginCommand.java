package com.koreait.myProject.Command;

import java.util.Map; 

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.myProject.dao.UserDAO;
import com.koreait.myProject.dto.User;

public class LoginCommand implements UserCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		User user = new User();
		user.setId(id);
		user.setPw(pw);
		
		UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
		User loginUser = userDAO.login(user);
		
		if (loginUser != null) {
			request.getSession().setAttribute("loginUser", loginUser);
		}

	}

	
	
}
