package com.koreait.myProject.galleryCommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.koreait.myProject.dao.GalleryBoardDAO;
import com.koreait.myProject.dto.GalleryBoard;

public class SelectAllCommand {

	public Map<String, Object> execute(SqlSession sqlSession) {
		
		GalleryBoardDAO galleryBoardDAO = sqlSession.getMapper(GalleryBoardDAO.class);
		
		List<GalleryBoard> list = galleryBoardDAO.selectAll();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (list.size() == 0) {
			resultMap.put("status", 500);
			resultMap.put("list", null);
			resultMap.put("message", "목록이 없습니다.");
		}else {
			resultMap.put("status", 200);
			resultMap.put("list", list);
			resultMap.put("message", "전체" + list.size() + "개의 목록을 가져왔습니다.");
		}
		return resultMap;
	}
		
		
		
		
}


