/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JDParametrosSistema.java
 *
 * Created on 23/08/2014, 11:16:15
 */
package zilleprojects.form;

import DAO.ParametroDAO;
import Modelo.Parametro;
import Modelo.ParametrosSistema;
import Modelo.tablemodel.TablaParametrosModel;
import Vista.JDialogCustom;
import Vista.OpcionPanel;
import Vista.PanelAzul;
import java.awt.Color;
import java.awt.Dimension;
import org.jdesktop.application.Action;
import org.jdesktop.application.Task;

/**
 *
 * @author m4tuu
 */
public class JDParametrosSistema extends JDialogCustom {
    TablaParametrosModel tableModel = new TablaParametrosModel();
    /** Creates new form JDParametrosSistema */
    public JDParametrosSistema(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        CargarParametros();
        initComponents();
        jTextPane1.setForeground(Color.red);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdNuevoParametro = new javax.swing.JDialog();
        jPanel3 = new PanelAzul();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtClave = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtGrupoClave = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        btnCerrarNuevo = new javax.swing.JButton();
        btnAddNuevo = new javax.swing.JButton();
        jPanel1 = new PanelAzul();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        jdNuevoParametro.setLocationByPlatform(true);
        jdNuevoParametro.setMinimumSize(new java.awt.Dimension(100, 40));
        jdNuevoParametro.setModal(true);
        jdNuevoParametro.setName("jdNuevoParametro"); // NOI18N
        jdNuevoParametro.setResizable(false);
        jdNuevoParametro.setUndecorated(true);

        jPanel3.setName("jPanel3"); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridLayout(3, 2, 10, 10));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(zilleprojects.ZilleProjectsApp.class).getContext().getResourceMap(JDParametrosSistema.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel2.add(jLabel1);

        txtClave.setText(resourceMap.getString("txtClave.text")); // NOI18N
        txtClave.setName("txtClave"); // NOI18N
        txtClave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClaveKeyReleased(evt);
            }
        });
        jPanel2.add(txtClave);

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel2.add(jLabel2);

        txtGrupoClave.setText(resourceMap.getString("txtGrupoClave.text")); // NOI18N
        txtGrupoClave.setName("txtGrupoClave"); // NOI18N
        txtGrupoClave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGrupoClaveKeyReleased(evt);
            }
        });
        jPanel2.add(txtGrupoClave);

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel2.add(jLabel3);

        txtValor.setText(resourceMap.getString("txtValor.text")); // NOI18N
        txtValor.setName("txtValor"); // NOI18N
        jPanel2.add(txtValor);

        btnCerrarNuevo.setIcon(resourceMap.getIcon("btnCerrarNuevo.icon")); // NOI18N
        btnCerrarNuevo.setText(resourceMap.getString("btnCerrarNuevo.text")); // NOI18N
        btnCerrarNuevo.setName("btnCerrarNuevo"); // NOI18N
        btnCerrarNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarNuevoActionPerformed(evt);
            }
        });

        btnAddNuevo.setIcon(resourceMap.getIcon("btnAddNuevo.icon")); // NOI18N
        btnAddNuevo.setText(resourceMap.getString("btnAddNuevo.text")); // NOI18N
        btnAddNuevo.setName("btnAddNuevo"); // NOI18N
        btnAddNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(153, Short.MAX_VALUE)
                .addComponent(btnAddNuevo)
                .addGap(20, 20, 20)
                .addComponent(btnCerrarNuevo)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(165, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddNuevo)
                    .addComponent(btnCerrarNuevo))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(66, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jdNuevoParametroLayout = new javax.swing.GroupLayout(jdNuevoParametro.getContentPane());
        jdNuevoParametro.getContentPane().setLayout(jdNuevoParametroLayout);
        jdNuevoParametroLayout.setHorizontalGroup(
            jdNuevoParametroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jdNuevoParametroLayout.setVerticalGroup(
            jdNuevoParametroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(400, 500));
        setModal(true);
        setName("Form"); // NOI18N
        setResizable(false);

        jPanel1.setMinimumSize(new java.awt.Dimension(400, 500));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 500));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(tableModel);
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTextPane1.setBackground(resourceMap.getColor("jTextPane1.background")); // NOI18N
        jTextPane1.setBorder(null);
        jTextPane1.setEditable(false);
        jTextPane1.setFont(jTextPane1.getFont().deriveFont(jTextPane1.getFont().getStyle() | java.awt.Font.BOLD, jTextPane1.getFont().getSize()+2));
        jTextPane1.setForeground(resourceMap.getColor("jTextPane1.foreground")); // NOI18N
        jTextPane1.setText(resourceMap.getString("jTextPane1.text")); // NOI18N
        jTextPane1.setDoubleBuffered(true);
        jTextPane1.setName("jTextPane1"); // NOI18N
        jScrollPane2.setViewportView(jTextPane1);

        btnCancelar.setIcon(resourceMap.getIcon("btnCancelar.icon")); // NOI18N
        btnCancelar.setText(resourceMap.getString("btnCancelar.text")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(zilleprojects.ZilleProjectsApp.class).getContext().getActionMap(JDParametrosSistema.class, this);
        btnGuardar.setAction(actionMap.get("guardarParametros")); // NOI18N
        btnGuardar.setIcon(resourceMap.getIcon("btnGuardar.icon")); // NOI18N
        btnGuardar.setText(resourceMap.getString("btnGuardar.text")); // NOI18N
        btnGuardar.setName("btnGuardar"); // NOI18N

        jButton1.setAction(actionMap.get("recargarParametros")); // NOI18N
        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N

        btnNuevo.setIcon(resourceMap.getIcon("btnNuevo.icon")); // NOI18N
        btnNuevo.setText(resourceMap.getString("btnNuevo.text")); // NOI18N
        btnNuevo.setName("btnNuevo"); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEliminar.setAction(actionMap.get("quitarParametro")); // NOI18N
        btnEliminar.setIcon(resourceMap.getIcon("btnEliminar.icon")); // NOI18N
        btnEliminar.setText(resourceMap.getString("btnEliminar.text")); // NOI18N
        btnEliminar.setName("btnEliminar"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCancelar)
                            .addComponent(btnGuardar)
                            .addComponent(jButton1)
                            .addComponent(btnNuevo)
                            .addComponent(btnEliminar)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancelar, btnEliminar, btnGuardar, btnNuevo, jButton1});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(45, 45, 45)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar)
                        .addGap(14, 14, 14)
                        .addComponent(btnCancelar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancelar, btnEliminar, btnGuardar, btnNuevo, jButton1});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCerrarNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarNuevoActionPerformed
        jdNuevoParametro.dispose();
    }//GEN-LAST:event_btnCerrarNuevoActionPerformed

    private void btnAddNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNuevoActionPerformed
        
        if (txtClave.getText().isEmpty() || txtValor.getText().isEmpty()) {
            Error("Debe ingresar al menos la clave y su valor.");
            txtClave.requestFocus();
        } else {
            Parametro p = new Parametro();
            p.setClave(txtClave.getText());
            p.setClaveGrupo(txtGrupoClave.getText());
            p.setValor(txtValor.getText());
            tableModel.addFila(p);
            jdNuevoParametro.dispose();
        }
    }//GEN-LAST:event_btnAddNuevoActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        jdNuevoParametro.setMinimumSize(new Dimension(300, 200));
        jdNuevoParametro.getRootPane().setDefaultButton(btnAddNuevo);
        jdNuevoParametro.setIconImage(icono);
        jdNuevoParametro.setLocationRelativeTo(null);
        jdNuevoParametro.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtClaveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClaveKeyReleased
        String text = txtClave.getText().toUpperCase();
        if (text.endsWith(" ")) text =  text.trim() + "_";
        txtClave.setText(text);
        
    }//GEN-LAST:event_txtClaveKeyReleased

    private void txtGrupoClaveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGrupoClaveKeyReleased
        String text = txtGrupoClave.getText().toUpperCase();
        if (text.endsWith(" ")) text = text.trim() + "_";
        txtGrupoClave.setText(text);
    }//GEN-LAST:event_txtGrupoClaveKeyReleased

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
            java.util.logging.Logger.getLogger(JDParametrosSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDParametrosSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDParametrosSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDParametrosSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                JDParametrosSistema dialog = new JDParametrosSistema(new javax.swing.JFrame(), true);
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

    @Action
    public Task guardarParametros() {
        if ( OpcionPanel.YES_OPTION == OpcionPanel.showConfirmDialog(this, 
                "¿Está seguro que desea guardar los cambios? \nReviselos cuidadosamente,"
                + " ya que tienen alcance global a todo el sistema.", 
                "¿GuardarParámetros?", OpcionPanel.YES_NO_OPTION, OpcionPanel.QUESTION_MESSAGE))
            return new GuardarParametrosTask(org.jdesktop.application.Application.getInstance(zilleprojects.ZilleProjectsApp.class));
        return null;
    }

    private void CargarParametros() {
        ParametrosSistema.CargarParametros();
        tableModel.clean();
        for (Parametro p : ParametrosSistema.parametros) {
            tableModel.addFila(p);
        }
    }

    private class GuardarParametrosTask extends org.jdesktop.application.Task<Object, Void> {
        GuardarParametrosTask(org.jdesktop.application.Application app) {
            // Runs on the EDT.  Copy GUI state that
            // doInBackground() depends on from parameters
            // to GuardarParametrosTask fields, here.
            super(app);
        }
        @Override protected Object doInBackground() {
            // Your Task's code here.  This method runs
            // on a background thread, so don't reference
            // the Swing GUI from here.
            ParametroDAO dao = new ParametroDAO();
            dao.conectar();
            for(int i = 0; i < tableModel.getRowCount(); i++) {
                dao.guardar((Parametro)tableModel.getFila(i));
            }
            return true;  // return your result
        }
        @Override protected void succeeded(Object result) {
            // Runs on the EDT.  Update the GUI based on
            // the result computed by doInBackground().
            if((Boolean) result) {
                Success("Parámetros guardados correctamente");
            } else {
                Error("No se guardaron los cambios.");
            }
        }
    }

    @Action
    public Task recargarParametros() {
        return new RecargarParametrosTask(org.jdesktop.application.Application.getInstance(zilleprojects.ZilleProjectsApp.class));
    }

    private class RecargarParametrosTask extends org.jdesktop.application.Task<Object, Void> {
        RecargarParametrosTask(org.jdesktop.application.Application app) {
            // Runs on the EDT.  Copy GUI state that
            // doInBackground() depends on from parameters
            // to RecargarParametrosTask fields, here.
            super(app);
        }
        @Override protected Object doInBackground() {
            // Your Task's code here.  This method runs
            // on a background thread, so don't reference
            // the Swing GUI from here.
            CargarParametros();
            return null;  // return your result
        }
        @Override protected void succeeded(Object result) {
            // Runs on the EDT.  Update the GUI based on
            // the result computed by doInBackground().
            Info("Parámetros recargados!");
        }
    }

    @Action
    public Task quitarParametro() {
        if (jTable1.getSelectedRow() == -1) {
            Error("No ha seleccionado una fila");
            return null;
        } 
        if (OpcionPanel.YES_OPTION == 
                OpcionPanel.showConfirmDialog(this, "¿Desea remover este parámetro?\n" + getSelectedParametro() 
                + "\nNo podrá ser recuperado.", 
                "Confirmar", OpcionPanel.YES_NO_OPTION)){          
            return new QuitarParametroTask(org.jdesktop.application.Application.getInstance(zilleprojects.ZilleProjectsApp.class));
        }
        return null;
    }

    private class QuitarParametroTask extends org.jdesktop.application.Task<Object, Void> {
        QuitarParametroTask(org.jdesktop.application.Application app) {
            // Runs on the EDT.  Copy GUI state that
            // doInBackground() depends on from parameters
            // to QuitarParametroTask fields, here.
            super(app);
        }
        @Override protected Object doInBackground() {
            // Your Task's code here.  This method runs
            // on a background thread, so don't reference
            // the Swing GUI from here.
            ParametroDAO dao = new ParametroDAO();
            dao.conectar();
            return  dao.Borrar((Parametro)tableModel.getFila(jTable1.getSelectedRow()));
        }
        @Override protected void succeeded(Object result) {
            // Runs on the EDT.  Update the GUI based on
            // the result computed by doInBackground().
            if ((Boolean) result) {
                Success("Parámetro borrado");
                tableModel.delFila(tableModel.getFila(jTable1.getSelectedRow()));
            } else {
                Error("Parámetro no borrado. Intentelo nuevamente");
            }
        }
    }

    private String getSelectedParametro() {
        if (jTable1.getSelectedRow() == -1) {
            return "";
        } else {
            Parametro p = (Parametro) tableModel.getFila(jTable1.getSelectedRow());
            return p.getClave() + (p.getClaveGrupo().isEmpty() ? " ": " (" + p.getClaveGrupo() + ") ") +
                    " -> " + p.getValor();
                    
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNuevo;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrarNuevo;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JDialog jdNuevoParametro;
    private javax.swing.JTextField txtClave;
    private javax.swing.JTextField txtGrupoClave;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
