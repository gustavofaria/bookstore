package ufu.ppi.gustavo.livraria.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ufu.ppi.gustavo.livraria.Utils;
import ufu.ppi.gustavo.livraria.DTO.Genero;
import ufu.ppi.gustavo.livraria.DTO.Livro;
import ufu.ppi.gustavo.livraria.DTO.Livro_edit;

public class Livro_GeneroDAO {
	private static Connection jdbcConnection = Utils.getConnection();

	public static boolean insertBook(Livro_edit livro_edit) throws SQLException {
		Livro livro = new Livro(livro_edit.getId(), livro_edit.getTitulo(), livro_edit.getAutor(),
				livro_edit.getPreco());
		boolean book_inserted = LivroDAO.insertBook(livro);
		boolean rowInserted = true;
		for (Genero gen : livro_edit.getGenero()) {
			String sql = "INSERT INTO livro_has_genero (livro_id, genero_id) VALUES (?, ?)";

			PreparedStatement statement = jdbcConnection.prepareStatement(sql);
			statement.setInt(1, livro.getId());
			statement.setInt(2, gen.getId());

			rowInserted = rowInserted && statement.executeUpdate() > 0;
			statement.close();
		}
		return book_inserted && rowInserted;
	}

	public static boolean deleteBook(Livro_edit livro_edit) throws SQLException {

		String sql = "DELETE FROM livro_has_genero where livro_id = ?";

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, livro_edit.getId());

		boolean generes_deleted = statement.executeUpdate() > 0;
		statement.close();

		Livro livro = new Livro(livro_edit.getId(), livro_edit.getTitulo(), livro_edit.getAutor(),
				livro_edit.getPreco());
		boolean book_deleted = LivroDAO.deleteBook(livro);
		return book_deleted && generes_deleted;
	}

	public static boolean updateLivro(Livro_edit livro_edit) throws SQLException {
		Livro livro = new Livro(livro_edit.getId(), livro_edit.getTitulo(), livro_edit.getAutor(), livro_edit.getPreco());
		boolean livro_updated = LivroDAO.updateLivro(livro);
//		deletar todos os gêneros associados ao livro, depois recolocá-los
		String sql = "DELETE FROM livro_has_genero where livro_id = ?";

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, livro_edit.getId());
//		boolean deleted = statement.executeUpdate() > 0;
		statement.executeUpdate();
		statement.close();
		
		boolean rowInserted = true;
		for (Genero gen : livro_edit.getGenero()) {
			String sql_insert = "INSERT INTO livro_has_genero (livro_id, genero_id) VALUES (?, ?)";

			PreparedStatement ins_statement = jdbcConnection.prepareStatement(sql_insert);
			ins_statement.setInt(1, livro.getId());
			ins_statement.setInt(2, gen.getId());

			rowInserted = rowInserted && ins_statement.executeUpdate() > 0;
			ins_statement.close();
		}
		
		
		return rowInserted  && livro_updated;
	}

	public static Livro_edit getBook(int id) throws SQLException {
		Livro book = LivroDAO.getBook(id);
		Livro_edit livro_edit = new Livro_edit(book.getId(),book.getTitulo(),book.getAutor(),book.getPreco());
		
		String sql = "SELECT g.*  FROM livro_has_genero as l_h_g"
				+ " inner join genero as g on g.genero_id = l_h_g.genero_id"
				+ " WHERE l_h_g.livro_id = ?";

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			String descricao = resultSet.getString("descricao");
			int gen_id = resultSet.getInt("genero_id");
			Genero gen = new Genero(gen_id, descricao);
			livro_edit.appendGenero(gen);
		}

		resultSet.close();
		statement.close();
		return livro_edit;
	}
}
