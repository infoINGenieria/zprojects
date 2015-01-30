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
@Table(name="tipo_costo")
public class TipoCosto extends EntidadAbstracta {
    
    
    private int id; 
    private String nombre;
    private int tipo;
    //private List<Equipos> equipos;

    public static final int POSESION = 1;
    public static final int UTILIZACION = 2;
    
    public static final String POSESION_TXT = "Costo por posesión";
    public static final String UTILIZACION_TXT = "Costo por utilización";
    
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
        tipo = POSESION;
    }
    
    public static TipoCosto getTipoPosesion() {
        TipoCosto t = new TipoCosto();
        t.setId(POSESION);
        t.setTipo(POSESION);
        t.setNombre(POSESION_TXT);
        return t;
    }
    
    public static TipoCosto getTipoUtilizacion() {
        TipoCosto t = new TipoCosto();
        t.setId(UTILIZACION);
        t.setTipo(UTILIZACION);
        t.setNombre(UTILIZACION_TXT);
        return t;
    }

    @Override
    public boolean validate() {
        if (nombre == null) {
            error += "Nombre no puede dejarse vacio;";
        }
        if (tipo != POSESION && tipo != UTILIZACION) {
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
            case POSESION:
                return POSESION_TXT;
            case UTILIZACION:
                return UTILIZACION_TXT;
        }
        return null;
    }
    
    @Transient
    public static String[] getTipos() {
        return new String[] { POSESION_TXT, UTILIZACION_TXT };
    }

    public void setTipoByLabel(String str) {
        if (str.equals(POSESION_TXT)) {
            tipo = POSESION;
        } else if (str.equals(UTILIZACION_TXT)) {
            tipo = UTILIZACION;
        }
        
    }

    @OneToMany(mappedBy = "tipo")
    public List<PrecioHistorico> getPrecioHistoricos() {
        return precioHistoricos;
    }

    public void setPrecioHistoricos(List<PrecioHistorico> precioHistoricos) {
        this.precioHistoricos = precioHistoricos;
    }
    
    
}
