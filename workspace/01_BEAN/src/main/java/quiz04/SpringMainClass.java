package quiz04;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("quiz04.xml");
		
		Student s1 = ctx.getBean("student1", Student.class);
		Student s2 = ctx.getBean("student2", Student.class);
		
		s1.info();
		s2.info();
		
		ctx.close();
		
		
	}

}
