package tobi.ch3.dao;

import lombok.Setter;
import org.springframework.dao.EmptyResultDataAccessException;
import tobi.ch3.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	@Setter
	private DataSource dataSource;

	@Setter
	private JdbcContext jdbcContext;

	public void setDataSource(DataSource dataSource) {
		jdbcContext = new JdbcContext();
		jdbcContext.setDataSource(dataSource);
		this.dataSource = dataSource;
	}

	public void add(User user) throws SQLException {
		jdbcContext.workWithStatementStrategy(c -> {
			PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) VALUES(?, ?, ?)");
			ps.setLong(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());
			return ps;
		});
	}

	public User get(Long id) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = dataSource.getConnection();

			ps = c.prepareStatement("select * from users where id = ?");
			ps.setLong(1, id);

			rs = ps.executeQuery();

			User user = null;
			if (rs.next()) {
				user = new User(rs.getLong("id"), rs.getString("name"), rs.getString("password"));
			}
			if (user == null) {
				throw new EmptyResultDataAccessException(1);
			}
			return user;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public void deleteAll() throws SQLException {
		jdbcContext.executeSql("delete from users");
	}

	public int getCount() throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = dataSource.getConnection();
			ps = c.prepareStatement("select count(*) from users");

			rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw e;
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
		}
	}
}
