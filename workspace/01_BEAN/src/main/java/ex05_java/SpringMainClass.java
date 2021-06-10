package ex05_java;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {
		
		// @Configuration 에노테이션을 처리할 클래스는 AnnotationConfigApplicationContext
		// AbstractApplicationContext가 부모클래스
		
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		Singer s = ctx.getBean("singer", Singer.class);
		s.info();
		
		ctx.close();
	}

}
