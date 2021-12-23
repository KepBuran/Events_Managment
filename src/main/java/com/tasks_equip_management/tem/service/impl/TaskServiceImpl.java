//package com.tasks_equip_management.tem.service.impl;
//
//import com.tasks_equip_management.tem.repo.TaskRepo;
//import com.tasks_equip_management.tem.repo.model.Task;
//import com.tasks_equip_management.tem.service.TaskService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.sql.Timestamp;
//import java.util.List;
//import java.util.Optional;
//
//@RequiredArgsConstructor
//@Service
//public final class TaskServiceImpl implements TaskService {
//
//    private final TaskRepo taskRepo;
//
//    public List<Task> fetchAllTasks() {
//        return taskRepo.findAll();
//    }
//
//    public Task fetchTaskById(long id) throws IllegalArgumentException {
//        final Optional<Task> maybeTask = taskRepo.findById(id);
//
//        if (maybeTask.isPresent())
//            return maybeTask.get();
//        else
//            throw new IllegalArgumentException("Invalid car ID");
//    }
//
//    public long createTask(String name, String description, Timestamp deadline, long statusId) {
//        final Task task = new Task(name, description, deadline, statusId);
//        final Task savedTask = taskRepo.save(task);
//
//        return savedTask.getId();
//    }
//
//    public void updateTask(long id, String name, String description, Timestamp deadline, long statusId) throws IllegalArgumentException {
//        final Optional<Task> maybeTask = taskRepo.findById(id);
//
//        if (maybeTask.isEmpty())
//            throw new IllegalArgumentException("Invalid task ID");
//
//        final Task task = maybeTask.get();
//        if (name != null && !name.isBlank()) task.setName(name);
//        if (description != null && !description.isBlank()) task.setDescription(description);
//        if (deadline != null) task.setDeadline(deadline);
//        if (statusId > -1) task.setStatusId(statusId);
//        taskRepo.save(task);
//    }
//
//    public void deleteTask(long id) {
//        taskRepo.deleteById(id);
//    }
//}
//
