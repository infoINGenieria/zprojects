/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author matuuar
 */
public class RiItem {
    
    private int riItemId, riId;
 
    private String cantidad, unidad, detalle, observacion;

    public RiItem() {
    }


    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getRiId() {
        return riId;
    }

    public void setRiId(int riId) {
        this.riId = riId;
    }

    public int getRiItemId() {
        return riItemId;
    }

    public void setRiItemId(int riItemId) {
        this.riItemId = riItemId;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RiItem other = (RiItem) obj;
        if (this.riItemId != other.riItemId) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.riItemId;
        return hash;
    }

    
    
    
    
}
