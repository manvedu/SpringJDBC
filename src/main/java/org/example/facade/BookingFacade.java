package org.example.facade;

import org.example.model.User;
import org.example.model.Event;
import org.example.model.Ticket;
import java.io.FileNotFoundException;

import java.util.List;

public interface BookingFacade {
    User createUser(Long id, String name, String email);
    Event createEvent(Long id, String title, String description, String date);
    Ticket bookTicket(Long id, Long eventId, Long userId, int seatNumber);
    User getUser(Long id);
    List<User> getUsersByName(String name);
    List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);
    void preloadTickets(String filePath) throws FileNotFoundException;
}
