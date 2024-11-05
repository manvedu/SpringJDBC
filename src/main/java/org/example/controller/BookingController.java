package org.example.controller;

import org.example.facade.BookingFacade;
import org.example.model.Event;
import org.example.model.Ticket;
import org.example.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingFacade bookingFacade;

    public BookingController(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @PostMapping("/createUser")
    public User createUser(@RequestParam Long id,
                           @RequestParam String name,
                           @RequestParam String email) {
        return bookingFacade.createUser(id, name, email);
    }

    @PostMapping("/createEvent")
    public Event createEvent(@RequestParam Long id,
                             @RequestParam String title,
                             @RequestParam String description,
                             @RequestParam String date) {
        return bookingFacade.createEvent(id, title, description, date);
    }

    @PostMapping("/bookTicket")
    public Ticket bookTicket(@RequestParam Long id,
                             @RequestParam Long eventId,
                             @RequestParam Long userId,
                             @RequestParam int seatNumber) {
        return bookingFacade.bookTicket(id, eventId, userId, seatNumber);
    }
}
