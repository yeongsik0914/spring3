package polymorphism05;

import org.springframework.stereotype.Component;

@Component("speaker2")
public class SonySpeaker implements Speaker {

	@Override
	public void volumeUp() {
		System.out.println("SonySpeaker - ������");
	}

	@Override
	public void volumedown() {
		System.out.println("SonySpeaker - �����ٿ�");
	}

}
