package ufu.ppi.gustavo.livraria.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ufu.ppi.gustavo.livraria.Utils;
import ufu.ppi.gustavo.livraria.DTO.Pessoa;

public class PessoaDAO {
	private static Connection jdbcConnection = Utils.getConnection();

	public static boolean insertPessoa(Pessoa pessoa) throws SQLException {
		String sql = "INSERT INTO pessoa (nome, endereco, num_cartao, cvv, validade_cartao)" + " VALUES (?,?,?,?,?)";

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, pessoa.getNome());
		statement.setString(2, pessoa.getEndereco());
		statement.setString(3, pessoa.getNum_cartao());
		statement.setString(4, pessoa.getCvv());
		statement.setInt(5, pessoa.getValidade_cartao());
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		return rowInserted;
	}

	public static List<Pessoa> listAllPessoas() throws SQLException {
		List<Pessoa> listPessoa = new ArrayList<>();

		String sql = "SELECT * FROM pessoa";

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("pessoa_id");
			String nome = resultSet.getString("nome");
			String endereco = resultSet.getString("endereco");
			String num_cartao = resultSet.getString("num_cartao");
			String cvv = resultSet.getString("cvv");
			int validade_cartao = resultSet.getInt("validade_cartao");

			Pessoa pessoa = new Pessoa(id, nome, endereco, num_cartao, cvv, validade_cartao);
			listPessoa.add(pessoa);
		}

		resultSet.close();
		statement.close();

		return listPessoa;
	}

	public static boolean deletePessoa(Pessoa pess) throws SQLException {
		String sql_compra = "DELETE FROM compra where pessoa_id = ?";

		PreparedStatement statement_compra = jdbcConnection.prepareStatement(sql_compra);
		statement_compra.setInt(1, pess.getId());

		boolean compras_deleted = statement_compra.executeUpdate() > 0;
		statement_compra.close();

		String sql = "DELETE FROM pessoa where pessoa_id = ?";

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, pess.getId());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		return rowDeleted && compras_deleted;
	}

	public static boolean updatePessoa(Pessoa pessoa) throws SQLException {
		String sql = "UPDATE pessoa SET " +
				"nome = ? " +
				", endereco = ? " +
				", num_cartao = ? " +
				", cvv = ? " +
				", validade_cartao = ? " +
					"WHERE pessoa_id = ?";

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, pessoa.getNome());
		statement.setString(2, pessoa.getEndereco());
		statement.setString(3, pessoa.getNum_cartao());
		statement.setString(4, pessoa.getCvv());
		statement.setInt(5, pessoa.getValidade_cartao());
		statement.setInt(6, pessoa.getId());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		return rowUpdated;
	}

	public static Pessoa getPessoa(int id) throws SQLException {
		Pessoa pess = null;
		String sql = "SELECT * FROM pessoa WHERE pessoa_id = ?";

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String nome = resultSet.getString("nome");
			String endereco = resultSet.getString("endereco");
			String num_cartao = resultSet.getString("num_cartao");
			String cvv = resultSet.getString("cvv");
			int validade_cartao = resultSet.getInt("validade_cartao");

			pess = new Pessoa(id, nome, endereco, num_cartao, cvv, validade_cartao);
			

		}

		resultSet.close();
		statement.close();

		return pess;
	}
}
