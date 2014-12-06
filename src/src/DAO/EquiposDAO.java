/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Equipos;
import ViewModel.ItemAlarma;
import Utils.FechaUtil;
import ViewModel.ItemAlarmaBean;
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
                    + " MODELO, AÑO, DOMINIO, VTO_VTV, VTO_SEGURO, "
                    + " VTO_OTROS1, VTO_OTROS2, VTO_OTROS3, DESCRIPCION_VTO1, DESCRIPCION_VTO2, DESCRIPCION_VTO3"
                    + ") values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setString(1, eq.getN_interno());
            ps.setString(2, eq.getEquipos());
            ps.setString(3, eq.getMarca());
            ps.setString(4, eq.getModelo());
            ps.setDouble(5, eq.getAño());
            ps.setString(6, eq.getDominio());
            ps.setDate(7, FechaUtil.getFechatoDB(eq.getVto_vtv()));
            ps.setDate(8, FechaUtil.getFechatoDB(eq.getVto_seguro()));
            ps.setDate(9, FechaUtil.getFechatoDB(eq.getVto_otros1()));
            ps.setDate(10, FechaUtil.getFechatoDB(eq.getVto_otros2()));
            ps.setDate(11, FechaUtil.getFechatoDB(eq.getVto_otros3()));
            ps.setString(12, eq.getDescripcion_vto1());
            ps.setString(13, eq.getDescripcion_vto2());
            ps.setString(14, eq.getDescripcion_vto3());
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
            String query = "update equipos set N_INTERNO=?, EQUIPO=?, MARCA=?, "
                    + "MODELO=?, AÑO=?, DOMINIO=?, VTO_VTV=?, VTO_SEGURO=?, "
                    + "VTO_OTROS1=?, VTO_OTROS2=?, VTO_OTROS3=?, DESCRIPCION_VTO1=?, "
                    + "DESCRIPCION_VTO2=?, DESCRIPCION_VTO3=? "
                    + "where ID = ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setString(1, eq.getN_interno());
            ps.setString(2, eq.getEquipos());
            ps.setString(3, eq.getMarca());
            ps.setString(4, eq.getModelo());
            ps.setDouble(5, eq.getAño());
            ps.setString(6, eq.getDominio());
            ps.setDate(7, FechaUtil.getFechatoDB(eq.getVto_vtv()));
            ps.setDate(8, FechaUtil.getFechatoDB(eq.getVto_seguro()));
            ps.setDate(9, FechaUtil.getFechatoDB(eq.getVto_otros1()));
            ps.setDate(10, FechaUtil.getFechatoDB(eq.getVto_otros2()));
            ps.setDate(11, FechaUtil.getFechatoDB(eq.getVto_otros3()));
            ps.setString(12, eq.getDescripcion_vto1());
            ps.setString(13, eq.getDescripcion_vto2());
            ps.setString(14, eq.getDescripcion_vto3());
            ps.setInt(15, eq.getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                r = rs.getInt(1);
            }
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            r = -1;
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
                eq.setVto_vtv(rs.getDate("VTO_VTV"));
                eq.setVto_seguro(rs.getDate("VTO_SEGURO"));
                eq.setVto_otros1(rs.getDate("VTO_OTROS1"));
                eq.setVto_otros2(rs.getDate("VTO_OTROS2"));
                eq.setVto_otros3(rs.getDate("VTO_OTROS3"));
                eq.setDescripcion_vto1(rs.getString("DESCRIPCION_VTO1"));
                eq.setDescripcion_vto2(rs.getString("DESCRIPCION_VTO2"));
                eq.setDescripcion_vto3(rs.getString("DESCRIPCION_VTO3"));
                
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
                eq.setVto_vtv(rs.getDate("VTO_VTV"));
                eq.setVto_seguro(rs.getDate("VTO_SEGURO"));
                eq.setVto_otros1(rs.getDate("VTO_OTROS1"));
                eq.setVto_otros2(rs.getDate("VTO_OTROS2"));
                eq.setVto_otros3(rs.getDate("VTO_OTROS3"));
                eq.setDescripcion_vto1(rs.getString("DESCRIPCION_VTO1"));
                eq.setDescripcion_vto2(rs.getString("DESCRIPCION_VTO2"));
                eq.setDescripcion_vto3(rs.getString("DESCRIPCION_VTO3"));
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
                eq.setVto_vtv(rs.getDate("VTO_VTV"));
                eq.setVto_seguro(rs.getDate("VTO_SEGURO"));
                eq.setVto_otros1(rs.getDate("VTO_OTROS1"));
                eq.setVto_otros2(rs.getDate("VTO_OTROS2"));
                eq.setVto_otros3(rs.getDate("VTO_OTROS3"));
                eq.setDescripcion_vto1(rs.getString("DESCRIPCION_VTO1"));
                eq.setDescripcion_vto2(rs.getString("DESCRIPCION_VTO2"));
                eq.setDescripcion_vto3(rs.getString("DESCRIPCION_VTO3"));
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los equipos.\n");
        }
        return eq;

    }

    public ArrayList<Equipos> buscar(String consulta) {
        String query = null;
        ArrayList<Equipos> equipos = new ArrayList<Equipos>();
        try {
            query = "select distinct * from equipos where EQUIPO like '%"+consulta+"%' or DOMINIO like '%"+consulta+"%'"
                    + " or N_INTERNO like '%"+consulta+"%' or MARCA like '%"+consulta+"%'"
                    + " or MODELO like '%"+consulta+"%' or AÑO like '%"+consulta+"%'";
            
            
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
                eq.setVto_vtv(rs.getDate("VTO_VTV"));
                eq.setVto_seguro(rs.getDate("VTO_SEGURO"));
                eq.setVto_otros1(rs.getDate("VTO_OTROS1"));
                eq.setVto_otros2(rs.getDate("VTO_OTROS2"));
                eq.setVto_otros3(rs.getDate("VTO_OTROS3"));
                eq.setDescripcion_vto1(rs.getString("DESCRIPCION_VTO1"));
                eq.setDescripcion_vto2(rs.getString("DESCRIPCION_VTO2"));
                eq.setDescripcion_vto3(rs.getString("DESCRIPCION_VTO3"));
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
                eq.setVto_vtv(rs.getDate("VTO_VTV"));
                eq.setVto_seguro(rs.getDate("VTO_SEGURO"));
                eq.setVto_otros1(rs.getDate("VTO_OTROS1"));
                eq.setVto_otros2(rs.getDate("VTO_OTROS2"));
                eq.setVto_otros3(rs.getDate("VTO_OTROS3"));
                eq.setDescripcion_vto1(rs.getString("DESCRIPCION_VTO1"));
                eq.setDescripcion_vto2(rs.getString("DESCRIPCION_VTO2"));
                eq.setDescripcion_vto3(rs.getString("DESCRIPCION_VTO3"));
               equipos.add(eq);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los equipos.\n");
        }
        return equipos;

    }
    public ArrayList<ItemAlarmaBean> getAlarmasEquiposForReport(java.util.Date inicio, java.util.Date limite){
        //TODO: arreglar esto
        ArrayList<ItemAlarma> alarmas = new ArrayList<ItemAlarma>();
        ArrayList<ItemAlarmaBean> itemes = new ArrayList<ItemAlarmaBean>();
        alarmas = getAlarmasEquipos(inicio, limite);
        for(ItemAlarma item: alarmas){
            itemes.add(new ItemAlarmaBean(item.getFecha(), null/*item.getFecha()*/, item.getMensaje(), "Alarma automática por vencimiento."));
        }
        return itemes;
    }
    
    public ArrayList<ItemAlarma> getAlarmasEquipos(java.util.Date inicio, java.util.Date fin){
        ArrayList<ItemAlarma> alarmas = new ArrayList<ItemAlarma>();
        try {
//            String query = "select distinct * from equipos where "
//                    + "(VTO_VTV >= ? and VTO_VTV <= ?) or "
//                    + "(VTO_SEGURO >= ? and VTO_SEGURO <= ?) or "
//                    + "(VTO_OTROS1 >= ? and VTO_OTROS1 <= ?) or "
//                    + "(VTO_OTROS2 >= ? and VTO_OTROS2 <= ?) or "
//                    + "(VTO_OTROS3 >= ? and VTO_OTROS3 <= ?)";
            String query = "select distinct * from equipos where "
                    + "VTO_VTV <= ? or "
                    + "VTO_SEGURO <= ? or "
                    + "VTO_OTROS1 <= ? or "
                    + "VTO_OTROS2 <= ? or "
                    + "VTO_OTROS3 <= ?";
            PreparedStatement ps = conector.prepareStatement(query);
            //java.sql.Date inicioDB = FechaUtil.getFechatoDB(inicio);
            java.sql.Date finDB = FechaUtil.getFechatoDB(fin);
            //ps.setDate(1, inicioDB);
            ps.setDate(1, finDB);
            //ps.setDate(3, inicioDB);
            ps.setDate(2, finDB);
            //ps.setDate(5, inicioDB);
            ps.setDate(3, finDB);
            //ps.setDate(7, inicioDB);
            ps.setDate(4, finDB);
            //ps.setDate(9, inicioDB);
            ps.setDate(5, finDB);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                java.util.Date vt = FechaUtil.getFechaFromDB(rs.getDate("VTO_VTV"));
                
                if(vt!=null && vt.before(fin)){ //vt se encuentra en el rango
                    ItemAlarma al = new ItemAlarma();
                    if (vt.before(inicio)) {
                        al.setTipo(2);
                        al.setMensaje("La VTV del equipo "+rs.getString("N_INTERNO")+" se venció el "+FechaUtil.getFecha(vt));
                    } else {
                        al.setMensaje("La VTV del equipo "+rs.getString("N_INTERNO")+" vence el "+FechaUtil.getFecha(vt));
                        if(vt.equals(inicio)) {
                            al.setTipo(1);
                        }else {
                            al.setTipo(0);
                        }
                    }                   
                    al.setFecha(vt);                   
                    alarmas.add(al);
                }
                
                java.util.Date seg = FechaUtil.getFechaFromDB(rs.getDate("VTO_SEGURO"));
                if(seg != null && seg.before(fin)){ //seg se encuentra en el rango
                    ItemAlarma al = new ItemAlarma();
                    if (seg.before(inicio)) {
                        al.setTipo(2);
                        al.setMensaje("El seguro del equipo "+rs.getString("N_INTERNO")+" se venció el "+FechaUtil.getFecha(seg));
                    } else {
                        al.setMensaje("El seguro del equipo "+rs.getString("N_INTERNO")+" vence el "+FechaUtil.getFecha(seg));
                        if(seg.equals(inicio)) {
                            al.setTipo(1);
                        }else {
                            al.setTipo(0);
                        }
                    }       
                    al.setFecha(seg);
                    alarmas.add(al);
                }
                
                java.util.Date otros1 = FechaUtil.getFechaFromDB(rs.getDate("VTO_OTROS1"));
                if(otros1 != null && otros1.before(fin)){ //seg se encuentra en el rango
                    ItemAlarma al = new ItemAlarma();
                    if (otros1.before(inicio)) {
                        al.setTipo(2);
                        al.setMensaje(rs.getString("DESCRIPCION_VTO1")+" del equipo "
                                +rs.getString("N_INTERNO")+" se venció el "+FechaUtil.getFecha(otros1));
                    } else {
                        al.setMensaje(rs.getString("DESCRIPCION_VTO1")+" del equipo "
                                +rs.getString("N_INTERNO")+" vence el "+FechaUtil.getFecha(otros1));
                        if(otros1.equals(inicio)) {
                            al.setTipo(1);
                        }else {
                            al.setTipo(0);
                        }
                    }                   
                    al.setFecha(otros1);                 
                    alarmas.add(al);
                }
                
                java.util.Date otros2 = FechaUtil.getFechaFromDB(rs.getDate("VTO_OTROS2"));
                if(otros2 != null && otros2.before(fin)){ //seg se encuentra en el rango
                    ItemAlarma al = new ItemAlarma();
                    if (otros2.before(inicio)) {
                        otros2.setTime(2);
                        al.setMensaje(rs.getString("DESCRIPCION_VTO2")+" del equipo "
                            +rs.getString("N_INTERNO")+" se venció el "+FechaUtil.getFecha(otros2));
                    } else {
                        al.setMensaje(rs.getString("DESCRIPCION_VTO2")+" del equipo "
                            +rs.getString("N_INTERNO")+" vence el "+FechaUtil.getFecha(otros2));
                        if(otros2.equals(inicio)) {
                            al.setTipo(1);
                        }else {
                            al.setTipo(0);
                        }
                    }
                    al.setFecha(otros2);                   
                    alarmas.add(al);
                }
                
                java.util.Date otros3 = FechaUtil.getFechaFromDB(rs.getDate("VTO_OTROS3"));
                if(otros3 != null && otros3.after(inicio) && otros3.before(fin)){ //seg se encuentra en el rango
                    ItemAlarma al = new ItemAlarma();
                    if (otros3.before(inicio)) {
                        otros3.setTime(2);
                        al.setMensaje(rs.getString("DESCRIPCION_VTO3")+" del equipo "
                            +rs.getString("N_INTERNO")+" se venció el "+FechaUtil.getFecha(otros3));
                    } else {
                        al.setMensaje(rs.getString("DESCRIPCION_VTO3")+" del equipo "
                            +rs.getString("N_INTERNO")+" vence el "+FechaUtil.getFecha(otros3));
                        if(otros3.equals(inicio)) {
                            al.setTipo(1);
                        }else {
                            al.setTipo(0);
                        }
                    }
                    al.setFecha(otros3);                 
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
