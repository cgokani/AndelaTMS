package com.andela.tms.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.util.Date;

@Table(name = "tasks")
@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Integer id;

	@Column(unique = true, length = 200, nullable = false)
	private String title;

	@Lob
	private String description;

	@Column(nullable = false, columnDefinition = "varchar(20) not null default 'TO_DO'")
	@Enumerated(EnumType.STRING)
	private TaskStatusEnum status;

	@Column(nullable = false)
	private Integer priority;

	@Column(name = "due_date")
	private Date dueDate;

	@CreationTimestamp
	@Column(updatable = false, name = "created_at")
	private Date createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskStatusEnum getStatus() {
		return status;
	}

	public void setStatus(TaskStatusEnum status) {
		this.status = status;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	

}