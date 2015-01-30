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
public class DefaultHibernateDAO extends AbstractHibernateDAO implements IBaseDAO {

    @Override
    public void conectar() {
        
    }

    @Override
    public int guardar(EntidadAbstracta entidad) {
        if (almacenaEntidad(entidad)) {       
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public int modificar(EntidadAbstracta entidad) {
        return guardar(entidad);
    }

    @Override
    public boolean eliminar(EntidadAbstracta entidad) {
        return eliminarEntidad(entidad);
    }
}
