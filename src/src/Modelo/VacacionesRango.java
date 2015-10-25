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
    double minDias, maxDias;
    int cantidadDias;

    public int getCantidadDias() {
        return cantidadDias;
    }

    public void setCantidadDias(int cantidadDias) {
        this.cantidadDias = cantidadDias;
    }

    public double getMaxDias() {
        return maxDias;
    }

    public void setMaxDias(double maxDias) {
        this.maxDias = maxDias;
    }

    public double getMinDias() {
        return minDias;
    }

    public void setMinDias(double minDias) {
        this.minDias = minDias;
    }

    public VacacionesRango(double minDias, double maxDias, int cantidadDias) {
        this.minDias = minDias;
        this.maxDias = maxDias;
        this.cantidadDias = cantidadDias;
    }

    public VacacionesRango() {
    }
    
    public int getCantidadDiasSegunAntiguedad(double antiguedad){
        if(minDias <= antiguedad && maxDias >= antiguedad){
                    return cantidadDias;
        }
        return -1;
    }
}
