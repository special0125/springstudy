package ex01_inject;

public class SpringMainClass {

	public static void main(String[] args) {
		
		SelectListCommand selectListCommand = new SelectListCommand();
		selectListCommand.execute();  // dao가 자동으로 주입되지 않았다면 NullPointerException 발생
		
	}

}
