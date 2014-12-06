/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Combustible;
import Utils.FechaUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Matu
 */
public class CombustibleDAO {
    
    public CombustibleDAO() {}
    
    Connection conector;
    public void conectar() {
        try {
            conector = ConectorDB.getConector();
        } catch (Exception ex) {
        }
    }
    
    public int guardar(Combustible combustible) {
        int r = 0;
        String query = null;

        try {
            
            query = "insert into combustible (ESTACIONID, FECHA, CANTIDAD, "
                    + " RESPONSABLE) "
                    + "values ( ?, ?, ?, ?)";
            PreparedStatement ps = conector.prepareStatement(query);
            
            ps.setInt(1, combustible.getEstacionID());
            ps.setDate(2, FechaUtil.getFechatoDB(combustible.getFecha()));
            ps.setDouble(3, combustible.getCantidad());
            ps.setString(4, combustible.getResponsable());
           
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
    
    public int modificar(Combustible combustible) {
        int r = 0;
        String query = null;

        try {
            
            query = "update combustible set ESTACIONID =?, FECHA=?, CANTIDAD=?, RESPONSABLE=?"
                    + " where COMBUSTIBLEID =" +combustible.getCombustibleID();
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, combustible.getEstacionID());
            ps.setDate(2, FechaUtil.getFechatoDB(combustible.getFecha()));
            ps.setDouble(3, combustible.getCantidad());
            
            ps.setString(4, combustible.getResponsable());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                r = generatedKeys.getInt(1);
            }else{
                r= combustible.getCombustibleID();
                
            }
            generatedKeys.close();
            ps.close();

        } catch (SQLException ex) {
            r = 0;
        }
        return r;
    }
    
    public boolean borrar(Combustible combustible) {

        boolean r =false;
        try {
            
            String query = "delete from combustible where COMBUSTIBLEID = ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, combustible.getCombustibleID());
            
            
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
    
    public ArrayList<Combustible> cargarTodos() {
        String query = null;
        ArrayList<Combustible> cargas = new ArrayList<Combustible>();
        try {
            query = "select * from combustible";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                Combustible combustible = new Combustible();
                combustible.setCombustibleID(rs.getInt("COMBUSTIBLEID"));
                combustible.setEstacionID(rs.getInt("ESTACIONID"));
                combustible.setFecha(rs.getDate("FECHA"));
                combustible.setCantidad(rs.getDouble("CANTIDAD"));
                combustible.setResponsable(rs.getString("RESPONSABLE"));
                cargas.add(combustible);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar las cargas de combustible: " +ex.getMessage()+ "\n");
        }
        return cargas;

    } 
   public ArrayList<Combustible> findByEstacion(int estacionID) {
        String query = null;
        ArrayList<Combustible> cargas = new ArrayList<Combustible>();
        try {
            query = "select * from combustible where ESTACIONID = "+estacionID;
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 
                Combustible combustible = new Combustible();
                combustible.setCombustibleID(rs.getInt("COMBUSTIBLEID"));
                combustible.setEstacionID(rs.getInt("ESTACIONID"));
                combustible.setFecha(rs.getDate("FECHA"));
                combustible.setCantidad(rs.getDouble("CANTIDAD"));
                combustible.setResponsable(rs.getString("RESPONSABLE"));
                cargas.add(combustible);
                
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar las cargas de combustible: " +ex.getMessage()+ "\n");
        }
        return cargas;

    } 
   public ArrayList<Combustible> findById(int estacionID) {
        String query = null;
        ArrayList<Combustible> cargas = new ArrayList<Combustible>();
        try {
            query = "select * from combustible where COMBUSTIBLEID = "+estacionID;
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 
                Combustible combustible = new Combustible();
                combustible.setCombustibleID(rs.getInt("COMBUSTIBLEID"));
                combustible.setEstacionID(rs.getInt("ESTACIONID"));
                combustible.setFecha(rs.getDate("FECHA"));
                combustible.setCantidad(rs.getDouble("CANTIDAD"));
                combustible.setResponsable(rs.getString("RESPONSABLE"));
                cargas.add(combustible);
                
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar las cargas de combustible: " +ex.getMessage()+ "\n");
        }
        return cargas;

    } 
    
}
