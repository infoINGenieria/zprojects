/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.tablemodel;


import Modelo.Usuario;
import java.util.LinkedList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;


/**
 *
 * @author matuu
 */
public class UsuariosTableModel extends ZilleAbstractTableModel{


    public UsuariosTableModel(){
        super();

    }

    
    @Override
    public Object getValueAt(int row, int col) {
       Usuario aux;

        aux = (Usuario)(datos.get(row));

        switch (col)
        {
            case 0:
                return aux.getId();
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


    @Override
    public void setValueAt(Object value, int row, int col) {
        Usuario aux;
        aux = (Usuario)(datos.get(row));
try{
        switch (col)
        {
            case 0:
                aux.setId((Integer)value);
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



    @Override
    public int getColumnCount() {
        return 3;
    }

}
