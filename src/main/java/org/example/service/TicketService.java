package org.example.service;

import org.example.dao.TicketDao;
import org.example.model.Ticket;
import org.example.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);
    private TicketDao ticketDao;

    @Autowired
    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public Ticket bookTicket(Long id, Long eventId, Long userId, int seatNumber) {
        Ticket ticket = new Ticket(id, eventId, userId, seatNumber);
        logger.info("TicketService - bookTicket: " + ticket);
        return ticketDao.save(ticket);
    }

    public Ticket getTicket(Long id) {
        logger.info("TicketService - getTicket by id: " + id);
        return ticketDao.getById(id);
    }

    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        logger.info("TicketService - getBookedTickets for user: " + user.getId());
        return ticketDao.findTicketsByUser(user, pageSize, pageNum);
    }
}


