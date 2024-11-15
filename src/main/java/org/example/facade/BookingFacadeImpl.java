package org.example.facade;

import org.example.model.Event;
import org.example.model.Ticket;
import org.example.model.User;
import org.example.model.TicketBatch;
import org.example.model.TicketXml;
import org.example.service.EventService;
import org.example.service.TicketService;
import org.example.service.UserService;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.List;

public class BookingFacadeImpl implements BookingFacade {

    private final UserService userService;
    private final EventService eventService;
    private final TicketService ticketService;
    private final Jaxb2Marshaller marshaller;
    private final PlatformTransactionManager transactionManager;

    public BookingFacadeImpl(UserService userService, EventService eventService, TicketService ticketService,
                             Jaxb2Marshaller marshaller, PlatformTransactionManager transactionManager) {
        this.userService = userService;
        this.eventService = eventService;
        this.ticketService = ticketService;
        this.marshaller = marshaller;
        this.transactionManager = transactionManager;
    }

    @Override
    public User createUser(Long id, String name, String email) {
        return userService.createUser(id, name, email);
    }

    @Override
    public Event createEvent(Long id, String title, String description, String date) {
        return eventService.createEvent(id, title, description, date);
    }

    @Override
    public Ticket bookTicket(Long id, Long eventId, Long userId, int seatNumber) {
        return ticketService.bookTicket(id, eventId, userId, seatNumber);
    }

    @Override
    public User getUser(Long id) {
        return userService.getUser(id);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userService.getUsersByName(name);
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketService.getBookedTickets(user, pageSize, pageNum);
    }

    @Override
    public void preloadTickets(String filePath) {
        File xmlFile = new File(filePath);

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            TicketBatch ticketBatch = (TicketBatch) marshaller.unmarshal(new StreamSource(xmlFile));
            List<TicketXml> ticketXmlList = ticketBatch.getTickets();

            for (TicketXml ticketXml : ticketXmlList) {
                int seatNumber = Integer.parseInt(ticketXml.getPlace());

                Ticket ticket = new Ticket(null, ticketXml.getEvent(), ticketXml.getUser(), seatNumber);
                ticketService.bookTicket(ticket.getId(), ticket.getEventId(), ticket.getUserId(), ticket.getSeatNumber());
            }

            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw new RuntimeException("Failed to preload tickets from XML", e);
        }
    }
}
