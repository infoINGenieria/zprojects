/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author m4tuu
 */
public interface IEntidadAbstracta {
    /**
     * Método que valida el modelo.
     * Obligatorio en todas las entidades.
     * @return Verdadero si el modelo es válido
     */
    public boolean validate();
}
