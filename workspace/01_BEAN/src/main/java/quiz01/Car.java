package quiz01;

public class Car {

	private String model;
	private int price;
	
	public Car(String model, int price) {
		super();
		this.model = model;
		this.price = price;
	}
	
	public void info() {
		System.out.println("모델명: " + model);
		System.out.println("가격: " + price);
	}
	
}
