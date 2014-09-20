/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author matuu
 */
public class Funcion extends EntidadAbstracta{
    
    int id;
    String funcion;

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Funcion(String funcion) {
        this.funcion = funcion;
    }
    public Funcion() {
   }
    public Funcion(int i) {
        id=i;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Funcion other = (Funcion) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        return hash;
    }
    
    @Override
    public String toString(){
        return funcion;
    }

    @Override
    public boolean validate() {
        if (funcion.isEmpty()) {
            error += "El nombre de la función es obligatorio;";
            return false;
        }
        return true;
    }
    
    
}
