/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Utils.MyTime;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author matu
 */
public final class Registro extends EntidadAbstracta {

    int id, partediario_id;
    boolean especial = false;
    Time hs_salida, hs_llegada, hs_inicio, hs_fin,
            hs_ialmuerzo, hs_falmuerzo;
    Time hs_normal, hs_viaje, hs_almuerzo, hs_50, hs_100, total_hs, hs_tarea, hs_almuerzoDividido;
    String dia, fecha;

    public Registro() {
    }

    public Registro(String fecha, Time hs_salida, Time hs_llegada, Time hs_inicio, Time hs_fin, Time hs_ialmuerzo, Time hs_falmuerzo) {
        this.fecha = fecha;
        this.hs_salida = hs_salida;
        this.hs_llegada = hs_llegada;
        this.hs_inicio = hs_inicio;
        this.hs_fin = hs_fin;
        this.hs_ialmuerzo = hs_ialmuerzo;
        this.hs_falmuerzo = hs_falmuerzo;
        
        
        

    }
    public void calcular(Perfiles p, boolean desarraigo){
        if(p.obra.isTieneRegistro()){
            esEspecialHoy();
            calcularTotalHs();
            calcularHsAlmuerzo();
            calcularHsViaje();
            calcularHsTarea();
            diferenciaDeHS(p);
        }
        
        
    }

    public void calcularEspecial(MyTime op, Perfiles perfil) {
        if (dia.equals("SAB")) {
            if (hs_salida.compareTo(perfil.getSabEsp_i()) < 0) {
                
                this.hs_100 = Time.valueOf("00:00:00");
                //Lo que sea después de las 13 es al 100
                this.hs_100 = op.restarTime(hs_fin, perfil.getSabEsp_i());
                //Además, le resto el tiempo de almuerzo que sea necesario
                switch(almuerzoEnEspecial(perfil.getSabEsp_i(), perfil.getSabEsp_f())){
                    //case 1: No restamos el almuerzo al horario especial
                    case 2: 
                        this.hs_100 = op.restarTime(this.hs_100, hs_almuerzo);
                        break;
                    case 3:
                        this.hs_100 = op.restarTime(this.hs_100, hs_almuerzoDividido);
                        break;
                }
                this.hs_50 = Time.valueOf("00:00:00");
                
                this.hs_50 = op.restarTime(perfil.getSabEsp_i(), hs_inicio);
                this.hs_50 = op.restarTime(this.hs_50, Time.valueOf("04:00:00"));
                //al restar 4 hs es posible que hs_50 quede muy grande, si es menos a 4 hs
                if(this.hs_50.compareTo(Time.valueOf("00:00:00"))<=0){
                    this.hs_50=Time.valueOf("00:00:00");
                    
                }
                switch(almuerzoEnEspecial(perfil.getSabEsp_i(), perfil.getSabEsp_f())){
                    
                    case 1: 
                        this.hs_50 = op.restarTime(this.hs_50, hs_almuerzo);
                        break;
                        //case 2: El aalmuerzo completo fue restado en las hs al 100
                    case 3:
                        this.hs_50 = op.restarTime(this.hs_50, hs_almuerzoDividido);
                        break;
                }
            } else {
                //Todo el horario esta en horario especial

                this.hs_100 = op.restarTime(total_hs, this.hs_viaje);
                this.hs_100 = op.restarTime(this.hs_100, this.hs_almuerzo);
                this.hs_50 = Time.valueOf("00:00:00");
            }

        } else if (dia.equals("DOM")) {

            if (hs_salida.compareTo(perfil.getDomEsp_i()) < 0) {
                //r2 = op.restarTime(perfil.getDomEsp_i(), hs_entrada);
                //r2 = op.restarTime(r2, hs_viaje);
                //this.hs_50 = (Time) r2.clone();
                this.hs_50 = Time.valueOf("00:00:00");
                this.hs_100 = Time.valueOf("00:00:00");
                this.hs_100 = op.restarTime(hs_llegada, perfil.getDomEsp_i());
                this.hs_100 = op.restarTime(this.hs_100, op.restarTime(hs_llegada, hs_fin));
                switch(almuerzoEnEspecial(perfil.getDomEsp_i(), perfil.getDomEsp_f())){
                    //case 1: No restamos el almuerzo al horario especial
                    case 2: 
                        this.hs_100 = op.restarTime(this.hs_100, hs_almuerzo);
                        break;
                    case 3:
                        this.hs_100 = op.restarTime(this.hs_100, hs_almuerzoDividido);
                        break;
                }
            } else {

                this.hs_100 = op.restarTime(total_hs, this.hs_viaje);
                this.hs_100 = op.restarTime(this.hs_100, this.hs_almuerzo);
                this.hs_50 = Time.valueOf("00:00:00");
            }

        } else {  //es feriado

            if (hs_salida.compareTo(perfil.getFeriado_i()) < 0) {
                
                this.hs_50 = Time.valueOf("00:00:00");
                this.hs_100 = Time.valueOf("00:00:00");
                this.hs_100 = op.restarTime(hs_llegada, perfil.getFeriado_i());
                this.hs_100 = op.restarTime(this.hs_100, op.restarTime(hs_llegada, hs_fin));
                switch(almuerzoEnEspecial(perfil.getFeriado_i(), perfil.getFeriado_f())){
                    //case 1: No restamos el almuerzo al horario especial
                    case 2: 
                        this.hs_100 = op.restarTime(this.hs_100, hs_almuerzo);
                        break;
                    case 3:
                        this.hs_100 = op.restarTime(this.hs_100, hs_almuerzoDividido);
                        break;
                }
                
            } else {
                hs_100 = op.restarTime(total_hs, this.hs_viaje);
                hs_100 = op.restarTime(hs_100, this.hs_almuerzo);
                this.hs_50 = Time.valueOf("00:00:00");
            }
        }
    }
    public int almuerzoEnEspecial(Time ini, Time fin){
        int opc=0;
        if(hs_falmuerzo.compareTo(ini)<0){
            /*El almuerzo termina antes de 
             * las 13Hs.
             */
            opc= 1;
        }
        if(hs_ialmuerzo.compareTo(ini)>=0){
            /* El almuerzo empieza a las 13. 
             */
            opc= 2;
        }
        if(hs_ialmuerzo.compareTo(ini)<0 && hs_falmuerzo.compareTo(ini)>0){
            /* Solo se resta parte 
             * del amuerzo
             */
            MyTime op = new MyTime();
            hs_almuerzoDividido = op.restarTime(hs_falmuerzo, ini);
            opc= 3;
        }
        //System.out.println("Almuerzo: "+opc);
        return opc;
        
    }
    
    

    public void diferenciaDeHS(Perfiles perfil) {
        this.hs_normal = perfil.getHs_normal();
        MyTime op = new MyTime();
        Time t = Time.valueOf("22:00:00");
        t = op.restarTime(t, this.hs_viaje);
        t = op.restarTime(t, this.hs_almuerzo);
        t = op.restarTime(t, this.hs_normal);
        t = op.restarTime(Time.valueOf("22:00:00"), t);
        if (especial) {
            calcularEspecial(op, perfil);
        } else {
           
            if (t.compareTo(total_hs) < 0) {
                hs_50 = op.restarTime(total_hs, t);
            } else {
                hs_50 = Time.valueOf("00:00:00");
            }
            hs_100 = Time.valueOf("00:00:00");
        }
    }

    public void calcularTotalHs() {
        MyTime op = new MyTime();
        this.total_hs = op.restarTime(this.hs_llegada, this.hs_salida);
        //System.out.println("Total: "+ total_hs.toString());

    }

    public void calcularHsAlmuerzo() {
        MyTime op = new MyTime();
        this.hs_almuerzo = op.restarTime(this.hs_falmuerzo, this.hs_ialmuerzo);
        //System.out.println("Almuerzo: "+hs_almuerzo.toString());
    }
    public void calcularHsTarea() {
        MyTime op = new MyTime();
        this.hs_tarea = op.restarTime(this.total_hs, this.hs_viaje);
        this.hs_tarea = op.restarTime(hs_tarea, hs_almuerzo);
        //System.out.println("Trabajadas: "+hs_tarea.toString());
    }

    public void calcularHsViaje() {
        MyTime op = new MyTime();
        Time viaje1 = op.restarTime(hs_inicio, hs_salida);
        Time viaje2 = op.restarTime(hs_llegada, hs_fin);
        Time t = Time.valueOf("22:00:00");
        t = op.restarTime(t, viaje1);
        t = op.restarTime(t, viaje2);
        this.hs_viaje = op.restarTime(Time.valueOf("22:00:00"), t);
        //System.out.println("Viaje: "+viaje1.toString()+" + "+ viaje2.toString()+ "= "+hs_viaje.toString());

    }

    public void esEspecialHoy() {
        String[] parse = fecha.split("/");
        GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(parse[2]),
                Integer.parseInt(parse[1]) - 1, Integer.parseInt(parse[0]));

        if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            //especial = true;
            dia = "SAB";
        } else if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            //especial = true;
            dia = "DOM";
        } else {
            dia = "otro";
            //especial = false;
        }
    }

    public GregorianCalendar getFechaWString(String s) {
        //SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
        String[] parse = s.split("/");
        GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(parse[2]),
                Integer.parseInt(parse[1]), Integer.parseInt(parse[0]));

        return gc;
    }

    public String getStringWFecha(GregorianCalendar gc) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(gc.getTime());

    }
    public Time getHs_tarea() {
        return hs_tarea;
    }

    public Time getHs_almuerzoDividido() {
        return hs_almuerzoDividido;
    }

    public void setHs_almuerzoDividido(Time hs_almuerzoDividido) {
        this.hs_almuerzoDividido = hs_almuerzoDividido;
    }


    public void setHs_tarea(Time hs_tarea) {
        this.hs_tarea = hs_tarea;
    }

    public boolean isEspecial() {
        return especial;
    }

    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

    public Time getHs_falmuerzo() {
        return hs_falmuerzo;
    }

    public void setHs_falmuerzo(Time hs_falmuerzo) {
        this.hs_falmuerzo = hs_falmuerzo;
    }

    public Time getHs_fin() {
        return hs_fin;
    }

    public void setHs_fin(Time hs_fin) {
        this.hs_fin = hs_fin;
    }

    public Time getHs_ialmuerzo() {
        return hs_ialmuerzo;
    }

    public void setHs_ialmuerzo(Time hs_ialmuerzo) {
        this.hs_ialmuerzo = hs_ialmuerzo;
    }

    public Time getHs_inicio() {
        return hs_inicio;
    }

    public void setHs_inicio(Time hs_inicio) {
        this.hs_inicio = hs_inicio;
    }

    public Time getHs_llegada() {
        return hs_llegada;
    }

    public void setHs_llegada(Time hs_llegada) {
        this.hs_llegada = hs_llegada;
    }

    public Time getHs_salida() {
        return hs_salida;
    }

    public void setHs_salida(Time hs_salida) {
        this.hs_salida = hs_salida;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Time getHs_almuerzo() {
        return hs_almuerzo;
    }

    public void setHs_almuerzo(Time hs_almuerzo) {
        this.hs_almuerzo = hs_almuerzo;
    }

    public Time getTotal_hs() {
        return total_hs;
    }

    public void setTotal_hs(Time total_hs) {
        this.total_hs = total_hs;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Time getHs_100() {
        return hs_100;
    }

    public void setHs_100(Time hs_100) {
        this.hs_100 = hs_100;
    }

    public Time getHs_50() {
        return hs_50;
    }

    public void setHs_50(Time hs_50) {
        this.hs_50 = hs_50;
    }

    public Time getHs_normal() {
        return hs_normal;
    }

    public void setHs_normal(Time hs_normal) {
        this.hs_normal = hs_normal;
    }

    public Time getHs_viaje() {
        return hs_viaje;
    }

    public void setHs_viaje(Time hs_viaje) {
        this.hs_viaje = hs_viaje;
    }

    @Override
    public boolean validate() {
        return true;
    }

    public int getPartediario_id() {
        return partediario_id;
    }

    public void setPartediario_id(int partediario_id) {
        this.partediario_id = partediario_id;
    }
    
    
}
