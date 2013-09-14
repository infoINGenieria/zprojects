/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.sql.Time;

/**
 *
 * @author matu
 */
public class Perfiles {

     int id;
     String nombre;
     Time sabEsp_i, sabEsp_f, domEsp_i, domEsp_f;
     Time feriado_i, feriado_f, hs_normal;
     Obras obra;

    public Perfiles() {
        id=0;
        nombre="standard";
        sabEsp_f=Time.valueOf("23:59:59");
        sabEsp_i=Time.valueOf("13:00:00");
        domEsp_i=Time.valueOf("00:00:00");
        domEsp_f=Time.valueOf("23:59:59");
        feriado_i=Time.valueOf("00:00:00");
        feriado_f=Time.valueOf("23:59:59");
        hs_normal=Time.valueOf("08:00:00");
            
    }

    public  Obras getObra() {
        return obra;
    }

    public  void setObra(Obras obra) {
        this.obra = obra;
    }

    public Perfiles(int id) {
        this.id = id;
    }

    public Time getHs_normal() {
        return hs_normal;
    }

    public void setHs_normal(Time hs_normal) {
        this.hs_normal = hs_normal;
    }
    
    
    public Time getDomEsp_f() {
        return domEsp_f;
    }

    public void setDomEsp_f(Time domEsp_f) {
        this.domEsp_f = domEsp_f;
    }

    public Time getDomEsp_i() {
        return domEsp_i;
    }

    public void setDomEsp_i(Time domEsp_i) {
        this.domEsp_i = domEsp_i;
    }

    public Time getFeriado_f() {
        return feriado_f;
    }

    public void setFeriado_f(Time feriado_f) {
        this.feriado_f = feriado_f;
    }

    public Time getFeriado_i() {
        return feriado_i;
    }

    public void setFeriado_i(Time feriado_i) {
        this.feriado_i = feriado_i;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Time getSabEsp_f() {
        return sabEsp_f;
    }

    public void setSabEsp_f(Time sabEsp_f) {
        this.sabEsp_f = sabEsp_f;
    }

    public Time getSabEsp_i() {
        return sabEsp_i;
    }

    public void setSabEsp_i(Time sabEsp_i) {
        this.sabEsp_i = sabEsp_i;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Perfiles other = (Perfiles) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id;
        return hash;
    }

    @Override
    public String toString(){
        return nombre;
    }


}
