/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.DAO;

import br.com.fatec.DATABASE.Banco;
import br.com.fatec.model.Andamento;
import br.com.fatec.model.Pedido;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

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
        String sql = "delete from pedidos where idPedido = ?";
        
        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, Integer.toString(model.getIdPedido()));
        removeu = pst.executeUpdate() > 0 ? true : false;
        return  removeu;
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
        
        if(alterar && alterarAndamento){
            return true;
        }else{
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
