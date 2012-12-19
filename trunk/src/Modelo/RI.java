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
public class RI {
    int RI_ID, obraID;
    String RI_num, observaciones, OC_num, proveedor, codigoObra, solicitante;
    Date fecha_necesidad, fecha_emision, fecha_oc, fecha_entrega;
    
    public RI() {
    }

    @Override
    public String toString() {
        return "["+RI_num + "] "+codigoObra+" - "+solicitante;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RI other = (RI) obj;
        if (this.RI_ID != other.RI_ID) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.RI_ID;
        return hash;
    }

    public String getCodigoObra() {
        return codigoObra;
    }

    public void setCodigoObra(String codigoObra) {
        this.codigoObra = codigoObra;
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

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }
    
    

    public String getOC_num() {
        return OC_num;
    }

    public void setOC_num(String OC_num) {
        this.OC_num = OC_num;
    }

    public int getRI_ID() {
        return RI_ID;
    }

    public void setRI_ID(int RI_ID) {
        this.RI_ID = RI_ID;
    }

    public String getRI_num() {
        return RI_num;
    }

    public void setRI_num(String RI_num) {
        this.RI_num = RI_num;
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

    public int getObraID() {
        return obraID;
    }

    public void setObraID(int obraID) {
        this.obraID = obraID;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    
    
}
