/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author matuuar
 */
public class RI {
    int RI_ID, obraID;
    String RI_num, observaciones, codigoObra, solicitante;
    
    
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
    
    
    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
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
}
