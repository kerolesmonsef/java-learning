package com.foo.learning.listener;

import com.foo.learning.event.UserRegistrationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterListener implements ApplicationListener<UserRegistrationEvent> {
    @Override
    public void onApplicationEvent(UserRegistrationEvent event) {
        System.out.println("User Registered : " + event.getUser().getName());
    }
}
