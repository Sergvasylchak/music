package com.music.lbry.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SystemConfig {
    private static Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        SystemConfig.environment = environment;
    }
}
