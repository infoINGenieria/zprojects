/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import DAO.InformesHorasDAO;
import DAO.ParteDiarioDAO;
import Modelo.InformesHoras;
import Modelo.Operario;
import Modelo.Registro;
import java.util.ArrayList;
import java.util.Date;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author m4tuu
 */
public class Reportes {
    
    public JasperPrint GenerarInformeHorasByOperarioRango(ParteDiarioDAO pd, InformesHorasDAO ihDAO, 
            int operarioId, String periodo, Date desdeF, Date hastaF) {
            //Buscando todos los registros entre las fechas de ese operario
            InformesHoras ih= new InformesHoras();
            ih.setDesdeF(desdeF);
            ih.setHastaF(hastaF);
            ih.setId_operario(operarioId);
            ih.setPeriodo(periodo);
            int res = 0;
            
            ArrayList<Registro> registros= pd.traerRegistroEntre(ih.getId_operario(), 
                    ih.getDesdeF(), ih.getHastaF());
            
            //calcular los tiempos de cada registro
            for(int o=0;o<registros.size();o++){
                ih.tomarElTiempo(registros.get(o));  
            }
            //calcular el total de veces que tiene multifunción
            int multi_fc=pd.contarMultifuncion(ih.getId_operario(), ih.getDesdeF(), ih.getHastaF());
            //calcular los porcentajes de las obras
            String x100Obra= pd.porcentajeObra(ih.getId_operario(), ih.getDesdeF(), ih.getHastaF());
            ih.setMultiFc(multi_fc);
            ih.setX100Obras(x100Obra);    
            ih.CalcularValoresHoras();
            res=ihDAO.guardar(ih);
            JasperPrint jp = ihDAO.registrosHoras(ih);
            return jp;
                
    }
    public JasperPrint GenerarInformeHorasByOperarioRango(int operarioId, String periodo, 
            Date desdeF, Date hastaF) {
        ParteDiarioDAO pd = new ParteDiarioDAO();
        InformesHorasDAO ihDAO = new InformesHorasDAO();
        pd.conectar();
        ihDAO.conectar();
        return GenerarInformeHorasByOperarioRango(pd, ihDAO, operarioId, periodo, desdeF, hastaF);
    }
    
}
