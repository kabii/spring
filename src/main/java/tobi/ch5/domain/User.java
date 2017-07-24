package tobi.ch5.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class User {
	private Long id;
	private String name;
	private String password;

	private Level level;
	private int login;
	private int recommend;

	private Date lastUpgraded;

	public User(Long id, String name, String password, Level level, int login, int recommend) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.level = level;
		this.login = login;
		this.recommend = recommend;
	}

	public void upgradeLevel() {
		Level nextLevel = level.getNextLevel();
		if (nextLevel == null) {
			throw new IllegalStateException("Unable to upgrad level : " + level);
		}
		level = nextLevel;
		lastUpgraded = new Date();
	}
}
