package polymorphism05;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser3 {

	public static void main(String[] args) {
		
		//��ü ������ Spring framework ���� ���� �� DI �� ���ؼ� ��ü�� ����
			// 1. XML ���Ͽ��� ��ü�� ���� ���� : 
				//src/main/resource : �ַ� ������ ���õ� ������ �����ϴ� �� (mybatis ���� ����, bean�� xml, db Connection)
					//applicationcontext.xml : Bean�� �����ϴ� ����, Spring Framework���� ����
			// 2. @ (������̼�)�� ����ؼ� ��ü�� ������ DI (��ü�� ����) <=== Spring Boot ����ϴ� ���
				//a. @ (������̼�) �� ����� �� �ֵ��� ������ �ʿ���.
					//applicationContext.xml : Bean ���� ����, ������̼��� ����� �� �ֵ��� ���� �ʿ�. (context ���)
						//<context:component-scan base-package="polymorphism05"></context:component-scan>
				//b. Ŭ���� ���� @Component ������̼��� �ٿ��� ��ü�� ���� <== Ŭ���� �ܺο��� �Ҵ�
						// �پ��� ������ Ŭ������ �����ϱ� ������ Ŭ������ ������ ���� �����̸����� ������̼��� ����.
					//@Component	: �Ϲ����� Ŭ������ Bean (��üȭ) ����
					//@Service		: ����Ͻ� ������ ó���ϴ� Ŭ������ Bean ����
					//@Repository	: ������ ���̽� ������ ó���ϴ� DAO Ŭ������ ����
					//@Controller	: ����� ��û�� Controller Ŭ������ �ٿ��ִ� 
		
				//c. Spring framework ���� ������ ��ü�� ������ ����(DI)�ϴ� ������̼�
						// Ŭ���� ���ο��� Ŭ���� ������ �Ҵ�.
					//@Autowired : �ش� Ÿ���� ��ü�� ã�Ƽ� �ڵ����� �Ҵ��ϴ� ������̼� (Ŭ���� ���� ���� �Ҵ�)
							// �ش� Ÿ���� ��ü�� ������ ������ ��� ������ �߻�
					//@Qualifier : Ư�� ��ü �̸��� ����ؼ� �ҷ����� ��, @Autowired �� ���� �����
		
					//@Inject 	: �ڹٿ��� �����Ǵ� ������̼�, @Autowired �� ������ ������̼� (Ÿ������ ��ü�� ������ �´�.)
					//@Resource : �ڹٿ��� �����Ǵ� ������̼�, <== �̸����� ��ü�� �ĺ�
							// @Autowired + @Qualifier �� �ϳ��� �����.
		
		// 1. Spring �����̳ʸ� ����
		AbstractApplicationContext factory =
				new GenericXmlApplicationContext("applicationContext.xml");
		
		
		// 2. Spring �����̳ʷ� ���� �ʿ��� ��ü�� Lookup �Ѵ�. : DI (��ü�� ����) 
		TV tv = (TV) factory.getBean("appleTV");
		
		
		tv.powerOn();
		tv.powerOff();
		tv.volumeUp();
		tv.volumedown();
	}

}
