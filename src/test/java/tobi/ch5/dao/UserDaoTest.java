package tobi.ch5.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tobi.ch5.domain.Level;
import tobi.ch5.domain.User;

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
		user1 = new User(1L, "Nani", "nani", Level.GOLD, 1, 30);
		user2 = new User(2L, "Noel", "noel", Level.SILVER, 30, 50);
		user3 = new User(3L, "Genji", "genji", Level.BASIC, 10, 20);
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
	public void update() {
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(), is(2));

		user1.setLevel(Level.BASIC);
		user1.setName("Nani2");
		user1.setPassword("nani2");
		user1.setLogin(10);
		user1.setRecommend(100);

		dao.update(user1);
		assertThat(dao.getCount(), is(2));

		User user = dao.get(user1.getId());
		checkSameUser(user1, user);
		user = dao.get(user2.getId());
		checkSameUser(user2, user);
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
		assertThat(user1.getLevel(), is(user2.getLevel()));
		assertThat(user1.getLogin(), is(user2.getLogin()));
		assertThat(user1.getRecommend(), is(user2.getRecommend()));
	}
}
