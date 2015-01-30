/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.EntidadAbstracta;
import Modelo.FamiliaEquipo;
import Modelo.PrecioHistorico;
import Modelo.PrecioHistoricoBean;
import Modelo.PrecioHistoricoDetalladoBean;
import Utils.FileManager;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
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
import org.apache.poi.ss.usermodel.RichTextString;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;

/**
 *
 * @author m4tuu
 */
public class PrecioHistoricoDAO extends DefaultHibernateDAO implements IAbstractDAO{

    FamiliaEquipo familia;
    
    public PrecioHistoricoDAO(FamiliaEquipo familia) {
        super();
        this.familia = familia;
    }
    
    @Override
    public ArrayList<EntidadAbstracta> cargarTodos() {
        return filtrarPorTexto("");
    }

    @Override
    public ArrayList<EntidadAbstracta> filtrarPorTexto(String text) {
        AbstractHibernateDAO dummy = new AbstractHibernateDAO(){};

        ArrayList<PrecioHistorico> listaResultado = null;

        try
        {
            dummy.iniciaOperacion();
            
            // Estoy viendo como hacer el query 
            // Utilizar esta quiery para filtrar y obtener todos si text == "" 
            SQLQuery query = dummy.getSession().createSQLQuery(
                    "SELECT distinct ph.* FROM precio_historico ph "
                    + "left join familia_equipo fa on fa.id = ph.familia_id "
                    + "left join tipo_costo tc on tc.id = ph.tipo_id "
                    + "where fa.id = "+ familia.getId() + " and ("
                    + "fa.nombre like '%" +text + "%' "
                    + "or ph.valor like '%"+ text +"%' "
                    + "or tc.nombre like '%"+ text +"%' "
                    + "or (ph.fechaAlta >= '" + text + "' and ph.fechaBaja <= '" + text + "') "
                    + ") order by fechaAlta desc").addEntity(PrecioHistorico.class);
            listaResultado = (ArrayList<PrecioHistorico>) query.list();
            
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
         AbstractHibernateDAO dummy = new AbstractHibernateDAO(){};
         BigInteger count = new BigInteger("0");
        try
        {
            dummy.iniciaOperacion();
            count = (BigInteger) dummy.getSession().createSQLQuery(
                    "select count(id) FROM precio_historico where familia_id = " + familia.getId()
                    ).uniqueResult();
        }
        catch (HibernateException he)
        {
            dummy.manejaExcepcion(he);
            count=new BigInteger("0");
        }
        finally
        {
            dummy.terminaOperacion();
        }
        return count.intValue();
    }
    
    public boolean insertNuevoPrecioHistorico(PrecioHistorico h1, PrecioHistorico h2) {
        AbstractHibernateDAO dummy = new AbstractHibernateDAO(){};
        boolean isOk = false;
        try {
            dummy.iniciaOperacion();
            dummy.getSession().createQuery("update PrecioHistorico ph set ph.fechaBaja = :fBaja "
                    + "where ph.familia.id = :familiaId and ph.fechaBaja is null")
                    .setDate("fBaja", new Date())
                    .setInteger("familiaId", h1.getFamilia().getId()).executeUpdate();
            dummy.getSession().save(h1);
            dummy.getSession().save(h2);
            isOk = true;
        } catch (HibernateException he) {
            dummy.manejaExcepcion(he);
            
        } finally {
            dummy.terminaOperacion();
        }
        return isOk;
    } 
    
    
    public ArrayList<PrecioHistoricoBean> obtenerCostosEntre(Date desde, Date hasta) {
        AbstractHibernateDAO dummy = new AbstractHibernateDAO(){};

        ArrayList<PrecioHistoricoBean> listaResultado = new ArrayList<PrecioHistoricoBean>();

        try
        {
            dummy.iniciaOperacion();
            Query query = dummy.getSession().createSQLQuery(
                    "select pd.fecha, count(f.id) as cantidad, "
                    + "(select nombre from familia_equipo where id = f.id) as nombreFamilia, "
                    + "(((select valor as posesion from precio_historico "
                    + "   where familia_id = f.id and tipo_id = 1 and (fechaBaja > pd.fecha or fechaBaja is null) and fechaAlta <= pd.fecha "
                    + "   order by id desc limit 1) "
                    + ") * count(f.id)) as posesion, "
                    + "(((select valor as utilizacion from precio_historico "
                    + "   where familia_id = f.id and tipo_id = 2 and (fechaBaja > pd.fecha or fechaBaja is null) and fechaAlta <= pd.fecha "
                    + "   order by id desc limit 1) "
                    + ") * count(f.id)) as utilizacion "
                    + "from partediario pd "
                    + "inner join registro_equipo re on re.id = pd.equipo "
                    + "inner join equipos e on re.equipo = e.id "
                    + "left join familia_equipo f on f.id = e.familia_equipo_id "
                    + "where pd.fecha between :fechaDesde and :fechaHasta and re.equipo != 1 "
                    + "group by pd.fecha, f.id "
                    + "order by pd.fecha ")
                    .setDate("fechaDesde", desde)
                    .setDate("fechaHasta", hasta);
            List<Object[]> objects = query.list();
            for(Object[] o:objects) {
                // Si el equipo del parte no tiene familia asignada, ignorar.
                if (o[2] == null) continue;
                listaResultado.add(new PrecioHistoricoBean(o));
            }     
            
        }
        catch (HibernateException he)
        {
            dummy.manejaExcepcion(he);
        }
        finally
        {
            dummy.terminaOperacion();
        }
        return listaResultado;
    }
    
    public ArrayList<PrecioHistoricoDetalladoBean> obtenerCostosDetalladoEntre(Date desde, Date hasta) {
        AbstractHibernateDAO dummy = new AbstractHibernateDAO(){};

        ArrayList<PrecioHistoricoDetalladoBean> listaResultado = new ArrayList<PrecioHistoricoDetalladoBean>();

        try
        {
            dummy.iniciaOperacion();
            Query query = dummy.getSession().createSQLQuery(
                    "select pd.fecha, pd.numero, o.nombre, "
                    + "(select nombre from familia_equipo where id = f.id) as nombreFamilia, "
                    + "e.n_interno, "
                    + "((select valor as posesion from precio_historico "
                    + "   where familia_id = f.id and tipo_id = 1 and (fechaBaja > pd.fecha or fechaBaja is null) and fechaAlta <= pd.fecha "
                    + "   order by id desc limit 1) "
                    + ") as posesion, "
                    + "((select valor as utilizacion from precio_historico "
                    + "   where familia_id = f.id and tipo_id = 2 and (fechaBaja > pd.fecha or fechaBaja is null) and fechaAlta <= pd.fecha "
                    + "   order by id desc limit 1) "
                    + ") as utilizacion "
                    + "from partediario pd "
                    + "inner join registro_equipo re on re.id = pd.equipo "
                    + "inner join operarios o on pd.operario = o.id "
                    + "inner join equipos e on re.equipo = e.id "
                    + "left join familia_equipo f on f.id = e.familia_equipo_id "
                    + "where pd.fecha between :fechaDesde and :fechaHasta and re.equipo != 1 "
                    + "order by pd.fecha ")
                    .setDate("fechaDesde", desde)
                    .setDate("fechaHasta", hasta);
            List<Object[]> objects = query.list();
            for(Object[] o:objects) {
                // Si el equipo del parte no tiene familia asignada, ignorar.
                if(o[3] == null) continue;
                listaResultado.add(new PrecioHistoricoDetalladoBean(o));
            }     
            
        }
        catch (HibernateException he)
        {
            dummy.manejaExcepcion(he);
        }
        finally
        {
            dummy.terminaOperacion();
        }
        return listaResultado;
    }
    
    public void ReporteXlsCostos(ArrayList<PrecioHistoricoBean> data) {
       
        HSSFWorkbook myWorkBook = new HSSFWorkbook();
        HSSFSheet mySheet = myWorkBook.createSheet("Costos en el periodo");
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
           "FECHA", "FAMILIA", "CANTIDAD", "COSTO POSESIÓN", "COSTO UTILIZACIÓN", "TOTAL" };
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
        i++;
        for(PrecioHistoricoBean ea : data){
            myRow = mySheet.getRow(i);
            if (myRow == null) {
                myRow = mySheet.createRow(i);
            }

            int c = 0;
            HSSFCell myCell;
            // Fecha  A
            myCell = myRow.createCell(c++);  
            CreationHelper ch = myWorkBook.getCreationHelper();
            HSSFCellStyle cellStyle = myWorkBook.createCellStyle();
                cellStyle.setDataFormat(ch.createDataFormat().getFormat("dd/MM/yyyy")); 
            myCell.setCellValue(ea.fecha);
            myCell.setCellStyle(cellStyle);
            
            // Familia B
            myCell = myRow.createCell(c++);
            myCell.setCellValue(new HSSFRichTextString(ea.nombreFamilia));
            
            // Cantidad C
            myCell = myRow.createCell(c++);
            myCell.setCellValue(ea.cantidad);
            
            // Posesión D
            myCell = myRow.createCell(c++);
            myCell.setCellValue(ea.posesion);
            
            //Utilización E
            myCell = myRow.createCell(c++);
            myCell.setCellValue(ea.utilizacion);
            
            //TOTAL
            myCell = myRow.createCell(c++);
            myCell.setCellFormula("SUM(D" + (i+1)+ ":E" +(i+1) + ")");
            i++;
        }
        

        for (int b = 0; b < 5; b++){
            mySheet.autoSizeColumn((short)b); //ajusta el ancho de la primera columna  
        }  
              
        FileOutputStream out;
        File dest = new File(FileManager.getDefaultFolder(), "CostosHistoricos.xls");
        try {
            out = new FileOutputStream(dest);
            myWorkBook.write(out);
            Desktop.getDesktop().open(dest);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrancosLicenciasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrancosLicenciasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void ReporteXlsCostosDetallado(ArrayList<PrecioHistoricoDetalladoBean> data) {
       
        HSSFWorkbook myWorkBook = new HSSFWorkbook();
        HSSFSheet mySheet = myWorkBook.createSheet("Costos detallados en el periodo.");
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
        //"select pd.fecha, pd.numero, o.nombre, f.nombre as nombreFamilia, e.n_interno, "
        
        //ENCABEZADOS
        String[] cabecera = {
           "FECHA", "NÚMERO PARTE", "NOMBRE", "FAMILIA", "N° EQUIPO INTERNO", 
           "COSTO POSESIÓN", "COSTO UTILIZACIÓN", "TOTAL" };
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
        i++;
        for(PrecioHistoricoDetalladoBean ea : data){
            myRow = mySheet.getRow(i);
            if (myRow == null) {
                myRow = mySheet.createRow(i);
            }

            int c = 0;
            HSSFCell myCell;
            // Fecha  A
            myCell = myRow.createCell(c++);  
            CreationHelper ch = myWorkBook.getCreationHelper();
            HSSFCellStyle cellStyle = myWorkBook.createCellStyle();
                cellStyle.setDataFormat(ch.createDataFormat().getFormat("dd/MM/yyyy")); 
            myCell.setCellValue(ea.fecha);
            myCell.setCellStyle(cellStyle);
            
            // NUMERO B
            myCell = myRow.createCell(c++);
            myCell.setCellValue(new HSSFRichTextString(ea.numero));
            
            // Nombre C
            myCell = myRow.createCell(c++);
            myCell.setCellValue(new HSSFRichTextString(ea.nombre));
            
            // Familia D
            myCell = myRow.createCell(c++);
            myCell.setCellValue(new HSSFRichTextString(ea.nombreFamilia));
            
            // N° interno E
            myCell = myRow.createCell(c++);
            myCell.setCellValue(new HSSFRichTextString(ea.nInterno));
            
            // Posesión F
            myCell = myRow.createCell(c++);
            myCell.setCellValue(ea.posesion);
            
            //Utilización G
            myCell = myRow.createCell(c++);
            myCell.setCellValue(ea.utilizacion);
            
            //TOTAL H
            myCell = myRow.createCell(c++);
            myCell.setCellFormula("SUM(F" + (i+1)+ ":G" +(i+1) + ")");
            i++;
        }
        

        for (int b = 0; b < 8; b++){
            mySheet.autoSizeColumn((short)b); //ajusta el ancho de la primera columna  
        }  
              
        FileOutputStream out;
        File dest = new File(FileManager.getDefaultFolder(), "CostosHistoricosDetallados.xls");
        try {
            out = new FileOutputStream(dest);
            myWorkBook.write(out);
            Desktop.getDesktop().open(dest);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrancosLicenciasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrancosLicenciasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
