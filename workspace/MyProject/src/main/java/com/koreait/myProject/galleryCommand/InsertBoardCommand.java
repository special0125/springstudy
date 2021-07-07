package com.koreait.myProject.galleryCommand;

import java.io.File;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.myProject.dao.GalleryBoardDAO;

public class InsertBoardCommand implements GalleryCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)map.get("multipartRequest");
		
		String id = multipartRequest.getParameter("id");
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		
		MultipartFile file = multipartRequest.getFile("filename");
		
		
		GalleryBoardDAO galleryBoardDAO = sqlSession.getMapper(GalleryBoardDAO.class);
		
		if (file != null && !file.isEmpty()) {
			
			// 올릴 파일명
			String originalFilename = file.getOriginalFilename();
			// 서버에 저장할 파일명
			// 파일명의 중복 방지 대책필요
			// 파일명_올린시간.확장자
			String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);  // 확장자
			String filename = originalFilename.substring(0, originalFilename.lastIndexOf("."));  // 0번째부터 . 전까지
			String uploadFilename = filename + "_" + System.currentTimeMillis() + "." + extension;
			
			// 첨부파일을 저장할 서버 위치
			String realPath = multipartRequest.getServletContext().getRealPath("resources/archive");
			// 디렉터리 생성
			File archive = new File(realPath);
			if (!archive.exists()) {
				archive.mkdirs();
			}
			
			// 서버에 첨부파일 저장
			File attach = new File(archive, uploadFilename);
			try {
				file.transferTo(attach);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			// DB에 넣는 파일명을 인코딩 처리
			try {
				uploadFilename = URLEncoder.encode(uploadFilename, "utf-8");
			} catch (Exception e) { }
			
			
			// DB에 데이터 저장
			galleryBoardDAO.insertBoard(id, title, content, uploadFilename);
		}else {
			
			// DB에 데이터 저장
			galleryBoardDAO.insertBoard(id, title, content, ""); // 첨부가 없는 경우
			
			
			
		}
	}

}
