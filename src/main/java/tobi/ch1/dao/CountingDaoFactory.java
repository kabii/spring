package tobi.ch1.dao;

import org.springframework.context.annotation.Bean;

public class CountingDaoFactory {
	@Bean
	public UserDao userDao() {
		UserDao dao = new UserDao();
		dao.setConnectionMaker(connectionMaker());
		return dao;
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		return new CountingConnectionMaker(new SimpleConnectionMaker());
	}
}
