/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author m4tuu
 */
public class FrancosLicenciasCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        //Obtengo el model
        if (table.getSelectedRow() == row) {
            c.setBackground(Color.decode("#87ceeb"));
            c.setForeground(Color.DARK_GRAY);
        } else {
            if (column == 3 || column == 7 || column == 10)
            {
                c.setForeground(Color.black);
                
                c.setBackground(Color.decode("#cc99ff"));
            }
            else {
                c.setBackground(UIManager.getColor("Table.Background"));
                c.setForeground(Color.DARK_GRAY);
            }
        }
        return c;
    }
    
}
