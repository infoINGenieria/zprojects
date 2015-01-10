/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="familia_equipo")
public class FamiliaEquipo extends EntidadAbstracta {
    
    
    private int id; 
    private String nombre;
    //private List<Equipos> equipos;

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
    
//    @OneToMany(mappedBy = "equipos", fetch= FetchType.EAGER, cascade= CascadeType.ALL)
//    public List<Equipos> getEquipos() {
//        return equipos;
//    }
//
//    public void setEquipos(List<Equipos> items) {
//        this.equipos = items;
//    }

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
    
    
}
