/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author matuu
 */
public class RegistroEquipo {
    
    int id, idEquipo, estacionServicioID;
    String iniHoro, finHoro, iniOdo, finOdo, cantCombustible, est_Servicio,tarea;
    boolean datosCarga;

    public RegistroEquipo() {
        id=0;
    }

    public int getEstacionServicioID() {
        return estacionServicioID;
    }

    public void setEstacionServicioID(int estacionServicioID) {
        this.estacionServicioID = estacionServicioID;
    }
    

    public String getCantCombustible() {
        return cantCombustible;
    }

    public void setCantCombustible(String cantCombustible) {
        this.cantCombustible = cantCombustible;
    }

    public boolean isDatosCarga() {
        return datosCarga;
    }

    public void setDatosCarga(boolean datosCarga) {
        this.datosCarga = datosCarga;
    }

    public String getEst_Servicio() {
        return est_Servicio;
    }

    public void setEst_Servicio(String est_Servicio) {
        this.est_Servicio = est_Servicio;
    }

    public String getFinHoro() {
        return finHoro;
    }

    public void setFinHoro(String finHoro) {
        this.finHoro = finHoro;
    }

    public String getFinOdo() {
        return finOdo;
    }

    public void setFinOdo(String finOdo) {
        this.finOdo = finOdo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getIniHoro() {
        return iniHoro;
    }

    public void setIniHoro(String iniHoro) {
        this.iniHoro = iniHoro;
    }

    public String getIniOdo() {
        return iniOdo;
    }

    public void setIniOdo(String iniOdo) {
        this.iniOdo = iniOdo;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RegistroEquipo other = (RegistroEquipo) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idEquipo != other.idEquipo) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.id;
        hash = 47 * hash + this.idEquipo;
        return hash;
    }
    
    
    
    
    
    
}
