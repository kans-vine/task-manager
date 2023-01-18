package com.assignment.taskmanager.service;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.assignment.taskmanager.domain.Task;
import com.assignment.taskmanager.exception.InvalidRequestException;
import com.assignment.taskmanager.repository.TaskRepository;

/**
 * This is the implementation class for task service..
 * 
 * @author kans.samy
 * 
 */
@Service
public class TaskServiceImpl implements TaskService {
	private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

	private Set<String> taskStatus = new LinkedHashSet<>(Arrays.asList("Not Started", "In Progress", "Completed"));
	
	private final TaskRepository taskRepository;

	public TaskServiceImpl(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	/**
	 * Method to list all the tasks
	 */
	public List<Task> listTasks() {
		return taskRepository.findAll();
	}

	/**
	 * Method to add a task into the database. Throw an exception if the new task
	 * has the same title persisted in the db.
	 */
	public Task addTask(Task task) {
		List<Task> allTasks = listTasks();
		Long existingTasksCount = allTasks.stream()
				.filter(t -> t.getTitle().equalsIgnoreCase(task.getTitle())).count();
		if (existingTasksCount > 0) {
			log.debug("A given task is already exists : {}", task);
			throw new InvalidRequestException("A Task with the given title already exists!!");
		}
		if(!taskStatus.contains(task.getStatus())) {
			String possibleValues = String.join(", ", taskStatus);
			throw new InvalidRequestException("Invalid Task Status! Possilbe Values are: ["+possibleValues+"]" );
		}
		return taskRepository.save(task);
	}

}
