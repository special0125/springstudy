package com.koreait.contact01.command;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.contact01.dao.ContactDAO;
import com.koreait.contact01.dto.Contact;

public class InsertContactCommand implements ContactCommand {

	@Autowired
	private ContactDAO contactDAO;
	
	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		Contact contact = (Contact)map.get("contact");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		response.setContentType("text/html; charset=utf-8");
		int result = contactDAO.insertContact(contact);
			try {
				PrintWriter out = response.getWriter();
				if(result > 0) {
					out.println("<script>");
					out.println("alert('연락처가 등록 되었습니다.')");
					out.println("location.href='list.do'");
					out.println("</script>");
				}else {
					out.println("<script>");
					out.println("alert('연락처가 등록 되지않았습니다.')");
					out.println("history.back()");
					out.println("</script>");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}


