package tobi.ch1.domain;

import lombok.Getter;
import lombok.Setter;

public class User {
	@Getter
	@Setter
	private Long id;
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private String password;
}
