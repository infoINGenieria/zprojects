/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.tablemodel;

import Modelo.FamiliaEquipo;

/**
 *
 * @author m4tuu
 */
public class FamiliaEquipoTableModel  extends ZilleAbstractTableModel {

    @Override
    public String getColumnName(int columnIndex) {
        switch(columnIndex) {
            case 0:
                return "Nombre";
        }
        return null;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
       FamiliaEquipo aux;
      
        aux = (FamiliaEquipo)(datos.get(arg0));

        switch (arg1)
        {
            case 0:
                return aux.getNombre();
        }
        return null;
    }
    
}
