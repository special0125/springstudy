package com.koreait.myProject.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.myProject.galleryCommand.InsertBoardCommand;
import com.koreait.myProject.galleryCommand.SelectAllCommand;
import com.koreait.myProject.galleryCommand.SelectByNoCommand;

@Controller
public class BoardController {

	private SqlSession sqlSession;
	private SelectAllCommand selectAllCommand;
	private SelectByNoCommand selectByNoCommand;
	private InsertBoardCommand insertBoardCommand;
	
	
	@Autowired
	public BoardController(SqlSession sqlSession, 
						   SelectAllCommand selectAllCommand,
						   SelectByNoCommand selectByNoCommand,
						   InsertBoardCommand insertBoardCommand) {
		super();
		this.sqlSession = sqlSession;
		this.selectAllCommand = selectAllCommand;
		this.selectByNoCommand = selectByNoCommand;
		this.insertBoardCommand = insertBoardCommand;
	}



	@GetMapping(value="galleryBoard.do")
	public String galleryBoard() {
		return "galleryBoard/galleryBoard"; 
	}
	
	@GetMapping(value="selectAll.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectAll() {
		return selectAllCommand.execute(sqlSession);
	}
	
	@GetMapping(value="selectByNo.do")
	public String selectByNo(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		selectByNoCommand.execute(sqlSession, model);
		return "galleryBoard/selectByNo";
	}
	
	@GetMapping(value="insertPage.do")
	public String insertPage() {
		return "galleryBoard/insertPage";
	}
	
	@PostMapping(value="insertBoard.do")
	public String insertBoard(MultipartHttpServletRequest multipartRequest, Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		insertBoardCommand.execute(sqlSession, model);
		return "redirect:selectAll.do";
	}
	
}
