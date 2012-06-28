/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Matu
 */
public class Combustible {
    int combustibleID, estacionID;
    Date fecha;
    double cantidad;
    String responsable;

    public Combustible() {
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public int getCombustibleID() {
        return combustibleID;
    }

    public void setCombustibleID(int combustibleID) {
        this.combustibleID = combustibleID;
    }

    public int getEstacionID() {
        return estacionID;
    }

    public void setEstacionID(int estacionID) {
        this.estacionID = estacionID;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Combustible other = (Combustible) obj;
        if (this.combustibleID != other.combustibleID) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.combustibleID;
        return hash;
    }
    
    
}
