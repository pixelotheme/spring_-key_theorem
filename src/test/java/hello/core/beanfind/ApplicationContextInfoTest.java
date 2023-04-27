package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//junit5 부터는 public 설정을 하지 않아도 된다
class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        //빈 이름 가져오기
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        //iter + tab  단축키
        for(String beanDefinitionName: beanDefinitionNames){
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("beanDefinitionName = " + beanDefinitionName + "object : " + bean);
        }
    }
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        //빈 이름 가져오기
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        //iter + tab  단축키
        for(String beanDefinitionName: beanDefinitionNames){
            //빈의 메타데이터의 정보를 꺼낼수 있다
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);


            // ROLE_APPLICATION - 내가 애플리케이션 개발을 위해 등록한 빈 또는 외부라이브러리 ( 스프링이 내부에서 사용하는 빈 이 아닌 )
             if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                 Object bean = ac.getBean(beanDefinitionName);
                 System.out.println("beanDefinitionName = " + beanDefinitionName + " object : " + bean);
             }
            //ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE){
                 Object bean = ac.getBean(beanDefinitionName);
                 System.out.println("beanDefinitionName = " + beanDefinitionName + " object : " + bean);
             }

        }
    }
}
