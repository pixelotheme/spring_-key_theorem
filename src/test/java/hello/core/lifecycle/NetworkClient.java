package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 8.1 빈 생명주기 콜백
 * 애플리케이션이 서버에 올라올때 외부 네트워크에 미리 연결하는 테스트
 *
 * javax - java 언어에서 공식적으로 지원하다 보니 spring 을 쓰지않아도 된다
 * */


public class NetworkClient {

    private String url;

    //생성할때
    public NetworkClient(){
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
    @PostConstruct
    public void init() throws Exception {
        System.out.println("init ");
        connect();
        call("초기화 연결 메시지");
    }

    //빈이 종료될때 호출
    @PreDestroy
    public void close() throws Exception {
        System.out.println("close");
        disconnect();
    }
}
