/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import br.com.fatec.DATABASE.Banco;
import br.com.fatec.model.ConsultaAvancada;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Isaque
 */
public class ConsultaDAO implements DAO<ConsultaAvancada>{

    private ResultSet rs;
    private ConsultaAvancada consulta;
    private PreparedStatement pst;
    
    @Override
    public boolean insere(ConsultaAvancada model) throws SQLException {
        return false;
    }

    @Override
    public boolean remove(ConsultaAvancada model) throws SQLException {
        return false;
    }

    @Override
    public boolean altera(ConsultaAvancada model) throws SQLException {
        return false;
    }

    @Override
    public ConsultaAvancada buscaID(ConsultaAvancada model) throws SQLException {
        return null;
    }

    @Override
    public Collection<ConsultaAvancada> lista(String criterio) throws SQLException {
        //cria uma lista para armazenar os dados vindos do banco
        ArrayList<ConsultaAvancada> lista = new ArrayList<>();
        
        String sql = "SELECT cliente.cpfCliente, cliente.nomeCliente, "
                    + "cliente.celularCliente, pedido.dataPedido, pedido.valorPedido, "
                    + "pedido.obsPedido, andamento.andamentoPedido "
                    + "FROM cliente inner JOIN pedido "
                    + "on cliente.cpfCliente = pedido.cpfCliente "
                    + "INNER JOIN andamento on pedido.idPedido = andamento.idPedido";

        //precisa fazer filtro para listagem
        if(criterio != null && criterio.length() > 0) {
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
        while(rs.next()) {
            //criar o objeto
            consulta = new ConsultaAvancada();
            
            //mover os dados do resultSet para o objeto proprietário
            consulta.setCpf(rs.getString("cpfCliente"));
            consulta.setNomeCliente(rs.getString("nomeCliente"));
            consulta.setPreco(rs.getString("valorPedido"));
            consulta.setCelular(rs.getString("celularCliente"));
            consulta.setDataPedido(rs.getString("dataPedido"));
            consulta.setObsPedido(rs.getString("obsPedido"));
            consulta.setStatusPedido(rs.getString("andamentoPedido"));
           
            
            
            //move o objeto para a coleção
            lista.add(consulta);
        }
                
        //fecha a conexao
        Banco.desconectar();
        
        //devolve o objeto proprietario
        return lista;
    }
    
}
