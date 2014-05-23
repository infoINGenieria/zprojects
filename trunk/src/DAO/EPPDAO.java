/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.EPP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author m4tuu
 */
public class EPPDAO {
    
    Connection conector;

    public EPPDAO() {
    }

    public void conectar() {
        try {
            conector = ConectorDB.getConector();
        } catch (Exception ex) {
        }
    }
    
    public int guardar(EPP epp) {
        int r = -1;
        String query = null;

        try {
            if(epp.getId()!=0){
                query = "update epp set NOMBRE=?, MEDIDA=? where ID ="+ epp.getId();
            }else{
                query = "insert into epp (NOMBRE, MEDIDA) values (?,?) ";
            
            }
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setString(1, epp.getNombre());
            ps.setString(2, epp.getMedida());
            
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

    
 

    public ArrayList<EPP> cargarTodos() {
        String query = null;
        ArrayList<EPP> fcs = new ArrayList<EPP>();
        try {
            query = "select * from epp";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                EPP epp = new EPP();
                epp.setId(rs.getInt("ID"));
                epp.setNombre(rs.getString("NOMBRE"));
                epp.setMedida(rs.getString("MEDIDA"));
                fcs.add(epp);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los Obras.\n");
        }
        return fcs;

    }
    
    public boolean borrar(EPP epp) {

        boolean r =false;
        try {
            
            String query = "delete from epp where ID = ? ";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, epp.getId());
                       
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
    
    public EPP findById(int id) {
        String query = null;
        EPP epp = new EPP();
        try {
            query = "select * from epp where ID = "+id;
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                epp.setId(rs.getInt("ID"));
                epp.setNombre(rs.getString("NOMBRE"));
                epp.setMedida(rs.getString("MEDIDA"));
                
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar el EPP\n");
        }
        return epp;

    }
}
