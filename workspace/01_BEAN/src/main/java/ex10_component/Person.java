package ex10_component;

import org.springframework.stereotype.Component;

/*
	@Component
	1. Bean으로 만들 클래스를 직접 Bean으로 생성해 준다.
	2. XML이나 Java로 별도로 생성하지 않아도 생성된다.
	3.
 
 */

@Component  // <bean id="person" class="Person" /> bean의 id는 자동으로 부여된다. (클래스이름의 첫 글자를 소문자로 변경)
public class Person {
	
	public void info() {
		System.out.println("나는 사람이다.");
	}
	
}
