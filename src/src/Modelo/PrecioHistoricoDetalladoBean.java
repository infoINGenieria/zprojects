/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author m4tuu
 */
public class PrecioHistoricoDetalladoBean {
    public Date fecha;
    public Double posesion, utilizacion;
    public String numero, nombre, nombreFamilia, nInterno;

    public PrecioHistoricoDetalladoBean(Object[] o) {
        this.fecha = (Date) o[0];
        this.numero = o[1].toString();
        this.nombre = o[2].toString();
        this.nombreFamilia = o[3].toString();
        this.nInterno = o[4].toString();
        this.posesion = o[5] == null ? 0 : ((Double) o[5]).doubleValue();
        this.utilizacion =  o[6] == null ? 0 : ((Double) o[6]).doubleValue();
        
    }

    public PrecioHistoricoDetalladoBean() {
    }
    
}
