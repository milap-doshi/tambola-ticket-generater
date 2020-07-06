package com.milap.tambolaticketgenerator.service;

import com.milap.context.SessionValidationServiceContext;
import com.milap.context.TicketGeneratorContext;
import com.milap.tambolaticketgenerator.model.Ticket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TicketGeneratorContext.class)
public class TicketGeneratorTest {

    @Autowired
    TicketGenerator ticketGenerator;

    @Test
    public void testGenerateTicket() {
        String session = "test1";
        Ticket ticket = ticketGenerator.generateNewTicket(session);
        assertThat(ticket).isNotNull();

        int[][] numbers = ticket.getNumbers();
        long count = Arrays.stream(numbers).flatMapToInt(x -> Arrays.stream(x))
                .filter(x -> x > 0).count();
        assertThat(count).isEqualTo(15);

        for (int i = 0; i < 2; i++) {
            count = Arrays.stream(numbers[i])
                    .filter(x -> x > 0).count();
            assertThat(count).isEqualTo(5);
        }
    }
}
