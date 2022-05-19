package domain.employees;

import java.util.ArrayList;

public class Employee {
	protected Integer id;
	protected String name;
	protected String description;
	protected Float salary;
	protected Integer department;
	protected String assignment;

	public Employee() {
	}

	public Employee(int id, String name, String description, Float salary, int department, String assignment) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.salary = salary;
		this.department = department;
		this.assignment = assignment;
	}

	public Employee getInfo() {
		return this;
	}

	public Integer getID() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public int getDepartment() {
		return this.department;
	}

	public Float getSalary() {
		return this.salary;
	}

	public String getAssignment() {
		return this.assignment;
	}

	public void setDepartment(int code) {
		this.department = code;
	}

	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean validate() {
		if (this.name == null || this.name.isEmpty()) {
			return false;
		}

		if (this.description == null || this.description.isEmpty()) {
			return false;
		}

		if (this.salary == null) {
			return false;
		}

		if (this.assignment == null || this.assignment.isEmpty()) {
			return false;
		}

		return true;
	}
	
	public String getDepartmentName() {
		if (this.getDepartment() == 1)
			return "Storage";
		else if (this.getDepartment() == 2)
			return "Delivery";
		else
			return "Null";
	}
	
	
	// returns the shelfs in supervise, also the dept of the employee.
	public ArrayList<Employee> viewAssignment() {
		// go to employee_db and find its current department
		ArrayList<Employee> employees = new ArrayList<Employee>();
		employees.add(this);

		return employees;
	}
}