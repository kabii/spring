package tobi.ch5.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserTest {
	User user;

	@Before
	public void setUp() {
		user = new User();
	}

	@Test
	public void upgradeLevel() {
		for (Level level: Level.values()) {
			if (level == Level.GOLD)
				continue;
			user.setLevel(level);
			user.upgradeLevel();
			assertThat(user.getLevel(), is(level.getNextLevel()));
		}
	}

	@Test(expected = IllegalStateException.class)
	public void cannotUpgradeLevel() {
		for (Level level: Level.values()) {
			if (level != Level.GOLD)
				continue;
			user.setLevel(level);
			user.upgradeLevel();
		}
	}
}