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
public class DialogPanel extends javax.swing.JPanel {
     

        @Override
        public void paintComponent(Graphics g) {
            Dimension tamanio = getSize();
            ImageIcon imagenFondo = new ImageIcon(getClass().
                    getResource("/zilleprojects/resources/panel1.png"));
            g.drawImage(imagenFondo.getImage(), 0, 0,
                    tamanio.width, tamanio.height, null);
            setOpaque(false);

            super.paintComponent(g);
        }
}