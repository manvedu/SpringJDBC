package org.example.controller;

import org.example.facade.BookingFacade;
import org.example.model.Event;
import org.example.model.User;
import org.example.model.Ticket;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingFacade bookingFacade;

    @Test
    public void testCreateUser() throws Exception {
        mockMvc.perform(post("/api/booking/createUser")
                        .param("id", "1")
                        .param("name", "John Doe")
                        .param("email", "john.doe@example.com")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateEvent() throws Exception {
        Event event = new Event(1L, "Event Title", "Event Description", "2023-10-20");
        Mockito.when(bookingFacade.createEvent(1L, "Event Title", "Event Description", "2023-10-20")).thenReturn(event);

        mockMvc.perform(post("/api/booking/createEvent")
                        .param("id", "1")
                        .param("title", "Event Title")
                        .param("description", "Event Description")
                        .param("date", "2023-10-20")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("id")))
                .andExpect(content().string(containsString("1")));
    }
/*
    @Test
    public void testGetUser() throws Exception {
        User user = new User(1L, "John Doe", "john.doe@example.com");
        Mockito.when(bookingFacade.getUser(eq(1L))).thenReturn(user);

        mockMvc.perform(get("/api/booking/getUser")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<h2>User Details</h2>")))
                .andExpect(content().string(containsString("<p>ID: <span>1</span></p>")))
                .andExpect(content().string(containsString("<p>Name: <span>John Doe</span></p>")))
                .andExpect(content().string(containsString("<p>Email: <span>john.doe@example.com</span></p>")));
    }

    @Test
    public void testGetUsersByName() throws Exception {
        User user = new User(1L, "John Doe", "john.doe@example.com");
        Mockito.when(bookingFacade.getUsersByName(eq("John Doe"))).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/api/booking/getUsersByName")
                        .param("name", "John Doe"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<h2>User List</h2>")))
                .andExpect(content().string(containsString("<li>")))
                .andExpect(content().string(containsString("ID: <span>1</span>,")))
                .andExpect(content().string(containsString("Name: <span>John Doe</span>,")))
                .andExpect(content().string(containsString("Email: <span>john.doe@example.com</span>")));
    }

 */

    @Test
    public void testBookTicket() throws Exception {
        Ticket ticket = new Ticket(1L, 1L, 1L, 1);
        Mockito.when(bookingFacade.bookTicket(1L, 1L, 1L, 1)).thenReturn(ticket);

        mockMvc.perform(post("/api/booking/bookTicket")
                        .param("id", "1")
                        .param("eventId", "1")
                        .param("userId", "1")
                        .param("seatNumber", "1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());
    }

    @Test
    public void testPreloadTickets() throws Exception {
        Mockito.doNothing().when(bookingFacade).preloadTickets("tickets.xml");

        mockMvc.perform(post("/api/booking/preloadTickets")
                        .param("filePath", "tickets.xml")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Batch ticket creation successful")));
    }
}
