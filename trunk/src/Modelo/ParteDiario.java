/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;

/**
 *
 * @author matuu
 */
public class ParteDiario {

    int id, idOperario, idFuncion, idObra, idHorario, idEquipo, idSituacion;
    Date fecha;
    String numero, observaciones, nombreO;
    boolean multifuncion, desarraigo;

    @Override
    public String toString() {
        if (idSituacion == 1) {
            return getNumero() + " " + nombreO + " - " + getStringWFecha(fecha);
        } else {
            return nombreO + " - " + getStringWFecha(fecha);
        }
    }

    public ParteDiario() {
    }

    public boolean isDesarraigo() {
        return desarraigo;
    }

    public void setDesarraigo(boolean desarraigo) {
        this.desarraigo = desarraigo;
    }
    
    

    public int getIdSituacion() {
        return idSituacion;
    }

    public void setIdSituacion(int idSituacion) {
        this.idSituacion = idSituacion;
    }

    public String getNombreO() {
        return nombreO;
    }

    public void setNombreO(String nombreO) {
        this.nombreO = nombreO;
    }

    public GregorianCalendar getFechaWString(String s) {
        //SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
        String[] parse = s.split("/");
        GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(parse[2]),
                Integer.parseInt(parse[1]), Integer.parseInt(parse[0]));

        return gc;
    }

    public String getStringWFecha(Date gc) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(gc);

    }

    public String getFecha2DB(Date gc) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(gc);

    }

    public boolean isMultifuncion() {
        return multifuncion;
    }

    public void setMultifuncion(boolean multifuncion) {
        this.multifuncion = multifuncion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(int idFuncion) {
        this.idFuncion = idFuncion;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public int getIdOperario() {
        return idOperario;
    }

    public void setIdOperario(int idOperario) {
        this.idOperario = idOperario;
    }

    public String getNumero() {
        return numero;
    }

    public String getNumeroPre() {
        String[] parse = numero.split("-");
        return parse[0];
    }

    public String getNumeroPos() {
        String[] parse = numero.split("-");
        return parse[1];
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setNumero(String pre, String pro) {
        Formatter fmt = new Formatter();
        numero = fmt.format("%1$04d-%2$08d", Integer.parseInt(pre), Integer.parseInt(pro)).toString();
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
