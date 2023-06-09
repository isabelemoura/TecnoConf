/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

/**
 *
 * @author Isaque
 */
public class Andamento {
    private int idPedido;
    private String andamentoPedido;

    @Override
    public String toString() {
        return getAndamentoPedido();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.idPedido;
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
        final Andamento other = (Andamento) obj;
        return this.idPedido == other.idPedido;
    }

    
    
    public Andamento(int idPedido, String andamentoPedido) {
        this.idPedido = idPedido;
        this.andamentoPedido = andamentoPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getAndamentoPedido() {
        return andamentoPedido;
    }

    public void setAndamentoPedido(String andamentoPedido) {
        this.andamentoPedido = andamentoPedido;
    }
    
    
    
    
}
