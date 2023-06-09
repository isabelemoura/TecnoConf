/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.model;

import java.util.Objects;

/**
 *
 * @author Aluno
 */
public class Ingredientes {
    private String nomeProduto;
    private String quantidade;
    //composição
    private Fornecedor fornecedor;
    private String CNPJ;
    private String unidadeMedida;
    private String tipoProduto;

    public Ingredientes(String nomeProduto, String quantidade, Fornecedor fornecedor, String CNPJ, String unidadeMedida, String tipoProduto) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
        this.CNPJ = CNPJ;
        this.unidadeMedida = unidadeMedida;
        this.tipoProduto = tipoProduto;
    }
    

    public Ingredientes() {
    }
    //asdasdas

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
    
    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }
    

    @Override
    public String toString() {
        return fornecedor.getNomeFornecedor();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.CNPJ);
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
        final Ingredientes other = (Ingredientes) obj;
        if (!Objects.equals(this.CNPJ, other.CNPJ)) {
            return false;
        }
        return true;
    }
    
    
    
    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }
    
    
}
