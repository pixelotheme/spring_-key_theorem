package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUserPrototype(){
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1= clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        //프로토타입 빈은 처음 ClientBean 생성시 DI 적용 시점에 생성되면서 주입시켜
        //같은 prototypeBean을 사용한다
        //ClientBean을 호출해도 싱글톤으로 새로 생성되지 않기 때문에
        // 이미 prototypeBean으로 주입된 ClientBean을 사용한다다
       ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);

    }

    @Scope("singleton")
//    @RequiredArgsConstructor - 생성자 주입 적용 바로된다
    static class ClientBean{
//        private final PrototypeBean prototypeBean;

//
//        @Autowired // 생성자주입은 하나면 Autowired 삭제가능
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

//        @Autowired
//        ApplicationContext applicationContext;

//        @Autowired // 테스트라 필드주입으로 넣어준다
//        private ObjectProvider<PrototypeBean> prototypeBeans;

        @Autowired // javax.inject.Provider
        private Provider<PrototypeBean> prototypeBeans;

        public int logic(){
            //이렇게 계속 호출하면 새로 생성시킨다
//            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
            //해당하는 빈을 스프링 컨테이너에 요청해 가져온다
//            PrototypeBean prototypeBean = prototypeBeans.getObject();
            //provider사용
            PrototypeBean prototypeBean = prototypeBeans.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy ");
        }
    }
}
