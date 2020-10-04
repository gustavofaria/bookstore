package ufu.ppi.gustavo.livraria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils {
	private static String jdbcURL = "jdbc:mysql://localhost:3306/Livraria";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "123456";
	private static Connection jdbcConnection = connection();

	private static Connection connection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			System.err.println(e.getCause()); 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return connection;
	}

	public static Connection getConnection() {
		return jdbcConnection;
	}
}
