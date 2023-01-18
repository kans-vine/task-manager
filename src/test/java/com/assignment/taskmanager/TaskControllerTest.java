package com.assignment.taskmanager;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.assignment.taskmanager.domain.Task;
import com.assignment.taskmanager.exception.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	Task task;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testVerifyTheUserIsAuthorized() throws Exception {
		mockMvc.perform(get("/api/tasks").contentType("application/json")).andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser(value = "user")
	void testVerifyTheRequestIsAuthorized() throws Exception {
		mockMvc.perform(get("/api/tasks").contentType("application/json")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(value = "user")
	void testVerifyTheRequestIsBadRequest() throws Exception {
		mockMvc.perform(post("/api/tasks").contentType("application/json")).andExpect(status().isBadRequest());
	}

	@Test
	@WithMockUser(value = "user")
	void testAddNewTaskWithEmptyTitleAndEmptyStatus() throws JsonProcessingException, Exception {
		Task task = new Task();
		MvcResult result = mockMvc.perform(
				post("/api/tasks").contentType("application/json").content(objectMapper.writeValueAsString(task)))
				.andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		ErrorResponse error_response = objectMapper.readValue(contentAsString, ErrorResponse.class);
		assertTrue(error_response.getErrors().contains("Task title cannot be blank")
				&& error_response.getErrors().contains("Task status cannot be blank"));
	}

	@Test
	@WithMockUser(value = "user")
	void testAddNewTaskWithTitleMoreThanExpectedCharacters() throws JsonProcessingException, Exception {
		Task task = new Task(1L,
				"This is java based service to manage the tasks given. This service offers couple of endpoints. Consumes json and produces json.",
				"Java microservice", "Not Started");
		MvcResult result = mockMvc.perform(
				post("/api/tasks").contentType("application/json").content(objectMapper.writeValueAsString(task)))
				.andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		ErrorResponse error_response = objectMapper.readValue(contentAsString, ErrorResponse.class);
		assertTrue(error_response.getErrors().contains("Task title must be between 1-100 characters"));
	}

	@Test
	@WithMockUser(value = "user")
	void testAddNewTaskWithNoStatus() throws JsonProcessingException, Exception {
		Task task = new Task();
		MvcResult result = mockMvc.perform(
				post("/api/tasks").contentType("application/json").content(objectMapper.writeValueAsString(task)))
				.andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		ErrorResponse error_response = objectMapper.readValue(contentAsString, ErrorResponse.class);
		assertTrue(error_response.getErrors().contains("Task status cannot be blank"));
	}

	@Test
	@WithMockUser(value = "user")
	void testAddNewTaskWithIDAsRequestBody() throws JsonProcessingException, Exception {
		Task task = new Task(1L, "Java Service", "SpringBoot microserice", "Not Started");
		MvcResult result = mockMvc.perform(
				post("/api/tasks").contentType("application/json").content(objectMapper.writeValueAsString(task)))
				.andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		ErrorResponse error_response = objectMapper.readValue(contentAsString, ErrorResponse.class);
		assertTrue(error_response.getErrors().contains("A new task cannot already have an id"));
	}

}
