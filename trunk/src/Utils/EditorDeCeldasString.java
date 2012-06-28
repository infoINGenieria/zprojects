/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;


import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 *
 * @author matuu
 */
public class EditorDeCeldasString extends JTextField {



    public EditorDeCeldasString( int columns) {
        super(columns);
        this.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                FocusGained(evt);
            }
        });

    }

    private void FocusGained(java.awt.event.FocusEvent evt) {
        this.selectAll();
    }

    @Override
    protected boolean processKeyBinding(final KeyStroke ks,final KeyEvent e, final int condition, final boolean pressed) {
        if (hasFocus()) {
            return super.processKeyBinding(ks, e, condition, pressed);
        } else {
            requestFocus();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    processKeyBinding(ks, e, condition, pressed);
                }
            });
        return true;
        }
    }


    public String getValue() {
        String retVal = null;
            retVal = getText().toString();
        return retVal;
    }

    public void setValue(String value) {
        try{

            setText(value);
        }
        catch (IllegalArgumentException ex) {
            //
        }
    }


}
