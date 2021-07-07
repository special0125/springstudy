package com.koreait.myProject.dao;

import java.util.List;

import com.koreait.myProject.dto.GalleryBoard;
import com.koreait.myProject.dto.Page;

public interface GalleryBoardDAO {

	public List<GalleryBoard> selectAll(Page page);
	
	public int getTotalMemberCount();
	
	public GalleryBoard selectByNo(long no);
	
	public int insertBoard(String id, String title, String content, String filename);
	
	public int updateBoard(String id, String title, String content, Long no);
	
	public int deleteBoard(long no);
}
