package com.example.solution;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TasksService {
    private final TasksRepository tasksRepository;

    public Task create(Task tasks) {
        return tasksRepository.save(tasks);
    }

    public List<Task> getAllDone() {
        return new ArrayList<>(tasksRepository.findByDoneTrue());

    }

    public List<Task> getCategory(String category) {
        return new ArrayList<>(tasksRepository.findByCategory(category));

    }

    public List<Task> getAllNotDone() {
        return new ArrayList<>(tasksRepository.findByDoneFalse());

    }

    public void update(Long id, Task tasks) {
        Optional<Task> optionalExistingTask = tasksRepository.findById(id);

        if (optionalExistingTask.isPresent()) {
            Task existingTask = optionalExistingTask.get();
            if (!tasks.getName().isBlank()) {
                existingTask.setName(tasks.getName());
            }
            if (!tasks.getCategory().isBlank()) {
                existingTask.setCategory(tasks.getCategory());
            }
            if (tasks.getDueDate() != null) {
                existingTask.setDueDate(tasks.getDueDate());
            }
            tasksRepository.save(existingTask);
        }
    }

    public void updateDoneTrue(Long id) {
        tasksRepository.findById(id)
                .ifPresent(tasksUpdate -> {
                    tasksUpdate.setDone(true);
                    tasksRepository.save(tasksUpdate);
                });
    }
}
