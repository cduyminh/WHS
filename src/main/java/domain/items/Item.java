package domain.items;

import java.util.*;

public abstract class Item {
	protected Integer id;
	protected String name;
	protected String description;
	protected double price;
	protected Integer type;
	protected Integer department;
	protected Integer quantity;
	protected Date date;

	public Item() {
	}

	public Item(Integer id, String name, String description, double price, Integer type, Integer department,
			Integer quantity, Date date) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.type = type;
		this.department = department;
		this.quantity = quantity;
		if (date == null) {
			this.date = new Date();
		}
		else
			this.date = date;
	}

	public String getName() {
		return this.name;
	}

	public Integer getID() {
		return this.id;
	}

	public String getDescription() {
		return this.description;
	}

	public double getPrice() {
		return this.price;
	}

	public Integer getType() {
		return this.type;
	}

	public Integer getDepartment() {
		return this.department;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public Date getDate() {
		return this.date;
	}

	public void setID(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean validate() {
		if (this.name == null || this.name.isEmpty()) {
			return false;
		}

		if (this.description == null || this.description.isEmpty()) {
			return false;
		}

		if (this.price <= 0) {
			return false;
		}

		if (this.type <= 0) {
			return false;
		}

		if (this.department <= 0) {
			return false;
		}

		if (this.quantity <= 0) {
			return false;
		}

		if (this.date == null) {
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

	public abstract boolean isExpired();
	public abstract String getTypeName();
}