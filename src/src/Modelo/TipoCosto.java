/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author m4tuu
 */
@Entity
@Table(name="tipo_costo")
public class TipoCosto extends EntidadAbstracta {
    
    
    private int id; 
    private String nombre;
    private int tipo;
    //private List<Equipos> equipos;

    public static final int POR_DIA = 1;
    public static final int POR_PARTE = 2;
    
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    
//    @OneToMany(mappedBy = "equipos", fetch= FetchType.EAGER, cascade= CascadeType.ALL)
//    public List<Equipos> getEquipos() {
//        return equipos;
//    }
//
//    public void setEquipos(List<Equipos> items) {
//        this.equipos = items;
//    }

    public TipoCosto() {
        tipo = POR_DIA;
    }

    @Override
    public boolean validate() {
        if (nombre == null) {
            error += "Nombre no puede dejarse vacio;";
        }
        if (tipo != POR_DIA && tipo != POR_PARTE) {
            error += "El tipo no es válido;";
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
        final TipoCosto other = (TipoCosto) obj;
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
    
    @Transient
    public String getTipoLabel() {
        switch(tipo){
            case POR_DIA:
                return "Por día";
            case POR_PARTE:
                return "Por parte diario";
        }
        return null;
    }
    
    @Transient
    public static String[] getTipos() {
        return new String[] { "Por día", "Por parte diario" };
    }

    public void setTipoByLabel(String str) {
        if (str.equals("Por día")) {
            tipo = POR_DIA;
        } else if (str.equals("Por parte diario")) {
            tipo = POR_PARTE;
        }
        
    }
    
    
}
