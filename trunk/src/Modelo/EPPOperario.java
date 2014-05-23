/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author m4tuu
 */
public class EPPOperario {
    private int id, operarioId, eppId, tipo;
    private String valor;
    
    public static int UNIDAD = 1;
    public static int TALLE = 2;
    

    public int getEppId() {
        return eppId;
    }

    public void setEppId(int eppId) {
        this.eppId = eppId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOperarioId() {
        return operarioId;
    }

    public void setOperarioId(int operarioId) {
        this.operarioId = operarioId;
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
        if (this.operarioId != other.operarioId) {
            return false;
        }
        if (this.eppId != other.eppId) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    public EPPOperario(int operarioId, int eppId) {
        this.operarioId = operarioId;
        this.eppId = eppId;
    }

    public EPPOperario(int operarioId, int eppId, int tipo) {
        this.operarioId = operarioId;
        this.eppId = eppId;
        this.tipo = tipo;
    }
    
    public EPPOperario(int operarioId, EPP epp){
        this.operarioId = operarioId;
        this.eppId = epp.getId();
        if(epp.tieneTalle()){
            tipo = EPPOperario.TALLE;
        }else{
            tipo = EPPOperario.UNIDAD;
        }
    }

    public EPPOperario() {
    }
    
    
}
