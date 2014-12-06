/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

import Modelo.EntidadAbstracta;
import java.util.Date;

/**
 *
 * @author Matu
 */
public class ItemAlarma extends EntidadAbstracta implements Comparable<ItemAlarma>{
    /*
     * 0 info
     * 1 warn
     */
    int tipo;
    String mensaje;
    Date fecha;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public ItemAlarma(int tipo, String mensaje) {
        this.tipo = tipo;
        this.mensaje = mensaje;
    }
    public ItemAlarma() {
        
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemAlarma other = (ItemAlarma) obj;
        if ((this.mensaje == null) ? (other.mensaje != null) : !this.mensaje.equals(other.mensaje)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (this.mensaje != null ? this.mensaje.hashCode() : 0);
        return hash;
    }

    @Override
    public int compareTo(ItemAlarma o) {
        if (this.fecha.before(o.fecha)) {
            return -1;
        } else if (this.fecha.after(o.fecha)) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean validate() {
        if (this.fecha == null) {
            this.error += "Fecha no puede ser nulo.;";
        }
        if (this.mensaje.isEmpty()) {
            this.error += "El campo Mensaje no puede ser vacio.;";
        }
        return this.error.isEmpty();
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {
        
    }
    
    
    
    
}
