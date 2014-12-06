/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;


import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 *
 * @author matuu
 */
public final class EditorDeCeldasFloat extends JTextField {


    private Toolkit toolkit;
    private NumberFormat integerFormatter;

    public EditorDeCeldasFloat(float value, int columns) {
        super(columns);
        this.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                FocusGained(evt);
            }
        });
        toolkit = Toolkit.getDefaultToolkit();
        integerFormatter = NumberFormat.getNumberInstance();
        
        setValue(value);
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


    public float getValue() {
        float retVal = 0;
        try {
            retVal = integerFormatter.parse(getText()).floatValue();
        } catch (ParseException e) {
            toolkit.beep();
        }
        return retVal;
    }

    public void setValue(float value) {
        setText(integerFormatter.format(value));
    }

    @Override
    protected Document createDefaultModel() {
        return new EditorDeFloatDocument();
    }

    

    protected class EditorDeFloatDocument extends PlainDocument {

        @Override
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
    }





}
