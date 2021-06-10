package quiz04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

	
	@Bean
	public Lecture lecture2() {
		
		return new Lecture("파이썬", "파이썬왕");
	}
	
	@Bean
	public Student student2() {
		
		return new Student("윤학생", lecture2());
	}
	
}
