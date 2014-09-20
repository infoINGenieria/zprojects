/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.tablemodel;

import Modelo.EntidadAbstracta;
import java.util.LinkedList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author m4tuu
 */
public abstract class ZilleAbstractTableModel extends AbstractTableModel{

    protected LinkedList<EntidadAbstracta> datos = new LinkedList<EntidadAbstracta>();
    protected LinkedList<TableModelListener> listeners = new LinkedList<TableModelListener>();
    
    
    @Override
    public int getRowCount() {   
        return datos.size();
    }
    
//    @Override
//    public abstract int getColumnCount();
//    
//    @Override
//    public abstract Object getValueAt(int row, int col) ;
//    
//    @Override
//    public abstract Class getColumnClass(int columnIndex);
//    
//    @Override
//    public abstract String getColumnName(int columnIndex);
    
    
    public void addFila (EntidadAbstracta reg) { 
        datos.add (reg);
        TableModelEvent evento;
        evento = new TableModelEvent (this, this.getRowCount()-1,
            this.getRowCount()-1, TableModelEvent.ALL_COLUMNS,
            TableModelEvent.INSERT);
        avisaSuscriptores (evento);
    }
    
    public void delFila (EntidadAbstracta reg) {
        datos.remove(reg);
        TableModelEvent evento;
        evento = new TableModelEvent (this, this.getRowCount()-1,
            this.getRowCount()-1, TableModelEvent.ALL_COLUMNS,
            TableModelEvent.DELETE);

        avisaSuscriptores (evento);
    }
    
    public void insertarFila(EntidadAbstracta value, int row) {
        datos.remove(row);
        datos.add(row, value);
        TableModelEvent evento = new TableModelEvent (this, row, row, TableModelEvent.ALL_COLUMNS);
        avisaSuscriptores (evento);
    }
    
    public EntidadAbstracta getFila(int row){
        if(datos.size()!=0)
            return (EntidadAbstracta)(datos.get(row));
        return null;
    }
    
    public void clean(){
        datos.clear();
        TableModelEvent evento;
        evento = new TableModelEvent(this);
        avisaSuscriptores (evento);
        
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

    @Override
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        
            return false;
    }


    protected void avisaSuscriptores (TableModelEvent evento)
    {
        int i;

        // Bucle para todos los suscriptores en la lista, se llama al metodo
        // tableChanged() de los mismos, pasándole el evento.
        for (i=0; i<listeners.size(); i++)
            ((TableModelListener)listeners.get(i)).tableChanged(evento);
    }
}
