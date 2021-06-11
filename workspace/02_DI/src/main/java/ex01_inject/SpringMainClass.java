package ex01_inject;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("app-context1.xml");
		SelectListCommand s = ctx.getBean("selectListCommand", SelectListCommand.class);
		s.execute();
		ctx.close();
	
		
	}

}
