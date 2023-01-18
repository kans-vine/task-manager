package com.assignment.taskmanager.service;

import java.util.List;

import com.assignment.taskmanager.domain.Task;

/**
 * An interface for Task Service
 * 
 * @author kans.samy
 *
 */
public interface TaskService {

	// Method to list all tasks
	public List<Task> listTasks();

	// Method to add a task
	public Task addTask(Task task);

}
