package domain.departments;

import domain.items.*;
import domain.employees.*;
import java.util.*;
import database.items.ItemDB;
import database.employees.EmployeeDB;

// Delivery is only created once.
public class Delivery implements Department {
	private static ArrayList<Employee> employees;
	private static ArrayList<Item> items;

	private static ArrayList<String> customers;

	// singleton instance
	private static ItemDB item_db;
	private static EmployeeDB employee_db;

	// constructor
	public Delivery() {
		item_db = new ItemDB();
		employee_db = new EmployeeDB();
		employees = new ArrayList<Employee>();
		items = new ArrayList<Item>();
		customers = new ArrayList<String>();

		customers.add("Elon Musk");
		customers.add("Bill Gates");
		customers.add("Steve Jobs");
		customers.add("Mark Zuckerberg");
		customers.add("Jeff Bezos");
		customers.add("Larry Page");
		customers.add("Sergey Brin");

		try {
			employees = employee_db.getDeptEmployees(2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			items = item_db.getDeptItem(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// issue export all the items in Delivery.items
	// Warehouse will receive the cost.
	public String issue(Integer itemID) {
		StringBuilder sb = new StringBuilder();

		for (Item item : items) {
			if (item.getID() == itemID) {
				try {
					item_db.remove(item.getID());
				} catch (Exception e) {
					e.printStackTrace();
				}

				sb.append("The item is being exported to customer: \n");
				sb.append(item.getName());

				items.remove(item);
				break;
			}
		}

		return sb.toString();

	}

	public String issue() {
		StringBuilder sb = new StringBuilder();
		sb.append("The following items are being exported to customer: \n");

		// loops through items
		// if itemID matches, return item's price
		for (Item item : items) {
			try {
				item_db.remove(item.getID());
			} catch (Exception e) {
				e.printStackTrace();
			}

			sb.append(item.getName() + ", ");

			items.remove(item);
			break;
		}

		return sb.toString();
	}

	public ArrayList<Item> getItems() {
		try {
			items = item_db.getDeptItem(2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return items;
	}

	public ArrayList<Item> getItems(Integer id, String name) {
		try {
			items = item_db.getDeptItem(2);
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
			employees = employee_db.getDeptEmployees(2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return employees;
	}

	public ArrayList<Employee> getEmployees(Integer id, String name) {
		try {
			employees = employee_db.getDeptEmployees(2);
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

	// return the department info.
	public Delivery getInfo() {
		return null;
	}
	
	public void AddItem(Item item) {
		items.add(item);
	}

	public void RemoveItem(Item item) {
		items.remove(item);
	}
	
}