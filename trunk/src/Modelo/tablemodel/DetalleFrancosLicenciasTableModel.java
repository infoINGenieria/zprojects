/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.tablemodel;

import Utils.FechaUtil;
import ViewModel.ItemDetalleFrancosLicencias;

/**
 *
 * @author m4tuu
 */
public class DetalleFrancosLicenciasTableModel extends ZilleAbstractTableModel {

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "FECHA";
            case 1:
                return "CENTRO DE COSTO";
            case 2:
                return "N° PARTE DIARIO";
        }
        return "";
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        ItemDetalleFrancosLicencias aux;
        
        aux = (ItemDetalleFrancosLicencias) datos.get(i);
        switch(i1) {
            case 0:
                return FechaUtil.getFecha(aux.getFecha(), FechaUtil.DATE_FORMAT_HUMAN);
            case 1:
                return aux.getcCosto();
            case 2:
                return aux.getNumeroParteDiario();
            default:
                return null;
        }
    }
    
}
