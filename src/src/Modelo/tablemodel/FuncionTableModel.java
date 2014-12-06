/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.tablemodel;

import Modelo.Funcion;

/**
 *
 * @author m4tuu
 */
public class FuncionTableModel extends ZilleAbstractTableModel {

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Funcion aux = (Funcion) getFila(row);
        if (col == 0) {
            return aux.getId();
        } else if (col == 1) {
            return aux.getFuncion();
        }
        return null;
        
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Integer.class;
        } else {
            return String.class;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == 0) {
            return "ID";
        } else {
            return "NOMBRE";
        }
    }
    @Override
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col == 1) return true;
            return false;
    }
}
