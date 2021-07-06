package com.koreait.apptest.controller;

import java.util.Map; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.apptest.command.IdCheckCommand;
import com.koreait.apptest.command.JoinCommand;
import com.koreait.apptest.command.LeaveCommand;
import com.koreait.apptest.command.LoginCommand;
import com.koreait.apptest.command.LogoutCommand;


//@Controller이 없었음
@Controller
public class MemberController {

	private SqlSession sqlSession;
	private IdCheckCommand idCheckCommand;
	private JoinCommand joinCommand;
	private LoginCommand loginCommand;
	private LogoutCommand logoutCommand;
	private LeaveCommand leaveCommand;

	@Autowired
	public MemberController(SqlSession sqlSession,
			IdCheckCommand idCheckCommand, 
			JoinCommand joinCommand,
			LoginCommand loginCommand,
			LogoutCommand logoutCommand,
			LeaveCommand leaveCommand) {
		super();
		this.sqlSession = sqlSession;
		this.idCheckCommand = idCheckCommand;
		this.joinCommand = joinCommand;
		this.loginCommand = loginCommand;
		this.logoutCommand = logoutCommand;
		this.leaveCommand = leaveCommand;
	}

	@GetMapping(value = { "/", "index.do" })
	public String index() {
		return "index";
	}
	
	// @GetMapping으로 되어있었음
	@PostMapping(value = "login.do")
	public String login(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		loginCommand.execute(sqlSession, model);
		return "redirect:/";
	}
	
	@GetMapping(value = "logout.do")
	public String logout(HttpSession session, Model model) {
		model.addAttribute("session", session);
		logoutCommand.execute(sqlSession, model);
		return "redirect:/";
	}
	
	@GetMapping(value = "joinPage.do")
	public String joinPage() {
		return "member/join";
	}
	
	// @PostMapping으로 되있었음 , @ResponseBody 에너테이션 없었음. ajax json 데이터를 받으려면 @ResponseBody에너테이션 필수
	@GetMapping(value = "idCheck.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Integer> idCheck(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return idCheckCommand.execute(sqlSession, model);
	}
	
	@PostMapping(value = "join.do")
	public void join(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		joinCommand.execute(sqlSession, model);
	}
	
	@GetMapping(value = "leave.do")
	public void leave(HttpSession session, HttpServletResponse response, Model model) {
		model.addAttribute("session", session);
		model.addAttribute("response", response);
		leaveCommand.execute(sqlSession, model);
	}

}
