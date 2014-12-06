/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.tablemodel;

import Modelo.EPP;

/**
 *
 * @author m4tuu
 */
public class EPPTableModel extends ZilleAbstractTableModel{
    
    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int row, int col) {
        EPP aux;
       
        aux = (EPP)(datos.get(row));

        switch (col)
        {
            case 0:
                return aux.getNombre();
            case 1:
                return aux.getMedida();                
            default:
                return null;
        }
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch(columnIndex) {
            case 0:
                return "NOMBRE";
            case 1:
                return "MEDIDA";
        }
        return "";
    }
}
