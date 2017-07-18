package tobi.ch1.dao;

import org.springframework.context.annotation.Bean;

public class CountingDaoFactory {
	@Bean
	public UserDao userDao() {
		return new UserDao(connectionMaker());
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		return new CountingConnectionMaker(new SimpleConnectionMaker());
	}
}
