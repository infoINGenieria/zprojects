/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import DAO.ParametroDAO;
import Utils.ArrayParametro;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author m4tuu
 */
public class ParametrosSistema {
    
    public static ArrayParametro parametros = new ArrayParametro();
    public static RangoVacaciones rangosVacaciones = new RangoVacaciones();
    
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
    
    public static DefaultComboBoxModel getComboModel(ArrayParametro array) {
        
        String[] elementos = new String[array.size()];
        for(int i = 0; i < array.size(); i++) {
            elementos[i] = array.get(i).valor;
        }
        return new DefaultComboBoxModel(elementos);
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
    
    public static class RangoVacaciones{
        private ArrayList<VacacionesRango> rangos = new ArrayList<VacacionesRango>();
        private boolean leidos = false;
        
        public void CargarRangos(){
            for(int i = 1; i <= getValorInt("RANGO_CANTIDAD"); i ++)
            {
                int minDias, maxDias, cantidad;
                minDias = getValorInt("MIN_DIAS_RANGO_" + i);
                maxDias = getValorInt("MAX_DIAS_RANGO_" + i);
                cantidad = getValorInt("VACACIONES_DIAS_RANGO_" + i);
                rangos.add(new VacacionesRango(minDias, maxDias, cantidad));
                
            }
            leidos = true;
        }
        
        public ArrayList<VacacionesRango> getRangos(){
            return rangos;
        }
        
        public boolean Cargados(){
            return leidos;
        }
        
        public int getDiasDeVacaciones(int antiguedad){
        
            for(VacacionesRango vr: rangos){
                int cant = vr.getCantidadDiasSegunAntiguedad(antiguedad);
                if(cant != -1){
                    return cant;
                }
            }
            return 0;
        }
    }
    
    
    
}
