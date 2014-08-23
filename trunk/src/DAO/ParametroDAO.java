/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Parametro;
import Utils.ArrayParametro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author m4tuu
 */
public class ParametroDAO {
    public ParametroDAO() {}
    
    Connection conector;
    public void conectar() {
        try {
            conector = ConectorDB.getConector();
        } catch (Exception ex) {
        }
    }
    
    public int guardar(Parametro param) {
        int r = 0;
        String query = null;

        try {    
            query = "select * from PARAMETRO where clave = ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setString(1, param.getClave());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                query = "update parametro set CLAVE_GRUPO=?, VALOR=? where CLAVE=?";
            }else{
                query = "insert into parametro (CLAVE_GRUPO, VALOR, CLAVE) "
                        + "values(?, ?, ?)";
            }
            ps = conector.prepareStatement(query);          
            ps.setString(1, param.getClaveGrupo());
            ps.setString(2, param.getValor());
            ps.setString(3, param.getClave());
            
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                r = generatedKeys.getInt(1);
            }
            generatedKeys.close();
            ps.close();

        } catch (SQLException ex) {
            r = 0;
        }
        return r;
    }
    
    public Parametro FindById(String clave) {
        String query = null;
        
        try {
            query = "select * from parametro where CLAVE = ? limit 1";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            Parametro p = new Parametro();
            if (rs.next()) {
              p.setClave(rs.getString("CLAVE"));  
              p.setClaveGrupo(rs.getString("CLAVE_GRUPO"));
              p.setValor(rs.getString("VALOR"));
              
            }
            rs.close();
            ps.close();
            return p;
        } catch (SQLException ex) {
            System.out.print("Falló al cargar el parámetro: " +ex.getMessage()+ "\n");
            return null;
        }
    }
    
    public ArrayList<Parametro> FindByGrupo(String grupo) {
        String query = null;
        ArrayList<Parametro> parametros = new ArrayList<Parametro>();
        try {
            query = "select * from parametro where CLAVE_GRUPO = ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setString(1, grupo.toUpperCase());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              Parametro p = new Parametro();
              p.setClave(rs.getString("CLAVE"));  
              p.setClaveGrupo(rs.getString("CLAVE_GRUPO"));
              p.setValor(rs.getString("VALOR"));
              parametros.add(p);
            }
            rs.close();
            ps.close();
            
        } catch (SQLException ex) {
            System.out.print("Falló al cargar los parámetros: " +ex.getMessage()+ "\n");
            
        }
        return parametros;
    }
    
     public ArrayList<Parametro> CargarTodo() {
        String query = null;
        ArrayParametro parametros = new ArrayParametro();
        try {
            query = "select * from parametro";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              Parametro p = new Parametro();
              p.setClave(rs.getString("CLAVE"));  
              p.setClaveGrupo(rs.getString("CLAVE_GRUPO"));
              p.setValor(rs.getString("VALOR"));
              parametros.add(p);
            }
            rs.close();
            ps.close();
            
        } catch (SQLException ex) {
            System.out.print("Falló al cargar los parámetros: " +ex.getMessage()+ "\n");
            
        }
        return parametros;
    }
     
     public boolean Borrar(Parametro p) {
        boolean r = false;
        try {
            String query = "delete from parametro where CLAVE = ? limit 1";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setString(1, p.getClave());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                r=true;
            }
            ps.close();
            rs.close();
            r=true;
        } catch (SQLException ex) {
            r = false;
        }
        return r;
    }
}
