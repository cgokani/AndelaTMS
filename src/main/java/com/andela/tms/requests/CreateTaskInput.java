package com.andela.tms.requests;

import java.util.Date;

import com.andela.tms.entities.Task;
import com.andela.tms.entities.TaskStatusEnum;

public record CreateTaskInput(String title, String description, TaskStatusEnum status, Date dueDate, Integer priority) {
    public Task toTask() {
        Task task = new Task();

        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        task.setDueDate(dueDate);
        task.setPriority(priority);

        return task;
    }
}