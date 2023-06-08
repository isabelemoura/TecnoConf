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
    private String nomeProduto;
    private String nomeCliente;
    private float preco;
    private String celular;
    private LocalDate dataPedido;
    private String obsPedido;
    private String status;

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

    public Pedido(int idPedido, Cliente cliente, String nomeProduto, String nomeCliente, float preco, String celular, LocalDate dataPedido, String obsPedido, String status) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.nomeProduto = nomeProduto;
        this.nomeCliente = nomeCliente;
        this.preco = preco;
        this.celular = celular;
        this.dataPedido = dataPedido;
        this.obsPedido = obsPedido;
        this.status = status;
    }

    public int getPedido() {
        return idPedido;
    }

    public void setPedido(int pedido) {
        this.idPedido = pedido;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getObsPedido() {
        return obsPedido;
    }

    public void setObsPedido(String obsPedido) {
        this.obsPedido = obsPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
