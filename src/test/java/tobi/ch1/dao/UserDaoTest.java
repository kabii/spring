package tobi.ch1.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobi.ch1.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
	public static void main(String[] args) throws SQLException {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);

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
