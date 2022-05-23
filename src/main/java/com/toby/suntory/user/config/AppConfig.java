package com.toby.suntory.user.config;

import com.toby.suntory.user.service.TxProxyFactoryBean;
import com.toby.suntory.user.service.UserService;
import com.toby.suntory.user.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class AppConfig {

    private final PlatformTransactionManager transactionManager;
    private final UserService userService;
    private final UserServiceImpl target;

    public AppConfig(PlatformTransactionManager transactionManager, UserService userService, UserServiceImpl target) {
        this.transactionManager = transactionManager;
        this.userService = userService;
        this.target = target;
    }

    @Bean
    public TxProxyFactoryBean userService() {
        TxProxyFactoryBean txProxyFactoryBean = new TxProxyFactoryBean();
        txProxyFactoryBean.setTarget(target);
        txProxyFactoryBean.setPattern("upgradeLevels");
        txProxyFactoryBean.setServiceInterface(UserService.class);
        return txProxyFactoryBean;
    }
}
