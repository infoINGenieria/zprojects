/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

import Modelo.EntidadAbstracta;
import java.util.Date;

/**
 *
 * @author m4tuu
 */
public class ItemDetalleFrancosLicencias extends EntidadAbstracta{

    Date fecha;
    String cCosto, numeroParteDiario;

    public String getcCosto() {
        return cCosto;
    }

    public void setcCosto(String cCosto) {
        this.cCosto = cCosto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNumeroParteDiario() {
        return numeroParteDiario;
    }

    public void setNumeroParteDiario(String numeroParteDiario) {
        this.numeroParteDiario = numeroParteDiario;
    }

    public ItemDetalleFrancosLicencias() {
    }

    public ItemDetalleFrancosLicencias(Date fecha, String cCosto, String numeroParteDiario) {
        this.fecha = fecha;
        this.cCosto = cCosto;
        this.numeroParteDiario = numeroParteDiario;
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
    
}
