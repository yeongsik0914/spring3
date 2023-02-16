package polymorphism05;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component("appleTV")
public class AppleTV implements TV {

	@Resource(name = "speaker2")	//@Autowired + @Qualifier
	private Speaker speaker;
	
	@Override
	public void powerOn() {
		System.out.println("AppleTV - ������ �մϴ�.");

	}

	@Override
	public void powerOff() {
		System.out.println("AppleTV - ������ ���ϴ�.");
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
