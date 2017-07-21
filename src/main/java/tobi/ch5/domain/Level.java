package tobi.ch5.domain;

public enum Level {
	BASIC(1),
	SILVER(2),
	GOLD(3);

	private int value;

	Level(int value) {
		this.value = value;
	}

	public int intValue() {
		return value;
	}

	public static Level valueOf(int value) {
		for (Level level: Level.values()) {
			if (level.value == value)
				return level;
		}
		throw new AssertionError("Unknown value: " + value);
	}
}
