package database;

import java.sql.SQLException;
import domain.employees.*;

import java.util.ArrayList;

// Interface holds the requirement functions controllers.
// Models implements model.
public interface EmployeeDAO {
	// Requirement/Behavior: create 1 new employee and return ID.
	public int create(Employee employee)
			throws SQLException;

	public void remove(int employee_id)
			throws SQLException;

	public void update(Employee employee)
			throws SQLException;

	public Employee get(int employee_id)
			throws SQLException;

	public ArrayList<Employee> getAll() throws SQLException;

	public ArrayList<Employee> getDeptEmployees(int deptCode) throws SQLException;

	public void assignEmployeeDepartment(int employee_id, int department_id) throws SQLException;

	public void removeEmployeeDepartment(int employee_id, int department_id) throws SQLException;
}