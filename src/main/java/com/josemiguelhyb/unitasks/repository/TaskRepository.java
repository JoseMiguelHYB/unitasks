package com.josemiguelhyb.unitasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.josemiguelhyb.unitasks.model.Task;

// Esto nos da un CRUD completo sin escribir ningnuna senetencia SQL
public interface TaskRepository extends JpaRepository <Task, Long> {
}
