/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

import java.time.LocalDate;
import java.sql.Date;

/**
 *
 * author Isaque
 */
public class Pedido {

    private int idPedido;
    private Cliente cliente;
    private String nomeCliente;
    private String preco;
    private String celular;
    private String dataPedido;
    private String obsPedido;
    private String status;
    private String cpf;
    

    public Pedido(int idPedido, Cliente cliente, String nomeCliente, String preco, String celular, String dataPedido, String obsPedido, String status) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.nomeCliente = nomeCliente;
        this.preco = preco;
        this.celular = celular;
        this.dataPedido = dataPedido;
        this.obsPedido = obsPedido;
        this.status = status;
    }

    public Pedido() {

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.idPedido;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        if (this.idPedido != other.idPedido) {
            return false;
        }
        return true;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public int getPedido() {
        return idPedido;
    }

    public void setPedido(int pedido) {
        this.idPedido = pedido;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObsPedido() {
        return obsPedido;
    }

    public void setObsPedido(String obsPedido) {
        this.obsPedido = obsPedido;
    }

    

}
