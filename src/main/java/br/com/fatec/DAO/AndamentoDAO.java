/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import br.com.fatec.DATABASE.Banco;
import br.com.fatec.model.Andamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Isaque
 */
public class AndamentoDAO implements DAO<Andamento>{

    private ResultSet rs;
    private Andamento andamento;
    private PreparedStatement pst;
    
    @Override
    public boolean insere(Andamento model) throws SQLException {
        boolean inseriu = false;
        String sql = "insert into Andamento (idPedido, andamentoPedido)"
                + "values (?,?)";
        
        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, Integer.toString(model.getIdPedido()));
        pst.setString(2, model.getAndamentoPedido());
        
        inseriu = pst.executeUpdate() > 0 ? true : false;
        return inseriu;
    }

    @Override
    public boolean remove(Andamento model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean altera(Andamento model) throws SQLException {
        boolean alterou = false;
        String sql = "update Andamento set andamentoPedido = ? where idPedido = ?";
        
        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, model.getAndamentoPedido());
        pst.setString(2, Integer.toString(model.getIdPedido()));
        
        alterou = pst.executeUpdate() > 0 ? true : false;
        
        return alterou;
    }

    @Override
    public Andamento buscaID(Andamento model) throws SQLException {
        andamento = null;
        String sql = "select * from andamento where idPedido = ?";
        
        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, Integer.toString(model.getIdPedido()));
        
        rs = pst.executeQuery();
        while(rs.next()){
            andamento = new Andamento();
            andamento.setAndamentoPedido(rs.getString("andamentoPedido"));
            andamento.setIdPedido(Integer.parseInt(rs.getString("idPedido")));
        }
        return andamento;
    }

    @Override
    public Collection<Andamento> lista(String criterio) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
