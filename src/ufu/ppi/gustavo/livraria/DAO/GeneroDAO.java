package ufu.ppi.gustavo.livraria.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ufu.ppi.gustavo.livraria.Utils;
import ufu.ppi.gustavo.livraria.DTO.Genero;

public class GeneroDAO {
	private static Connection jdbcConnection = Utils.getConnection();

	public static boolean insertGenero(Genero gen) throws SQLException {
		String sql = "INSERT INTO genero (descricao) VALUES (?)";

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, gen.getDescricao());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		return rowInserted;
	}

	public static List<Genero> listAllGeneros() throws SQLException {
		List<Genero> listGenero = new ArrayList<>();

		String sql = "SELECT * FROM genero";

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("genero_id");
			String descricao = resultSet.getString("descricao");

			Genero gen = new Genero(id, descricao);
			listGenero.add(gen);
		}

		resultSet.close();
		statement.close();

		return listGenero;
	}

	public static boolean deleteGenero(Genero gen) throws SQLException {
		String sql_busca = "SELECT * FROM livro_has_genero WHERE genero_id = ? ";

		PreparedStatement statement_busca = jdbcConnection.prepareStatement(sql_busca);
		statement_busca.setInt(1, gen.getId());
		ResultSet resultSet = statement_busca.executeQuery();

		if (resultSet.next()) {
			return false;
		}

		resultSet.close();
		statement_busca.close();

		String sql = "DELETE FROM genero where genero_id = ?";

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, gen.getId());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		return rowDeleted;
	}

	public static boolean updateGenero(Genero gen) throws SQLException {
		String sql = "UPDATE genero SET decricao = ?";
		sql += " WHERE genero_id = ?";

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, gen.getDescricao());
		statement.setInt(4, gen.getId());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		return rowUpdated;
	}

	public static Genero getGenero(int id) throws SQLException {
		Genero gen = null;
		String sql = "SELECT * FROM genero WHERE genero_id = ?";

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			int id_genero = resultSet.getInt("genero_id");
			String descricao = resultSet.getString("descricao");

			gen = new Genero(id_genero, descricao);
		}

		resultSet.close();
		statement.close();

		return gen;
	}
}
