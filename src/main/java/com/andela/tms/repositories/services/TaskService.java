package com.andela.tms.repositories.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.stereotype.Service;

import com.andela.tms.entities.Task;
import com.andela.tms.entities.TaskStatusEnum;
import com.andela.tms.repositories.TaskRepository;
import com.andela.tms.requests.FilterTaskInput;
import com.andela.tms.specification.TaskSpecification;

import jakarta.persistence.criteria.Predicate;

@Service
public class TaskService {

	private final TaskRepository taskRepository;

	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public Task create(Task task) {
		return taskRepository.save(task);
	}

	public List<Task> findAll() {
		List<Task> tasks = new ArrayList<>();
		taskRepository.findAll().forEach(tasks::add);

		return tasks;
	}

	public Optional<Task> findById(int id) {
		return taskRepository.findById(id);
	}

	public Task update(Task taskToUpdate) {
		return taskRepository.save(taskToUpdate);
	}

	public void delete(int id) {
		taskRepository.deleteById(id);
	}

	public List<Task> findByTitle(String title) {
		return taskRepository.findAll(TaskSpecification.hasTitle(title));
	}

	public List<Task> findByDescription(String description) {
		return taskRepository.findAll(TaskSpecification.hasDescription(description));
	}

	public List<Task> findByStatus(TaskStatusEnum status) {
		return taskRepository.findAll(TaskSpecification.hasStatus(status));
	}

	public List<Task> filterTasks(FilterTaskInput filterTaskInput) {
		return taskRepository
				.findAll((TaskSpecification.hasTitle(filterTaskInput.title()).and(TaskSpecification.hasDescription(filterTaskInput.description()).and(TaskSpecification.hasStatus(filterTaskInput.status())))));
	}
}
