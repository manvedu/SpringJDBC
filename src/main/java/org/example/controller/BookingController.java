package org.example.controller;

import org.example.facade.BookingFacade;
import org.example.model.Event;
import org.example.model.Ticket;
import org.example.model.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
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

    @GetMapping("/getUser")
    public ModelAndView getUser(@RequestParam Long id) {
        User user = bookingFacade.getUser(id);
        ModelAndView modelAndView = new ModelAndView("user");
        if (user != null) {
            modelAndView.addObject("user", user);
        } else {
            modelAndView.setViewName("error");  // Redirects to an error template if user not found
            modelAndView.addObject("message", "User not found.");
        }
        return modelAndView;
    }

    // Method to display users by name
    @GetMapping("/getUsersByName")
    public ModelAndView getUsersByName(@RequestParam String name) {
        List<User> users = bookingFacade.getUsersByName(name);
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", users);
        return modelAndView;
    }
}
