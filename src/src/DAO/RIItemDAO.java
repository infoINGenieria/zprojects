/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.RiItem;
import Utils.FechaUtil;
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
    
    public int guardar(RiItem it) throws SQLException {
        int r = 0;
        String query = null;
        query = "insert into ri_item (riId, cantidad, unidad,"
                + " detalle, observacion, oc_num, proveedor, "
                +" fecha_entrega, fecha_oc, fecha_emision, fecha_necesidad,"
                +" valor) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conector.prepareStatement(query);
        ps.setInt(1, it.getRiId());
        ps.setInt(2, it.getCantidad());
        ps.setString(3, it.getUnidad());
        ps.setString(4, it.getDetalle());
        ps.setString(5, it.getObservacion());
        ps.setString(6, it.getOC_num());
        ps.setString(7, it.getProveedor());
        ps.setDate(8, FechaUtil.getFechatoDB( it.getFecha_entrega()));
        ps.setDate(9, FechaUtil.getFechatoDB(it.getFecha_oc()));
        ps.setDate(10, FechaUtil.getFechatoDB(it.getFecha_emision()));
        ps.setDate(11, FechaUtil.getFechatoDB(it.getFecha_necesidad()));
        ps.setString(12, it.getValor());
        ps.executeUpdate();
        ResultSet generatedKeys = ps.getGeneratedKeys();

        if (generatedKeys.next()) {
            r = generatedKeys.getInt(1);
        }
        generatedKeys.close();
        ps.close();

        
        return r;
    }

    public int modificar(RiItem it) throws SQLException {

        int r = 0;
        
         /*`riItemId , riId, cantidad, unidad, detalle, observacion
        */
        String query = "update ri_item set riId=?, cantidad=?, unidad=?,"
                + " detalle=?, observacion=?, oc_num=?, proveedor=?, fecha_entrega=?, "
                + " fecha_oc=?, fecha_emision=?, fecha_necesidad=?, valor=? where riItemId = ?";
        PreparedStatement ps = conector.prepareStatement(query);
        ps.setInt(1, it.getRiId());
        ps.setInt(2, it.getCantidad());
        ps.setString(3, it.getUnidad());
        ps.setString(4, it.getDetalle());
        ps.setString(5, it.getObservacion());
        ps.setString(6, it.getOC_num());
        ps.setString(7, it.getProveedor());
        ps.setDate(8, FechaUtil.getFechatoDB( it.getFecha_entrega()));
        ps.setDate(9, FechaUtil.getFechatoDB(it.getFecha_oc()));
        ps.setDate(10, FechaUtil.getFechatoDB(it.getFecha_emision()));
        ps.setDate(11, FechaUtil.getFechatoDB(it.getFecha_necesidad()));
        ps.setString(12, it.getValor());
        ps.setInt(13, it.getId());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            r = rs.getInt(1);
        }
        ps.close();
        rs.close();

        return r;
    }
    
    public ArrayList<RiItem> cargarTodos(int riId) {
        String query = null;
        ArrayList<RiItem> riItems = new ArrayList<RiItem>();
        try {
            query = "select * from ri_item where riId =" +riId;
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RiItem it = new RiItem();
                it.setId(rs.getInt("riItemId"));
                it.setRiId(rs.getInt("riId"));
                it.setCantidad(rs.getInt("cantidad"));
                it.setUnidad(rs.getString("unidad"));
                it.setDetalle(rs.getString("detalle"));
                it.setObservacion(rs.getString("observacion"));
                it.setOC_num(rs.getString("oc_num"));
                it.setProveedor(rs.getString("proveedor"));
                it.setFecha_entrega(rs.getDate("fecha_entrega"));
                it.setFecha_oc(rs.getDate("fecha_oc"));
                it.setFecha_emision(rs.getDate("fecha_emision"));
                it.setFecha_necesidad(rs.getDate("fecha_necesidad"));
                it.setValor(rs.getString("valor"));
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
                
                it.setId(rs.getInt("riItemId"));
                it.setRiId(rs.getInt("riId"));
                it.setCantidad(rs.getInt("cantidad"));
                it.setUnidad(rs.getString("unidad"));
                it.setDetalle(rs.getString("detalle"));
                it.setObservacion(rs.getString("observacion"));
                it.setOC_num(rs.getString("oc_num"));
                it.setProveedor(rs.getString("proveedor"));
                it.setFecha_entrega(rs.getDate("fecha_entrega"));
                it.setFecha_oc(rs.getDate("fecha_oc"));
                it.setFecha_emision(rs.getDate("fecha_emision"));
                it.setFecha_necesidad(rs.getDate("fecha_necesidad"));
                it.setValor(rs.getString("valor"));
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
            ps.setInt(1, it.getId());
            
            
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
