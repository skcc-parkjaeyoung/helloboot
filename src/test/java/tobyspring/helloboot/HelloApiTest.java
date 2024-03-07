package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;


class HelloApiTest {
    @Test
    void helloApi() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> res =
                testRestTemplate.getForEntity("http://localhost:8080/hello?name={name}",
                        String.class,
                        "spring");

        //todo : status code = 200
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);

        //todo : header(Content-Type) = text/plain

        assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE))
                .startsWith(MediaType.TEXT_PLAIN_VALUE);

        //todo : body = Hello Spring
        assertThat(res.getBody()).isEqualTo("*Hello spring*");

    }

    @Test
    void helloControllerFailTest() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> res =
                testRestTemplate.getForEntity("http://localhost:8080/hello?name=",
                        String.class);
        //todo : status code = 500
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}