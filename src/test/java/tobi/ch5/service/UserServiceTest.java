package tobi.ch5.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tobi.ch5.dao.DaoFactory;
import tobi.ch5.dao.UserDao;
import tobi.ch5.domain.Level;
import tobi.ch5.domain.User;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static tobi.ch5.service.BasicUserLevelUpgradePolicy.MIN_LOGIN_COUNT_FOR_SILVER;
import static tobi.ch5.service.BasicUserLevelUpgradePolicy.MIN_RECOMMEND_COUNT_FOR_GOLD;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoFactory.class)
public class UserServiceTest {
	@Autowired
	private UserService userService;

	@Autowired
	private UserDao dao;

	private List<User> userList;

	@Before
	public void setUp() {
		userList = Arrays.asList(
			new User(1L, "Nani", "nani", Level.BASIC, MIN_LOGIN_COUNT_FOR_SILVER - 1, 30),
			new User(2L, "Noel", "noel", Level.BASIC, MIN_LOGIN_COUNT_FOR_SILVER, 30),
			new User(3L, "Shape", "shape", Level.SILVER, 50, MIN_RECOMMEND_COUNT_FOR_GOLD - 1),
			new User(4L, "Doren", "doren", Level.SILVER, 50, MIN_RECOMMEND_COUNT_FOR_GOLD),
			new User(5L, "Kevin", "kevin", Level.GOLD, 50, 50));
	}

	@Test
	public void add() {
		dao.deleteAll();

		User userWithLevel = userList.get(4);
		User userWithoutLevel = userList.get(0);
		userWithoutLevel.setLevel(null);

		userService.add(userWithLevel);
		userService.add(userWithoutLevel);

		User user1 = dao.get(userWithLevel.getId());
		User user2 = dao.get(userWithoutLevel.getId());

		assertThat(user1.getLevel(), is(userWithLevel.getLevel()));
		assertThat(user2.getLevel(), is(Level.BASIC));
	}

	@Test
	public void upgradeLevels() {
		dao.deleteAll();
		for (User user: userList) {
			dao.add(user);
		}

		userService.upgradeLevels();
		checkLevel(userList.get(0), false);
		checkLevel(userList.get(1), true);
		checkLevel(userList.get(2), false);
		checkLevel(userList.get(3), true);
		checkLevel(userList.get(4), false);
	}

	@Test
	public void bean() {
		assertThat(userService, is(notNullValue()));
	}

	private void checkLevel(User user, boolean upgraded) {
		User upgradedUser = dao.get(user.getId());
		if (upgraded) {
			assertThat(upgradedUser.getLevel(), is(user.getLevel().getNextLevel()));
		} else {
			assertThat(upgradedUser.getLevel(), is(user.getLevel()));
		}
	}
}