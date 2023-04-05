package com.milap.tambolaticketgenerator.service;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.milap.tambolaticketgenerator.model.Session;
import com.milap.tambolaticketgenerator.model.Ticket;

@Service
public class SessionService {

	private static HashMap<String, Session> sessionHashMap = new HashMap<>();
	private final TicketGenerator ticketGenerator;

	public SessionService(TicketGenerator ticketGenerator) {
		this.ticketGenerator = ticketGenerator;
	}

	public String startASession() {
		String sessionId = UUID.randomUUID().toString();
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
		Ticket ticket = ticketGenerator.getNewTicket();
		while (!session.addTicketToSession(ticket)) {
			ticket = ticketGenerator.getNewTicket();
		}
		return ticket;
	}

	
	public Session getSessionById(String sessionId) {
		return sessionHashMap.get(sessionId);
	}
}
