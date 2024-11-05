package org.example.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tickets")
public class TicketBatch {

    private List<TicketXml> tickets;

    @XmlElement(name = "ticket")
    public List<TicketXml> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketXml> tickets) {
        this.tickets = tickets;
    }
}
