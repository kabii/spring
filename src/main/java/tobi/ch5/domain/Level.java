package tobi.ch5.domain;

public enum Level {
	GOLD(3, null),
	SILVER(2, GOLD),
	BASIC(1, SILVER);

	private int value;
	private Level nextLevel;

	Level(int value, Level nextLevel) {
		this.value = value;
		this.nextLevel = nextLevel;
	}

	public int intValue() {
		return value;
	}

	public Level getNextLevel() {
		return nextLevel;
	}

	public static Level valueOf(int value) {
		for (Level level: Level.values()) {
			if (level.value == value)
				return level;
		}
		throw new AssertionError("Unknown value: " + value);
	}
}
