/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;


import java.sql.Time;
import java.util.LinkedList;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author matu
 */
public class TablaHorarioIngresoModel  extends AbstractTableModel {

    private LinkedList datos = new LinkedList();
    private LinkedList listeners = new LinkedList();

    public TablaHorarioIngresoModel(){
        super();
        
    }

   

    public void addRegistro (Registro r)
    {
        TableModelEvent evento;
        if(datos.size()== 0){
            datos.add (r);        
            evento = new TableModelEvent (this, this.getRowCount()-1,
                this.getRowCount()-1, TableModelEvent.ALL_COLUMNS,
                TableModelEvent.INSERT);
        }
        else{
            datos.removeAll(datos);
            datos.add (r);        
            evento = new TableModelEvent (this, this.getRowCount()-1,
                this.getRowCount()-1, TableModelEvent.ALL_COLUMNS,
                TableModelEvent.UPDATE);
        }
        avisaSuscriptores (evento);
         
    }


    @Override
    public Object getValueAt(int row, int col) {
       Registro aux;
       
        aux = (Registro)(datos.get(row));

        switch (col)
        {
            case 0:
                return aux.getHs_salida();
            case 1:
                return aux.getHs_inicio();
            case 2:
                return aux.getHs_ialmuerzo();
            case 3:
                return aux.getHs_falmuerzo();
            case 4:
                return aux.getHs_fin();
            case 5:
                return aux.getHs_llegada();
            case 6:
                return aux.isEspecial();
            
            default:
                return null;
        }


    }
    

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex)
        {
            case 6:          
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
                return "Hs Salida";
            case 1:
                return "Hs Inicio Tareas";
           case 2:
               return "Hs Almuerzo";
            case 3:
                return "Hs Fin Almuerzo";
           case 4:
                return "Hs Fin Tareas";
            case 5:
                return "Hs Llegada";
            case 6:
                return "Especial";
            default:
                return null;
        }
    }


    public void insertarFila(Registro value, int row) {
        datos.remove(row);
        datos.add(row, value);
        TableModelEvent evento = new TableModelEvent (this, row, row, TableModelEvent.ALL_COLUMNS);
        avisaSuscriptores (evento);
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
Registro aux;

        aux = (Registro)(datos.get(row));
try{
        switch (col)
        {
            case 0:
                if(!value.toString().isEmpty())
                aux.setHs_salida(getHora((String)value));
                break;
            case 1:
               if(!value.toString().isEmpty())
                aux.setHs_inicio(getHora((String)value));
                break;
            case 2:
                if(!value.toString().isEmpty())
                aux.setHs_ialmuerzo(getHora((String)value));
                break;
            case 3:
                if(!value.toString().isEmpty())
                aux.setHs_falmuerzo(getHora((String)value));
                break;
            case 4:
                if(!value.toString().isEmpty())
                aux.setHs_fin(getHora((String)value));
                break;
            case 5:
               if(!value.toString().isEmpty())
                aux.setHs_llegada(getHora((String)value));
                break;
            case 6:
                aux.setEspecial(((Boolean) value).booleanValue());
                break;
            
            
        }
    }catch(IllegalArgumentException ex){
        if (SwingUtilities.isEventDispatchThread()) {
                        Vista.OpcionPanel.showMessageDialog(null,
                            "La columna \"" + getColumnName(col)
                            + "\" solo acepta el formato hh:mm o hh:mm:ss.");
                    } else {
                        System.err.println("La columna \"" + getColumnName(col)
                            + "\" solo acepta el formato hh:mm.");
                    }

    }

        TableModelEvent evento = new TableModelEvent (this, row, row, col);

        avisaSuscriptores (evento);


    }

    private Time getHora(String entrada){
        Time hora;
        if(entrada.length()==8){
            hora= Time.valueOf(entrada.replace('.', ':'));
        }
        else if((entrada.length()<=3)){
            if(entrada.contains(".")||entrada.contains(":")){
                hora=Time.valueOf(entrada.replace('.', ':').concat("00:00"));
            }else{
                hora=Time.valueOf(entrada.concat(":00:00"));
            }

        }

        else{
            hora= Time.valueOf(entrada.replace('.', ':').concat(":00"));
        }
        
        return hora;
       
    }
    @Override
    public int getRowCount() {
        
        return datos.size();
    }

    public Registro getFila(int row){
        return (Registro)(datos.get(row));
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
        
        
    

    public int getColumnCount() {
        return 7;
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        // Añade el suscriptor a la lista de suscriptores
        listeners.add (l);
    }


    @Override
    public void removeTableModelListener(TableModelListener l) {
        // Elimina los suscriptores.
        listeners.remove(l);
    }


    private void avisaSuscriptores (TableModelEvent evento)
    {
        int i;

        // Bucle para todos los suscriptores en la lista, se llama al metodo
        // tableChanged() de los mismos, pasándole el evento.
        for (i=0; i<listeners.size(); i++)
            ((TableModelListener)listeners.get(i)).tableChanged(evento);
    }

}

