package com.andela.tms.requests;

import java.util.Date;

import com.andela.tms.entities.TaskStatusEnum;

public record UpdateTaskInput(TaskStatusEnum status, Date dueDate) {
    
}