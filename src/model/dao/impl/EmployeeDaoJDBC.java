package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.EmployeeDao;
import model.entities.Employee;

public class EmployeeDaoJDBC implements EmployeeDao {

	private Connection conn;
	
	public EmployeeDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Employee obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"INSERT INTO employee"
					+ "(name, cpf, job, salary, phone, created_at)"
					+ "VALUE"
					+ "(?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			st.setString(2, obj.getCpf());
			st.setString(3, obj.getJob());
			st.setInt(4, obj.getSalary());
			st.setString(5, obj.getPhone());
			st.setDate(6, new java.sql.Date(obj.getCreated_at().getTime()));
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
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

	@Override
	public void update(Employee obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"UPDATE employee "
					+ "SET name = ?, cpf = ?, job = ?, salary = ?, phone = ? "
					+ "WHERE id = ?");
			st.setString(1, obj.getName());
			st.setString(2, obj.getCpf());
			st.setString(3, obj.getJob());
			st.setInt(4, obj.getSalary());
			st.setString(5, obj.getPhone());
			st.setInt(6, obj.getId());
			
			int rowsAffected = st.executeUpdate();
			if(rowsAffected < 1) {
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

	@Override
	public void deleteById(Integer[] list) {
		PreparedStatement st = null;
		
		try {
			for(Integer id: list) {
				st = conn.prepareStatement(
						"DELETE FROM employee "
						+"WHERE id = ?");
				st.setInt(1, id);
				
				int rowsAffected = st.executeUpdate();
				if(rowsAffected < 1) {
					throw new DbException("Erro, nenhuma linha foi afetada.");
				}
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Employee findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM employee WHERE id = ?");
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				return new Employee(rs.getInt("id"),rs.getString("name"),rs.getString("cpf"),rs.getString("job"),rs.getInt("salary"),rs.getString("phone"),rs.getDate("created_at"));
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
	
	@Override
	public List<Employee> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM employee ORDER BY id");
			rs = st.executeQuery();
			
			List<Employee> list = new ArrayList<>();
			
			while(rs.next()) {
				Employee func = new Employee(rs.getInt("id"),rs.getString("name"),rs.getString("cpf"),rs.getString("job"),rs.getInt("salary"),rs.getString("phone"),rs.getDate("created_at"));
				
				list.add(func);
			}
			
			return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

}
