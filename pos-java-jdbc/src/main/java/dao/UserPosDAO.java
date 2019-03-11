package dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.BeanUserFone;
import model.Telefone;
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

    /**
     * Método para adicionar um novo registro de Usuário.
     * @param userposjava
     */
    public void salvar(Userposjava userposjava) {

        try {
            String sql = "INSERT INTO userposjava (nome, email) VALUES (?, ?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, userposjava.getNome());
            insert.setString(2, userposjava.getEmail());
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
     * Método para adicionar um novo registro de Telefone.
     *
     * @param telefone
     */
    public void salvarTelefone(Telefone telefone) {

        try {
            String sql = "INSERT INTO telefoneuser (numero, tipo, usuariopessoa) VALUES (?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, telefone.getNumero());
            statement.setString(2, telefone.getTipo());
            statement.setLong(3, telefone.getUsuario());
            statement.execute();
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
     * Método para listar os registros de Usuários.
     *
     * @return
     */
    public List<Userposjava> listar() throws Exception {
        List<Userposjava> list = new ArrayList<Userposjava>();

        String sql = "SELECT * FROM userposjava";

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
     * Método para buscar um registro de Usuário específico.
     *
     * @return retorno
     */
    public Userposjava buscar(Long id) throws Exception {
        Userposjava retorno = new Userposjava();

        String sql = "SELECT * FROM userposjava WHERE id = '" + id + "'";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado = statement.executeQuery();

        while (resultado.next()) { // Retorna um ou nada.

            // Seta os atributos vindos do banco
            retorno.setId(resultado.getLong("id"));
            retorno.setNome(resultado.getString("nome"));
            retorno.setEmail(resultado.getString("email"));
        }

        return retorno;
    }

    /**
     * Método para buscar registros de Usuários com seus respectivos Telefones.
     *
     * @return beanUserFones
     */
    public List<BeanUserFone> listaUserFone (Long idUser){
        List<BeanUserFone> beanUserFones = new ArrayList<BeanUserFone>();

        String sql = " SELECT nome, numero, email FROM telefoneuser AS fone ";
        sql += " JOIN userposjava AS pos";
        sql += " ON fone.usuariopessoa = pos.id";
        sql += " WHERE pos.id = " + idUser;

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                BeanUserFone userFone = new BeanUserFone();

                userFone.setEmail(resultSet.getString("email"));
                userFone.setNome(resultSet.getString("nome"));
                userFone.setNumero(resultSet.getString("numero"));
                beanUserFones.add(userFone);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return beanUserFones;
    }

    /**
     * Método para atualizar um registro de um Usuário.
     *
     * @param userposjava
     */
    public void atualizar(Userposjava userposjava) {
        try {

            String sql = "UPDATE userposjava SET nome = ?, email = ? WHERE id = '" + userposjava.getId() + "'";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userposjava.getNome());
            statement.setString(2, userposjava.getEmail());

            statement.execute();
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }

            e.printStackTrace();
        }

    }

    /**
     * Método para excluir registro de um Usuário.
     * @param id
     */
    public void deletar(Long id){

        try {

            String sql = "DELETE FROM userposjava WHERE id = '" + id +"'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            connection.commit();

        }catch (SQLException e) {
            try {
                connection.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }


    public void deleteFonesPorUser(Long idUser){

        try {
            String sqlFone = "DELETE FROM telefoneuser WHERE usuariopessoa = "+idUser;
            String sqlUser = "DELETE FROM userposjava WHERE id = "+idUser;

            // Exclui os registros do Filho.
            PreparedStatement statement = connection.prepareStatement(sqlFone);
            statement.executeUpdate();
            connection.commit();

            // Exclui os registros do Pai.
            PreparedStatement state = connection.prepareStatement(sqlUser);
            state.executeUpdate();
            connection.commit();

        }catch (SQLException e) {
            try {
                connection.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }


    }
}
