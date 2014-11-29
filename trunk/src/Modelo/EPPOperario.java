/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author m4tuu
 */
@Entity
@Table(name="epp_operarios")
public class EPPOperario extends EntidadAbstracta {
    
    
    private int id; 
    private int tipo;
    private String valor;
    private Operario operario;
    private EPP epp;
    
    public static int UNIDAD = 1;
    public static int TALLE = 2;
    
    @ManyToOne
    @JoinColumn(name = "epp_id",nullable = false)
    public EPP getEpp() {
        return epp;
    }

    public void setEpp(EPP epp) {
        this.epp = epp;
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "operario_id",nullable = false)
    public Operario getOperario() {
        return operario;
    }

    public void setOperario(Operario operario) {
        this.operario = operario;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EPPOperario other = (EPPOperario) obj;
        if (!this.operario.equals(other.operario)) {
            return false;
        }
        if (!this.epp.equals(other.epp)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    public EPPOperario(Operario operario, EPP epp) {
        this.operario = operario;
        this.epp = epp;
        if(epp.tieneTalle()){
            tipo = EPPOperario.TALLE;
        }else{
            tipo = EPPOperario.UNIDAD;
        }
    }

    public EPPOperario(Operario operario, EPP epp, int tipo) {
        this.operario = operario;
        this.epp = epp;
        this.tipo = tipo;
    }

    public EPPOperario() {
    }

    @Override
    public boolean validate() {
        if (operario == null) {
            error += "Falta asociación a Operario;";
        }
        if (epp == null) {
            error += "Falta asociación a EPP;";
        }
        if (valor == null || valor.isEmpty()) {
            error += "Valor no debería ser vacio;";
        }
        return error.isEmpty();
    }
    
    
}
