/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author matuuar
 */
public class PanelEsquinaAzul extends javax.swing.JPanel {
     

        @Override
        public void paintComponent(Graphics g) {
            ImageIcon imagenFondo = new ImageIcon(getClass().
                    getResource("/zilleprojects/resources/bg5.png"));
            g.drawImage(imagenFondo.getImage(), 0, 0,
                    640, 389, null);
            setOpaque(false);

            super.paintComponent(g);
        }
}