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
public class TablaParametrosModel extends AbstractTableModel{
    private LinkedList datos = new LinkedList();
    private LinkedList listeners = new LinkedList();

    public TablaParametrosModel(){
        super();
    }
    
    public void clean(){
        datos.clear();
        TableModelEvent evento;
        evento = new TableModelEvent(this);
        avisaSuscriptores (evento);
        
    }
    
    public void addRegistro (Parametro item)
    {
        datos.add (item);

        TableModelEvent evento;
        evento = new TableModelEvent (this, this.getRowCount()-1,
            this.getRowCount()-1, TableModelEvent.ALL_COLUMNS,
            TableModelEvent.INSERT);

        avisaSuscriptores (evento);
         
    }
    
    public void delRegistro (Parametro item)
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
       Parametro aux;
       
        aux = (Parametro)(datos.get(row));

        switch (col)
        {
            case 0:
                return aux.getClave();
            case 1:
                return aux.getClaveGrupo();
            case 2:
                return aux.getValor();
            default:
                return null;
        }


    }
    @Override
    public Class getColumnClass(int columnIndex) {
        return String.class;
    }
    
    @Override
    public String getColumnName(int columnIndex)
    {

       switch (columnIndex)
        {
            case 0:
                return "Clave";
            case 1:
                return "Grupo";
            case 2:
                return "Valor";
            default:
                return null;
        }
    }
    
    public void insertarFila(Parametro value, int row) {
        datos.remove(row);
        datos.add(row, value);
        TableModelEvent evento = new TableModelEvent (this, row, row, TableModelEvent.ALL_COLUMNS);
        avisaSuscriptores (evento);
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        Parametro aux;

        aux = (Parametro)(datos.get(row));
        try{
            switch (col)
            {
                case 0:
                    aux.setClave(value.toString());
                    break;
                case 1:
                    aux.setClaveGrupo(value.toString());
                    break;
                case 2:
                    aux.setValor(value.toString());
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

    public Parametro getFila(int row){
        return (Parametro)(datos.get(row));
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
            if (col==0)
                return false;
            return true;
        }
        
        
    

    @Override
    public int getColumnCount() {
        return 3;
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
