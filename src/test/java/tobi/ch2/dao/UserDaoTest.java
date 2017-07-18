package tobi.ch2.dao;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobi.ch1.domain.User;

import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserDaoTest {
	@Test
	public void addAndGet() throws SQLException {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);

		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		User user = new User();
		user.setId(1L);
		user.setName("Nani");
		user.setPassword("nani");

		dao.add(user);
		assertThat(dao.getCount(), is(1));

		System.out.println(user.getId() + " registration success");

		User user2 = dao.get(user.getId());
		assertThat(user2.getName(), is(user.getName()));
		assertThat(user2.getPassword(), is(user.getPassword()));
	}

	public static void main(String[] args) {
		JUnitCore.main("tobi.ch2.dao.UserDaoTest");
	}
}
