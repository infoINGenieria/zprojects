/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author matu
 */
public class MyTime  {

    public Time sumarTime(java.sql.Time hora1, java.sql.Time hora2) {
        return operacionTime(hora1, hora2, "+");
    }

    public Time sumarTime(List<java.sql.Time> horas) {
        long suma = 0;
        for (java.sql.Time hora : horas) {
            if (suma == 0) {
                suma = hora.getTime();
            } else {
                suma = operacionMilisegundos(getMilisegundosToTime(suma), hora, "+");
            }
        }
        return getMilisegundosToTime(suma);
    }

    public Time restarTime(java.sql.Time hora1, java.sql.Time hora2) {
        return operacionTime(hora1, hora2, "-");
    }

    private Time operacionTime(java.sql.Time hora1, java.sql.Time hora2, String operacion) {
        long lnMilisegundos = operacionMilisegundos(hora1, hora2, operacion);
        return new java.sql.Time(lnMilisegundos);
    }

    private long operacionMilisegundos(java.sql.Time hora1, java.sql.Time hora2, String operacion) {
        long time1 = getMilisegundosDesde1970(hora1);
        long time2 = getMilisegundosDesde1970(hora2);

        long timeTotal = 0;
        if (operacion.equals("+")) {
            timeTotal = time1 + time2;
        } else if (operacion.equals("-")) {
            timeTotal = time1 - time2;
        }

        //java.util.Date utilDate = new java.util.Date("1970/01/01 " + milisegundosToStringHoras(timeTotal));
        //java.util.Date utilDate = new java.util.Date("1970/01/01 " + milisegundosToStringHoras(timeTotal));
        
        String[] hhmm = milisegundosToStringHoras(timeTotal).toString().split(":");
        Calendar calendarIn = new GregorianCalendar(1970, 01, 01, Integer.parseInt(hhmm[0]), Integer.parseInt(hhmm[1]), Integer.parseInt(hhmm[2]));
        
        long lnMilisegundos = calendarIn.getTimeInMillis();
        return lnMilisegundos;
    }

    private long getMilisegundosDesde1970(java.sql.Time hora) {
        long returnAux = 0;

//creamos una fecha con la hora que nos pasan y fecha 1970/01/01

        String[] hhmm = hora.toString().split(":");
        Calendar calendarIn = new GregorianCalendar(1970, 01, 01, Integer.parseInt(hhmm[0]), Integer.parseInt(hhmm[1]), Integer.parseInt(hhmm[2]));
        /*
        java.util.Date fecha = new java.util.Date("1970/01/01 " + hora.toString());
        
        Calendar calendarIn = new GregorianCalendar();
        calendarIn.setTime(fecha);
        SimpleDateFormat sdf = new SimpleDateFormat();
        
        sdf.applyPattern("HH");
        calendarIn.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sdf.format(fecha.getTime())));
        sdf.applyPattern("mm");
        calendarIn.set(Calendar.MINUTE, Integer.parseInt(sdf.format(fecha.getTime())));
        sdf.applyPattern("ss");
        calendarIn.set(Calendar.SECOND, Integer.parseInt(sdf.format(fecha.getTime())));
        */
        returnAux = calendarIn.getTimeInMillis();

// el java.sql.time hay que sumarle una hora
// ya que java.sql.Time t = new java.sql.Time(0) = 01:00:00
        return returnAux + 3600000;
    }

    private Time getMilisegundosToTime(long milisegundos) {
        return new java.sql.Time(milisegundos);
    }

    private String milisegundosToStringHoras(long milisegundos) {
        String auxReturn;
        double milisegundosAux = milisegundos;

        Float horaD = new Float(milisegundosAux / 3600000);
        int hora =horaD.intValue();

        Float restoHora = new Float(milisegundosAux % 3600000);

        Float minutoD = restoHora / 60000;
        int minuto = minutoD.intValue();

        Float restoMinutos = restoHora % 60000;

        Float segundosD = restoMinutos / 1000;
        int segundos = segundosD.intValue();

        auxReturn = hora + ":" + minuto + ":" + segundos;

        return auxReturn;
    }
}


