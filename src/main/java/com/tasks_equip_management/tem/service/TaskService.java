package com.tasks_equip_management.tem.service;

import com.tasks_equip_management.tem.repo.EventStatusRepo;
import com.tasks_equip_management.tem.repo.model.Event;
import com.tasks_equip_management.tem.repo.model.EventStatus;
import com.tasks_equip_management.tem.repo.model.Task;
import com.tasks_equip_management.tem.repo.TaskRepo;
import com.tasks_equip_management.tem.repo.TaskStatusRepo;
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
@CacheConfig(cacheNames = "task")
public class TaskService {
    @Autowired
    private final TaskRepo taskRepo;
    private final TaskStatusRepo taskStatusRepo;

    public List<Task> fetchAllTasks() {
        return taskRepo.findAll();
    }

    //@Cacheable(key="!#eventId")
    public List<Task> fetchEventAllTasks(long eventId) {
        return taskRepo.findByEventId(eventId);
    }

    //@Cacheable(key = "#id")
    public Task fetchTaskById(long id) throws IllegalArgumentException {
        final Optional<Task> maybeTask = taskRepo.findById(id);

        if (maybeTask.isPresent())
            return maybeTask.get();
        else
            throw new IllegalArgumentException("Invalid task ID");
    }


    public long createTask(String name, long eventId, String description, Timestamp deadline, long taskStatusId) {
        System.out.println(eventId);
        final TaskStatus taskStatus = taskStatusRepo.findById(taskStatusId).get();
        final Task task = new Task(name, eventId, description, deadline, taskStatus);
        System.out.println(task.getEventId());
        final Task savedTask = taskRepo.save(task);
        long id = savedTask.getId();
        return savedTask.getId();
    }

    //@CachePut(key = "#id")
    public void updateTask(long id, String name, String description, Timestamp deadline, long taskStatusId) throws IllegalArgumentException {
        final Optional<Task> maybeTask = taskRepo.findById(id);
        final Optional<TaskStatus> maybeTaskStatus = taskStatusRepo.findById(taskStatusId);

        if (maybeTask.isEmpty())
            throw new IllegalArgumentException("Invalid task ID");

        final Task task = maybeTask.get();
        if (name != null && !name.isBlank()) task.setName(name);
        if (description != null && !description.isBlank()) task.setDescription(description);
        if (deadline != null) task.setDeadline(deadline);
        if (!maybeTaskStatus.isEmpty()) task.setTaskStatus(maybeTaskStatus.get());
        taskRepo.save(task);
    }

    //@CacheEvict(key = "#id")
    public void deleteTask(long id) {
        taskRepo.deleteById(id);
    }
}


