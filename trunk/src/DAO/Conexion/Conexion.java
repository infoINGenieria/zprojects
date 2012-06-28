/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matuuar
 */
public class Conexion {

     static String host = "localhost";
     static String dbname = "zilleprojects";
     static String dbuser = "zille";
     static String dbpass = "InfoIngenieria";
     static boolean where= false;
     static Connection conexion = null;
     static int result=0;
    /** Nomenclatura 'Result'
     * 0: No conectado.
     * 1: Conectado.
     * 2: Archivo accesible. Datos incorrectos.
     * 3: Archivo inaccesible.
      * @throws SQLException 
      */


    public void commit() throws SQLException{
    if(Conexion.conexion !=null){
        Conexion.conexion.commit();
        }
    }

    public void roolback() throws SQLException{
     if(Conexion.conexion !=null){
        Conexion.conexion.rollback();
        }
    }

    public static Connection getConexion() {
        if(dbname==null || dbname.equals("none")){
            if(result!=3){
                result=2;
            }
        }else if (conexion == null) {

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conexion = DriverManager.getConnection("jdbc:mysql://" + host + "/" + dbname, dbuser, dbpass);
                result=1;
                

            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                result=2;
            } catch (InstantiationException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                result=2;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        //System.out.print(host + " "+dbname+" "+ dbuser+" "+dbpass);
        return conexion;
    }

    public static void setConexion(Connection conexion) {
        Conexion.conexion = conexion;
    }
    

    public  int getResult() {
        return result;
    }

    public void setResult(int result) {
        Conexion.result = result;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        Conexion.dbname = dbname;
    }

    public String getDbpass() {
        return dbpass;
    }

    public void setDbpass(String dbpass) {
        Conexion.dbpass = dbpass;
    }

    public String getDbuser() {
        return dbuser;
    }

    public void setDbuser(String dbuser) {
        Conexion.dbuser = dbuser;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        Conexion.host = host;
    }

    public boolean isWhere() {
        return where;
    }

    public void setWhere(boolean where) {
        Conexion.where = where;
    }



}
