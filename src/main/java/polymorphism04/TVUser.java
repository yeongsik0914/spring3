package polymorphism04;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {

	public static void main(String[] args) {
		
		//��ü �������� Spring framework ���� ���� �� DI �� ���ؼ� ��ü�� ����
			// 1. XML ���Ͽ��� ��ü�� ���� ���� : 
				//src/main/resource : �ַ� ������ ���õ� ������ �����ϴ� �� (mybatis ���� ����, bean�� xml, db Connection)
					//applicationcontext.xml : Bean�� �����ϴ� ����, Spring Framework���� ����
			// 2. @ (������̼�)�� ����ؼ� ��ü�� ������ DI (��ü�� ����) <=== Spring Boot ����ϴ� ���
		
		// 1. Spring �����̳ʸ� ����
		AbstractApplicationContext factory =
				new GenericXmlApplicationContext("applicationContext.xml");
		
		
		// 2. Spring �����̳ʷ� ���� �ʿ��� ��ü�� Lookup �Ѵ�. : DI (��ü�� ����) 
		TV tv = (TV) factory.getBean("tv");
		
		
		tv.powerOn();
		tv.powerOff();
		tv.volumeUp();
		tv.volumedown();
	}

}
