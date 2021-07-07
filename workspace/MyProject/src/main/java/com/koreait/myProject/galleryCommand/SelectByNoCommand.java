package com.koreait.myProject.galleryCommand;

import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.myProject.dao.GalleryBoardDAO;
import com.koreait.myProject.dto.GalleryBoard;

public class SelectByNoCommand implements GalleryCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		long no = Long.parseLong(request.getParameter("no"));
		
		GalleryBoardDAO galleryBoardDAO = sqlSession.getMapper(GalleryBoardDAO.class);
		GalleryBoard galleryBoard = galleryBoardDAO.selectByNo(no);

		if(galleryBoard != null) {
			model.addAttribute("galleryBoard", galleryBoard);
			try {
				model.addAttribute("filename", URLDecoder.decode(galleryBoard.getFilename(), "utf-8"));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		
		
	}

}
