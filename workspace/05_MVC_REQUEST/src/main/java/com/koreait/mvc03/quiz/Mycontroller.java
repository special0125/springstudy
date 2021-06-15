package com.koreait.mvc03.quiz;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Mycontroller {

	@RequestMapping("quiz/v01")
	public String a(HttpServletRequest request, Model model) {
		
		String title = request.getParameter("title");
		int hit = Integer.parseInt(request.getParameter("hit"));
		model.addAttribute("title", title);
		model.addAttribute("hit", hit);
		model.addAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		return "quiz/view01";
	}
	
	@RequestMapping("quiz/v02")
	public String b(@RequestParam("title") String title, @RequestParam("hit") int hit, Model model) {
		
		return "quiz/view02";
	}
	
	@RequestMapping("quiz/v03")
	public String c(Board board, Model model) {
		
		model.addAttribute("board", board);
		return "quiz/view03";
	}
	
	@RequestMapping("quiz/v04")
	public String d(@ModelAttribute("title") String title, @ModelAttribute("hit") int hit) {
		
		return "quiz/view04";
	}
	
	
}
