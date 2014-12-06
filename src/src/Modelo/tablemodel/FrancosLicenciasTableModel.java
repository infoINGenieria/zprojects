/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.tablemodel;

import ViewModel.ItemFrancoLicencia;

/**
 *
 * @author m4tuu
 */
public class FrancosLicenciasTableModel extends ZilleAbstractTableModel {

    @Override
    public int getColumnCount() {
        return 14;
    }

    @Override
    public Object getValueAt(int i, int i1) {
       ItemFrancoLicencia aux;
      
        aux = (ItemFrancoLicencia)(datos.get(i));

        switch (i1)
        {
            case 0:
                return aux.getNombre();
            case 1:
                return aux.getFrancosAjustados();
            case 2:
                return aux.getPagados();
            case 3:
                return aux.getTotal();
            case 4:
                return aux.getDiasLicenciaAnual();
            case 5:
                return aux.getLicenciasPendientes();
            case 6:
                return aux.getLicenciaDisponibles();
            case 7:
                return aux.francosEntidad.getSolicitados1();
            case 8:
                return aux.francosEntidad.getSolicitados2();
            case 9:
                return aux.getPendientesActual();
            case 10:
                return aux.francosEntidad.getSale1() != null ? aux.francosEntidad.getSale1().toString() : null;
            case 11:
                return aux.francosEntidad.getEntra1() != null ? aux.francosEntidad.getEntra1().toString() : null;
            case 12:
                return aux.francosEntidad.getSale2() != null ? aux.francosEntidad.getSale2().toString() : null;
            case 13:
                return aux.francosEntidad.getSale2() != null ? aux.francosEntidad.getSale2().toString() : null;
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch(columnIndex) {
            case 0:
                return "NOMBRE";
            case 1:
                return "FRANCOS";
            case 2:
                return "PAGOS";
            case 3:
                return "TOTAL";
            case 4:
                return "DIAS LIC X AÑO";
            case 5:
                return "PENDIENTES";
            case 6:
                return "LIC DISPONIBLE";
            case 7:
                return "SOLICITADOS";
            case 8:
                return "SOLICITADOS2";
            case 9:
                return "PENDIENTE ACTUAL";
            case 10:
                return "SALE";
            case 11:
                return "ENTRA";
            case 12:
                return "SALE2";
            case 13:
                return "ENTRA2";
            default:
                return null;
        }
    }
    
    
}
