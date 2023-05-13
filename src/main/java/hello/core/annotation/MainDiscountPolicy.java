package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * Target - 필드, 메소드, 파라미터, 타입, 어노테이션 에 사용가능
 * Retention - 어노테이션이 어디에 적용되는지, 언제까지 어노테이션 소스가 유지 되는지
 * Inherited - 자식 클래스에서 부모 클래스의 어노테이션 사용 가능
 * */

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}
