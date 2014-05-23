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
 * @author m4tuu
 */
public class EPPTableModel extends AbstractTableModel{

    private LinkedList datos = new LinkedList();
    private LinkedList listeners = new LinkedList();

    public EPPTableModel(){
        super();
    }
    
    public void clean(){
        datos.clear();
        TableModelEvent evento;
        evento = new TableModelEvent(this);
        avisaSuscriptores (evento);     
    }
    
    public void add (EPPItem item)
    {
        datos.add (item);

        TableModelEvent evento;
        evento = new TableModelEvent (this, this.getRowCount()-1,
            this.getRowCount()-1, TableModelEvent.ALL_COLUMNS,
            TableModelEvent.INSERT);
        avisaSuscriptores (evento);
         
    }
    
    public void delete (EPPItem item)
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
    
    public void insertarFila(EPPItem value, int row) {
        datos.remove(row);
        datos.add(row, value);
        TableModelEvent evento = new TableModelEvent (this, row, row, TableModelEvent.ALL_COLUMNS);
        avisaSuscriptores (evento);
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
                    aux.valores.setValor(value.toString());
                    break;
                case 3:
                    aux.valores.setValor(value.toString());
                    break;
            }
        } catch(IllegalArgumentException ex){ 
            System.err.println(ex.getMessage());
        }
        TableModelEvent evento = new TableModelEvent (this, row, row, col);
        avisaSuscriptores (evento);
    }
    
      @Override
    public int getRowCount() {   
        return datos.size();
    }

    public EPPItem getFila(int row){
        return (EPPItem)(datos.get(row));
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
