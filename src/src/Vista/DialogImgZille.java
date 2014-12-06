/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author matuuar
 */
public class DialogImgZille  extends javax.swing.JPanel {
    
    @Override
        public void paintComponent(Graphics g) {
            //Dimension tamanio = getSize();
            ImageIcon imagenFondo = new ImageIcon(getClass().
                    getResource("/zilleprojects/resources/zille.png"));
            g.drawImage(imagenFondo.getImage(), 0, 0,
                    419, 149, null);
            setOpaque(false);

            super.paintComponent(g);
        }
}
