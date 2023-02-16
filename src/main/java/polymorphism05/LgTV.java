package polymorphism05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("tv2")	//LgTv tv2 = new LgTV();
public class LgTV implements TV {

	@Autowired	// Speaker Ÿ���� ��ü�� ã�Ƽ� speaker ������ ����
	private Speaker speaker;
	
	@Override
	public void powerOn() {
		System.out.println("LgTV - ������ �մϴ�.");

	}

	@Override
	public void powerOff() {
		System.out.println("LgTV - ������ ���ϴ�.");

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
