package com.trackexpenses.user;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK, 
		classes = UserApplication.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("when running integration test")
public class UserApplicationIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private UserService userService;
	
	private UserInfo testUserInfo;
	
	@BeforeAll
	public void init() {
		userService.addUser(new UserInfo("testId1", "testName1", "testLastName1", "test.user.1@test.com", "+123456789"));
		userService.addUser(new UserInfo("testId2", "testName2", "testLastName2", "test.user.2@test.com", "+123456789"));
	}
	
	@BeforeEach
	public void createTestUser() {
		testUserInfo = new UserInfo("testId3", "testName3", "testLastName3", "test.user.3@test.com", "+123456789");
	}

	@Test
	@DisplayName("the getAllUsers method should return a JSON array and status 200")
	public void whenGetAllUsers_thenStatus200() throws Exception{
		
		mockMvc.perform(get("/users")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].firstName", is("testName1")));
	}
	
	@Nested
	@DisplayName("the getUser method")
	class TestGetUser{
		@Test
		@DisplayName("should return status 200 when user is found")
		public void whenUserFound_thenReturns200() throws Exception {
		
			mockMvc.perform(get("/users/{userId}", "testId1")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.userId", is("testId1")));
		}
		
		@Test
		@DisplayName("should return status 404 when user is not found")
		public void whenUserNotFound_thenReturns404() throws Exception {
					
			mockMvc.perform(get("/users/{userId}", "noUserWithId")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isNotFound());
		}
	}
	
	@Nested
	@DisplayName("the addUser method")
	class TestAddUser{
		@Test
		@DisplayName("should return status 200 when user is added successfully")
		public void whenValidInput_thenReturns200() throws Exception {
		
			mockMvc.perform(post("/users")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(testUserInfo)))
					.andExpect(status().isOk());
		}
		
		@Test
		@DisplayName("should return status 400 when null/empty field in input")
		public void whenBlankField_thenReturns400() throws Exception {
			
			testUserInfo.setFirstName("");
		
			mockMvc.perform(post("/users")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(testUserInfo)))
					.andExpect(status().isBadRequest());
		}
		
		@Test
		@DisplayName("should return status 400 when invalid email in input")
		public void whenInvalidEmail_thenReturns400() throws Exception {
			
			testUserInfo.setEmail("invalidEmail.com");
		
			mockMvc.perform(post("/users")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(testUserInfo)))
					.andExpect(status().isBadRequest());
		}
	}
	
	@Nested
	@DisplayName("the updateUser method")
	class TestUpdateUser{
		@Test
		@DisplayName("should return status 200 when user is updated successfully")
		public void whenValidInput_thenReturns200() throws Exception {
		
			testUserInfo.setUserId("testId1");
			testUserInfo.setFirstName("newFirstName");
			
			mockMvc.perform(put("/users/{userId}", "testId1")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(testUserInfo)))
					.andExpect(status().isOk());
		}
		
		@Test
		@DisplayName("should return status 400 when null/empty field in input")
		public void whenBlankField_thenReturns400() throws Exception {
			
			testUserInfo.setUserId("testId1");
			testUserInfo.setFirstName("");
		
			mockMvc.perform(put("/users/{userId}", "testId1")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(testUserInfo)))
					.andExpect(status().isBadRequest());
		}
		
		@Test
		@DisplayName("should return status 400 when invalid email in input")
		public void whenInvalidEmail_thenReturns400() throws Exception {
			
			testUserInfo.setUserId("testId1");
			testUserInfo.setEmail("newEmail");
		
			mockMvc.perform(put("/users/{userId}", "testId1")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(testUserInfo)))
					.andExpect(status().isBadRequest());
		}
	}
	
	@Nested
	@DisplayName("the deleteUser method")
	class TestDeleteUser{
		@Test
		@DisplayName("should return status 200 when user is deleted successfully")
		public void whenValidInput_thenReturns200() throws Exception {
		
			mockMvc.perform(delete("/users/{userId}", "testId2")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		}
		
		@Test
		@DisplayName("should return status 404 when user is not found")
		public void whenUserNotFound_thenReturns404() throws Exception {
					
			mockMvc.perform(delete("/users/{userId}", "noUserWithId")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isNotFound());
		}
	}

}