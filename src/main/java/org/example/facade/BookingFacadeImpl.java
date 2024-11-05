package org.example.facade;

import org.example.model.Event;
import org.example.model.Ticket;
import org.example.model.User;
import org.example.service.EventService;
import org.example.service.TicketService;
import org.example.service.UserService;

public class BookingFacadeImpl implements BookingFacade{

    private final UserService userService;
    private final EventService eventService;
    private final TicketService ticketService;

    public BookingFacadeImpl(UserService userService, EventService eventService, TicketService ticketService) {
        this.userService = userService;
        this.eventService = eventService;
        this.ticketService = ticketService;
    }

    @Override
    public User createUser(Long id, String name, String email) {
        return userService.createUser(id, name, email);
    }

    @Override
    public Event createEvent(Long id, String title, String description, String date) {
        return eventService.createEvent(id, title, description, date);
    }

    @Override
    public Ticket bookTicket(Long id, Long eventId, Long userId, int seatNumber) {
        return ticketService.bookTicket(id, eventId, userId, seatNumber);
    }
}
