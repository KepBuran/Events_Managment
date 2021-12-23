package com.tasks_equip_management.tem.repo.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "events")

public final class Event implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Event name cannot be empty")
    @Column(name = "sname")
    private String name;

    @Column(name = "slocation")
    private String location;

    @Column(name = "devent_date")
    private Timestamp eventDate;

    @JoinColumn(name = "status_id")
    @OneToOne(cascade = CascadeType.ALL)
    private EventStatus eventStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Event() {
    }

    public Event(String name, String location, Timestamp eventDate, EventStatus eventStatus) {
        this.name = name;
        this.location = location;
        this.eventDate = eventDate;
        this.eventStatus = eventStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getEventDate() {
        return eventDate;
    }

    public void setEventDate(Timestamp eventDate) {
        this.eventDate = eventDate;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }
}