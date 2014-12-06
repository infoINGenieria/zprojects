/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.tablemodel;

import ViewModel.ItemObra;
import javax.swing.event.TableModelEvent;


/**
 *
 * @author matuuar
 */
public class ObrasTablaInforme extends ZilleAbstractTableModel  {
   
    public ObrasTablaInforme(){
        super();
    }
    
    public void clean(){
        datos.clear();
        TableModelEvent evento;
        evento = new TableModelEvent(this);
        avisaSuscriptores (evento);
        
    }
    
    @Override
    public Object getValueAt(int row, int col) {
       ItemObra aux;
       
        aux = (ItemObra)(datos.get(row));

        switch (col)
        {
            case 0:
                return aux.isSelected();
            case 1:
                return aux.getCodigo();
            case 2:
                return aux.getObra();
            case 3:
                return aux.getResponsable();
            default:
                return null;
        }


    }
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex)
        {
            case 0:          
                return Boolean.class;
            default:            
                return String.class;
        }
    }
    
    @Override
    public String getColumnName(int columnIndex)
    {

       switch (columnIndex)
        {
            case 0:
                return "";
            case 1:
                return "Código";
            case 2:
                return "Nombre";
            case 3:
                return "Responsable";
            default:
                return null;
        }
    }
    
    
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        ItemObra aux;

        aux = (ItemObra)(datos.get(row));
        try{
            switch (col)
            {
                case 0:
                    aux.setSelected(Boolean.parseBoolean(value.toString()));
                    break;
                case 1:
                    aux.setCodigo(value.toString());
                    break;
                case 2:
                    aux.setObra((String)value);
                    break;
                case 3:
                    aux.setResponsable((String)value);
                    break;

            }
        }catch(IllegalArgumentException ex){
        

    }
        TableModelEvent evento = new TableModelEvent (this, row, row, col);
        avisaSuscriptores (evento);


    }
    
      @Override
    public int getRowCount() {   
        return datos.size();
    }

    
    @Override
    public boolean isCellEditable(int row, int col) {
            if(col == 0) return true;
            return false;
        }
        
        
    

    @Override
    public int getColumnCount() {
        return 4;
    }

    
}
