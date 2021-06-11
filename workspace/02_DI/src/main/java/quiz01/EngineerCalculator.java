package quiz01;

import org.springframework.beans.factory.annotation.Autowired;

public class EngineerCalculator {

	private int a;
	private int b;
	private Calculator calculator;
	
	public EngineerCalculator() {}
	public EngineerCalculator(int a, int b, Calculator calculator) {
		super();
		this.a = a;
		this.b = b;
		this.calculator = calculator;
	}
	
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public Calculator getCalculator() {
		return calculator;
	}
	@Autowired
	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}





	public void execute() {
		calculator.add(a, b);
		calculator.subtract(a, b);
		calculator.multiply(a, b);
		calculator.divide(a, b);
	}
	
}
