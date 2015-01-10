/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.tablemodel;

import Modelo.TipoCosto;

/**
 *
 * @author m4tuu
 */
public class TipoCostoTableModel  extends ZilleAbstractTableModel {

    @Override
    public String getColumnName(int columnIndex) {
        switch(columnIndex) {
            case 0:
                return "Nombre";
            case 1:
                return "Tipo";
        }
        return null;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
       TipoCosto aux;
      
        aux = (TipoCosto)(datos.get(arg0));

        switch (arg1)
        {
            case 0:
                return aux.getNombre();
            case 1:
                return aux.getTipoLabel();
        }
        return null;
    }
    
    
}
