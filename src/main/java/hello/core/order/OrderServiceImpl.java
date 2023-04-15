package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    //메모리 저장소를 주입
//    private final MemberRepository memberRepository = new MemoryMemberRepository();


    //정량적 할인 정택 주입
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //정률 할인 정책 주입
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    //final 을 지워줬다 -> 무조건 값이 할당 되어야 하기 떄문
    //DIP 원칙 준수 -> 그러나 값이 없어 null 예외 발생 - discountPolicy. 점 찍는순간 할당된것이 없어서 예외
   private final DiscountPolicy discountPolicy;
   private final MemberRepository memberRepository;

   //생성자 주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice){
        Member member = memberRepository.findById(memberId);
        //Service 에서도 무었을 어떻게 할인하는지 모른다 - discountPolicy 에서 할인 정책을 모두 수행한다
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

}
