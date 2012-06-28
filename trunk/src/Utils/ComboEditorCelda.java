/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.Component;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author matuu
 */
public class ComboEditorCelda extends DefaultCellEditor {

    List<String[]> values;
                

    public ComboEditorCelda(List<String[]> values) {
        super(new JComboBox());
        this.values = values;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {

        JComboBox combo = (JComboBox) getComponent();
        combo.removeAllItems();
        String[] valores = values.get(row);

        for (int i = 0; i < valores.length; i++) {
            combo.addItem(valores[i]);
        }
        combo.setSelectedItem(value);

        return combo;
    }
}
