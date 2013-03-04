/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.RiItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author matuuar
 */
public class RIItemDAO {
    Connection conector;
    
    public RIItemDAO(){}
    
    public void conectar() {
        try {
            conector = ConectorDB.getConector();
        } catch (Exception ex) {
        }
    }
    
    public void conectar(Connection conn){
        try{
            conector = conn;
        }catch (Exception ex) {}
    }
    
    public int guardar(RiItem it) {
        int r = 0;
        String query = null;

        try {
            /*`riItemId , riId, cantidad, unidad, detalle, observacion
            */
            query = "insert into ri_item (riId, cantidad, unidad,"
                    + " detalle, observacion) values ( ?, ?, ?, ?, ?)";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, it.getRiId());
            ps.setString(2, it.getCantidad());
            ps.setString(3, it.getUnidad());
            ps.setString(4, it.getDetalle());
            ps.setString(5, it.getObservacion());
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

    public int modificar(RiItem it) {

        int r = 0;
        try {
             /*`riItemId , riId, cantidad, unidad, detalle, observacion
            */
            String query = "update ri_item set riId=?, cantidad=?, unidad=?,"
                    + " detalle=?, observacion=? where riItemId = ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, it.getRiId());
            ps.setString(2, it.getCantidad());
            ps.setString(3, it.getUnidad());
            ps.setString(4, it.getDetalle());
            ps.setString(5, it.getObservacion());
            ps.setInt(6, it.getRiItemId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                r = rs.getInt(1);
            }
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            r = 0;
        }

        return r;
    }
    /*`N_INTERNO` `VEHICULO`  `MARCA`   `MODELO`  `AÑO`  `DOMINIO` 
    */

    public ArrayList<RiItem> cargarTodos(int riId) {
        String query = null;
        ArrayList<RiItem> riItems = new ArrayList<RiItem>();
        try {
            query = "select * from ri_item where riId =" +riId;
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RiItem it = new RiItem();
                it.setRiItemId(rs.getInt("riItemId"));
                it.setRiId(rs.getInt("riId"));
                it.setCantidad(rs.getString("cantidad"));
                it.setUnidad(rs.getString("unidad"));
                it.setDetalle(rs.getString("detalle"));
                it.setObservacion(rs.getString("observacion"));
                
                riItems.add(it);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los riItems.\n");
        }
        return riItems;

    }
    
    public RiItem findById(int id) {
        String query = null;
       RiItem it = new RiItem();
        try {
            query = "select * from ri_item where riItemId = " +id;
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                it.setRiItemId(rs.getInt("riItemId"));
                it.setRiId(rs.getInt("riId"));
                it.setCantidad(rs.getString("cantidad"));
                it.setUnidad(rs.getString("unidad"));
                it.setDetalle(rs.getString("detalle"));
                it.setObservacion(rs.getString("observacion"));
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los riItem.\n");
        }
        return it;

    }
    
    public boolean borrar(RiItem it) {

        boolean r =false;
        try {
            
            String query = "delete from ri_item where riItemId = ? ";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, it.getRiItemId());
            
            
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
