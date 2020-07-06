package com.milap.tambolaticketgenerator.controller;

import com.milap.tambolaticketgenerator.model.Ticket;
import com.milap.tambolaticketgenerator.service.TicketGenerator;
import com.milap.tambolaticketgenerator.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TambolaController {

    private ValidationService validationService;
    private TicketGenerator ticketGenerator;

    @Autowired
    public TambolaController(ValidationService validationService, TicketGenerator ticketGenerator) {
        this.validationService = validationService;
        this.ticketGenerator = ticketGenerator;
    }

    @PostMapping(path = "/new")
    public ResponseEntity<Ticket> generateTicket(@RequestParam(name = "session") String session) {
        Ticket ticket = ticketGenerator.generateNewTicket(session);
        return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
    }

    @PostMapping(value = "/validate/row")
    private boolean validateRows(int rowNum, Ticket ticket) {
        return validationService.validateRow(rowNum, ticket);
    }
}