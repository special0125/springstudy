package ex05_java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
	@configuration
	: 안녕. 나는 bean(객체)을 만들어 주는 java 클래스야.
*/

@Configuration
public class AppContext {

	// Bean을 만드는 메소드를 하나씩 만든다.
	// 메소드 -> Bean 생성
	
	
	@Bean // 나는 Bean을 만드는 메소드이다.
	// 반환타입 : Song -> <bean class="Song">
	// 메소드명 : song -> <bean id="song>
	public Song song() {
		Song s = new Song();
		s.setTitle("hello");  // <property name="title">
		s.setGenre("balad");  // <property name="genre">
		return s;
	}
	
	@Bean(name="singer")  // <bean id="siger">
	// 반환타입: Singer -> <bean class="Singer">
	public Singer xyz() {  // 메소드명은 사용되지 않는다.
		return new Singer("adel", song());  // <constructor-arg>
	}
	
}
