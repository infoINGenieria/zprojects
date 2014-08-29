/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Matu
 */
public class ImageIconTable extends DefaultTableCellRenderer {

  /*
   * @see TableCellRenderer#getTableCellRendererComponent(JTable, Object, boolean, boolean, int, int)
   */
    @Override
  public Component getTableCellRendererComponent(JTable table, Object value,
                                                 boolean isSelected, boolean hasFocus, 
                                                 int row, int column) {
    int opcion = Integer.valueOf(value.toString());
    ImageIcon icon = null;
    switch(opcion) {
        case 1:
            icon = new ImageIcon(getClass().
                    getResource("/zilleprojects/resources/warn-icon.png"));
            break;
        case 2:
            icon = new ImageIcon(getClass().
                    getResource("/zilleprojects/resources/error-icon.png"));
            break;   
        default:
        case 0:
            icon = new ImageIcon(getClass().
                    getResource("/zilleprojects/resources/info-icon.png"));
            break;
    }
    setIcon(icon);
    
    return this;
  }
}

