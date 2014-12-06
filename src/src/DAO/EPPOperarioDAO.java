/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.EPPOperario;
import Modelo.Operario;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author m4tuu
 */
public class EPPOperarioDAO extends AbstractHibernateDAO {
    public ArrayList<EPPOperario> getTodos(Operario operario) throws HibernateException
    {
        AbstractHibernateDAO dummy = new AbstractHibernateDAO(){};
        ArrayList<EPPOperario> eppOperario = new ArrayList<EPPOperario>();
        try
        {
            dummy.iniciaOperacion();
            Criteria filtrado = dummy.getSession().createCriteria(EPPOperario.class);
            filtrado.add(Restrictions.eq("operario", operario));

            eppOperario = (ArrayList<EPPOperario>)filtrado.list();
        }
        catch (HibernateException he)
        {
            dummy.manejaExcepcion(he);
        }
        finally
        {
            dummy.terminaOperacion();
        }
        return eppOperario;
    }
}
