package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
//        ApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
         /**기본 ApplicationContext 에서 close 를 지원하지 않고
        하위 인터페이스 ConfigurableApplicationContext 에서 지원한다
         ApplicationContext -> ConfigurableApplicationContext -> AbstractApplicationContext ->
         -> GenericApplicationContext -> AnnotationConfigApplicationContext
          */
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        //직접 스프링 컨테이너를 닫아준다
        ac.close();
    }

    //설정 정보 -
    @Configuration
    static class LifeCycleConfig{

        //빈 등록
        // destroyMethod 기본 값 : (inferred) -> AbstractBeanDefinition.INFER_METHOD
//        @Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient(){
            //생성자 호출시 url 을 안넣어줘서 null -
            //생성할때 connect, call 메서드 모두 실행시킴
            NetworkClient networkClient = new NetworkClient();

            //생성후 값을 넣어줘야 할때 어떻게 해야하나?
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
