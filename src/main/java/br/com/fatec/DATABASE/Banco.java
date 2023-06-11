/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DATABASE;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Isaque
 */
public class Banco {
    
    //criar atributos
    public static String bancoDados, usuario, senha, servidor;
    public static int porta;

    //variavel de conexao
    public static java.sql.Connection conexao = null;

    //define valores padrão
    static {
        //mysql e mariaDB
        bancoDados = "tecnoconf";
        usuario = "teste";
        senha = "1234";
        servidor = "localhost";
        //este produto foi feito com banco dedos mysql rodando na porta 4000
        //por padrão, todos os bancos rodão na porta 3306
        porta = 3306;
        
        /*
        //sqlServer
        bancoDados = "Loja";
        usuario = "sa";
        senha = "123456";
        servidor = "localhost";
        porta = 1433;
        */
    }

    //métodos
    public static void conectar() throws SQLException {
        //mysql workbench
        String url = "jdbc:mysql://" + servidor +
                     ":" + porta + "/" + bancoDados;

        //MariaDB xampp
        //String url = "jdbc:mariadb://" + servidor +
        //             ":" + porta + "/" + bancoDados;
        
        //sqlServer
        //String url = "jdbc:sqlserver://" + servidor
        //        + ":" + porta + ";databaseName=" + bancoDados;

        conexao = DriverManager.getConnection(url, usuario, senha);
    }

    public static void desconectar() throws SQLException {
        conexao.close();
    }

    public static java.sql.Connection obterConexao() 
                throws SQLException {
        if (conexao == null) {
            throw new SQLException("Conexão está fechada..");
        } else {
            return conexao;
        }
    }
}
