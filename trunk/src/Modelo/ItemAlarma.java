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
public class ItemAlarma implements Comparable<ItemAlarma>{
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

    public int compareTo(ItemAlarma o) {
        if (this.fecha.before(o.fecha)) {
            return -1;
        } else if (this.fecha.after(o.fecha)) {
            return 1;
        } else {
            return 0;
        }
    }
    
    
    
    
}
