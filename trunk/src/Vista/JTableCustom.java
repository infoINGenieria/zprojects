/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author matuu
 */
public class JTableCustom extends JTable{

    @Override
    public Component prepareRenderer(
        TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        if (isRowSelected(row)) {
            c.setBackground(Color.decode("#87ceeb"));
        } else {
            c.setBackground(Color.white);
        }
        return c;
    }

}
