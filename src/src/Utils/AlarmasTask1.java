/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Matu
 */
public class AlarmasTask1 extends Thread {
    
    @Override
    public void run(){
        Date dia = new Date();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dia);
        gc.add(GregorianCalendar.DATE, 20);
        Date proximo = gc.getTime();
        System.out.println(FechaUtil.getFecha(dia));
        System.out.println(FechaUtil.getFecha(proximo));
        //Buscar las VTO_VT con fecha prximo 20 dias.
        //Buscar las VtO_CARNET con fecha proximo 20 días.
        //Buscar las VTO_SEGURO con fecha proximo 20 días.
        
        //Luego, buscar las alarmas para hoy, y cuyo fecha previa sea antes o igual a hoy.
    }
    private void verificarVto_VT(Date d){
        
    }
}
