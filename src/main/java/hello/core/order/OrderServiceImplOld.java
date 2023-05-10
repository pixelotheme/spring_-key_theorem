package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 롬북 적용
 * */

@RequiredArgsConstructor // final이 붙은 필수값의 생성자 주입 코드를 그대로 만들어준다
@Component
public class OrderServiceImplOld implements OrderService{

    //메모리 저장소를 주입
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //정량적 할인 정택 주입
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //정률 할인 정책 주입
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    //final 을 지워줬다 -> 무조건 값이 할당 되어야 하기 떄문
    //DIP 원칙 준수 -> 그러나 값이 없어 null 예외 발생 - discountPolicy. 점 찍는순간 할당된것이 없어서 예외
//    final = 초기화를 하거나 생성자 호출시 에만 들어가 불변성을 지킬수 있다
    // 무조건 값이 할당 되어야 해서 컴파일 시점에 오류난다
    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;



    //v2 그러나 Autowired를 필드 주입을 권장하지 않는다
//    @Autowired private DiscountPolicy discountPolicy;
//    @Autowired private MemberRepository memberRepository;

//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }
//
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

//---------------수정자 주입
//    @Autowired(required = false)
//    public void setMemberRepository(MemberRepository memberRepository){
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy){
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }

    //------------이렇게 2개의 생성자가 있다면 autowired 지정해주어야한다
//    public OrderServiceImpl() {
//
//    }

    //@RequiredArgsConstructor 로 대체
    //생성자 주입
//    @Autowired // 여러 의존관계도 한번에 주입 받을수 있다
    //생성자가 하나만 있다면 Autowired 지정하지 않아도 된다
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        System.out.println("1memberRepository = " + memberRepository);
//        System.out.println("1discountPolicy = " + discountPolicy);
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

//    이런식으로 의존관계 주입을 수정할수 있게 만들면 안된다
//    public void setDiscountPolicy(DiscountPolicy discountPolicy){
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice){
        Member member = memberRepository.findById(memberId);
        //Service 에서도 무었을 어떻게 할인하는지 모른다 - discountPolicy 에서 할인 정책을 모두 수행한다
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
