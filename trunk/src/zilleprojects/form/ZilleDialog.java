/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UsuarioesDialog.java
 *
 * Created on 11/12/2013, 08:22:33
 */
package zilleprojects.form;


import DAO.AbstractDAO;
import Modelo.EntidadAbstracta;
import Modelo.tablemodel.ZilleAbstractTableModel;
import Vista.JDialogCustom;
import Vista.JTableCustom;
import Vista.OpcionPanel;
import Vista.PanelEsquinaAzul;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.jdesktop.application.Action;
import org.jdesktop.application.Task;

/**
 *
 * @author matuu
 */
public abstract class ZilleDialog extends JDialogCustom {
    
    
    
    /** Creates new form UsuarioesDialog */
    public ZilleDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);  
        initComponents();
        configurarTabla();
        cargarEntidades().execute();
        ConfigurarDialog();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        wrap = new PanelEsquinaAzul();
        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnRecargar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatos = new JTableCustom();
        lblTotalEntidad = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblTotalFiltrado = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtBuscarTexto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(zilleprojects.ZilleProjectsApp.class).getContext().getResourceMap(ZilleDialog.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setLocationByPlatform(true);
        setName("Form"); // NOI18N

        wrap.setName("wrap"); // NOI18N
        wrap.setLayout(new java.awt.BorderLayout());

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);

        lblTitulo.setFont(lblTitulo.getFont().deriveFont(lblTitulo.getFont().getStyle() | java.awt.Font.BOLD, lblTitulo.getFont().getSize()+4));
        lblTitulo.setLabelFor(this);
        lblTitulo.setText(resourceMap.getString("lblTitulo.text")); // NOI18N
        lblTitulo.setName("lblTitulo"); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(zilleprojects.ZilleProjectsApp.class).getContext().getActionMap(ZilleDialog.class, this);
        btnRecargar.setAction(actionMap.get("cargarEntidades")); // NOI18N
        btnRecargar.setIcon(resourceMap.getIcon("btnRecargar.icon")); // NOI18N
        btnRecargar.setText(resourceMap.getString("btnRecargar.text")); // NOI18N
        btnRecargar.setName("btnRecargar"); // NOI18N
        jPanel3.add(btnRecargar);

        btnNuevo.setAction(actionMap.get("showNuevaEntidad")); // NOI18N
        btnNuevo.setIcon(resourceMap.getIcon("btnNuevo.icon")); // NOI18N
        btnNuevo.setText(resourceMap.getString("btnNuevo.text")); // NOI18N
        btnNuevo.setName("btnNuevo"); // NOI18N
        jPanel3.add(btnNuevo);

        btnEditar.setAction(actionMap.get("showModificarEntidad")); // NOI18N
        btnEditar.setIcon(resourceMap.getIcon("btnEditar.icon")); // NOI18N
        btnEditar.setText(resourceMap.getString("btnEditar.text")); // NOI18N
        btnEditar.setName("btnEditar"); // NOI18N
        jPanel3.add(btnEditar);

        btnEliminar.setAction(actionMap.get("EliminarEntidad")); // NOI18N
        btnEliminar.setIcon(resourceMap.getIcon("btnEliminar.icon")); // NOI18N
        btnEliminar.setText(resourceMap.getString("btnEliminar.text")); // NOI18N
        btnEliminar.setName("btnEliminar"); // NOI18N
        jPanel3.add(btnEliminar);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblDatos.setAutoCreateRowSorter(true);
        tblDatos.setFont(tblDatos.getFont().deriveFont(tblDatos.getFont().getSize()+2f));
        tblDatos.setGridColor(resourceMap.getColor("tblDatos.gridColor")); // NOI18N
        tblDatos.setName("tblDatos"); // NOI18N
        tblDatos.setRowHeight(24);
        tblDatos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDatosMouseClicked(evt);
            }
        });
        tblDatos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblDatosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblDatos);

        lblTotalEntidad.setFont(lblTotalEntidad.getFont().deriveFont(lblTotalEntidad.getFont().getStyle() | java.awt.Font.BOLD, lblTotalEntidad.getFont().getSize()+2));
        lblTotalEntidad.setText(resourceMap.getString("lblTotalEntidad.text")); // NOI18N
        lblTotalEntidad.setName("lblTotalEntidad"); // NOI18N

        jLabel17.setForeground(resourceMap.getColor("jLabel20.foreground")); // NOI18N
        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        lblTotalFiltrado.setFont(lblTotalFiltrado.getFont().deriveFont(lblTotalFiltrado.getFont().getStyle() | java.awt.Font.BOLD, lblTotalFiltrado.getFont().getSize()+2));
        lblTotalFiltrado.setText(resourceMap.getString("lblTotalFiltrado.text")); // NOI18N
        lblTotalFiltrado.setName("lblTotalFiltrado"); // NOI18N

        jLabel20.setForeground(resourceMap.getColor("jLabel20.foreground")); // NOI18N
        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        txtBuscarTexto.setText(resourceMap.getString("txtBuscarTexto.text")); // NOI18N
        txtBuscarTexto.setName("txtBuscarTexto"); // NOI18N
        txtBuscarTexto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarTextoKeyReleased(evt);
            }
        });

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 921, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotalFiltrado, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalEntidad)
                    .addComponent(jLabel17)
                    .addComponent(lblTotalFiltrado)
                    .addComponent(jLabel20)
                    .addComponent(txtBuscarTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        wrap.add(jPanel1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wrap, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wrap, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarTextoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarTextoKeyReleased
       
        if(txtBuscarTexto.getText().length()>0){
            filtrarEntidades().execute();
        }if(txtBuscarTexto.getText().isEmpty()){
           cargarEntidades().execute();
       }
    }//GEN-LAST:event_txtBuscarTextoKeyReleased

    private void tblDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatosMouseClicked
        setEntidadAbstracta(getTableModel().getFila(tblDatos.getSelectedRow()));
    }//GEN-LAST:event_tblDatosMouseClicked

    private void tblDatosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDatosKeyReleased
        setEntidadAbstracta(getTableModel().getFila(tblDatos.getSelectedRow()));
    }//GEN-LAST:event_tblDatosKeyReleased

    /* Metodos que debe ser sobreescritos*/
    protected abstract String getTablaName();
    protected abstract String getNombreEntidad();
    protected abstract void limpiarEntidad();
    protected abstract AbstractDAO getDao();
    protected abstract EntidadAbstracta getEntidadAbstracta();
    protected abstract void setEntidadAbstracta(EntidadAbstracta ea);
    protected abstract ZilleAbstractTableModel getTableModel();
    protected abstract ZilleAbstractTableModel setNewTableModel();
    protected abstract void setTableModel(ZilleAbstractTableModel ea);
    protected abstract void setTitulo();
    protected abstract Dimension getSizeMainWindow();
    protected abstract JDialog getAMEntidad();
    protected abstract JButton getBtnAceptarEntidadEdit();
    protected abstract JButton getBtnCancelarEntidadEdit();
    protected abstract void CargarEntidadAlEditar(EntidadAbstracta entidad);   
    protected abstract boolean GuardarEntidadAlEditar();
    protected abstract Dimension getSizeDialogEdit();

    private void ConfigurarDialog() {
        setTitulo();
        setSizeDialogo();
        lblTitulo.setText("Administración de " + getNombreEntidad());
        
    }
    
    
    
    private void configurarTabla() {
        setTableModel(setNewTableModel());
        
        final AbstractDAO udao =  getDao();
        udao.conectar();
        ///Esto es para guardar los cambios cuando se edita desde la tabla
        getTableModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // Solo actualizo la db si hubieron cambios en alguna columna
                if ( e.getColumn() != -1){
                    int row = e.getFirstRow();
                    EntidadAbstracta entidad = getTableModel().getFila(row);
                    if(entidad != null){
                        if(udao.guardar(entidad)== 0)                            
                        {
                            Error("Existen datos inválidos. Por favor, compruebe los datos.");
                        }
                    }
                }
            }
        });
    }
     @Action
    public final Task cargarEntidades() {
        limpiarFiltros();
        return new CargarEntidadesTask(zilleprojects.ZilleProjectsApp.getApplication());
    }

    private void setSizeDialogo() {
        
        this.setMinimumSize(getSizeMainWindow());
        this.setMaximumSize(getSizeMainWindow());
        this.setPreferredSize(getSizeMainWindow());
        this.pack();
    }
     
    private class CargarEntidadesTask extends org.jdesktop.application.Task<Object, Void> {
        List<EntidadAbstracta> entidadesList;
        CargarEntidadesTask(org.jdesktop.application.Application app) {
            super(app);
        }

        @Override
        protected Object doInBackground()  {
            final AbstractDAO udao = getDao();
            udao.conectar();
            entidadesList = udao.cargarTodos();
            totalFiltrado = totalEntidad = udao.count(getTablaName());
            return null;
        }

        @Override
        protected void succeeded(Object result) {

            if (entidadesList != null) {
                configurarTabla();
                for(EntidadAbstracta c:entidadesList){
                    getTableModel().addFila(c);
                }
                tblDatos.setModel(getTableModel());
                configurarColumnas();
                lblTotalEntidad.setText(String.valueOf(totalEntidad));
                lblTotalFiltrado.setText(String.valueOf(totalFiltrado));
            } else {
                OpcionPanel.showMessageDialog(null, "Falló la carga de "+ getNombreEntidad() +". Intente nuevamente.",
                        "Fallo de Conexión", OpcionPanel.INFORMATION_MESSAGE);
            }
        }
    }

    @Action
    public final Task filtrarEntidades() {
        
        return new FiltrarDatosTask(zilleprojects.ZilleProjectsApp.getApplication());
    }

    private class FiltrarDatosTask extends org.jdesktop.application.Task<Object, Void> {
        ArrayList<EntidadAbstracta> entidades;
        String query;
        Date fechaDesde, fechaHasta;
        FiltrarDatosTask(org.jdesktop.application.Application app) {
            super(app);
            query = txtBuscarTexto.getText();
            
            
        }

        @Override
        protected Object doInBackground()  {
            AbstractDAO dao = getDao();
            dao.conectar();
            entidades = dao.filtrarPorTexto(query);
            totalFiltrado = entidades.size();
            return null;
        }

        @Override
        protected void succeeded(Object result) {

            if (entidades != null) {
                configurarTabla();
                for(EntidadAbstracta c:entidades){
                    getTableModel().addFila(c);
                }

                tblDatos.setModel(getTableModel());
                configurarColumnas();
                lblTotalFiltrado.setText(String.valueOf(totalFiltrado));
            } else {
                OpcionPanel.showMessageDialog(null, "Falló la carga de " + getNombreEntidad() +". Intente nuevamente.",
                        "Fallo de Conexión", OpcionPanel.INFORMATION_MESSAGE);
                cargarEntidades().execute();
            }
        }
    }

    @Action
    public final Task GuardarEntidad() {
        if(!GuardarEntidadAlEditar()){
            OpcionPanel.showError(getEntidadAbstracta().getErrores());
            return null;
        }
        return new GuardarEntidadTask(zilleprojects.ZilleProjectsApp.getApplication());
    }

    private class GuardarEntidadTask extends org.jdesktop.application.Task<Object, Void> {
        boolean guardadoOk;
        boolean esUpdate = false;
        GuardarEntidadTask(org.jdesktop.application.Application app) {
            super(app);
            
            esUpdate = getEntidadAbstracta().getId() == 0 ? false : true;
        }

        @Override
        protected Object doInBackground()  {
            AbstractDAO dao = getDao();
            dao.conectar();
            if(esUpdate)
                guardadoOk = dao.modificar(getEntidadAbstracta()) != 0;
            else{
                int i = dao.guardar(getEntidadAbstracta());
                if(i != 0){
                    getEntidadAbstracta().setId(i);
                    guardadoOk = true;
                }
            }
            return null;
        }

        @Override
        protected void succeeded(Object result) {
            String msg;
            if (guardadoOk) {
                if(esUpdate){
                    getTableModel().insertarFila(getEntidadAbstracta(), tblDatos.getSelectedRow());
                    msg = "Éxito! Se actualizó la información de " + getEntidadAbstracta();
                }else{
                    getTableModel().addFila(getEntidadAbstracta());
                    msg = "Éxito! Se agrego la entidad: " +getEntidadAbstracta();
                }
//                tblDatos.setModel(getTableModel());
//                configurarColumnas();
                getAMEntidad().dispose();
                limpiarEntidadEdit();
                cargarEntidades().execute();
                Success("Éxito", msg);               
            } else {
                Error("Error", "Falló la carga de " +getNombreEntidad() +
                        ". Intente nuevamente.");
            }
        }
    }
    
    public JTable getTabla() {
        return tblDatos;
    }
    
    public void setWidths(int[] valores) {
        widths = valores;
    }
    
    int[] widths = null;
    
    public void configurarColumnas() {
        if (widths!= null) {
            for(int i= 0; i < widths.length; i++) {
                tblDatos.getColumnModel().getColumn(i).setWidth(widths[i]);
            }
        }
    }

    @Action
    public final Task EliminarEntidad() {

        if(getEntidadAbstracta().getId()==0 || tblDatos.getSelectedRow() == -1){

            super.Info("Eliminar", "Por favor, primero seleccione una fila.");
            return null;
        }
        if(!(OpcionPanel.YES_OPTION == JOptionPane.showConfirmDialog(ZilleDialog.this,
                "¿Realmente desea eliminar la entidad "+ getEntidadAbstracta() +"?", "¿Eliminar?",
                OpcionPanel.YES_NO_OPTION, OpcionPanel.QUESTION_MESSAGE))){
            
            return null;
        }
        return new EliminarEntidadTask(zilleprojects.ZilleProjectsApp.getApplication());
    }

     class EliminarEntidadTask extends org.jdesktop.application.Task<Object, Void> {
        boolean eliminadoOK;
        String nombre = getEntidadAbstracta().toString();
        EliminarEntidadTask(org.jdesktop.application.Application app) {
            super(app);
        }

        @Override
        protected Object doInBackground()  {
            AbstractDAO dao = getDao();
            dao.conectar();
            eliminadoOK = dao.eliminar(getEntidadAbstracta());
            return null;
        }

        @Override
        protected void succeeded(Object result) {
            if (eliminadoOK) {
                
                cargarEntidades().execute();
                Success("Éxito", "Se eliminó correctamente la entidad: " + nombre);
                
            } else {
                Error("Error", "Falló la eliminación de la entidad "  + nombre +
                        ". Intente nuevamente.");
            }
        }
    }

    @Action
    public void limpiarFiltros(){
        txtBuscarTexto.setText("");
    }

    @Action
    public void showNuevaEntidad() {
       limpiarEntidad();
       internalAMdialog = getAMEntidad();
       internalAMdialog.setTitle("Nueva entidad: " + getNombreEntidad());
       CargarEntidadAlEditar(getEntidadAbstracta());
       internalAMdialog.setLocationRelativeTo(null);
       internalAMdialog.getRootPane().setDefaultButton(getBtnAceptarEntidadEdit());
       internalAMdialog.setMinimumSize(getSizeDialogEdit());
       internalAMdialog.setPreferredSize(getSizeDialogEdit());
       internalAMdialog.pack();
       internalAMdialog.setVisible(true);
    }
    
    @Action
    public void showModificarEntidad() {
        if(tblDatos.getSelectedRow() != -1) {
            setEntidadAbstracta(getTableModel().getFila(tblDatos.getSelectedRow()));
        }
        if(tblDatos.getSelectedRow() == -1 && getEntidadAbstracta().getId() == 0){
            Info("Información", "Seleccione una fila primero.");
            return;
        }
       
       internalAMdialog = getAMEntidad();
       CargarEntidadAlEditar(getEntidadAbstracta());
       internalAMdialog.setTitle("Editar " + getNombreEntidad());       
       internalAMdialog.setLocationRelativeTo(null);
       internalAMdialog.getRootPane().setDefaultButton(getBtnAceptarEntidadEdit());     
       internalAMdialog.setMinimumSize(getSizeDialogEdit());
       internalAMdialog.setPreferredSize(getSizeDialogEdit());
       internalAMdialog.pack();
       internalAMdialog.setVisible(true);
    }

    @Action
    public void limpiarEntidadEdit(){
        try{
        setEntidadAbstracta(getTableModel().getFila(tblDatos.getSelectedRow()));
        }catch(Exception ex){
            limpiarEntidad();
        }
        
    
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRecargar;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotalEntidad;
    private javax.swing.JLabel lblTotalFiltrado;
    private javax.swing.JTable tblDatos;
    private javax.swing.JTextField txtBuscarTexto;
    private javax.swing.JPanel wrap;
    // End of variables declaration//GEN-END:variables
    
    long totalEntidad = 0;
    long totalFiltrado = 0;
    
    private JDialog internalAMdialog;
}