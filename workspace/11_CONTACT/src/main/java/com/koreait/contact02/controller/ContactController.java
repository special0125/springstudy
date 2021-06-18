package com.koreait.contact02.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.contact02.command.ContactListCommand;
import com.koreait.contact02.command.DeleteContactCommand;
import com.koreait.contact02.command.InsertContactCommand;
import com.koreait.contact02.command.SelectContactByNoCommand;
import com.koreait.contact02.command.UpdateContactCommand;
import com.koreait.contact02.config.BeanConfiguration;
import com.koreait.contact02.dto.Contact;

@Controller
public class ContactController {
	
	AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);  // ctx를 사용하지 않아도 됨 @Autowired도 사용가능
	
	@GetMapping(value="/")  // value={ "/", "selectContactList.do"} 한번에 넘기기
	public String index() {
		return "index";
	}
	
	@GetMapping(value="list.do") 
	public String list(Model model) {
		ContactListCommand contactListCommand = ctx.getBean("contactList", ContactListCommand.class);
		contactListCommand.execute(model);
		return "contact/list";
	}
	
	@GetMapping(value="selectContactByNo.do")
	public String selectContactByNo(@RequestParam("no") long no, Model model) {
		model.addAttribute("no", no);
		SelectContactByNoCommand selectContactByNoCommand = ctx.getBean("selectContactByNo", SelectContactByNoCommand.class);
		selectContactByNoCommand.execute(model);
		return "contact/view";
	}
	
	@GetMapping(value="insertContactPage.do")
	public String insertContactPage() {
		return "contact/insert";
	}
	
	@GetMapping(value="insertContact.do")
	public void insertContact(Contact contact, Model model, HttpServletResponse response) {
		model.addAttribute("contact", contact);
		model.addAttribute("response", response);
		InsertContactCommand insertContactCommand = ctx.getBean("insertContact", InsertContactCommand.class);
		insertContactCommand.execute(model);
		// return "redirect:list.do";
	}
	
	@PostMapping(value="updateContact.do")
	public void updateContact(Contact contact, Model model, HttpServletResponse response) {
		model.addAttribute("response", response);
		model.addAttribute("contact", contact);
		UpdateContactCommand updateContactCommand = ctx.getBean("updateContact", UpdateContactCommand.class);
		updateContactCommand.execute(model);
		// return "redirect:selectContactByNo.do?no=" + contact.getNo();
	}
	
	@GetMapping(value="deleteContact.do")
	public String deleteContact(@RequestParam("no") long no, Model model) {
		model.addAttribute("no", no);
		DeleteContactCommand deleteContactCommand = ctx.getBean("deleteContact", DeleteContactCommand.class);
		deleteContactCommand.execute(model);
		return "redirect:list.do";
	}

}
