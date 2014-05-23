/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import DAO.ParametroDAO;
import Utils.ArrayParametro;

/**
 *
 * @author m4tuu
 */
public class ParametrosSistema {
    
    static ArrayParametro parametros = new ArrayParametro();
    static RangoVacaciones rangosVacaciones = new RangoVacaciones();
    
    public static Parametro get(String clave){
        return parametros.Find(clave);
    }
    
    public static ArrayParametro getGrupo(String grupo){
        ArrayParametro aux = new ArrayParametro();
        for(Parametro p: parametros){
            if(p.getClaveGrupo().equals(grupo.toUpperCase())){
                aux.add(p);
            }
        }
        return aux;
    }
    
    public static void CargarParametros(){
        ParametroDAO dao = new ParametroDAO();
        dao.conectar();
        parametros = (ArrayParametro) dao.CargarTodo();
        rangosVacaciones.CargarRangos();
    }
    
    public static int getValorInt(String clave){
        try{
            return Integer.parseInt(ParametrosSistema.get(clave).getValor());
        }catch(Exception ex){
            return 0;
        }
    }
    
    static class RangoVacaciones{
        private int minDiasRango_1, minDiasRango_2,minDiasRango_3, minDiasRango_4;
        private int maxDiasRango_1, maxDiasRango_2, maxDiasRango_3, maxDiasRango_4;
        private  int diasRango1, diasRango2, diasRango3, diasRango4;
        private boolean leidos = false;
        
        public void CargarRangos(){
            minDiasRango_1 = getValorInt("MIN_DIAS_RANGO_1");
            maxDiasRango_1 = getValorInt("MAX_DIAS_RANGO_1");
            minDiasRango_2 = getValorInt("MIN_DIAS_RANGO_2");
            maxDiasRango_2 = getValorInt("MAX_DIAS_RANGO_2");
            minDiasRango_3 = getValorInt("MIN_DIAS_RANGO_3");
            maxDiasRango_3 = getValorInt("MAX_DIAS_RANGO_3");
            minDiasRango_4 = getValorInt("MIN_DIAS_RANGO_4");
            maxDiasRango_4 = getValorInt("MAX_DIAS_RANGO_4");
            diasRango1 = getValorInt("VACACIONES_DIAS_RANGO_1");
            diasRango2 = getValorInt("VACACIONES_DIAS_RANGO_2");
            diasRango3 = getValorInt("VACACIONES_DIAS_RANGO_3");
            diasRango4 = getValorInt("VACACIONES_DIAS_RANGO_4");
            leidos = true;
        }
        
        public boolean Cargados(){
            return leidos;
        }
        
        public int getDiasDeVacaciones(int antiguedad){
        
            if(minDiasRango_1 <= antiguedad && maxDiasRango_1 >= antiguedad){
                    return diasRango1;
            }else if(minDiasRango_2 <= antiguedad && maxDiasRango_2 >= antiguedad){
                    return diasRango2;
            }else if(minDiasRango_3 <= antiguedad && maxDiasRango_3 >= antiguedad){
                    return diasRango3;
            }else if(minDiasRango_4 <= antiguedad && maxDiasRango_4 >= antiguedad){
                    return diasRango4;
            }
            return 0;
        }
    }
    
    
    
}
