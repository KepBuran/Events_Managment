package com.tasks_equip_management.tem.repo;

import com.tasks_equip_management.tem.repo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Table;
import javax.transaction.Transactional;
import java.util.List;


public interface TaskRepo extends JpaRepository<Task, Long> {

    @Query("FROM Task t WHERE t.eventId = ?1")
    List<Task> findByEventId(long eventID);

    @Modifying
    @Query("DELETE FROM Task t WHERE t.id = ?1")
    Integer deleteById(long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Task t WHERE t.eventId = ?1")
    Integer deleteTasksByEventId(long event_id);
}
