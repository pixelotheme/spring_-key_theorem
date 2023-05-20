package hello.core.lifecycle;

/**
 * 8.1 빈 생명주기 콜백
 * 애플리케이션이 서버에 올라올때 외부 네트워크에 미리 연결하는 테스트
 *
 * @Bean에 설정 정보를 추가
 * */


public class NetworkClientV2 {

    private String url;

    //생성할때
    public NetworkClientV2(){
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
    public void init() throws Exception {
        System.out.println("init ");
        connect();
        call("초기화 연결 메시지");
    }

    //빈이 종료될때 호출
    public void close() throws Exception {
        System.out.println("close");
        disconnect();
    }
}
