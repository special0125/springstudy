package quiz01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("quiz01.xml");
		
		EngineerCalculator e1 = ctx.getBean("engineerCalculator", EngineerCalculator.class);
		e1.execute();
		
		ctx.close();
		
		AbstractApplicationContext ctx2 = new AnnotationConfigApplicationContext(AppContext.class);
		
		EngineerCalculator e2 = ctx2.getBean("engineerCalculator", EngineerCalculator.class);
		e2.execute();
		
		ctx2.close();
	}

}
