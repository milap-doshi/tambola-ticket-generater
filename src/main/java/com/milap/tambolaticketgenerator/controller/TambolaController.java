package com.milap.tambolaticketgenerator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.milap.tambolaticketgenerator.model.Ticket;
import com.milap.tambolaticketgenerator.model.ValidationResult;
import com.milap.tambolaticketgenerator.service.SessionService;
import com.milap.tambolaticketgenerator.service.ValidationService;

@CrossOrigin(maxAge = 3600)
@Controller
public class TambolaController {

	private ValidationService validationService;
	private final SessionService sessionService;
	int Number = 1;

	public TambolaController(ValidationService validationService, SessionService sessionService) {
		this.validationService = validationService;
		this.sessionService = sessionService;
	}

	@GetMapping(path = "/start")
	public ResponseEntity<String> startAGame() {
		return ResponseEntity.ok(sessionService.startASession());
	}

	@PostMapping(path = "/get/ticket")
	public ResponseEntity<Ticket> getTicketFromSession(@RequestParam(name = "sessionId") String sessionId) {
		return ResponseEntity.ok(sessionService.getTicket(sessionId));
	}

	@PostMapping(value = "/get/number")
	private ResponseEntity<Integer> getNextNumber(@RequestParam(name = "sessionId") String sessionId) {
		return ResponseEntity.ok(sessionService.drawANumberFromSession(sessionId));
	}

	@PostMapping(value = "/validate/row")
	private ResponseEntity<ValidationResult> validateRow(@RequestParam(name = "sessionId") String sessionId,
			@RequestParam(name = "ticketId") String ticketId, @RequestParam(name = "rowNum") int rowNum) {
		return ResponseEntity.ok(validationService.validateRow(rowNum, sessionId, ticketId));
	}

}
