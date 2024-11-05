package org.example.facade;

import org.example.model.Event;
import org.example.model.Ticket;
import org.example.model.User;
import java.util.List;

/**
 * BookingFacade interface defines the main operations for managing users, events, and tickets in the system.
 */
public interface BookingFacade {

    /**
     * Creates a new user.
     *
     * @param id    the user ID
     * @param name  the user's name
     * @param email the user's email
     * @return the created User object
     */
    User createUser(Long id, String name, String email);

    /**
     * Creates a new event.
     *
     * @param id          the event ID
     * @param title       the title of the event
     * @param description the description of the event
     * @param date        the date of the event
     * @return the created Event object
     */
    Event createEvent(Long id, String title, String description, String date);

    /**
     * Books a ticket for an event by a user.
     *
     * @param id        the ticket ID
     * @param eventId   the event ID
     * @param userId    the user ID
     * @param seatNumber the seat number for the ticket
     * @return the booked Ticket object
     */
    Ticket bookTicket(Long id, Long eventId, Long userId, int seatNumber);

    /**
     * Retrieves a user by ID.
     *
     * @param id the user ID
     * @return the User object, or null if not found
     */
    User getUser(Long id);

    /**
     * Retrieves a list of users by name.
     *
     * @param name the user's name
     * @return a list of users with the given name
     */
    List<User> getUsersByName(String name);

    /**
     * Retrieves booked tickets for a given user, with pagination support.
     *
     * @param user     the user for whom to retrieve tickets
     * @param pageSize the number of tickets per page
     * @param pageNum  the page number to retrieve
     * @return a list of booked tickets for the user
     */
    List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);

    /**
     * Preloads tickets from an XML file.
     *
     * @param filePath the file path to load tickets from
     */
    void preloadTickets(String filePath);
}
