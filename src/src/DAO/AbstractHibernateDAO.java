/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.EntidadAbstracta;
import Utils.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author m4tuu
 */
public class AbstractHibernateDAO {
    
    private Session sesion;

    protected void iniciaOperacion()
    {
        sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.getTransaction().begin();
    }

    protected void terminaOperacion()
    {
        sesion.getTransaction().commit();
        sesion.close();
    }

    protected void manejaExcepcion(HibernateException he) throws HibernateException
    {
        sesion.getTransaction().rollback();
        throw he;
    }

    protected Session getSession()
    {
        return sesion;
    }

    public static boolean almacenaEntidad(EntidadAbstracta entidad) throws HibernateException
    {
        if(!entidad.validate()){
            return false;
        }
        AbstractHibernateDAO dummy = new AbstractHibernateDAO(){};
        
        try
        {
            dummy.iniciaOperacion();
            dummy.getSession().saveOrUpdate(entidad);
            dummy.getSession().flush();
        }
        catch (HibernateException he)
        {
            dummy.manejaExcepcion(he);
        }
        finally
        {
            dummy.terminaOperacion();
        }
        return true;
    }

    public static boolean eliminarEntidad(EntidadAbstracta entidad) throws HibernateException
    {

        AbstractHibernateDAO dummy = new AbstractHibernateDAO(){};

        try
        {
            dummy.iniciaOperacion();
            dummy.getSession().delete(entidad);
            dummy.getSession().flush();
        }
        catch (HibernateException he)
        {
            dummy.manejaExcepcion(he);
        }
        finally
        {
            dummy.terminaOperacion();
        }
        return true;
    }

    public static <T> T getEntidad(Serializable id, Class<T> claseEntidad) throws HibernateException
    {
        AbstractHibernateDAO dummy = new AbstractHibernateDAO(){};

        T objetoRecuperado = null;

        try
        {
            dummy.iniciaOperacion();
            objetoRecuperado = (T) dummy.getSession().get(claseEntidad, id);
        }
        catch (HibernateException he)
        {
            dummy.manejaExcepcion(he);
        }
        finally
        {
            dummy.terminaOperacion();
        }

        return objetoRecuperado;
    }
    
    public static <T> T getEntidad(Class<T> claseEntidad, String queryEnHQL) throws HibernateException
    {
        AbstractHibernateDAO dummy = new AbstractHibernateDAO(){};

        T objetoRecuperado = null;

        try
        {
            dummy.iniciaOperacion();
            objetoRecuperado = (T) dummy.getSession().createQuery(queryEnHQL).uniqueResult();
        }
        catch (HibernateException he)
        {
            dummy.manejaExcepcion(he);
        }
        finally
        {
            dummy.terminaOperacion();
        }

        return objetoRecuperado;
    }

    public static <T> List<T> getListaEntidades(Class<T> claseEntidad) throws HibernateException
    {
        AbstractHibernateDAO dummy = new AbstractHibernateDAO(){};

        List<T> listaResultado = null;

        try
        {
            dummy.iniciaOperacion();
            listaResultado = dummy.getSession().createQuery("FROM " + claseEntidad.getSimpleName()).list();
        }
        catch (HibernateException he)
        {
            dummy.manejaExcepcion(he);
        }
        finally
        {
            dummy.terminaOperacion();
        }

        return listaResultado;
    }

    public static <T> List<T> getListaEntidadesFiltrada(Class<T> claseEntidad, String queryEnHQL) throws HibernateException
    {
        AbstractHibernateDAO dummy = new AbstractHibernateDAO(){};

        List<T> listaResultado = null;

        try
        {
            dummy.iniciaOperacion();
            listaResultado = dummy.getSession().createQuery(queryEnHQL).list();
        }
        catch (HibernateException he)
        {
            dummy.manejaExcepcion(he);
        }
        finally
        {
            dummy.terminaOperacion();
        }

        return listaResultado;
    }

    public static <T> long count(Class<T> claseEntidad) throws HibernateException
    {
        AbstractHibernateDAO dummy = new AbstractHibernateDAO(){};

       long count=0;

        try
        {
            dummy.iniciaOperacion();
            Query q = dummy.getSession().createQuery("select count(entity) from "+claseEntidad.getSimpleName()+ " entity");
            count = (Long) q.uniqueResult();
        }
        catch (HibernateException he)
        {
            dummy.manejaExcepcion(he);
        }
        finally
        {
            dummy.terminaOperacion();
        }

        return count;
    }
}
