/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo.tablemodel;


import Modelo.Materiales;
import java.util.LinkedList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author matu
 */
public class TablaDatosTransporte  extends ZilleAbstractTableModel {  


    @Override
    public Object getValueAt(int row, int col) {
       Materiales aux;
       
        aux = (Materiales)(datos.get(row));

        switch (col)
        {
            case 0:
                return aux.isOk();
            case 1:
                return aux.getMaterial();
            case 2:
                return aux.getCantidad();
            case 3:
                return aux.getDistancia();
            case 4:
                return aux.getViajes();
            case 5:
                return aux.getCantera_cargadero();            
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
                return "¿OK?";
            case 1:
                return "Material";
           case 2:
               return "Cantidad (m3)";
            case 3:
                return "Distancia";
           case 4:
                return "Viajes";
            case 5:
                return "Cantera/Cargaderedo/Depósito";
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        Materiales aux;

        aux = (Materiales)(datos.get(row));
        try{
            switch (col)
            {
                case 0:
                    aux.setOk(((Boolean) value).booleanValue());
                    break;
                case 1:
                   if(!value.toString().isEmpty())
                    aux.setMaterial((String)value);
                    break;
                case 2:
                    if(!value.toString().isEmpty())
                    aux.setCantidad((String)value);
                    break;
                case 3:
                    if(!value.toString().isEmpty())
                    aux.setDistancia((String)value);
                    break;
                case 4:
                    if(!value.toString().isEmpty())
                    aux.setViajes((String)value);
                    break;
                case 5:
                   if(!value.toString().isEmpty())
                    aux.setCantera_cargadero((String)value);
                    break; 

            }
        }catch(IllegalArgumentException ex){
            /*if (SwingUtilities.isEventDispatchThread()) {
                            Vista.OpcionPanel.showMessageDialog(null,
                                "La columna \"" + getColumnName(col)
                                + "\" solo acepta el formato hh:mm o hh:mm:ss.");
                        } else {
                            System.err.println("La columna \"" + getColumnName(col)
                                + "\" solo acepta el formato hh:mm.");
                        }*/

        }

        TableModelEvent evento = new TableModelEvent (this, row, row, col);

        avisaSuscriptores (evento);


    }

    
    @Override
    public int getRowCount() {
        
        return datos.size();
    }


    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        /*if (col < 1) {
            return false;
        } else {*/
        
        
            return true;
        }
        
        
    

    @Override
    public int getColumnCount() {
        return 6;
    }


}

