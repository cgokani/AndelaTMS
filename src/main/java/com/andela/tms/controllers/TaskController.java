package com.andela.tms.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.andela.tms.entities.Task;
import com.andela.tms.entities.TaskStatusEnum;
import com.andela.tms.repositories.services.TaskService;
import com.andela.tms.requests.CreateTaskInput;
import com.andela.tms.requests.FilterTaskInput;
import com.andela.tms.requests.UpdateTaskInput;

@RestController
public class TaskController {
    public TaskService taskService;
    
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    
    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody CreateTaskInput createTaskInput) {
        Task taskCreated = taskService.create(createTaskInput.toTask());

        return new ResponseEntity<>(taskCreated, HttpStatus.CREATED);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> allTasks() {
        List<Task> tasks = taskService.findAll();

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
    
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> oneTask(@PathVariable int id) {
        Optional<Task> optionalTask = taskService.findById(id);

        if (optionalTask.isPresent()) {
            return new ResponseEntity<>(optionalTask.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    
    @GetMapping("/tasks/title/{title}")
    public ResponseEntity<List<Task>> getTaskbByTitle(@PathVariable String title) {
    	List<Task> tasks = taskService.findByTitle(title);

    	return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
    
    @GetMapping("/tasks/description/{description}")
    public ResponseEntity<List<Task>> getTaskbByDesciption(@PathVariable String description) {
    	List<Task> tasks = taskService.findByDescription(description);

    	return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
    
    @GetMapping("/tasks/status/{status}")
    public ResponseEntity<List<Task>> getTaskbByStatus(@PathVariable TaskStatusEnum status) {
    	List<Task> tasks = taskService.findByStatus(status);

    	return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
    
    @PatchMapping("/tasks/filter")
    public ResponseEntity<List<Task>> filterTasks(@RequestBody FilterTaskInput filterTaskInput) {
    	List<Task> tasks = taskService.filterTasks(filterTaskInput);

    	return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
    
    
    @PatchMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody UpdateTaskInput updateTaskInput) {
        Optional<Task> optionalTask = taskService.findById(id);

        if (optionalTask.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Task taskToUpdate = optionalTask.get();

        taskToUpdate.setStatus(updateTaskInput.status());
        taskToUpdate.setDueDate(updateTaskInput.dueDate());

        Task taskUpdated = taskService.update(taskToUpdate);

        return new ResponseEntity<>(taskUpdated, HttpStatus.OK);
    }
    
    
    @DeleteMapping("/tasks/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        taskService.delete(id);

        return ResponseEntity.noContent().build();
    }
      
    
    
}