package polymorphism05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SonyTV implements TV {

	@Autowired				//Speaker Ÿ���� ��ü�� Spring framework���� �˻��ؼ� ���� (DI)
	@Qualifier("speaker2")	//BossSpeaker(speaker) , SonySpeaker(speaker2) <== �� ���� ��ü ��� Speaker Ÿ���� ������
	private Speaker speaker;
	
	@Override
	public void powerOn() {
		System.out.println("SonyTV - ������ �մϴ�.");
	}

	@Override
	public void powerOff() {
		System.out.println("SonyTV - ������ ���ϴ�.");
	}

	@Override
	public void volumeUp() {
		speaker.volumeUp();
	}

	@Override
	public void volumedown() {
		speaker.volumedown();
	}

}
