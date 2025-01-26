package com.masterprojekat.springserver;

import com.masterprojekat.springserver.model.User;
import com.masterprojekat.springserver.dao.UserDao;
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
		user.setUsername("nenad123");
		user.setName("Nenad");
		user.setSurname("Markovic");
		user.setPassword("123");
		user.setDate("12-09-1997");
		user.setEmail("nenad@gmail.com");
		user.setPhoneNumber("0661122333");
		user.setType("ucenik");
		userDao.save(user);
	}

}
