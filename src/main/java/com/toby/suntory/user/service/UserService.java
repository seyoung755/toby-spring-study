package com.toby.suntory.user.service;

import com.toby.suntory.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    void add(User user);
    void upgradeLevels();
}
