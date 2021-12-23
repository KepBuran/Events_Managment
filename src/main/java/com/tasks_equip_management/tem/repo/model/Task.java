package com.tasks_equip_management.tem.repo.model;

import com.tasks_equip_management.tem.repo.TaskStatusRepo;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.io.Serializable;

@Entity
@Table(name = "tasks")

//public final class Task implements Serializable{

public final class Task implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(name = "event_id")
    private long eventId;

    @Column(name = "sname")
    private String name;

    @Column(name = "sdescription")
    private String description;

    @Column(name = "ddeadline")
    private Timestamp deadline;

    @JoinColumn(name = "status_id")
    @OneToOne(cascade = CascadeType.ALL)
    private TaskStatus taskStatus;

    public Task() {
    }

    public Task(long eventId, TaskStatus taskStatus) {
        this.eventId = eventId;
        this.name = "Noname";
        this.description = "";
        this.deadline = new Timestamp(System.currentTimeMillis());
        this.taskStatus = taskStatus;
    }

    public Task(String name, long eventId, String description, Timestamp deadline, TaskStatus taskStatus) {

        this.name = name;
        this.eventId = eventId;
        this.description = description;
        this.deadline = deadline;
        this.taskStatus = taskStatus;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
