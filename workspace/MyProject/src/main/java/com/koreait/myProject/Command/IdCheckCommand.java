package com.koreait.myProject.Command;

import java.util.HashMap; 
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.myProject.dao.UserDAO;

public class IdCheckCommand  {

	
	public Map<String, Integer> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String id = request.getParameter("id");
		
		UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
		
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		resultMap.put("count", userDAO.idCheck(id));
		return resultMap;
		
		
		
	}

}
