package tobi.ch5.service;

import tobi.ch5.domain.User;

public interface UserLevelUpgradePolicy {
	boolean canUpgradeLevel(User user);
	void upgradeLevel(User user);
}
