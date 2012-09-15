/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author matuuar
 */
public class ItemObra  extends Obras{
    private boolean selected;

    public void cast (Obras o){
        this.id = o.getId();
        this.obra = o.getObra();
        this.codigo = o.getCodigo();
        this.responsable = o.getResponsable();
        this.selected = false;
    }
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
}
