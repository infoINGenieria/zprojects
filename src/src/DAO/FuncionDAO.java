/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.EntidadAbstracta;
import Modelo.Funcion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author matuu
 */
public class FuncionDAO extends AbstractDAO{
    
    public FuncionDAO() {
    }
    
    @Override
    public int guardar(EntidadAbstracta funcion) {
        int r = -1;
        String query = null;
        Funcion fc = (Funcion)funcion;
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

    @Override
    public ArrayList<EntidadAbstracta> cargarTodos() {
        String query = null;
        ArrayList<EntidadAbstracta> fcs = new ArrayList<EntidadAbstracta>();
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

    @Override
    public int modificar(EntidadAbstracta entidad) {
        return guardar(entidad);
    }

    @Override
    public boolean eliminar(EntidadAbstracta entidad) {
        boolean r =false;
        try {
            String query = "delete from funcion where id = ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, entidad.getId());
            int rs = ps.executeUpdate();
            if (rs > 0) {
                r = true;
            }
            ps.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return r;
    }

    @Override
    public ArrayList<EntidadAbstracta> filtrarPorTexto(String text) {
        String query = null;
        ArrayList<EntidadAbstracta> fcs = new ArrayList<EntidadAbstracta>();
        try {
            query = "select * from funcion where FUNCION like ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setString(1, "%"+text+"%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Funcion func = new Funcion();
                func.setId(rs.getInt("ID"));
                func.setFuncion(rs.getString("FUNCION"));
                fcs.add(func);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar las FUNCION\n");
        }
        return fcs;
    }
}
