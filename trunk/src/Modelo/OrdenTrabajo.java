/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Utils.FechaUtil;
import java.util.Date;

/**
 *
 * @author matuu
 */
public class OrdenTrabajo {
    
    private int OrdenTrabajoID, nInternoID;
    private String km, hs, detalleServicio, manoDeObra, mantenimiento, nInterno, solicitante;
    private Date fecha, fechaApertura, fechaCierre;

    public OrdenTrabajo(){}
    
    
    public int getOrdenTrabajoID() {
        return OrdenTrabajoID;
    }

    public void setOrdenTrabajoID(int OrdenTrabajoID) {
        this.OrdenTrabajoID = OrdenTrabajoID;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }
    
    

    public String getDetalleServicio() {
        return detalleServicio;
    }

    public void setDetalleServicio(String detalleServicio) {
        this.detalleServicio = detalleServicio;
    }

    public String getnInterno() {
        return nInterno;
    }

    public void setnInterno(String nInterno) {
        this.nInterno = nInterno;
    }

    public int getnInternoID() {
        return nInternoID;
    }

    public void setnInternoID(int nInternoID) {
        this.nInternoID = nInternoID;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getManoDeObra() {
        return manoDeObra;
    }

    public void setManoDeObra(String manoDeObra) {
        this.manoDeObra = manoDeObra;
    }

    public String getMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(String mantenimiento) {
        this.mantenimiento = mantenimiento;
    }

   
    
    @Override
    public String toString(){
        return nInterno+" - "+FechaUtil.getFecha(fecha) +" ("+mantenimiento+")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrdenTrabajo other = (OrdenTrabajo) obj;
        if (this.OrdenTrabajoID != other.OrdenTrabajoID) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.OrdenTrabajoID;
        return hash;
    }

    public String getHs() {
        return hs;
    }

    public void setHs(String hs) {
        this.hs = hs;
    }
    
    
    
    
}
