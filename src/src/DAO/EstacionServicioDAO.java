/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.EstacionServicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author matuu
 */
public class EstacionServicioDAO {
    Connection conector;

    public EstacionServicioDAO() {
    }

    public void conectar() {
        try {
            conector = ConectorDB.getConector();
        } catch (Exception ex) {
        }
    }
    
    public int guardar(EstacionServicio es) {
        int r = -1;
        String query = null;

        try {
            if(es.getEstacionServicioID() !=0){
                query = "update est_servicio set NOMBRE=? where ID ="+ es.getEstacionServicioID();
            }else{
                query = "insert into est_servicio (NOMBRE) values (?) ";
            
            }
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setString(1, es.getNombre());
            
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            
            if (generatedKeys.next()) {
                r = generatedKeys.getInt(1);
            }
            
            generatedKeys.close();
            ps.close();

        } catch (SQLException ex) {
            r = -1;
        }
        return r;
    }

    
 

    public ArrayList<EstacionServicio> cargarTodos2Combo() {
        String query = null;
        ArrayList<EstacionServicio> estaciones = new ArrayList<EstacionServicio>();
        try {
            query = "select * from est_servicio";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            estaciones.add(new EstacionServicio(0, "No cargó"));
            while (rs.next()) {
                EstacionServicio es = new EstacionServicio();
                es.setEstacionServicioID(rs.getInt("ID"));
                es.setNombre(rs.getString("NOMBRE"));
                estaciones.add(es);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los Obras.\n");
        }
        return estaciones;

    }
    
    public ArrayList<EstacionServicio> cargarTodo() {
        String query = null;
        ArrayList<EstacionServicio> estaciones = new ArrayList<EstacionServicio>();
        try {
            query = "select * from est_servicio";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EstacionServicio es = new EstacionServicio();
                es.setEstacionServicioID(rs.getInt("ID"));
                es.setNombre(rs.getString("NOMBRE"));
                estaciones.add(es);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los Obras.\n");
        }
        return estaciones;

    }
    
    
    
    public int reasignarEliminar(EstacionServicio eliminar, EstacionServicio asignar) {
        int r = -1;
        String query = null;

        try {
            conector.setAutoCommit(false);
            query = "update registro_equipo SET IDSERVICIO = ? WHERE IDSERVICIO = ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, asignar.getEstacionServicioID());
            ps.setInt(2, eliminar.getEstacionServicioID());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            
            query = "delete from est_servicio where id = "+eliminar.getEstacionServicioID();
            ps = conector.prepareStatement(query);
            ps.executeUpdate();
            generatedKeys = ps.getGeneratedKeys();
            
            generatedKeys.close();
            ps.close();
            conector.commit();
            r=1;
            conector.setAutoCommit(true);

        } catch (SQLException ex) {
            r = -1;
            try{
                conector.rollback();
            }catch (SQLException e){
                r=-1;
            }
            
        }
       
        return r;
    }
    
}
