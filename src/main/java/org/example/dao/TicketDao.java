package org.example.dao;

import org.example.model.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketDao {
    private Map<Long, Ticket> ticketStorage = new HashMap<>();

    public Ticket save(Ticket ticket) {
        ticketStorage.put(ticket.getId(), ticket);
        return ticket;
    }

    public Ticket getById(Long id) {
        return ticketStorage.get(id);
    }

    public Map<Long, Ticket> getAll() {
        return ticketStorage;
    }
}
