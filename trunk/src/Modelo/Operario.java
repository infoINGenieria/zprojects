/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author matuu
 */
public class Operario {
   
    int id, funcion;
    String n_legajo, nombre, cuil, observaciones, descripcion_vto1, descripcion_vto2,descripcion_vto3;
    boolean desarraigo;
    Date vto_carnet, vto_psicofisico, vto_cargagral, vto_cargapeligrosa, vto_otros1, vto_otros2, vto_otros3;

    public boolean isDesarraigo() {
        return desarraigo;
    }

    public void setDesarraigo(boolean desarraigo) {
        this.desarraigo = desarraigo;
    }

            
    public int getFuncion() {
        return funcion;
    }

    public void setFuncion(int funcion) {
        this.funcion = funcion;
    }

    
    public String getCuil() {
        return cuil;
    }

    public void setCuil(String CUIL) {
        this.cuil = CUIL;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String NOMBRE) {
        this.nombre = NOMBRE;
    }

    public String getN_legajo() {
        return n_legajo;
    }

    public void setN_legajo(String N_LEGAJO) {
        this.n_legajo = N_LEGAJO;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String OBSERVACIONES) {
        this.observaciones = OBSERVACIONES;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Operario(String NOMBRE) {
        this.nombre = NOMBRE;
    }
    public Operario(int id){
        this.id = id;
    }
    
    public Operario() { }
            
    @Override
    public String toString(){
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Operario other = (Operario) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        return hash;
    }

    public Date getVto_carnet() {
        return vto_carnet;
    }

    public void setVto_carnet(Date vto_carnet) {
        this.vto_carnet = vto_carnet;
    }

    public String getDescripcion_vto1() {
        return descripcion_vto1;
    }

    public void setDescripcion_vto1(String descripcion_vto1) {
        this.descripcion_vto1 = descripcion_vto1;
    }

    public String getDescripcion_vto2() {
        return descripcion_vto2;
    }

    public void setDescripcion_vto2(String descripcion_vto2) {
        this.descripcion_vto2 = descripcion_vto2;
    }

    public String getDescripcion_vto3() {
        return descripcion_vto3;
    }

    public void setDescripcion_vto3(String descripcion_vto3) {
        this.descripcion_vto3 = descripcion_vto3;
    }

    public Date getVto_cargagral() {
        return vto_cargagral;
    }

    public void setVto_cargagral(Date vto_cargagral) {
        this.vto_cargagral = vto_cargagral;
    }

    public Date getVto_cargapeligrosa() {
        return vto_cargapeligrosa;
    }

    public void setVto_cargapeligrosa(Date vto_cargapeligrosa) {
        this.vto_cargapeligrosa = vto_cargapeligrosa;
    }

    public Date getVto_otros1() {
        return vto_otros1;
    }

    public void setVto_otros1(Date vto_otros1) {
        this.vto_otros1 = vto_otros1;
    }

    public Date getVto_otros2() {
        return vto_otros2;
    }

    public void setVto_otros2(Date vto_otros2) {
        this.vto_otros2 = vto_otros2;
    }

    public Date getVto_otros3() {
        return vto_otros3;
    }

    public void setVto_otros3(Date vto_otros3) {
        this.vto_otros3 = vto_otros3;
    }

    public Date getVto_psicofisico() {
        return vto_psicofisico;
    }

    public void setVto_psicofisico(Date vto_psicofisico) {
        this.vto_psicofisico = vto_psicofisico;
    }
    
    
                     
}
