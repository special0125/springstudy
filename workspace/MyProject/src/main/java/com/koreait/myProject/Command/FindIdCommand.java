package com.koreait.myProject.Command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.myProject.dao.UserDAO;
import com.koreait.myProject.dto.User;

public class FindIdCommand implements UserCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		
		UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
		User findId = userDAO.findId(user);
		
		if(findId != null) {
			model.addAttribute("findId", findId);
		}
	
		
		
		
	}

}
