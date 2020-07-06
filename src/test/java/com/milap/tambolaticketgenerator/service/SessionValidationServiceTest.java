package com.milap.tambolaticketgenerator.service;

import com.milap.context.SessionValidationServiceContext;
import com.milap.tambolaticketgenerator.model.Ticket;
import com.milap.tambolaticketgenerator.util.TicketUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SessionValidationServiceContext.class)
public class SessionValidationServiceTest {

    @Autowired
    SessionValidationService service = null;

    private static final String ticket1Str = "[[2,10,0,31,0,0,60,73,0],\n" +
            "[6,16,27,0,0,50,0,0,80],\n" +
            "[9,0,28,32,40,0,0,75,0]]";

    private static final String identicalToTicket1Str = "[[2,10,0,31,0,0,60,73,0],\n" +
            "[6,16,27,0,0,50,0,0,80],\n" +
            "[9,0,28,32,40,0,0,75,0]]";

    private static final String similarToTicket1Str = "[[2,10,0,31,0,0,60,73,0],\n" +
            "[6,0,27,0,32,50,0,0,80],\n" +
            "[9,16,28,0,40,0,0,75,0]]";

    private static final String ticket2Str = "[[3,10,0,31,0,0,60,73,0],\n" +
            "[6,16,27,0,0,50,0,0,89],\n" +
            "[9,0,28,32,40,0,0,75,0]]";

    @Test
    public void checkForIdenticalTicketsInSameSession() {
        Ticket ticket1 = TicketUtil.getTicketFromJSON(ticket1Str);
        Ticket ticket2 = TicketUtil.getTicketFromJSON(identicalToTicket1Str);

        String session = "test1";

        boolean result = service.addTicketToSession(session, ticket1);
        assertThat(result).isTrue();

        result = service.addTicketToSession(session, ticket2);
        assertThat(result).isFalse();
    }

    @Test
    public void checkForIdenticalTicketsInDifferentSession() {
        Ticket ticket1 = TicketUtil.getTicketFromJSON(ticket1Str);
        Ticket ticket2 = TicketUtil.getTicketFromJSON(identicalToTicket1Str);

        String session = "test1";

        boolean result = service.addTicketToSession(session, ticket1);
        assertThat(result).isTrue();

        session = "test2";

        result = service.addTicketToSession(session, ticket2);
        assertThat(result).isTrue();
    }

    @Test
    public void checkForSimilarTicketsInSameSession() {
        Ticket ticket1 = TicketUtil.getTicketFromJSON(ticket1Str);
        Ticket ticket2 = TicketUtil.getTicketFromJSON(similarToTicket1Str);

        String session = "test1";

        boolean result = service.addTicketToSession(session, ticket1);
        assertThat(result).isTrue();

        result = service.addTicketToSession(session, ticket2);
        assertThat(result).isFalse();
    }

    @Test
    public void checkForDifferentTicketsInSameSession() {
        Ticket ticket1 = TicketUtil.getTicketFromJSON(ticket1Str);
        Ticket ticket2 = TicketUtil.getTicketFromJSON(ticket2Str);

        String session = "test1";

        boolean result = service.addTicketToSession(session, ticket1);
        assertThat(result).isTrue();

        result = service.addTicketToSession(session, ticket2);
        assertThat(result).isTrue();
    }

    @AfterEach
    public void cleanUp() {
        service.resetSessionCache();
    }

}
