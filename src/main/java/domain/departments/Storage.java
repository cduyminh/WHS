package domain.departments;

import java.util.*;

import domain.items.*;
import domain.employees.*;

import database.items.ItemDB;
import database.employees.EmployeeDB;

public class Storage implements Department {
	private static ArrayList<Employee> employees;
	private ArrayList<Item> items;

	// singleton instance
	private static ItemDB item_db;
	private static EmployeeDB employee_db;

	// constructor
	public Storage() {
		item_db = new ItemDB();
		employee_db = new EmployeeDB();
		items = new ArrayList<Item>();
		employees = new ArrayList<Employee>();

		try {
			employees = employee_db.getDeptEmployees(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			items = item_db.getDeptItem(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Item> getItems() {
		try {
			items = item_db.getDeptItem(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return items;
	}

	public ArrayList<Item> getItems(Integer id, String name) {
		try {
			items = item_db.getDeptItem(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<Item> newItems = new ArrayList<Item>();
		for (Item item : items) {
			if (item.getID() == id || item.getName().equals(name)) {
				newItems.add(item);
			}
		}
		return newItems;
	}

	public ArrayList<Employee> getEmployees() {
		try {
			employees = employee_db.getDeptEmployees(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return employees;
	}

	public ArrayList<Employee> getEmployees(Integer id, String name) {
		try {
			employees = employee_db.getDeptEmployees(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<Employee> newEmployees = new ArrayList<Employee>();
		for (Employee employee : employees) {
			if (employee.getID() == id || employee.getName().equals(name)) {
				newEmployees.add(employee);
			}
		}
		return newEmployees;
	}

	// issue export the given item from database.
	// this does not remove the item's instance, but rather
	// notice the employee in this department the moving.
	public String issue(Integer itemID) {
		StringBuilder sb = new StringBuilder();
		sb.append("The following item is being transferred to Delivery Department: \n");

		for (Item item : items) {
			if (item.getID() == itemID) {
				item.setDepartment(2);

				try {
					item_db.update(item);
				} catch (Exception e) {
					e.printStackTrace();
				}

				sb.append(item.getName());

				items.remove(item);
				break;
			}
		}

		return sb.toString();
	}

	// issue all the items inside dept
	public String issue() {
		StringBuilder sb = new StringBuilder();
		sb.append("The following items are being transferred to Delivery Department: \n");

		for (Item item : items) {
			item.setDepartment(2);

			try {
				item_db.update(item);
			} catch (Exception e) {
				e.printStackTrace();
			}

			sb.append(item.getName() + ", ");

			items.remove(item);
			break;
		}

		return sb.toString();
	}

	public void AddEmployees(Employee[] new_employees) {
		for (Employee emp : new_employees) {
			employees.add(emp);
		}
	}

	public void RemoveEmployee(Employee[] new_employees) {
		for (Employee emp : new_employees) {
			employees.remove(emp);
		}
	}

	public Department getInfo() {
		return null;
	}

//	public static void main(String[] args) {
//		Storage storage = new Storage();
//		System.out.println(storage.getItems());
//		System.out.println(storage.getEmployees());
//		System.out.println(storage.getItems(1, ""));
//
//		System.out.println(storage.issue(1));
//	}
	
	public void AddItem(Item item) {
		items.add(item);
	}

	public void RemoveItem(Item item) {
		items.remove(item);
	}
	
	
}