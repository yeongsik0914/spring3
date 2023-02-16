package polymorphism01;

public class TVUser {

	public static void main(String[] args) {
		
		//������(���յ��� ����) : ���� ������ ��ư� �ϴ� �ڵ��̴�.
			//1. �ڵ��� ���յ��� �����ϰ� �ؼ� ���� ������ ���� �ϴ� �ڵ�
				// a. �������̽��� ����ؼ� ������ ��ü���� ������ �޼ҵ� �̸��� ���
				// b. ��ü ������ �����ڰ� �ڵ峻�ο��� ������ ���� �ƴ϶� Spring �����ӿ�ũ���� ���� ( IoC )
					// ��ü�� �ʿ��� ���� ���� ( DI )
					// Bean (��ü) : �����ӿ�ũ���� �����ؼ� ���� 
		
		SamsungTV tv = new SamsungTV();
		tv.powerOn();
		tv.powerOff();
		tv.volumeUp();
		tv.volumeDown();
		
		System.out.println("=====================");
		//TV ����ڰ� Samsung TV���� LgTV�� �ٲ��.
		LgTv tv2 = new LgTv();
		tv2.turnOn();
		tv2.turnOff();
		tv2.soundUp();
		tv2.sounddown();
	}

}
