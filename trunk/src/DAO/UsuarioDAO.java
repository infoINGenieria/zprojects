/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author matuuar
 */
public class UsuarioDAO {
     Connection conector;
    
    public UsuarioDAO(){  
}
    public void conectar() {
        try {
            conector=ConectorDB.getConector();
        }catch (Exception ex){}
    }
    
    public int login() {
        String query;
        System.out.println("Verificando credenciales");
        try {
            query = "select * from usuario where USER = ? and PASS = MD5(?) ";
            PreparedStatement ps = conector.prepareStatement(query);

            ps.setString(1, Usuario.getUser());
            ps.setString(2, Usuario.getPass());
            
            ResultSet executeQuery = ps.executeQuery();
            executeQuery.next();

            if (executeQuery.getInt("ID") > 0) {
               Usuario.setId_user(executeQuery.getInt("ID"));
                Usuario.setUser(executeQuery.getString("USER"));
                Usuario.setRol(executeQuery.getString("ROL"));
                Usuario.setPass(executeQuery.getString("PASS"));
            }
            executeQuery.close();
            ps.close();
            System.out.println("Usuario conectado: "+Usuario.getUser());
            return Usuario.getId_user();
        } catch (SQLException ex) {
            return Usuario.getId_user();
        }
    }

    public int guardar(String user, String pass, String rol) {
        int r = 0;
        String query = null;

        try {
            query = "SELECT id FROM usuario WHERE user = ? and pass = md5(?)";
            PreparedStatement ps1 = conector.prepareStatement(query);
            ps1.setString(1, user);
            ps1.setString(2, pass);
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                ps1.close();
                rs.close();
                return -1;
            } else {
                query = "insert into usuario (USER, PASS, ROL) values (?, md5(?), ?)";
                PreparedStatement ps = conector.prepareStatement(query);
                ps.setString(1, user);
                ps.setString(2, pass);
                ps.setString(3, rol);
                ps.executeUpdate();
                ResultSet generatedKeys = ps.getGeneratedKeys();

                if (generatedKeys.next()) {
                    r = generatedKeys.getInt(1);
                }
                generatedKeys.close();
                ps.close();
            }
        } catch (SQLException ex) {
            r = 0;
        }
        return r;
    }

    public int modificar(String user, String passold, String pass, String rol) {

        int r = 0;
        try {
            String query = "SELECT id FROM usuario WHERE user = ? and pass = md5(?)";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, passold);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                r = rs.getInt("ID_USUARIO");
                query = "update usuario set PASS=md5(?), ROL=? where ID =" + r;
                ps = conector.prepareStatement(query);
                ps.setString(1, pass);
                ps.setString(2, rol);
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    r = rs.getInt(1);
                }

            } else{  //si la clave no corresponde al usuario
                r=-1;
            }
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            r = 0;
        }

    return r ;
}

    public ArrayList<String> cargarUser(){
        String query=null;
        ArrayList<String> nombres= new ArrayList<String>();
        try{
            query="select user from usuario";
            PreparedStatement ps=conector.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                String user=rs.getString("USER");
                nombres.add(user);
            }

         rs.close();
         ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los usuarios.\n");
        }
        return nombres;

    }
    
}
