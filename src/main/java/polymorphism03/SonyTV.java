package polymorphism03;

public class SonyTV implements TV {

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
		System.out.println("SonyTV - ������ ���Դϴ�.");
	}

	@Override
	public void volumedown() {
		System.out.println("SonyTV - ������ ����ϴ�.");
	}

}
