package tobi.ch5.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import tobi.ch5.service.BasicUserLevelUpgradePolicy;
import tobi.ch5.service.UserLevelUpgradePolicy;
import tobi.ch5.service.UserService;

import javax.sql.DataSource;

@Configuration
public class DaoFactory {
	@Bean
	public UserDao userDao() {
		UserDaoJdbc dao = new UserDaoJdbc();
		dao.setDataSource(dataSource());
		return dao;
	}

	@Bean
	public UserLevelUpgradePolicy userLevelUpgradePolicy() {
		return new BasicUserLevelUpgradePolicy();
	}

	@Bean
	public DataSource dataSource() {
		return new SingleConnectionDataSource("jdbc:mysql://localhost/users", "root", "root", true);
	}

	@Bean
	public UserService userService() {
		return new UserService();
	}
}
