/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author Matu
 */
public class PanelAlarma extends javax.swing.JPanel {
     

        @Override
        public void paintComponent(Graphics g) {
            //Dimension tamanio = getSize();
            ImageIcon imagenFondo = new ImageIcon(getClass().
                    getResource("/zilleprojects/resources/alarma.png"));
            g.drawImage(imagenFondo.getImage(), 10, 0,
                    261, 36, null);
            setOpaque(false);

            super.paintComponent(g);
        }
    
}
