/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author matuuar
 */
public class RiItem {
    
    private int riItemId, riId;
    String OC_num, proveedor;
    Date fecha_necesidad, fecha_emision, fecha_oc, fecha_entrega;
    private String cantidad, unidad, detalle, observacion;

    public RiItem() {
        fecha_necesidad = new Date();
        fecha_emision = new Date();
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

    public String getEstado(){
        if(fecha_necesidad!=null && fecha_emision!=null && fecha_oc !=null && fecha_entrega!=null){
            return "Cumplida";
        }
        if(fecha_necesidad!=null && fecha_emision!=null && fecha_oc !=null && fecha_entrega==null){
            return "OC generada";
        }
        
            return "Emitida";
        
        
    }

    public String getOC_num() {
        return OC_num;
    }

    public void setOC_num(String OC_num) {
        this.OC_num = OC_num;
    }

    public Date getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(Date fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public Date getFecha_necesidad() {
        return fecha_necesidad;
    }

    public void setFecha_necesidad(Date fecha_necesidad) {
        this.fecha_necesidad = fecha_necesidad;
    }

    public Date getFecha_oc() {
        return fecha_oc;
    }

    public void setFecha_oc(Date fecha_oc) {
        this.fecha_oc = fecha_oc;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    
    
    
    
    
}
