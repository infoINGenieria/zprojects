/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class Mensaje {

   public void error(Exception ex){
       String texto = ex.getMessage();
       JOptionPane.showMessageDialog(null, texto.substring(texto.indexOf(":")+1, texto.length()), "Error del Sistema", JOptionPane.ERROR_MESSAGE);
   }
   public void errorMSJ(String texto){
       JOptionPane.showMessageDialog(null, texto.substring(texto.indexOf(":")+1, texto.length()), "Error del Sistema", JOptionPane.ERROR_MESSAGE);
   }

   public void avisoMSJ(String texto){
       JOptionPane.showMessageDialog(null, texto.substring(texto.indexOf(":")+1, texto.length()), "Aviso", JOptionPane.INFORMATION_MESSAGE);
   }
}
