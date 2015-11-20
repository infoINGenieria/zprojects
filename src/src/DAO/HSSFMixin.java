/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 *
 * @author matuuar
 */
public class HSSFMixin {
    
    public final int STYLE_AMARILLO = 1;
    public final int STYLE_AZUL = 2;
    public final int STYLE_VERDE = 3;
    
    protected HSSFRow createRow(HSSFSheet mySheet, int i) {
         HSSFRow myRow = mySheet.getRow(i);        
         if (myRow == null) { myRow = mySheet.createRow(i); }
         return myRow;
     }
     
     protected HSSFCell createCell(HSSFRow row, int i, String content, HSSFCellStyle style) {
        HSSFCell myCell = row.createCell(i);
        myCell.setCellValue(new HSSFRichTextString(content));
        if(style != null) myCell.setCellStyle(style);
        return myCell;
     }
     
     protected HSSFCell createCell(HSSFRow row, int i, String content) {
         return createCell(row, i, content, null);
     }
     
     protected HSSFCell createCell(HSSFRow row, int i, int content) {
         HSSFCell myCell = row.createCell(i);
        myCell.setCellValue(content);
        return myCell;
     }
     
     protected HSSFCellStyle getCellStyle(HSSFWorkbook myWorkBook, int option) {
         switch(option) {
            case STYLE_AMARILLO:
                HSSFCellStyle amarillo = myWorkBook.createCellStyle();
                amarillo.setFillForegroundColor(HSSFColor.YELLOW.index);
                amarillo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                amarillo.setBorderBottom((short)14);
                amarillo.setBorderLeft((short)14);
                amarillo.setBorderRight((short)14);
                amarillo.setBorderTop((short)14);
                amarillo.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                amarillo.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                amarillo.setWrapText(true);
                return amarillo;           
            case STYLE_AZUL:
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
                return azul;
            case STYLE_VERDE:
                HSSFCellStyle verde = myWorkBook.createCellStyle();
                verde.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
                verde.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                verde.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                verde.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                verde.setWrapText(true);
                verde.setBorderBottom((short)14);
                verde.setBorderLeft((short)14);
                verde.setBorderRight((short)14);
                verde.setBorderTop((short)14);
                return verde;
            default:
                    return null;
         }
         
     }
}
