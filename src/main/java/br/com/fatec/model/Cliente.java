/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

import java.util.Objects;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Isaque
 */
public class Cliente {

    private String cpf;
    private String nomeCliente;
    private String email;
    private String celular;
    private String endereco;
    private String cep;

    public Cliente(String cpf, String nomeCliente, String email, String celular, String endereco, String cep) {
        this.cpf = cpf;
        this.nomeCliente = nomeCliente;
        this.email = email;
        this.celular = celular;
        this.endereco = endereco;
        this.cep = cep;
    }

    public Cliente() {
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.cpf);
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
        final Cliente other = (Cliente) obj;
        return Objects.equals(this.cpf, other.cpf);
    }

    @Override
    public String toString() {
        return getNomeCliente();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        if (celular.length() > 20) {
            Alert alerta = new Alert(Alert.AlertType.ERROR,
                    "CPF invalido",
                    ButtonType.OK);
            alerta.showAndWait();
        } else {
            this.celular = celular;
        }

    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
