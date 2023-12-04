package com.example.solution;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller

public class TasksController {
    private final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @PostMapping("/add")
    public String addTask(@RequestParam String name, @RequestParam String category, @RequestParam boolean done, @RequestParam String dueDate) {
        if (name.isEmpty()||dueDate instanceof String) {
            return "redirect:/err.html";
        }
        tasksService.create(new Task(name, category, done, LocalDate.parse(dueDate)));
        return "redirect:/index.html";
    }


    @RequestMapping("/update")
    public String update(@RequestParam Long id, @ModelAttribute Task tasks) {
        tasksService.update(id, tasks);
        return "redirect:/index.html";
    }

    @GetMapping("/archive")
    public String getAllDone(Model model) {
        model.addAttribute("allTasks", tasksService.getAllDone());
        return "tasks.html";
    }

    @GetMapping("/change")
    public String getTrueDone(@RequestParam Long id) {
        tasksService.updateDoneTrue(id);
        return "redirect:/index.html";
    }

    @GetMapping("/category")
    public String getCategory(@RequestParam String category, Model model) {
        if (category.isEmpty()) {
            return "redirect:/err.html";
        }
        model.addAttribute("allTasks", tasksService.getCategory(category));
        return "tasks.html";
    }


    @GetMapping("/actual")
    public String getAllNotDone(Model model) {
        model.addAttribute("allTasks", tasksService.getAllNotDone());
        return "tasks.html";

    }
}
