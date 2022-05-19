package domain.items;

import java.util.*;

public class Equipment extends Item {
	// constructor
	public Equipment() {
	}

	public Equipment(Integer id, String name, String description, double price, Integer type, Integer department,
			Integer quantity, Date date) {
		super(id, name, description, price, type, department, quantity, date);
	}

	// checkup checks if the equipment management time is over.
	// if yes, sold the equipment for the price and added to the warehouse.
	public boolean isExpired() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +7);

		Date mantenanceDate = cal.getTime();
		if (mantenanceDate.after(this.date)) {
			return true;
		}

		return false;
	}
	
	public String getTypeName() {
		return "Equipment";
	}
	
}