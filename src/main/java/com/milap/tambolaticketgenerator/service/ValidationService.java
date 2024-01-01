package com.milap.tambolaticketgenerator.service;

import com.milap.tambolaticketgenerator.model.Session;
import com.milap.tambolaticketgenerator.model.Ticket;
import com.milap.tambolaticketgenerator.model.ValidationResult;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.milap.tambolaticketgenerator.model.ValidationStatus.*;
import static java.util.Collections.emptyList;

@Service
public class ValidationService {

    private final SessionService sessionService;

    public ValidationService(SessionService sessionService) {
        super();
        this.sessionService = sessionService;
    }

    public ValidationResult validateRow(int rowNum, String sessionId, String ticketId) {
        Session session = sessionService.getSessionById(sessionId);
        if (session == null) {
            return new ValidationResult(INVALID_SESSION.getStatus(), emptyList());
        }
        Optional<Ticket> ticketOptional = session.getTickets().stream()
                .filter(ticket -> ticket.getId().equals(ticketId)).findFirst();
        if (ticketOptional.isPresent()) {
            List<Integer> unmatchedList = Arrays.stream(ticketOptional.get().getNumbers()[rowNum])
                    .filter(number -> session.getRouletteOptions().contains(number))
                    .mapToObj(number -> number)
                    .collect(Collectors.toList());

            return unmatchedList.isEmpty()
                    ? new ValidationResult(SUCCESS.getStatus(), emptyList())
                    : new ValidationResult(NUMBER_MISMATCH.getStatus(), unmatchedList);
        } else {
            return new ValidationResult(INVALID_TICKET.getStatus(), emptyList());
        }
    }

    public ValidationResult validateCorners(String sessionId, String ticketId) {
        Session session = sessionService.getSessionById(sessionId);
        if (session == null) {
            return new ValidationResult(INVALID_SESSION.getStatus(), emptyList());
        }
        Optional<Ticket> ticketOptional = session.getTickets().stream()
                .filter(ticket -> ticket.getId().equals(ticketId)).findFirst();
        if (ticketOptional.isPresent()) {
            List<Integer> unmatchedList = ticketOptional.get().getCorners()
                    .stream()
                    .filter(number -> session.getRouletteOptions().contains(number))
                    .collect(Collectors.toList());

            return unmatchedList.isEmpty()
                    ? new ValidationResult(SUCCESS.getStatus(), emptyList())
                    : new ValidationResult(NUMBER_MISMATCH.getStatus(), unmatchedList);
        } else {
            return new ValidationResult(INVALID_TICKET.getStatus(), emptyList());
        }
    }
}
