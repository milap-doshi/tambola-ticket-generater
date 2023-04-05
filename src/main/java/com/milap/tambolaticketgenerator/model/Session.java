package com.milap.tambolaticketgenerator.model;

import static java.util.stream.Collectors.toList;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Session {
	private final String sessionId;
	private final HashSet<Ticket> tickets;
	private final List<Integer> rouletteOptions;

	public Session(String sessionId) {
		this.sessionId = sessionId;
		this.tickets = new HashSet<>();
		this.rouletteOptions = IntStream.range(1, 91).mapToObj(number -> number).collect(toList());
	}

	public String getSessionId() {
		return sessionId;
	}

	public HashSet<Ticket> getTickets() {
		return tickets;
	}

	public List<Integer> getRouletteOptions() {
		return rouletteOptions;
	}

	public boolean addTicketToSession(Ticket ticket) {
		if (!tickets.contains(ticket)) {
			tickets.add(ticket);
			return true;
		}
		return false;
	}

	public int drawANumber() {
		int optionsSize = rouletteOptions.size();
		int posToReturn = new Random().nextInt(optionsSize);
		int returnValue = rouletteOptions.get(posToReturn);
		rouletteOptions.remove(posToReturn);
		return returnValue;
	}
}
