package com.koreait.myProject.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.myProject.dto.Page;
import com.koreait.myProject.galleryCommand.DeleteGalleryBoardCommand;
import com.koreait.myProject.galleryCommand.InsertBoardCommand;
import com.koreait.myProject.galleryCommand.SelectAllCommand;
import com.koreait.myProject.galleryCommand.SelectByNoCommand;
import com.koreait.myProject.galleryCommand.UpdateGalleryBoardCommand;

@Controller
public class BoardController {

	private SqlSession sqlSession;
	private SelectAllCommand selectAllCommand;
	private SelectByNoCommand selectByNoCommand;
	private InsertBoardCommand insertBoardCommand;
	private  UpdateGalleryBoardCommand updateGalleryBoardCommand;
	private DeleteGalleryBoardCommand deleteGalleryBoardCommand;
	
	@Autowired
	public BoardController(SqlSession sqlSession, 
						   SelectAllCommand selectAllCommand,
						   SelectByNoCommand selectByNoCommand,
						   InsertBoardCommand insertBoardCommand,
						   UpdateGalleryBoardCommand updateGalleryBoardCommand,
						   DeleteGalleryBoardCommand deleteGalleryBoardCommand) {
		super();
		this.sqlSession = sqlSession;
		this.selectAllCommand = selectAllCommand;
		this.selectByNoCommand = selectByNoCommand;
		this.insertBoardCommand = insertBoardCommand;
		this.updateGalleryBoardCommand = updateGalleryBoardCommand;
		this.deleteGalleryBoardCommand = deleteGalleryBoardCommand;
	}



	@GetMapping(value="galleryBoard.do")
	public String galleryBoard() {
		return "galleryBoard/galleryBoard"; 
	}
	
	@PostMapping(value="selectAll.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectAll(@RequestBody Page page, Model model) {
		model.addAttribute("page", page.getPage());
		return selectAllCommand.execute(sqlSession, model);
		
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
	public String insertBoard(MultipartHttpServletRequest multipartRequest,
							  Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		insertBoardCommand.execute(sqlSession, model);
		return "redirect:galleryBoard.do";
	}
	
	@PostMapping(value="updateBoard.do")
	public String updateBoard(MultipartHttpServletRequest multipartRequest, Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		updateGalleryBoardCommand.execute(sqlSession, model);
		return "redirect:selectByNo.do?no=" + multipartRequest.getParameter("no");
	}
	
	@PostMapping(value="deleteBoard.do")
	public String deleteBoard(MultipartHttpServletRequest multipartRequest, Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		deleteGalleryBoardCommand.execute(sqlSession, model);
		return "redirect:galleryBoard.do";
	}
	
	
	
}
