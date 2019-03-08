package dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Userposjava;
import conexaojdbc.SingleConnection;

public class UserPosDAO {

	private Connection connection;

	/**
	 * Construtor para buscar a conexão.
	 */
	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Userposjava userposjava) {

		try {
			String sql = "insert into userposjava (id, nome, email) values (?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setLong(1, userposjava.getId());
			insert.setString(2, userposjava.getNome());
			insert.setString(3, userposjava.getEmail());
			insert.execute();
			connection.commit(); // Comita a transação

		} catch (Exception e) {

			try {
				connection.rollback(); // Reverte a transação

			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Método para listar os registros da tabela de userposjava.
	 * 
	 * @return
	 */
	public List<Userposjava> listar() throws Exception {
		List<Userposjava> list = new ArrayList<Userposjava>();

		String sql = "select * from userposjava";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			Userposjava userposjava = new Userposjava();

			// Seta os atributos
			userposjava.setId(resultado.getLong("id"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setEmail(resultado.getString("email"));

			// Adiciona na lista
			list.add(userposjava);
		}

		return list;
	}

	/**
	 * Método para buscar um registro da tabela de userposjava.
	 * 
	 * @return
	 */
	public Userposjava buscar(Long id) throws Exception {
		Userposjava retorno = new Userposjava();

		String sql = "select * from userposjava where id = '" + id + "'"; System.out.println("Sintaxe do SQL utilizado ->\n " + sql );

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) { // Retorna um ou nada.

			Userposjava userposjava = new Userposjava();
			// Seta os atributos
			userposjava.setId(resultado.getLong("id"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setEmail(resultado.getString("email"));

		}

		return retorno;
	}

	/**
	 * 
	 * @param userposjava
	 */
	public void atualizar (Userposjava userposjava){
		try {

			String sql = "UPDATE userposjava SET nome = ? WHERE id = " + userposjava.getId();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userposjava.getNome());

			statement.execute();
			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
