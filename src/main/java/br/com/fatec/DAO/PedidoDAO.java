/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.DAO;

import br.com.fatec.DATABASE.Banco;
import br.com.fatec.model.Pedido;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Aluno
 */
public class PedidoDAO implements DAO <Pedido>{
    
    private ResultSet rs;
    private Pedido pedido;
    private PreparedStatement pst;
    
    @Override
    public boolean insere(Pedido model) throws SQLException {
        boolean inclui = false;
        String sql = "insert into Pedido(nomeProduto, valorPedido, "
                + "celularCliente, dataPedido, obsPedido) values(?,?,?,?,?,?)";
        
        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        
        pst.setString(1, model.getNomeProduto());
        pst.setString(2, model.getNomeCliente());
        pst.setFloat(3, model.getPreco());
        pst.setString(4, model.getCelular());
        pst.setDate(5, java.sql.Date.valueOf(model.getDataPedido()));       
        pst.setString(6, model.getObsPedido());
        
        inclui = pst.executeUpdate() > 0 ? true : false;
        Banco.desconectar();
        return inclui;
    }

    @Override
    public boolean remove(Pedido model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean altera(Pedido model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pedido buscaID(Pedido model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Pedido> lista(String criterio) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
