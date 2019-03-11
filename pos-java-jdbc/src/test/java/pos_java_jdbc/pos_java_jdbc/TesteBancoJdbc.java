package pos_java_jdbc.pos_java_jdbc;

import java.util.List;

import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.UserPosDAO;

public class TesteBancoJdbc {

    /**
     * Método para teste
     */
    @Test
    public void initBanco() {
        UserPosDAO UserPosDAO = new UserPosDAO();
        Userposjava userposjava = new Userposjava();

        userposjava.setNome("DELETE");
        userposjava.setEmail("del@del.com");

        UserPosDAO.salvar(userposjava);
    }

    @Test
    public void initListar() {
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
    public void initBuscar() {

        UserPosDAO dao = new UserPosDAO();

        try {
            System.out.println("\n");
            Userposjava userposjava = dao.buscar(6L);
            System.out.println(userposjava);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void initAtualizar() {
        try {

            UserPosDAO dao = new UserPosDAO();

            Userposjava objetobanco = dao.buscar(6L);
            objetobanco.setNome("Teleco Teco");
            objetobanco.setEmail("teco@teco.com");

            dao.atualizar(objetobanco);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void initDeletar() {
        try {

            UserPosDAO dao = new UserPosDAO();
            dao.deletar(7L);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testeInsertTelefone() {
        try {
            Telefone telefone = new Telefone();

            telefone.setNumero("(61 9 9193-3273)");
            telefone.setTipo("Fixo");
            telefone.setUsuario(6L);

            UserPosDAO dao = new UserPosDAO();
            dao.salvarTelefone(telefone);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testeCarreagaFonesUser() {
        try {
            UserPosDAO dao = new UserPosDAO();

            List<BeanUserFone> beanUserFones = dao.listaUserFone(8L);

            for (BeanUserFone beanUserFone : beanUserFones){
                System.out.println("\n---------------");
                System.out.println(beanUserFone);
                System.out.println("---------------");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteUserFone() {
        try {
            UserPosDAO dao = new UserPosDAO();

            dao.deleteFonesPorUser(6L);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
