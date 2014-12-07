/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.DatosFrancoOperario;
import Modelo.EntidadAbstracta;
import Modelo.Operario;
import Utils.FechaUtil;
import ViewModel.ItemDetalleFrancosLicencias;
import ViewModel.ItemFrancoLicencia;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

/**
 *
 * @author m4tuu
 */
public class FrancosLicenciasDAO extends AbstractDAO {

    @Override
    public int guardar(EntidadAbstracta entidad) {
        throw new UnsupportedOperationException("No deberías llamar a este método");
    }

    @Override
    public int modificar(EntidadAbstracta entidad) {
        throw new UnsupportedOperationException("No deberías llamar a este método");
    }

    @Override
    public boolean eliminar(EntidadAbstracta entidad) {
        throw new UnsupportedOperationException("No deberías llamar a este método");
    }

    @Override
    public ArrayList<EntidadAbstracta> cargarTodos() {
        return filtrarPorTexto("");
    }    

    @Override
    public ArrayList<EntidadAbstracta> filtrarPorTexto(String text) {
        ArrayList<EntidadAbstracta> todos = new ArrayList<EntidadAbstracta>();
        String query = "";
        try {
            query = "select distinct sum(if (r.especial,1,0)) as francos, "
                    + "sum(if(o.descuenta_francos,1,0)) as devueltos, "
                    + "sum(if(o.descuenta_licencias,1,0)) as dias_vacaciones, "
                    + "op.nombre, op.fecha_ingreso, fl.* from operarios op "
                    + "left join  partediario pd  on pd.operario = op.id "
                    + "left join registro r on r.id = pd.horario "
                    + "inner join obras o on  o.id = pd.obra "
                    + "left join franco_licencia fl on fl.operario_id = op.id ";
            if (!text.isEmpty()) {
                query += "where op.nombre like '%" + text + "%' ";
            }
            query +="group by op.id order by op.nombre";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ItemFrancoLicencia ifl = new ItemFrancoLicencia();
                DatosFrancoOperario dfo = new DatosFrancoOperario();
                if (rs.getObject("id") != null){
                    dfo.setOperarioId(rs.getInt("operario_id"));
                    dfo.setId(rs.getInt("id"));
                    dfo.setAjusteFrancos(rs.getInt("ajuste_francos"));
                    dfo.setAjusteLicencias(rs.getInt("ajuste_licencias"));
                    dfo.setPagados(rs.getInt("pagados"));
                    dfo.setSolicitados1(rs.getInt("solicitados1"));
                    dfo.setSolicitados2(rs.getInt("solicitados2"));
                    dfo.setSale1(rs.getDate("sale1"));
                    dfo.setSale2(rs.getDate("sale2"));
                    dfo.setEntra1(rs.getDate("entra1"));
                    dfo.setEntra2(rs.getDate("entra2"));
                }
                ifl.francosEntidad = dfo;
                ifl.francosTrabajados = rs.getInt("francos");
                ifl.francosCompensatorios = rs.getInt("devueltos");
                ifl.nombre = rs.getString("nombre");
                ifl.diasLicenciasTomadas = rs.getInt("dias_vacaciones");
                ifl.diasLicencia = Operario.DiasVacaciones(rs.getDate("fecha_ingreso"));
                ifl.diasLicenciasAnteriores = Operario.DiasVacacionesAnteriores(rs.getDate("fecha_ingreso"));
                todos.add(ifl);
            }
        } catch (Exception ex) {
            
        }
        return todos;
    }
    
    public ItemFrancoLicencia getByIdOperario(int id) {
        String query = "";
        ItemFrancoLicencia ifl = new ItemFrancoLicencia();
        try {
            query = "select distinct sum(if (r.especial,1,0)) as francos, "
                    + "sum(if(o.descuenta_francos,1,0)) as devueltos, "
                    + "sum(if(o.descuenta_licencias,1,0)) as dias_vacaciones, "
                    + "op.nombre, op.fecha_ingreso, fl.* from operarios op "
                    + "left join partediario pd on pd.operario = op.id "
                    + "left join registro r on r.id = pd.horario "
                    + "inner join obras o on  o.id = pd.obra "
                    + "left join franco_licencia fl on fl.operario_id = op.id ";
            
            query += "where op.id = " + id + " group by op.id limit 1";
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                DatosFrancoOperario dfo = new DatosFrancoOperario();
                if (rs.getObject("id") != null){
                    dfo.setOperarioId(rs.getInt("operario_id"));
                    dfo.setId(rs.getInt("id"));
                    dfo.setAjusteFrancos(rs.getInt("ajuste_francos"));
                    dfo.setAjusteLicencias(rs.getInt("ajuste_licencias"));
                    dfo.setPagados(rs.getInt("pagados"));
                    dfo.setSolicitados1(rs.getInt("solicitados1"));
                    dfo.setSolicitados2(rs.getInt("solicitados2"));
                    dfo.setSale1(rs.getDate("sale1"));
                    dfo.setSale2(rs.getDate("sale2"));
                    dfo.setEntra1(rs.getDate("entra1"));
                    dfo.setEntra2(rs.getDate("entra2"));
                }
                ifl.francosEntidad = dfo;
                ifl.francosTrabajados = rs.getInt("francos");
                ifl.francosCompensatorios = rs.getInt("devueltos");
                ifl.nombre = rs.getString("nombre");
                ifl.diasLicenciasTomadas = rs.getInt("dias_vacaciones");
                ifl.diasLicencia = Operario.DiasVacaciones(rs.getDate("fecha_ingreso"));
                ifl.diasLicenciasAnteriores = Operario.DiasVacacionesAnteriores(rs.getDate("fecha_ingreso"));
                
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
        }
        return ifl;
    }
    
    public void ReporteXlsResumenFrancosLicencias(List<EntidadAbstracta> data) {
        String dest = "Francos&Licencias.xls";
        System.out.println("Cantidad de obras: "+data.size());
        //POIFSFileSystem fs = new POIFSFileSystem(new InputStream() {})
        HSSFWorkbook myWorkBook = new HSSFWorkbook();
        HSSFSheet mySheet = myWorkBook.createSheet("Francos y licencias");
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
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int anioPasado = cal.get(Calendar.YEAR) - 1 ;
        //ENCABEZADOS
        String[] cabecera = {
           "NOMBRE Y APELLIDO", "SUBTOTAL", "PAGOS", "TOTAL", "DIAS LIC. X AÑO",
            "PENDIENTE " + anioPasado, "LIC. DISP.", "SOLICITADOS", "SOLICITADOS2",
            "PENDIENTE ACTUAL", "SALE", "ENTRA", 
            "SALE", "ENTRA"
        };
        
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
            ItemFrancoLicencia ifl = (ItemFrancoLicencia) ea;
            i++;
            myRow = mySheet.getRow(i);
            if (myRow == null) {
                myRow = mySheet.createRow(i);
            }
            
            int c = 0;
            // Nombre
            HSSFCell myCell = myRow.createCell(c++);  
            myCell.setCellValue(new HSSFRichTextString(ifl.getNombre()));
            // subtotal
            myCell = myRow.createCell(c++);
            myCell.setCellValue(ifl.getFrancosAjustados());
            // pagados
            myCell = myRow.createCell(c++);
            myCell.setCellValue(ifl.getPagados());
            // total
            myCell = myRow.createCell(c++);
            myCell.setCellValue(ifl.getTotal());
            // licencias disponibles por año
            myCell = myRow.createCell(c++);
            myCell.setCellValue(ifl.getDiasLicenciaAnual());
            // pendientes
            myCell = myRow.createCell(c++);
            myCell.setCellValue(ifl.getLicenciasPendientes());
            // licencia diponibles
            myCell = myRow.createCell(c++);
            myCell.setCellValue(ifl.getLicenciaDisponibles());
            // solcitados 1
            myCell = myRow.createCell(c++);
            myCell.setCellValue(ifl.francosEntidad.getSolicitados1());
            // solicitados 2 
            myCell = myRow.createCell(c++);
            myCell.setCellValue(ifl.francosEntidad.getSolicitados2());
            // pendientes actuales
            myCell = myRow.createCell(c++);
            myCell.setCellValue(ifl.getPendientesActual());
            CreationHelper ch = myWorkBook.getCreationHelper();
            HSSFCellStyle cellStyle = myWorkBook.createCellStyle();
            cellStyle.setDataFormat(
                ch.createDataFormat().getFormat("dd/MM/yyyy")); 

            // sale 1
            myCell = myRow.createCell(c++);
            if (ifl.francosEntidad.getSale1() != null) {
                myCell.setCellValue(ifl.francosEntidad.getSale1());
                myCell.setCellStyle(cellStyle);
            }
            // entra 1
            myCell = myRow.createCell(c++);
            if (ifl.francosEntidad.getEntra1() != null) {
                myCell.setCellValue(ifl.francosEntidad.getEntra1());
                myCell.setCellStyle(cellStyle);
            }
            // sale 2
            myCell = myRow.createCell(c++);
            if (ifl.francosEntidad.getSale2() != null) {
                myCell.setCellValue(ifl.francosEntidad.getSale2());
                myCell.setCellStyle(cellStyle);
            }
            // entra 2
            myCell = myRow.createCell(c++);
            if (ifl.francosEntidad.getEntra2() != null) {
                
                myCell.setCellValue(ifl.francosEntidad.getEntra2());
                myCell.setCellStyle(cellStyle);
            }
        }
        

        for (int b = 0; b < 14; b++){
            mySheet.autoSizeColumn((short)b); //ajusta el ancho de la primera columna  
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
    
    public ArrayList<EntidadAbstracta> ReporteDetalles(int operarioId, boolean isFranco, boolean isLicencia, Date fecha){
        ArrayList<EntidadAbstracta> todos = new ArrayList<EntidadAbstracta>();
        String query = "";
        String query1 ="", sep2 ="";
        try {
            query = "select pd.fecha, o.codigo, pd.numero from partediario pd "
                    + "inner join obras o on o.id = pd.obra "
                    + "where pd.operario = ? ";
            if (isFranco && isLicencia) {
                query1 = " and (o.descuenta_francos is true or o.descuenta_licencias is true) ";
            } else if (isFranco) {
                query1 = " and o.descuenta_francos is true ";
            } else if (isLicencia) {
                query1 = " and o.descuenta_licencias is true ";
            }
            query += query1;
            if (fecha != null) {
                query += " and pd.fecha >= ?";
            }
            query += " order by pd.fecha";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, operarioId);
            if (fecha != null)
                ps.setDate(2, FechaUtil.getFechatoDB(fecha));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ItemDetalleFrancosLicencias aux = new ItemDetalleFrancosLicencias();
                aux.setFecha(rs.getDate("pd.fecha"));
                aux.setcCosto(rs.getString("o.codigo"));
                aux.setNumeroParteDiario(rs.getString("pd.numero"));
                todos.add(aux);
            }
            ps.close();
            rs.close();
        } catch(SQLException sql) {
            System.err.println("Error al cargar los detalles de francos y vacaciones.");
        }
        return todos;
    }
}
