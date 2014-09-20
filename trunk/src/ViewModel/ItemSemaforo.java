/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

import Modelo.EntidadAbstracta;
import Modelo.Operario;
import Modelo.ParteDiario;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author matuuar
 */
public class ItemSemaforo  extends EntidadAbstracta implements Comparable<ItemSemaforo>{
    
    private String nombre;
    private int total, operarioId;
    private Date ultimaEntrega, desdePeriodo, hastaPeriodo;
    private boolean alertar;
    private ArrayList<ParteDiario> partes = new ArrayList<ParteDiario>();
    

    public int getOperarioId() {
        return operarioId;
    }

    public void setOperarioId(int operarioId) {
        this.operarioId = operarioId;
    }

    
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

    public ItemSemaforo(int operarioId, String nombre) {
        this.nombre = nombre;
        this.operarioId = operarioId;
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
        Calendar calen = Calendar.getInstance();
        calen.set(1990, 1, 1);
        ultimaEntrega = calen.getTime();
        this.total = 0;
        for(ParteDiario part: partes){
            this.total++;
            if(ultimaEntrega.compareTo(part.getFecha()) < 0){
                ultimaEntrega = part.getFecha();
            }
        }
        if (limite.compareTo(ultimaEntrega) > 0){
            alertar = true;
        }else{
            alertar = false;
            
        }
        if(ultimaEntrega.compareTo(calen.getTime()) == 0){
            ultimaEntrega = null;
            alertar = true;
        }
        return alertar;
    } 
    
   

    @Override
    public int compareTo(ItemSemaforo o) {
        if (this.alertar ==  o.alertar ){
            if(this.nombre.compareToIgnoreCase(o.nombre) < 0) {
                return -1;
            }else if (this.nombre.compareToIgnoreCase(o.nombre) > 0){
                return 1;
            }else{
                return 0;
            }
        } else if(this.alertar == true){
            return -1;
        } else if(o.alertar == true){
            return 1;
        } 
        return 0;
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {
        
    }
    
}
