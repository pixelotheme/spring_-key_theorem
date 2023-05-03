package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //어노테이션을 어디에 붙는가? Type = 클래스 레벨을 추가로 본다, FIELD : 필드 레벨에 붙는다
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
    //해당 어노테이션이 붙으면 스캔대상에서 제외한다
}
