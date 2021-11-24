package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import db.DB;
import db.DbException;
import model.dao.UserDao;
import model.entities.User;

public class UserDaoJDBC implements UserDao {

	private Connection conn;
	
	public UserDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void create(User obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"INSERT INTO user"
					+ "(username, password, isAdmin)"
					+ "VALUE"
					+ "(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getUsername());
			st.setString(2, obj.getPassword());
			st.setBoolean(3, obj.getIsAdmin());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro, nenhuma linha foi afetada.");
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
	
	public User findByUser(String username) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT username FROM user WHERE username = ?");
			st.setString(1, username);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				return new User(null, rs.getString("username"),"",false);
			} else {
				return null;
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
	
	public User findByUser(User obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
			st.setString(1, obj.getUsername());
			st.setString(2, obj.getPassword());
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				return new User(rs.getInt("id"), rs.getString("username"),"",rs.getBoolean("isAdmin"));
			} else {
				return null;
			}
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
}
