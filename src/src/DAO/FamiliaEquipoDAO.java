/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.EntidadAbstracta;
import Modelo.FamiliaEquipo;
import Modelo.FamiliaEquipoBean;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;

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
//        List<FamiliaEquipo> list = getListaEntidades(FamiliaEquipo.class);
        ArrayList<EntidadAbstracta> returnList = filtrarPorTexto("");
        return returnList;
    }

    @Override
    public ArrayList<EntidadAbstracta> filtrarPorTexto(String text) {
        AbstractHibernateDAO dummy = new AbstractHibernateDAO(){};

        ArrayList<FamiliaEquipo> listaResultado = new ArrayList<FamiliaEquipo>();

        try
        {
            dummy.iniciaOperacion();
            Query query = dummy.getSession().createSQLQuery(
                    "SELECT distinct f.* , " +
                    "(select valor from precio_historico "
                    + "where familia_id = f.id and tipo_id = 1 and fechaBaja is null "
                    + "order by id desc limit 1) as valorPosesion, "
                    + "(select valor from precio_historico "
                    + "where familia_id = f.id and tipo_id = 2 and fechaBaja is null "
                    + "order by id desc limit 1) as valorUtilizacion FROM familia_equipo f "
                    + "left join precio_historico ph on ph.familia_id = f.id "
                    + "where f.nombre like '%"+ text +"%' ").setResultTransformer(new AliasToBeanResultTransformer(FamiliaEquipoBean.class));
            
            for(FamiliaEquipoBean a: (List<FamiliaEquipoBean>)query.list()) {
                listaResultado.add(a.convert2FamiliaEquipo());
            }
                 
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
