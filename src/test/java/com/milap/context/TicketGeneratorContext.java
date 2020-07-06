package com.milap.context;

import com.milap.tambolaticketgenerator.service.SessionValidationService;
import com.milap.tambolaticketgenerator.service.TicketGenerator;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicketGeneratorContext {

    @MockBean
    SessionValidationService sessionValidationService;

    @Bean
    public TicketGenerator getTicketGenerator() {
        Mockito.when(sessionValidationService.addTicketToSession(Mockito.anyString(), Mockito.any())).thenReturn(true);
        return new TicketGenerator(sessionValidationService);
    }

}
