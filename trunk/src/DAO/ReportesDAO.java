/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Obras;
import Utils.FechaUtil;
import java.awt.Desktop;
import java.awt.Dialog.ModalExclusionType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author matuu
 */
public class ReportesDAO {

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
    public void reportEquipo(Date desde, Date hasta, int idEquipo) {
        try {

            URL master = null;
            URL subreport = null;
            Map parametro = new HashMap();
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
            //JasperPrintManager.printReport(jasperPrint, false);
            
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
            jviewer.setTitle("Informe");
            jviewer.setVisible(true);
            
            
            //Exporta el informe a PDF
//            String destFileNamePdf= getClass().getResource("/prueba.pdf").toString();
            //Creación del PDF
//            JasperExportManager.exportReportToPdfFile(jasperPrint, destFileNamePdf);
            
            
            

        } catch (JRException j) {
            System.out.print(j.getMessage());
        }
    }
    
    public void reportPersEqXObra(Date desde, Date hasta, int idObra) {
        try {

            URL master = null;
            URL subreport = null;
            URL subreport2 = null;
            Map parametro = new HashMap();
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
            //JasperPrintManager.printReport(jasperPrint, false);
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);

            jviewer.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
            jviewer.setTitle("Informe");

            jviewer.setVisible(true);

        } catch (JRException j) {
            System.out.print(j.getMessage());
        }
    }
    
    public void reportEqMatXObra(Date desde, Date hasta, int idObra) {
        try {

            URL master = null;
            URL subreport = null;
            URL subreport2 = null;
            Map parametro = new HashMap();
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
            //JasperPrintManager.printReport(jasperPrint, false);
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);

            jviewer.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
            jviewer.setTitle("Informe");

            jviewer.setVisible(true);

        } catch (JRException j) {
            System.out.print(j.getMessage());
        }
    }
    
    
    public void reportRegistrosResumen(String periodo){
        try {

            URL master = null;
            URL subreport = null;
            Map parametro = new HashMap();
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
            //JasperPrintManager.printReport(jasperPrint, false);
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);

            jviewer.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
            jviewer.setTitle("Informe");

            jviewer.setVisible(true);

        } catch (JRException j) {
            System.out.print(j.getMessage());
        }
    }
    
    public void reportCombustibleObra(Date desde, Date hasta, int idObra){
        try {

            URL master = null;
            URL subreport = null;
            Map parametro = new HashMap();
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
            //JasperPrintManager.printReport(jasperPrint, false);
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);

            jviewer.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
            jviewer.setTitle("Informe");

            jviewer.setVisible(true);

        } catch (JRException j) {
            System.out.print(j.getMessage());
        }
    }
    
    public void reportCombustibleResumen(Date desde, Date hasta){
        try {

            URL master = null;
            Map parametro = new HashMap();
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
            //JasperPrintManager.printReport(jasperPrint, false);
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);

            jviewer.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
            jviewer.setTitle("Informe");

            jviewer.setVisible(true);

        } catch (JRException j) {
            System.out.print(j.getMessage());
        }
    }
    public void reportCombustibleEquipo(Date desde, Date hasta, int idEquipo){
        try {

            URL master = null;
            URL subreport = null;
            Map parametro = new HashMap();
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
            //JasperPrintManager.printReport(jasperPrint, false);
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);

            jviewer.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
            jviewer.setTitle("Informe");

            jviewer.setVisible(true);

        } catch (JRException j) {
            System.out.print(j.getMessage());
        }
    }
    
    public void reportCombustibleEestacion(Date desde, Date hasta, int idEstacion){
        try {

            URL master = null;
            URL subreport = null;
            URL subreport2 = null;
            Map parametro = new HashMap();
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
            //JasperPrintManager.printReport(jasperPrint, false);
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);

            jviewer.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
            jviewer.setTitle("Informe");

            jviewer.setVisible(true);

        } catch (JRException j) {
            System.out.print(j.getMessage());
        }
    }
    
     public String reportOrdenTrabajo( int idOT, String interno){
         String result="";
        try {

            URL master = null;
            Map parametro = new HashMap();
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
            JRXlsExporter exporterXLS = new JRXlsExporter();
            exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT,  jasperPrint);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            String soName = System.getProperty("os.name").toUpperCase();
            String archivo="OT"+idOT +" ("+ interno+ ").xls";
            //String command = "start " +archivo ;
            File folder = new File("Ordenes\\");
            if (soName.equals("LINUX")) { //Si se ejecuta en Linux  
                    folder = new File("Ordenes/");
                    if(!folder.exists()){
                        folder.mkdirs();
                    }
                    archivo=folder.getAbsoluteFile()+"/"+archivo;
                    //command = "xdg-open "+archivo;
            }else if(soName.equals("WINDOWS")|| soName.equals("WINDOWS 7")) {  //si se ejecuta en Windows)
                    folder = new File("Ordenes\\");
                    if(!folder.exists()){
                        folder.mkdirs();
                    }
                    archivo=folder.getAbsoluteFile()+"\\"+archivo;
                    //command = "start " +archivo ;
            }
            exporterXLS.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, archivo);
            exporterXLS.exportReport();
            Desktop.getDesktop().open(new File(archivo));
            result="Se ha exportado correctamente la órden de trabajo.\n"
                    + "Archivo: "+archivo;

        } catch (IOException ex) {
            Logger.getLogger(ReportesDAO.class.getName()).log(Level.SEVERE, null, ex);
            result="Error al procesar la solicitud."; 
        } catch (JRException j) {
            System.out.print(j.getMessage());
            result="Error al procesar la solicitud.";
        }
        return result;
    }
    /*
    
     */

    public void reportCustom(int idOperario, ArrayList<Obras> obrasIDlist, Date desde, Date hasta) {
        String dest = "customReport.xls";
        HSSFWorkbook myWorkBook = new HSSFWorkbook();
        HSSFSheet mySheet = myWorkBook.createSheet();
        
        String query= "select  OP.nombre, OP.id FROM partediario PD "
                + "LEFT JOIN obras OB ON PD.obra = OB.id "
                + "LEFT JOIN operarios OP ON PD.operario = OP.id "
                + "WHERE PD.fecha <= '"+FechaUtil.getFechaSQL(hasta)+"' AND PD.fecha >= '"+FechaUtil.getFechaSQL(desde)+"' "
                + "AND PD.situacion =1 ";
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
            myCell.setCellValue(new HSSFRichTextString("NOMBRE"));
        //TOTAL
            myCell = myRow.createCell(1);
            myCell.setCellValue(new HSSFRichTextString("TOTAL"));
        //OBRAS
            for (int h = 0; h < obrasIDlist.size(); h++) {
                myCell = myRow.createCell(h+2);
                myCell.setCellValue(new HSSFRichTextString(obrasIDlist.get(h).getCodigo()));
            }
        //Datos
            while (rs.next()) {
                i++;
                myRow = mySheet.getRow(i);
                if (myRow == null) {
                    myRow = mySheet.createRow(i);
                }
            //Nombre
                myCell = myRow.createCell(0);
                myCell.setCellValue(new HSSFRichTextString(rs.getString("nombre")));
            //Total
                HSSFCell myCellF = myRow.createCell(1);
                int i2= i+1;
                String formula = "SUM(C"+i2+":AA"+i2+")";
                myCellF.setCellFormula(formula);
            //Obras
                    for (int j = 0; j < obrasIDlist.size(); j++) {    
                    String subq = "select count(PD.obra) as cantidad FROM partediario PD WHERE PD.fecha <= '"+
                            FechaUtil.getFechaSQL(hasta)+"' AND PD.fecha >= '"+ FechaUtil.getFechaSQL(desde) +
                            "' AND PD.situacion =1 AND PD.obra = "+ obrasIDlist.get(j).getId() + " and PD.operario = "+
                            rs.getInt("id");
                    ps = conector.prepareStatement(subq);
                    ResultSet rs2 = ps.executeQuery();
                    myCell = myRow.createCell(j+2);
                    int value = 0;
                    if(rs2.next()){
                        value = rs2.getInt("cantidad"); 
                    }
                    myCell.setCellValue(value);
                }
                HSSFFormulaEvaluator evaluador = new HSSFFormulaEvaluator(myWorkBook) ;
                evaluador.evaluate(myCellF);
            }
            for (int b = 0; b < i; b++){
                mySheet.autoSizeColumn((short)b); //ajusta el ancho de la primera columna
            }          
            FileOutputStream out = new FileOutputStream(dest);
            myWorkBook.write(out);
            Desktop.getDesktop().open(new File(dest));
            out.close();
            
        } catch (SQLException ex){
        } catch (Exception e) {
                e.printStackTrace();
        }
        
    }
    
}
