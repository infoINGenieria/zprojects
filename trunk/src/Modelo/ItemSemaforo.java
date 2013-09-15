/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author matuuar
 */
public class ItemSemaforo {
    
    private String nombre;
    private int total;
    private Date ultimaEntrega, desdePeriodo, hastaPeriodo;
    private boolean alertar;
    private ArrayList<ParteDiario> partes = new ArrayList<ParteDiario>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getUltimaEntrega() {
        return ultimaEntrega;
    }

    public void setUltimaEntrega(Date ultimaEntrega) {
        this.ultimaEntrega = ultimaEntrega;
    }

    public boolean isAlertar() {
        return alertar;
    }

    public void setAlertar(boolean alertar) {
        this.alertar = alertar;
    }

    public ArrayList<ParteDiario> getPartes() {
        return partes;
    }

    public void setPartes(ArrayList<ParteDiario> partes) {
        this.partes = partes;
    }

    public Date getDesdePeriodo() {
        return desdePeriodo;
    }

    public void setDesdePeriodo(Date desdePeriodo) {
        this.desdePeriodo = desdePeriodo;
    }

    public Date getHastaPeriodo() {
        return hastaPeriodo;
    }

    public void setHastaPeriodo(Date hastaPeriodo) {
        this.hastaPeriodo = hastaPeriodo;
    }

    public ItemSemaforo(String nombre, int total, Date ultimaEntrega) {
        this.nombre = nombre;
        this.total = total;
        this.ultimaEntrega = ultimaEntrega;
    }

    public ItemSemaforo() {
    }

    public ItemSemaforo(String nombre) {
        this.nombre = nombre;
        
    }
    
    public ItemSemaforo(Operario operario){
        this.nombre = operario.getNombre();
        
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemSemaforo other = (ItemSemaforo) obj;
        if ((this.nombre == null) ? (other.nombre != null) : !this.nombre.equals(other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        return hash;
    }

   
    @Override
    public String toString() {
        return nombre.toUpperCase();
    }
    
    public boolean comprobar(Date limite){
        if (limite.compareTo(ultimaEntrega) < 0){
            alertar = true;
        }else{
            alertar = false;
            
        }
        return alertar;
    } 
    
}
