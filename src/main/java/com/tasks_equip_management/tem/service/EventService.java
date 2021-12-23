package com.tasks_equip_management.tem.service;

import com.tasks_equip_management.tem.repo.EventRepo;
import com.tasks_equip_management.tem.repo.EventStatusRepo;
import com.tasks_equip_management.tem.repo.TaskRepo;
import com.tasks_equip_management.tem.repo.model.Event;
import com.tasks_equip_management.tem.repo.model.EventStatus;
import com.tasks_equip_management.tem.repo.model.Task;
import com.tasks_equip_management.tem.repo.model.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@CacheConfig(cacheNames = "event")
public class EventService {
    @Autowired
    private final EventRepo eventRepo;
    private final EventStatusRepo eventStatusRepo;
    private final TaskRepo taskRepo;

    public List<Event> fetchAllTasks() {
        return eventRepo.findAll();
    }

    //@Cacheable(key = "#id")
    public Event fetchEventById(long id) throws IllegalArgumentException {
        final Optional<Event> maybeEvent = eventRepo.findById(id);

        if (maybeEvent.isPresent())
            return maybeEvent.get();
        else
            throw new IllegalArgumentException("Invalid event ID");
    }

    public long createEvent(String name, String location, Timestamp eventDate, long eventStatusId) {
        System.out.println(eventStatusId);
        final EventStatus eventStatus = eventStatusRepo.findById(eventStatusId).get();
        final Event event = new Event(name, location, eventDate, eventStatus);
        final Event savedEvent = eventRepo.save(event);
        long id = savedEvent.getId();
        return savedEvent.getId();
    }

    //@CachePut(key = "#id")
    public void updateEvent(long id, String name, String location, Timestamp eventDate, long eventStatusId) throws IllegalArgumentException {
        final Optional<Event> maybeEvent= eventRepo.findById(id);
        final Optional<EventStatus> maybeEventStatus = eventStatusRepo.findById(eventStatusId);

        if (maybeEvent.isEmpty())
            throw new IllegalArgumentException("Invalid event ID");

        final Event event = maybeEvent.get();
        if (name != null && !name.isBlank()) event.setName(name);
        if (location != null && !location.isBlank()) event.setLocation(location);
        if (eventDate != null) event.setEventDate(eventDate);
        if (!maybeEventStatus.isEmpty()) event.setEventStatus(maybeEventStatus.get());
        eventRepo.save(event);
    }

    //@CacheEvict(key = "#id")
    public void deleteEvent(long id) {
        taskRepo.deleteTasksByEventId(id);
        eventRepo.deleteById(id);
    }
}