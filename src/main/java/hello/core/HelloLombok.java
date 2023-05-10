package hello.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자 자동 생성
@ToString
public class HelloLombok {


    private String name;
    private int age;

    public static void main(String[] args) {
       HelloLombok helloLombok = new HelloLombok();
       helloLombok.setAge(11);

       int age = helloLombok.getAge();
        System.out.println("helloLombok = " + helloLombok);
    }
}
