/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JDialog;

/**
 *
 * @author matuu
 */
public class JDialogCustom extends JDialog{

    Frame parentFrame;
    protected Image icono = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/zilleprojects/resources/icono.png"));
    
    
    public JDialogCustom(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        parentFrame = parent;
        setIconImage(icono);
    }
    
    public void Success(String titulo, String mensaje){
        Mensajes.MostrarMensajeModal(parentFrame, titulo, mensaje, Mensajes.SUCCESS);
    }
    public void Success(String mensaje){
        Success("Éxito", mensaje);
    }

    public void Info(String titulo, String mensaje){
        Mensajes.MostrarMensajeModal(parentFrame, titulo, mensaje, Mensajes.INFO);
    }
    public void Info(String mensaje){
        Info("Información", mensaje);
    }

    public void Error(String titulo, String mensaje){
        Mensajes.MostrarMensajeModal(parentFrame, titulo, mensaje, Mensajes.ERROR);
    }
    
    public void Error(String mensaje){
        Error("Error", mensaje);
    }


    
}
