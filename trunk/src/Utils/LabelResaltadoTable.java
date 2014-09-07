/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Modelo.TablaAlarmasModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author m4tuu
 */
public class LabelResaltadoTable extends DefaultTableCellRenderer {

   
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
    boolean isSelected, boolean hasFocus, int row, int column){
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        switch(((TablaAlarmasModel)table.getModel()).getFila(row).getTipo()){
            case 2:
                cellComponent.setForeground(Color.RED);
                cellComponent.setFont(new Font("Arial", Font.BOLD, 12));
                break;
            case 1:
                cellComponent.setForeground(Color.ORANGE);
                cellComponent.setFont(new Font("Arial", Font.PLAIN, 12));
                break;
            case 0:
                cellComponent.setForeground(Color.BLUE);
                cellComponent.setFont(new Font("Arial", Font.PLAIN, 12));
                break;
        } 
        
        return cellComponent;
    }
}

