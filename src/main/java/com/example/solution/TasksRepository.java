package com.example.solution;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TasksRepository extends JpaRepository<Task, Long> {
    List<Task> findByDoneTrue();

    List<Task> findByDoneFalse();

    Optional<Task> findById(Long id);

    List<Task> findByCategory(String category);
}

