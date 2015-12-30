/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.tablemodel;

import Modelo.RiItem;
import Utils.FechaUtil;
import java.util.Date;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Matu
 */
public class TablaRIItemModel  extends ZilleAbstractTableModel {

     @Override
    public Object getValueAt(int row, int col) {
       RiItem aux;
       
        aux = (RiItem)(datos.get(row));

        switch (col)
        {
            case 0:
                return aux.getFecha_necesidad();
            case 1:
                return aux.getCantidad();
            case 2:
                return aux.getUnidad();
            case 3:
                return aux.getDetalle();
            case 4:
                return aux.getProveedor();
            case 5:
                return aux.getOC_num();
            case 6: 
                return aux.getFecha_emision();
            case 7: 
                return aux.getFecha_oc();
            case 8:
                return aux.getValor();
            case 9:
                return aux.getFecha_entrega();
            case 10:
                return aux.getEstado();
            case 11:
                return aux.getObservacion();
                
            default:
                return null;
        }


    }
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex)
        {
            case 0:
                return Date.class;
            case 1:
                return Integer.class;
            case 6:
                return Date.class;
            case 7:
                return Date.class;
            case 9:
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
               return "Fecha Necesidad";
            case 1:
                return "Cantidad";
            case 2:
                return "Unidad";
            case 3:
                return "Detalle";
            case 4:
                return "Proveedor";
            case 5:
                return "Número OC";
            case 6: 
                return "Fecha Emisión";
            case 7:
                return "Fecha OC";
            case 8:
                return "Valor OC";
            case 9:
                return "Fecha Entrega";
            case 10:
                return "Estado";
            case 11:
                return "Observaciones";
            default:
                return null;
        }
    }
        
    @Override
    public void setValueAt(Object value, int row, int col) {
        RiItem aux;

        aux = (RiItem)(datos.get(row));
        try{
            switch (col)
            {
                case 0:
                    aux.setFecha_necesidad(FechaUtil.getFecha(value.toString()));
                    break;
                case 1:
                    aux.setCantidad((Integer)value);
                    break;
                case 2:
                    aux.setUnidad(value.toString());
                    break;
                case 3:
                    aux.setDetalle((String)value);
                    break;
                case 4:
                    aux.setProveedor(value.toString());
                    break;
                case 5:
                    aux.setOC_num(value.toString());
                    break;
                case 6:
                    aux.setFecha_emision(FechaUtil.getFecha(value.toString()));
                    break;
                case 7:
                    aux.setFecha_oc(FechaUtil.getFecha(value.toString()));
                    break;
                case 8:
                    aux.setValor(value.toString());
                    break;
                case 9:
                    aux.setFecha_entrega(FechaUtil.getFecha(value.toString()));
                    break;
                case 10:
                    break;
                case 11:
                    aux.setObservacion((String)value);
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
        return 12;
    }

    

}
