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
    String n_interno, equipos, marca, modelo,dominio, descripcion_vto1,
            descripcion_vto2, descripcion_vto3;
    double año;
    Date vto_vtv, vto_seguro, vto_otros1, vto_otros2, vto_otros3;
    
    public long getDateVtoVTVToDB(){
       try{
           vto_vtv.getTime();
           return vto_vtv.getTime();
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

    public Date getVto_vtv() {
        return vto_vtv;
    }

    public void setVto_vtv(Date vto_vt) {
        this.vto_vtv = vto_vt;
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

    
    
    
    
    
}
