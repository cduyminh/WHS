package database.employees;

import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.EmployeeDAO;
import database.DBConnection;

import domain.employees.*;

public class EmployeeDB implements EmployeeDAO {
	static Connection con = DBConnection.getConnection();

	static final String QUERY_ALL = "SELECT * FROM employees";
	static final String QUERY = "SELECT * FROM employees WHERE id = ?";
	static final String DELETE = "DELETE FROM employees WHERE id = ?";
	static final String QUERY_BY_DEPARTMENT = "SELECT * FROM employees WHERE department = ?";
	static final String UPDATE_DEPARTMENT = "UPDATE employees SET department = ? WHERE id = ?";
	static final String INSERT = "INSERT INTO employees (name, description, salary, department, assignment) VALUES (?, ?, ?, ?, ?)";
	static final String UPDATE = "UPDATE employees SET name = ?, description = ?, salary = ?, department = ?, assignment = ? WHERE id = ?";

	public int create(Employee employee) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(INSERT);

		stmt.setString(1, employee.getName());
		stmt.setString(2, employee.getDescription());
		stmt.setDouble(3, employee.getSalary());
		stmt.setInt(4, employee.getDepartment());
		stmt.setString(5, employee.getAssignment());

		int id = 0;
		try {
			id = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	public void remove(int employee_id) throws SQLException {

		PreparedStatement stmt = con.prepareStatement(DELETE);
		stmt.setInt(1, employee_id);

		stmt.executeUpdate();
	}

	public void update(Employee employee) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(UPDATE);
		stmt.setString(1, employee.getName());
		stmt.setString(2, employee.getDescription());
		stmt.setDouble(3, employee.getSalary());
		stmt.setInt(4, employee.getDepartment());
		stmt.setString(5, employee.getAssignment());
		stmt.setInt(6, employee.getID());

		stmt.executeUpdate();
	}

	public Employee get(int employee_id) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(QUERY);
		stmt.setInt(1, employee_id);

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Employee employee = new Employee(rs.getInt("id"),
					rs.getString("name"),
					rs.getString("description"),
					rs.getFloat("salary"),
					rs.getInt("department"),
					rs.getString("assignment"));

			return employee;
		}

		return null;
	}

	public ArrayList<Employee> getAll() throws SQLException {
		ArrayList<Employee> employees = new ArrayList<Employee>();

		PreparedStatement stmt = con.prepareStatement(QUERY_ALL);

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Employee employee = new Employee(rs.getInt("id"),
					rs.getString("name"),
					rs.getString("description"),
					rs.getFloat("salary"),
					rs.getInt("department"),
					rs.getString("assignment"));

			employees.add(employee);
		}

		return employees;
	}

	public ArrayList<Employee> getDeptEmployees(int deptCode) throws SQLException {
		ArrayList<Employee> employees = new ArrayList<Employee>();

		PreparedStatement stmt = con.prepareStatement(QUERY_BY_DEPARTMENT);
		stmt.setInt(1, deptCode);

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Employee employee = new Employee(rs.getInt("id"),
					rs.getString("name"),
					rs.getString("description"),
					rs.getFloat("salary"),
					rs.getInt("department"),
					rs.getString("assignment"));

			employees.add(employee);
		}

		return employees;
	}

	public void assignEmployeeDepartment(int employee_id, int department_id) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(UPDATE_DEPARTMENT);

		stmt.setInt(1, department_id);
		stmt.setInt(2, employee_id);

		stmt.executeUpdate();
	}

	public void removeEmployeeDepartment(int employee_id, int department_id) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(UPDATE_DEPARTMENT);

		stmt.setInt(1, 0);
		stmt.setInt(2, employee_id);

		stmt.executeUpdate();
	}

}