package com.toby.suntory;

import com.toby.suntory.user.service.DummyMailSender;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailSender;

@TestConfiguration
public class TestConfig {

    @Bean
    public MailSender mailSender() {
        return new DummyMailSender();
    }
}
