/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author matuu
 */
public class EstacionServicio {
    
    private int estacionServicioID;
    private String nombre;

    public EstacionServicio(String nombre) {
        this.nombre = nombre;
    }

    public EstacionServicio(int estacionServicioID, String nombre) {
        this.estacionServicioID = estacionServicioID;
        this.nombre = nombre;
    }

    public EstacionServicio(int estacionServicioID) {
        this.estacionServicioID = estacionServicioID;
    }

    public EstacionServicio() {
    }

    public int getEstacionServicioID() {
        return estacionServicioID;
    }

    public void setEstacionServicioID(int estacionServicioID) {
        this.estacionServicioID = estacionServicioID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EstacionServicio other = (EstacionServicio) obj;
        if (this.estacionServicioID != other.estacionServicioID) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.estacionServicioID;
        return hash;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
    
}
