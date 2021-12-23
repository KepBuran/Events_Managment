package com.tasks_equip_management.tem.api;


import com.tasks_equip_management.tem.api.dto.TaskDto;
import com.tasks_equip_management.tem.repo.model.Task;
import com.tasks_equip_management.tem.repo.model.TaskStatus;
import com.tasks_equip_management.tem.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    public final TaskService taskService;

    @GetMapping("/all/{eventId}")
    public String all(@PathVariable long eventId, Model model) {
        final List<Task> tasks = taskService.fetchEventAllTasks(eventId);
        model.addAttribute("tasks", tasks);
        model.addAttribute("eventId", eventId);
        return "tasks/all";
    }

//    @GetMapping("/{eventId}/{id}")
//    public ResponseEntity<Task> show(@PathVariable long id) {
//        final Task task = taskService.fetchTaskById(id);
//        return ResponseEntity.ok(task);
//    }

    @GetMapping("/change/{id}")
    public String changePage(@PathVariable long id, Model model) {
        final Task task = taskService.fetchTaskById(id);
        model.addAttribute("task", task);
        return "tasks/change";
    }

    @PatchMapping("/update/{id}")
    public String update(@PathVariable long id, @Valid @ModelAttribute("task") Task task, BindingResult bindingResult, Model model) {
        boolean thereAreErrors = bindingResult.hasErrors();
        if (thereAreErrors) {
            model.addAttribute("task", task);
            return "tasks/change";
        }

        long taskStatusId = 1;
        String taskStatusName = task.getTaskStatus().getName();

        if (taskStatusName.equals("Unknown"))
            taskStatusId = 1;
        else if (taskStatusName.equals("Getting ready"))
            taskStatusId = 2;
        else if (taskStatusName.equals("Ready"))
            taskStatusId = 3;
        else if (taskStatusName.equals("Cancelled"))
            taskStatusId = 4;

        String name = task.getName();
        String description = task.getDescription();
        Timestamp deadline =  task.getDeadline();
        TaskStatus taskStatus = task.getTaskStatus();

        try {
            taskService.updateTask(id, name, description, deadline, taskStatusId);
            return "redirect:/tasks/all/"+Long.toString(task.getEventId());

        } catch (IllegalArgumentException e) {
            System.out.println("Error at event update");
            model.addAttribute("error", "Event was not changed");
            return "tasks/error";
        }
    }

    @GetMapping("/create_page/{eventId}")
    public String createPage(@PathVariable long eventId, Model model) {
        TaskDto task = new TaskDto(eventId);
        model.addAttribute("task", task);

//        model.addAttribute("eventId", eventId);
//        model.addAttribute("name", "Noname");
//        model.addAttribute("description", "");
//        model.addAttribute("deadline", new TimestampWrap());
//        model.addAttribute("taskStatus", "Unknown");
        return "tasks/create_page";
    }


    //    public String create(@PathVariable long eventId, @Valid @ModelAttribute("name") String name, @Valid @ModelAttribute("description") String description,
//                         @Valid @ModelAttribute("deadline") Timestamp timestamp, @ModelAttribute("taskStatus") String taskStatusName) {

    @PostMapping("/create/{eventId}")
    public String create(@PathVariable long eventId, @Valid @ModelAttribute("name") TaskDto taskDto, BindingResult bindingResult, Model model){
        boolean thereAreErrors = bindingResult.hasErrors();
        if (thereAreErrors) {
            model.addAttribute("eventid", eventId);
            model.addAttribute("task", taskDto);
            return "tasks/create_page";
        }

        long taskStatusId = 1;
        String taskStatusName = taskDto.getTaskStatus().getName();

        if (taskStatusName.equals("Unknown"))
            taskStatusId = 1;
        else if (taskStatusName.equals("Getting ready"))
            taskStatusId = 2;
        else if (taskStatusName.equals("Ready"))
            taskStatusId = 3;
        else if (taskStatusName.equals("Cancelled"))
            taskStatusId = 4;


        final long taskId = taskService.createTask(taskDto.getName(), eventId, taskDto.getDescription(), taskDto.getDeadline(), taskStatusId);
        final String taskUri = String.format("/tasks/%d", taskId);

        return "redirect:/tasks/all/"+Long.toString(eventId);
    }


    @GetMapping("/confirm_deletion/{id}")
    public String confirm_deletion(@PathVariable long id, Model model) {
        final Task task = taskService.fetchTaskById(id);
        model.addAttribute("name", task.getName());
        model.addAttribute("eventId", task.getEventId());
        model.addAttribute("id", id);
        return "tasks/confirm_deletion";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable long id) {
        final Task task = taskService.fetchTaskById(id);
        long eventId = task.getEventId();
        taskService.deleteTask(id);

        return "redirect:/tasks/all/"+Long.toString(eventId);
    }

}




