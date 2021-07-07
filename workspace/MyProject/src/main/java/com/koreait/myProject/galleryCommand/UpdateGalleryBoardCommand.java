package com.koreait.myProject.galleryCommand;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.myProject.dao.GalleryBoardDAO;

public class UpdateGalleryBoardCommand implements GalleryCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)map.get("multipartRequest");
	
		long no = Long.parseLong(multipartRequest.getParameter("no"));
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		String filename1 = multipartRequest.getParameter("filename1");
		MultipartFile filename2 = multipartRequest.getFile("filename2");
		
		String realPath = multipartRequest.getServletContext().getRealPath("resources/archive");
		
		File file = new File(realPath, filename1);  // 서버에 저장된 파일
		
		GalleryBoardDAO galleryBoardDAO = sqlSession.getMapper(GalleryBoardDAO.class);
		
		if (filename2 != null && !filename2.isEmpty()) {  //새로운 첨부가 있다.
			
			// 기존 첨부와 새로운 첨부가 모두 있으면 -> 기존 첨부를 지운다.
			if (file != null) {
				// 기존 첨부 지우기
				if (file.exists()) {
					file.delete();
				}
			}
			
			// 새 첨부 넣기
			String originalFilename = filename2.getOriginalFilename();
			String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);  // 확장자
			String filename = originalFilename.substring(0, originalFilename.lastIndexOf("."));
			String uploadFilename = filename + "_" + System.currentTimeMillis() + "." + extension;
			File uploadFile = new File(realPath, uploadFilename);
			try {
				filename2.transferTo(uploadFile);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			// DB에 넣는 파일명 인코딩 처리
			try {
				uploadFilename = URLEncoder.encode(uploadFilename, "utf-8");
			}catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			galleryBoardDAO.updateBoard(title, content, uploadFilename, no);
			
		} else {  // 새로운 첨부가 없다.
			galleryBoardDAO.updateBoard(title, content, filename1, no);
		}
		
		
	}

}
