package com.milap.tambolaticketgenerator.util;

import com.google.gson.Gson;
import com.milap.tambolaticketgenerator.model.Ticket;

public class TicketUtil {

    public static Ticket getTicketFromJSON(String json) {
        Gson gson = new Gson();
        int[][] numbers =  gson.fromJson(json, int[][].class);
        Ticket ticket = new Ticket();
        ticket.setNumbers(numbers);
        return ticket;
    }
}
