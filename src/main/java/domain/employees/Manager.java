package domain.employees;

import java.util.*;
import domain.warehouse.*;
import database.employees.EmployeeDB;

public class Manager extends Employee {
	private WareHouse warehouse;
	private EmployeeDB employee_DB;

	public Manager(WareHouse warehouse) {
		this.warehouse = warehouse;
		this.employee_DB = new EmployeeDB();
	}

	public Manager(int id, String name, String description, Float salary, int department, String assignment) {
		super(id, name, description, salary, department, assignment);
		this.warehouse = new WareHouse();
	}
	
	public Manager() {
		this.employee_DB = new EmployeeDB();
	}

	@Override
	public ArrayList<Employee> viewAssignment() {
		ArrayList<Employee> employees = null;

		try {
			employees = employee_DB.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return employees;
	}

	/**
	 * @param: employee_id: the id of the employee.
	 */
	public ArrayList<Employee> viewAssignment(Integer employee_id) {
		Employee employee = null;

		try {
			employee = employee_DB.get(employee_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<Employee> result = new ArrayList<Employee>();
		result.add(employee);

		return result;
	}

	/**
	 * @param: name:        the name of the employee.
	 * @param: description: the description of the employee.
	 * @param: salary:      the salary of the employee.
	 * @param: department:  the department of the employee.
	 * @param: assignment:  the assignment of the employee.
	 */
	public String addEmployee(String name, String description, Float salary, int department,
			String assignment) {
		Employee employee = new Employee(0, name, description, salary, department, assignment);

		if (!employee.validate()) {
			return "Invalid employee fields - please recheck";
		}

		int id = 0;
		try {
			id = employee_DB.create(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Integer.toString(id);
	}

	public String updateEmployee(Employee employee) {
		if (employee.getID() == null) {
			return "Employee ID is null - please fill in ID";
		}

		ArrayList<Employee> employeeInternal = new ArrayList<Employee>();
		employeeInternal = this.viewAssignment(employee.getID());

		if (employeeInternal.size() == 0) {
			return "Employee does not exist";
		}

		try {
			employee_DB.update(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Employee updated successfully";
	}

	public String removeEmployee(Employee employee) {
		if (employee.getID() == null) {
			return "Employee ID is null - please fill in ID";
		}

		ArrayList<Employee> employeeInternal = new ArrayList<Employee>();
		employeeInternal = this.viewAssignment(employee.getID());

		if (employeeInternal.size() == 0) {
			return "Employee does not exist";
		}

		try {
			employee_DB.remove(employee.getID());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Employee removed successfully";
	}

	// Function with simple error handling
	public String assignEmployeeToDepartment(Employee employee, Integer deptCode) {
		StringBuilder result = new StringBuilder();

		if (employee.getDepartment() == deptCode) {
			return "Employee is already assigned to this department";
		}

		if (deptCode == 0) {
			return "Department does not exist";
		}

		try {
			employee_DB.assignEmployeeDepartment(employee.getID(), deptCode);
			employee.setDepartment(deptCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.append("Employee is assigned to department: ");
		if (deptCode == 1) {
			result.append("Storage");
		} else {
			result.append("Delivery");
		}

		result.append(employee.getID());

		this.warehouse.assignEmployeeToDepartment(employee, deptCode);
		return result.toString();
	}

	// Function with simple error handling
	public String removeEmployeeFromDepartment(Employee employee, Integer deptCode) {
		StringBuilder result = new StringBuilder();

		if (employee.getDepartment() != deptCode) {
			System.out.println(employee.getDepartment());
			return "Employee is not assigned to this department";
		}

		if (deptCode == 0) {
			return "Department does not exist";
		}

		try {
			employee_DB.removeEmployeeDepartment(employee.getID(), deptCode);
			employee.setDepartment(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.append("Employee is removed from department: ");
		if (deptCode == 1) {
			result.append("Storage");
		} else {
			result.append("Delivery");
		}

		result.append(employee.getID());

		this.warehouse.removeEmployeeFromDepartment(employee, deptCode);
		return result.toString();
	}

	public static void main(String[] args) {
		WareHouse w = new WareHouse();
		Manager manager = new Manager(w);
		manager.removeEmployee(manager.viewAssignment().get(0));
	}

}