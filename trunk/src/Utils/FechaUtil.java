/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Mario
 */
public class FechaUtil {
    
  public static final String DATETIME_FORMAT_NOW = "yyyyMMdd-HH-mm-ss";
  public static final String DATE_FORMAT_NOW = "yyyyMMdd";
  public static final String DATE_FORMAT_HUMAN = "dd-MM-yyyy";
  public static final String DAY_FORMAT_HUMAN = "EEEE";

  public static String getDay(){
      Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(DAY_FORMAT_HUMAN);
    return sdf.format(cal.getTime());
  }
  public static String getFecha() {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
    return sdf.format(cal.getTime());

  }

  public static String getFechaHora() {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT_NOW);
    return sdf.format(cal.getTime());

  }
  
  public static String getFecha(Date d){
      SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_HUMAN);
      return sdf.format(d);
  }
  
  public static Date getFecha(String str){
      try{
      SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_HUMAN);
      return sdf.parse(str);
      }catch (ParseException ex){
          return new Date();
      }
  }
  
  public static java.sql.Date getFechatoDB(Date d){
      java.sql.Date fecha;
      if(d!=null){ 
         fecha = new java.sql.Date(d.getTime());
         return fecha;
      }
      else return null;
  }
  public static java.util.Date getFechaFromDB(java.sql.Date d){
      java.util.Date fecha;
      if(d!=null){
          fecha = new java.util.Date(d.getTime());
          return fecha;
      }else{
          return null;
      }
  }
  
  public static synchronized int diferenciasDeFechas(Date fechaPrevia, Date fecha) {

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechaInicioString = df.format(fechaPrevia);
        try {
            fechaPrevia = df.parse(fechaInicioString);
        } catch (ParseException ex) {
        }

        String fechaFinalString = df.format(fecha);
        try {
            fecha = df.parse(fechaFinalString);
        } catch (ParseException ex) {
        }

        long fechaInicialMs = fechaPrevia.getTime();
        long fechaFinalMs = fecha.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        return ((int) dias);
    }
  
  public static synchronized java.util.Date restarFechasDias(java.util.Date fch, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, -dias);
        return new java.util.Date(cal.getTimeInMillis());
    }
}
