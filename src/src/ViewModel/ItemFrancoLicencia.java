/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

import Modelo.DatosFrancoOperario;
import Modelo.EntidadAbstracta;

/**
 *
 * @author m4tuu
 */
public class ItemFrancoLicencia extends EntidadAbstracta {

    public String nombre;
    public int francosTrabajados, francosCompensatorios, diasLicencia, diasLicenciasTomadasAnterior, diasLicenciasTomadas, diasLicenciasAnteriores;
    public DatosFrancoOperario francosEntidad;

    public ItemFrancoLicencia() {
        francosEntidad = new DatosFrancoOperario();
    }

    public int getTotal() {
        return getFrancosAjustados() - getPagados();
    }
  
    public int getLicenciaDisponibles() {
        return  getDiasLicenciaAnual() + getLicenciasPendientes() - diasLicenciasTomadas;
    }
    
    public int getFrancosAjustados() {
        return francosTrabajados - francosCompensatorios - francosEntidad.getAjusteFrancos();
    }
    
    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {
    }

    public String getNombre() {
        return nombre;
    }

    public int getPagados() {
        return francosEntidad.getPagados();
    }

    public int getDiasLicenciaAnual() {
        return diasLicencia;
    }

    public int getLicenciasPendientes() {
        return diasLicenciasAnteriores - diasLicenciasTomadasAnterior - francosEntidad.getAjusteLicencias();
    }

    public int getPendientesActual() {
        return getLicenciaDisponibles() + getTotal() - francosEntidad.getSolicitados1() - francosEntidad.getSolicitados2();
    }

    public int getDiasLicenciasTomadas() {
        return diasLicenciasTomadas;
    }

    public void setDiasLicenciasTomadas(int diasLicenciasTomadas) {
        this.diasLicenciasTomadas = diasLicenciasTomadas;
    }
    
}
