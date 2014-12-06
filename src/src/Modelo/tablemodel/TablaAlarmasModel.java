/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.tablemodel;

import ViewModel.ItemAlarma;
import Utils.FechaUtil;
import java.util.Date;
import javax.swing.event.TableModelEvent;

/**
 *
 * @author Matu
 */
public class TablaAlarmasModel  extends ZilleAbstractTableModel { 

    @Override
    public Object getValueAt(int row, int col) {
       ItemAlarma aux;
       
        aux = (ItemAlarma)(datos.get(row));

        switch (col)
        {
            case 0:
                return aux.getTipo();
            case 1:
                return aux.getFecha();
            case 2:
                return aux.getMensaje();
            default:
                return null;
        }
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex)
        {
            case 0:          
                return Integer.class;
            case 1: 
                return Date.class;
            default:            
                return String.class;
        }
    }
    
    @Override
    public String getColumnName(int columnIndex)
    {

       switch (columnIndex)
        {
            case 0:
                return "";
            case 1:
                return "Fecha";
            case 2:
                return "Mensaje";
            default:
                return null;
        }
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        ItemAlarma aux;

        aux = (ItemAlarma)(datos.get(row));
        try{
            switch (col)
            {
                case 0:
                    aux.setTipo(Integer.parseInt(value.toString()));
                    break;
                case 1:
                    aux.setFecha(FechaUtil.getFecha(value.toString()));
                    break;
                case 2:
                    aux.setMensaje((String)value);
                    break;
            }
        }catch(IllegalArgumentException ex){
    }
        TableModelEvent evento = new TableModelEvent (this, row, row, col);
        avisaSuscriptores (evento);
    }
    
    @Override
    public int getRowCount() {   
        return datos.size();
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {      
        return false;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

}
