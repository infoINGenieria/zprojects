/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author m4tuu
 */
@Entity
@Table(name="familia_equipo")
public class FamiliaEquipo extends EntidadAbstracta {
    
    
    private int id; 
    private String nombre;
    private double valorPosesion, valorUtilizacion;
    //private List<Equipos> equipos;
    private List<PrecioHistorico> precioHistoricos;

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Transient
    public double getValorPosesion() {
        return valorPosesion;
    }

    public void setValorPosesion(double valorPosesion) {
        this.valorPosesion = valorPosesion;
    }

    @Transient
    public double getValorUtilizacion() {
        return valorUtilizacion;
    }

    public void setValorUtilizacion(double valorUtilizacion) {
        this.valorUtilizacion = valorUtilizacion;
    }
    

    public FamiliaEquipo() {
    }

    public FamiliaEquipo(int id) {
        this.id = id;
    }
    @Override
    public boolean validate() {
        if (nombre == null) {
            error += "Nombre no puede dejarse vacio;";
        }
        return error.isEmpty();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FamiliaEquipo other = (FamiliaEquipo) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @OneToMany(mappedBy = "familia")
    public List<PrecioHistorico> getPrecioHistoricos() {
        return precioHistoricos;
    }

    public void setPrecioHistoricos(List<PrecioHistorico> precioHistoricos) {
        this.precioHistoricos = precioHistoricos;
    }
    
    
}
