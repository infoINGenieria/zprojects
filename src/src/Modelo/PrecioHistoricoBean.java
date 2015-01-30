/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author m4tuu
 */
public class PrecioHistoricoBean {
    public Date fecha;
    public Double posesion, utilizacion;
    public int cantidad;
    public String nombreFamilia;

    public PrecioHistoricoBean(Object[] o) {
        this.fecha = (Date) o[0];
        this.cantidad = ((BigInteger) o[1]).intValue();
        this.nombreFamilia = o[2].toString();
        this.posesion = o[3] == null ? 0 : ((Double) o[3]).doubleValue();
        this.utilizacion =  o[4] == null ? 0 : ((Double) o[4]).doubleValue();
        
    }

    public PrecioHistoricoBean() {
    }
    
}
