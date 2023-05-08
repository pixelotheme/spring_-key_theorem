package hello.core.autowired;


import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;


//자동 주입 옵션 처리
public class AutowiredTest {


    @Test
    void AutowiredOption(){
        //TestBean 클래스를 스프링 빈으로 바로 등록시킨다
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{

        //자동 주입 대상이 없으면 수정자(setter) 메서드 자체가 호출이 안된다

        //Member 는 스프링 컨테이너 관리 대상이 아니라 member로 테스트
//        @Autowired 기본값이 true 라 바로 에러난다
        //호출이 안된다
        @Autowired(required = false)
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }
        //@Nullable : 생성자, 수정자 호출은 되는데 Null이 들어간다
        @Autowired
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("noBean2 = " + noBean2);
        }
        //스프링 빈이 없으면 Optional 안에 empty로 반환된다
        //Optional.empty
        @Autowired
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
