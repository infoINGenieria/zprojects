/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Utils.FechaUtil;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Matu
 */
public class TablaRIItemModel  extends AbstractTableModel {

    private LinkedList datos = new LinkedList();
    private LinkedList listeners = new LinkedList();

    public TablaRIItemModel(){
        super();
    }
    
    public void clean(){
        datos.clear();
        TableModelEvent evento;
        evento = new TableModelEvent(this);
        avisaSuscriptores (evento);
        
    }
    
    public void addRegistro (RiItem item)
    {
        datos.add (item);

        TableModelEvent evento;
        evento = new TableModelEvent (this, this.getRowCount()-1,
            this.getRowCount()-1, TableModelEvent.ALL_COLUMNS,
            TableModelEvent.INSERT);

        avisaSuscriptores (evento);
         
    }
    
    public void delRegistro (int item)
    {
        datos.remove(item);

        TableModelEvent evento;
        evento = new TableModelEvent (this, this.getRowCount()-1,
            this.getRowCount()-1, TableModelEvent.ALL_COLUMNS,
            TableModelEvent.DELETE);

        avisaSuscriptores (evento);
         
    }
    

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
                return aux.getFecha_entrega();
            case 9:
                return aux.getEstado();
            case 10:
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
            case 6:
                return Date.class;
            case 7:
                return Date.class;
            case 8:
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
                return "Fecha Entrega";
            case 9:
                return "Estado";
            case 10:
                return "Observaciones";
            default:
                return null;
        }
    }
    
    public void insertarFila(RiItem value, int row) {
        datos.remove(row);
        datos.add(row, value);
        TableModelEvent evento = new TableModelEvent (this, row, row, TableModelEvent.ALL_COLUMNS);
        avisaSuscriptores (evento);
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
                    aux.setCantidad(value.toString());
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
                    aux.setFecha_entrega(FechaUtil.getFecha(value.toString()));
                    break;
                case 9:
                    break;
                case 10:
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

    public RiItem getFila(int row){
        return (RiItem)(datos.get(row));
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
            
            return false;
        }
        
        
    

    @Override
    public int getColumnCount() {
        return 11;
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        // Añade el suscriptor a la lista de suscriptores
        listeners.add (l);
    }


    @Override
    public void removeTableModelListener(TableModelListener l) {
        // Elimina los suscriptores.
        listeners.remove(l);
    }


    private void avisaSuscriptores (TableModelEvent evento)
    {
        int i;

        // Bucle para todos los suscriptores en la lista, se llama al metodo
        // tableChanged() de los mismos, pasándole el evento.
        for (i=0; i<listeners.size(); i++)
            ((TableModelListener)listeners.get(i)).tableChanged(evento);
    }


}
