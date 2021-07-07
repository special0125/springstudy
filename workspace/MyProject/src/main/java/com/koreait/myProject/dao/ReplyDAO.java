package com.koreait.myProject.dao;

import java.util.List;

import com.koreait.myProject.dto.Page;
import com.koreait.myProject.dto.Reply;

public interface ReplyDAO {

	
	public int getTotalReplyCount();
	
	public List<Reply> replyList(Page page);
	
}
