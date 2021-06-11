package quiz02;

import org.springframework.beans.factory.annotation.Autowired;

public class TV {
	private int ch;  // 0 ~ 100
	private Speaker speaker;
	
	@Autowired
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	
	public void volUp() {
		speaker.volumeUp();
	}
	public void volDown() {
		speaker.volDown();
	}
	public void chUp() {
		ch++;
		// ch %= 101;
		if (ch > 100) {
			ch = 0;
		}
		System.out.println("현재 채널: " + ch);
	}
	public void chDown() {
		ch--;
		if (ch < 0) {
			ch = 100;
		}
		System.out.println("현재 채널: " + ch);
	}
	
}
