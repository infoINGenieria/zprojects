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
 * @author matuuar
 */
public class ObrasTablaInforme extends AbstractTableModel  {
    private LinkedList datos = new LinkedList();
    private LinkedList listeners = new LinkedList();

    public ObrasTablaInforme(){
        super();
    }
    
    public void clean(){
        datos.clear();
        TableModelEvent evento;
        evento = new TableModelEvent(this);
        avisaSuscriptores (evento);
        
    }
    
    public void addRegistro (ItemObra item)
    {
        datos.add (item);

        TableModelEvent evento;
        evento = new TableModelEvent (this, this.getRowCount()-1,
            this.getRowCount()-1, TableModelEvent.ALL_COLUMNS,
            TableModelEvent.INSERT);

        avisaSuscriptores (evento);
         
    }
    
    public void delRegistro (ItemObra item)
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
       ItemObra aux;
       
        aux = (ItemObra)(datos.get(row));

        switch (col)
        {
            case 0:
                return aux.isSelected();
            case 1:
                return aux.getCodigo();
            case 2:
                return aux.getObra();
            case 3:
                return aux.getResponsable();
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
                return "Código";
            case 2:
                return "Nombre";
            case 3:
                return "Responsable";
            default:
                return null;
        }
    }
    
    public void insertarFila(ItemObra value, int row) {
        datos.remove(row);
        datos.add(row, value);
        TableModelEvent evento = new TableModelEvent (this, row, row, TableModelEvent.ALL_COLUMNS);
        avisaSuscriptores (evento);
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        ItemObra aux;

        aux = (ItemObra)(datos.get(row));
        try{
            switch (col)
            {
                case 0:
                    aux.setSelected(Boolean.parseBoolean(value.toString()));
                    break;
                case 1:
                    aux.setCodigo(value.toString());
                    break;
                case 2:
                    aux.setObra((String)value);
                    break;
                case 3:
                    aux.setResponsable((String)value);
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

    public ItemObra getFila(int row){
        return (ItemObra)(datos.get(row));
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
            if(col == 0) return true;
            return false;
        }
        
        
    

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
