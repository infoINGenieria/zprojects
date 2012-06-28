/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Utils.FechaUtil;
import java.util.Date;

/**
 *
 * @author Matu
 */
public class Alarma {
    int alarmaID, riID;
    String comentario, nombre;
    Date fecha, fecha_previa;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public int getAlarmaID() {
        return alarmaID;
    }

    public void setAlarmaID(int alarmaID) {
        this.alarmaID = alarmaID;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha_previa() {
        return fecha_previa;
    }

    public void setFecha_previa(Date fecha_previa) {
        this.fecha_previa = fecha_previa;
    }

    public int getRiID() {
        return riID;
    }

    public void setRiID(int riID) {
        this.riID = riID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Alarma other = (Alarma) obj;
        if (this.alarmaID != other.alarmaID) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.alarmaID;
        return hash;
    }

    @Override
    public String toString() {
        return nombre + " ("+FechaUtil.getFecha(fecha)+")";
    }
    
    
}
