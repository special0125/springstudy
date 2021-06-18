package com.koreait.contact01.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.contact01.command.ContactListCommand;
import com.koreait.contact01.command.DeleteContactCommand;
import com.koreait.contact01.command.InsertContactCommand;
import com.koreait.contact01.command.SelectContactByNoCommand;
import com.koreait.contact01.command.UpdateContactCommand;
import com.koreait.contact01.dto.Contact;

@Controller
public class ContactController {
	
	private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
	private ContactListCommand contactListCommand;
	private SelectContactByNoCommand selectContactByNoCommand;
	private InsertContactCommand insertContactCommand;
	private UpdateContactCommand updateContactCommand;
	private DeleteContactCommand deleteContactCommand;
	@Autowired
	public void setCommand(ContactListCommand contactListCommand,
						   SelectContactByNoCommand selectContactByNoCommand,
						   InsertContactCommand insertContactCommand,
						   UpdateContactCommand updateContactCommand,
						   DeleteContactCommand deleteContactCommand) {
		this.contactListCommand = contactListCommand;
		this.selectContactByNoCommand = selectContactByNoCommand;
		this.insertContactCommand = insertContactCommand;
		this.updateContactCommand = updateContactCommand;
		this.deleteContactCommand = deleteContactCommand;
	}
	
	@GetMapping(value="/")
	public String index() {
		logger.info("index() 호출");
		return "index";
	}
	
	@GetMapping(value="list.do") 
	public String list(Model model) {
		logger.info("list() 호출");
		contactListCommand.execute(model);
		return "contact/list";
	}
	
	@GetMapping(value="selectContactByNo.do")
	public String selectContactByNo(@RequestParam("no") long no, Model model) {
		logger.info("selectBoardByNo() 호출");
		model.addAttribute("no", no);
		selectContactByNoCommand.execute(model);
		return "contact/view";
	}
	
	@GetMapping(value="insertContactPage.do")
	public String insertContactPage() {
		return "contact/insert";
	}
	
	@GetMapping(value="insertContact.do")
	public void insertContact(Contact contact, Model model, HttpServletResponse response) {
		logger.info("insertContact() 호출");
		model.addAttribute("contact", contact);
		model.addAttribute("response", response);
		insertContactCommand.execute(model);
		// return "redirect:list.do";
	}
	
	@PostMapping(value="updateContact.do")
	public void updateContact(Contact contact, Model model, HttpServletResponse response) {
		logger.info("updateContact() 호출");
		model.addAttribute("response", response);
		model.addAttribute("contact", contact);
		updateContactCommand.execute(model);
		// return "redirect:selectContactByNo.do?no=" + contact.getNo();
	}
	
	@GetMapping(value="deleteContact.do")
	public String deleteContact(@RequestParam("no") long no, Model model) {
		logger.info("deleteContact() 호출");
		model.addAttribute("no", no);
		deleteContactCommand.execute(model);
		return "redirect:list.do";
	}
	
	
	
}
