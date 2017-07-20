package tobi.ch3.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import tobi.ch3.domain.User;

import javax.sql.DataSource;

public class UserDao {
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		this.dataSource = dataSource;
	}

	public void add(User user) {
		jdbcTemplate.update("insert into users(id, name, password) VALUES(?, ?, ?)", user.getId(), user.getName(), user.getPassword());
	}

	public User get(Long id) {
		RowMapper<User> rowMapper = (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("name"), rs.getString("password"));
		return jdbcTemplate.queryForObject("select * from users where id = ?", new Object[] { id }, rowMapper);
	}

	public void deleteAll() {
		jdbcTemplate.update("delete from users");
	}

	public int getCount() {
		return jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
	}
}
