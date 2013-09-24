/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author matuu
 */
public class Obras {
    //ID 	CODIGO 	OBRA 	CONTRATO 	COMITENTE 	CUIT 	LUGAR 	PLAZO 	FECHA_INICIO
            int id;
            String codigo, obra, contrato, comitente, cuit, lugar, plazo, responsable;
            Date fecha_inicio;
            boolean tieneComida, tieneVianda, tieneDesarraigo, tieneRegistro, tieneEquipo;
            double limiteViandaDoble;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Obras other = (Obras) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.codigo == null) ? (other.codigo != null) : !this.codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id;
        hash = 29 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
        return hash;
    }

    public boolean isTieneEquipo() {
        return tieneEquipo;
    }

    public void setTieneEquipo(boolean tieneEquipo) {
        this.tieneEquipo = tieneEquipo;
    }

    public boolean isTieneRegistro() {
        return tieneRegistro;
    }

    public void setTieneRegistro(boolean tieneRegistro) {
        this.tieneRegistro = tieneRegistro;
    }
    
    

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public double getLimiteViandaDoble() {
        return limiteViandaDoble;
    }
    public Time getLimiteViandaDobleInTime(){
        String hora = (""+((int)limiteViandaDoble));
        hora = hora.substring(hora.length()-1);
        return Time.valueOf("0"+ hora +":00:00");
    }

    public void setLimiteViandaDoble(double limiteViandaDoble) {
        this.limiteViandaDoble = limiteViandaDoble;
    }

    public boolean isTieneComida() {
        return tieneComida;
    }

    public void setTieneComida(boolean tieneComida) {
        this.tieneComida = tieneComida;
    }

    public boolean isTieneDesarraigo() {
        return tieneDesarraigo;
    }

    public void setTieneDesarraigo(boolean tieneDesarraigo) {
        this.tieneDesarraigo = tieneDesarraigo;
    }

    public boolean isTieneVianda() {
        return tieneVianda;
    }

    public void setTieneVianda(boolean tieneVianda) {
        this.tieneVianda = tieneVianda;
    }
    
    

    public Obras(String codigo) {
        this.codigo = codigo;
        this.tieneComida = true;
        this.tieneDesarraigo = true;
        this.tieneVianda = true;
        this.limiteViandaDoble = 2;
    }
    public Obras(){
        this.tieneComida = true;
        this.tieneDesarraigo = true;
        this.tieneVianda = true;
        this.limiteViandaDoble = 2;
    }
    
   

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getComitente() {
        return comitente;
    }

    public void setComitente(String comitente) {
        this.comitente = comitente;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getObra() {
        return obra;
    }

    public void setObra(String obra) {
        this.obra = obra;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }
    
    
    @Override
    public String toString(){
        if(responsable == null || responsable.isEmpty()){
        return codigo;
        }else{
            return codigo + " ("+responsable+")";
        }
    }
            
    
}
