/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.InformesHoras;
import Modelo.Periodo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JRException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.awt.Dialog.ModalExclusionType;
import java.net.URL;
import java.sql.Connection;
import java.util.Collections;

/**
 *
 * @author matuu
 */
public class InformesHorasDAO {
    Connection conector;
    public void conectar() {
        try {
            conector = ConectorDB.getConector();
        } catch (Exception ex) {
        }
    }
    
    public int guardar(InformesHoras ih) {
        int r = ih.getId();
        String query = null;

        try {
            query="select ID from informes_horas where ID_OPERARIO = "+ih.getId_operario()+" and PERIODO like '"+
                    ih.getPeriodo()+"'";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                ih.setId(rs.getInt("ID"));
                r=ih.getId();
            }
            if(ih.getId()==0){
            query = "insert into informes_horas (ID_OPERARIO, MULTI_FC, TOTAL_HS_VIAJE,"
                    + " TOTAL_50, TOTAL_100, TOTAL_NORMAL, TOTAL_TAREA, PERIODO,"
                    + " DESDE_F, HASTA_F, X100OBRA) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
            }
            else{
                query = "update informes_horas set ID_OPERARIO=?, MULTI_FC=?, TOTAL_HS_VIAJE=?,"
                    + " TOTAL_50=?, TOTAL_100=?, TOTAL_NORMAL=?, TOTAL_TAREA=?, PERIODO=?,"
                    + " DESDE_F=?, HASTA_F=?, X100OBRA=? where ID = "+ih.getId();
            }
            ps = conector.prepareStatement(query);
            ps.setInt(1, ih.getId_operario());
            ps.setInt(2, ih.getMultiFc());
            ps.setFloat(3, ih.getTotal_hs_viaje());
            ps.setFloat(4, ih.getTotal_50());
            ps.setFloat(5, ih.getTotal_100());
            ps.setFloat(6, ih.getTotal_normal());
            ps.setFloat(7, ih.getTotal_tarea());
            ps.setString(8, ih.getPeriodo());
            ps.setDate(9, new java.sql.Date(ih.getDesdeF().getTime()));
            ps.setDate(10, new java.sql.Date(ih.getHastaF().getTime()));
            if(ih.getX100Obras().length()>128){
                ps.setString(11, ih.getX100Obras().substring(0, 127));
            }else{
                ps.setString(11, ih.getX100Obras());
            }
            
            
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                r = rs.getInt(1);
                ih.setId(r);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            r = -1;
        }
        return r;
    }

    

    public ArrayList<InformesHoras> cargarTodos() {
        String query = null;
        ArrayList<InformesHoras> arrayInformes = new ArrayList<InformesHoras>();
        try {
            query = "select * from informes_horas";
            /*ID_OPERARIO, MULTI_FC, TOTAL_HS_VIAJE,"
                    + " TOTAL_50, TOTAL_100, TOTAL_NORMAL, TOTAL_TAREA, PERIODO,"
                    + " DESDE_F, HASTA_F
            */
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                InformesHoras ih = new InformesHoras();
                ih.setId(rs.getInt("ID"));
                ih.setDesdeF(rs.getDate("DESDE_F"));
                ih.setHastaF(rs.getDate("HASTA_F"));
                ih.setId_operario(rs.getInt("ID_OPERARIO"));
                ih.setMultiFc(rs.getInt("MULTI_FC"));
                ih.setPeriodo(rs.getString("PERIODO"));
                ih.setTotal_100(rs.getFloat("TOTAL_100"));
                ih.setTotal_50(rs.getFloat("TOTAL_50"));
                ih.setTotal_hs_viaje(rs.getFloat("TOTAL_HS_VIAJE"));
                ih.setTotal_normal(rs.getFloat("TOTAL_NORMAL"));
                ih.setTotal_tarea(rs.getFloat("TOTAL_TAREA"));
                ih.setX100Obras(rs.getString("X100OBRA"));
                arrayInformes.add(ih);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los InformesHoras.\n");
        }
        return arrayInformes;

    }
    public ArrayList<InformesHoras> findByIdOperario(int id) {
        String query = null;
        ArrayList<InformesHoras> arrayInformes = new ArrayList<InformesHoras>();
        try {
            query = "select * from informes_horas where ID_OPERARIO =" +id
                    +" order by ID desc";
            /*ID_OPERARIO, MULTI_FC, TOTAL_HS_VIAJE,"
                    + " TOTAL_50, TOTAL_100, TOTAL_NORMAL, TOTAL_TAREA, PERIODO,"
                    + " DESDE_F, HASTA_F
            */
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                InformesHoras ih = new InformesHoras();
                ih.setId(rs.getInt("ID"));
                ih.setDesdeF(rs.getDate("DESDE_F"));
                ih.setHastaF(rs.getDate("HASTA_F"));
                ih.setId_operario(rs.getInt("ID_OPERARIO"));
                ih.setMultiFc(rs.getInt("MULTI_FC"));
                ih.setPeriodo(rs.getString("PERIODO"));
                ih.setTotal_100(rs.getFloat("TOTAL_100"));
                ih.setTotal_50(rs.getFloat("TOTAL_50"));
                ih.setTotal_hs_viaje(rs.getFloat("TOTAL_HS_VIAJE"));
                ih.setTotal_normal(rs.getFloat("TOTAL_NORMAL"));
                ih.setTotal_tarea(rs.getFloat("TOTAL_TAREA"));
                ih.setX100Obras(rs.getString("X100OBRA"));
                arrayInformes.add(ih);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los InformesHoras.\n");
        }
        return arrayInformes;

    }
    
    public ArrayList<InformesHoras> findByPeriodo(String periodo) {
        String query = null;
        ArrayList<InformesHoras> arrayInformes = new ArrayList<InformesHoras>();
        try {
            query = "select * from informes_horas where PERIODO = ?";
            
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setString(1, periodo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                InformesHoras ih = new InformesHoras();
                ih.setId(rs.getInt("ID"));
                ih.setDesdeF(rs.getDate("DESDE_F"));
                ih.setHastaF(rs.getDate("HASTA_F"));
                ih.setId_operario(rs.getInt("ID_OPERARIO"));
                ih.setMultiFc(rs.getInt("MULTI_FC"));
                ih.setPeriodo(rs.getString("PERIODO"));
                ih.setTotal_100(rs.getFloat("TOTAL_100"));
                ih.setTotal_50(rs.getFloat("TOTAL_50"));
                ih.setTotal_hs_viaje(rs.getFloat("TOTAL_HS_VIAJE"));
                ih.setTotal_normal(rs.getFloat("TOTAL_NORMAL"));
                ih.setTotal_tarea(rs.getFloat("TOTAL_TAREA"));
                ih.setX100Obras(rs.getString("X100OBRA"));
                arrayInformes.add(ih);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los InformesHoras.\n");
        }
        return arrayInformes;

    }
    public ArrayList<InformesHoras> findByPeriodoDistinct(String periodo) {
        String query = null;
        ArrayList<InformesHoras> arrayInformes = new ArrayList<InformesHoras>();
        try {
            query = "SELECT DISTINCT IH.* FROM partediario PD "
                    + "LEFT JOIN operarios O ON PD.operario = O.id "
                    + "LEFT JOIN informes_horas IH ON PD.operario = IH.id_operario "
                    + "where IH.periodo like '"+ periodo+"' order by O.NOMBRE asc";
            
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                InformesHoras ih = new InformesHoras();
                ih.setId(rs.getInt("ID"));
                ih.setDesdeF(rs.getDate("DESDE_F"));
                ih.setHastaF(rs.getDate("HASTA_F"));
                ih.setId_operario(rs.getInt("ID_OPERARIO"));
                ih.setMultiFc(rs.getInt("MULTI_FC"));
                ih.setPeriodo(rs.getString("PERIODO"));
                ih.setTotal_100(rs.getFloat("TOTAL_100"));
                ih.setTotal_50(rs.getFloat("TOTAL_50"));
                ih.setTotal_hs_viaje(rs.getFloat("TOTAL_HS_VIAJE"));
                ih.setTotal_normal(rs.getFloat("TOTAL_NORMAL"));
                ih.setTotal_tarea(rs.getFloat("TOTAL_TAREA"));
                ih.setX100Obras(rs.getString("X100OBRA"));
                arrayInformes.add(ih);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los InformesHoras.\n");
        }
        return arrayInformes;

    }
    
    public ArrayList<Periodo> findPeriodoDistinct() {
        String query = null;
        
        ArrayList<Periodo> periodos = new ArrayList<Periodo>();
        try {
            query = "SELECT DISTINCT periodo FROM informes_horas order by id desc";
            
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               periodos.add(new Periodo(rs.getString("PERIODO")));
            }
            Collections.sort(periodos, Collections.reverseOrder());
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los InformesHoras.\n");
        }
        return periodos;

    }
    
    public InformesHoras findById(int id) {
        String query = null;
        InformesHoras ih = new InformesHoras();
        try {
            query = "select * from informes_horas where ID = " +id;
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                ih.setId(rs.getInt("ID"));
                ih.setDesdeF(rs.getDate("DESDE_F"));
                ih.setHastaF(rs.getDate("HASTA_F"));
                ih.setId_operario(rs.getInt("ID_OPERARIO"));
                ih.setMultiFc(rs.getInt("MULTI_FC"));
                ih.setPeriodo(rs.getString("PERIODO"));
                ih.setTotal_100(rs.getFloat("TOTAL_100"));
                ih.setTotal_50(rs.getFloat("TOTAL_50"));
                ih.setTotal_hs_viaje(rs.getFloat("TOTAL_HS_VIAJE"));
                ih.setTotal_normal(rs.getFloat("TOTAL_NORMAL"));
                ih.setTotal_tarea(rs.getFloat("TOTAL_TAREA"));
                ih.setX100Obras(rs.getString("X100OBRA"));              
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los InformesHoras.\n");
        }
        return ih;

    }

    
    
    public boolean borrar(InformesHoras ih) {

        boolean r =false;
        try {
            
            String query = "delete from informes_horas where ID = ? ";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, ih.getId());
            
            
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
    
    
    /*Metodos de conexión y creación de reportes usando jasperReport
     * 
     */
    
    public JasperPrint registrosHoras(InformesHoras i)  {
        try {
            
            URL archivo = null;
            Map parametro = new HashMap();
            parametro.put("urlimagenZille", getClass().getResource("/Reportes/zille.png").toString());
            parametro.put("urlimagenZilleBg", getClass().getResource("/Reportes/zille-bg.png").toString());
            parametro.put("id_informe", i.getId());
            archivo = getClass().getResource("/Reportes/persona/Registros.jasper");
            System.out.println("Cargando desde: " + archivo);
            if (archivo == null) {
                System.out.println("No se encuentra el archivo.");
                //System.exit(2);
            }
                JasperReport masterReport = (JasperReport) JRLoader.loadObject(archivo);

                JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, conector);
                
                return jasperPrint;

        }catch (JRException j){
            j.getMessage();
        } catch( UnsupportedOperationException ex){
        } catch (ClassCastException ex){
        } catch( NullPointerException ex) {
        } catch ( IllegalArgumentException ex){
        } 
        return null;
        
    }
    public JasperPrint registrosDetalleHora(InformesHoras i)  {
        try {
            
            URL archivo = null;
            Map parametro = new HashMap();
            parametro.put("urlimagenZille", getClass().getResource("/Reportes/zille.png").toString());
            parametro.put("urlimagenZilleBg", getClass().getResource("/Reportes/zille-bg.png").toString());
            parametro.put("id_informe", i.getId());
            archivo = getClass().getResource("/Reportes/persona/DetallePeriodo.jasper");
            System.out.println("Cargando desde: " + archivo);
            if (archivo == null) {
                System.out.println("No se encuentra el archivo.");
                //System.exit(2);
            }
            JasperReport masterReport = (JasperReport) JRLoader.loadObject(archivo);

            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, conector);
            return jasperPrint;

        }catch (JRException j){
            j.getMessage();
            return null;
        } 
    }
    public JasperPrint registrosDetalleHora2Excel(InformesHoras i)  {
        try {
            
            URL archivo = null;
            Map parametro = new HashMap();
            parametro.put("urlimagenZille", getClass().getResource("/Reportes/zille.png").toString());
            parametro.put("urlimagenZilleBg", getClass().getResource("/Reportes/zille-bg.png").toString());
            parametro.put("id_informe", i.getId());
            archivo = getClass().getResource("/Reportes/persona/DetallePeriodo.jasper");
            System.out.println("Cargando desde: " + archivo);
            if (archivo == null) {
                System.out.println("No se encuentra el archivo.");
                //System.exit(2);
            }
                JasperReport masterReport = (JasperReport) JRLoader.loadObject(archivo);
                JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, conector);              
                return jasperPrint;

        }catch (JRException j){
            j.getMessage();
            return null;
        } 
     
    }
}
