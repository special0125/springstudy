package com.koreait.myProject.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.myProject.dao.UserDAO;
import com.koreait.myProject.dto.User;

public class LeaveCommand implements UserCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession)map.get("session");
		
		String id = ((User)session.getAttribute("loginUser")).getId();
		
		UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
		int count = userDAO.leave(id);
		
		if (count > 0) {
			session.invalidate();
		}
		
		
		
		
		
	}

}
