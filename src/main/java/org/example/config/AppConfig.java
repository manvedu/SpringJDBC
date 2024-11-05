package org.example.config;

import org.example.facade.BookingFacade;
import org.example.facade.BookingFacadeImpl;
import org.example.service.EventService;
import org.example.service.TicketService;
import org.example.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public BookingFacade bookingFacade(UserService userService, EventService eventService, TicketService ticketService) {
        return new BookingFacadeImpl(userService, eventService, ticketService);
    }
}