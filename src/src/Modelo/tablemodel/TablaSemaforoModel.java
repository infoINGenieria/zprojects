/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.tablemodel;

import ViewModel.ItemSemaforo;
import Utils.FechaUtil;
import java.util.Date;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author matuuar
 */
public class TablaSemaforoModel extends ZilleAbstractTableModel {


    public TablaSemaforoModel(){
        super();
    }

    @Override
    public Object getValueAt(int row, int col) {
       ItemSemaforo aux;
       
        aux = (ItemSemaforo)(datos.get(row));

        switch (col)
        {
            case 0:
                return aux.isAlertar();
            case 1:
                return aux.getNombre();
            case 2:
                return aux.getTotal();
            case 3:
                return aux.getUltimaEntrega();
            default:
                return null;
        }


    }
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex)
        {
            case 0:
                return Boolean.class;
            case 1:
                return Integer.class;
            case 2:
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
                return "NOMBRE";
            case 2:
                return "TOTAL";
            case 3:
                return "ÚLTIMA ENTREGA";
            default:
                return null;
        }
    }
    
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        ItemSemaforo aux;

        aux = (ItemSemaforo)(datos.get(row));
        try{
            switch (col)
            {
                case 0:
                    aux.setAlertar((Boolean) value);
                    break;
                case 1:
                    aux.setNombre(value.toString());
                    break;
                case 2:
                    aux.setTotal((Integer)value);
                    break;
                case 3:
                    aux.setUltimaEntrega(FechaUtil.getFecha(value.toString()));
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
        return 4;
    }

}
