/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.RI;
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
public class RIDAO {
    
    Connection conector;
    public void conectar() {
        try {
            conector = ConectorDB.getConector();
        } catch (Exception ex) {
        }
    }
    public int guardar(RI ri) {
        int r = 0;
        String query = null;

        try {          
            query = "insert into RI (OBRAID, RI_NUM, CANTIDAD, UNIDAD, DETALLE, "
                    + "FECHA_NECESIDAD, OBSERVACIONES, IMPORTE, OC_NUM, "
                    + "PROVEEDOR, FECHA_EMISION, FECHA_OC, FECHA_ENTREGA) "
                    + "values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conector.prepareStatement(query);     
            ps.setInt(1, ri.getObraID());
            ps.setString(2, ri.getRI_num());
            ps.setInt(3, ri.getCantidad());
            ps.setString(4, ri.getUnidad());
            ps.setString(5, ri.getDetalle());
            ps.setDate(6, FechaUtil.getFechatoDB(ri.getFecha_necesidad()));
            ps.setString(7, ri.getObservaciones());
            ps.setFloat(8, ri.getImporte());
            ps.setString(9,ri.getOC_num());
            ps.setString(10, ri.getProveedor());
            ps.setDate(11, FechaUtil.getFechatoDB(ri.getFecha_emision()));
            ps.setDate(12, FechaUtil.getFechatoDB(ri.getFecha_oc()));
            ps.setDate(13, FechaUtil.getFechatoDB(ri.getFecha_entrega()));
            
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
    
    public int modificar(RI ri) {
        int r = 0;
        String query = null;

        try {
            /*(ALARMAID, FECHA, NOMBRE, COMENTARIO, "
                    + "FECHA_PREVIA, RI_ID)*/
            query = "update RI set OBRAID=?, RI_NUM=?, CANTIDAD=?, UNIDAD=?, DETALLE=?, "
                    + "FECHA_NECESIDAD=?, OBSERVACIONES=?, IMPORTE=?, OC_NUM=?, "
                    + "PROVEEDOR=?, FECHA_EMISION=?, FECHA_OC=?, FECHA_ENTREGA=? where RIID = " +ri.getRI_ID();
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, ri.getObraID());
            ps.setString(2, ri.getRI_num());
            ps.setInt(3, ri.getCantidad());
            ps.setString(4, ri.getUnidad());
            ps.setString(5, ri.getDetalle());
            ps.setDate(6, FechaUtil.getFechatoDB(ri.getFecha_necesidad()));
            ps.setString(7, ri.getObservaciones());
            ps.setFloat(8, ri.getImporte());
            ps.setString(9,ri.getOC_num());
            ps.setString(10, ri.getProveedor());
            ps.setDate(11, FechaUtil.getFechatoDB(ri.getFecha_emision()));
            ps.setDate(12, FechaUtil.getFechatoDB(ri.getFecha_oc()));
            ps.setDate(13, FechaUtil.getFechatoDB(ri.getFecha_entrega()));
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                r = generatedKeys.getInt(1);
            }else{
                r= ri.getRI_ID();
                
            }
            generatedKeys.close();
            ps.close();

        } catch (SQLException ex) {
            r = 0;
        }
        return r;
    }
    
    public boolean borrar(RI ri) {

        boolean r =false;
        try {
            conector.setAutoCommit(false);
            String query = "delete from RI where RIID = ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, ri.getRI_ID());
            int rs = ps.executeUpdate();
            if (rs != 0) {   
                r=true;
                query = "delete from alarma where RI_ID = ?";
                ps = conector.prepareStatement(query);
                ps.setInt(1, ri.getRI_ID());
                rs = ps.executeUpdate();
                
            }
            conector.commit();
            ps.close();
            //rs.close();
            conector.setAutoCommit(true);
            r=true;

        } catch (SQLException ex) {
            r = false;
        }

        return r;
    }
    
    public ArrayList<RI> cargarTodos() {
        String query = null;
        ArrayList<RI> alarmas = new ArrayList<RI>();
        try {
            query = "select RI.*, OB.codigo from RI "
                    + "INNER JOIN obras OB ON RI.obraID = OB.id order by fecha_necesidad asc";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RI ri = new RI();
                ri.setRI_ID(rs.getInt("RIID"));
                ri.setObraID(rs.getInt("OBRAID"));
                ri.setRI_num(rs.getString("RI_NUM"));
                ri.setCantidad(rs.getInt("CANTIDAD"));
                ri.setUnidad(rs.getString("UNIDAD"));
                ri.setDetalle(rs.getString("DETALLE"));
                ri.setFecha_necesidad(rs.getDate("FECHA_NECESIDAD"));
                ri.setObservaciones(rs.getString("OBSERVACIONES"));
                ri.setImporte(rs.getFloat("IMPORTE"));
                ri.setOC_num(rs.getString("OC_NUM"));
                ri.setProveedor(rs.getString("PROVEEDOR"));
                ri.setFecha_emision(rs.getDate("FECHA_EMISION"));
                ri.setFecha_oc(rs.getDate("FECHA_OC"));
                ri.setFecha_entrega(rs.getDate("FECHA_ENTREGA"));
                ri.setCodigoObra(rs.getString("CODIGO"));
                alarmas.add(ri);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar las alarmas " +ex.getMessage()+ "\n");
        }
        return alarmas;

    } 
    
    public RI findById(int RI_ID) {
        String query = null;
        RI ri = new RI();
        try {
            query = "select RI.*, OB.codigo from RI "
                    + "INNER JOIN obras OB ON RI.obraID = OB.id where "
                    + "RI.RIID = "+RI_ID+ " order by fecha_necesidad asc";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                ri.setRI_ID(rs.getInt("RIID"));
                ri.setObraID(rs.getInt("OBRAID"));
                ri.setRI_num(rs.getString("RI_NUM"));
                ri.setCantidad(rs.getInt("CANTIDAD"));
                ri.setUnidad(rs.getString("UNIDAD"));
                ri.setDetalle(rs.getString("DETALLE"));
                ri.setFecha_necesidad(rs.getDate("FECHA_NECESIDAD"));
                ri.setObservaciones(rs.getString("OBSERVACIONES"));
                ri.setImporte(rs.getFloat("IMPORTE"));
                ri.setOC_num(rs.getString("OC_NUM"));
                ri.setProveedor(rs.getString("PROVEEDOR"));
                ri.setFecha_emision(rs.getDate("FECHA_EMISION"));
                ri.setFecha_oc(rs.getDate("FECHA_OC"));
                ri.setFecha_entrega(rs.getDate("FECHA_ENTREGA"));
                ri.setCodigoObra(rs.getString("CODIGO"));
                
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los ri: " +ex.getMessage()+ "\n");
        }
        return ri;

    } 
    
    public ArrayList<RI> find(String q) {
        String query = null;
        ArrayList<RI> alarmas = new ArrayList<RI>();
        try {
            query = "select RI.*, OB.codigo from RI "
                    + "INNER JOIN obras OB ON RI.obraID = OB.id where "
                    + "RI.RI_NUM like '%"+q+ "%' order by fecha_necesidad asc";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RI ri = new RI();
                ri.setRI_ID(rs.getInt("RIID"));
                ri.setObraID(rs.getInt("OBRAID"));
                ri.setRI_num(rs.getString("RI_NUM"));
                ri.setCantidad(rs.getInt("CANTIDAD"));
                ri.setUnidad(rs.getString("UNIDAD"));
                ri.setDetalle(rs.getString("DETALLE"));
                ri.setFecha_necesidad(rs.getDate("FECHA_NECESIDAD"));
                ri.setObservaciones(rs.getString("OBSERVACIONES"));
                ri.setImporte(rs.getFloat("IMPORTE"));
                ri.setOC_num(rs.getString("OC_NUM"));
                ri.setProveedor(rs.getString("PROVEEDOR"));
                ri.setFecha_emision(rs.getDate("FECHA_EMISION"));
                ri.setFecha_oc(rs.getDate("FECHA_OC"));
                ri.setFecha_entrega(rs.getDate("FECHA_ENTREGA"));
                ri.setCodigoObra(rs.getString("CODIGO"));
                alarmas.add(ri);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los ri: " +ex.getMessage()+ "\n");
        }
        return alarmas;

    } 
}
