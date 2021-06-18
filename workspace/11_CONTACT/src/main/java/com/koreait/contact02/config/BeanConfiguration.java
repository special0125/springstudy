package com.koreait.contact02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.koreait.contact02.command.ContactListCommand;
import com.koreait.contact02.command.DeleteContactCommand;
import com.koreait.contact02.command.InsertContactCommand;
import com.koreait.contact02.command.SelectContactByNoCommand;
import com.koreait.contact02.command.UpdateContactCommand;
import com.koreait.contact02.dao.ContactDAO;

@Configuration
public class BeanConfiguration {

	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
		dataSource.setUsername("spring");
		dataSource.setPassword("1111");
		return dataSource;
	}
	@Bean
	public JdbcTemplate template() {
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(dataSource());
		return template;
	}
	
	@Bean
	public ContactDAO contactDAO() {
		return new ContactDAO();
	}
	
	@Bean
	public ContactListCommand contactList() {
		return new ContactListCommand();
	}
	
	@Bean
	public DeleteContactCommand deleteContact() {
		return new DeleteContactCommand();
	}
	
	@Bean
	public InsertContactCommand insertContact() {
		return new InsertContactCommand();
	}
	
	@Bean 
	public SelectContactByNoCommand selectContactByNo() {
		return new SelectContactByNoCommand();
	}
	
	@Bean
	public UpdateContactCommand updateContact() {
		return new UpdateContactCommand();
	}
	
}
