package com.koreait.myProject.Command;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.myProject.dao.UserDAO;
import com.koreait.myProject.dto.User;

public class JoinCommand implements UserCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		User user = new User();
		user.setId(id);
		user.setPw(pw);
		user.setName(name);
		user.setPhone(phone);
		user.setEmail(email);
		user.setAddress(address);
		
		UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
		userDAO.join(user);
		
		
	}

}
