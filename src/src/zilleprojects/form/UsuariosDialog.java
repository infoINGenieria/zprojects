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


import DAO.UsuarioDAO;
import Modelo.Usuario;
import Modelo.tablemodel.UsuariosTableModel;
import Vista.JDialogCustom;
import Vista.JTableCustom;
import Vista.OpcionPanel;
import Vista.PanelAzul;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.jdesktop.application.Action;
import org.jdesktop.application.Task;

/**
 *
 * @author matuu
 */
public class UsuariosDialog extends JDialogCustom{
    
    
    
    /** Creates new form UsuarioesDialog */
    public UsuariosDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);  
        initComponents();
        //Copiamos la definicion de los numeros decimales
        
        //Cada dialogo debe pasarle el parent a las alertas, 
        //así ellas tiene de parent form a este dialogo.
        //Mensajes.Setup((JDialog) this);
        configurarTabla();
        cargarUsuarios().execute();
        
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AMUsuario = new javax.swing.JDialog(this, true);
        jPanel5 = new PanelAzul();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtNombreUsuarioEdit = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtPassEdit = new javax.swing.JPasswordField();
        jLabel13 = new javax.swing.JLabel();
        txtPassConfirmEdit = new javax.swing.JPasswordField();
        jLabel19 = new javax.swing.JLabel();
        cmbRoles = new javax.swing.JComboBox();
        btnAceptarUsuarioEdit = new javax.swing.JButton();
        btnCancelarUsuarioEdit = new javax.swing.JButton();
        jPanel1 = new PanelAzul();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        bttnRecargarUsuarios = new javax.swing.JButton();
        btnNuevoUsuario = new javax.swing.JButton();
        btnEditarUsuario = new javax.swing.JButton();
        btnEliminarUsuario = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new JTableCustom();
        lblTotalEntidad = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblTotalFiltrado = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtBuscarTexto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        AMUsuario.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        AMUsuario.setLocationByPlatform(true);
        AMUsuario.setMinimumSize(new java.awt.Dimension(450, 350));
        AMUsuario.setModalityType(java.awt.Dialog.ModalityType.TOOLKIT_MODAL);
        AMUsuario.setName("AMUsuario"); // NOI18N
        AMUsuario.setResizable(false);
        AMUsuario.getContentPane().setLayout(new javax.swing.BoxLayout(AMUsuario.getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setOpaque(false);

        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.GridLayout(4, 2, 15, 10));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(zilleprojects.ZilleProjectsApp.class).getContext().getResourceMap(UsuariosDialog.class);
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        jPanel6.add(jLabel11);

        txtNombreUsuarioEdit.setText(resourceMap.getString("txtNombreUsuarioEdit.text")); // NOI18N
        txtNombreUsuarioEdit.setName("txtNombreUsuarioEdit"); // NOI18N
        jPanel6.add(txtNombreUsuarioEdit);

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        jPanel6.add(jLabel12);

        txtPassEdit.setText(resourceMap.getString("txtPassEdit.text")); // NOI18N
        txtPassEdit.setName("txtPassEdit"); // NOI18N
        jPanel6.add(txtPassEdit);

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        jPanel6.add(jLabel13);

        txtPassConfirmEdit.setText(resourceMap.getString("txtPassConfirmEdit.text")); // NOI18N
        txtPassConfirmEdit.setName("txtPassConfirmEdit"); // NOI18N
        jPanel6.add(txtPassConfirmEdit);

        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N
        jPanel6.add(jLabel19);

        cmbRoles.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "De carga", "Administrador" }));
        cmbRoles.setName("cmbRoles"); // NOI18N
        jPanel6.add(cmbRoles);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(zilleprojects.ZilleProjectsApp.class).getContext().getActionMap(UsuariosDialog.class, this);
        btnAceptarUsuarioEdit.setAction(actionMap.get("GuardarUsuario")); // NOI18N
        btnAceptarUsuarioEdit.setText(resourceMap.getString("btnAceptarUsuarioEdit.text")); // NOI18N
        btnAceptarUsuarioEdit.setName("btnAceptarUsuarioEdit"); // NOI18N

        btnCancelarUsuarioEdit.setText(resourceMap.getString("btnCancelarUsuarioEdit.text")); // NOI18N
        btnCancelarUsuarioEdit.setName("btnCancelarUsuarioEdit"); // NOI18N
        btnCancelarUsuarioEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarUsuarioEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnAceptarUsuarioEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelarUsuarioEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarUsuarioEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptarUsuarioEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAceptarUsuarioEdit, btnCancelarUsuarioEdit});

        AMUsuario.getContentPane().add(jPanel5);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setLocationByPlatform(true);
        setModalityType(java.awt.Dialog.ModalityType.TOOLKIT_MODAL);
        setName("Form"); // NOI18N
        getContentPane().setLayout(new java.awt.GridLayout(1, 1));

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(926, 573));

        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, jLabel1.getFont().getSize()+4));
        jLabel1.setLabelFor(this);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        bttnRecargarUsuarios.setAction(actionMap.get("cargarUsuarioes")); // NOI18N
        bttnRecargarUsuarios.setIcon(resourceMap.getIcon("bttnRecargarUsuarios.icon")); // NOI18N
        bttnRecargarUsuarios.setText(resourceMap.getString("bttnRecargarUsuarios.text")); // NOI18N
        bttnRecargarUsuarios.setName("bttnRecargarUsuarios"); // NOI18N
        jPanel3.add(bttnRecargarUsuarios);

        btnNuevoUsuario.setAction(actionMap.get("showNuevoUsuario")); // NOI18N
        btnNuevoUsuario.setIcon(resourceMap.getIcon("btnNuevoUsuario.icon")); // NOI18N
        btnNuevoUsuario.setText(resourceMap.getString("btnNuevoUsuario.text")); // NOI18N
        btnNuevoUsuario.setName("btnNuevoUsuario"); // NOI18N
        jPanel3.add(btnNuevoUsuario);

        btnEditarUsuario.setAction(actionMap.get("showModificarUsuario")); // NOI18N
        btnEditarUsuario.setIcon(resourceMap.getIcon("btnEditarUsuario.icon")); // NOI18N
        btnEditarUsuario.setText(resourceMap.getString("btnEditarUsuario.text")); // NOI18N
        btnEditarUsuario.setName("btnEditarUsuario"); // NOI18N
        jPanel3.add(btnEditarUsuario);

        btnEliminarUsuario.setAction(actionMap.get("EliminarUsuario")); // NOI18N
        btnEliminarUsuario.setIcon(resourceMap.getIcon("btnEliminarUsuario.icon")); // NOI18N
        btnEliminarUsuario.setText(resourceMap.getString("btnEliminarUsuario.text")); // NOI18N
        btnEliminarUsuario.setName("btnEliminarUsuario"); // NOI18N
        jPanel3.add(btnEliminarUsuario);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblUsuarios.setAutoCreateRowSorter(true);
        tblUsuarios.setFont(tblUsuarios.getFont().deriveFont(tblUsuarios.getFont().getSize()+2f));
        tblUsuarios.setGridColor(resourceMap.getColor("tblUsuarios.gridColor")); // NOI18N
        tblUsuarios.setName("tblUsuarios"); // NOI18N
        tblUsuarios.setRowHeight(24);
        tblUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        tblUsuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblUsuariosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);

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
        txtBuscarTexto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarTextoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarTextoFocusLost(evt);
            }
        });
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
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
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
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

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarTextoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarTextoFocusGained
        if(txtBuscarTexto.getText().equals("Buscar...")){
            txtBuscarTexto.setText("");
        }
    }//GEN-LAST:event_txtBuscarTextoFocusGained

    private void txtBuscarTextoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarTextoFocusLost
        if(txtBuscarTexto.getText().isEmpty()){
            txtBuscarTexto.setText("Buscar...");
        }
    }//GEN-LAST:event_txtBuscarTextoFocusLost

    private void txtBuscarTextoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarTextoKeyReleased
       if(txtBuscarTexto.getText().length()>3){
            filtrarUsuarios().execute();
        }if(txtBuscarTexto.getText().isEmpty()){
           cargarUsuarios().execute();
       }
    }//GEN-LAST:event_txtBuscarTextoKeyReleased

    private void btnCancelarUsuarioEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarUsuarioEditActionPerformed
        AMUsuario.setVisible(false);
    }//GEN-LAST:event_btnCancelarUsuarioEditActionPerformed

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuarioesMouseClicked
        usuarioSeleccionado = (Usuario) usuariosTableModel.getFila(tblUsuarios.getSelectedRow());
    }//GEN-LAST:event_tblUsuarioesMouseClicked

    private void tblUsuariosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblUsuarioesKeyReleased
        usuarioSeleccionado = (Usuario) usuariosTableModel.getFila(tblUsuarios.getSelectedRow());
    }//GEN-LAST:event_tblUsuarioesKeyReleased


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UsuariosDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UsuariosDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UsuariosDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsuariosDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                UsuariosDialog dialog = new UsuariosDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

   

    private void configurarTabla() {
        usuariosTableModel = new UsuariosTableModel();
        final UsuarioDAO udao = new UsuarioDAO();
        udao.conectar();
        ///Esto es para guardar los cambios cuando se edita desde la tabla
        usuariosTableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // Solo actualizo la db si hubieron cambios en alguna columna
                if ( e.getColumn() != -1){
                    int row = e.getFirstRow();
                    Usuario usuario = (Usuario) usuariosTableModel.getFila(row);
                    if(usuario != null){
                        if(udao.guardar(usuario)== 0)
                            
                        {
                            Error("Existen datos inválidos. Por favor, compruebe los datos.");
                           
                        }
                    }
                }
            }
        });
        

    }
     @Action
    public final Task cargarUsuarios() {
        limpiarFiltros();
        return new CargarUsuariosTask(zilleprojects.ZilleProjectsApp.getApplication());
    }
     
    private class CargarUsuariosTask extends org.jdesktop.application.Task<Object, Void> {
        List<Usuario> usuarios;
        CargarUsuariosTask(org.jdesktop.application.Application app) {
            super(app);
        }

        @Override
        protected Object doInBackground()  {
            final UsuarioDAO udao = new UsuarioDAO();
            udao.conectar();
            usuarios = udao.cargarUser();
            totalFiltrado = totalEntidad = udao.count("usuario");
            return null;
        }

        @Override
        protected void succeeded(Object result) {

            if (usuarios != null) {
                configurarTabla();
                for(Usuario c:usuarios){
                    usuariosTableModel.addFila(c);
                }
                tblUsuarios.setModel(usuariosTableModel);
                lblTotalEntidad.setText(String.valueOf(totalEntidad));
                lblTotalFiltrado.setText(String.valueOf(totalFiltrado));
            } else {
                OpcionPanel.showMessageDialog(null, "Falló la carga de usuarios. Intente nuevamente.",
                        "Fallo de Conexión", OpcionPanel.INFORMATION_MESSAGE);
            }
        }
    }

//    @Action
//    public final Task filtrarUsuarioesDetallado() {
//        return new FiltrarUsuarioesDetalladoTask(zilleprojects.ZilleProjectsApp.getApplication());
//    }
//
//    private class FiltrarUsuarioesDetalladoTask extends org.jdesktop.application.Task<Object, Void> {
//        List<Usuario> usuarioes;
//        String nombre, direccion, telefono, email;
//        Double sueldo = -1D;
//        Date fechaDesde, fechaHasta;
//        FiltrarUsuarioesDetalladoTask(org.jdesktop.application.Application app) {
//            super(app);
//            nombre = txtFiltroNombre.getText();
//            direccion = txtFiltroDireccion.getText();
//            telefono = txtFiltroTelefono.getText();
//            email = txtFiltroEmail.getText();
//            if(!txtFiltroSueldo.getText().isEmpty()){
//                try{
//                    sueldo = Double.parseDouble(txtFiltroSueldo.getText());
//                }catch(NumberFormatException e){
//                    sueldo = -1D;
//                }
//            }
//            fechaDesde = jDateFiltroIngresoDesde.getDate();
//            fechaHasta = jDateFiltroIngresoHasta.getDate();
//        }
//
//        @Override
//        protected Object doInBackground()  {
//            UsuarioDAO dao = new UsuarioDAO();
//            usuarioes = dao.filtrarDetalladoUsuarioesTabla(nombre, direccion, telefono, email, sueldo ,fechaDesde, fechaHasta);
//            totalFiltrado = usuarioes.size();
//            return null;
//        }
//
//        @Override
//        protected void succeeded(Object result) {
//
//            if (usuarioes != null) {
//                configurarTabla();
//                for(Usuario c:usuarioes){
//                    usuariosTableModel.addFila(c);
//                }
//                tblUsuarioes.setModel(usuariosTableModel);
//                lblTotalFiltrado.setText(String.valueOf(totalFiltrado));
//            } else {
//                OpcionPanel.showMessageDialog(null, "Falló la carga de usuarioes. Intente nuevamente.",
//                        "Fallo de Conexión", OpcionPanel.INFORMATION_MESSAGE);
//            }
//        }
//    }
    @Action
    public final Task filtrarUsuarios() {
        
        return new FiltrarUsuariosTask(zilleprojects.ZilleProjectsApp.getApplication());
    }

    private class FiltrarUsuariosTask extends org.jdesktop.application.Task<Object, Void> {
        ArrayList<Usuario> usuarios;
        String query;
        Date fechaDesde, fechaHasta;
        FiltrarUsuariosTask(org.jdesktop.application.Application app) {
            super(app);
            query = txtBuscarTexto.getText();
            if(query.equals("Buscar...")){
                query = "";
            }
            
        }

        @Override
        protected Object doInBackground()  {
            UsuarioDAO dao = new UsuarioDAO();
            dao.conectar();
            usuarios = dao.filtrarUsuariosTabla(query);
            totalFiltrado = usuarios.size();
            return null;
        }

        @Override
        protected void succeeded(Object result) {

            if (usuarios != null) {
                configurarTabla();
                for(Usuario c:usuarios){
                    usuariosTableModel.addFila(c);
                }

                tblUsuarios.setModel(usuariosTableModel);
                lblTotalFiltrado.setText(String.valueOf(totalFiltrado));
            } else {
                OpcionPanel.showMessageDialog(null, "Falló la carga de usuarios. Intente nuevamente.",
                        "Fallo de Conexión", OpcionPanel.INFORMATION_MESSAGE);
                cargarUsuarios().execute();
            }
        }
    }

    @Action
    public final Task GuardarUsuario() {
        if(!GuardarUsuarioAlEditar()){
            OpcionPanel.showError(usuarioSeleccionado.getErrores());
            return null;
        }
        return new GuardarUsuarioTask(zilleprojects.ZilleProjectsApp.getApplication());
    }

    private class GuardarUsuarioTask extends org.jdesktop.application.Task<Object, Void> {
        boolean guardadoOk;
        boolean esUpdate = false;
        GuardarUsuarioTask(org.jdesktop.application.Application app) {
            super(app);
            
            esUpdate = usuarioSeleccionado.getId() == 0 ? false : true;
        }

        @Override
        protected Object doInBackground()  {
            UsuarioDAO udao = new UsuarioDAO();
            udao.conectar();
            if(esUpdate)
                guardadoOk = udao.modificar(usuarioSeleccionado) != 0;
            else{
                int i = udao.guardar(usuarioSeleccionado);
                if(i != 0){
                    usuarioSeleccionado.setId(i);
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
                    usuariosTableModel.insertarFila(usuarioSeleccionado, tblUsuarios.getSelectedRow());
                    msg = "Se actualizó la información del usuario " + usuarioSeleccionado;
                }else{
                    usuariosTableModel.addFila(usuarioSeleccionado);
                    msg = "Se creó correctamente el usuario " +usuarioSeleccionado;
                }
                tblUsuarios.setModel(usuariosTableModel);
                totalFiltrado = usuariosTableModel.getRowCount();
                lblTotalFiltrado.setText(String.valueOf(totalFiltrado));
                AMUsuario.dispose();
                limpiarUsuarioEdit();
                Success("Éxito", msg);

                
            } else {
                OpcionPanel.showError(btnAceptarUsuarioEdit, "Falló la carga de usuarios. Intente nuevamente.");
            }
        }
    }

    @Action
    public final Task EliminarUsuario() {

        if(usuarioSeleccionado.getId()==0 || tblUsuarios.getSelectedRow() == -1){

            super.Info("Eliminar", "Por favor, primero seleccione un usuario.");
            return null;
        }
        if(!(OpcionPanel.YES_OPTION == JOptionPane.showConfirmDialog(UsuariosDialog.this,
                "¿Realmente desea eliminar el usuario "+ usuarioSeleccionado +"?", "¿Eliminar?",
                OpcionPanel.YES_NO_OPTION, OpcionPanel.QUESTION_MESSAGE))){
            
            return null;
        }
        return new EliminarUsuarioTask(zilleprojects.ZilleProjectsApp.getApplication());
    }

     class EliminarUsuarioTask extends org.jdesktop.application.Task<Object, Void> {
        boolean eliminadoOK;
        String nombre = usuarioSeleccionado.toString();
        EliminarUsuarioTask(org.jdesktop.application.Application app) {
            super(app);
        }

        @Override
        protected Object doInBackground()  {
            UsuarioDAO udao = new UsuarioDAO();
            udao.conectar();
            eliminadoOK = udao.darDeBaja(usuarioSeleccionado);
            return null;
        }

        @Override
        protected void succeeded(Object result) {
            if (eliminadoOK) {
                
                cargarUsuarios().execute();
                Success("Éxito", "Se eliminó correctamente el usuario " +nombre);
                txtBuscarTexto.setText("Buscar...");
            } else {
                OpcionPanel.showError(btnAceptarUsuarioEdit, "Falló la carga de usuarios. Intente nuevamente.");
            }
        }
    }

    @Action
    public void limpiarFiltros(){
        txtBuscarTexto.setText("Buscar...");
    }

    @Action
    public void showNuevoUsuario() {
       usuarioSeleccionado = new Usuario();
       AMUsuario.setTitle("Nuevo usuario");
       AMUsuario.setSize(412, 270);
       AMUsuario.setLocationRelativeTo(this);
       AMUsuario.getRootPane().setDefaultButton(btnAceptarUsuarioEdit);
       AMUsuario.setVisible(true);
    }
    @Action
    public void showModificarUsuario() {
        if(tblUsuarios.getSelectedRow() == -1 || usuarioSeleccionado.getId() == 0){
            Info("Información", "Seleccione una fila primero.");
            return;
        }
       CargarUsuarioAlEditar(usuarioSeleccionado);
       AMUsuario.setTitle("Editar usuario");
       AMUsuario.setSize(412, 270);
       AMUsuario.setLocationRelativeTo(this);
       AMUsuario.getRootPane().setDefaultButton(btnAceptarUsuarioEdit);
       AMUsuario.setVisible(true);
       
    }

    @Action
    public void limpiarUsuarioEdit(){
        try{
        usuarioSeleccionado = (Usuario) usuariosTableModel.getFila(tblUsuarios.getSelectedRow());
        }catch(Exception ex){
            usuarioSeleccionado = new Usuario();
        }
        txtNombreUsuarioEdit.setText(null);
        txtNombreUsuarioEdit.setText(null);
        txtPassEdit.setText(null);
        txtPassConfirmEdit.setText(null);
        cmbRoles.setSelectedIndex(0);
        
    }

    void CargarUsuarioAlEditar(Usuario usuario){
        txtNombreUsuarioEdit.setText(usuario.getUser());
        cmbRoles.setSelectedItem(usuario.getRol());

    }
    boolean GuardarUsuarioAlEditar(){
        usuarioSeleccionado.setUser(txtNombreUsuarioEdit.getText());
        usuarioSeleccionado.setRol((String)cmbRoles.getSelectedItem());
        if(txtPassEdit.getText().isEmpty() && txtPassConfirmEdit.getText().isEmpty()){
            // Nada, se conserva el pass
        }else if (txtPassEdit.getText().equals(txtPassConfirmEdit.getText())){
            usuarioSeleccionado.setPass(txtPassEdit.getText());
        }else{
            usuarioSeleccionado.setError("Las contraseñas no coinciden.");
            return false;
        }
        return usuarioSeleccionado.validate();
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog AMUsuario;
    private javax.swing.JButton btnAceptarUsuarioEdit;
    private javax.swing.JButton btnCancelarUsuarioEdit;
    private javax.swing.JButton btnEditarUsuario;
    private javax.swing.JButton btnEliminarUsuario;
    private javax.swing.JButton btnNuevoUsuario;
    private javax.swing.JButton bttnRecargarUsuarios;
    private javax.swing.JComboBox cmbRoles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotalEntidad;
    private javax.swing.JLabel lblTotalFiltrado;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtBuscarTexto;
    private javax.swing.JTextField txtNombreUsuarioEdit;
    private javax.swing.JTextField txtPassConfirmEdit;
    private javax.swing.JTextField txtPassEdit;
    // End of variables declaration//GEN-END:variables
    UsuariosTableModel usuariosTableModel = new UsuariosTableModel();
    Usuario usuarioSeleccionado = new Usuario();
    long totalEntidad = 0;
    long totalFiltrado = 0;
}
