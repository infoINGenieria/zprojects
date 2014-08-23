/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author m4tuu
 */
public class VacacionesRango {
    int minDias, maxDias, cantidadDias;

    public int getCantidadDias() {
        return cantidadDias;
    }

    public void setCantidadDias(int cantidadDias) {
        this.cantidadDias = cantidadDias;
    }

    public int getMaxDias() {
        return maxDias;
    }

    public void setMaxDias(int maxDias) {
        this.maxDias = maxDias;
    }

    public int getMinDias() {
        return minDias;
    }

    public void setMinDias(int minDias) {
        this.minDias = minDias;
    }

    public VacacionesRango(int minDias, int maxDias, int cantidadDias) {
        this.minDias = minDias;
        this.maxDias = maxDias;
        this.cantidadDias = cantidadDias;
    }

    public VacacionesRango() {
    }
    
    public int getCantidadDiasSegunAntiguedad(int antiguedad){
        if(minDias <= antiguedad && maxDias >= antiguedad){
                    return cantidadDias;
        }
        return -1;
    }
}
