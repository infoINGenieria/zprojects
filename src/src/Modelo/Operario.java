/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author matuu
 */
@Entity
@Table(name="operarios")
public class Operario extends EntidadAbstracta {
   

    private int id;
    private int funcion;
    private String n_legajo, nombre, cuil, observaciones, descripcion_vto1, descripcion_vto2,descripcion_vto3;
    private boolean desarraigo;
    private Date vto_carnet, vto_psicofisico, vto_cargagral, vto_cargapeligrosa, vto_otros1, vto_otros2, vto_otros3, fecha_ingreso;

    public boolean isDesarraigo() {
        return desarraigo;
    }

    public void setDesarraigo(boolean desarraigo) {
        this.desarraigo = desarraigo;
    }

            
    public int getFuncion() {
        return funcion;
    }

    public void setFuncion(int funcion) {
        this.funcion = funcion;
    }

    
    public String getCuil() {
        return cuil;
    }

    public void setCuil(String CUIL) {
        this.cuil = CUIL;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String NOMBRE) {
        this.nombre = NOMBRE;
    }

    public String getN_legajo() {
        return n_legajo;
    }

    public void setN_legajo(String N_LEGAJO) {
        this.n_legajo = N_LEGAJO;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String OBSERVACIONES) {
        this.observaciones = OBSERVACIONES;
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Operario(String NOMBRE) {
        this.nombre = NOMBRE;
    }
    public Operario(int id){
        this.id = id;
    }
    
    public Operario() { }
            
    @Override
    public String toString(){
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Operario other = (Operario) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        return hash;
    }
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getVto_carnet() {
        return vto_carnet;
    }

    public void setVto_carnet(Date vto_carnet) {
        this.vto_carnet = vto_carnet;
    }

    public String getDescripcion_vto1() {
        return descripcion_vto1;
    }

    public void setDescripcion_vto1(String descripcion_vto1) {
        this.descripcion_vto1 = descripcion_vto1;
    }

    public String getDescripcion_vto2() {
        return descripcion_vto2;
    }

    public void setDescripcion_vto2(String descripcion_vto2) {
        this.descripcion_vto2 = descripcion_vto2;
    }

    public String getDescripcion_vto3() {
        return descripcion_vto3;
    }

    public void setDescripcion_vto3(String descripcion_vto3) {
        this.descripcion_vto3 = descripcion_vto3;
    }
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getVto_cargagral() {
        return vto_cargagral;
    }

    public void setVto_cargagral(Date vto_cargagral) {
        this.vto_cargagral = vto_cargagral;
    }
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getVto_cargapeligrosa() {
        return vto_cargapeligrosa;
    }

    public void setVto_cargapeligrosa(Date vto_cargapeligrosa) {
        this.vto_cargapeligrosa = vto_cargapeligrosa;
    }
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getVto_otros1() {
        return vto_otros1;
    }

    public void setVto_otros1(Date vto_otros1) {
        this.vto_otros1 = vto_otros1;
    }
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getVto_otros2() {
        return vto_otros2;
    }

    public void setVto_otros2(Date vto_otros2) {
        this.vto_otros2 = vto_otros2;
    }
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getVto_otros3() {
        return vto_otros3;
    }

    public void setVto_otros3(Date vto_otros3) {
        this.vto_otros3 = vto_otros3;
    }
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getVto_psicofisico() {
        return vto_psicofisico;
    }

    public void setVto_psicofisico(Date vto_psicofisico) {
        this.vto_psicofisico = vto_psicofisico;
    }
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
    @Transient
    public int getAniosAntiguedad(){
        if(this.fecha_ingreso != null){
            return CalcularAniosAntiguedad(this.fecha_ingreso);
        }
        return 0;
    }
    @Transient
    public int getDiasVacaciones(){
        if(this.fecha_ingreso != null){
            return DiasVacaciones(this.fecha_ingreso);
        }
        return 0;
    }
    
    public static int CalcularAniosAntiguedad(Date fecha){
        if(fecha == null) return 0;
        Calendar cal = Calendar.getInstance();
        Calendar hoy = Calendar.getInstance();
        cal.setTime(fecha);
        hoy.setTime(new Date());
        int anios = hoy.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        if(hoy.get(Calendar.MONTH) < cal.get(Calendar.MONTH)){
            anios--;
        }else if(hoy.get(Calendar.MONTH) == cal.get(Calendar.MONTH) &&
                hoy.get(Calendar.DATE) < cal.get(Calendar.DATE)){
            anios--;
        }
        if(anios < 0) return 0;
        return anios;
    }
    
    public static int DiasVacaciones(Date fecha){
        int antiguedad = CalcularAniosAntiguedad(fecha);
        return ParametrosSistema.rangosVacaciones.getDiasDeVacaciones(antiguedad);
    }
            
    public static int DiasVacacionesAnteriores(Date fecha) {
        int antiguedad = CalcularAniosAntiguedad(fecha);
        int contador = 0;
        for (int i = 0; i < antiguedad; i++) {
            contador += ParametrosSistema.rangosVacaciones.getDiasDeVacaciones(i);
        }
        return contador;
    }

    @Override
    public boolean validate() {
        if (n_legajo.isEmpty()) {
            error += "El Número de legajo es obligatorio;";
        }
        if (nombre.isEmpty()) {
            error += "El Nombre es obligatorio;";
        }
        return error.isEmpty();
    }
}