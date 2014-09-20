/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author m4tuu
 */
public class EPP extends EntidadAbstracta {
    private int id;
    private String nombre, medida;

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
        this.medida = "";}

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
