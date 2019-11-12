package com.trackexpenses.user;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trackexpenses.user.exception.UserNotFoundException;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@DisplayName("when unit testing UserController")
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private UserService mockedUserService;
	
	@Test
	@DisplayName("the getAllUsers method should return a JSON array and status 200")
	public void whenGetAllUsers_thenStatus200() throws Exception {
		
		List<UserInfo> users = Arrays.asList(
				new UserInfo("testId1", "testName1", "testLastName1", "test@test.com", "+123456789"),
				new UserInfo("testId2", "testName2", "testLastName2", "test@test.com", "+123456789")
				);
		when(mockedUserService.getAllUsers()).thenReturn(users);
		
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
			
			UserInfo user = new UserInfo("testId1", "testName1", "testLastName1", "test@test.com", "+123456789");
			when(mockedUserService.getUser(any(String.class))).thenReturn(user);
		
			mockMvc.perform(get("/users/{userId}", "testId1")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.userId", is("testId1")));
		}
		
		@Test
		@DisplayName("should return status 404 when user is not found")
		public void whenUserNotFound_thenReturns404() throws Exception {
			
			when(mockedUserService.getUser(any(String.class))).thenThrow(UserNotFoundException.class);
		
			mockMvc.perform(get("/users/{userId}", "testId1")
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
			
			UserInfo user = new UserInfo("testId1", "testName1", "testLastName1", "test@test.com", "+123456789");
			when(mockedUserService.addUser(any(UserInfo.class))).thenReturn(user);
		
			mockMvc.perform(post("/users")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(user)))
					.andExpect(status().isOk());
		}
		
		@Test
		@DisplayName("should return status 400 when null/empty field in input")
		public void whenBlankField_thenReturns400() throws Exception {
			
			UserInfo user = new UserInfo("testId1", "", "", "test@test.com", "+123456789");
			when(mockedUserService.addUser(any(UserInfo.class))).thenReturn(user);
		
			mockMvc.perform(post("/users")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(user)))
					.andExpect(status().isBadRequest());
		}
		
		@Test
		@DisplayName("should return status 400 when invalid email in input")
		public void whenInvalidEmail_thenReturns400() throws Exception {
			
			UserInfo user = new UserInfo("testId1", "testName", "testLastName", "test.com", "+123456789");
			when(mockedUserService.addUser(any(UserInfo.class))).thenReturn(user);
		
			mockMvc.perform(post("/users")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(user)))
					.andExpect(status().isBadRequest());
		}
	}
	
	@Nested
	@DisplayName("the updateUser method")
	class TestUpdateUser{
		@Test
		@DisplayName("should return status 200 when user is updated successfully")
		public void whenValidInput_thenReturns200() throws Exception {
			
			UserInfo user = new UserInfo("testId1", "testName1", "testLastName1", "test@test.com", "+123456789");
			when(mockedUserService.updateUser(any(UserInfo.class))).thenReturn(user);
		
			mockMvc.perform(put("/users/{userId}", "testId1")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(user)))
					.andExpect(status().isOk());
		}
		
		@Test
		@DisplayName("should return status 400 when null/empty field in input")
		public void whenBlankField_thenReturns400() throws Exception {
			
			UserInfo user = new UserInfo("testId1", "", "", "test@test.com", "+123456789");
			when(mockedUserService.updateUser(any(UserInfo.class))).thenReturn(user);
		
			mockMvc.perform(put("/users/{userId}", "testId1")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(user)))
					.andExpect(status().isBadRequest());
		}
		
		@Test
		@DisplayName("should return status 400 when invalid email in input")
		public void whenInvalidEmail_thenReturns400() throws Exception {
			
			UserInfo user = new UserInfo("testId1", "testName", "testLastName", "test.com", "+123456789");
			when(mockedUserService.updateUser(any(UserInfo.class))).thenReturn(user);
		
			mockMvc.perform(put("/users/{userId}", "testId1")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(user)))
					.andExpect(status().isBadRequest());
		}
	}
	
	@Nested
	@DisplayName("the deleteUser method")
	class TestDeleteUser{
		@Test
		@DisplayName("should return status 200 when user is deleted successfully")
		public void whenValidInput_thenReturns200() throws Exception {
			
			when(mockedUserService.deleteUser(any(String.class))).thenReturn("user deleted");
		
			mockMvc.perform(delete("/users/{userId}", "testId1")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		}
		
		@Test
		@DisplayName("should return status 404 when user is not found")
		public void whenUserNotFound_thenReturns404() throws Exception {
			
			when(mockedUserService.deleteUser(any(String.class))).thenThrow(UserNotFoundException.class);
		
			mockMvc.perform(delete("/users/{userId}", "testId1")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isNotFound());
		}
	}
	
	
}
