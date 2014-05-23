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
public class EntidadAbstracta {
    
    protected Date fechabaja;

    public Date getFechabaja() {
        return fechabaja;
    }

    public void setFechabaja(Date fechabaja) {
        this.fechabaja = fechabaja;
    }
    
    public String getTipo(){
        return this.getClass().getName();
    }
}
