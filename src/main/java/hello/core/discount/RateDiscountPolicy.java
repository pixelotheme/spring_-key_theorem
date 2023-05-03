package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

//구현체
@Component
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price){
        if(member.getGrade() == Grade.VIP){
            //10% 할인해준다 - 맞는지 항상 확실하게 테스트 해줘야한다
            return price * discountPercent / 100;
        }else{
            return 0;
        }
    }

}
