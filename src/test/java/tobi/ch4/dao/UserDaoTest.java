package tobi.ch4.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tobi.ch4.domain.User;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoFactory.class)
public class UserDaoTest {
	@Autowired
	private UserDao dao;

	private User user1;
	private User user2;
	private User user3;

	@Before
	public void setUp() {
		user1 = new User(1L, "Nani", "nani");
		user2 = new User(2L, "Noel", "noel");
		user3 = new User(3L, "Genji", "genji");
	}

	@Test
	public void addAndGet() {
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(), is(2));

		User user = dao.get(user1.getId());
		checkSameUser(user, user1);

		user = dao.get(user2.getId());
		checkSameUser(user, user2);
	}

	@Test
	public void getAll() {
		dao.deleteAll();

		List<User> users = dao.getAll();
		assertThat(users.size(), is(0));

		dao.add(user1);
		users = dao.getAll();
		assertThat(users.size(), is(1));
		checkSameUser(users.get(0), user1);

		dao.add(user2);
		users = dao.getAll();
		assertThat(users.size(), is(2));
		checkSameUser(users.get(0), user1);
		checkSameUser(users.get(1), user2);

		dao.add(user3);
		users = dao.getAll();
		assertThat(users.size(), is(3));
		checkSameUser(users.get(0), user1);
		checkSameUser(users.get(1), user2);
		checkSameUser(users.get(2), user3);
	}

	@Test
	public void count() {
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
	public void getUserFailure() {
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		dao.get(-1L);
	}

	private void checkSameUser(User user1, User user2) {
		assertThat(user1.getId(), is(user2.getId()));
		assertThat(user1.getName(), is(user2.getName()));
		assertThat(user1.getPassword(), is(user2.getPassword()));
	}
}
