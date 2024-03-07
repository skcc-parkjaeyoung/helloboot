package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleHelloServiceTest {
    @Test
    void simpleHelloService(){
        SimpleHelloService simpleHelloService = new SimpleHelloService();

        String res = simpleHelloService.sayHello("test");
        assertThat(res).isEqualTo("Hello test");
    }

    @Test
    void helloDecorator(){
        HelloDecorator decorator = new HelloDecorator(name -> name);
        String ret = decorator.sayHello("Test");

        Assertions.assertThat(ret).isEqualTo("*Test*");
    }
}