package com.koreait.mvc03.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.mvc03.dto.Member;

@Controller
public class LoginController {

	@RequestMapping("loginPage.do")
	public String a() {
		
		return "member/login";
	}
	
	/*
	@RequestMapping("login.do")  // 매핑값은 중복 불가능
	public String b(HttpServletRequest request, Model model) {
		
		model.addAttribute("id", request.getParameter("id"));
		model.addAttribute("pw", request.getParameter("pw"));
		
		return "member/loginResult";
	}
	*/
	
	/*
	@RequestMapping("login.do")
	public String c(@RequestParam("id") String id,
					@RequestParam("pw") String pw,
					Model model) {
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		
		return "member/loginResult";
	}
	*/
	
	@RequestMapping("login.do")
	public String d(Member member, Model model) {
		model.addAttribute("id", member.getId());
		model.addAttribute("pw", member.getPw());
		
		return "member/loginResult";
	}
	
}
