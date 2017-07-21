package tobi.ch5.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import tobi.ch5.domain.Level;
import tobi.ch5.domain.User;

import javax.sql.DataSource;
import java.util.List;

public class UserDaoJdbc implements UserDao {
	private JdbcTemplate jdbcTemplate;
	private RowMapper<User> rowMapper = (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("name"), rs.getString("password"),
		Level.valueOf(rs.getInt("level")), rs.getInt("login"), rs.getInt("recommend"));

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void add(User user) {
		jdbcTemplate.update("insert into users(id, name, password, level, login, recommend) VALUES(?, ?, ?, ?, ?, ?)", user.getId(), user.getName(), user.getPassword(),
			user.getLevel().intValue(), user.getLogin(), user.getRecommend());
	}

	@Override
	public User get(Long id) {
		return jdbcTemplate.queryForObject("select * from users where id = ?", new Object[] { id }, rowMapper);
	}

	@Override
	public List<User> getAll() {
		return jdbcTemplate.query("select * from users order by id", rowMapper);
	}

	@Override
	public void deleteAll() {
		jdbcTemplate.update("delete from users");
	}

	@Override
	public int getCount() {
		return jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
	}
}
