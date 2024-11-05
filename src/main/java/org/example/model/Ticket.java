package org.example.model;

public class Ticket {
    private Long id;
    private Long eventId;
    private Long userId;
    private int seatNumber;

    public Ticket(Long id, Long eventId, Long userId, int seatNumber) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
        this.seatNumber = seatNumber;
    }

    public Long getId() {
        return id;
    }
}
