/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import DAO.Conexion.Conexion;
import DAO.Conexion.LeerXML;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author m4tuu
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static final LeerXML configDB = new LeerXML();
    
    static {
        ReloadConfig();
    }
    
    public static void ReloadConfig() {
        try {
            AnnotationConfiguration config = new AnnotationConfiguration();
            Conexion conn = configDB.config();
             /*
            <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/zille2</property>
            <property name="hibernate.connection.username">root</property>
            <property name="hibernate.connection.password">infomati</property>*/
            config.setProperty("hibernate.connection.url", 
                    "jdbc:mysql://" + conn.getHost() + ":3306/" + conn.getDbname());
            config.setProperty("hibernate.connection.username", conn.getDbuser());
            config.setProperty("hibernate.connection.password", conn.getDbpass());
            System.out.println("Reiniciando configuración hibernate");
            sessionFactory = config.configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
