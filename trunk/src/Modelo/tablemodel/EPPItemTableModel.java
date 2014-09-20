/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.tablemodel;

import ViewModel.EPPItem;
import javax.swing.event.TableModelEvent;

/**
 *
 * @author m4tuu
 */
public class EPPItemTableModel extends ZilleAbstractTableModel{

    public EPPItemTableModel(){
        super();
    }

    @Override
    public Object getValueAt(int row, int col) {
       EPPItem aux;
      
        aux = (EPPItem)(datos.get(row));

        switch (col)
        {
            case 0:
                return aux.getNombre();
            case 1:
                return aux.getMedida();
            case 2:
                return aux.getValorCombo();
            case 3:
                return aux.getValorInt();
            default:
                return null;
        }
    }
    
    @Override
    public Class getColumnClass(int columnIndex) { 
        if(columnIndex == 3) return Integer.class;
        return String.class;
    }
    
    @Override
    public String getColumnName(int columnIndex)
    {

       switch (columnIndex)
        {
            case 0:
                return "NOMBRE";
            case 1:
                return "MEDIDA";
            case 2:
                return "TALLE";
            case 3:
                return "NÚMERO";
            default:
                return null;
        }
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        EPPItem aux;

        aux = (EPPItem)(datos.get(row));
        try{
            switch (col)
            {
                case 0:
                    aux.setNombre(value.toString());
                    break;
                case 1:
                    aux.setMedida(value.toString());
                    break;
                case 2:
                    aux.getValores().setValor(value.toString());
                    break;
                case 3:
                    aux.getValores().setValor(value.toString());
                    break;
            }
        } catch(IllegalArgumentException ex){ 
            System.err.println(ex.getMessage());
        }
        TableModelEvent evento = new TableModelEvent (this, row, row, col);
        avisaSuscriptores (evento);
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        EPPItem aux = (EPPItem) datos.get(row);
        if(aux.getEpp().tieneTalle()){
            if(col == 2) return true;
            return false;
        }else{
            if(col == 3) return true;
            return false;
        }     
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    
}
