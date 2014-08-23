/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

import java.util.Date;

/**
 *
 * @author m4tuu
 */
public class ItemAlarmaBean implements Comparable<ItemAlarmaBean>{
    private Date fecha, fechaPrevia;
    private String mensaje, comentario;

    public ItemAlarmaBean(Date fecha, Date fechaPrevia, String mensaje, String comentario) {
        this.fecha = fecha;
        this.fechaPrevia = fechaPrevia;
        this.mensaje = mensaje;
        this.comentario = comentario;
    }

    public ItemAlarmaBean() {
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaPrevia() {
        return fechaPrevia;
    }

    public void setFechaPrevia(Date fechaPrevia) {
        this.fechaPrevia = fechaPrevia;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "Alarma: " + "(" + fecha + ") " + mensaje ;
    }
    
    @Override
    public int compareTo(ItemAlarmaBean o) {
        if (this.fecha.before(o.fecha)) {
            return -1;
        } else if (this.fecha.after(o.fecha)) {
            return 1;
        } else {
            return 0;
        }
    }
}
