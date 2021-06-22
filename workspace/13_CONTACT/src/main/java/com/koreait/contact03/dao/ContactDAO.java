package com.koreait.contact03.dao;

import java.util.List;

import com.koreait.contact03.dto.Contact;

public interface ContactDAO {

	public List<Contact> selectContactList();
	
	public Contact selectContactByNo(long no);
}
