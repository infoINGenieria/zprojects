/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.ItemAlarma;
import Modelo.Operario;
import Utils.FechaUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author matuu
 */
public class OperarioDAO {
    Connection conector;

    public OperarioDAO() {
    }

    public void conectar() {
        try {
            conector = ConectorDB.getConector();
        } catch (Exception ex) {
        }
    }
    
    public int guardar(Operario op) {
        int r = -1;
        String query = null;

        try {
        
            query = "insert into operarios (N_LEGAJO, NOMBRE, CUIL, OBSERVACIONES, FUNCION, DESARRAIGO, VTO_CARNET) values "
                    + "(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setString(1, op.getN_legajo());
            ps.setString(2, op.getNombre());
            ps.setString(3, op.getCuil());
            ps.setString(4, op.getObservaciones());
            ps.setInt(5, op.getFuncion());
            ps.setBoolean(6, op.isDesarraigo());
            ps.setDate(7, FechaUtil.getFechatoDB(op.getVto_carnet()));
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
        }
        return r;
    }

    public int modificar(Operario op) {

        int r = -1;
        try {
            String query = "update operarios set N_LEGAJO=?, NOMBRE=?, CUIL=?, "
                    + "OBSERVACIONES=?, FUNCION=?, DESARRAIGO=?, VTO_CARNET =? where ID = ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setString(1, op.getN_legajo());
            ps.setString(2, op.getNombre());
            ps.setString(3, op.getCuil());
            ps.setString(4, op.getObservaciones());
            ps.setInt(5, op.getFuncion());
            ps.setBoolean(6, op.isDesarraigo());
            ps.setDate(7, FechaUtil.getFechatoDB(op.getVto_carnet()));
            ps.setInt(8, op.getId());
            
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
 

    public ArrayList<Operario> cargarTodos() {
        String query = null;
        ArrayList<Operario> empleados = new ArrayList<Operario>();
        try {
            query = "select * from operarios order by nombre asc";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            //ID N_LEGAJO, NOMBRE, CUIL, OBSERVACIONES
            while (rs.next()) {
                Operario op = new Operario();
                op.setId(rs.getInt("ID"));
                op.setN_legajo(rs.getString("N_LEGAJO"));
                op.setNombre(rs.getString("NOMBRE"));
                op.setCuil(rs.getString("CUIL"));
                op.setObservaciones(rs.getString("OBSERVACIONES"));
                op.setFuncion(rs.getInt("FUNCION"));
                op.setDesarraigo(rs.getBoolean("DESARRAIGO"));
                op.setVto_carnet(rs.getDate("VTO_CARNET"));
                empleados.add(op);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los Obras.\n");
        }
        return empleados;

    }

   public ArrayList<Operario> buscar(String consulta) {
        String query = null;
        ArrayList<Operario> empleados = new ArrayList<Operario>();
        try {
            query="select * from operarios where NOMBRE like '%"+consulta+"%' or "
                    + "CUIL like '%"+consulta+"%' or N_LEGAJO like '%"+consulta+"%'"
                    + " order by nombre asc";
            
            PreparedStatement ps = conector.prepareStatement(query);
            
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Operario op = new Operario();
                op.setId(rs.getInt("ID"));
                op.setN_legajo(rs.getString("N_LEGAJO"));
                op.setNombre(rs.getString("NOMBRE"));
                op.setCuil(rs.getString("CUIL"));
                op.setObservaciones(rs.getString("OBSERVACIONES"));
                op.setFuncion(rs.getInt("FUNCION"));
                op.setDesarraigo(rs.getBoolean("DESARRAIGO"));
                op.setVto_carnet(rs.getDate("VTO_CARNET"));
                empleados.add(op);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los Obras.\n");
        }
        return empleados;

    }
   
   public Operario buscar(int id) {
        String query = null;
        Operario op = new Operario();
        try {
            query="select * from operarios where ID = "+id;
            
            PreparedStatement ps = conector.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                op.setId(rs.getInt("ID"));
                op.setN_legajo(rs.getString("N_LEGAJO"));
                op.setNombre(rs.getString("NOMBRE"));
                op.setCuil(rs.getString("CUIL"));
                op.setObservaciones(rs.getString("OBSERVACIONES"));
                op.setFuncion(rs.getInt("FUNCION"));
                op.setDesarraigo(rs.getBoolean("DESARRAIGO"));
                op.setVto_carnet(rs.getDate("VTO_CARNET"));
                
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar el Operario.\n");
        }
        return op;

    }
    
    public boolean borrar(Operario op) {

        boolean r =false;
        try {
            
            String query = "delete from operarios where ID = ? ";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, op.getId());
                       
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
    
    
    public ArrayList<ItemAlarma> getAlarmasEquipos(java.util.Date inicio, java.util.Date fin){
        ArrayList<ItemAlarma> alarmas = new ArrayList<ItemAlarma>();
        try {
            String query = "select distinct * from operarios where VTO_CARNET "
                    + ">= ? and VTO_CARNET <= ? ";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setDate(1, FechaUtil.getFechatoDB(inicio));
            ps.setDate(2, FechaUtil.getFechatoDB(fin));
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                Date vt = rs.getDate("VTO_CARNET");
                if(vt!=null && vt.compareTo(inicio)>=0 && vt.compareTo(fin)<=0){ //vt se encuentra en el rango
                    ItemAlarma al = new ItemAlarma();
                    if(vt.equals(inicio)) {
                        al.setTipo(1);
                    }else {
                        al.setTipo(0);
                    }
                    al.setFecha(vt);
                    al.setMensaje("El registro de conducir del empleado "+rs.getString("NOMBRE")+" vence el "+FechaUtil.getFecha(vt));
                    alarmas.add(al);
                }
                
                
           
            }
            rs.close();
            ps.close();
            
        }catch (SQLException ex){
            System.out.print("Falló al cargar las alarmas de los empleados.\n");
        }
        return alarmas;
    }
}
