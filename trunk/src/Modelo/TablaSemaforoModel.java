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
 * @author matuuar
 */
public class TablaSemaforoModel extends AbstractTableModel {

    private LinkedList datos = new LinkedList();
    private LinkedList listeners = new LinkedList();

    public TablaSemaforoModel(){
        super();
    }
    
    public void clean(){
        datos.clear();
        TableModelEvent evento;
        evento = new TableModelEvent(this);
        avisaSuscriptores (evento);
        
    }
    
    public void addRegistro (ItemSemaforo item)
    {
        datos.add (item);

        TableModelEvent evento;
        evento = new TableModelEvent (this, this.getRowCount()-1,
            this.getRowCount()-1, TableModelEvent.ALL_COLUMNS,
            TableModelEvent.INSERT);

        avisaSuscriptores (evento);
         
    }
    
    public void delRegistro (ItemSemaforo item)
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
    
    public void insertarFila(ItemSemaforo value, int row) {
        datos.remove(row);
        datos.add(row, value);
        TableModelEvent evento = new TableModelEvent (this, row, row, TableModelEvent.ALL_COLUMNS);
        avisaSuscriptores (evento);
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

    public ItemSemaforo getFila(int row){
        return (ItemSemaforo)(datos.get(row));
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        
            return false;
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