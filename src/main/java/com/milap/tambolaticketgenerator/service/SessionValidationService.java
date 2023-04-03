package com.milap.tambolaticketgenerator.service;

import com.milap.tambolaticketgenerator.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;

@Service
public class SessionValidationService {
    private static HashMap<String, HashSet<Ticket>> sessionMap = new HashMap<>();

    public boolean addTicketToSession(String session, Ticket ticket) {
        boolean isUnique = false;
        if (!sessionMap.containsKey(session)) {
            HashSet<Ticket> tickets = new HashSet<>();
            tickets.add(ticket);
            sessionMap.put(session, tickets);
            isUnique = true;
        } else {
            HashSet<Ticket> tickets = sessionMap.get(session);
            int size = tickets.size();
            tickets.add(ticket);
            if (tickets.size() > size){
                isUnique = true;
            }
        }
        return isUnique;
    }
    
    public static HashMap<String, HashSet<Ticket>> getSessionMap() {
		return sessionMap;
	}



	public void resetSessionCache() {
        sessionMap.clear();
    }
}
