package polymorphism05;

import org.springframework.stereotype.Component;

@Component("speaker")	// BossSpeaker speaker = new BossSpeaker();
public class BossSpeaker implements Speaker {

	@Override
	public void volumeUp() {
		System.out.println("BossSpeaker - ������");
	}

	@Override
	public void volumedown() {
		System.out.println("BossSpeaker - �����ٿ�");
	}

}
