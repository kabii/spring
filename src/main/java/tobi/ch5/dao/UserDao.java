package tobi.ch5.dao;

import tobi.ch5.domain.User;

import java.util.List;

public interface UserDao {
	void add(User user);
	User get(Long id);
	List<User> getAll();
	void deleteAll();
	int getCount();
}
