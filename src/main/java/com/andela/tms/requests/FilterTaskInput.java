package com.andela.tms.requests;

import com.andela.tms.entities.TaskStatusEnum;

public record FilterTaskInput(String title, String description, TaskStatusEnum status) {
    
}