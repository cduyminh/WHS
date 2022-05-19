package domain.warehouse;

import java.util.*;
import domain.items.*;
import domain.employees.*;
import database.items.ItemDB;

import domain.departments.*;

// Manage the employees;
// Thus, the CRUD of employees & items is also implemented here.
public class WareHouse {
	private static String name;
	private static Integer numberOfItems;

	// singleton instance
	private static ItemDB item_db;

	private static final Integer storageDeptCode = 1;
	private static final Integer deliveryDeptCode = 2;

	private Delivery deliveryDept;
	private Storage storageDept;

	// Constructor
	// Initialize current Item in database if existed.
	public WareHouse() {
		name = "Warehouse System";
		item_db = new ItemDB();
		deliveryDept = new Delivery();
		storageDept = new Storage();

		ArrayList<Item> items = null;
		try {
			items = item_db.getAllItems();
		} catch (Exception e) {
			e.printStackTrace();
		}

		numberOfItems = items.size();
	}

	public WareHouse getInfo() {
		return this;
	}

	public static String getName() {
		return name;
	}

	public static Integer getNumberOfItems() {
		return numberOfItems;
	}

	public Delivery getDeliveryDept() {
		return this.deliveryDept;
	}

	public Storage getStorageDept() {
		return this.storageDept;
	}

	// receipt inputs multiple items to warehouse.
	// this function will be simulated.
	public void receipt(List<Item> items) {
		Date date = new Date();
		for (Iterator<Item> i = items.iterator(); i.hasNext();) {
			Item item = i.next();
			try {
				item.setDate(date);
				item_db.add(item);

			} catch (Exception e) {
				e.printStackTrace();
			}

			numberOfItems++;

			if (item.getDepartment() == 1) {
				storageDept.AddItem(item);
			} else if (item.getDepartment() == 2) {
				deliveryDept.AddItem(item);
			}
		}
	}
	
	public void receipt(Item item) {
		Date date = new Date();
			try {
				item.setDate(date);
				item_db.add(item);

			} catch (Exception e) {
				e.printStackTrace();
			}
			numberOfItems++;
			if (item.getDepartment() == 1) {
				storageDept.AddItem(item);
			} else if (item.getDepartment() == 2) {
				deliveryDept.AddItem(item);
			}
	}

	public ArrayList<Item> searchItems(Integer id, String name) {
		ArrayList<Item> items = new ArrayList<Item>();

		try {
			items = item_db.getAllItems();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<Item> result = new ArrayList<Item>();
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			if (item.getName().equals(name)) {
				result.add(item);
			} else {
				if (item.getID() == id) {
					result.add(item);
				}
			}
		}

		return items;
	}

	public String issue(Item item) {
		if (item.getDepartment() == storageDeptCode) {
			return this.storageDept.issue(item.getID());
		}

		if (item.getDepartment() == deliveryDeptCode) {
			return this.deliveryDept.issue(item.getID());
		}

		return "Cannot seems to find the current department of item";
	}

	public String assignEmployeeToDepartment(Employee employee, Integer deptCode) {
		if (deptCode == storageDeptCode) {
			this.storageDept.AddEmployees(new Employee[] { employee });
			return "Assign employee successfully";
		}

		this.deliveryDept.AddEmployees(new Employee[] { employee });
		return "Assign employee successfully";
	}

	public String removeEmployeeFromDepartment(Employee employee, Integer deptCode) {
		if (deptCode == storageDeptCode) {
			this.storageDept.RemoveEmployee(new Employee[] { employee });
			return "Remove employee successfully";
		}

		this.deliveryDept.RemoveEmployee(new Employee[] { employee });
		return "Remove employee successfully";
	}

	public String updateItem(Item item) {
		ArrayList<Item> itemInternal = new ArrayList<Item>();
		itemInternal = this.searchItems(item.getID(), "");
		if (itemInternal.size() == 0) {
			return "Item does not match with any inside the warehouse";
		}

		if (!item.validate()) {
			return "invalid item, please fill all the fields in item";
		}

		try {
			item_db.update(item);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Item updated successfully";
	}

	public String removeItem(Item item) {
		ArrayList<Item> itemInternal = new ArrayList<Item>();

		itemInternal = this.searchItems(item.getID(), "");
		if (itemInternal.size() == 0) {
			return "Item does not match with any inside the warehouse";
		}

		try {
			item_db.remove(item.getID());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (item.getDepartment() == 1) {
			this.storageDept.RemoveItem(item);
		} else if (item.getDepartment() == 2) {
			this.deliveryDept.RemoveItem(item);
		}

		return "Item removed successfully";
	}

	// Get warehouse report on:
	// 1. How many employees are are in the WH? How many of them are managers?
	// 2. The number of items in WH? How many of each items?
	// 3. Number of full shelves, or empty shelves.
	public String reportWareHouseActivity() {
		return "";
	}


}