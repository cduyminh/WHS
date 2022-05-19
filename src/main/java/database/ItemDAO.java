package database;

import java.util.ArrayList;
import java.sql.SQLException;
import domain.items.*;

public interface ItemDAO {
	public int add(Item item)
			throws SQLException;

	public void remove(int id)
			throws SQLException;

	public Item getItem(int id)
			throws SQLException;

	public ArrayList<Item> getAllItems()
			throws SQLException;

	public void update(Item item)
			throws SQLException;

	public ArrayList<Item> getDeptItem(int deptCode) throws SQLException;

}