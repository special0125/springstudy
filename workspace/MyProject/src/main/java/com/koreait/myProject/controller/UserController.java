package com.koreait.myProject.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.myProject.command.EmailAuthCommand;
import com.koreait.myProject.command.IdCheckCommand;
import com.koreait.myProject.command.JoinCommand;
import com.koreait.myProject.command.LeaveCommand;
import com.koreait.myProject.command.LoginCommand;
import com.koreait.myProject.command.LogoutCommand;

@Controller
public class UserController {

	private SqlSession sqlSession;
	private LoginCommand loginCommand;
	private LogoutCommand logoutCommand;
	private IdCheckCommand idCheckCommand;
	private EmailAuthCommand emailAuthCommand;
	private JoinCommand joinCommand;
	private LeaveCommand leaveCommand;
	
	@Autowired
	public UserController(SqlSession sqlSession, 
						  LoginCommand loginCommand,
						  LogoutCommand logoutCommand,
						  LeaveCommand leaveCommand,
						  IdCheckCommand idCheckCommand,
						  EmailAuthCommand emailAuthCommand,
						  JoinCommand joinCommand){
		super();
		this.sqlSession = sqlSession;
		this.loginCommand = loginCommand;
		this.logoutCommand = logoutCommand;
		this.leaveCommand = leaveCommand;
		this.idCheckCommand = idCheckCommand;
		this.emailAuthCommand = emailAuthCommand;
		this.joinCommand = joinCommand;
	}

	@GetMapping(value= {"/", "index"})
	public String index() {
		return "index";
	}
	
	@GetMapping(value="joinPage.do")
	public String joinPage() {
		return "user/join";
	}
	
	@GetMapping(value="idCheck.do", produces="application/json; charset=utf-8") 
	@ResponseBody
	public Map<String, Integer> idCheck(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return idCheckCommand.execute(sqlSession, model);
	}
	
	@GetMapping(value="verifyNum.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, String> verifyNum(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return emailAuthCommand.execute(sqlSession, model);
	}
	
	
	@PostMapping(value="join.do") 
	public String join(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		joinCommand.execute(sqlSession, model);
		return "redirect:/";
	}
	

	@PostMapping(value="login.do")
	public String login(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		loginCommand.execute(sqlSession, model);
		return "redirect:/";
	}
	
	@GetMapping(value="logout.do")
	public String logout(HttpSession session, Model model) {
		model.addAttribute("session", session);
		logoutCommand.execute(sqlSession, model);
		return "redirect:/";
	}
	
	
	@GetMapping(value="leave.do")
	public String leave(HttpSession session, Model model) {
		model.addAttribute("session", session);
		leaveCommand.execute(sqlSession, model);
		return "redirect:/";
	}
	
}
