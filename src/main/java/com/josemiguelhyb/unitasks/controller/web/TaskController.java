package com.josemiguelhyb.unitasks.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.josemiguelhyb.unitasks.service.TaskService;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String home(Model model) {
        // Insertamos tareas iniciales si no existen
        taskService.insertSampleTasks();

        // Recuperamos todas las tareas
        model.addAttribute("message", "Tareas cargadas desde la BD con éxito");
        model.addAttribute("tasks", taskService.getAllTasks());

        return "index";
    }
    
    @PostMapping("/tasks/{id}/toggle")
    public String toggleTask(@PathVariable Long id) {
        taskService.toggleCompleted(id);
        return "redirect:/tasks";
    }

    
    @PostMapping("/tasks/add")
    public String addTask(@RequestParam String title,
                          @RequestParam String dueDate) {
        taskService.addTask(title, dueDate);
        return "redirect:/tasks";
    }
    
    @PostMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id); 
        return "redirect:/tasks";
    }

    @PostMapping("/tasks/edit/{id}")
    public String editTask(@PathVariable Long id,
                           @RequestParam String title,
                           @RequestParam String dueDate) {
        taskService.updateTask(id, title, dueDate);
        return "redirect:/tasks";
    }
    
    
}
