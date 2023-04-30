package hello.core.xml;


import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlAppContext {

    @Test
    void xmlAppContext(){
//        GenericXmlApplicationContext genericXmlApplicationContext = new GenericXmlApplicationContext("appConfig.xml");
        //자바 코드가 아닌것은 resources 에 넣어준다
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

    }

}
