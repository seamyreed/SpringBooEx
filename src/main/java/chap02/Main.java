package chap02;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Created by hyojeongyoon on 2016. 8. 9..
 */
public class Main {
    public static void main(String[] args) {
        // 1. 설정 정보를 이용해서 빈 객체를 생성한다.
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext("classpath:applicationContext.xml");

        // 2. 빈 객체를 제공한다. 빈 객체를 찾는다.
        Greeter greeter = ctx.getBean("greeter", Greeter.class);
        String message = greeter.greet("윤효정");
        System.out.println(message);

        ctx.close();
    }
}
