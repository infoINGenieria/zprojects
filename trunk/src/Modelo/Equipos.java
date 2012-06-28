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
public class Equipos {
    
     int id;
    String n_interno, equipos, marca, modelo,dominio;
    double año;
    Date vto_vt, vto_seguro;
    
    public long getDateToDB(){
       try{
           vto_vt.getTime();
           return vto_vt.getTime();
       }catch (NullPointerException ex){
           return 0;
       }
    }

    public Date getVto_seguro() {
        return vto_seguro;
    }

    public void setVto_seguro(Date vto_seguro) {
        this.vto_seguro = vto_seguro;
    }

    public Date getVto_vt() {
        return vto_vt;
    }

    public void setVto_vt(Date vto_vt) {
        this.vto_vt = vto_vt;
    }

    
    public double getAño() {
        return año;
    }

    public void setAño(double año) {
        this.año = año;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getN_interno() {
        return n_interno;
    }

    public void setN_interno(String n_interno) {
        this.n_interno = n_interno;
    }

    public String getEquipos() {
        return equipos;
    }

    public void setEquipos(String equipos) {
        this.equipos = equipos;
    }

    public Equipos(String n_interno) {
        this.n_interno = n_interno;
    }
    
    public Equipos(){
        
    }
    @Override
    public String toString(){
        return n_interno + " "+equipos +" "+ marca + "(" + modelo + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Equipos other = (Equipos) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
        return hash;
    }
    
    
    
    
}
