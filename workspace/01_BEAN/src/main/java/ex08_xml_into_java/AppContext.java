package ex08_xml_into_java;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/*
	app-context8.xml을 가져오는 방법
	
	@ImportResource 에노테이션을 추가한다
*/

@ImportResource("app-context8.xml")
@Configuration
public class AppContext {

	
	@Bean
	public Gun gun1() {
		return new Gun("k2", 15);
	}
	
	@Bean
	public Soldier soldier1() {
		Soldier soldier = new Soldier();
		soldier.setName("김상사");
		soldier.setGun(gun1());
		Map<String, String> army = new HashMap<String, String>();
		army.put("부대명", "충성부대");
		army.put("사단장", "포스타");
		army.put("부대위치", "강원도 철원군");
		soldier.setArmy(army);
		return soldier;
	}
	
}
