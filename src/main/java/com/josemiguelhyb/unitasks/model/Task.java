package com.josemiguelhyb.unitasks.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private boolean completed = false;

    private LocalDate dueDate; // Fecha de vencimiento

    public Task() { }

    public Task(String title, boolean completed, LocalDate dueDate) {
        this.title = title;
        this.completed = completed;
        this.dueDate = dueDate;
    }   

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	@Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", completed=" + completed +
                ", dueDate=" + dueDate +
                '}';
    }
}