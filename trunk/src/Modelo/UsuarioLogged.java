/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


/**
 *
 * @author matuuar
 */

public class UsuarioLogged extends EntidadAbstracta{
    private static UsuarioLogged INSTANCE = null;
    private static int id_user;
    private static String user;
    private static String pass;
    private static String rol;
    private static String error="";


    private UsuarioLogged() {
    }

    private synchronized static void inicia(){
        if (INSTANCE ==null){
            if (user ==null){
                throw new IllegalArgumentException("Antes de llamar a este método se debe llamar setUsuario");
            }
            INSTANCE = new UsuarioLogged();
          }
    }

    private synchronized static void cierra(){
        INSTANCE=null;
    }
    public static void setId_user(int id_user) {
        inicia();
        UsuarioLogged.id_user = id_user;
    }

    public static void setPass(String pass) {
        inicia();
       
        UsuarioLogged.pass = pass;
    }

    public static void setRol(String rol) {

        inicia();
        UsuarioLogged.rol = rol;
    }

    public static void setUser(String user) {
        cierra();
        UsuarioLogged.user = user;
        inicia();
    }

    public static int getId_user() {
        inicia();
        return id_user;
    }

    public static String getPass() {
        inicia();
        return pass;
    }

    public static String getRol() {
        inicia();
        return rol;
    }

    public static String getUser() {
        inicia();
        return user;
    }

    public static String getError() {
        return error;
    }

    public static void setError(String error) {
        UsuarioLogged.error = error;
    }

    


    
  
    
    

}