/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;


import java.awt.event.KeyEvent;
import java.sql.Time;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 *
 * @author matuu
 */
public class EditorCeldasTime extends JTextField {


    

    public EditorCeldasTime( int columns) {
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


    public Time getValue() {
        Time retVal = null;
            retVal = Time.valueOf(getText().toString());    
        return retVal;
    }

    public void setValue(String value) {
        try{
            Time t=Time.valueOf(value);
            setText((value));
        }
        catch (IllegalArgumentException ex) {
            //
        }
    }
/*
    @Override
    protected Document createDefaultModel() {
        return new EditorDeEnterosDocument();
    }

    */
/*
    protected class EditorDeEnterosDocument extends PlainDocument {

        public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {

            char[] source = str.toCharArray();
            char[] result = new char[source.length];
            int j = 0;

            for (int i = 0; i < result.length; i++) {
                if (Character.isDigit(source[i])) {
                    result[j++] = source[i];
                } else {
                    toolkit.beep();
                }
            }
            super.insertString(offs, new String(result, 0, j),(javax.swing.text.AttributeSet) a);
        }
    }*/





}
