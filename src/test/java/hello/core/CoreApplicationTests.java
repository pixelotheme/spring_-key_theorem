package hello.core;

import hello.core.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // 해당 어노테이션이 있으면 스프링 컨테이너에 빈을 모두 등록한후 꺼내와
	//orderService 에 주입해줄수 있어 순수한 java 테스트 코드 사용 가능
class CoreApplicationTests {

	//스프링 컨테이너에서진행 - 바로 테스트 가능하다
//	@Autowired
//	OrderService orderService;


	@Test
	void contextLoads() {
		//테스트 코드 잓헝 가능
//		orderService.~~~
	}

}
