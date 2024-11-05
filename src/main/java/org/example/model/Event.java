package org.example.model;

public class Event {
    private Long id;
    private String title;
    private String description;
    private String date;

    public Event(long id, String title, String description, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;

    }

    public Long getId() {
        return id;
    }
}
