/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


import java.util.Date;
import java.util.Formatter;


/**
 *
 * @author matuu
 */
public class InformesHoras {
    
    int id, id_operario, multiFc=0;
    float total_hs_viaje=0,  total_50=0, 
            total_100=0, total_normal=0, 
            total_tarea=0;
    String periodo, x100Obras;
    Date desdeF, hastaF;

    public InformesHoras(int id_operario, String periodo) {
        this.id_operario = id_operario;
        this.periodo = periodo;
    }
    @Override
    public String toString(){
         Formatter fmt = new Formatter();
         return fmt.format("%1$05d",id).toString() +": "+periodo;
    }
    
    public InformesHoras(){}

    public String getX100Obras() {
        return x100Obras;
    }

    public void setX100Obras(String x100Obras) {
        this.x100Obras = x100Obras;
    }

    
    public Date getDesdeF() {
        return desdeF;
    }

    public void setDesdeF(Date desdeF) {
        this.desdeF = desdeF;
    }

    public Date getHastaF() {
        return hastaF;
    }

    public void setHastaF(Date hastaF) {
        this.hastaF = hastaF;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_operario() {
        return id_operario;
    }

    public void setId_operario(int id_operario) {
        this.id_operario = id_operario;
    }

    public int getMultiFc() {
        return multiFc;
    }

    public void setMultiFc(int multiFc) {
        this.multiFc = multiFc;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public float getTotal_100() {
        return total_100;
    }

    public void setTotal_100(float total_100) {
        this.total_100 = total_100;
    }

    public float getTotal_50() {
        return total_50;
    }

    public void setTotal_50(float total_50) {
        this.total_50 = total_50;
    }

    public float getTotal_hs_viaje() {
        return total_hs_viaje;
    }

    public void setTotal_hs_viaje(float total_hs_viaje) {
        this.total_hs_viaje = total_hs_viaje;
    }

    public float getTotal_normal() {
        return total_normal;
    }

    public void setTotal_normal(float total_normal) {
        this.total_normal = total_normal;
    }

    public float getTotal_tarea() {
        return total_tarea;
    }

    public void setTotal_tarea(float total_tarea) {
        this.total_tarea = total_tarea;
    }
    public float Redondear(float numero) {
        return (float) (Math.round(numero * Math.pow(10, 2)) / Math.pow(10, 2));
    }

    public void tomarElTiempo(Registro r){
       
        String parse[] = r.getHs_viaje().toString().split(":");
        total_hs_viaje+=(Integer.parseInt(parse[0])*3600+Integer.parseInt(parse[1])*60+Integer.parseInt(parse[2]));
        parse = r.getHs_50().toString().split(":");
        total_50+=Integer.parseInt(parse[0])*3600+Integer.parseInt(parse[1])*60+Integer.parseInt(parse[2]);
        parse = r.getHs_100().toString().split(":");
        total_100+=Integer.parseInt(parse[0])*3600+Integer.parseInt(parse[1])*60+Integer.parseInt(parse[2]);
        parse = r.getHs_normal().toString().split(":");
        total_normal+=Integer.parseInt(parse[0])*3600+Integer.parseInt(parse[1])*60+Integer.parseInt(parse[2]);
        parse = r.getHs_tarea().toString().split(":");
        total_tarea+=Integer.parseInt(parse[0])*3600+Integer.parseInt(parse[1])*60+Integer.parseInt(parse[2]);
        
    }
    
    public void CalcularValoresHoras(){
       
         total_hs_viaje=Redondear(total_hs_viaje/3600);
         total_50=Redondear(total_50/3600);
         total_100=Redondear(total_100/3600);
         total_normal=Redondear(total_normal/3600);
         total_tarea=Redondear(total_tarea/3600);
    }
    
    
    
   
    
    
}
