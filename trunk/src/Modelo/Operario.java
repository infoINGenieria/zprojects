/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author matuu
 */
public class Operario {
   
    int id, funcion;
    String n_legajo, nombre, cuil, observaciones;
    boolean desarraigo;
    Date vto_carnet;

    public boolean isDesarraigo() {
        return desarraigo;
    }

    public void setDesarraigo(boolean desarraigo) {
        this.desarraigo = desarraigo;
    }

            
    public int getFuncion() {
        return funcion;
    }

    public void setFuncion(int funcion) {
        this.funcion = funcion;
    }

    
    public String getCuil() {
        return cuil;
    }

    public void setCuil(String CUIL) {
        this.cuil = CUIL;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String NOMBRE) {
        this.nombre = NOMBRE;
    }

    public String getN_legajo() {
        return n_legajo;
    }

    public void setN_legajo(String N_LEGAJO) {
        this.n_legajo = N_LEGAJO;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String OBSERVACIONES) {
        this.observaciones = OBSERVACIONES;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Operario(String NOMBRE) {
        this.nombre = NOMBRE;
    }
    public Operario(int id){
        this.id = id;
    }
    
    public Operario() { }
            
    @Override
    public String toString(){
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Operario other = (Operario) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        return hash;
    }

    public Date getVto_carnet() {
        return vto_carnet;
    }

    public void setVto_carnet(Date vto_carnet) {
        this.vto_carnet = vto_carnet;
    }
    
    
                     
}
