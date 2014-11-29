/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.tablemodel;

import DAO.AbstractHibernateDAO;
import Modelo.EPPOperario;
import Modelo.EppEntregaItem;
import Utils.FechaUtil;

/**
 *
 * @author m4tuu
 */
public class TablaDetalleEntregaIndumentariaModel extends ZilleAbstractTableModel {

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Nombre";
            case 1:
                return "Tipo Medida";
            case 2:
                return "Valor";
            case 3:
                return "Valor por defecto";
            default:
                return null;
        }
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        EppEntregaItem aux = new EppEntregaItem();
        aux = (EppEntregaItem) datos.get(i);
        switch(i1) {
            case 0:
                return aux.getEpp().getNombre();
            case 1:
                return aux.getEpp().getMedida();
            case 2:
                return aux.getMedida();
            case 3:
                EPPOperario op = ((EPPOperario) AbstractHibernateDAO.getEntidad(EPPOperario.class, 
                        "FROM EPPOperario eo where eo.operario.id = " + aux.getEppEntrega().getOperarioId() +
                        " and eo.epp.id = " + aux.getEpp().getId()));
                return op == null? "": op.getValor();
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
