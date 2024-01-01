package com.milap.tambolaticketgenerator.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.milap.tambolaticketgenerator.model.Session;
import com.milap.tambolaticketgenerator.model.Ticket;

import static com.milap.tambolaticketgenerator.SessionName.generateSessionName;

@Service
public class SessionService {

	private static HashMap<String, Session> sessionHashMap = new HashMap<>();
	private final TicketGenerator ticketGenerator;

	private final TicketNumberGenerationService ticketNumberGenerationService;

	public SessionService(TicketGenerator ticketGenerator, TicketNumberGenerationService ticketNumberGenerationService) {
		this.ticketGenerator = ticketGenerator;
        this.ticketNumberGenerationService = ticketNumberGenerationService;
    }

	public String startASession() {
		String sessionId = generateSessionName();
		Session session = new Session(sessionId);
		sessionHashMap.put(sessionId, session);
		return sessionId;
	}

	public int drawANumberFromSession(String sessionId) {
		Session session = sessionHashMap.get(sessionId);
		return session.drawANumber();
	}

	public Ticket getTicket(String sessionId) {
		Session session = sessionHashMap.get(sessionId);
		Ticket ticket = ticketGenerator.getNewTicket(ticketNumberGenerationService.getNextNumber(sessionId));
		while (!session.addTicketToSession(ticket)) {
			ticket = ticketGenerator.getNewTicket(ticketNumberGenerationService.getNextNumber(sessionId));
		}
		return ticket;
	}

	
	public Session getSessionById(String sessionId) {
		return sessionHashMap.get(sessionId);
	}
}
