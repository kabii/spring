package tobi.ch2.dao;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import tobi.ch2.domain.User;

import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserDaoTest {
	@Test
	public void addAndGet() throws SQLException {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

		UserDao dao = context.getBean("userDao", UserDao.class);
		User user1 = new User(1L, "Nani", "nani");
		User user2 = new User(2L, "Noel", "noel");

		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(), is(2));

		User u1 = dao.get(user1.getId());
		assertThat(u1.getName(), is(user1.getName()));
		assertThat(u1.getPassword(), is(user1.getPassword()));

		User u2 = dao.get(user2.getId());
		assertThat(u2.getName(), is(user2.getName()));
		assertThat(u2.getPassword(), is(user2.getPassword()));
	}

	@Test
	public void count() throws SQLException {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

		UserDao dao = context.getBean("userDao", UserDao.class);
		User user1 = new User(1L, "Nani", "nani");
		User user2 = new User(2L, "Noel", "noel");
		User user3 = new User(3L, "Genji", "genji");

		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		dao.add(user1);
		assertThat(dao.getCount(), is(1));

		dao.add(user2);
		assertThat(dao.getCount(), is(2));

		dao.add(user3);
		assertThat(dao.getCount(), is(3));
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

		UserDao dao = context.getBean("userDao", UserDao.class);
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		dao.get(-1L);
	}

	public static void main(String[] args) {
		JUnitCore.main("tobi.ch2.dao.UserDaoTest");
	}
}
