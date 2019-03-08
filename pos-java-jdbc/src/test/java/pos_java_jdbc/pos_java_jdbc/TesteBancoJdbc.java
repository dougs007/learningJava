package pos_java_jdbc.pos_java_jdbc;

import java.util.List;

import model.Userposjava;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.UserPosDAO;

public class TesteBancoJdbc {

	/**
	 * Método para teste
	 */
	@Test
	public void initBanco(){
		UserPosDAO UserPosDAO = new UserPosDAO();
		Userposjava userposjava = new Userposjava();

		userposjava.setId(6L);
		userposjava.setNome("Pipico");
		userposjava.setEmail("777@777.com");

		UserPosDAO.salvar(userposjava);
	}

	@Test
	public void initListar(){
		UserPosDAO dao = new UserPosDAO();

		try {
			List<Userposjava> list = dao.listar();
			System.out.println("\n---------------------------------------");
			for (Userposjava userposjava : list) {
				System.out.println(userposjava);
				System.out.println("---------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void initBuscar(){

		UserPosDAO dao = new UserPosDAO();

		try {
			Userposjava userposjava = dao.buscar(3L);
			System.out.println(userposjava);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void initAtualizar(){
		try {

			UserPosDAO dao = new UserPosDAO();

			Userposjava objetobanco = dao.buscar(4L);
			objetobanco.setNome("Nome mudado com método atualizar");
			
			dao.atualizar(objetobanco);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
