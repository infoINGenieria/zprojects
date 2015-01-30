/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.EntidadAbstracta;

/**
 *
 * @author m4tuu
 */
public interface IBaseDAO {
    public void conectar();
    public int guardar(EntidadAbstracta entidad);
    public int modificar(EntidadAbstracta entidad);
    public boolean eliminar(EntidadAbstracta entidad);
}
