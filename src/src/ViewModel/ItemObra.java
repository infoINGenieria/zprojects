/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

import Modelo.Obras;

/**
 *
 * @author matuuar
 */
public class ItemObra  extends Obras{
    private boolean selected;

    public void cast (Obras o){
        this.setId(o.getId());
        this.setObra(o.getObra());
        this.setCodigo(o.getCodigo());
        this.setResponsable(o.getResponsable());
        this.selected = false;
    }
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
}
