package com.josemiguelhyb.unitasks.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name="tasks")
@Data       // Ya genera los getters, setters, toString(), equals, hashCode
@NoArgsConstructor // Crea un constructor vacío (lo necesita JPA)
@AllArgsConstructor // Crea un constructor con todos los campos
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private boolean completed = false;

    private LocalDate dueDate; // Fecha de vencimiento

}