/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Obras;
import Utils.FechaUtil;
import Utils.FileManager;
import ViewModel.ItemAlarmaBean;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
/**
 *
 * @author matuu
 */
public class ReportesDAO  extends HSSFMixin {

    Connection conector;

    public void conectar() {
        try {
            conector = ConectorDB.getConector();
        } catch (Exception ex) {
        }
    }
    /*
    public void reportPersEqTodos(Date desde, Date hasta) {
        try {

            URL master = null;
            URL subreport = null;
            Map parametro = new HashMap();
            parametro.put("urlimagenZille", getClass().getResource("/Reportes/zille.png").toString());
            parametro.put("urlimagenMF", getClass().getResource("/Reportes/mf.png").toString());
            
            parametro.put("desde_f", desde);
            parametro.put("hasta_f", hasta);

            master = getClass().getResource("/Reportes/Obra-Persona.jasper");
            subreport = getClass().getResource("/Reportes/Obra-Persona-Sub.jasper");
            System.out.println("Cargando desde: " + master);
            if (master == null) {
                System.out.println("No se encuentra el archivo master.");
                //System.exit(2);
            }
            if (subreport == null) {
                System.out.println("No se encuentra el archivo subreportr.");
                //System.exit(2);
            }
            JasperReport masterReport = (JasperReport) JRLoader.loadObject(master);
            JasperReport subReport = (JasperReport) JRLoader.loadObject(subreport);
            parametro.put("subreport", subReport);
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, conector);
            //JasperPrintManager.printReport(jasperPrint, false);
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);

            jviewer.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
            jviewer.setTitle("Informe");

            jviewer.setVisible(true);

        } catch (JRException j) {
            System.out.print(j.getMessage());
        }
    }
    */
    public JasperPrint reportEquipo(Date desde, Date hasta, int idEquipo) {
        try {

            URL master = null;
            URL subreport = null;
            HashMap<String, Object> parametro = new HashMap<String, Object>();
            parametro.put("urlimagenZille", getClass().getResource("/Reportes/zille.png").toString());
                       
            parametro.put("desde_f", desde);
            parametro.put("hasta_f", hasta);
            parametro.put("id_equipo", new Integer(idEquipo));
            master = getClass().getResource("/Reportes/equipo/Equipo-ObraPersona.jasper");
            subreport = getClass().getResource("/Reportes/equipo/Equipo-ObraPersona-resumen.jasper");
            
            System.out.println("Cargando desde: " + master);
            if (master == null) {
                System.out.println("No se encuentra el archivo master.");
                //System.exit(2);
            }
            System.out.println("Cargando desde: " + subreport);
            if (master == null) {
                System.out.println("No se encuentra el archivo subreport.");
                //System.exit(2);
            }
            JasperReport masterReport = (JasperReport) JRLoader.loadObject(master);
            JasperReport report = (JasperReport) JRLoader.loadObject(subreport);
            parametro.put("subreport", report);
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, conector);
            return jasperPrint;
        } catch (JRException j) {
            System.out.print(j.getMessage());
            return null;
        }
    }
    
    public JasperPrint equiposTodos2Excel(Date desde, Date hasta, int idEquipo)  {
        try {
            
            URL master = null;
            URL subreport = null;
            HashMap<String, Object> parametro = new HashMap<String, Object>();
            parametro.put("urlimagenZille", getClass().getResource("/Reportes/zille.png").toString());
                       
            parametro.put("desde_f", desde);
            parametro.put("hasta_f", hasta);
            parametro.put("id_equipo", new Integer(idEquipo));
            master = getClass().getResource("/Reportes/equipo/Equipo-ObraPersona.jasper");
            subreport = getClass().getResource("/Reportes/equipo/Equipo-ObraPersona-resumen.jasper");
            
            System.out.println("Cargando desde: " + master);
            if (master == null) {
                System.out.println("No se encuentra el archivo master.");
                //System.exit(2);
            }
            System.out.println("Cargando desde: " + subreport);
            if (master == null) {
                System.out.println("No se encuentra el archivo subreport.");
                //System.exit(2);
            }
            
            JasperReport masterReport = (JasperReport) JRLoader.loadObject(master);
            JasperReport report = (JasperReport) JRLoader.loadObject(subreport);
            parametro.put("subreport", report);
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, conector);
            
                         
            return jasperPrint;

        }catch (JRException j){
            j.getMessage();
            return null;
        } 
     
    }
    
    public JasperPrint reportPersEqXObra(Date desde, Date hasta, int idObra) {
        try {

            URL master = null;
            URL subreport = null;
            URL subreport2 = null;
            HashMap<String, Object> parametro = new HashMap<String, Object>();
            parametro.put("urlimagenZille", getClass().getResource("/Reportes/zille.png").toString());
            parametro.put("urlimagenMF", getClass().getResource("/Reportes/mf.png").toString());
            
            parametro.put("desde_f", desde);
            parametro.put("hasta_f", hasta);
            parametro.put("idobra", new Integer(idObra));
            master = getClass().getResource("/Reportes/obra/Obra-Persona.jasper");
            subreport = getClass().getResource("/Reportes/obra/Obra-Persona-Sub.jasper");
            subreport2 = getClass().getResource("/Reportes/obra/Obra-Persona-Sub_funcion.jasper");
            System.out.println("Cargando desde: " + master);
            if (master == null) {
                System.out.println("No se encuentra el archivo master.");
                //System.exit(2);
            }
            System.out.println("Cargando desde: " + subreport);
            if (subreport == null) {
                System.out.println("No se encuentra el archivo subreporte.");
                //System.exit(2);
            }
            System.out.println("Cargando desde: " + subreport2);
            if (subreport2 == null) {
                System.out.println("No se encuentra el archivo subreporte 2.");
                //System.exit(2);
            }
            JasperReport masterReport = (JasperReport) JRLoader.loadObject(master);
            JasperReport subReport = (JasperReport) JRLoader.loadObject(subreport);
            JasperReport subReport2 = (JasperReport) JRLoader.loadObject(subreport2);
            parametro.put("subreport", subReport);
            parametro.put("subreport2", subReport2);
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, conector);
            return jasperPrint;

        } catch (JRException j) {
            System.out.print(j.getMessage());
            return null;
        }
    }
    
    public JasperPrint reportEqMatXObra(Date desde, Date hasta, int idObra) {
        try {

            URL master = null;
            URL subreport = null;
            URL subreport2 = null;
            HashMap<String, Object> parametro = new HashMap<String, Object>();
            parametro.put("urlimagenZille", getClass().getResource("/Reportes/zille.png").toString());
            parametro.put("urlimagenMF", getClass().getResource("/Reportes/mf.png").toString());
            
            parametro.put("desde_f", desde);
            parametro.put("hasta_f", hasta);
            parametro.put("id_obra", new Integer(idObra));
            master = getClass().getResource("/Reportes/obra/Obra-EqMat.jasper");
            subreport = getClass().getResource("/Reportes/obra/Obra-EqMat-Sub.jasper");
            subreport2 = getClass().getResource("/Reportes/obra/Obra-EqMat-Resumen.jasper");
            
            System.out.println("Cargando desde: " + master);
            if (master == null) {
                System.out.println("No se encuentra el archivo master.");
                //System.exit(2);
            }
            System.out.println("Cargando desde: " + subreport);
            if (subreport == null) {
                System.out.println("No se encuentra el archivo subreport.");
                //System.exit(2);
            }
            System.out.println("Cargando desde: " + subreport2);
            if (subreport2 == null) {
                System.out.println("No se encuentra el archivo subreport2.");
                //System.exit(2);
            }
            JasperReport masterReport = (JasperReport) JRLoader.loadObject(master);
            JasperReport subReport = (JasperReport) JRLoader.loadObject(subreport);
            parametro.put("subreport", subReport);
            JasperReport subReport2 = (JasperReport) JRLoader.loadObject(subreport2);
            parametro.put("subreport2", subReport2);
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, conector);
           return jasperPrint;

        } catch (JRException j) {
            System.out.print(j.getMessage());
            return null;
        }
    }
    
    
    public JasperPrint reportRegistrosResumen(String periodo){
        try {

            URL master = null;
            URL subreport = null;
            HashMap<String, Object> parametro = new HashMap<String, Object>();
            parametro.put("urlimagenZille", getClass().getResource("/Reportes/zille.png").toString());
            parametro.put("urlimagenZilleBg", getClass().getResource("/Reportes/zille-bg.png").toString());
            parametro.put("periodo", periodo);
            master = getClass().getResource("/Reportes/persona/Registros-Resumen.jasper");
            subreport = getClass().getResource("/Reportes/persona/Registros-Resumen-Sub.jasper");
            System.out.println("Cargando desde: " + master);
            if (master == null) {
                System.out.println("No se encuentra el archivo master.");
                //System.exit(2);
            }
            System.out.println("Cargando desde: " + subreport);
            if (subreport == null) {
                System.out.println("No se encuentra el archivo subreportr.");
                //System.exit(2);
            }
            JasperReport masterReport = (JasperReport) JRLoader.loadObject(master);
            JasperReport subReport = (JasperReport) JRLoader.loadObject(subreport);
            parametro.put("subreport", subReport);
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, conector);
            return jasperPrint;

        } catch (JRException j) {
            System.out.print(j.getMessage());
            return null;
        }
    }
    
    public JasperPrint reportCombustibleObra(Date desde, Date hasta, int idObra){
        try {

            URL master = null;
            URL subreport = null;
            HashMap<String, Object> parametro = new HashMap<String, Object>();
            parametro.put("urlimagenZille", getClass().getResource("/Reportes/zille.png").toString());
            
            parametro.put("desde_f", desde);
            parametro.put("hasta_f", hasta);
            parametro.put("id_obra", new Integer(idObra));
            master = getClass().getResource("/Reportes/combustible/InformeCombustibleObra.jasper");
            subreport = getClass().getResource("/Reportes/combustible/InformeCombustibleObra-sub.jasper");
            System.out.println("Cargando desde: " + master);
            if (master == null) {
                System.out.println("No se encuentra el archivo master.");
                //System.exit(2);
            }
            System.out.println("Cargando desde: " + subreport);
            if (subreport == null) {
                System.out.println("No se encuentra el archivo subreportr.");
                //System.exit(2);
            }
            JasperReport masterReport = (JasperReport) JRLoader.loadObject(master);
            JasperReport subReport = (JasperReport) JRLoader.loadObject(subreport);
            parametro.put("subreport", subReport);
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, conector);
            return jasperPrint;

        } catch (JRException j) {
            System.out.print(j.getMessage());
            return null;
        }
    }
    
    public JasperPrint reportCombustibleResumen(Date desde, Date hasta){
        try {

            URL master = null;
            HashMap<String, Object> parametro = new HashMap<String, Object>();
            parametro.put("urlimagenZille", getClass().getResource("/Reportes/zille.png").toString());
            
            parametro.put("desde_f", desde);
            parametro.put("hasta_f", hasta);
            master = getClass().getResource("/Reportes/combustible/InformeCombustibleResumen.jasper");
            System.out.println("Cargando desde: " + master);
            if (master == null) {
                System.out.println("No se encuentra el archivo master.");
                //System.exit(2);
            }
            JasperReport masterReport = (JasperReport) JRLoader.loadObject(master);
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, conector);
           return jasperPrint;

        } catch (JRException j) {
            System.out.print(j.getMessage());
        }
        return null;
    }
    public JasperPrint reportCombustibleEquipo(Date desde, Date hasta, int idEquipo){
        try {

            URL master = null;
            URL subreport = null;
            HashMap<String, Object> parametro = new HashMap<String, Object>();
            parametro.put("urlimagenZille", getClass().getResource("/Reportes/zille.png").toString());
            
            parametro.put("desde_f", desde);
            parametro.put("hasta_f", hasta);
            parametro.put("id_equipo", new Integer(idEquipo));
            master = getClass().getResource("/Reportes/combustible/InformeCombustibleEquipo.jasper");
            subreport = getClass().getResource("/Reportes/combustible/InformeCombustibleEquipo-sub.jasper");
            System.out.println("Cargando desde: " + master);
            if (master == null) {
                System.out.println("No se encuentra el archivo master.");
                //System.exit(2);
            }
            System.out.println("Cargando desde: " + subreport);
            if (subreport == null) {
                System.out.println("No se encuentra el archivo subreportr.");
                //System.exit(2);
            }
            JasperReport masterReport = (JasperReport) JRLoader.loadObject(master);
            JasperReport subReport = (JasperReport) JRLoader.loadObject(subreport);
            parametro.put("subreport", subReport);
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, conector);
           return jasperPrint;

        } catch (JRException j) {
            System.out.print(j.getMessage());
        }
        return null;
    }
    
    public JasperPrint reportCombustibleEestacion(Date desde, Date hasta, int idEstacion){
        try {

            URL master = null;
            URL subreport = null;
            URL subreport2 = null;
            HashMap<String, Object> parametro = new HashMap<String, Object>();
            parametro.put("urlimagenZille", getClass().getResource("/Reportes/zille.png").toString());
            
            parametro.put("desde_f", desde);
            parametro.put("hasta_f", hasta);
            parametro.put("id_estacion", new Integer(idEstacion));
            master = getClass().getResource("/Reportes/combustible/InformeCombustibleEstacion.jasper");
            subreport = getClass().getResource("/Reportes/combustible/InformeCombustibleEstacion-sub.jasper");
            subreport2 = getClass().getResource("/Reportes/combustible/ingresoCombustible.jasper");
            System.out.println("Cargando desde: " + master);
            if (master == null) {
                System.out.println("No se encuentra el archivo master.");
                //System.exit(2);
            }
            System.out.println("Cargando desde: " + subreport);
            if (subreport == null) {
                System.out.println("No se encuentra el archivo subreportr.");
                //System.exit(2);
            }
            System.out.println("Cargando desde: " + subreport2);
            if (subreport2 == null) {
                System.out.println("No se encuentra el archivo subreportr2.");
                //System.exit(2);
            }
            JasperReport masterReport = (JasperReport) JRLoader.loadObject(master);
            JasperReport subReport =    (JasperReport) JRLoader.loadObject(subreport);
            JasperReport subReport2 =   (JasperReport) JRLoader.loadObject(subreport2);
            parametro.put("subreport", subReport);
            parametro.put("subreport2", subReport2);
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, conector);
            return jasperPrint;

        } catch (JRException j) {
            System.out.print(j.getMessage());
            return null;
        }
    }
    
     public String reportOrdenTrabajo( int idOT, String interno){
         String result="";
        try {

            URL master = null;
            HashMap<String, Object> parametro = new HashMap<String, Object>();
            parametro.put("ordentrabajoid", idOT);
            master = getClass().getResource("/Reportes/ordenTrabajo/otReport.jasper");
            System.out.println("Cargando desde: " + master);
            if (master == null) {
                System.out.println("No se encuentra el archivo master.");
                //System.exit(2);
            }           
            JasperReport masterReport = (JasperReport) JRLoader.loadObject(master);
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, conector);          
            //Exporta el informe a excel
            
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            String archivo="OT"+idOT +" ("+ interno+ ").xls";
            File ordenesFolder = FileManager.getPathDirectory(new String[]{"Ordenes"}, true);
            File exportF = new File(ordenesFolder, archivo);

            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportF));
            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
            configuration.setOnePagePerSheet(true);
            configuration.setDetectCellType(true);
            configuration.setCollapseRowSpan(false);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            Desktop.getDesktop().open(exportF);
            result="Se ha exportado correctamente la órden de trabajo.\n"
                    + "Archivo: "+exportF;

        } catch (IOException ex) {
            Logger.getLogger(ReportesDAO.class.getName()).log(Level.SEVERE, null, ex);
            result="Error al procesar la solicitud."; 
        } catch (JRException j) {
            System.out.print(j.getMessage());
            result="Error al procesar la solicitud.";
        }
        return result;
    }
     
     public String reportRIexcel( int riid, String ri_num){
         String result="";
        try {

            URL master = null;
            HashMap<String, Object> parametro = new HashMap<String, Object>();
            parametro.put("riid", riid);
            master = getClass().getResource("/Reportes/ri/ri.jasper");
            System.out.println("Cargando desde: " + master);
            if (master == null) {
                System.out.println("No se encuentra el archivo master.");
                //System.exit(2);
            }
            
            JasperReport masterReport = (JasperReport) JRLoader.loadObject(master);
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, conector);          
            //Exporta el informe a excel
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            String archivo="RI"+riid +"("+ ri_num+ ").xls";
            File riFolder = FileManager.getPathDirectory(new String[]{"RI"}, true);
            File exportF = new File(riFolder, archivo);
            
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportF));
            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
            configuration.setOnePagePerSheet(true);
            configuration.setDetectCellType(true);
            configuration.setCollapseRowSpan(false);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            Desktop.getDesktop().open(exportF);
            result="Se ha exportado correctamente el Requerimirnto Interno "+ri_num+".\n"
                    + "Archivo: "+exportF;

        } catch (IOException ex) {
            Logger.getLogger(ReportesDAO.class.getName()).log(Level.SEVERE, null, ex);
            result="Error al procesar la solicitud."; 
        } catch (JRException j) {
            System.out.print(j.getMessage());
            result="Error al procesar la solicitud.";
        }
        return result;
    }
         
     
    public String reportUtilizacionEquipoPorObra(Date desde, Date hasta) {
        HSSFWorkbook myWorkBook = new HSSFWorkbook();
        HSSFSheet mySheet = myWorkBook.createSheet("Lista de equipos por CC");
        HSSFCellStyle amarillo = getCellStyle(myWorkBook, STYLE_AMARILLO);
        
        HSSFCellStyle azul = getCellStyle(myWorkBook, STYLE_AZUL);
        
        String query= "SELECT EQ.N_INTERNO as nint, f.nombre as familia, O.NOMBRE as nombre, "
                + "EQ.marca as marca, EQ.modelo as modelo, "
                + "COUNT(PD.id) as DiasEnMes, OB.OBRA as obra, OB.id as obraid "
                + "FROM partediario PD "
                + "INNER JOIN operarios O ON PD.operario = O.id "
                + "INNER JOIN obras OB ON PD.obra = OB.id "
                + "INNER JOIN registro_equipo RQ ON PD.id = RQ.partediario_id "
                + "INNER JOIN equipos EQ ON RQ.equipo = EQ.id "
                + "INNER JOIN familia_equipo f ON f.id = EQ.familia_equipo_id "
                + "where PD.fecha <= '"+ FechaUtil.getFechaSQL(hasta)+"' and "
                + "PD.fecha >= '"+ FechaUtil.getFechaSQL(desde)+"' and EQ.id != 1 "
                + "AND PD.situacion =1 "
                + "group by OB.id, EQ.N_INTERNO "
                + "order by OB.id, EQ.N_INTERNO desc";
        try {
            
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            
            //Datos
            int lastObraId = 0;
            while (rs.next()) {
                // si es distinto, creo cabecera
                HSSFRow myRow = null;
                HSSFCell myCell = null;
                if(lastObraId != rs.getInt("obraid")) {
                    if(lastObraId != 0) // NO en la primera vez
                    {
                        i = i +3;
                    }
                    lastObraId = rs.getInt("obraid");
                    
                    //ENCABEZADOS
                    myRow = createRow(mySheet, i);
                    myRow.setHeight((short)600);
                    myCell = createCell(myRow, 0, "Listado de equipos en " + rs.getString("obra"), amarillo);
                    
                    mySheet.addMergedRegion(new CellRangeAddress(i,i,0,4));  // merge cell
                    //Siguiente renglón
                    i++; 
                    myRow = createRow(mySheet, i);    
                    myRow.setHeight((short)400);
                    // Interno
                    myCell = createCell(myRow, 0,"Interno", azul);
                    
                    // Familia
                    myCell = createCell(myRow, 1,"Familia de equipo", azul);
                    // Equipo
                    myCell = createCell(myRow, 2,"Equipo", azul);
                    // Operario
                    myCell = createCell(myRow, 3,"Operario", azul);
                    // Dias en el mes
                    myCell = createCell(myRow, 4,"Días en el mes", azul);
                    i++;
                }
                
                myRow = createRow(mySheet, i);
                // Interno
                myCell = createCell(myRow, 0, rs.getString("nint"));
                // Familia
                myCell = createCell(myRow, 1, rs.getString("familia"));
                // Marca y modelo
                myCell = createCell(myRow, 2, rs.getString("marca") + " " + rs.getString("modelo"));
                // Operario
                myCell = createCell(myRow, 3, rs.getString("nombre"));
                // Dias
                myCell = createCell(myRow, 4, rs.getInt("DiasEnMes"));
                i++;
            }
            
            for (int b = 0; b < 5; b++){
                if(b == 0 || b == 4){
                    mySheet.setColumnWidth(b, 3500); 
                }else {
                    mySheet.setColumnWidth(b, 8000); 
                }
            }  
            File dest = new File(FileManager.getTmpFolder(), "UtilizacionEquiposPorCC.xls");
            FileOutputStream out = new FileOutputStream(dest);
            myWorkBook.write(out);
            out.close();
            Desktop.getDesktop().open(dest);
            return "Reporte creado en: " + dest.getAbsolutePath();
        } catch (UnsupportedOperationException ex) {
            return "Reporte creado en: " + FileManager.getTmpFolder().getAbsolutePath() + "/" + "UtilizacionEquiposPorCC.xls";
        } catch (SQLException ex){
            return "No se pudo crear el reporte.\nError de lectura en la base de datos"; 
        } catch (Exception e) {
            return "No se pudo crear el reporte.\nError desconocido"; 
        }
    }

    public void reportCustom(int idOperario, ArrayList<Obras> obrasIDlist, Date desde, Date hasta) {
        
        System.out.println("Cantidad de obras: "+obrasIDlist.size());
        //POIFSFileSystem fs = new POIFSFileSystem(new InputStream() {})
        HSSFWorkbook myWorkBook = new HSSFWorkbook();
        HSSFSheet mySheet = myWorkBook.createSheet("Reporte Personalizado");
        //Style
            HSSFCellStyle amarillo = getCellStyle(myWorkBook, STYLE_AMARILLO);
            HSSFCellStyle azul = getCellStyle(myWorkBook, STYLE_AZUL);           
            HSSFCellStyle verde = getCellStyle(myWorkBook, STYLE_VERDE);
            
        String query= "select  OP.nombre, OP.id FROM partediario PD "
                + "LEFT JOIN obras OB ON PD.obra = OB.id "
                + "LEFT JOIN operarios OP ON PD.operario = OP.id "
                + "WHERE PD.fecha <= '"+FechaUtil.getFechaSQL(hasta)+"' AND PD.fecha >= '"+FechaUtil.getFechaSQL(desde)+"'";
        if(idOperario != 0){
                query += " AND PD.operario = " + idOperario;
        }
        query += " Group by OP.id ORDER BY OP.nombre asc";
        try {
            
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            
        //ENCABEZADOS
            
            HSSFRow myRow = mySheet.getRow(i);
            if (myRow == null) { myRow = mySheet.createRow(i); }  
            HSSFCell myCell = myRow.createCell(0);
            myCell.setCellValue(new HSSFRichTextString("TOTAL DE DIAS POR CENTRO DE COSTO"));
            myCell.setCellStyle(amarillo);
            
            //Siguiente renglón
            i++;
            
            myRow = mySheet.getRow(i);        
            if (myRow == null) { myRow = mySheet.createRow(i); }    
            myRow.setHeight((short)600);
            myCell = myRow.createCell(0);
            myCell.setCellValue(new HSSFRichTextString("APELLIDO Y NOMBRE"));
            myCell.setCellStyle(amarillo);
        //TOTAL
            myCell = myRow.createCell(1);
            myCell.setCellValue(new HSSFRichTextString("TOTAL"));
            myCell.setCellStyle(azul);
        //OBRAS
            for (int h = 0; h < obrasIDlist.size(); h++) {
                myCell = myRow.createCell(h+2);
                myCell.setCellValue(new HSSFRichTextString(obrasIDlist.get(h).getCodigo()));
                if((h+2) % 2 == 0){
                    myCell.setCellStyle(verde);
                }else{
                    myCell.setCellStyle(azul);
                }
            }
        //Datos
            int contador = 0;
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                if(nombre==null || nombre.isEmpty()){
                    continue;
                }
                i++;
                myRow = mySheet.getRow(i);
                if (myRow == null) {
                    myRow = mySheet.createRow(i);
                }
            //Nombre
                myCell = myRow.createCell(0);  
                myCell.setCellValue(new HSSFRichTextString(nombre));
            //Total
                HSSFCell myCellF = myRow.createCell(1);
                int i2= i+1;
                String formula = "SUM(C"+i2+":AZ"+i2+")";
                myCellF.setCellFormula(formula);
            //Obras
                for (int j = 0; j < obrasIDlist.size(); j++) {
                    String subq = "select count(PD.obra) as cantidad FROM partediario PD WHERE PD.fecha <= '"
                            + FechaUtil.getFechaSQL(hasta) + "' AND PD.fecha >= '" + FechaUtil.getFechaSQL(desde)
                            + "' AND PD.obra = " + obrasIDlist.get(j).getId() + " and PD.operario = "
                            + rs.getInt("id");
                    ps = conector.prepareStatement(subq);
                    ResultSet rs2 = ps.executeQuery();
                    myCell = myRow.createCell(j + 2);
                    
                    int value = 0;
                    if (rs2.next()) {
                        value = rs2.getInt("cantidad");
                    }
                    myCell.setCellValue(value);
                    
                }
                HSSFFormulaEvaluator evaluador = new HSSFFormulaEvaluator(myWorkBook) ;
                evaluador.evaluate(myCellF);
                contador++;
            }
            
            for (int b = 0; b < i; b++){
                mySheet.autoSizeColumn((short)b); //ajusta el ancho de la primera columna  
            }   
            //Vuelvo a la fila 0, a ingresar las formulas de totales
            myRow = mySheet.getRow(0);
            myCell = myRow.getCell(1);
            if (myCell == null) {
                    myCell = myRow.createCell(1);
                }
            char col = 'B';
            String formula = "SUM("+col+"3:"+col+(contador+2)+")";
            myCell.setCellFormula(formula);
            myCell.setCellStyle(azul);
            HSSFFormulaEvaluator evaluador = new HSSFFormulaEvaluator(myWorkBook) ;
            evaluador.evaluate(myCell);

            col='A';
            for(int c = 0; c < obrasIDlist.size();c++){
                myCell = myRow.getCell(c+2);
                if (myCell == null) {
                    myCell = myRow.createCell(c+2);
                }
                //Paso el char a int, lo incremento y lo vuelvo a char.
                int columnaInt = (int)col;
                String col1;
                if((columnaInt+c+2) >= 91){
                    col1 = "A"+(char) ((columnaInt+(c)-24));
                }else{
                    col1 = ""+(char)(columnaInt+(c+2));
                }
                
                formula = "SUM("+col1+"3:"+col1+(contador+2)+")";
                myCell.setCellFormula(formula);
                if((c+2) % 2 == 0){
                    myCell.setCellStyle(verde);
                }else{
                    myCell.setCellStyle(azul);
                }
                evaluador.evaluate(myCell);
            }
            
            mySheet.createFreezePane(2, 2);
            File dest = new File(FileManager.getDefaultFolder(), "customReport.xls");
            FileOutputStream out = new FileOutputStream(dest);
            myWorkBook.write(out);
            Desktop.getDesktop().open(dest);
            out.close();
            
        } catch (SQLException ex){
        } catch (Exception e) {
        }
        
    }
    
    public boolean reportRISeguimiento( Date desde, Date hasta) {
        
        //Array con los nombres de las campos
        //14
        String[] cabecera = {
            "RI N°", "COD. CC", "FECHA EMISIÓN", "ITEMS",
            "UNIDAD", "CANTIDAD", "FECHA NECESIDAD", "OC N°",
            "FECHA OC", "PROVEEDOR", "VALOR (SIN IVA)", 
            "FECHA DE ENTREGA REAL", "AGENDA COMPRAS(DÍAS)",
            "AGENDA ENTREGA (DÍAS)"
        };
        //Array con los nombres de las columnas en la db
        //Los ultimos 4 se calculan
        //10
        String[] columna = {
            "RI_NUM", "codigo", "fecha_emision", "detalle", "unidad",
            "cantidad", "fecha_necesidad", "oc_num", "fecha_oc", "proveedor",
            "valor", "fecha_entrega"
        };
        //POIFSFileSystem fs = new POIFSFileSystem(new InputStream() {})
        HSSFWorkbook myWorkBook = new HSSFWorkbook();
        HSSFSheet mySheet = myWorkBook.createSheet("Planilla de seguimiento de RI y OC");
        //Style
            HSSFCellStyle amarillo = myWorkBook.createCellStyle();
            amarillo.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            amarillo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            amarillo.setBorderBottom((short)14);
            amarillo.setBorderLeft((short)14);
            amarillo.setBorderRight((short)14);
            amarillo.setBorderTop((short)14);
            amarillo.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            amarillo.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            amarillo.setWrapText(true);
           
            
            HSSFCellStyle azul = myWorkBook.createCellStyle();
            azul.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
            azul.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            azul.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            azul.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            /*azul.setBorderBottom((short)14);
            azul.setBorderLeft((short)14);
            azul.setBorderRight((short)14);
            azul.setBorderTop((short)14);*/
            azul.setWrapText(true);
            
            
            HSSFCellStyle normal = myWorkBook.createCellStyle();
            normal.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            normal.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            normal.setWrapText(false);
            
            
            HSSFCellStyle verde = myWorkBook.createCellStyle();
            verde.setFillForegroundColor(HSSFColor.SEA_GREEN.index);
            verde.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            verde.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            verde.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            verde.setWrapText(true);
            /*verde.setBorderBottom((short)14);
            verde.setBorderLeft((short)14);
            verde.setBorderRight((short)14);
            verde.setBorderTop((short)14);*/
            
                int i = 1;
            
            //ENCABEZADOS
            
            HSSFRow myRow = mySheet.getRow(i);
            if (myRow == null) { myRow = mySheet.createRow(i); } 
            HSSFCell myCell = myRow.createCell(0);
            myCell.setCellValue(new HSSFRichTextString("FECHA"));
            myCell.setCellStyle(amarillo);
            myCell = myRow.createCell(1);
            myCell.setCellValue(new HSSFRichTextString(FechaUtil.getFecha(new Date(), FechaUtil.DATE_FORMAT_EXCEL)));
            myCell.setCellStyle(amarillo);
            i= i+2;
            myRow = mySheet.getRow(i);
            if (myRow == null) { myRow = mySheet.createRow(i); } 
            mySheet.addMergedRegion(new CellRangeAddress(i,i,0,6));
            myRow.setHeight((short)600);
            myCell = myRow.createCell(0);
            myCell.setCellValue(new HSSFRichTextString("CARGA DE DETALLE DE REQUERIMIENTO DE COMPRA"));
            myCell.setCellStyle(azul);
            myCell = myRow.createCell(7);
            mySheet.addMergedRegion(new CellRangeAddress(i,i,7,13));
            myRow.setHeight((short)600);
            myCell.setCellValue(new HSSFRichTextString("SEGUIMIENTO Y CONTROL DE ÓRDENES DE COMPRA"));
            myCell.setCellStyle(verde);
            //Siguiente renglón
            i++;
            
            myRow = mySheet.getRow(i);        
            if (myRow == null) { myRow = mySheet.createRow(i); }    
            //myRow.setHeight((short)600);
            int h = 0;
            for(String item:cabecera){
                myCell = myRow.createCell(h);
                myRow.setHeight((short)600);
                
                myCell.setCellValue(new HSSFRichTextString(item));
                if (h < 7){
                myCell.setCellStyle(azul);
                }else{
                    myCell.setCellStyle(verde);
                }
                h++;
            }
         
        
            
        String query= "select ri.*, it.*, OB.codigo from ri "+
                "left join ri_item it on ri.RIID = it.riid "+
                "left join obras OB on OB.id = ri.obraid "+
                "where ri.fecha_creacion between '"+ FechaUtil.getFechaSQL(desde) +
                "' and '" +FechaUtil.getFechaSQL(hasta) + "' ORDER BY ri.fecha_creacion asc";
        try {
            
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
        
            //Datos
            int contador = 0;
            while (rs.next()) {
                
                i++;
                myRow = mySheet.getRow(i);
                if (myRow == null) {
                    myRow = mySheet.createRow(i);
                }
                for (int j=0;j<12;j++){
                    myCell = myRow.createCell(j);  
                    if(j==2 || j==6 || j==8 || j==11){
                        myCell.setCellValue(new HSSFRichTextString(
                                FechaUtil.getFecha(rs.getDate(columna[j]), FechaUtil.DATE_FORMAT_EXCEL)));
                    }else{
                        myCell.setCellValue(new HSSFRichTextString(rs.getString(columna[j])));
                    }
                    myCell.setCellStyle(normal);
                    
                }
               
                
                try{
                     //Agenda compras =SI(D61="";" ";SI(K61="";H61-($O$2);+H61-K61))
                    HSSFCell myCellF = myRow.createCell(12);
                    int j = i+1;
                    String formula = "IF(C"+j+"=\"\",\" \",IF(I"+j+"=\"\",G"+j+"-($B$2),+G"+j+"-I"+j+"))";
                    myCellF.setCellFormula(formula);
                    myCellF.setCellStyle(normal);
                    //Agenda Entrega =SI(D61=0;" ";SI(N61=0;H61-$O$2;H61-N61))
                    HSSFCell myCellF2 = myRow.createCell(13);
                    formula = "IF(C"+j+"=\"\",\" \",IF(L"+j+"=\"\",G"+j+"-$B$2,G"+j+"-L"+j+"))";
                    myCellF2.setCellFormula(formula);
                    myCellF2.setCellStyle(normal);
                    HSSFFormulaEvaluator evaluador = new HSSFFormulaEvaluator(myWorkBook) ;
                    evaluador.evaluate(myCellF);
                    evaluador.evaluate(myCellF2);
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
            
            for (int b = 0; b < 14; b++){
                if(b == 1 ||b == 13){
                    mySheet.setColumnWidth(b, 7000);
                }else if(b==4 || b==5){
                    mySheet.setColumnWidth(b, 4000);
                }else
                    mySheet.autoSizeColumn((short)b); //ajusta el ancho de la primera columna  
            }   
            
            mySheet.createFreezePane(0,5);
            File dest = new File(FileManager.getDefaultFolder(),"SeguimientoRI.xls");
            FileOutputStream out = new FileOutputStream(dest);
            myWorkBook.write(out);
            Desktop.getDesktop().open(dest);
            out.close();
            return true;
        } catch (SQLException ex){
        } catch (Exception e) {
        }
        return false;
    }

    public JasperPrint InformeVencimientosOperario() {
        try {
            
            URL master = null;
            HashMap<String, Object> parametro = new HashMap<String, Object>();
            parametro.put("urlimagenZille", getClass().getResource("/Reportes/zille.png").toString());
            
           
            master = getClass().getResource("/Reportes/persona/Operario-Vencimiento.jasper");
            System.out.println("Cargando desde: " + master);
            if (master == null) {
                System.out.println("No se encuentra el archivo master.");
                //System.exit(2);
            }
            JasperReport masterReport = (JasperReport) JRLoader.loadObject(master);
            
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, conector);
            return jasperPrint;
        } catch (JRException j) {
            System.out.print(j.getMessage());
        }
        return null;
    }

    public JasperPrint InformeAlarmasProximas(Date fechaLimite) {
        try {
            ArrayList<ItemAlarmaBean> data = new ArrayList<ItemAlarmaBean>();
            // Busco alarmas proximas 
            AlarmasDAO aDao = new AlarmasDAO();
            aDao.conectar();
            data.addAll(aDao.findAlarmasAntesDe(fechaLimite));
            // Busco fecha de vencimientos próximas
            Date hoy = new Date();
            hoy = FechaUtil.resetTime(hoy);
 

            EquiposDAO edao=new EquiposDAO();
            edao.conectar();
            OperarioDAO odao = new OperarioDAO();
            odao.conectar();    
            //Buscar las VTO_VT con fecha prximo 20 dias.
            //Buscar las VTO_SEGURO con fecha proximo 20 días.
            ArrayList<ItemAlarmaBean> equipos = edao.getAlarmasEquiposForReport(hoy, fechaLimite);
            if(equipos != null && !equipos.isEmpty()) data.addAll(equipos);
            //Buscar las VtO_CARNET con fecha proximo 20 días.
            ArrayList<ItemAlarmaBean> operarios = odao.getAlarmasOperariosForReport(hoy, fechaLimite);
            data.addAll(operarios);
            
            Collections.sort(data);
            
            URL master = getClass().getResource("/Reportes/persona/Alarmas-Proximas.jasper");
            System.out.println("Cargando desde: " + master);
            if (master == null) {
                System.out.println("No se encuentra el archivo master.");
                //System.exit(2);
            }
            JasperReport masterReport = (JasperReport) JRLoader.loadObject(master);
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, null, 
                new JRBeanCollectionDataSource(data));
            return jasperPrint;
            
        } catch (JRException j) {
            System.out.print(j.getMessage());
        }
        return null;
    }
}
