package domain.departments;

import domain.items.*;
import domain.employees.*;
import java.util.*;

public interface Department {
	public String issue(Integer itemID);

	public String issue();

	public ArrayList<Item> getItems();

	public ArrayList<Item> getItems(Integer id, String name);

	public ArrayList<Employee> getEmployees();

	public ArrayList<Employee> getEmployees(Integer id, String name);

	public void AddEmployees(Employee[] employees);

	public void RemoveEmployee(Employee[] employee);

	public Department getInfo();
	
	public void AddItem(Item item);

	public void RemoveItem(Item item);
	
}