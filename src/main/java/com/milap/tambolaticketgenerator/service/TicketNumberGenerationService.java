package com.milap.tambolaticketgenerator.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TicketNumberGenerationService {

    private static Map<String, Integer> ticketNumberMap = new HashMap<>();


    public synchronized int getNextNumber(String sessionId) {
        if(ticketNumberMap.containsKey(sessionId)) {
            int lastTicketNo = ticketNumberMap.get(sessionId);
            lastTicketNo++;
            ticketNumberMap.put(sessionId, lastTicketNo);
            return lastTicketNo;
        } else {
            int ticketNo = 1;
            ticketNumberMap.put(sessionId, ticketNo);
            return ticketNo;
        }
    }
}
