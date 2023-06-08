/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.DAO;

import br.com.fatec.DATABASE.Banco;
import br.com.fatec.model.Ingredientes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Aluno
 */
public class IngredientesDAO implements DAO<Ingredientes> {

     private ResultSet rs;
    private Ingredientes ingredientes;
    private PreparedStatement pst;
    
    @Override
    public boolean insere(Ingredientes model) throws SQLException {
        
        
        boolean inseriu = false;
        String sql = "insert into ingredientes(nomeProduto,Qtde,nomeFornecedor,tipoProduto,unMedida, Cnpj)"
                + "values(?,?,?,?,?,?)";
        
        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, model.getNomeProduto());
        pst.setString(2, Integer.toString(model.getQuantidade()));
        pst.setString(3, model.getFornecedor().getNomeFornecedor());
        pst.setString(4, model.getTipoProduto());
        pst.setString(5, model.getUnidadeMedida());
        pst.setString(6, model.getCNPJ());
        
        inseriu = pst.executeUpdate() > 0 ? true : false;
        Banco.desconectar();
        
        return inseriu;
    }

    @Override
    public boolean remove(Ingredientes model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean altera(Ingredientes model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ingredientes buscaID(Ingredientes model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Ingredientes> lista(String criterio) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
