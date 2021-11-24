package model.dao;

import db.DB;
import model.dao.impl.EmployeeDaoJDBC;
import model.dao.impl.UserDaoJDBC;

public class DaoFactory {

	public static EmployeeDao createEmployeeDao() {
		return new EmployeeDaoJDBC(DB.getConnection());
	}
	
	public static UserDao createUserDao() {
		return new UserDaoJDBC(DB.getConnection());
	}
}
