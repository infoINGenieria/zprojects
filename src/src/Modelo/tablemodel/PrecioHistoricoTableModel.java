/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.tablemodel;

import Modelo.FamiliaEquipo;
import Modelo.PrecioHistorico;
import Utils.FechaUtil;

/**
 *
 * @author m4tuu
 */
public class PrecioHistoricoTableModel  extends ZilleAbstractTableModel {

    @Override
    public String getColumnName(int columnIndex) {
        switch(columnIndex) {
            case 0:
                return "Tipo";
            case 1:
                return "Valor";
            case 2:
                return "Fecha de alta";
            case 3:
                return "Fecha de baja";
        }
        return null;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
       PrecioHistorico aux;
      
        aux = (PrecioHistorico)(datos.get(arg0));

        switch (arg1)
        {
            case 0:
                return aux.getTipo().getNombre();
            case 1:
                return aux.getValor();
            case 2:
                return FechaUtil.getFecha(aux.getFechaAlta());
            case 3:
                return FechaUtil.getFecha(aux.getFechaBaja());
        }
        return null;
    }
    
}
