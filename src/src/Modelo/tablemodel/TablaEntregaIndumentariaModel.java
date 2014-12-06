/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.tablemodel;

import DAO.AbstractHibernateDAO;
import Modelo.EppEntrega;
import Modelo.Operario;
import Utils.FechaUtil;

/**
 *
 * @author m4tuu
 */
public class TablaEntregaIndumentariaModel extends ZilleAbstractTableModel {

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Nombre";
            case 1:
                return "Fecha";
            case 2:
                return "Observaciones";
            default:
                return null;
        }
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        EppEntrega aux = new EppEntrega();
        aux = (EppEntrega) datos.get(i);
        switch(i1) {
            case 0:
                return AbstractHibernateDAO.getEntidad(aux.getOperarioId(), Operario.class);
            case 1:
                return FechaUtil.getFecha(aux.getFecha(), FechaUtil.DATE_FORMAT_HUMAN);
            case 2:
                return aux.getObservaciones();
        }
        return null;
    }
    
    @Override
    public Class getColumnClass(int i) {
        switch(i){
            case 0: 
                return Integer.class;
            default:
                return String.class;
        }
        
    }
}
