package polymorphism03;

public class BeanFactory {

	// Spring framework �� ������ ���� ������ ���� (Factory ����)
	// 	������ ���� : ���� ������ ���� �� �� �ִ� �ڵ带 �ۼ��� �� �ִ� ����� ����.
			// Spring Framework �� ������ ������ ����ִ�.
	// Bean : ��ü
	// BeanFactory : ��ü�� �ܺο��� �����ؼ� ������� ��ü�� ���� : 
	
	public Object getBean (String beanName) {
		
		// �����ܿ��� ������ Ŭ������ ��ü�� �����ؼ� �����ָ� ��
		if (beanName.equals("samsung")) {
			return new SamsungTV();
		} else if (beanName.equals("lg")) {
			return new LgTV();
		} else if (beanName.equals("sony")) {
			return new SonyTV();
		} else if (beanName.equals("apple")) {
			return new AppleTV();
		}
		return null;
	}
}
