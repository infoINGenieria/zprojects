/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author m4tuu
 */
@Entity
@Table(name="epp")
public class EPP extends EntidadAbstracta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String nombre;
    private String medida;
    
    @OneToMany(mappedBy = "epp", cascade= CascadeType.ALL)
    private List<EppEntregaItem> eppEntregaItems;

    public List<EppEntregaItem> getEppEntregaItems() {
        return eppEntregaItems;
    }

    public void setEppEntregaItems(List<EppEntregaItem> eppEntregaItems) {
        this.eppEntregaItems = eppEntregaItems;
    }

    
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EPP(String nombre, String medida) {
        this.nombre = nombre;
        this.medida = medida;
    }
    
    public EPP(){
        this.nombre = "";
        this.medida = "";
    }

    public EPP(int id) {
        this.id = id;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EPP other = (EPP) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        return nombre +"[" + medida +"]";
    }
    
    public boolean tieneTalle(){
        return medida.toUpperCase().equals("TALLE");
    }

    @Override
    public boolean validate() {
        boolean r = true;
        if (this.medida.isEmpty()) {
            error += "El campo Medida es necesario;";
            r = false;
        }
        if (this.nombre.isEmpty()) {
            error += "El campo Nombre es necesario;";
            r = false;
        }
        return r;
    }
}
