/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


import java.util.Date;
import java.util.LinkedList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author matuu
 */
public class UsuariosTableModel extends AbstractTableModel{

    private LinkedList<Usuario> datos = new LinkedList<Usuario>();
    private LinkedList<Object> listeners = new LinkedList<Object>();

    public UsuariosTableModel(){
        super();

    }

    

    public void addFila (Usuario user)
    {
        datos.add (user);

        TableModelEvent evento;
        evento = new TableModelEvent (this, this.getRowCount()-1,
            this.getRowCount()-1, TableModelEvent.ALL_COLUMNS,
            TableModelEvent.INSERT);

        avisaSuscriptores (evento);

    }
    public void delFila (Usuario usuario)
    {
        datos.remove(usuario);

        TableModelEvent evento;
        evento = new TableModelEvent (this, this.getRowCount()-1,
            this.getRowCount()-1, TableModelEvent.ALL_COLUMNS,
            TableModelEvent.DELETE);

        avisaSuscriptores (evento);

    }
    
    @Override
    public Object getValueAt(int row, int col) {
       Usuario aux;

        aux = (Usuario)(datos.get(row));

        switch (col)
        {
            case 0:
                return aux.getId_user();
            case 1:
                return aux.getUser();
            case 2:
                return aux.getRol();
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
                return "ID";
            case 1:
                return "Nombre de usuario";
           case 2:
               return "Rol";
            
            default:
                return null;
        }
    }


    public void insertarFila(Usuario value, int row) {
        datos.remove(row);
        datos.add(row, value);
        TableModelEvent evento = new TableModelEvent (this, row, row, TableModelEvent.ALL_COLUMNS);
        avisaSuscriptores (evento);
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        Usuario aux;
        aux = (Usuario)(datos.get(row));
try{
        switch (col)
        {
            case 0:
                aux.setId_user((Integer)value);
                break;
            case 1:
                aux.setUser((String)value);
                break;
            case 2:
                aux.setRol((String)value);
                break;

        }
    }catch(IllegalArgumentException ex){
        /*if (SwingUtilities.isEventDispatchThread()) {
                        Vista.OpcionPanel.showMessageDialog(null,
                            "La columna \"" + getColumnName(col)
                            + "\" solo acepta el formato hh:mm o hh:mm:ss.");
                    } else {
                        System.err.println("La columna \"" + getColumnName(col)
                            + "\" solo acepta el formato hh:mm.");
                    }*/

    }

        TableModelEvent evento = new TableModelEvent (this, row, row, col);

        avisaSuscriptores (evento);


    }


    @Override
    public int getRowCount() {

        return datos.size();
    }

    public Usuario getFila(int row){
        if(datos.size()!=0)
            return (Usuario)(datos.get(row));
        return null;
    }


    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        
            return false;
    }



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
