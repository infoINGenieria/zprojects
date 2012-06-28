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

    static int id;
    static String nombre;
    static Time sabEsp_i, sabEsp_f, domEsp_i, domEsp_f;
    static Time feriado_i, feriado_f, hs_normal;

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


    public Perfiles(int id) {
        Perfiles.id = id;
    }

   
    

    public Time getHs_normal() {
        return hs_normal;
    }

    public void setHs_normal(Time hs_normal) {
        Perfiles.hs_normal = hs_normal;
    }
    
    
    public Time getDomEsp_f() {
        return domEsp_f;
    }

    public void setDomEsp_f(Time domEsp_f) {
        Perfiles.domEsp_f = domEsp_f;
    }

    public Time getDomEsp_i() {
        return domEsp_i;
    }

    public void setDomEsp_i(Time domEsp_i) {
        Perfiles.domEsp_i = domEsp_i;
    }

    public Time getFeriado_f() {
        return feriado_f;
    }

    public void setFeriado_f(Time feriado_f) {
        Perfiles.feriado_f = feriado_f;
    }

    public Time getFeriado_i() {
        return feriado_i;
    }

    public void setFeriado_i(Time feriado_i) {
        Perfiles.feriado_i = feriado_i;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        Perfiles.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        Perfiles.nombre = nombre;
    }

    public Time getSabEsp_f() {
        return sabEsp_f;
    }

    public void setSabEsp_f(Time sabEsp_f) {
        Perfiles.sabEsp_f = sabEsp_f;
    }

    public Time getSabEsp_i() {
        return sabEsp_i;
    }

    public void setSabEsp_i(Time sabEsp_i) {
        Perfiles.sabEsp_i = sabEsp_i;
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
        if (Perfiles.id != Perfiles.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Perfiles.id;
        return hash;
    }

    

    

    @Override
    public String toString(){
        return nombre;
    }


}
