/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.JOptionPane;

/**
 *
 * @author matu
 */
public  class  OpcionPanel extends JOptionPane{
    Icon icono = (Icon) Toolkit.getDefaultToolkit().getImage(getClass().getResource("/zilleprojects/resources/icono.png"));

    
    public OpcionPanel() {  
        setIcon(icon);
    }

    public void setIcono(Image imagen){
        setIcon(icon);
    }
   
    public static void showError(Component comp, String msg){
        OpcionPanel.showMessageDialog(comp, msg, "Error", ERROR_MESSAGE);
    }
    public static void showError(String msg){
        OpcionPanel.showError(null, msg);
    }

    public static void showError(String[] msgArr){
        String msj = "Se encontraron los siguientes errores:\n";
        for(String err:msgArr){
            msj += "- " + err;
        }
        OpcionPanel.showError(null, msj);
    }
    public static void showInfo(String msg){
        OpcionPanel.showMessageDialog(null, msg, "Error", INFORMATION_MESSAGE);
    }

    public static void showSuccess(String msg){
        OpcionPanel.showMessageDialog(null, msg, "Error", PLAIN_MESSAGE);
    }

}
