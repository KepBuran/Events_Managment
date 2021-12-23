package com.tasks_equip_management.tem.repo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "task_statuses")

public class TaskStatus implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "sname")
    private String name;

    @Column(name = "scolor")
    private String color;

    public TaskStatus(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public TaskStatus() {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}