package com.koreait.contact01.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.koreait.contact01.dto.Contact;

public class ContactDAO {

	@Autowired
	private JdbcTemplate template;
	
	private String sql;
	
	// list
	public List<Contact> contactList() {
		sql = "SELECT NO, NAME, TEL, ADDR, EMAIL, NOTE FROM CONTACT";
		return template.query(sql, new BeanPropertyRowMapper<Contact>(Contact.class)); 	
	}
	
	public Contact selectContactByNo(long no) {
		sql = "SELECT NO, NAME, TEL, ADDR, EMAIL, NOTE FROM CONTACT WHERE NO = ?";
		return template.queryForObject(sql, new BeanPropertyRowMapper<Contact>(Contact.class), no);
	}
	
	public int insertContact(final Contact contact) {
		sql = "INSERT INTO CONTACT VALUES(CONTACT_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
		return template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, contact.getName());
				ps.setString(2, contact.getTel());
				ps.setString(3, contact.getAddr());
				ps.setString(4, contact.getEmail());
				ps.setString(5, contact.getNote());
			}
		});
	}
	
	public int updateContact(final Contact contact) {
		sql = "UPDATE CONTACT SET NAME = ?, TEL = ?, ADDR = ?, EMAIL = ?, NOTE = ? WHERE NO = ?";
		return template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, contact.getName());
				ps.setString(2, contact.getTel());
				ps.setString(3, contact.getAddr());
				ps.setString(4, contact.getEmail());
				ps.setString(5, contact.getNote());
				ps.setLong(6, contact.getNo());
			}
		});
	}
	
	public int deleteContact(final long no) {
		sql = "DELETE FROM CONTACT WHERE NO = ?";
		return template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setLong(1, no);
			}
		});
	}
	
}
