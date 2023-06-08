/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import br.com.fatec.DATABASE.Banco;
import br.com.fatec.model.Cliente;
import br.com.fatec.model.EstadoCode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Isaque
 */
public class ClienteDAO implements DAO<Cliente> {

    private ResultSet rs;
    private Cliente cliente;
    private PreparedStatement pst;

    @Override
    public boolean insere(Cliente model) throws SQLException {
        boolean inseriu = false;
        String sql = "insert into Cliente(cpfCliente, nomeCliente, emailCliente, celularCliente, cep, enderecoCliente) "
                + "values(?,?,?,?,?,?)";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, model.getCpf());
        pst.setString(2, model.getNomeCliente());
        pst.setString(3, model.getEmail());
        pst.setString(4, model.getCelular());
        pst.setString(5, model.getCep());
        pst.setString(6, model.getEndereco());

        inseriu = pst.executeUpdate() > 0 ? true : false;
        Banco.desconectar();
        return inseriu;
    }

    @Override
    public boolean remove(Cliente model) throws SQLException {
        boolean deletou = false;
        String sql = "delete from Cliente where cpfCliente = ?";
        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, model.getCpf());

        deletou = pst.executeUpdate() > 0 ? true : false;
        Banco.desconectar();
        return deletou;
    }

    @Override
    public boolean altera(Cliente model) throws SQLException {
        boolean alterado = false;
        String sql = "update Cliente set nomeCliente = ?, emailCliente = ?, celularCliente = ?, cep=?, enderecoCliente = ?";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, model.getNomeCliente());
        pst.setString(2, model.getEmail());
        pst.setString(3, model.getCelular());
        pst.setString(4, model.getCep());
        pst.setString(5, model.getEndereco());

        alterado = pst.executeUpdate() > 0 ? true : false;
        Banco.desconectar();

        return alterado;
    }

    @Override
    public Cliente buscaID(Cliente model) throws SQLException {
        EstadoCodeDAO estadoDAO = new EstadoCodeDAO();

        cliente = null;
        String sql = "select * from Cliente where cpfCliente = ?";
        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, model.getCpf());

        rs = pst.executeQuery();
        if (rs.next()) {
            cliente = new Cliente();
            cliente.setCpf(rs.getString("cpfCliente"));
            cliente.setNomeCliente(rs.getString("nomeCliente"));
            cliente.setEmail(rs.getString("emailCliente"));
            cliente.setCelular(rs.getString("celularCliente"));
            cliente.setEndereco(rs.getString("enderecoCliente"));
            cliente.setCep(rs.getString("cep"));

            //verificar como trazer a UF do banco de dados no registro do cliente 
        }
        Banco.desconectar();
        return cliente;
    }

    @Override
    public Collection<Cliente> lista(String criterio) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
