package com.assignment.taskmanager.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.taskmanager.domain.Task;
import com.assignment.taskmanager.exception.InvalidRequestException;
import com.assignment.taskmanager.service.TaskService;

/**
 * REST controller for managing {@link com.taskmanager.domain.Task}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TaskController {

	private final Logger log = LoggerFactory.getLogger(TaskController.class);

	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	/**
	 * {@code POST  /tasks} : Create a new task.
	 *
	 * @param task the task to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new task, or with status {@code 400 (Bad Request)} if the
	 *         task has already an ID.
	 */
	@PostMapping("/tasks")
	public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
		log.debug("REST request to save Task : {}", task);
		if (task.getId() != null) {
			throw new InvalidRequestException("A new task cannot already have an id");
		}
		return ResponseEntity.ok(taskService.addTask(task));
	}

	/**
	 * {@code GET  /tasks} : get all the tasks.
	 * 
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of tasks in body.
	 */
	@GetMapping("/tasks")
	public List<Task> getAllTasks() {
		log.debug("REST request to get all Tasks");
		return taskService.listTasks();
	}

}
