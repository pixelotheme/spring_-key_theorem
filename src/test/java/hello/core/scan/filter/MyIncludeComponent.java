package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //어노테이션을 어디에 붙는가? Type = 클래스 레벨을 추가로 본다, FIELD : 필드 레벨에 붙는다
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
    // 해당 어노테이션이 붙으면 컴포넌트 스캔의 대상이 된다
}
