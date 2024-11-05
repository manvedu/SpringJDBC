package org.example.facade;

import org.example.model.User;
import org.example.model.Event;
import org.example.model.Ticket;

public interface BookingFacade {
    User createUser(Long id, String name, String email);
    Event createEvent(Long id, String title, String description, String date);
    Ticket bookTicket(Long id, Long eventId, Long userId, int seatNumber);
}
