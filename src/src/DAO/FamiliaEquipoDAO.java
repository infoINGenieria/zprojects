/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.EntidadAbstracta;
import Modelo.FamiliaEquipo;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;

/**
 *
 * @author m4tuu
 */
public class FamiliaEquipoDAO extends AbstractHibernateDAO implements IAbstractDAO{
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

    @Override
    public ArrayList<EntidadAbstracta> cargarTodos() {
        List<FamiliaEquipo> list = getListaEntidades(FamiliaEquipo.class);
        ArrayList<EntidadAbstracta> returnList = new ArrayList<EntidadAbstracta>(list);
        return returnList;
    }

    @Override
    public ArrayList<EntidadAbstracta> filtrarPorTexto(String text) {
        AbstractHibernateDAO dummy = new AbstractHibernateDAO(){};

        ArrayList<FamiliaEquipo> listaResultado = null;

        try
        {
            dummy.iniciaOperacion();
            SQLQuery query = dummy.getSession().createSQLQuery(
                    "SELECT distinct f.* FROM familia_equipo f "
                    + "where f.nombre like '%" +text + "%' ").addEntity(FamiliaEquipo.class);
            listaResultado = (ArrayList<FamiliaEquipo>) query.list();        
        }
        catch (HibernateException he)
        {
            dummy.manejaExcepcion(he);
        }
        finally
        {
            dummy.terminaOperacion();
        }
        ArrayList<EntidadAbstracta> list1 = new ArrayList<EntidadAbstracta>(listaResultado);
        return list1;
    }

    @Override
    public int count(String name) {
        return (int) AbstractHibernateDAO.count(FamiliaEquipo.class);
    }
}
