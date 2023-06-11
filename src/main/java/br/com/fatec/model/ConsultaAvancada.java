/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

/**
 *
 * @author Isaque
 */
public class ConsultaAvancada {
    private String cpf;
    private String nomeCliente;
    private String preco;
    private String celular;
    private String dataPedido;
    private String statusPedido;
    private String obsPedido;

    public ConsultaAvancada(String cpf, String nomeCliente, String preco, String celular, String dataPedido, String statusPedido, String obsPedido) {
        this.cpf = cpf;
        this.nomeCliente = nomeCliente;
        this.preco = preco;
        this.celular = celular;
        this.dataPedido = dataPedido;
        this.statusPedido = statusPedido;
        this.obsPedido = obsPedido;
    }

    public ConsultaAvancada() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public String getObsPedido() {
        return obsPedido;
    }

    public void setObsPedido(String obsPedido) {
        this.obsPedido = obsPedido;
    }
    
    
    
    
}
