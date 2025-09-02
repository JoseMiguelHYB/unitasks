package com.josemiguelhyb.unitasks.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.josemiguelhyb.unitasks.model.Task;
import com.josemiguelhyb.unitasks.repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Crear tareas de ejemplo (a pelo, solo al principio)
    public void insertSampleTasks() {
        if (taskRepository.count() == 0) { // para no duplicar siempre
        	taskRepository.save(new Task("Comprar pan", false, LocalDate.now().plusDays(1)));
        	taskRepository.save(new Task("Estudiar Spring Boot", false, LocalDate.now().plusDays(2)));
        	taskRepository.save(new Task("Hacer ejercicio", false, LocalDate.now().plusDays(1)));
        	taskRepository.save(new Task("Leer un libro", false, LocalDate.now().plusDays(3)));
        	taskRepository.save(new Task("Llamar a mamá", false, LocalDate.now()));

        	// Agrego ~30 más
        	taskRepository.save(new Task("Preparar la presentación", false, LocalDate.now().plusDays(2)));



        }
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    @Transactional
    public void toggleCompleted(Long id) {
        Task t = taskRepository.findById(id).orElseThrow();
        t.setCompleted(!t.isCompleted()); // alternar
        // Al cerrar la transacción Hibernate hace el UPDATE
    }
    
    @Transactional
    public void addTask(String title, String dueDate) {
        Task t = new Task(title, false, LocalDate.parse(dueDate));
        taskRepository.save(t);
    }

    
    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    
    public void updateTask(Long id, String title, String dueDate) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTitle(title);
        task.setDueDate(LocalDate.parse(dueDate));
        taskRepository.save(task);
    }

    
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
    
    
    
    @Transactional
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task no encontrada con id " + id));

        task.setTitle(taskDetails.getTitle());
        task.setDueDate(taskDetails.getDueDate());
        task.setCompleted(taskDetails.isCompleted());

        return taskRepository.save(task);
    }
    
    
}
