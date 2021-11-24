package model.dao;

import model.entities.User;

public interface UserDao {

	void create(User obj);
	User findByUser(String username);
	User findByUser(User obj);
}
