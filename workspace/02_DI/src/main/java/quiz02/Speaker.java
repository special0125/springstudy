package quiz02;

public class Speaker {

	private int vol;  // 0 ~ 100
	
	
	public void volumeUp() {
		vol++;
		if (vol > 100) {
			vol = 100;
		}
		System.out.println("현재 볼륨: " + vol);
	}
	public void volDown() {
		vol--;
		if (vol < 0) {
			vol = 0;
		}
		System.out.println("현재 볼륨: " + vol);
	}
	
}
