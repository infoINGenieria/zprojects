/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.Conexion.Conexion;
import DAO.Conexion.LeerXML;
import java.sql.Connection;
import org.jdom2.JDOMException;

/**
 *
 * @author matuuar
 */
public class ConectorDB {

    static Connection conector;

    public ConectorDB() {
    }

    static public Connection getConector() {
        try {
            if (conector == null) {
                LeerXML connectionData = new LeerXML();
                connectionData.config();
                conector = Conexion.getConexion();
                System.out.println("Conectando a la Base de Datos");
            }
        } catch (Exception ex) {
        }
    
    
    return conector ;
}
}
