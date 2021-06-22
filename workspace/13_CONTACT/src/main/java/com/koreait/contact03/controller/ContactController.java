package com.koreait.contact03.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.contact03.command.SelectContactByNoCommand;
import com.koreait.contact03.command.SelectContactListCommand;

@Controller
public class ContactController {
	
	private SqlSession sqlSession;
	private SelectContactListCommand selectContactListCommand;
	private SelectContactByNoCommand selectContactByNoCommand;
	
	
	@Autowired
	public ContactController(SqlSession sqlSession, 
							 SelectContactListCommand selectContactListCommand,
							 SelectContactByNoCommand selectContactByNoCommand) {
		super();
		this.sqlSession = sqlSession;
		this.selectContactListCommand = selectContactListCommand;
		this.selectContactByNoCommand = selectContactByNoCommand;
	}

	@GetMapping(value="/")
	public String index() {
		return "index";
	}
	
	@GetMapping(value="selectContactList.do")
	public String selectContactList(Model model) {
		selectContactListCommand.execute(sqlSession, model);
		return "contact/list";
	}
	
	@GetMapping(value="selectContactByNo.do")
	public String selectselectContactByNo(@RequestParam("no") long no, Model model) {
		model.addAttribute("no", no);
		selectContactByNoCommand.execute(sqlSession, model);
		return "contact/view";
	}
	
	@GetMapping(value="insertContactPage.do")
	public String insertContactPage() {
		return "contact/insert";
	}
	
		
}

