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
        
            query = "insert into operarios (N_LEGAJO, NOMBRE, CUIL, OBSERVACIONES, " +
                    "FUNCION, DESARRAIGO, VTO_CARNET, VTO_PSICOFISICO, VTO_CARGAGRAL, "+
                    "VTO_CARGAPELIGROSA, DESCRIPCION_VTO1, DESCRIPCION_VTO2, " +
                    "DESCRIPCION_VTO3, VTO_OTROS1, VTO_OTROS2, VTO_OTROS3) values "
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setString(1, op.getN_legajo());
            ps.setString(2, op.getNombre());
            ps.setString(3, op.getCuil());
            ps.setString(4, op.getObservaciones());
            ps.setInt(5, op.getFuncion());
            ps.setBoolean(6, op.isDesarraigo());
            ps.setDate(7, FechaUtil.getFechatoDB(op.getVto_carnet()));
            ps.setDate(8, FechaUtil.getFechatoDB(op.getVto_psicofisico()));
            ps.setDate(9, FechaUtil.getFechatoDB(op.getVto_cargagral()));
            ps.setDate(10, FechaUtil.getFechatoDB(op.getVto_cargapeligrosa()));
            ps.setString(11, op.getDescripcion_vto1());
            ps.setString(12, op.getDescripcion_vto2());
            ps.setString(13, op.getDescripcion_vto3());
            ps.setDate(14, FechaUtil.getFechatoDB(op.getVto_otros1()));
            ps.setDate(15, FechaUtil.getFechatoDB(op.getVto_otros2()));
            ps.setDate(16, FechaUtil.getFechatoDB(op.getVto_otros3()));
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
                    + "OBSERVACIONES=?, FUNCION=?, DESARRAIGO=?, VTO_CARNET =?, " +
                    "VTO_PSICOFISICO=?, VTO_CARGAGRAL=?, "+
                    "VTO_CARGAPELIGROSA=?, DESCRIPCION_VTO1=?, DESCRIPCION_VTO2=?, " +
                    "DESCRIPCION_VTO3=?, VTO_OTROS1=?, VTO_OTROS2=?, VTO_OTROS3=? where ID = ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setString(1, op.getN_legajo());
            ps.setString(2, op.getNombre());
            ps.setString(3, op.getCuil());
            ps.setString(4, op.getObservaciones());
            ps.setInt(5, op.getFuncion());
            ps.setBoolean(6, op.isDesarraigo());
            ps.setDate(7, FechaUtil.getFechatoDB(op.getVto_carnet()));
            ps.setDate(8, FechaUtil.getFechatoDB(op.getVto_psicofisico()));
            ps.setDate(9, FechaUtil.getFechatoDB(op.getVto_cargagral()));
            ps.setDate(10, FechaUtil.getFechatoDB(op.getVto_cargapeligrosa()));
            ps.setString(11, op.getDescripcion_vto1());
            ps.setString(12, op.getDescripcion_vto2());
            ps.setString(13, op.getDescripcion_vto3());
            ps.setDate(14, FechaUtil.getFechatoDB(op.getVto_otros1()));
            ps.setDate(15, FechaUtil.getFechatoDB(op.getVto_otros2()));
            ps.setDate(16, FechaUtil.getFechatoDB(op.getVto_otros3()));
            ps.setInt(17, op.getId());
            
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
                op.setVto_psicofisico(rs.getDate("VTO_PSICOFISICO"));
                op.setVto_cargagral(rs.getDate("VTO_CARGAGRAL"));
                op.setVto_cargapeligrosa(rs.getDate("VTO_CARGAPELIGROSA"));
                op.setDescripcion_vto1(rs.getString("DESCRIPCION_VTO1"));
                op.setDescripcion_vto2(rs.getString("DESCRIPCION_VTO2"));
                op.setDescripcion_vto3(rs.getString("DESCRIPCION_VTO3"));
                op.setVto_otros1(rs.getDate("VTO_OTROS1"));
                op.setVto_otros2(rs.getDate("VTO_OTROS2"));
                op.setVto_otros3(rs.getDate("VTO_OTROS3"));
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
                op.setVto_psicofisico(rs.getDate("VTO_PSICOFISICO"));
                op.setVto_cargagral(rs.getDate("VTO_CARGAGRAL"));
                op.setVto_cargapeligrosa(rs.getDate("VTO_CARGAPELIGROSA"));
                op.setDescripcion_vto1(rs.getString("DESCRIPCION_VTO1"));
                op.setDescripcion_vto2(rs.getString("DESCRIPCION_VTO2"));
                op.setDescripcion_vto3(rs.getString("DESCRIPCION_VTO3"));
                op.setVto_otros1(rs.getDate("VTO_OTROS1"));
                op.setVto_otros2(rs.getDate("VTO_OTROS2"));
                op.setVto_otros3(rs.getDate("VTO_OTROS3"));
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
                op.setVto_psicofisico(rs.getDate("VTO_PSICOFISICO"));
                op.setVto_cargagral(rs.getDate("VTO_CARGAGRAL"));
                op.setVto_cargapeligrosa(rs.getDate("VTO_CARGAPELIGROSA"));
                op.setDescripcion_vto1(rs.getString("DESCRIPCION_VTO1"));
                op.setDescripcion_vto2(rs.getString("DESCRIPCION_VTO2"));
                op.setDescripcion_vto3(rs.getString("DESCRIPCION_VTO3"));
                op.setVto_otros1(rs.getDate("VTO_OTROS1"));
                op.setVto_otros2(rs.getDate("VTO_OTROS2"));
                op.setVto_otros3(rs.getDate("VTO_OTROS3"));
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
    
    
    public ArrayList<ItemAlarma> getAlarmasOperarios(java.util.Date inicio, java.util.Date fin){
        ArrayList<ItemAlarma> alarmas = new ArrayList<ItemAlarma>();
        try {
            String query = "select distinct * from operarios where "
                    + "VTO_CARNET between ? and ? or "
                    + "VTO_PSICOFISICO between ? and ? or "
                    + "VTO_CARGAGRAL between ? and ? or "
                    + "VTO_CARGAPELIGROSA between ? and ? or "
                    + "VTO_OTROS1 between ? and ? or "
                    + "VTO_OTROS2 between ? and ? or "
                    + "VTO_OTROS3 between ? and ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setDate(1, FechaUtil.getFechatoDB(inicio));
            ps.setDate(2, FechaUtil.getFechatoDB(fin));
            ps.setDate(3, FechaUtil.getFechatoDB(inicio));
            ps.setDate(4, FechaUtil.getFechatoDB(fin));
            ps.setDate(5, FechaUtil.getFechatoDB(inicio));
            ps.setDate(6, FechaUtil.getFechatoDB(fin));
            ps.setDate(7, FechaUtil.getFechatoDB(inicio));
            ps.setDate(8, FechaUtil.getFechatoDB(fin));
            ps.setDate(9, FechaUtil.getFechatoDB(inicio));
            ps.setDate(10, FechaUtil.getFechatoDB(fin));
            ps.setDate(11, FechaUtil.getFechatoDB(inicio));
            ps.setDate(12, FechaUtil.getFechatoDB(fin));
            ps.setDate(13, FechaUtil.getFechatoDB(inicio));
            ps.setDate(14, FechaUtil.getFechatoDB(fin));
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ///Para cada Fecha de vencimiento, creo una alarma
                Date vt = rs.getDate("VTO_CARNET");
                if(vt!=null && vt.compareTo(inicio)>=0 && vt.compareTo(fin)<=0){ //vt se encuentra en el rango
                    ItemAlarma al = new ItemAlarma();
                    if(vt.equals(inicio)) {
                        al.setTipo(1);
                    }else {
                        al.setTipo(0);
                    }
                    al.setFecha(vt);
                    al.setMensaje("Vence el registro de conducir del empleado "+rs.getString("NOMBRE"));
                    alarmas.add(al);
                }
                ///Para cada Fecha de vencimiento, creo una alarma
                Date vtPF = rs.getDate("VTO_PSICOFISICO");
                if(vtPF!=null && vtPF.compareTo(inicio)>=0 && vtPF.compareTo(fin)<=0){ //vtPF se encuentra en el rango
                    ItemAlarma al = new ItemAlarma();
                    if(vtPF.equals(inicio)) {
                        al.setTipo(1);
                    }else {
                        al.setTipo(0);
                    }
                    al.setFecha(vtPF);
                    al.setMensaje("Vence el psicofísico del empleado "+rs.getString("NOMBRE"));
                    alarmas.add(al);
                }
                ///Para cada Fecha de vencimiento, creo una alarma
                Date vtCG = rs.getDate("VTO_CARGAGRAL");
                if(vtCG!=null && vtCG.compareTo(inicio)>=0 && vtCG.compareTo(fin)<=0){ //vtCG se encuentra en el rango
                    ItemAlarma al = new ItemAlarma();
                    if(vtCG.equals(inicio)) {
                        al.setTipo(1);
                    }else {
                        al.setTipo(0);
                    }
                    al.setFecha(vtCG);
                    al.setMensaje("Vence el registro de 'cargas generales' del empleado "+rs.getString("NOMBRE"));
                    alarmas.add(al);
                }
                ///Para cada Fecha de vencimiento, creo una alarma
                Date vtoCP = rs.getDate("VTO_CARGAPELIGROSA");
                if(vtoCP!=null && vtoCP.compareTo(inicio)>=0 && vtoCP.compareTo(fin)<=0){ //vtoCP se encuentra en el rango
                    ItemAlarma al = new ItemAlarma();
                    if(vtoCP.equals(inicio)) {
                        al.setTipo(1);
                    }else {
                        al.setTipo(0);
                    }
                    al.setFecha(vtoCP);
                    al.setMensaje("Vence el registro de 'cargas peligrosas' del empleado "+rs.getString("NOMBRE"));
                    alarmas.add(al);
                }
                ///Para cada Fecha de vencimiento, creo una alarma
                Date vto1 = rs.getDate("VTO_OTROS1");
                if(vto1!=null && vto1.compareTo(inicio)>=0 && vto1.compareTo(fin)<=0){ //vto1 se encuentra en el rango
                    ItemAlarma al = new ItemAlarma();
                    if(vto1.equals(inicio)) {
                        al.setTipo(1);
                    }else {
                        al.setTipo(0);
                    }
                    al.setFecha(vto1);
                    al.setMensaje("Vence '"+ rs.getString("DESCRIPCION_VTO1")+"' del empleado "+rs.getString("NOMBRE"));
                    alarmas.add(al);
                }
                ///Para cada Fecha de vencimiento, creo una alarma
                Date vto2 = rs.getDate("VTO_OTROS2");
                if(vto2!=null && vto2.compareTo(inicio)>=0 && vto2.compareTo(fin)<=0){ //vto2 se encuentra en el rango
                    ItemAlarma al = new ItemAlarma();
                    if(vto2.equals(inicio)) {
                        al.setTipo(1);
                    }else {
                        al.setTipo(0);
                    }
                    al.setFecha(vto2);
                    al.setMensaje("Vence '"+rs.getString("DESCRIPCION_VTO2")+"' del empleado "+rs.getString("NOMBRE"));
                    alarmas.add(al);
                }
                ///Para cada Fecha de vencimiento, creo una alarma
                Date vto3 = rs.getDate("VTO_OTROS3");
                if(vto3!=null && vto3.compareTo(inicio)>=0 && vto3.compareTo(fin)<=0){ //vto3 se encuentra en el rango
                    ItemAlarma al = new ItemAlarma();
                    if(vto3.equals(inicio)) {
                        al.setTipo(1);
                    }else {
                        al.setTipo(0);
                    }
                    al.setFecha(vto3);
                    al.setMensaje("Vence '"+rs.getString("DESCRIPCION_VTO3")+"' del empleado "+rs.getString("NOMBRE"));
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
