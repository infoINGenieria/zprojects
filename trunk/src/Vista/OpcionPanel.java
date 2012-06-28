/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

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
   

}
