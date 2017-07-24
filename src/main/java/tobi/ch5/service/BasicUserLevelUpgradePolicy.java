package tobi.ch5.service;

import tobi.ch5.domain.User;

public class BasicUserLevelUpgradePolicy implements UserLevelUpgradePolicy {
	public static final int MIN_LOGIN_COUNT_FOR_SILVER = 50;
	public static final int MIN_RECOMMEND_COUNT_FOR_GOLD = 30;

	@Override
	public boolean canUpgradeLevel(User user) {
		switch (user.getLevel()) {
			case BASIC:
				return user.getLogin() >= MIN_LOGIN_COUNT_FOR_SILVER;
			case SILVER:
				return user.getRecommend() >= MIN_RECOMMEND_COUNT_FOR_GOLD;
			case GOLD:
				return false;
			default:
				throw new IllegalArgumentException("Unknown level + " + user.getLevel());
		}
	}

	@Override
	public void upgradeLevel(User user) {
		user.upgradeLevel();
	}
}
