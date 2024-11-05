package org.example.service;

import org.example.dao.EventDao;
import org.example.model.Event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

public class EventService {
    private static final Logger logger = LoggerFactory.getLogger(EventService.class);
    private EventDao eventDao;

    @Autowired
    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public Event createEvent(Long id, String title, String description, String date) {
        Event event = new Event(id, title, description, date);
        logger.info("EventService - createEvent: " + event);
        return eventDao.save(event);
    }

    public Event getEvent(Long id) {
        logger.info("EventService - getEvent: " + id);
        return eventDao.getById(id);
    }
}
