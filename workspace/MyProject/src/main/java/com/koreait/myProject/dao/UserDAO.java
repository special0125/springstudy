package com.koreait.myProject.dao;

import com.koreait.myProject.dto.User;

public interface UserDAO {

	public int idCheck(String id);
	
	public int join(User user);

	public User login(User user);
	
	public int leave(String id);
	
	public User findId(User user);
	
	public User findUser(User user);
	
	public int changePw(User user);
}
