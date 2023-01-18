package com.assignment.taskmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.assignment.taskmanager.domain.Task;
import com.assignment.taskmanager.exception.InvalidRequestException;
import com.assignment.taskmanager.repository.TaskRepository;
import com.assignment.taskmanager.service.TaskServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
	
	@Mock
	TaskRepository taskRepository;
	
	@InjectMocks
	private TaskServiceImpl taskService;
   

	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
    
	@Test
    void tesAddTask() throws JsonProcessingException, Exception {
    	Task task = new Task(1L, "Java Service", "SpringBoot microserice", "Not Started");
    	taskService.addTask(task);
    	verify(taskRepository).save(task);
	}
	
	@Test
	void testAddTaskWithExistingTitle() {
		Task task = new Task(1L, "Java Service", "SpringBoot microserice", "Not Started");
		List <Task> taskArray = new ArrayList<Task>();
		taskArray.add(task);
		Mockito.when(taskRepository.findAll())
        .thenReturn(taskArray);
    	
		Throwable exception = assertThrows(InvalidRequestException.class, () -> taskService.addTask(task));
	    assertEquals("A Task with the given title already exists!!", exception.getMessage());
	}
	
	@Test
	void testAddTaskWithWrongStatus() {
		Task task = new Task(1L, "Java Service", "SpringBoot microserice", "Started");
		List <Task> taskArray = new ArrayList<Task>();
		Mockito.when(taskRepository.findAll())
        .thenReturn(taskArray);
    	
		Throwable exception = assertThrows(InvalidRequestException.class, () -> taskService.addTask(task));
	    assertEquals("Invalid Task Status! Possilbe Values are: [Not Started, In Progress, Completed]", exception.getMessage());
	}

}