/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.DAO;

import br.com.fatec.DATABASE.Banco;
import br.com.fatec.model.Fornecedor;
import br.com.fatec.model.Ingredientes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        pst.setString(2, model.getQuantidade());
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
        ingredientes = null;
        String sql = "select * from ingredientes where Cnpj = ?";
        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, model.getCNPJ());
        
        rs = pst.executeQuery();
        while (rs.next()) {
            
            ingredientes.setNomeProduto(rs.getString("nomeProduto"));
            ingredientes.setQuantidade(rs.getString("Qtde"));
            ingredientes.setTipoProduto(rs.getString("tipoProduto"));
            ingredientes.setUnidadeMedida(rs.getString("unMedida"));
            
            Fornecedor forne = new Fornecedor();
            FornecedorDAO fdao  = new FornecedorDAO();
            forne.setNomeFornecedor(rs.getString("nomeFornecedor"));
            forne = fdao.buscaID(forne);
            ingredientes.setFornecedor(forne);

        }

        Banco.desconectar();
        System.out.println("Fornecedor CNPJ: "+ model.getCNPJ());
        
        return ingredientes;
    }

    @Override
    public Collection<Ingredientes> lista(String criterio) throws SQLException {
        //criar uma coleção
        Collection<Ingredientes> listagem = new ArrayList<>();
        Ingredientes ingre = null;

        //Comando SELECT
        String sql = "SELECT * FROM ingredientes ";
        //colocar filtro ou nao
        if (criterio.length() != 0) {
            sql += "WHERE " + criterio;
        }

        //conecta ao banco
        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);

        //Executa o comando SELECT
        rs = pst.executeQuery();

        //le o próximo regitro
        while (rs.next()) { //achou 1 registro
            //cria o objeto veiculo
            try {
                Fornecedor forne = new Fornecedor();
                //move os dados do resultSet para o objeto fornecedor
                ingre.setCNPJ(rs.getString("Cnpj"));
                forne.setNomeFornecedor(rs.getString("nomeFornecedor"));
                ingre.setFornecedor(forne);
                ingre.setNomeProduto(rs.getString("nomeProduto"));
                ingre.setQuantidade(rs.getString("Qtde"));
                ingre.setTipoProduto(rs.getString("tipoProduto"));
                ingre.setUnidadeMedida(rs.getString("unMedida"));

                listagem.add(ingre);
            } catch (SQLException ex) {
                System.out.println("Erro dentro do while: " + ex);
            }

        }

        Banco.desconectar();

        return listagem;
    }

}
