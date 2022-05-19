package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Singleton class
public class DBConnection {
	private static Connection con = null;

	static {
		String url = "jdbc:mysql://root:Chinhcd61@127.0.0.1/warehouse";
		String password = "Chinhcd61";
		String username = "root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		if (con == null) {
			System.out.println("Connection failed");
		} else {
			System.out.println("Connection successful");
		}
	}

	public static Connection getConnection() {
		return con;
	}
}