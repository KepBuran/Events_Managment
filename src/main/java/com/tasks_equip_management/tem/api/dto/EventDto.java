package com.tasks_equip_management.tem.api.dto;

import com.tasks_equip_management.tem.repo.model.EventStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
public class EventDto {
    private String name;
    private String location;
    private Timestamp eventDate;
    private EventStatus eventStatus;

    public EventDto() {
        this.name = "Name";
        this.location = "Location";
        this.eventDate = new Timestamp(System.currentTimeMillis());
        this.eventStatus = new EventStatus("Unknown", "Gray");
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
