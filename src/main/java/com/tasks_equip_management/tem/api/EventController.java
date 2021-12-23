package com.tasks_equip_management.tem.api;

import com.tasks_equip_management.tem.api.dto.EventDto;
import com.tasks_equip_management.tem.api.dto.TaskDto;
import com.tasks_equip_management.tem.repo.model.Event;
import com.tasks_equip_management.tem.repo.model.EventStatus;
import com.tasks_equip_management.tem.repo.model.Task;
import com.tasks_equip_management.tem.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    public final EventService eventService;

    @GetMapping("/all")
    public String index(Model model) {
        final List<Event> events = eventService.fetchAllTasks();
        ArrayList<String> dates = new ArrayList<String>();
        for (int i = 0; i < events.size(); i++){
            dates.add(new SimpleDateFormat("MM/dd/yyyy").format(events.get(i).getEventDate()));
        }

        model.addAttribute("events", events);
        model.addAttribute("dates", dates);
        return "events/all";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable long id,Model model) {
        final Event event = eventService.fetchEventById(id);
        System.out.println("EVENT ID");
        System.out.println(event.getId());
        model.addAttribute("event", event);
        return "events/overlook";
    }

    @GetMapping("/change/{id}")
    public String changePage(@PathVariable long id,Model model) {
        final Event event = eventService.fetchEventById(id);
        model.addAttribute("event", event);
        return "events/change";
    }

    @PatchMapping("/update/{id}")
    public String update(@PathVariable long id, @Valid @ModelAttribute("event") Event event, BindingResult bindingResult, Model model) {
        boolean thereAreErrors = bindingResult.hasErrors();
        if (thereAreErrors) {
            model.addAttribute("event", event);
            return "events/change";
        }

        long eventStatusId = 1;
        String taskStatusName = event.getEventStatus().getName();


        if (taskStatusName.equals("Unknown"))
            eventStatusId = 1;
        else if (taskStatusName.equals("Preparing"))
            eventStatusId = 2;
        else if (taskStatusName.equals("Online"))
            eventStatusId = 3;
        else if (taskStatusName.equals("Completed"))
            eventStatusId = 4;
        else if (taskStatusName.equals("Cancelled"))
            eventStatusId = 5;

        String name = event.getName();
        String location = event.getLocation();
        Timestamp eventDate = event.getEventDate();

        EventStatus eventStatus = event.getEventStatus();
        try {
            eventService.updateEvent(id, name, location, eventDate, eventStatusId);
            model.addAttribute("id", event.getId());
            return "events/overlook";
        } catch (IllegalArgumentException e) {
            System.out.println("Error at event update");
            model.addAttribute("error", "Event was not changed");
            return "events/error";
        }
    }

    @GetMapping("/create_page")
    public String createPage(Model model) {
        EventDto event = new EventDto();
        model.addAttribute("event", event);

        return "events/create_page";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("name") EventDto eventDto, BindingResult bindingResult, Model model){
        boolean thereAreErrors = bindingResult.hasErrors();
        if (thereAreErrors) {
            model.addAttribute("event", eventDto);
            return "events/create_page";
        }

        long eventStatusId = 1;
        String taskStatusName = eventDto.getEventStatus().getName();


        if (taskStatusName.equals("Unknown"))
            eventStatusId = 1;
        else if (taskStatusName.equals("Preparing"))
            eventStatusId = 2;
        else if (taskStatusName.equals("Online"))
            eventStatusId = 3;
        else if (taskStatusName.equals("Completed"))
            eventStatusId = 4;
        else if (taskStatusName.equals("Cancelled"))
            eventStatusId = 5;


        final long taskId = eventService.createEvent(eventDto.getName(), eventDto.getLocation(), eventDto.getEventDate(), eventStatusId);
        final String taskUri = String.format("/events/%d", taskId);

        return "redirect:/events/all";
    }

    @GetMapping("/confirm_deletion/{id}")
    public String confirm_deletion(@PathVariable long id, Model model) {
        final Event event = eventService.fetchEventById(id);
        model.addAttribute("name", event.getName());
        model.addAttribute("id", id);
        return "events/confirm_deletion";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable long id) {
        final Event event = eventService.fetchEventById(id);
        eventService.deleteEvent(id);

        return "redirect:/events/all";
    }


//    @PostMapping
//    public String create(@RequestBody com.tasks_equip_management.tem.api.dto.Event event, ) {
//        String name = event.getName();
//        String description = event.getDescription();
//        Timestamp deadline =  event.getDeadline();
//        Long statusId = event.getStatusId();
//
//        final long taskId = taskService.createTask(name, description, deadline, statusId);
//        final String taskUri = String.format("/tasks/%d", taskId);
//
//        return "events/overlook";
//    }
//


//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable long id) {
//        taskService.deleteTask(id);
//
//        return ResponseEntity.noContent().build();
//    }

}