/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author m4tuu
 */
public abstract class EntidadAbstracta  implements Serializable, IEntidadAbstracta{
    
    protected Date fechabaja;
    protected String error="";

    public Date getFechabaja() {
        return fechabaja;
    }

    public void setFechabaja(Date fechabaja) {
        this.fechabaja = fechabaja;
    }
        
    public String getTipoDeClase(){
        return this.getClass().getName();
    }
    
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    public void addError(String error) {
        this.error+= error +";";
    }
    
    public String[] getErrores() {
        return error.split(";");
    }
    public abstract int getId();
    public abstract void setId(int id);
}
