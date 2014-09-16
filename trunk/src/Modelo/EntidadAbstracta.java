/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author m4tuu
 */
public abstract class EntidadAbstracta {
    
    protected Date fechabaja;
    protected String error;

    public Date getFechabaja() {
        return fechabaja;
    }

    public void setFechabaja(Date fechabaja) {
        this.fechabaja = fechabaja;
    }
        
    public String getTipo(){
        return this.getClass().getName();
    }
    
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    public String[] getErrores() {
        return error.split(";");
    }
    public abstract boolean validate();
    public abstract int getId();
    public abstract void setId(int id);
}
