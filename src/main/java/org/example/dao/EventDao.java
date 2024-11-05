package org.example.dao;

import org.example.model.Event;

import java.util.HashMap;
import java.util.Map;

public class EventDao {
    private Map<Long, Event> eventStorage = new HashMap<>();

    public Event save(Event event) {
        eventStorage.put(event.getId(), event);
        return event;
    }

    public Event getById(Long id) {
        return eventStorage.get(id);
    }

    public Map<Long, Event> getAll() {
        return eventStorage;
    }
}
