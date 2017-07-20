package tobi.ch4.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import tobi.ch4.domain.User;

import javax.sql.DataSource;
import java.util.List;

public class UserDao {
	private JdbcTemplate jdbcTemplate;
	private RowMapper<User> rowMapper = (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("name"), rs.getString("password"));

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void add(User user) {
		jdbcTemplate.update("insert into users(id, name, password) VALUES(?, ?, ?)", user.getId(), user.getName(), user.getPassword());
	}

	public User get(Long id) {
		return jdbcTemplate.queryForObject("select * from users where id = ?", new Object[] { id }, rowMapper);
	}

	public List<User> getAll() {
		return jdbcTemplate.query("select * from users order by id", rowMapper);
	}

	public void deleteAll() {
		jdbcTemplate.update("delete from users");
	}

	public int getCount() {
		return jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
	}
}
