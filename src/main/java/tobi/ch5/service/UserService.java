package tobi.ch5.service;

import org.springframework.beans.factory.annotation.Autowired;
import tobi.ch5.dao.UserDao;
import tobi.ch5.domain.Level;
import tobi.ch5.domain.User;

import java.util.List;

public class UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private UserLevelUpgradePolicy userLevelUpgradePolicy;

	public void add(User user) {
		if (user.getLevel() == null) {
			user.setLevel(Level.BASIC);
		}
		userDao.add(user);
	}

	public void upgradeLevels() {
		List<User> userList = userDao.getAll();
		for (User user: userList) {
			if (canUpgradeLevel(user)) {
				upgradeLevel(user);
			}
		}
	}

	private boolean canUpgradeLevel(User user) {
		return userLevelUpgradePolicy.canUpgradeLevel(user);
	}

	private void upgradeLevel(User user) {
		userLevelUpgradePolicy.upgradeLevel(user);
		userDao.update(user);
	}
}
