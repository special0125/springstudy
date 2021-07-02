package com.koreait.myProject.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.koreait.myProject.command.EmailAuthCommand;
import com.koreait.myProject.command.IdCheckCommand;
import com.koreait.myProject.command.JoinCommand;
import com.koreait.myProject.command.LeaveCommand;
import com.koreait.myProject.command.LoginCommand;
import com.koreait.myProject.command.LogoutCommand;
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
	
	
	
	
}