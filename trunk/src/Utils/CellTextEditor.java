/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

import java.awt.Component;
import java.awt.event.KeyEvent;
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
public class CellTextEditor extends JTextField implements TableCellEditor {

  private String temp=null;
  private boolean editable;
 
 public CellTextEditor(boolean editable)
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


    @Override
 public Component getTableCellEditorComponent(JTable tabla, Object valor, boolean isSelected, int fila, int columna)
   {
   this.setBackground(tabla.getBackground());
   if(valor.getClass()==Integer.class){
       temp= Integer.toString((Integer)valor);
   }else if(valor.getClass()==Float.class){
       temp= Float.toString((Float)valor);
   }
   this.setText(temp);
   return this;
   }

    @Override
 public Object getCellEditorValue()
   {
   return temp;
   }

    @Override
 public boolean isCellEditable(EventObject e)
   {
   return editable;
   }

    @Override
 public boolean shouldSelectCell(EventObject e)
   {
   return true;  
   }

 

    @Override
 public void cancelCellEditing()
   { this.setText(temp);
    }

    @Override
 public void addCellEditorListener(CellEditorListener cel)
   {}

    @Override
 public void removeCellEditorListener(CellEditorListener cel)
   {}

 public void setCellEditable(boolean editable)
   {
   this.editable = editable;
   }

    @Override
    public boolean stopCellEditing() {
        return true;
    }

}
