/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Equipos;
import Modelo.ItemAlarma;
import Utils.FechaUtil;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author matuu
 */
public class EquiposDAO {
     Connection conector;

    public EquiposDAO() {
    }

    public void conectar() {
        try {
            conector = ConectorDB.getConector();
        } catch (Exception ex) {
        }
    }

    public int guardar(Equipos eq) {
        int r = 0;
        String query = null;

        try {
            /*`N_INTERNO` `VEHICULO`  `MARCA`   `MODELO`  `AÑO`  `DOMINIO` 
            */
            query = "insert into equipos (N_INTERNO, EQUIPO, MARCA,"
                    + " MODELO, AÑO, DOMINIO, VTO_VT, VTO_SEGURO) values ( ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setString(1, eq.getN_interno());
            ps.setString(2, eq.getEquipos());
            ps.setString(3, eq.getMarca());
            ps.setString(4, eq.getModelo());
            ps.setDouble(5, eq.getAño());
            ps.setString(6, eq.getDominio());
            ps.setDate(7, FechaUtil.getFechatoDB(eq.getVto_vt()));
            ps.setDate(8, FechaUtil.getFechatoDB(eq.getVto_seguro()));
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

    public int modificar(Equipos eq) {

        int r = 0;
        try {
            String query = "update equipos set N_INTERNO=?, EQUIPO=?, MARCA=?,"
                    + " MODELO=?, AÑO=?, DOMINIO=?, VTO_VT=?, VTO_SEGURO=? where ID = ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setString(1, eq.getN_interno());
            ps.setString(2, eq.getEquipos());
            ps.setString(3, eq.getMarca());
            ps.setString(4, eq.getModelo());
            ps.setDouble(5, eq.getAño());
            ps.setString(6, eq.getDominio());
            ps.setDate(7, FechaUtil.getFechatoDB(eq.getVto_vt()));
            ps.setDate(8, FechaUtil.getFechatoDB(eq.getVto_seguro()));
            ps.setInt(9, eq.getId());
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

    public ArrayList<Equipos> cargarTodos() {
        String query = null;
        ArrayList<Equipos> equipos = new ArrayList<Equipos>();
        try {
            query = "select * from equipos";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Equipos eq = new Equipos();
                eq.setId(rs.getInt("ID"));
                eq.setN_interno(rs.getString("N_INTERNO"));
                eq.setEquipos(rs.getString("EQUIPO"));
                eq.setMarca(rs.getString("MARCA"));
                eq.setModelo(rs.getString("MODELO"));
                eq.setAño(rs.getDouble("AÑO"));
                eq.setDominio(rs.getString("DOMINIO"));
                eq.setVto_vt(rs.getDate("VTO_VT"));
                eq.setVto_seguro(rs.getDate("VTO_SEGURO"));
                equipos.add(eq);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los equipos.\n");
        }
        return equipos;

    }
    public ArrayList<Equipos> cargarTodosMenos1() {
        String query = null;
        ArrayList<Equipos> equipos = new ArrayList<Equipos>();
        try {
            query = "select * from equipos where ID != 1";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Equipos eq = new Equipos();
                eq.setId(rs.getInt("ID"));
                eq.setN_interno(rs.getString("N_INTERNO"));
                eq.setEquipos(rs.getString("EQUIPO"));
               eq.setMarca(rs.getString("MARCA"));
                eq.setModelo(rs.getString("MODELO"));
                eq.setAño(rs.getDouble("AÑO"));
                eq.setDominio(rs.getString("DOMINIO"));
                eq.setVto_vt(rs.getDate("VTO_VT"));
                eq.setVto_seguro(rs.getDate("VTO_SEGURO"));
                equipos.add(eq);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los equipos.\n");
        }
        return equipos;

    }
    
    public Equipos findById(int id) {
        String query = null;
       Equipos eq = new Equipos();
        try {
            query = "select * from equipos where ID = " +id;
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                eq.setId(rs.getInt("ID"));
                eq.setN_interno(rs.getString("N_INTERNO"));
                eq.setEquipos(rs.getString("EQUIPO"));
                eq.setMarca(rs.getString("MARCA"));
                eq.setModelo(rs.getString("MODELO"));
                eq.setAño(rs.getDouble("AÑO"));
                eq.setDominio(rs.getString("DOMINIO"));
                eq.setVto_vt(rs.getDate("VTO_VT"));
                eq.setVto_seguro(rs.getDate("VTO_SEGURO"));
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los equipos.\n");
        }
        return eq;

    }

    public ArrayList<Equipos> buscar(int opcion, String consulta) {
        String query = null;
        ArrayList<Equipos> equipos = new ArrayList<Equipos>();
        try {
            switch(opcion){
                case 0:
                    query = "select * from equipos where DOMINIO like '%"+consulta+"%'";
                    break;
                case 1:
                    query = "select * from equipos where N_INTERNO like '%"+consulta+"%'";
                    break;
                case 2:
                    query = "select * from equipos where MARCA like '%"+consulta+"%'";
                    break;
                case 3:
                    query = "select * from equipos where AÑO like '%"+consulta+"%'";
                    break;
            }
            
            PreparedStatement ps = conector.prepareStatement(query);
            
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               Equipos eq = new Equipos();
                eq.setId(rs.getInt("ID"));
                eq.setN_interno(rs.getString("N_INTERNO"));
                eq.setEquipos(rs.getString("EQUIPO"));
                eq.setMarca(rs.getString("MARCA"));
                eq.setModelo(rs.getString("MODELO"));
                eq.setAño(rs.getDouble("AÑO"));
                eq.setDominio(rs.getString("DOMINIO"));
                eq.setVto_vt(rs.getDate("VTO_VT"));
                eq.setVto_seguro(rs.getDate("VTO_SEGURO"));
               equipos.add(eq);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los equipos.\n");
        }
        return equipos;

    }
    
    public boolean borrar(Equipos eq) {

        boolean r =false;
        try {
            
            String query = "delete from equipos where ID = ? and N_INTERNO = ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, eq.getId());
            ps.setString(2, eq.getN_interno());
            
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
    
    public ArrayList<Equipos> buscarDIMA( String consulta) {
        String query = null;
        ArrayList<Equipos> equipos = new ArrayList<Equipos>();
        try {
            query = "select  * from equipos where DOMINIO like '%"+consulta+"%' or "
                    + " EQUIPO like '%"+consulta+"%' or N_INTERNO like '%"+consulta+"%' or"
                    + "  MARCA like '%"+consulta+"%' or AÑO like '%"+consulta+"%'";
                    
                       
            PreparedStatement ps = conector.prepareStatement(query);
            
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               Equipos eq = new Equipos();
                eq.setId(rs.getInt("ID"));
                eq.setN_interno(rs.getString("N_INTERNO"));
                eq.setEquipos(rs.getString("EQUIPO"));
                eq.setMarca(rs.getString("MARCA"));
                eq.setModelo(rs.getString("MODELO"));
                eq.setAño(rs.getDouble("AÑO"));
                eq.setDominio(rs.getString("DOMINIO"));
                eq.setVto_vt(rs.getDate("VTO_VT"));
                eq.setVto_seguro(rs.getDate("VTO_SEGURO"));
               equipos.add(eq);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los equipos.\n");
        }
        return equipos;

    }
    
    public ArrayList<ItemAlarma> getAlarmasEquipos(java.util.Date inicio, java.util.Date fin){
        ArrayList<ItemAlarma> alarmas = new ArrayList<ItemAlarma>();
        try {
            String query = "select distinct * from equipos where (VTO_VT "
                    + ">= ? and VTO_VT <= ?) or (VTO_SEGURO >= ? and VTO_SEGURO <= ?)";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setDate(1, FechaUtil.getFechatoDB(inicio));
            ps.setDate(2, FechaUtil.getFechatoDB(fin));
            ps.setDate(3, FechaUtil.getFechatoDB(inicio));
            ps.setDate(4, FechaUtil.getFechatoDB(fin));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                java.util.Date vt = FechaUtil.getFechaFromDB(rs.getDate("VTO_VT"));
                java.util.Date seg = FechaUtil.getFechaFromDB(rs.getDate("VTO_SEGURO"));
                
                if(vt!=null && vt.after(inicio) && vt.before(fin)){ //vt se encuentra en el rango
                    ItemAlarma al = new ItemAlarma();
                    if(vt.equals(inicio)) {
                        al.setTipo(1);
                    }else {
                        al.setTipo(0);
                    }
                    al.setFecha(vt);
                    al.setMensaje("La verificación técnica del equipo "+rs.getString("N_INTERNO")+" vence el "+FechaUtil.getFecha(vt));
                    alarmas.add(al);
                }
                
                if(seg != null && seg.after(inicio) && seg.before(fin)){ //seg se encuentra en el rango
                    ItemAlarma al = new ItemAlarma();
                    if(seg.equals(inicio)) {
                        al.setTipo(1);
                    }else {
                        al.setTipo(0);
                    }
                    al.setFecha(seg);
                    al.setMensaje("El seguro del equipo "+rs.getString("N_INTERNO")+" vence el "+FechaUtil.getFecha(seg));
                    alarmas.add(al);
                }
           
            }
            rs.close();
            ps.close();
            
        }catch (SQLException ex){
            System.out.print("Falló al cargar las alarmas de los equipos.\n");
        }
        return alarmas;
    }
    
}
