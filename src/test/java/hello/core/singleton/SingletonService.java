package hello.core.singleton;

public class SingletonService {

    //자기자신을 내부에 private 으로 가지고 있는데
    //static 으로 선언했다 = 클래스 변수 = 클래스가 메모리에 로드될때 한번만 실행되며
    //인스턴스 변수와는 다르게 항상 같은 값을 갖는다
    // + final 은 변경할 수 없는 상수, 오버라이딩x, 자손클래스 정의 x
    private static final SingletonService instance = new SingletonService();

    //조회 -- 미리 생성해둔 인스턴스를 반환해준다
    public static SingletonService getInstance(){
        return instance;
    }

    //private 생성자로 누군가가 강제로 new 하지 못하게 막아준다
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출출");    }

    //만약 누군가가 new 로 생성하면 어떻게 되는거지?(현재 같은 클래스라 호출이 가능하지만...)
    //외부 에서 테스트하면 알수있다
    // 결 방법 : private 생성자를 사용한다
//    public static void main(String[] args) {
//        SingletonService singletonService1 = new SingletonService();
//        SingletonService singletonService2 = new SingletonService();
//
//    }




}
