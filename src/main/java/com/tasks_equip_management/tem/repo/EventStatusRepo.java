package com.tasks_equip_management.tem.repo;


import com.tasks_equip_management.tem.repo.model.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventStatusRepo extends JpaRepository<EventStatus, Long> {

}