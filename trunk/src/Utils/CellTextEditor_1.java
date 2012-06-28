/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.sql.Time;
import java.util.EventObject;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author matu
 */
public class CellTextEditor_1 extends JTextField implements TableCellEditor {

  private String temp=null;
  private boolean editable;
 
 public CellTextEditor_1(boolean editable)
   {
   super("");
   this.editable = editable;
   
    this.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                FocusGained(evt);
            }
        });
 }

 private void FocusGained(java.awt.event.FocusEvent evt) {
        this.selectAll();
        temp=this.getText();
    }

 @Override
    protected boolean processKeyBinding(final KeyStroke ks,final KeyEvent e, final int condition, final boolean pressed) {
        if (hasFocus()) {
            return super.processKeyBinding(ks, e, condition, pressed);
        } else {
            requestFocus();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    processKeyBinding(ks, e, condition, pressed);
                }
            });
        return true;
        }
    }


 public Component getTableCellEditorComponent(JTable tabla, Object valor, boolean isSelected, int fila, int columna)
   {
   this.setBackground(tabla.getBackground());
   if(valor.getClass()==Integer.class){
       temp= Integer.toString((Integer)valor);
   }else if(valor.getClass()==Float.class){
       temp= Float.toString((Float)valor);
   }else if(valor==Time.class){
       temp=((Time)valor).toString();
   }
   this.setText(temp);
   return this;
   }

 public Object getCellEditorValue()
   {
   return temp;
   }

 public boolean isCellEditable(EventObject e)
   {
   return editable;
   }

 public boolean shouldSelectCell(EventObject e)
   {
   return true;  
   }

 

 public void cancelCellEditing()
   { this.setText(temp);
    }

 public void addCellEditorListener(CellEditorListener cel)
   {}

 public void removeCellEditorListener(CellEditorListener cel)
   {}

 public void setCellEditable(boolean editable)
   {
   this.editable = editable;
   }

    public boolean stopCellEditing() {
        return true;
    }

}
