package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	private static String url = "jdbc:postgresql://localhost:5432/posjava";
	private static String password = "123456";
	private static String user = "postgres";
	private static Connection connection = null;

	static {
		conectar();
	}
	/**
	 * Construtor da classe de conexão
	 * 
	 */
	public SingleConnection(){
		conectar();
	}

	/**
	 * Método para conetar com o banco de dados.
	 */
	private static void conectar(){

		try {

			if(connection == null){
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.println("Conectou com sucesso !!!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Método para pegar a conexão aberta
	 * @return connection
	 */
	public static Connection getConnection(){
		return connection;
	}
}
