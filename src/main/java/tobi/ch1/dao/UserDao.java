package tobi.ch1.dao;

import tobi.ch1.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	private ConnectionMaker connectionMaker;

	public UserDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();

		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) VALUES(?, ?, ?)");
		ps.setLong(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		ps.executeUpdate();

		ps.close();
		c.close();
	}

	public User get(Long id) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();

		PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
		ps.setLong(1, id);

		ResultSet rs = ps.executeQuery();
		rs.next();
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));

		rs.close();
		ps.close();
		c.close();

		return user;
	}
}
