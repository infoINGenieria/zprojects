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
  public static final String DATE_FORMAT_SQL = "yyyy-MM-dd";
  public static final String DAY_FORMAT_HUMAN = "EEEE";
  public static final String DATE_FORMAT_EXCEL = "dd/MM/yyyy";

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
      if(d!=null)
        return sdf.format(d);
      return null;
      
  }
  public static String getFecha(Date d, final String formato){
      SimpleDateFormat sdf = new SimpleDateFormat(formato);
      if(d!=null)
        return sdf.format(d);
      return null;
      
  }
  
  public static String getFechaSQL(Date d){
      SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_SQL);
      return sdf.format(d);
  }
  
  public static Date getFecha(String str){
      /**
       * Parse Date from String en dd-MM-yyyy
       */
      try{
      SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_HUMAN);
      return sdf.parse(str);
      }catch (ParseException ex){
          return new Date();
      }
  }
  
  public static int getAño(Date fecha){
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(fecha);
      return calendar.get(Calendar.YEAR);
  }
  
  public static Date addAño(Date fecha) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(fecha);
      calendar.add(Calendar.YEAR, 1);
      return calendar.getTime();
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
  
  public static java.util.Date inicioPeriodo(){
      return inicioPeriodo(new Date());
  }
  
  public static java.util.Date inicioPeriodo(Date fecha){
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        int dias = calendar.get(Calendar.DATE);
        int mes = calendar.get(Calendar.MONTH);
        int año = calendar.get(Calendar.YEAR);
        if(dias < 21){
            mes -= 1;
        }
        calendar.set(año, mes, 21);
        return calendar.getTime();
        
  }
  
  public static Date finPeriodo(){
      return finPeriodo(new Date());
  }
  
  public static Date finPeriodo(Date fecha){
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(fecha);
      int dias = calendar.get(Calendar.DATE);
        int mes = calendar.get(Calendar.MONTH);
        int año = calendar.get(Calendar.YEAR);
        if(dias < 21){
            mes -= 1;
      }
      calendar.set(año, mes + 1, 20);
      return calendar.getTime();
  }
  
  
  public static Date resetTime(Date fecha){
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(fecha);
        /*Seteo las horas, minutos etc en 0 para que al comparar con los date de sql
         * filtren bien las fechas
         */
        gc.set(GregorianCalendar.MINUTE,0);
        gc.set(GregorianCalendar.HOUR_OF_DAY,0);
        gc.set(GregorianCalendar.SECOND,0);
        gc.set(GregorianCalendar.MILLISECOND,0);
        return gc.getTime();
  }
  
  public static Date get31DicAnioActual(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, 31);
        cal.set(Calendar.MONTH, 11);
        return cal.getTime();
  }
  public static Date get31DicAnioAnterior(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, 31);
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) -1);
        return cal.getTime();
  }
  public static Date get31DicByAnio(int año){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.YEAR, año);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        cal.set(Calendar.MONTH, 11);
        return cal.getTime();
  }
  public static Date get31Dic(Date fecha){
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        cal.set(Calendar.MONTH, 11);
        return cal.getTime();
  }
}
