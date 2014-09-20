/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.EntidadAbstracta;
import Modelo.Usuario;
import Modelo.UsuarioLogged;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author matuuar
 */
public class UsuarioDAO extends AbstractDAO  { 
     
    public UsuarioDAO(){  
}

    
    public int login() {
        
        String query;
        System.out.println("Verificando credenciales");
        try {
            query = "select * from usuario where USER = ? and PASS = MD5(?) and fechabaja is null ";
            PreparedStatement ps = conector.prepareStatement(query);

            ps.setString(1, UsuarioLogged.getUser());
            ps.setString(2, UsuarioLogged.getPass());
            
            ResultSet executeQuery = ps.executeQuery();
            executeQuery.next();

            if (executeQuery.getInt("ID") > 0) {
                UsuarioLogged.setId_user(executeQuery.getInt("ID"));
                UsuarioLogged.setUser(executeQuery.getString("USER"));
                UsuarioLogged.setRol(executeQuery.getString("ROL"));
                UsuarioLogged.setPass(executeQuery.getString("PASS"));
            }
            executeQuery.close();
            ps.close();
            System.out.println("Usuario conectado: "+UsuarioLogged.getUser());
            return UsuarioLogged.getId_user();
        } catch (SQLException ex) {
            return UsuarioLogged.getId_user();
        }
    }
    
    @Override
    public int guardar(EntidadAbstracta entity) {
        int r = 0;
        String query = null;
        Usuario user = (Usuario) entity;
        try {
            query = "SELECT id FROM usuario WHERE user = ? and fechabaja is null";
            PreparedStatement ps1 = conector.prepareStatement(query);
            ps1.setString(1, user.getUser());
           
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                ps1.close();
                rs.close();
                return -1;
            } else {
                query = "insert into usuario (USER, PASS, ROL) values (?, md5(?), ?)";
                PreparedStatement ps = conector.prepareStatement(query);
                ps.setString(1, user.getUser());
                ps.setString(2, user.getPass());
                ps.setString(3, user.getRol());
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
    
    public int modificar(Usuario user) {

        int r = 0;
        try {
            String query = "";
            if(user.getPass()==null || user.getPass().isEmpty())
                    query = "update usuario set USER=?, ROL=? where ID =" + user.getId();
                else
                    query = "update usuario set USER=?, ROL=?, PASS=md5(?) where ID =" + user.getId();
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setString(1, user.getUser());
            ps.setString(2, user.getRol());
            if (!(user.getPass()==null || user.getPass().isEmpty())) ps.setString(3, user.getPass());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            
            r = user.getId();
            
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            r = 0;
        }

        return r ;
    }

    public ArrayList<String> cargarUserString(){
        String query=null;
        ArrayList<String> nombres= new ArrayList<String>();
        try{
            query="select user from usuario where fechabaja is null";
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

    public ArrayList<Usuario> filtrarUsuariosTabla(String query) {
        
        ArrayList<Usuario> usuarios= new ArrayList<Usuario>();
        try{
            query="select * from usuario where fechabaja is null and (user like '%" + query + "%' or"
                    + " rol like '%" + query + "%')";
            PreparedStatement ps=conector.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Usuario user = new Usuario();
                user.setId(rs.getInt("ID"));
                user.setUser(rs.getString("USER"));
                user.setRol(rs.getString("ROL"));
                usuarios.add(user);
            }

         rs.close();
         ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los usuarios.\n");
        }
        return usuarios;
    }
    
    public ArrayList<Usuario> cargarUser(){
        String query=null;
        ArrayList<Usuario> usuarios= new ArrayList<Usuario>();
        try{
            query="select * from usuario where fechabaja is null";
            PreparedStatement ps=conector.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Usuario user = new Usuario();
                user.setId(rs.getInt("ID"));
                user.setUser(rs.getString("USER"));
                user.setRol(rs.getString("ROL"));
                usuarios.add(user);
            }

         rs.close();
         ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los usuarios.\n");
        }
        return usuarios;

    }

    public boolean darDeBaja(Usuario usuarioSeleccionado) {
        try{
            PreparedStatement ps = conector.prepareStatement("update usuario set fechabaja = NOW() where id = ?");
            ps.setInt(1, usuarioSeleccionado.getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
               
            }
            ps.close();
            rs.close();
            
        }catch(Exception ex){
            return false;
        }
        return true;
    }

    @Override
    public int modificar(EntidadAbstracta entidad) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean eliminar(EntidadAbstracta entidad) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<EntidadAbstracta> cargarTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<EntidadAbstracta> filtrarPorTexto(String text) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
    
    