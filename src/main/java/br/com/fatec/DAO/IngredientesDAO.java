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
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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
        String sql = "insert into ingredientes(idProduto, nomeProduto,Qtde,nomeFornecedor,tipoProduto,unMedida, Cnpj)"
                + "values(?,?,?,?,?,?,?)";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, Integer.toString(model.getIdIngrediente()));
        pst.setString(2, model.getNomeProduto());
        pst.setString(3, model.getQuantidade());
        pst.setString(4, model.getFornecedor().getNomeFornecedor());
        pst.setString(5, model.getTipoProduto());
        pst.setString(6, model.getUnidadeMedida());
        pst.setString(7, model.getCNPJ());
        try {
            inseriu = pst.executeUpdate() > 0 ? true : false;
        } catch (SQLIntegrityConstraintViolationException ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR,
                    "Este ID já existe na base de dados!",
                    ButtonType.OK
            );

            alerta.showAndWait();
        }

        Banco.desconectar();

        return inseriu;
    }

    @Override
    public boolean remove(Ingredientes model) throws SQLException {
        boolean delerou = false;
        String sql = "DELETE FROM ingredientes where idProduto = ? and Cnpj = ?";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, Integer.toString(model.getIdIngrediente()));
        pst.setString(2, model.getCNPJ());

        delerou = pst.executeUpdate() > 0 ? true : false;
        return delerou;

    }

    @Override
    public boolean altera(Ingredientes model) throws SQLException {
        boolean alterou = false;
        String sql = "update ingredientes set nomeProduto=?,Qtde=?,nomeFornecedor=?,tipoProduto=?,unMedida=?, Cnpj=? where idProduto = ?";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, model.getNomeProduto());
        pst.setString(2, model.getQuantidade());
        pst.setString(3, model.getFornecedor().getNomeFornecedor());
        pst.setString(4, model.getTipoProduto());
        pst.setString(5, model.getUnidadeMedida());
        pst.setString(6, model.getCNPJ());
        pst.setString(7, Integer.toString(model.getIdIngrediente()));
        alterou = pst.executeUpdate() > 0 ? true : false;
        return alterou;
    }

    @Override
    public Ingredientes buscaID(Ingredientes model) throws SQLException {

        String sql = "SELECT * FROM ingredientes WHERE idProduto = ? and Cnpj=?";
        ingredientes = null;
        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, Integer.toString(model.getIdIngrediente()));
        pst.setString(2, model.getCNPJ());
        rs = pst.executeQuery();
        while (rs.next()) {
            ingredientes = new Ingredientes();
            ingredientes.setCNPJ(rs.getString("Cnpj"));
            ingredientes.setIdIngrediente(Integer.parseInt(rs.getString("idProduto")));
            ingredientes.setNomeProduto(rs.getString("nomeProduto"));
            ingredientes.setQuantidade(rs.getString("Qtde"));
            ingredientes.setTipoProduto(rs.getString("tipoProduto"));
            ingredientes.setUnidadeMedida(rs.getString("unMedida"));
        }

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
