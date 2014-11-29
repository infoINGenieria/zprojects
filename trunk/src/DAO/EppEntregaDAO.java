/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.EPPOperario;
import Modelo.EntidadAbstracta;
import Modelo.EppEntrega;
import Modelo.EppEntregaItem;
import Modelo.Operario;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.util.CellRangeAddress;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;

/**
 *
 * @author m4tuu
 */
public class EppEntregaDAO extends AbstractHibernateDAO implements IAbstractDAO {

    @Override
    public void conectar() {
        
    }

    @Override
    public int guardar(EntidadAbstracta entidad) {
        if (almacenaEntidad(entidad)) {
            
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public int modificar(EntidadAbstracta entidad) {
        return guardar(entidad);
    }

    @Override
    public boolean eliminar(EntidadAbstracta entidad) {
        return eliminarEntidad(entidad);
    }

    @Override
    public ArrayList<EntidadAbstracta> cargarTodos() {
        List<EppEntrega> list = getListaEntidades(EppEntrega.class);
        ArrayList<EntidadAbstracta> returnList = new ArrayList<EntidadAbstracta>(list);
        return returnList;
    }

    @Override
    public ArrayList<EntidadAbstracta> filtrarPorTexto(String text) {
        AbstractHibernateDAO dummy = new AbstractHibernateDAO(){};

        ArrayList<EppEntrega> listaResultado = null;

        try
        {
            dummy.iniciaOperacion();
            SQLQuery query = dummy.getSession().createSQLQuery(
                    "SELECT distinct ee.* FROM epp_entrega ee "
                    + "left join operarios op on op.id = ee.operarioId "
                    + "where op.nombre like '%" +text + "%' or ee.observaciones like '%"+ text + "%'").addEntity(EppEntrega.class);
            listaResultado = (ArrayList<EppEntrega>) query.list();
            
        }
        catch (HibernateException he)
        {
            dummy.manejaExcepcion(he);
        }
        finally
        {
            dummy.terminaOperacion();
        }
        ArrayList<EntidadAbstracta> list1 = new ArrayList<EntidadAbstracta>(listaResultado);
        return list1;
    }

    @Override
    public int count(String name) {
        return (int) AbstractHibernateDAO.count(EppEntrega.class);
    }
    
    
    public void ReporteXlsResumenEntregaEpp(List<EntidadAbstracta> data) {

        
        /*
         * Exportar lo filtrado en la tabla a xla
         * 
         */
        String dest = "EntregaEpp.xls";
        System.out.println("Cantidad de entregas de indumentaria: "+data.size());
        //POIFSFileSystem fs = new POIFSFileSystem(new InputStream() {})
        HSSFWorkbook myWorkBook = new HSSFWorkbook();
        HSSFSheet mySheet = myWorkBook.createSheet("Entregas de indumentaria");
        //Style   
        HSSFCellStyle azul = myWorkBook.createCellStyle();
        azul.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
        azul.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        azul.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        azul.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        azul.setBorderBottom((short)14);
        azul.setBorderLeft((short)14);
        azul.setBorderRight((short)14);
        azul.setBorderTop((short)14);
        azul.setWrapText(true);
        
        //ENCABEZADOS
        String[] cabecera = {
           "NOMBRE Y APELLIDO", "FECHA", "OBSERVACIONES", "EPP", "MEDIDA ENTRAGADA", "MEDIDA OPERARIO"
        };
        String mergingCell="";
        int i = 0;
        HSSFRow myRow = mySheet.getRow(i);
        if (myRow == null) { myRow = mySheet.createRow(i); }
        int h = 0;
        for(String item:cabecera){
            HSSFCell myCell = myRow.createCell(h);
            myRow.setHeight((short)600);

            myCell.setCellValue(new HSSFRichTextString(item));

            myCell.setCellStyle(azul);
            h++;
        }
        for(EntidadAbstracta ea : data){
            EppEntrega ifl = (EppEntrega) ea;
            Operario oper = AbstractHibernateDAO.getEntidad(ifl.getOperarioId(), Operario.class);
            
            //Mergear celdas de operario y entrega
            
            int idx=0;
            
            
            for(EppEntregaItem item : ifl.getItems()){ 
                i++;
                myRow = mySheet.getRow(i);
                if (myRow == null) {
                    myRow = mySheet.createRow(i);
                }

                int c = 0;
                HSSFCell myCell;
                if (idx == 0){
                    idx = i;
                    // Nombre
                    myCell = myRow.createCell(c++);  
                    myCell.setCellValue(new HSSFRichTextString(oper.getNombre()));
                    // Fecha
                    CreationHelper ch = myWorkBook.getCreationHelper();
                    HSSFCellStyle cellStyle = myWorkBook.createCellStyle();
                    cellStyle.setDataFormat(
                        ch.createDataFormat().getFormat("dd/MM/yyyy")); 
                    myCell = myRow.createCell(c++);
                    myCell.setCellValue(ifl.getFecha());
                    myCell.setCellStyle(cellStyle);
                    // OBSERVACIONES
                    myCell = myRow.createCell(c++);
                    myCell.setCellValue(ifl.getObservaciones());
                } else {
                    c = 3;
                }
                // EPP
                myCell = myRow.createCell(c++);
                myCell.setCellValue(item.getEpp().getNombre());
                // MEDIDA
                myCell = myRow.createCell(c++);
                myCell.setCellValue(item.getMedida());
                // MEDIDA OPERARIO
                myCell = myRow.createCell(c++);
                EPPOperario op = ((EPPOperario) AbstractHibernateDAO.getEntidad(EPPOperario.class, 
                        "FROM EPPOperario eo where eo.operario.id = " + ifl.getOperarioId() +
                        " and eo.epp.id = " + item.getEpp().getId()));
                myCell.setCellValue(op == null ? "" : op.getValor());
                
 
            }
            mergingCell += idx + "-" + ifl.getItems().size() + ";";
        }
        

        for (int b = 0; b < 6; b++){
            mySheet.autoSizeColumn((short)b); //ajusta el ancho de la primera columna  
        }  
        //merging
        System.out.println("Marging: " + mergingCell);
        for(String par: mergingCell.split(";")){
            String[] cell = par.split("-");
            try {
                int start = Integer.parseInt(cell[0]);
                int end =Integer.parseInt(cell[1]);
                end = start + end -1;
                mySheet.addMergedRegion(new CellRangeAddress(start, end, 0, 0));
                mySheet.addMergedRegion(new CellRangeAddress(start, end, 1, 1));
                mySheet.addMergedRegion(new CellRangeAddress(start, end, 2, 2));
            }
            catch(Exception e) { }
        }        
        FileOutputStream out;
        try {
            out = new FileOutputStream(dest);
            myWorkBook.write(out);
            Desktop.getDesktop().open(new File(dest));
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrancosLicenciasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrancosLicenciasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
