package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 8.1 빈 생명주기 콜백
 * 애플리케이션이 서버에 올라올때 외부 네트워크에 미리 연결하는 테스트
 *
 * InitializingBean - afterPropertiesSet : 의존관계 주입이 끝나면 호출한다
 * DisposableBean - destroy : 빈이 종료될때 호출
 * -> AnnotationConfigApplicationContext - Closing 이 호출될때 실행된다
 *
 * */


public class NetworkClientV1 implements InitializingBean, DisposableBean {

    private String url;

    //생성할때
    public NetworkClientV1(){
        System.out.println("생성자 호출, url = " + url);
//        connect();
//        call("초기화 연결 메시지");
    }

    public void setUrl(String url){
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect : " + url);
    }
    //
    public void call(String message){
        System.out.println("call : " + url + " message = " + message);
    }

    //서비스 종료시 - 호출
    public void disconnect(){
        System.out.println("close " + url);
    }

    //의존관계 주입 끝난후 호출
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(" hello.core.lifecycle.NetworkClient.afterPropertiesSet ");
        connect();
        call("초기화 연결 메시지");
    }

    //빈이 종료될때 호출
    @Override
    public void destroy() throws Exception {
        System.out.println("hello.core.lifecycle.NetworkClient.destroy");
        disconnect();
    }
}
