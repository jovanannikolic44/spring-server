package com.masterpojekat.springserver;

import com.masterpojekat.springserver.models.User;
import com.masterpojekat.springserver.models.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringServerApplicationTests {

	// Can be autowired because UserDao is specified to be @Service
	@Autowired
	private UserDao userDao;

	@Test
	void addUserTest() {
		User user = new User();
		user.setUsername("ana123");
		user.setName("Ana");
		user.setSurname("Anic");
		user.setPassword("123");
		user.setDate("12-09-1997");
		user.setEmail("ana@gmail.com");
		user.setPhoneNumber("0641122333");
		user.setType("ucenik");
		userDao.save(user);
	}

}
