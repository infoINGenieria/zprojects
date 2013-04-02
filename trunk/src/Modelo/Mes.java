/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author matuuar
 */
public class Mes {
    
    public static String getMes(int i){
        String mes="";
        switch(i){
            case 0:
                return "Enero";
            case 1:
                return "Febrero";
            case 2:
                return "Marzo";
            case 3:
                return "Abril";
            case 4:
                return "Mayo";
            case 5:
                return "Junio";
            case 6:
                return "Julio";
            case 7:
                return "Agosto";
            case 8:
                return "Septiembre";
            case 9:
                return "Octubre";
            case 10:
                return "Noviembre";
            case 11:
                return "Diciembre";
                                   
        }
        return mes;
    }
    public static int getMes(String mes){
        
        if(mes.equals("Enero")){
            return 0;
        }else if(mes.equals("Febrero")){
            return 1;
        }else if(mes.equals("Marzo")){
            return 2;
        }else if(mes.equals("Abril")){
            return 3;
        }else if(mes.equals("Mayo")){
            return 4;
        }else if(mes.equals("Junio")){
            return 5;
        }else if(mes.equals("Julio")){
            return 6;
        }else if(mes.equals("Agosto")){
            return 7;
        }else if(mes.equals("Septiembre")){
            return 8;
        }else if(mes.equals("Octubre")){
            return 9;
        }else if(mes.equals("Noviembre")){
            return 10;
        }else if(mes.equals("Diciembre")){
            return 11;
        }      
        return 0;
    }
}
