package com.milap.context;

import com.milap.tambolaticketgenerator.service.SessionValidationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SessionValidationServiceContext {
    @Bean
    public SessionValidationService getSessionValidationService() {
        return new SessionValidationService();
    }
}
