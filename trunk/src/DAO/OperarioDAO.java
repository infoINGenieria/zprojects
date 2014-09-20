/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.DatosFrancoOperario;
import Modelo.EPPOperario;
import ViewModel.ItemAlarma;
import Modelo.Operario;
import Utils.FechaUtil;
import ViewModel.ItemAlarmaBean;
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
                    "DESCRIPCION_VTO3, VTO_OTROS1, VTO_OTROS2, VTO_OTROS3, FECHA_INGRESO) values "
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            ps.setDate(17, FechaUtil.getFechatoDB(op.getFecha_ingreso()));
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
                    "DESCRIPCION_VTO3=?, VTO_OTROS1=?, VTO_OTROS2=?, VTO_OTROS3=?, "
                    + "FECHA_INGRESO=? where ID = ?";
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
            ps.setDate(17, FechaUtil.getFechatoDB(op.getFecha_ingreso()));
            ps.setInt(18, op.getId());
            
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
                op.setFecha_ingreso(rs.getDate("FECHA_INGRESO"));
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
                op.setFecha_ingreso(rs.getDate("FECHA_INGRESO"));
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
                op.setFecha_ingreso(rs.getDate("FECHA_INGRESO"));
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
    
    public ArrayList<ItemAlarmaBean> getAlarmasOperariosForReport(java.util.Date inicio, java.util.Date fin){
        //TODO: arreglar esto
        ArrayList<ItemAlarma> alarmas = getAlarmasOperarios(inicio, fin);
        ArrayList<ItemAlarmaBean> itemes = new ArrayList<ItemAlarmaBean>();
        for(ItemAlarma item: alarmas){
            itemes.add(new ItemAlarmaBean(item.getFecha(), null, item.getMensaje(), "Alarma automática por vencimiento."));
        }
        return itemes;
    }
    
    public ArrayList<ItemAlarma> getAlarmasOperarios(java.util.Date inicio, java.util.Date fin){
        ArrayList<ItemAlarma> alarmas = new ArrayList<ItemAlarma>();
        try {
            String query = "select distinct * from operarios where "
                    + "VTO_CARNET <= ? or "
                    + "VTO_PSICOFISICO <= ? or "
                    + "VTO_CARGAGRAL <= ? or "
                    + "VTO_CARGAPELIGROSA <= ? or "
                    + "VTO_OTROS1 <= ? or "
                    + "VTO_OTROS2 <= ? or "
                    + "VTO_OTROS3 <= ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setDate(1, FechaUtil.getFechatoDB(fin));
            ps.setDate(2, FechaUtil.getFechatoDB(fin));
            ps.setDate(3, FechaUtil.getFechatoDB(fin));
            ps.setDate(4, FechaUtil.getFechatoDB(fin));
            ps.setDate(5, FechaUtil.getFechatoDB(fin));
            ps.setDate(6, FechaUtil.getFechatoDB(fin));
            ps.setDate(7, FechaUtil.getFechatoDB(fin));
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ///Para cada Fecha de vencimiento, creo una alarma
                Date vt = rs.getDate("VTO_CARNET");
                if(vt!=null && vt.before(fin)){ //vt se encuentra en el rango
                    ItemAlarma al = new ItemAlarma();
                    if (vt.before(inicio)){
                        al.setMensaje("Venció el registro de conducir del empleado "+rs.getString("NOMBRE"));
                        al.setTipo(2);
                    } else {
                        al.setMensaje("Vence el registro de conducir del empleado "+rs.getString("NOMBRE"));
                        if(vt.equals(inicio)) {
                            al.setTipo(1);
                        }else {
                            al.setTipo(0);
                        }
                    }
                    al.setFecha(vt);
                    alarmas.add(al);
                }
                ///Para cada Fecha de vencimiento, creo una alarma
                Date vtPF = rs.getDate("VTO_PSICOFISICO");
                if(vtPF!=null && vtPF.before(fin)){ //vtPF se encuentra en el rango
                    ItemAlarma al = new ItemAlarma();
                    if (vtPF.before(inicio)) {
                        al.setMensaje("Venció el psicofísico del empleado "+rs.getString("NOMBRE"));
                        al.setTipo(2);
                    }else {
                        al.setMensaje("Vence el psicofísico del empleado "+rs.getString("NOMBRE"));
                        if(vtPF.equals(inicio)) {
                            al.setTipo(1);
                        }else {
                            al.setTipo(0);
                        }
                    }
                    al.setFecha(vtPF);                   
                    alarmas.add(al);
                }
                ///Para cada Fecha de vencimiento, creo una alarma
                Date vtCG = rs.getDate("VTO_CARGAGRAL");
                if(vtCG!=null && vtCG.before(fin)){ //vtCG se encuentra en el rango
                    ItemAlarma al = new ItemAlarma();
                    if(vtCG.before(fin)) {
                        al.setMensaje("Venció el registro de 'cargas generales' del empleado "+rs.getString("NOMBRE"));
                        al.setTipo(2);
                    }else {
                        al.setMensaje("Vence el registro de 'cargas generales' del empleado "+rs.getString("NOMBRE"));
                        if(vtCG.equals(inicio)) {
                            al.setTipo(1);
                        }else {
                            al.setTipo(0);
                        }
                    }
                    al.setFecha(vtCG);                  
                    alarmas.add(al);
                }
                ///Para cada Fecha de vencimiento, creo una alarma
                Date vtoCP = rs.getDate("VTO_CARGAPELIGROSA");
                if(vtoCP!=null && vtoCP.before(fin)){ //vtoCP se encuentra en el rango
                    ItemAlarma al = new ItemAlarma();
                    if(vtoCP.before(inicio)) {
                        al.setMensaje("Vence el registro de 'cargas peligrosas' del empleado "+rs.getString("NOMBRE"));
                        al.setTipo(2);
                    }else{
                        al.setMensaje("Vence el registro de 'cargas peligrosas' del empleado "+rs.getString("NOMBRE"));
                        if(vtoCP.equals(inicio)) {
                            al.setTipo(1);
                        }else {
                            al.setTipo(0);
                        }
                    }
                    al.setFecha(vtoCP);                  
                    alarmas.add(al);
                }
                ///Para cada Fecha de vencimiento, creo una alarma
                Date vto1 = rs.getDate("VTO_OTROS1");
                if(vto1!=null && vto1.before(fin)){ //vto1 se encuentra en el rango
                    ItemAlarma al = new ItemAlarma();
                    if(vto1.before(inicio)) {
                        al.setMensaje("Venció '"+ rs.getString("DESCRIPCION_VTO1")+"' del empleado "+rs.getString("NOMBRE"));
                        al.setTipo(2);
                    }else{ 
                        al.setMensaje("Vence '"+ rs.getString("DESCRIPCION_VTO1")+"' del empleado "+rs.getString("NOMBRE"));
                        if(vto1.equals(inicio)) {
                            al.setTipo(1);
                        }else {
                            al.setTipo(0);
                        }
                    }
                    al.setFecha(vto1);
                    alarmas.add(al);
                }
                ///Para cada Fecha de vencimiento, creo una alarma
                Date vto2 = rs.getDate("VTO_OTROS2");
                if(vto2!=null && vto2.before(fin)){ //vto2 se encuentra en el rango
                    ItemAlarma al = new ItemAlarma();
                    if(vto2.before(inicio)){
                        al.setTipo(2);
                        al.setMensaje("Venció '"+rs.getString("DESCRIPCION_VTO2")+"' del empleado "+rs.getString("NOMBRE"));
                    }else{
                        al.setMensaje("Vence '"+rs.getString("DESCRIPCION_VTO2")+"' del empleado "+rs.getString("NOMBRE"));
                        if(vto2.equals(inicio)) {
                            al.setTipo(1);
                        }else {
                            al.setTipo(0);
                        }
                    }
                    al.setFecha(vto2);                 
                    alarmas.add(al);
                }
                ///Para cada Fecha de vencimiento, creo una alarma
                Date vto3 = rs.getDate("VTO_OTROS3");
                if(vto3!=null && vto3.before(fin)){ //vto3 se encuentra en el rango
                    ItemAlarma al = new ItemAlarma();
                    if(vto3.before(inicio)) {
                        al.setMensaje("Vence '"+rs.getString("DESCRIPCION_VTO3")+"' del empleado "+rs.getString("NOMBRE"));
                        al.setTipo(2);
                    }else {
                        al.setMensaje("Vence '"+rs.getString("DESCRIPCION_VTO3")+"' del empleado "+rs.getString("NOMBRE"));
                        if(vto3.equals(inicio)) {
                            al.setTipo(1);
                        }else {
                            al.setTipo(0);
                        }
                    }
                    al.setFecha(vto3);
                    
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
    
    
    
    public int guardarEPP(EPPOperario epp) {
        int r = -1;
        String query = null;

        try {
            if(epp.getId()!=0){
                query = "update epp_operarions set EPP_ID=?, OPERARIO_ID=?, "
                        + "VALOR=?, TIPO=? where ID ="+ epp.getId();
            }else{
                query = "insert into epp_operarios (EPP_ID, OPERARIO_ID, VALOR, TIPO) "
                        + "values (?,?,?,?) ";
            
            }
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, epp.getEppId());
            ps.setInt(2, epp.getOperarioId());
            ps.setString(3, epp.getValor());
            ps.setInt(4, epp.getTipo());
            
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

    
 

    public ArrayList<EPPOperario> cargarTodos(int operarioId) {
        String query = null;
        ArrayList<EPPOperario> fcs = new ArrayList<EPPOperario>();
        try {
            query = "select * from epp_operarios where operario_id = ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, operarioId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                EPPOperario epp = new EPPOperario();
                epp.setId(rs.getInt("ID"));
                epp.setEppId(rs.getInt("EPP_ID"));
                epp.setOperarioId(rs.getInt("OPERARIO_ID"));
                epp.setValor(rs.getString("VALOR"));
                epp.setTipo(rs.getInt("TIPO"));
                fcs.add(epp);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar las EPP del operario.\n");
        }
        return fcs;

    }
    
    public boolean borrar(EPPOperario epp) {

        boolean r =false;
        try {
            
            String query = "delete from epp_operarios where ID = ? ";
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
    
    public EPPOperario findById(int id) {
        String query = null;
        EPPOperario epp = new EPPOperario();
        try {
            query = "select * from epp_operarios where ID = "+id;
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {  
                epp.setId(rs.getInt("ID"));
                epp.setEppId(rs.getInt("EPP_ID"));
                epp.setOperarioId(rs.getInt("OPERARIO_ID"));
                epp.setTipo(rs.getInt("TIPO"));
                epp.setValor(rs.getString("VALOR"));
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar el EPP del operario\n");
        }
        return epp;

    }
    
    public EPPOperario findByIds(int operario_id, int epp_id) {
        String query = null;
        EPPOperario epp = new EPPOperario();
        try {
            query = "select * from epp_operarios where EPP_ID = ? and OPERARIO_ID = ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, epp_id);
            ps.setInt(2, operario_id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {  
                epp.setId(rs.getInt("ID"));
                epp.setEppId(rs.getInt("EPP_ID"));
                epp.setOperarioId(rs.getInt("OPERARIO_ID"));
                epp.setTipo(rs.getInt("TIPO"));
                epp.setValor(rs.getString("VALOR"));
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar el EPP del operario\n");
        }
        return epp;

    }
    
    public int guardarDatosFranco(DatosFrancoOperario dfo) {
        int r = -1;
        String query = null;

        try {
            PreparedStatement ps = conector.prepareStatement("select id from franco_licencia "
                    + "where operario_id = " + dfo.getOperarioId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dfo.setId(rs.getInt("ID"));
            }
            
            if(dfo.getId()==0)
                query = "insert into franco_licencia (OPERARIO_ID, AJUSTE, PAGADOS, "
                    + "SOLICITADOS1, SOLICITADOS2, ENTRA1, ENTRA2, SALE1, SALE2) values "
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            else
                query = "update franco_licencia set OPERARIO_ID=?, AJUSTE=?, "
                        + "PAGADOS=?, SOLICITADOS1=?, SOLICITADOS2=?, ENTRA1=?, "
                        + "ENTRA2=?, SALE1=?, SALE2=? where ID=?";
            ps = conector.prepareStatement(query);
            ps.setInt(1, dfo.getOperarioId());
            ps.setInt(2, dfo.getAjuste());
            ps.setInt(3, dfo.getPagados());
            ps.setInt(4, dfo.getSolicitados1());
            ps.setInt(5, dfo.getSolicitados2());
            ps.setDate(6, FechaUtil.getFechatoDB(dfo.getEntra1()));
            ps.setDate(7, FechaUtil.getFechatoDB(dfo.getEntra2()));
            ps.setDate(8, FechaUtil.getFechatoDB(dfo.getSale1()));
            ps.setDate(9, FechaUtil.getFechatoDB(dfo.getSale2()));
            if(dfo.getId()!=0) ps.setInt(10, dfo.getId());
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
    
    public DatosFrancoOperario findDatosFrancoById(int operario_id) {
        String query = null;
        DatosFrancoOperario dfo = new DatosFrancoOperario();
        try {
            query = "select * from franco_licencia where OPERARIO_ID = ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, operario_id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {  
                dfo.setId(rs.getInt("ID"));
                dfo.setOperarioId(rs.getInt("OPERARIO_ID"));
                dfo.setAjuste(rs.getInt("AJUSTE"));
                dfo.setPagados(rs.getInt("PAGADOS"));
                dfo.setSolicitados1(rs.getInt("SOLICITADOS1"));
                dfo.setSolicitados2(rs.getInt("SOLICITADOS2"));
                dfo.setEntra1(FechaUtil.getFechaFromDB(rs.getDate("ENTRA1")));
                dfo.setEntra2(FechaUtil.getFechaFromDB(rs.getDate("ENTRA2")));
                dfo.setSale1(FechaUtil.getFechaFromDB(rs.getDate("SALE1")));
                dfo.setSale2(FechaUtil.getFechaFromDB(rs.getDate("SALE2")));
                
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los datos de franco del operario\n");
        }
        return dfo;

    }
}
