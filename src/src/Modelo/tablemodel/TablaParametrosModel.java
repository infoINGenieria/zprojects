/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.tablemodel;

import Modelo.Parametro;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author m4tuu
 */
public class TablaParametrosModel extends ZilleAbstractTableModel{
   

    public TablaParametrosModel(){
        super();
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


}
