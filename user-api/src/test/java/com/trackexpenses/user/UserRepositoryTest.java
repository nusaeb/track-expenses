package com.trackexpenses.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/* 
 * Ideally, there is no need to unit test codes that's not written by us, e.g. the basic
 * CRUD methods from CrudRepository. But I did it here just for learning purpose.
*/
@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("when unit testing UserRepository")
public class UserRepositoryTest {
	
	@Autowired
	private TestEntityManager mockedTestEntityManager;
	@Autowired
	private UserRepository mockedUserRepository;
	
	@Test
	@DisplayName("the find by id method should retrieve the user entity from DB")
	void testFindByIdSuccess() {
		UserInfo expectedUserInfo = new UserInfo("testId", "testFirstName", "testLastName", "test.email@test.com", "+37200000000");
		mockedTestEntityManager.persist(expectedUserInfo);
		mockedTestEntityManager.flush();
		
		UserInfo actualUserInfo = mockedUserRepository.findById("testId").get();
		
		assertThat(actualUserInfo.getUserId()).isEqualTo(expectedUserInfo.getUserId());
	}
	
	@Test()
	@DisplayName("the find by id method should throw NoSuchElementException when a matched entity is not found")
	void testFindByIdNotFound() {
		
		assertThrows(java.util.NoSuchElementException.class,
				() -> mockedUserRepository.findById("test").get(),
				"NoSuchElementException is thrown when a matched entity is not found");
		
	}

}
