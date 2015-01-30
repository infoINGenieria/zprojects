/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author m4tuu
 */
public class FamiliaEquipoBean {
    
    /**
     * Esta clase sirve como adaptador para mostrar las familias con los valores vigentes
     */
    
    private int id;
    private String nombre;
    private Double valorPosesion;
    private Double valorUtilizacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getValorPosesion() {
        return valorPosesion;
    }

    public void setValorPosesion(Double valorPosesion) {
        if (valorPosesion==null) this.valorPosesion=0D;
        this.valorPosesion = valorPosesion;
    }

    public Double getValorUtilizacion() {
        return valorUtilizacion;
    }

    public void setValorUtilizacion(Double valorUtilizacion) {
        if(valorUtilizacion==null) this.valorUtilizacion = 0D;
        this.valorUtilizacion = valorUtilizacion;
    }

    public FamiliaEquipo convert2FamiliaEquipo() {
        FamiliaEquipo fe = new FamiliaEquipo();
        fe.setId(id);
        fe.setNombre(nombre);
        fe.setValorPosesion(valorPosesion==null?0:valorPosesion);
        fe.setValorUtilizacion(valorUtilizacion==null?0:valorUtilizacion);
        return fe;
    }
    
    
    
}
