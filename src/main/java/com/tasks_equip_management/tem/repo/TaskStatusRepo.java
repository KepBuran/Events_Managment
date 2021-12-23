package com.tasks_equip_management.tem.repo;


import com.tasks_equip_management.tem.repo.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Table;
import java.util.List;


public interface TaskStatusRepo extends JpaRepository<TaskStatus, Long> {

}
