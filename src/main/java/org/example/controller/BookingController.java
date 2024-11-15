package org.example.controller;

import org.example.facade.BookingFacade;
import org.example.model.Event;
import org.example.model.Ticket;
import org.example.model.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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

    @GetMapping("/getUsersByName")
    public ModelAndView getUsersByName(@RequestParam String name) {
        List<User> users = bookingFacade.getUsersByName(name);
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @PostMapping("/preloadTickets")
    public String preloadTickets(@RequestParam String filePath) {
        try {
            bookingFacade.preloadTickets(filePath);
            return "Batch ticket creation successful";
        } catch (Exception e) {
            return "Failed to preload tickets: " + e.getMessage();
        }
    }

/*
    @GetMapping(value = "/api/booking/getBookedTickets", produces = MediaType.APPLICATION_PDF_VALUE)
    public void getBookedTicketsPdf(
            @RequestParam Long userId,
            @RequestParam int pageSize,
            @RequestParam int pageNum,
            @RequestHeader(HttpHeaders.ACCEPT) String acceptHeader,
            HttpServletResponse response) throws IOException, DocumentException {

        if (!MediaType.APPLICATION_PDF_VALUE.equals(acceptHeader)) {
            throw new IllegalArgumentException("This endpoint only supports PDF responses.");
        }

        User user = bookingFacade.getUser(userId);
        List<Ticket> tickets = bookingFacade.getBookedTickets(user, pageSize, pageNum);

        // Set the content type and create the PDF document
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Add content to the PDF
        document.add(new Paragraph("Booked Tickets for User: " + user.getName()));
        document.add(new Paragraph("Email: " + user.getEmail()));
        document.add(new Paragraph(" ")); // Add space

        for (Ticket ticket : tickets) {
            document.add(new Paragraph("Ticket ID: " + ticket.getId() +
                    ", Event ID: " + ticket.getEventId() +
                    ", Seat Number: " + ticket.getSeatNumber()));
        }

        document.close();
    }

 */



}
