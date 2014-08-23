/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Alarma;
import Utils.FechaUtil;
import ViewModel.ItemAlarmaBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author matuuar
 */
public class AlarmasDAO {
    public AlarmasDAO() {}
    
    Connection conector;
    public void conectar() {
        try {
            conector = ConectorDB.getConector();
        } catch (Exception ex) {
        }
    }
    
    public int guardar(Alarma al) {
        int r = 0;
        String query = null;

        try {          
            query = "insert into alarma (FECHA, NOMBRE, COMENTARIO, "
                    + "FECHA_PREVIA, RI_ID) "
                    + "values ( ?, ?, ?, ?, ?)";
            PreparedStatement ps = conector.prepareStatement(query);          
            ps.setDate(1, FechaUtil.getFechatoDB(al.getFecha()));
            ps.setString(2, al.getNombre());
            ps.setString(3, al.getComentario());
            ps.setDate(4, FechaUtil.getFechatoDB(al.getFecha_previa()));
            ps.setInt(5, al.getRiID());
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
    
    public int modificar(Alarma al) {
        int r = 0;
        String query = null;

        try {
            /*(ALARMAID, FECHA, NOMBRE, COMENTARIO, "
                    + "FECHA_PREVIA, RI_ID)*/
            query = "update alarma set FECHA=?, NOMBRE=?, COMENTARIO=?,"
                    + " FECHA_PREVIA =?, RI_ID=? where ALARMAID =" +al.getAlarmaID();
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setDate(1, FechaUtil.getFechatoDB(al.getFecha()));
            ps.setString(2, al.getNombre());
            ps.setString(3, al.getComentario());
            ps.setDate(4, FechaUtil.getFechatoDB(al.getFecha_previa()));
            ps.setInt(5, al.getRiID());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                r = generatedKeys.getInt(1);
            }else{
                r= al.getAlarmaID();
                
            }
            generatedKeys.close();
            ps.close();

        } catch (SQLException ex) {
            r = 0;
        }
        return r;
    }
    
    public boolean borrar(Alarma al) {

        boolean r =false;
        try {
            
            String query = "delete from alarma where ALARMAID = ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, al.getAlarmaID());
            
            
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
    
    public ArrayList<Alarma> cargarTodos() {
        String query = null;
        ArrayList<Alarma> alarmas = new ArrayList<Alarma>();
        Date hace2Mes = FechaUtil.restarFechasDias(new Date(), 60);
        try {
            query = "select * from alarma where fecha >"+FechaUtil.getFecha(hace2Mes)+" order by fecha desc ";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                Alarma al = new Alarma();
                al.setAlarmaID(rs.getInt("ALARMAID"));
                al.setRiID(rs.getInt("RI_ID"));
                al.setFecha(rs.getDate("FECHA"));
                al.setFecha_previa(rs.getDate("FECHA_PREVIA"));
                al.setComentario(rs.getString("COMENTARIO"));
                al.setNombre(rs.getString("NOMBRE"));
                alarmas.add(al);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar las alarmas " +ex.getMessage()+ "\n");
        }
        return alarmas;

    } 
   public ArrayList<Alarma> findById(int alarmaID) {
        String query = null;
        ArrayList<Alarma> alarmas = new ArrayList<Alarma>();
        try {
            query = "select * from alarmas where ALARMAID = "+alarmaID;
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 
                 Alarma al = new Alarma();
                al.setAlarmaID(rs.getInt("ALARMAID"));
                al.setRiID(rs.getInt("RI_ID"));
                al.setFecha(rs.getDate("FECHA"));
                al.setFecha_previa(rs.getDate("FECHA_PREVIA"));
                al.setComentario(rs.getString("COMENTARIO"));
                al.setNombre(rs.getString("NOMBRE"));
                alarmas.add(al);
                
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar las alarmas: " +ex.getMessage()+ "\n");
        }
        return alarmas;

    } 
   
   public ArrayList<Alarma> find(String q) {
        String query = null;
        ArrayList<Alarma> alarmas = new ArrayList<Alarma>();
        try {
            query = "select distinct * from alarma where NOMBRE like '%" + q + "%' or COMENTARIO like '%" + q +"%'";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 
                Alarma al = new Alarma();
                al.setAlarmaID(rs.getInt("ALARMAID"));
                al.setRiID(rs.getInt("RI_ID"));
                al.setFecha(rs.getDate("FECHA"));
                al.setFecha_previa(rs.getDate("FECHA_PREVIA"));
                al.setComentario(rs.getString("COMENTARIO"));
                al.setNombre(rs.getString("NOMBRE"));
                alarmas.add(al);
                
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar las alarmas: " +ex.getMessage()+ "\n");
        }
        return alarmas;

    } 
   
   public ArrayList<Alarma> findAlarmasActivadas() {
        String query = null;
        
        Date hoy = new Date();
        hoy = FechaUtil.resetTime(hoy);
        ArrayList<Alarma> alarmas = new ArrayList<Alarma>();
        try {
            query = "select * from alarma where fecha >='"+FechaUtil.getFechatoDB(hoy)+"' order by fecha asc";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                try{
                Date fecha = FechaUtil.resetTime(FechaUtil.getFechaFromDB(rs.getDate("FECHA")));
                Date previa = FechaUtil.getFechaFromDB(rs.getDate("FECHA_PREVIA"));
                 if(hoy.equals(fecha) || (previa != null && (hoy.equals(previa)  || hoy.after(previa)))) {
                    Alarma al = new Alarma();
                    al.setAlarmaID(rs.getInt("ALARMAID"));
                    al.setRiID(rs.getInt("RI_ID"));
                    al.setFecha(rs.getDate("FECHA"));
                    al.setFecha_previa(rs.getDate("FECHA_PREVIA"));
                    al.setComentario(rs.getString("COMENTARIO"));
                    al.setNombre(rs.getString("NOMBRE"));
                    alarmas.add(al);
                 }
                }catch(Exception ex){
                    System.err.print("Falló al cargar la alarma : " +ex.getMessage()+ "\n");
                }
                
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.err.print("Falló al cargar las alarmas: " +ex.getMessage()+ "\n");
        }
        return alarmas;

    } 
   
   public ArrayList<ItemAlarmaBean> findAlarmasAntesDe(Date limite) {
        String query = null;
        ArrayList<ItemAlarmaBean> alarmas = new ArrayList<ItemAlarmaBean>();
        try {
            query = "SELECT fecha, comentario, nombre, fecha_previa FROM alarma "
                    + "WHERE fecha >=  ?  and fecha <= ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setDate(1, FechaUtil.getFechatoDB(new Date()));
            ps.setDate(2, FechaUtil.getFechatoDB(limite));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 
                ItemAlarmaBean al = new ItemAlarmaBean();
                al.setFecha(rs.getDate("fecha"));
                al.setFechaPrevia(rs.getDate("fecha_previa"));
                al.setComentario(rs.getString("comentario"));
                al.setMensaje(rs.getString("nombre"));
                alarmas.add(al);             
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar las alarmas: " +ex.getMessage()+ "\n");
        }
        return alarmas;

    } 
   
   //SELECT fecha, comentario, nombre, fecha_previa FROM alarma WHERE fecha >=  $P{hoy}  and fecha <= $P{fecha_limite}
}
