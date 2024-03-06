package tobyspring.helloboot;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class HellobootApplication {
    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean(HelloController.class);
        applicationContext.refresh();


        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {

            servletContext.addServlet("frontController", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp)
                        throws IOException {

                    if (req.getRequestURI().equals("/hello") &&
                            req.getMethod().equals(HttpMethod.GET.name())) {

                        String name = req.getParameter("name");

                        HelloController helloController = applicationContext.getBean(HelloController.class);

                        String ret = helloController.hello(name);

                        //resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                        resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                        resp.getWriter().println(ret);

                    } else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                        resp.getWriter().println("NOT FOUND~~!");
                    }

                }
            }).addMapping("/*");
        });
        webServer.start();
    }
}
