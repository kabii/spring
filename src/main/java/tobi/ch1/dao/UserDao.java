package tobi.ch1.dao;

import tobi.ch1.domain.User;

import java.sql.*;

public class UserDao {
	public void add(User user) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/users", "root", "root");

		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) VALUES(?, ?, ?)");
		ps.setLong(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		ps.executeUpdate();

		ps.close();
		c.close();
	}

	public User get(Long id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/users", "root", "root");

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

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao dao = new UserDao();

		User user = new User();
		user.setId(1L);
		user.setName("Nani");
		user.setPassword("nani");

		dao.add(user);

		System.out.println(user.getId() + " registration success");

		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());

		System.out.println(user2.getId() + " view success");
	}
}
