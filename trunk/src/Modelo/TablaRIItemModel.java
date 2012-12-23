/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

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
                return aux.getCantidad();
            case 1:
                return aux.getUnidad();
            case 2:
                return aux.getDetalle();
            case 3:
                return aux.getObservacion();
            default:
                return null;
        }


    }
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex)
        {
           
            
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
                return "Cantidad";
            case 1:
                return "Unidad";
            case 2:
                return "Detalle";
            case 3:
                return "Observaciones";
            default:
                return null;
        }
    }
    
    public void insertarFila(ItemAlarma value, int row) {
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
                    aux.setCantidad(value.toString());
                    break;
                case 1:
                    aux.setUnidad(value.toString());
                    break;
                case 2:
                    aux.setDetalle((String)value);
                    break;
                case 3:
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
        
            return true;
        }
        
        
    

    @Override
    public int getColumnCount() {
        return 4;
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
