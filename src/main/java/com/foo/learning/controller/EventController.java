package com.foo.learning.controller;

import com.foo.learning.entity.User;
import com.foo.learning.event.UserRegistrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventController {
    private final ApplicationEventPublisher eventPublisher;

    @GetMapping("/allow/event")
    public String fireEvent() {
        User user = new User("user", "password");

        eventPublisher.publishEvent(new UserRegistrationEvent(this, user));
        return "event fired";
    }
}
