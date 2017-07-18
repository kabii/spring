package tobi.ch1.dao;

import tobi.ch1.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao dao = new UserDao(new SimpleConnectionMaker());

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
