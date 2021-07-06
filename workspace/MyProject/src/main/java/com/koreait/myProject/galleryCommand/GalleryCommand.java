package com.koreait.myProject.galleryCommand;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface GalleryCommand {

	public void execute(SqlSession sqlSession, Model model);
	
	
	
	
}
