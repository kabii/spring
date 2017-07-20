package tobi.ch3.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;

@Configuration
public class DaoFactory {
	@Bean
	public UserDao userDao() {
		UserDao dao = new UserDao();
		dao.setDataSource(dataSource());
		return dao;
	}

	@Bean
	public DataSource dataSource() {
		return new SingleConnectionDataSource("jdbc:mysql://localhost/users", "root", "root", true);
	}
}
