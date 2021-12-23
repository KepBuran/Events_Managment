package com.tasks_equip_management.tem.api.dto;

import com.tasks_equip_management.tem.repo.TaskStatusRepo;
import com.tasks_equip_management.tem.repo.model.TaskStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.Optional;

@Data
@NoArgsConstructor
public final class TaskDto {
    @NotEmpty
    private String name;

    private long eventId;
    private String description;
    private Timestamp deadline;
    private TaskStatus taskStatus;
    private TaskStatusRepo taskStatusRepo;

    public TaskDto(long eventId) {
        this.eventId = eventId;
        this.name = "Noname";
        this.description = "";
        this.deadline = new Timestamp(System.currentTimeMillis());
        this.taskStatus = new TaskStatus("Unknown", "Gray");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
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

