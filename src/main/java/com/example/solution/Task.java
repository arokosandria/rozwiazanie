package com.example.solution;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String category;
    @Column
    private boolean done;
    @Column
    private LocalDate dueDate;

    public Task(String name, String category, boolean done, LocalDate dueDate) {
        this.name = name;
        this.category = category;
        this.done = done;
        this.dueDate = dueDate;
    }

}
