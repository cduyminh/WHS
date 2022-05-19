package database.items;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.items.*;
import database.ItemDAO;
import database.DBConnection;

public class ItemDB implements ItemDAO {
	static Connection con = DBConnection.getConnection();

	static final String INSERT = "INSERT INTO items (name, description, price, type, department, quantity, date) VALUES (?, ?, ?, ?, ?, ?, ?)";
	static final String UPDATE = "UPDATE items SET name = ?, description = ?, price = ?, department = ?, quantity = ?, date = ? WHERE id = ?";
	static final String QUERY = "SELECT id, name, description, price, type, department, quantity, date FROM items WHERE id = ?";
	static final String QUERY_ALL = "SELECT id, name, description, price, type, department, quantity, date FROM items";
	static final String DELETE = "DELETE FROM items WHERE id = ?";
	static final String QUERY_BY_DEPARTMENT = "SELECT id, name, description, price, type, department, quantity, date FROM items WHERE department = ?";
	static final String UPDATE_DEPARTMENT = "UPDATE items SET department = ? WHERE id = ?";

	@Override
	public int add(Item item) throws SQLException {

		PreparedStatement stmt = con.prepareStatement(INSERT);
		java.sql.Date sqlDate = new java.sql.Date(item.getDate().getTime());

		stmt.setString(1, item.getName());
		stmt.setString(2, item.getDescription());
		stmt.setDouble(3, item.getPrice());
		stmt.setInt(4, item.getType());
		stmt.setInt(5, item.getDepartment());
		stmt.setInt(6, item.getQuantity());
		stmt.setDate(7, sqlDate);

		int n = stmt.executeUpdate();

		return n;
	}

	@Override
	public void remove(int ID) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(DELETE);

		stmt.setInt(1, ID);

		stmt.executeUpdate();
	}

	@Override
	public Item getItem(int ID) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(QUERY);

		stmt.setInt(1, ID);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			int type = rs.getInt("type");
			if (type == 1) {
				Item item = new Refreshment(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("description"),
						rs.getDouble("price"),
						type,
						rs.getInt("department"),
						rs.getInt("quantity"),
						rs.getDate("date"));
				return item;
			} else {
				Item item = new Equipment(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("description"),
						rs.getDouble("price"),
						type,
						rs.getInt("department"),
						rs.getInt("quantity"),
						rs.getDate("date"));
				return item;
			}
		}

		return null;
	}

	@Override
	public ArrayList<Item> getAllItems() throws SQLException {
		String query = "select * from items";
		PreparedStatement stmt = con.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();

		ArrayList<Item> ls = new ArrayList();

		while (rs.next()) {
			int type = rs.getInt("type");

			if (type == 1) {
				Item item = new Refreshment(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("description"),
						rs.getDouble("price"),
						type,
						rs.getInt("department"),
						rs.getInt("quantity"),
						rs.getDate("date"));
				ls.add(item);

			} else {
				Item item = new Equipment(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("description"),
						rs.getDouble("price"),
						type,
						rs.getInt("department"),
						rs.getInt("quantity"),
						rs.getDate("date"));
				ls.add(item);

			}
		}

		return ls;
	}

	@Override
	public ArrayList<Item> getDeptItem(int deptCode) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(QUERY_BY_DEPARTMENT);
		stmt.setInt(1, deptCode);
		ResultSet rs = stmt.executeQuery();

		ArrayList<Item> ls = new ArrayList();
		while (rs.next()) {
			int type = rs.getInt("type");

			if (type == 1) {
				Item item = new Refreshment(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("description"),
						rs.getDouble("price"),
						type,
						rs.getInt("department"),
						rs.getInt("quantity"),
						rs.getDate("date"));
				ls.add(item);
			} else {
				Item item = new Equipment(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("description"),
						rs.getDouble("price"),
						type,
						rs.getInt("department"),
						rs.getInt("quantity"),
						rs.getDate("date"));
				ls.add(item);
			}
		}

		return ls;
	}

	@Override
	public void update(Item item) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(UPDATE);

		java.sql.Date sqlDate = new java.sql.Date(item.getDate().getTime());

		stmt.setString(1, item.getName());
		stmt.setString(2, item.getDescription());
		stmt.setDouble(3, item.getPrice());
		stmt.setInt(4, item.getDepartment());
		stmt.setInt(5, item.getQuantity());
		stmt.setDate(6, sqlDate);
		stmt.setInt(7, item.getID());

		stmt.executeUpdate();
	}
}