
package org.example.facade;

import org.example.model.Event;
import org.example.model.Ticket;
import org.example.model.User;
import org.example.service.UserService;
import org.example.service.EventService;
import org.example.service.TicketService;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class BookingFacadeImplTest {
    @Mock
    private UserService userService;

    @Mock
    private EventService eventService;

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private BookingFacadeImpl bookingFacade;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {

        Long userId = 1L;
        String userName = "Michael Jackson";
        String email = "mjackson@testing.com";

        User user = new User(userId, userName, email);
        when(userService.createUser(userId, userName, email)).thenReturn(user);
        User createdUser = bookingFacade.createUser(userId, userName, email);

        verify(userService, times(1)).createUser(userId, userName, email);
        assertEquals(userName, createdUser.getName());
    }

    @Test
    public void testCreateEvent() {
        Long eventId = 1L;
        String title = "Michael Jackson";
        String description = "Just a normal concert";
        String date = " 2024-10-10";
        Event event = new Event(eventId, title, description, date);

        when(eventService.createEvent(eventId, title, description, date)).thenReturn(event);
        Event createdEvent = bookingFacade.createEvent(eventId, title, description, date);

        verify(eventService, times(1)).createEvent(eventId, title, description, date);
        assertEquals(eventId, createdEvent.getId());
    }

    @Test
    public void testBookTicket() {
        Long ticketId = 1L;
        Ticket ticket = new Ticket(ticketId, 1L, 1L, 123);

        when(ticketService.bookTicket(ticketId, 1L, 1L, 123)).thenReturn(ticket);
        Ticket createdTicket = bookingFacade.bookTicket(ticketId, 1L, 1L, 123);

        verify(ticketService, times(1)).bookTicket(ticketId, 1L, 1L, 123);
        assertEquals(ticketId, createdTicket.getId());
    }
}
