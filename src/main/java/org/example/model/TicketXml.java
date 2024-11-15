package org.example.model;

import javax.xml.bind.annotation.XmlAttribute;

public class TicketXml {
    private Long user;
    private Long event;
    private String category;
    private String place;

    @XmlAttribute
    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    @XmlAttribute
    public Long getEvent() {
        return event;
    }

    public void setEvent(Long event) {
        this.event = event;
    }

    @XmlAttribute
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @XmlAttribute
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
