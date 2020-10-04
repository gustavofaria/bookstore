package ufu.ppi.gustavo.livraria.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ufu.ppi.gustavo.livraria.Utils;
import ufu.ppi.gustavo.livraria.DTO.Compra;

public class CompraDAO {
	private static Connection jdbcConnection = Utils.getConnection();

	public static boolean insertCompra(Compra compra) throws SQLException {
		String sql = "INSERT INTO compra (pessoa_id, livro_id, quantidade)" + " VALUES (?,?,?)";

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, compra.getPessoa_id());
		statement.setInt(2, compra.getLivro_id());
		statement.setInt(3, compra.getQuantidade());
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		return rowInserted;
	}

	public static List<Compra> listAllCompras() throws SQLException {
		List<Compra> listCompra = new ArrayList<>();

		String sql = "SELECT * FROM compra";

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("compra_id");
			int pessoa_id = resultSet.getInt("pessoa_id");
			int livro_id = resultSet.getInt("livro_id");
			int quantidade = resultSet.getInt("quantidade");

			Compra compra = new Compra(id, pessoa_id, livro_id, quantidade);
			listCompra.add(compra);
		}

		resultSet.close();
		statement.close();

		return listCompra;
	}

	public static boolean deleteCompra(Compra compra) throws SQLException {
		String sql_compra = "DELETE FROM compra where compra_id = ?";

		PreparedStatement statement_compra = jdbcConnection.prepareStatement(sql_compra);
		statement_compra.setInt(1, compra.getId());

		boolean compras_deleted = statement_compra.executeUpdate() > 0;
		statement_compra.close();
		return compras_deleted;
	}

	public static boolean updatePessoa(Compra compra) throws SQLException {
		String sql = "UPDATE compra SET " +
				"pessoa_id = ? " +
				", livro_id = ? " +
				", quantidade = ? " +
					"WHERE compra_id = ?";

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, compra.getPessoa_id());
		statement.setInt(2, compra.getLivro_id());
		statement.setInt(3, compra.getQuantidade());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		return rowUpdated;
	}


	
	public static Compra getCompra(int id) throws SQLException {
		Compra compra = null;
		String sql = "SELECT * FROM compra WHERE compra_id = ?";

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {	
		int pessoa_id = resultSet.getInt("pessoa_id");
		int livro_id = resultSet.getInt("livro_id");
		int quantidade = resultSet.getInt("quantidade");

			compra = new Compra(id, pessoa_id, livro_id, quantidade);
			

		}

		resultSet.close();
		statement.close();

		return compra;
	}
}
