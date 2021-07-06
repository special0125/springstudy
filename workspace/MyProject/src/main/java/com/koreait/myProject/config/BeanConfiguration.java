package com.koreait.myProject.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.koreait.myProject.Command.ChangePwCommand;
import com.koreait.myProject.Command.EmailAuthCommand;
import com.koreait.myProject.Command.FindIdCommand;
import com.koreait.myProject.Command.FindPwCommand;
import com.koreait.myProject.Command.IdCheckCommand;
import com.koreait.myProject.Command.JoinCommand;
import com.koreait.myProject.Command.LeaveCommand;
import com.koreait.myProject.Command.LoginCommand;
import com.koreait.myProject.Command.LogoutCommand;
import com.koreait.myProject.galleryCommand.InsertBoardCommand;
import com.koreait.myProject.galleryCommand.SelectAllCommand;
import com.koreait.myProject.galleryCommand.SelectByNoCommand;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class BeanConfiguration {

	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
		hikariConfig.setJdbcUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
		hikariConfig.setUsername("spring");
		hikariConfig.setPassword("1111");
		return hikariConfig;
	}
	
	@Bean
	public HikariDataSource hikariDataSource() {
		return new HikariDataSource(hikariConfig());
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(hikariDataSource());
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/koreait/myProject/dao/*.xml"));
		return sqlSessionFactory.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	@Bean
	public IdCheckCommand idCheckCommand() {
		return new IdCheckCommand();
	}
	
	@Bean
	public EmailAuthCommand emailAuthCommand() {
		return new EmailAuthCommand();
	}
	
	@Bean
	public JoinCommand joinCommand() {
		return new JoinCommand();
	}
	
	@Bean
	public LoginCommand loginCommand() {
		return new LoginCommand();
	}
	
	@Bean 
	public LogoutCommand logoutCommand() {
		return new LogoutCommand();
	}
	
	@Bean
	public LeaveCommand leaveCommand() {
		return new LeaveCommand();
	}
	
	@Bean
	public FindIdCommand findIdCommand() {
		return new FindIdCommand();
	}
	
	@Bean
	public FindPwCommand findPwCommand() {
		return new FindPwCommand();
	}
	
	@Bean
	public ChangePwCommand changePwCommand() {
		return new ChangePwCommand();
	}
	
	@Bean
	public SelectAllCommand selectAllCommand() {
		return new SelectAllCommand();
	}
	
	@Bean
	public SelectByNoCommand selectByNoCommand() {
		return new SelectByNoCommand();
	}
	
	@Bean
	public InsertBoardCommand insertBoardCommand() {
		return new InsertBoardCommand();
	}
	
}