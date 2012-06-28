/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Modelo.OrdenTrabajo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author matuu
 */
public class OrdenTrabajoDAO {
    
    Connection conector;

    public OrdenTrabajoDAO() {}
    

    public void conectar() {
        try {
            conector = ConectorDB.getConector();
        } catch (Exception ex) {
        }
    }
    
    public int guardar(OrdenTrabajo ot) {
        int r = 0;
        String query = null;

        try {
            //OrdenTrabajoID 	Fecha 	KmHs 	DetalleServicio 	NInterno 	
            // ManoDeObra 	AperturaFecha 	CierreFecha 	Mantenimiento
            query = "insert into orden_trabajo (FECHA, KM, HS, DETALLESERVICIO, NINTERNOID, "
                    + " MANODEOBRA, APERTURAFECHA, CIERREFECHA, MANTENIMIENTO, NINTERNO, SOLICITANTE) "
                    + "values ( ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?)";
            PreparedStatement ps = conector.prepareStatement(query);
            if (ot.getFecha() != null) {
                ps.setDate(1, new Date(ot.getFecha().getTime()));
            } else {
                ps.setDate(1, null);
            }
            ps.setString(2, ot.getKm());
            ps.setString(3, ot.getHs());
            ps.setString(4, ot.getDetalleServicio());
            ps.setInt(5, ot.getnInternoID());
            ps.setString(6, ot.getManoDeObra());
            if(ot.getFechaApertura()!=null){
                ps.setDate(7, new Date(ot.getFechaApertura().getTime()));
            }else{
                ps.setString(7, null);
            }
            if(ot.getFechaCierre()!=null){
                ps.setDate(8, new Date(ot.getFechaCierre().getTime()));
            }else{
                ps.setString(8, null);
            }
            ps.setString(9, ot.getMantenimiento());
            ps.setString(10, ot.getnInterno());
            ps.setString(11, ot.getSolicitante());
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
    
    public int modificar(OrdenTrabajo ot) {
        int r = 0;
        String query = null;

        try {
            //OrdenTrabajoID 	Fecha 	KmHs 	DetalleServicio 	NInterno 	
            // ManoDeObra 	AperturaFecha 	CierreFecha 	Mantenimiento
            query = "update orden_trabajo set FECHA=?, KM=?, HS=?, DETALLESERVICIO=?, NINTERNOID=?, "
                    + "MANODEOBRA=?, APERTURAFECHA=?, CIERREFECHA=?, MANTENIMIENTO=?, NINTERNO =?, SOLICITANTE=? "
                    + "where ORDENTRABAJOID =" +ot.getOrdenTrabajoID();
            PreparedStatement ps = conector.prepareStatement(query);
            if (ot.getFecha() != null) {
                ps.setDate(1, new Date(ot.getFecha().getTime()));
            } else {
                ps.setDate(1, null);
            }
            ps.setString(2, ot.getKm());
            ps.setString(3, ot.getHs());
            ps.setString(4, ot.getDetalleServicio());
            ps.setInt(5, ot.getnInternoID());
            ps.setString(6, ot.getManoDeObra());
            if(ot.getFechaApertura()!=null){
                ps.setDate(7, new Date(ot.getFechaApertura().getTime()));
            }else{
                ps.setString(7, null);
            }
            if(ot.getFechaCierre()!=null){
                ps.setDate(8, new Date(ot.getFechaCierre().getTime()));
            }else{
                ps.setString(8, null);
            }
            ps.setString(9, ot.getMantenimiento());
            ps.setString(10, ot.getnInterno());
            ps.setString(11, ot.getSolicitante());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                r = generatedKeys.getInt(1);
            }else{
                r= ot.getOrdenTrabajoID();
                
            }
            generatedKeys.close();
            ps.close();

        } catch (SQLException ex) {
            r = 0;
        }
        return r;
    }
    
    public boolean borrar(OrdenTrabajo ot) {

        boolean r =false;
        try {
            
            String query = "delete from orden_trabajo where OrdenTrabajoID = ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, ot.getOrdenTrabajoID());
            
            
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
    
    public OrdenTrabajo existeParaEquipo (int equipoID) {

        OrdenTrabajo ot =new OrdenTrabajo();
        try {
            
            String query = "select * from orden_trabajo where NinternoID = ? and cierreFecha is null order by fecha desc";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, equipoID);
      
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                ot.setOrdenTrabajoID(rs.getInt("ORDENTRABAJOID"));
                ot.setFecha(rs.getDate("FECHA"));
                ot.setKm(rs.getString("KM"));
                ot.setHs(rs.getString("HS"));
                ot.setDetalleServicio(rs.getString("DETALLESERVICIO"));
                ot.setnInternoID(rs.getInt("NINTERNOID"));
                ot.setManoDeObra(rs.getString("MANODEOBRA"));
                ot.setFechaApertura(rs.getDate("APERTURAFECHA"));
                ot.setFechaCierre(rs.getDate("CIERREFECHA"));
                ot.setMantenimiento(rs.getString("MANTENIMIENTO"));
                ot.setnInterno(rs.getString("NINTERNO"));
                ot.setSolicitante(rs.getString("SOLICITANTE"));
            }
            
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            ot.setOrdenTrabajoID(-1);
            return ot;
        }

        return ot;
    }
    
   public ArrayList<OrdenTrabajo> cargarTodos() {
        String query = null;
        ArrayList<OrdenTrabajo> ordenes = new ArrayList<OrdenTrabajo>();
        try {
            query = "select * from orden_trabajo";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //OrdenTrabajoID 	Fecha 	KmHs 	DetalleServicio 	NInterno 	
                // ManoDeObra 	AperturaFecha 	CierreFecha 	Mantenimiento
                OrdenTrabajo ot = new OrdenTrabajo();
                ot.setOrdenTrabajoID(rs.getInt("ORDENTRABAJOID"));
                ot.setFecha(rs.getDate("FECHA"));
                ot.setKm(rs.getString("KM"));
                ot.setHs(rs.getString("HS"));
                ot.setDetalleServicio(rs.getString("DETALLESERVICIO"));
                ot.setnInternoID(rs.getInt("NINTERNOID"));
                ot.setManoDeObra(rs.getString("MANODEOBRA"));
                ot.setFechaApertura(rs.getDate("APERTURAFECHA"));
                ot.setFechaCierre(rs.getDate("CIERREFECHA"));
                ot.setMantenimiento(rs.getString("MANTENIMIENTO"));
                ot.setnInterno(rs.getString("NINTERNO"));
                ot.setSolicitante(rs.getString("SOLICITANTE"));
                ordenes.add(ot);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar las ordenes de trabajo: " +ex.getMessage()+ "\n");
        }
        return ordenes;

    } 
   public ArrayList<OrdenTrabajo> busqueda(String filtro) {
        String query = null;
        ArrayList<OrdenTrabajo> ordenes = new ArrayList<OrdenTrabajo>();
        try {
            query = "select distinct * from orden_trabajo where FECHA like '%"+ filtro+"%' "
                    + "or NINTERNO like '%"+filtro+"%' or NINTERNOID like '%"+filtro+"%' or MANODEOBRA like '%" +
                    filtro+"%' or  MANTENIMIENTO like '%"+filtro+"%' or APERTURAFECHA like '%"
                    +filtro+"%' or CIERREFECHA like '%"+filtro+"%' or SOLICITANTE like '%"+filtro+"%'";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //OrdenTrabajoID 	Fecha 	KmHs 	DetalleServicio 	NInterno 	
                // ManoDeObra 	AperturaFecha 	CierreFecha 	Mantenimiento
                OrdenTrabajo ot = new OrdenTrabajo();
                ot.setOrdenTrabajoID(rs.getInt("ORDENTRABAJOID"));
                ot.setFecha(rs.getDate("FECHA"));
                ot.setKm(rs.getString("KM"));
                ot.setHs(rs.getString("HS"));
                ot.setDetalleServicio(rs.getString("DETALLESERVICIO"));
                ot.setnInternoID(rs.getInt("NINTERNOID"));
                ot.setManoDeObra(rs.getString("MANODEOBRA"));
                ot.setFechaApertura(rs.getDate("APERTURAFECHA"));
                ot.setFechaCierre(rs.getDate("CIERREFECHA"));
                ot.setMantenimiento(rs.getString("MANTENIMIENTO"));
                ot.setnInterno(rs.getString(("NINTERNO")));
                ot.setSolicitante(rs.getString("SOLICITANTE"));
                ordenes.add(ot);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar las ordenes de trabajo: " +ex.getMessage()+ "\n");
        }
        return ordenes;

    } 
    
}
