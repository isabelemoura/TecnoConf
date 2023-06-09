/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import br.com.fatec.DATABASE.Banco;
import br.com.fatec.model.Fornecedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Isaque
 */
public class FornecedorDAO implements DAO<Fornecedor> {

    private ResultSet rs;
    private Fornecedor fornecedor;
    private PreparedStatement pst;

    @Override
    public boolean insere(Fornecedor model) throws SQLException {
        boolean insere = false;
        String sql = "insert into fornecedor(Cnpj,telefoneFornecedor, nomeFornecedor, cep, enderecoFornecedor)"
                + "values(?,?,?,?,?)";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, model.getCNPJ());
        pst.setString(2, model.getCelular());
        pst.setString(3, model.getNomeFornecedor());
        pst.setString(4, model.getCep());
        pst.setString(5, model.getEndereco());

        insere = pst.executeUpdate() > 0 ? true : false;
        Banco.desconectar();
        return insere;
    }

    @Override
    public boolean remove(Fornecedor model) throws SQLException {
        boolean removeu = false;
        String sql = "delete from Fornecedor where Cnpj = ?";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, model.getCNPJ());

        removeu = pst.executeUpdate() > 0 ? true : false;
        Banco.desconectar();
        return removeu;
    }

    @Override
    public boolean altera(Fornecedor model) throws SQLException {
        boolean alterou = false;
        String sql = "update fornecedor telefoneFornecedor=?, nomeFornecedor=?, cep=?, enderecoFornecedor=? where Cnpj = ?";
        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, model.getCelular());
        pst.setString(2, model.getNomeFornecedor());
        pst.setString(3, model.getCep());
        pst.setString(4, model.getEndereco());
        pst.setString(5, model.getCNPJ());
        alterou = pst.executeUpdate() > 0 ? true : false;
        Banco.desconectar();
        return alterou;
    }

    @Override
    public Fornecedor buscaID(Fornecedor model) throws SQLException {
        fornecedor = null;
        String sql = "select * from fornecedor where CNPJ = ?";
        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, model.getCNPJ());

        rs = pst.executeQuery();
        while (rs.next()) {
            fornecedor = new Fornecedor();
            fornecedor.setCNPJ(rs.getString("Cnpj"));
            fornecedor.setCelular(rs.getString("telefoneFornecedor"));
            fornecedor.setEndereco(rs.getString("enderecoFornecedor"));
            fornecedor.setNomeFornecedor(rs.getString("nomeFornecedor"));
            fornecedor.setCep(rs.getString("cep"));

        }

        Banco.desconectar();
        return fornecedor;
    }

    @Override
    public Collection<Fornecedor> lista(String criterio) throws SQLException {
        //criar uma coleção
        Collection<Fornecedor> listagem = new ArrayList<>();

        fornecedor = null;
        //Comando SELECT
        String sql = "SELECT * FROM fornecedor ";
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
                fornecedor = new Fornecedor();
                //move os dados do resultSet para o objeto fornecedor
                fornecedor.setCNPJ(rs.getString("Cnpj"));
                fornecedor.setCelular(rs.getString("telefoneFornecedor"));
                fornecedor.setEndereco(rs.getString("enderecoFornecedor"));
                fornecedor.setNomeFornecedor(rs.getString("nomeFornecedor"));
                fornecedor.setCep(rs.getString("cep"));
                //adicionar na coleção

                listagem.add(fornecedor);
            } catch (SQLException ex) {
                System.out.println("Erro dentro do while: " + ex);
            }

        }

        Banco.desconectar();

        return listagem;
    }

}
