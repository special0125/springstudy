package com.koreait.myProject.galleryCommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.myProject.dao.GalleryBoardDAO;
import com.koreait.myProject.dto.GalleryBoard;
import com.koreait.myProject.dto.Page;
import com.koreait.myProject.util.PagingUtils;

public class SelectAllCommand{
	
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		int page = (Integer)map.get("page");
		
		GalleryBoardDAO galleryBoardDAO = sqlSession.getMapper(GalleryBoardDAO.class);
		int totalRecord = galleryBoardDAO.getTotalMemberCount();
		
		Page paging = PagingUtils.getPage(totalRecord, page);
		
		
		List<GalleryBoard> list = galleryBoardDAO.selectAll(paging);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (list.size() == 0) {
			resultMap.put("status", 500);
			resultMap.put("list", null);
			resultMap.put("paging", paging);
			resultMap.put("message", "목록이 없습니다.");
		}else {
			resultMap.put("status", 200);
			resultMap.put("list", list);
			resultMap.put("paging", paging);
			resultMap.put("message", list.size() + "개의 목록이 있습니다.");
		}
		return resultMap;
		
		
		
		
		
	}
		
		
		
		
}


