/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.RI;
import Modelo.RiItem;
import Utils.FechaUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
    
    public int guardar(RI ri, ArrayList<RiItem> riItemes) {
        int r = 0;
        String query = null;
        
        try {          
            conector.setAutoCommit(false);
            query = "insert into ri (OBRAID, RI_NUM, "
                    + "OBSERVACIONES, SOLICITANTE, FECHA_CREACION) "
                    + "values ( ?, ?, ?, ?, ?)";
            PreparedStatement ps = conector.prepareStatement(query);     
            ps.setInt(1, ri.getObraID());
            ps.setString(2, ri.getRI_num());    
            ps.setString(3, ri.getObservaciones());
            ps.setString(4, ri.getSolicitante());
            ps.setDate(5, FechaUtil.getFechatoDB(new Date()));
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                r = generatedKeys.getInt(1);
            }
            RIItemDAO riiDao = new RIItemDAO();
            riiDao.conectar(conector);
            for(RiItem item:riItemes){
                item.setRiId(r);
                riiDao.guardar(item);
            }
            conector.commit();
            
            generatedKeys.close();
            ps.close();
            conector.setAutoCommit(true);
            
        } catch (SQLException ex) {
            r = 0;
        }
        return r;
    }
    
    public int modificar(RI ri, ArrayList<RiItem> riItems) {
        int r = 0;
        String query = null;

        try {
            conector.setAutoCommit(false);
            query = "update ri set OBRAID=?, RI_NUM=?, "
                    + "OBSERVACIONES=?, SOLICITANTE=? "
                    + " where RIID = " +ri.getRI_ID();
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, ri.getObraID());
            ps.setString(2, ri.getRI_num());
            ps.setString(3, ri.getObservaciones());
            ps.setString(4, ri.getSolicitante());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                r = generatedKeys.getInt(1);
            }else{
                r= ri.getRI_ID();
                
            }
            RIItemDAO riiDao = new RIItemDAO();
            riiDao.conectar(conector);
            final ArrayList<RiItem> riItemAll = riiDao.cargarTodos(r);
            for(RiItem item:riItemAll){
                if(riItems.contains(item)){ //si sigue estando, se modifica
                    //busco el index del item
                    int idx = riItems.indexOf(item);
                    //guardamos los cambios
                    riiDao.modificar(riItems.get(idx));
                    //lo removemos de la lista
                    riItems.remove(idx);  
                }else{ //si no está se elimina
                    riiDao.borrar(item);
                }
            }
            if(!riItems.isEmpty()){ //Si quedan item sin guardar
                for(RiItem it: riItems){
                    it.setRiId(r);
                    riiDao.guardar(it);
                }
            }
            conector.commit();
            generatedKeys.close();
            ps.close();
            conector.setAutoCommit(false);

        } catch (SQLException ex) {
            r = 0;
        }
        return r;
    }
    
    public boolean borrar(RI ri) {

        boolean r =false;
        try {
            conector.setAutoCommit(false);
            String query = "delete from ri where RIID = ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, ri.getRI_ID());
            int rs = ps.executeUpdate();
            if (rs != 0) {   
                r=true;
                query = "delete from alarma where RI_ID = ?";
                ps = conector.prepareStatement(query);
                ps.setInt(1, ri.getRI_ID());
                rs = ps.executeUpdate();
                
                query = "delete from ri_item where riId = ?";
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
        ArrayList<RI> ris = new ArrayList<RI>();
        try {
            query = "select ri.*, OB.codigo from ri "
                    + "LEFT JOIN obras OB ON ri.obraID = OB.id order by fecha_creacion asc";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RI ri = new RI();
                ri.setRI_ID(rs.getInt("RIID"));
                ri.setObraID(rs.getInt("OBRAID"));
                ri.setRI_num(rs.getString("RI_NUM"));
                ri.setObservaciones(rs.getString("OBSERVACIONES"));
                ri.setSolicitante(rs.getString("SOLICITANTE"));
                ri.setCodigoObra(rs.getString("CODIGO"));
                ri.setFecha_creacion(rs.getDate("FECHA_CREACION"));
                ris.add(ri);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar las ri " +ex.getMessage()+ "\n");
        }
        return ris;

    } 
    
    public RI findById(int RI_ID) {
        String query = null;
        RI ri = new RI();
        try {
            query = "select ri.*, OB.codigo from ri "
                    + "LEFT JOIN obras OB ON ri.obraID = OB.id where "
                    + "ri.RIID = "+RI_ID+ " order by fecha_creacion asc";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                ri.setRI_ID(rs.getInt("RIID"));
                ri.setObraID(rs.getInt("OBRAID"));
                ri.setRI_num(rs.getString("RI_NUM"));
                ri.setObservaciones(rs.getString("OBSERVACIONES"));
                ri.setSolicitante(rs.getString("SOLICITANTE"));
                ri.setCodigoObra(rs.getString("CODIGO"));
                ri.setFecha_creacion(rs.getDate("FECHA_CREACION"));
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
        ArrayList<RI> ris = new ArrayList<RI>();
        try {
            query = "select ri.*, OB.codigo from ri "
                    + "LEFT JOIN obras OB ON ri.obraID = OB.id where "
                    + "ri.RI_NUM like '%"+q+"%' or "
                    + "OB.codigo like '%"+q+"%' or "
                    + "ri.solicitante like '%"+q+"%' "
                    + "order by fecha_creacion asc";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RI ri = new RI();
                ri.setRI_ID(rs.getInt("RIID"));
                ri.setObraID(rs.getInt("OBRAID"));
                ri.setRI_num(rs.getString("RI_NUM"));
                ri.setObservaciones(rs.getString("OBSERVACIONES"));
                ri.setSolicitante(rs.getString("SOLICITANTE"));
                ri.setCodigoObra(rs.getString("CODIGO"));
                ri.setFecha_creacion(rs.getDate("FECHA_CREACION"));
                ris.add(ri);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los ri: " +ex.getMessage()+ "\n");
        }
        return ris;

    } 
}
