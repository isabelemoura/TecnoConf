/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import br.com.fatec.DATABASE.Banco;
import br.com.fatec.model.EstadoCode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Isaque
 */
public class EstadoCodeDAO implements DAO<EstadoCode> {

    private ResultSet rs;
    private EstadoCode estado;
    private PreparedStatement pst;

    @Override
    public boolean insere(EstadoCode model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean remove(EstadoCode model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean altera(EstadoCode model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public EstadoCode buscaID(EstadoCode model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<EstadoCode> buscaUFs(String state) throws SQLException {
    List<EstadoCode> ufs = new ArrayList<>();
    String sql = "SELECT * FROM EstadoCode";
    if (!state.isEmpty()) {
        sql += " WHERE uf_code = ?";
    }

    Banco.conectar();
    pst = Banco.obterConexao().prepareStatement(sql);
    if (!state.isEmpty()) {
        pst.setString(1, state);
    }
    rs = pst.executeQuery();
    while (rs.next()) {
        EstadoCode uf = new EstadoCode();
        uf.setUf(rs.getString("uf_code"));
        ufs.add(uf);
    }
    Banco.desconectar();
    return ufs;
}


    @Override
    public Collection<EstadoCode> lista(String criterio) throws SQLException {
        Collection<EstadoCode> listagem = new ArrayList<>();

        estado = null;
        //Comando SELECT
        String sql = "SELECT * FROM estadocode";
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
            //cria o objeto estadoCode
            estado = new EstadoCode();
            //move os dados do resultSet para o objeto veiculo
            estado.setUf(rs.getString("uf_code"));

            //adicionar na coleção
            listagem.add(estado);
        }

        Banco.desconectar();

        return listagem;
    }

}
