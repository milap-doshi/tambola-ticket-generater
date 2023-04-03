package com.milap.tambolaticketgenerator.service;

import static com.milap.tambolaticketgenerator.service.SessionValidationService.getSessionMap;

import java.util.HashSet;

import org.springframework.stereotype.Service;

import com.milap.tambolaticketgenerator.model.Ticket;

@Service
public class ValidationService {
	public boolean validateRow(int rowNum, String session, String ticketId) {
		HashSet<Ticket> tickets = getSessionMap().get(session);
		Ticket ticketForValidation = tickets.stream().filter(ticket -> ticket.getId().equals(ticketId)).findFirst().orElseThrow();
		int [] rowNumbers = ticketForValidation.getNumbers()[rowNum];
		return false;
	}
}
