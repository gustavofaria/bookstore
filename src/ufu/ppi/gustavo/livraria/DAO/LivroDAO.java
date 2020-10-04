package ufu.ppi.gustavo.livraria.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ufu.ppi.gustavo.livraria.Utils;
import ufu.ppi.gustavo.livraria.DTO.Livro;

public class LivroDAO {
	private static Connection jdbcConnection = Utils.getConnection();

	public static boolean insertBook(Livro livro) throws SQLException {
		String sql = "INSERT INTO livro (titulo, autor, preco) VALUES (?, ?, ?)";
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, livro.getTitulo());
		statement.setString(2, livro.getAutor());
		statement.setFloat(3, livro.getPreco());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		return rowInserted;
	}

	public static List<Livro> listAllBooks() throws SQLException {
		List<Livro> listBook = new ArrayList<>();

		String sql = "SELECT * FROM livro";

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("livro_id");
			String title = resultSet.getString("titulo");
			String author = resultSet.getString("autor");
			float price = resultSet.getFloat("preco");

			Livro book = new Livro(id, title, author, price);
			listBook.add(book);
		}

		resultSet.close();
		statement.close();

		return listBook;
	}

	public static boolean deleteBook(Livro book) throws SQLException {
		String sql = "DELETE FROM livro where livro_id = ?";

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, book.getId());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		return rowDeleted;
	}

	public static boolean updateLivro(Livro book) throws SQLException {
		String sql = "UPDATE livro SET titulo = ?, autor = ?, preco = ?";
		sql += " WHERE livro_id = ?";

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, book.getTitulo());
		statement.setString(2, book.getAutor());
		statement.setFloat(3, book.getPreco());
		statement.setInt(4, book.getId());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		return rowUpdated;
	}

	public static Livro getBook(int id) throws SQLException {
		Livro book = null;
		String sql = "SELECT * FROM livro WHERE livro_id = ?";

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String title = resultSet.getString("titulo");
			String author = resultSet.getString("autor");
			float price = resultSet.getFloat("preco");

			book = new Livro(id, title, author, price);
		}

		resultSet.close();
		statement.close();

		return book;
	}
}
