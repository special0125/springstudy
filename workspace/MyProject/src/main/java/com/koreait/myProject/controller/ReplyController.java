package com.koreait.myProject.controller;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.myProject.ReplyCommand.ReplyListCommand;
import com.koreait.myProject.dto.Page;

@Controller
public class ReplyController {

	private SqlSession sqlSession;
	private ReplyListCommand replyListCommand;

	@Autowired
	public ReplyController(SqlSession sqlSession,
						   ReplyListCommand replyListCommand) {
		super();
		this.sqlSession = sqlSession;
		this.replyListCommand = replyListCommand;
	}
	
	@PostMapping(value="replyList.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectAll(@RequestBody Page page, Model model) {
		model.addAttribute("page", page.getPage());
		return replyListCommand.execute(sqlSession, model);
	}
	
}
