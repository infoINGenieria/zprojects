/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Situacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author matuu
 */
public class SituacionDAO {
    Connection conector;

    public SituacionDAO() {
    }

    public void conectar() {
        try {
            conector = ConectorDB.getConector();
        } catch (Exception ex) {
        }
    }
    
    public int guardar(Situacion sit) {
        int r = -1;
        String query = null;

        try {
            if(sit.getId()!=0){
                query = "update situacion set situacion=? where ID ="+ sit.getId();
            }else{
                query = "insert into situacion (situacion) values (?) ";
            
            }
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setString(1, sit.getSituacion());
            
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

    
 

    public ArrayList<Situacion> cargarTodos() {
        String query = null;
        ArrayList<Situacion> fcs = new ArrayList<Situacion>();
        try {
            query = "select * from situacion";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Situacion sit = new Situacion();
                sit.setId(rs.getInt("ID"));
                sit.setSituacion(rs.getString("SITUACION"));
                fcs.add(sit);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los Obras.\n");
        }
        return fcs;

    }
}
