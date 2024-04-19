package com.foo.learning.event;

import com.foo.learning.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
public class UserRegistrationEvent extends ApplicationEvent {
    private User user;

    public UserRegistrationEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
