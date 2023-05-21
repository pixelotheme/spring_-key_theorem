package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 빈스코프 1강
 * 싱글톤 테스트트 * */

public class SingletonTest {

    @Test
    void singletoneBeanFind(){
        //SingletonBean 이 컴포넌트 스캔 대상이되어 bean으로 자동 등록된다
        //@Configuration, @Component, @Bean 따로 안붙여줘도 된다
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);
        // == 주소값 비교
        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);
        singletonBean1.destroy();
        singletonBean2.destroy();
        ac.close();
    }

    @Scope("singleton") // singleton 없어도 자동으로 들어간다
    static class SingletonBean{

        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init 호출");
        }
        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean.destroy 호출");
        }
    }
}
