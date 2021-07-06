package com.koreait.myProject.dao;

import java.util.List;

import com.koreait.myProject.dto.GalleryBoard;

public interface GalleryBoardDAO {

	public List<GalleryBoard> selectAll();
	
	public GalleryBoard selectByNo(long no);
	
	public int insertBoard(String id, String title, String content, String filename);
}
