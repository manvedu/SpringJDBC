package org.example.service;


import org.example.dao.TicketDao;
import org.example.model.Ticket;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;
import static org.junit.Assert.*;

public class TicketServiceTest {

    @Mock
    private TicketDao ticketDao;

    @InjectMocks
    private TicketService ticketService;
    private Ticket mockedTicket;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockedTicket = new Ticket(1L, 1L, 1L, 123);
    }

    @Test
    public void testbookTicket() {
        Long id = 1L;

        ticketService.bookTicket(id, 1L, 1L, 123);
        ArgumentCaptor<Ticket> ticketCaptor = ArgumentCaptor.forClass(Ticket.class);
        verify(ticketDao, times(1)).save(ticketCaptor.capture());

        Ticket capturedTicket = ticketCaptor.getValue();

        assertEquals(id, capturedTicket.getId());
    }

    @Test
    public void testgetTicket() {
        Long ticketId = 1L;
        Ticket ticket = new Ticket(ticketId, 1L, 1L, 123);
        when(ticketDao.getById(ticketId)).thenReturn(ticket);

        Ticket createdTicket = ticketService.getTicket(ticketId);

        assertEquals(ticketId, createdTicket.getId());
        verify(ticketDao, times(1)).getById(ticketId);
    }
}
