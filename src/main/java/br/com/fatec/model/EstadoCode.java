/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Isaque
 */
public class EstadoCode {
    private String uf;

    public EstadoCode() {
    }

    public EstadoCode(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return uf;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
