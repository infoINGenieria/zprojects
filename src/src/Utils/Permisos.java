/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Modelo.UsuarioLogged;
import Vista.OpcionPanel;

/**
 *
 * @author m4tuu
 */
public class Permisos {
    public static boolean verificarCredenciales(String rol) {
        if (zilleprojects.ZilleProjectsView.inicio) {
            if (Permisos.verificarRol(rol)) {
                return true;
            }else{
                sinPermisos();
            }
        } else{
            notLogin();
        }
        return false;
    }
    
    public static boolean verificarRol(String roles) {
        for(String rol: roles.split(",")){
            if(UsuarioLogged.getRol().toUpperCase().equals(rol.trim().toUpperCase()) ||
                    UsuarioLogged.getRol().toUpperCase().equals("ADMINISTRADOR")){
                return true;
            }
        }
        
        return false;
    }

     public static void notLogin() {
            OpcionPanel.showConfirmDialog(null, "Debe iniciar una sesión e \nidentificarse en el sistema.", 
                    "Usuario no identificado", -1, OpcionPanel.ERROR_MESSAGE);
     }
     private static void sinPermisos() {
            OpcionPanel.showConfirmDialog(null, "No tiene permisos para realizar \nesta acción.", 
                    "Usuario no autorizado", -1, OpcionPanel.ERROR_MESSAGE);
     }
}
