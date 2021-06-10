package quiz05;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@ImportResource("quiz05.xml")
@Configuration
public class AppContext {

	@Bean
	public Person husband1() {
		return new Person("브레드", "남편");
	}
	@Bean
	public Person wife1() {
		return new Person("엘리스", "아내");
	}
	
	@Bean
	public HoneyMoon honeyMoon1() {
		return new HoneyMoon("하와이", husband1(), wife1());
		
	}
	
}
