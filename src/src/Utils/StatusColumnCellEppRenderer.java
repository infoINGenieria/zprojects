/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Modelo.tablemodel.EPPItemTableModel;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author m4tuu
 */
public class StatusColumnCellEppRenderer extends DefaultTableCellRenderer {
  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

    //Cells are by default rendered as a JLabel.
    JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);


    //Get the status for the current row.
    EPPItemTableModel tableModel = (EPPItemTableModel) table.getModel();
    
    if (tableModel.isCellEditable(row, col)) {
        
        if(table.getSelectedRow() == row){
            l.setBackground(UIManager.getColor("Table.selectionBackground"));
            l.setForeground(Color.DARK_GRAY);
        }else{
            l.setBackground(UIManager.getColor("Table.Background"));
        } 
    }else{
        if(table.getSelectedRow() == row){
            l.setBackground(UIManager.getColor("Table.selectionBackground"));
            l.setForeground(Color.GRAY);
        }else{
            l.setForeground(Color.GRAY);
            l.setBackground(Color.GRAY);
        } 
    }

  //Return the JLabel which renders the cell.
  return l;
  }
}
