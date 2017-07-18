package tobi.ch2.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
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
