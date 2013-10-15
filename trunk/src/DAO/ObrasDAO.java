/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Obras;
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
public class ObrasDAO {
     Connection conector;

    public ObrasDAO() {
    }

    public void conectar() {
        try {
            conector = ConectorDB.getConector();
        } catch (Exception ex) {
        }
    }
    
    public int guardar(Obras obra) {
        int r = -1;
        String query = null;

        try {
        
            query = "insert into obras (CODIGO, OBRA, CONTRATO, COMITENTE,"
                    + " CUIT, LUGAR, PLAZO, FECHA_INICIO, RESPONSABLE, TIENE_COMIDA, "
                    + "TIENE_VIANDA, TIENE_DESARRAIGO, LIMITE_VIANDA_DOBLE, "
                    + "TIENE_REGISTRO, TIENE_EQUIPO) values "
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setString(1, obra.getCodigo());
            ps.setString(2, obra.getObra());
            ps.setString(3, obra.getContrato());
            ps.setString(4, obra.getComitente());
            ps.setString(5, obra.getCuit());
            ps.setString(6, obra.getLugar());
            ps.setString(7, obra.getPlazo());
            if (obra.getFecha_inicio() != null) {
                ps.setDate(8, new Date(obra.getFecha_inicio().getTime()));
            } else {
                ps.setDate(8, null);
            }
            ps.setString(9,obra.getResponsable());
            ps.setBoolean(10, obra.isTieneComida());
            ps.setBoolean(11, obra.isTieneVianda());
            ps.setBoolean(12, obra.isTieneDesarraigo());
            ps.setDouble(13, obra.getLimiteViandaDoble());
            ps.setBoolean(14, obra.isTieneRegistro());
            ps.setBoolean(15, obra.isTieneEquipo());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            r=1;
            if (generatedKeys.next()) {
                r = generatedKeys.getInt(1);
            }
            generatedKeys.close();
            ps.close();

        } catch (SQLException ex) {
            r = -1;
        } catch (Exception ex){
            r=0;
        }
        return r;
    }

    public int modificar(Obras obra) {

        int r = -1;
        try {
            String query = "update obras set CODIGO=?, OBRA=?, CONTRATO=?, COMITENTE=?,"
                    + " CUIT=?, LUGAR=?, PLAZO=?, FECHA_INICIO=?, RESPONSABLE=?,"
                    + " TIENE_COMIDA=?, TIENE_VIANDA=?, TIENE_DESARRAIGO=?,"
                    + " LIMITE_VIANDA_DOBLE=? , TIENE_REGISTRO=?, "
                    + " TIENE_EQUIPO=? where ID = ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setString(1, obra.getCodigo());
            ps.setString(2, obra.getObra());
            ps.setString(3, obra.getContrato());
            ps.setString(4, obra.getComitente());
            ps.setString(5, obra.getCuit());
            ps.setString(6, obra.getLugar());
            ps.setString(7, obra.getPlazo());
            if (obra.getFecha_inicio() != null) {
                ps.setDate(8, new Date(obra.getFecha_inicio().getTime()));
            } else {
                ps.setDate(8, null);
            }
            ps.setString(9, obra.getResponsable());
            ps.setBoolean(10, obra.isTieneComida());
            ps.setBoolean(11, obra.isTieneVianda());
            ps.setBoolean(12, obra.isTieneDesarraigo());
            ps.setDouble(13, obra.getLimiteViandaDoble());
            ps.setBoolean(14, obra.isTieneRegistro());
            ps.setBoolean(15, obra.isTieneEquipo());
            ps.setInt(16, obra.getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            r=0;
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            r = -1;
        }

        return r;
    }
 

    public ArrayList<Obras> cargarTodos() {
        String query = null;
        ArrayList<Obras> obras = new ArrayList<Obras>();
        try {
            query = "select * from obras";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            //ID CODIGO OBRA CONTRATO COMITENTE CUIT LUGAR PLAZO FECHA_INICIO
            while (rs.next()) {
                Obras obra = new Obras();
                obra.setId(rs.getInt("ID"));
                obra.setCodigo(rs.getString("CODIGO"));
                obra.setObra(rs.getString("OBRA"));
                obra.setContrato(rs.getString("CONTRATO"));
                obra.setComitente(rs.getString("COMITENTE"));
                obra.setCuit(rs.getString("CUIT"));
                obra.setLugar(rs.getString("LUGAR"));
                obra.setPlazo(rs.getString("PLAZO"));
                obra.setFecha_inicio(rs.getDate("FECHA_INICIO"));
                obra.setResponsable(rs.getString("RESPONSABLE"));
                obra.setTieneComida(rs.getBoolean(("TIENE_COMIDA")));
                obra.setTieneVianda(rs.getBoolean(("TIENE_VIANDA")));
                obra.setTieneDesarraigo(rs.getBoolean(("TIENE_DESARRAIGO")));
                obra.setLimiteViandaDoble(rs.getDouble(("LIMITE_VIANDA_DOBLE")));
                obra.setTieneRegistro(rs.getBoolean("TIENE_REGISTRO"));
                obra.setTieneEquipo(rs.getBoolean("TIENE_EQUIPO"));
                obras.add(obra);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los Obras.\n");
        }
        return obras;

    }

    
    public ArrayList<Obras> cargarObrasParteDiarioMasivo() {
        String query = null;
        ArrayList<Obras> obras = new ArrayList<Obras>();
        try {
            query = "select * from obras where tiene_registro is false and tiene_equipo is false";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            //ID CODIGO OBRA CONTRATO COMITENTE CUIT LUGAR PLAZO FECHA_INICIO
            while (rs.next()) {
                Obras obra = new Obras();
                obra.setId(rs.getInt("ID"));
                obra.setCodigo(rs.getString("CODIGO"));
                obra.setObra(rs.getString("OBRA"));
                obra.setContrato(rs.getString("CONTRATO"));
                obra.setComitente(rs.getString("COMITENTE"));
                obra.setCuit(rs.getString("CUIT"));
                obra.setLugar(rs.getString("LUGAR"));
                obra.setPlazo(rs.getString("PLAZO"));
                obra.setFecha_inicio(rs.getDate("FECHA_INICIO"));
                obra.setResponsable(rs.getString("RESPONSABLE"));
                obra.setTieneComida(rs.getBoolean(("TIENE_COMIDA")));
                obra.setTieneVianda(rs.getBoolean(("TIENE_VIANDA")));
                obra.setTieneDesarraigo(rs.getBoolean(("TIENE_DESARRAIGO")));
                obra.setLimiteViandaDoble(rs.getDouble(("LIMITE_VIANDA_DOBLE")));
                obra.setTieneRegistro(rs.getBoolean("TIENE_REGISTRO"));
                obra.setTieneEquipo(rs.getBoolean("TIENE_EQUIPO"));
                obras.add(obra);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los Obras.\n");
        }
        return obras;

    }
   
    public boolean borrar(Obras obra) {

        boolean r =false;
        try {
            
            String query = "delete from obras where ID = ? ";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, obra.getId());
                       
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
    
    public Obras findById(int id) {
        String query = null;
        Obras obra = new Obras();
        try {
            query = "select * from obras where ID ="+id;
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                obra.setId(rs.getInt("ID"));
                obra.setCodigo(rs.getString("CODIGO"));
                obra.setObra(rs.getString("OBRA"));
                obra.setContrato(rs.getString("CONTRATO"));
                obra.setComitente(rs.getString("COMITENTE"));
                obra.setCuit(rs.getString("CUIT"));
                obra.setLugar(rs.getString("LUGAR"));
                obra.setPlazo(rs.getString("PLAZO"));
                obra.setFecha_inicio(rs.getDate("FECHA_INICIO"));
                obra.setResponsable(rs.getString("RESPONSABLE"));
                obra.setTieneComida(rs.getBoolean(("TIENE_COMIDA")));
                obra.setTieneVianda(rs.getBoolean(("TIENE_VIANDA")));
                obra.setTieneDesarraigo(rs.getBoolean(("TIENE_DESARRAIGO")));
                obra.setLimiteViandaDoble(rs.getDouble(("LIMITE_VIANDA_DOBLE")));
                obra.setTieneRegistro(rs.getBoolean("TIENE_REGISTRO"));
                obra.setTieneEquipo(rs.getBoolean("TIENE_EQUIPO"));
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar la Obra\n");
        }
        return obra;

    }
    
    
    
    
    
}
