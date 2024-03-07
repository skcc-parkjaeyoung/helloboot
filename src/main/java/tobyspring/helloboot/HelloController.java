package tobyspring.helloboot;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class HelloController {
    //HelloService helloService = new SimpleHelloService();
    //todo : assembler(Spring Container) 가 생성자 파라미터로 HelloService 를 주입
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello(String name) {
        if (name == null || name.trim().length() == 0)
            throw new IllegalArgumentException("name 이 없습니다");

        return helloService.sayHello(Objects.requireNonNull(name));
    }

/*    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("applicationContext = " + applicationContext);
        this.applicationContext = applicationContext;

    }*/
}