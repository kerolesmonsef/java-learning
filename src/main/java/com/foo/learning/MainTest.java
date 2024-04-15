package com.foo.learning;

import com.foo.learning.repository.UserRepository;
import com.foo.learning.service.JWTService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.foo.learning"}) // Adjust the package if necessary
public class MainTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainTest.class);
        JWTService service = context.getBean(JWTService.class);
        UserRepository userRepository = context.getBean(UserRepository.class);

    }
}
