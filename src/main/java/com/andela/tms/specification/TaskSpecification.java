package com.andela.tms.specification;

import org.springframework.data.jpa.domain.Specification;

import com.andela.tms.entities.Task;
import com.andela.tms.entities.TaskStatusEnum;

public class TaskSpecification {

	
	public static Specification<Task> hasTitle(String title) {
	    return (task, cq, cb) -> cb.equal(task.get("title"), title);
	}
	
	public static Specification<Task> hasDescription(String description) {
	    return (task, cq, cb) -> cb.equal(task.get("description"), description);
	}
	
	public static Specification<Task> hasStatus(TaskStatusEnum status) {
	    return (task, cq, cb) -> cb.equal(task.get("status"), status);
	}
}
