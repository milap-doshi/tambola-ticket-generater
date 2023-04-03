package com.milap.tambolaticketgenerator.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.milap.tambolaticketgenerator.model.Ticket;
import com.milap.tambolaticketgenerator.service.SessionService;
import com.milap.tambolaticketgenerator.service.TicketGenerator;
import com.milap.tambolaticketgenerator.service.ValidationService;

@Controller
public class TambolaController {

	private ValidationService validationService;
	private TicketGenerator ticketGenerator;
	private final SessionService sessionService;
	int Number = 1;

	@Autowired
	public TambolaController(ValidationService validationService, TicketGenerator ticketGenerator,
			SessionService sessionService) {
		this.validationService = validationService;
		this.ticketGenerator = ticketGenerator;
		this.sessionService = sessionService;
	}

	@PostMapping(path = "/start")
	public ResponseEntity<String> startAGame() {
		return ResponseEntity.ok(sessionService.startASession());
	}

	@PostMapping(path = "/get/ticket")
	public ResponseEntity<Ticket> getTicketFromSession(@RequestParam(name = "sessionId") String sessionId) {
		return ResponseEntity.ok(sessionService.getTicket(sessionId));
	}

	/*
	 * @GetMapping(path = "/new") public ResponseEntity<List<Ticket>>
	 * generateTicket(@RequestParam(name = "session") String session,
	 * 
	 * @RequestParam(value = "tickets", required = false, defaultValue = "1") int
	 * tickets) {
	 * 
	 * List<Ticket> returnList = new ArrayList<>(); for (int i = 0; i < tickets;
	 * i++) { Ticket ticket = ticketGenerator.generateNewTicket(session);
	 * returnList.add(ticket); } return new ResponseEntity<List<Ticket>>(returnList,
	 * HttpStatus.OK); }
	 * 
	 * @PostMapping(value = "/validate/row") private boolean validateRows(int
	 * rowNum, String session, String ticketId) { return
	 * validationService.validateRow(rowNum, session, ticketId); }
	 */

	@PostMapping(value = "/get/number")
	private ResponseEntity<Integer> getNextNumber(@RequestParam(name = "sessionId") String sessionId) {
		return ResponseEntity.ok(sessionService.drawANumberFromSession(sessionId));
	}

}
