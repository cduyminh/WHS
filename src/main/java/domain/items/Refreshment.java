package domain.items;

import java.util.*;

// Refreshment: 1
// Equipment: 2
public class Refreshment extends Item {
	// constructor
	public Refreshment() {
		super();
	}

	public Refreshment(Integer id, String name, String description, double price, Integer type, Integer department,
			Integer quantity, Date date) {
		super(id, name, description, price, type, department, quantity, date);
	}

	public Refreshment(Refreshment food) {
	}

	// checkup checks if the refreshment session is over.
	// if yes, remove the refreshment.
	public boolean isExpired() {
		Date date = new Date();
		if (this.date.after(date)) {
			return true;
		}

		return false;
	}
	
	public String getTypeName() {
		return "Refreshment";
	}
}