package com.koreait.integration1.service;

import java.util.List;
import java.util.Map;

import com.koreait.integration1.domain.Board;

public interface BoardService {

	
	public List<Board> totalList();
	
	public List<Board> searchList(Map<String, String> map);
	
}
