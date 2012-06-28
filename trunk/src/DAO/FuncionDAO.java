/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Funcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author matuu
 */
public class FuncionDAO {
    Connection conector;

    public FuncionDAO() {
    }

    public void conectar() {
        try {
            conector = ConectorDB.getConector();
        } catch (Exception ex) {
        }
    }
    
    public int guardar(Funcion fc) {
        int r = -1;
        String query = null;

        try {
            if(fc.getId()!=0){
                query = "update funcion set FUNCION=? where ID ="+ fc.getId();
            }else{
                query = "insert into funcion (FUNCION) values (?) ";
            
            }
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setString(1, fc.getFuncion());
            
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

    
 

    public ArrayList<Funcion> cargarTodos() {
        String query = null;
        ArrayList<Funcion> fcs = new ArrayList<Funcion>();
        try {
            query = "select * from funcion";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            //ID CODIGO OBRA CONTRATO COMITENTE CUIT LUGAR PLAZO FECHA_INICIO
            while (rs.next()) {
                Funcion fc = new Funcion();
                fc.setId(rs.getInt("ID"));
                fc.setFuncion(rs.getString("FUNCION"));
                fcs.add(fc);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los Obras.\n");
        }
        return fcs;

    }
}
