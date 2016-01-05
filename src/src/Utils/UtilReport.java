/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Vista.Mensajes;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Frame;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import zilleprojects.form.JExportarReporte;

/**
 *
 * @author m4tuu
 */
public class UtilReport {
    
    public static void MostrarEnJViewer(JasperPrint jp){
         //JasperPrintManager.printReport(jasperPrint, false);
            JasperViewer jviewer;
        try {
            jviewer = new JasperViewer(jp, false);
            jviewer.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
            jviewer.setTitle("Informe");
            jviewer.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(UtilReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void MostrarDialogSeleccion(Frame parent, JasperPrint jp){
        if(jp==null){
            Mensajes.MostrarMensajeModal(null, "Error", "Ocurrió un error al generar el informe.", Mensajes.ERROR);
            
        }else {
            JExportarReporte exp = new JExportarReporte(parent, true, jp);
            exp.setLocationRelativeTo(null);
            exp.setVisible(true);
        }
    }
}
