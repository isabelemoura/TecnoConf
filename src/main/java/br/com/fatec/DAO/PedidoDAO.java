/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.DAO;

import br.com.fatec.DATABASE.Banco;
import br.com.fatec.model.Andamento;
import br.com.fatec.model.Cliente;
import br.com.fatec.model.Pedido;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Aluno
 */
public class PedidoDAO implements DAO<Pedido> {

    private ResultSet rs;
    private Pedido pedido;
    private PreparedStatement pst;

    @Override
    public boolean insere(Pedido model) throws SQLException {
        boolean inclui = false;
        boolean incluiAndamento = false;

        AndamentoDAO andamentoDAO = new AndamentoDAO();
        Andamento andamento = new Andamento();

        String sql = "insert into pedido (idPedido,valorPedido, celularCliente,dataPedido,obsPedido,cpfCliente)"
                + "values(?,?,?,?,?,?)";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, Integer.toString(model.getIdPedido()));
        pst.setString(2, model.getPreco());
        pst.setString(3, model.getCelular());
        pst.setString(4, model.getDataPedido());
        pst.setString(5, model.getObsPedido());
        pst.setString(6, model.getCpf());

        inclui = pst.executeUpdate() > 0 ? true : false;

        andamento.setAndamentoPedido(model.getStatus());
        andamento.setIdPedido(model.getIdPedido());
        incluiAndamento = andamentoDAO.insere(andamento);

        Banco.desconectar();

        if (inclui && incluiAndamento) {
            return inclui;
        } else {
            return false;
        }
//        inclui = pst.executeUpdate() > 0 ? true : false;
    }

    @Override
    public boolean remove(Pedido model) throws SQLException {
        boolean removeu = false;
        String sql = "delete from pedido where idPedido = ?";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, Integer.toString(model.getIdPedido()));
        removeu = pst.executeUpdate() > 0 ? true : false;
        return removeu;
    }

    @Override
    public boolean altera(Pedido model) throws SQLException {
        boolean alterar = false;
        boolean alterarAndamento = false;
        String sql = "update pedido set valorPedido=?, celularCliente=?,dataPedido=?,obsPedido=? where idPedido = ?";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, model.getPreco());
        pst.setString(2, model.getCelular());
        pst.setString(3, model.getDataPedido());
        pst.setString(4, model.getObsPedido());
        pst.setString(5, Integer.toString(model.getIdPedido()));

        //Alterando andandomento
        AndamentoDAO anDAO = new AndamentoDAO();
        Andamento andamento = new Andamento();
        andamento.setAndamentoPedido(model.getStatus());
        andamento.setIdPedido(model.getIdPedido());
        alterarAndamento = anDAO.altera(andamento);
        alterar = pst.executeUpdate() > 0 ? true : false;

        if (alterar && alterarAndamento) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Pedido buscaID(Pedido model) throws SQLException {
        pedido = null;

        String Sql = "SELECT * FROM PEDIDO where idPedido = ?";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(Sql);
        pst.setString(1, Integer.toString(model.getIdPedido()));

        rs = pst.executeQuery();
        while (rs.next()) {
            pedido = new Pedido();
            pedido.setCelular(rs.getString("celularCliente"));
            pedido.setCpf(rs.getString("cpfCliente"));
            pedido.setDataPedido(rs.getString("dataPedido"));
            pedido.setIdPedido(Integer.parseInt(rs.getString("idPedido")));
            pedido.setObsPedido(rs.getString("obsPedido"));
            pedido.setPreco(rs.getString("valorPedido"));
        }

        return pedido;
    }

    @Override
    public Collection<Pedido> lista(String criterio) throws SQLException {
        ArrayList<Pedido> lista = new ArrayList<>();
        //serve para pegar os dados do cliente confome o CPF
        Collection<Cliente> listaCliente = new ArrayList<>();
        String sql = "SELECT * FROM Pedido";

        //precisa fazer filtro para listagem
        if (criterio != null && criterio.length() > 0) {
            sql += " WHERE " + criterio;
        }

        //abre a conexao com o banco
        Banco.conectar();

        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);

        //executar o comando
        rs = pst.executeQuery(); //esse método serve para SELECT

        //Varre todo o resultado da consulta e coloca cada registro dentro
        //de um objeto e coloca o objeto dentro da coleção
        while (rs.next()) {
            //criar o objeto

            try {
                pedido = new Pedido();

                //mover os dados do resultSet para o objeto proprietário
                pedido.setCelular(rs.getString("celularCliente"));
                pedido.setCpf(rs.getString("cpfCliente"));
                pedido.setDataPedido(rs.getString("dataPedido"));
                pedido.setIdPedido(Integer.parseInt(rs.getString("idPedido")));
                pedido.setObsPedido(rs.getString("obsPedido"));
                pedido.setPreco(rs.getString("valorPedido"));

              

                lista.add(pedido);
            } catch (SQLException ex) {

                Alert alerta = new Alert(Alert.AlertType.ERROR,
                        "Erro Preenche Tabela: " + ex.getMessage(),
                        ButtonType.OK);
                alerta.showAndWait();
                lista.add(pedido);
            }
        }

        //fecha a conexao
        Banco.desconectar();

        //devolve o objeto proprietario
        return lista;
    }

}
