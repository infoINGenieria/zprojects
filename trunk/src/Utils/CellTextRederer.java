/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author matu
 */
public class CellTextRederer extends JTextField implements TableCellRenderer {

    public CellTextRederer() {
        super();
    }

    public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean isSelected, boolean hasFocus, int row, int column) {
        Color especial= new Color(205,48,169);
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        try {
            if (valor != null && valor.getClass().equals(Class.forName("Calendar"))) {
                this.setText(sdf.format(((Calendar) valor).getTime()));
                if( ((Calendar) valor).get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||
                        ((Calendar) valor).get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY){
                    this.setBackground(especial);
                }
            }
            else {
                this.setText("");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CellTextRederer.class.getName()).log(Level.SEVERE, null, ex);
        }
   setHorizontalAlignment(CENTER);
   setOpaque(true);
   if (isSelected || hasFocus){
       this.setBackground(especial);
   }    
   else{
       this.setBackground(SystemColor.control);
   }
    

   return this;

    }

}
