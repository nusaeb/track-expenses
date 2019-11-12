package com.trackexpenses.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("when unit testing UserService")
public class UserServiceTest {

	@Mock
	private UserRepository mockedUserRepository;
	@InjectMocks
	private UserService userService;
	
	@BeforeEach
	void publishTestReport(TestInfo testInfo, TestReporter testReporter) {
		testReporter.publishEntry(testInfo.getDisplayName());
	}
	
	@Nested
	@DisplayName("the get user method")
	class GetUserTest{
		
		@Test
		@DisplayName("should successfully get a user when given a non-null, valid String id")
		public void testGetUserWithValidId() {
			UserInfo userInfo = new UserInfo();
			userInfo.setUserId("test");
			Optional<UserInfo> expected = Optional.of(userInfo);
			when(mockedUserRepository.findById(any(String.class))).thenReturn(expected);
			UserInfo foundUserInfo = userService.getUser("test");
			assertThat(foundUserInfo.getUserId()).isEqualTo(expected.get().getUserId());
		}

		@Test
		@DisplayName("should throw null pointer exception when given a null id")
		public void testGetUserWithNullId() {
			assertThrows(NullPointerException.class, 
					() -> userService.getUser(null), 
					"Trying to find user with null id should throw null pointer exception.");
		}
	}
	
	@Nested
	@DisplayName("the add user method")
	class AddUserTest{
		
		@Test
		@DisplayName("should successfully add a user when given a non-null, valid UserInfo object")
		public void testAddUserWithValidUser() {
			UserInfo expectedUserInfo = new UserInfo("TestUser1", "FirstName", "LastName", "test@test.com", "+37200000000");
			when(mockedUserRepository.save(any(UserInfo.class))).thenReturn(expectedUserInfo);
			
			UserInfo actualUserInfo = userService.addUser(expectedUserInfo);
		
			assertThat(actualUserInfo.getFirstName()).isEqualTo(expectedUserInfo.getFirstName());
		}
		
		@Test
		@DisplayName("should throw null pointer exception when given a null object")
		public void testAddUserWithNullUser() {
			assertThrows(NullPointerException.class, 
					() -> userService.addUser(null), 
					"Trying to add null user should throw null pointer exception.");
		}
	}
	
	@Nested
	@DisplayName("the update user method")
	class UpdateUserTest{
		
		@Test
		@DisplayName("should successfully update a user when given a non-null, valid UserInfo object")
		public void testUpdateUserWithValidUser() {
			UserInfo expectedUserInfo = new UserInfo("TestUser1", "FirstName", "LastName", "test@test.com", "+37200000000");
			when(mockedUserRepository.save(any(UserInfo.class))).thenReturn(expectedUserInfo);
			
			UserInfo actualUserInfo = userService.updateUser(expectedUserInfo);
			
			assertThat(actualUserInfo.getFirstName()).isEqualTo(expectedUserInfo.getFirstName());
		}
		
		@Test
		@DisplayName("should throw null pointer exception when given a null object")
		public void testUpdateUserWithNullUser() {
			assertThrows(NullPointerException.class, 
					() -> userService.updateUser(null), 
					"Trying to update null user should throw null pointer exception.");
		}
	}

	@Nested
	@DisplayName("the delete user method")
	class DeleteUserTest{
		
		@Test
		@DisplayName("should successfully delete a user when given a non-null, valid String id")
		public void testDeleteUserWithValidId() {
			assertThat(userService.deleteUser("test")).isEqualToIgnoringCase("user deleted");
		}
		
		@Test
		@DisplayName("should throw null pointer exception when given a null id")
		public void testDeleteUserWithNullId() {
			assertThrows(NullPointerException.class, 
					() -> userService.deleteUser(null), 
					"Trying to delete user with null id should throw null pointer exception exception.");
		}
	}

}
