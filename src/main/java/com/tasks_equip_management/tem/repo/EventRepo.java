package com.tasks_equip_management.tem.repo;

import com.tasks_equip_management.tem.repo.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EventRepo extends JpaRepository<Event, Long> {

    @Modifying
    @Query("DELETE FROM Event e WHERE e.id = ?1")
    Integer deleteById(long event_id);

//    @Modifying
//    @Query("DELETE FROM Task t WHERE t.eventId = ?1")
//    Integer deleteTasksById(long event_id) {
//    };
}

