/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JDPrecioHistorico.java
 *
 * Created on 10/01/2015, 11:19:14
 */
package zilleprojects.form;

import DAO.IAbstractDAO;
import DAO.PrecioHistoricoDAO;
import Modelo.EntidadAbstracta;
import Modelo.FamiliaEquipo;
import Modelo.PrecioHistorico;
import Modelo.TipoCosto;
import Modelo.tablemodel.PrecioHistoricoTableModel;
import Modelo.tablemodel.ZilleAbstractTableModel;
import Vista.OpcionPanel;
import Vista.PanelEsquinaAzul;
import java.awt.Dimension;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JDialog;
import org.jdesktop.application.Action;
import org.jdesktop.application.Task;

/**
 *
 * @author m4tuu
 */
public class JDPrecioHistorico extends ZilleDialog {

    FamiliaEquipo familia;
    /** Creates new form JDPrecioHistorico */
    public JDPrecioHistorico(java.awt.Frame parent, boolean modal, FamiliaEquipo familiaSelect) {
        
        super(parent, modal);
        initComponents();
        this.familia = familiaSelect;
        setTitulo();
        SetButtonExtra(btnNuevoHistorico);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanel1 = new PanelEsquinaAzul();
        txtTipo = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        btnNuevoHistorico = new javax.swing.JButton();
        jDNuevoHistorico = new javax.swing.JDialog();
        jPanel2 = new PanelEsquinaAzul();
        btnNewHistorico = new javax.swing.JButton();
        btnCancelarHistorico = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNewUtilizacion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNewPosesion = new javax.swing.JTextField();
        txtNombreFamilia = new javax.swing.JLabel();
        dateAlta = new com.toedter.calendar.JDateChooser();

        jDialog1.setMinimumSize(new java.awt.Dimension(360, 144));
        jDialog1.setModal(true);
        jDialog1.setName("jDialog1"); // NOI18N
        jDialog1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jDialog1ComponentShown(evt);
            }
        });

        jPanel1.setMaximumSize(new java.awt.Dimension(1000, 1000));
        jPanel1.setMinimumSize(new java.awt.Dimension(360, 144));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(360, 144));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(zilleprojects.ZilleProjectsApp.class).getContext().getResourceMap(JDPrecioHistorico.class);
        txtTipo.setText(resourceMap.getString("txtTipo.text")); // NOI18N
        txtTipo.setName("txtTipo"); // NOI18N

        txtValor.setText(resourceMap.getString("txtValor.text")); // NOI18N
        txtValor.setName("txtValor"); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);

        btnCancelar.setIcon(resourceMap.getIcon("btnCancelar.icon")); // NOI18N
        btnCancelar.setText(resourceMap.getString("btnCancelar.text")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(zilleprojects.ZilleProjectsApp.class).getContext().getActionMap(JDPrecioHistorico.class, this);
        btnAceptar.setAction(actionMap.get("GuardarEntidad")); // NOI18N
        btnAceptar.setIcon(resourceMap.getIcon("btnAceptar.icon")); // NOI18N
        btnAceptar.setText(resourceMap.getString("btnAceptar.text")); // NOI18N
        btnAceptar.setName("btnAceptar"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAceptar))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTipo)
                        .addGap(18, 18, 18)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(217, 217, 217))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, Short.MAX_VALUE)
        );

        btnNuevoHistorico.setAction(actionMap.get("SowNuevoHistorico")); // NOI18N
        btnNuevoHistorico.setIcon(resourceMap.getIcon("btnNuevoHistorico.icon")); // NOI18N
        btnNuevoHistorico.setText(resourceMap.getString("btnNuevoHistorico.text")); // NOI18N
        btnNuevoHistorico.setName("btnNuevoHistorico"); // NOI18N

        jDNuevoHistorico.setMinimumSize(new java.awt.Dimension(400, 290));
        jDNuevoHistorico.setModal(true);
        jDNuevoHistorico.setName("jDNuevoHistorico"); // NOI18N
        jDNuevoHistorico.setResizable(false);

        jPanel2.setMinimumSize(new java.awt.Dimension(400, 290));
        jPanel2.setName("jPanel2"); // NOI18N

        btnNewHistorico.setAction(actionMap.get("NuevoHistorico")); // NOI18N
        btnNewHistorico.setIcon(resourceMap.getIcon("btnNewHistorico.icon")); // NOI18N
        btnNewHistorico.setText(resourceMap.getString("btnNewHistorico.text")); // NOI18N
        btnNewHistorico.setName("btnNewHistorico"); // NOI18N

        btnCancelarHistorico.setIcon(resourceMap.getIcon("btnCancelarHistorico.icon")); // NOI18N
        btnCancelarHistorico.setText(resourceMap.getString("btnCancelarHistorico.text")); // NOI18N
        btnCancelarHistorico.setName("btnCancelarHistorico"); // NOI18N
        btnCancelarHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarHistoricoActionPerformed(evt);
            }
        });

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        txtNewUtilizacion.setText(resourceMap.getString("txtNewUtilizacion.text")); // NOI18N
        txtNewUtilizacion.setName("txtNewUtilizacion"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        txtNewPosesion.setText(resourceMap.getString("txtNewPosesion.text")); // NOI18N
        txtNewPosesion.setName("txtNewPosesion"); // NOI18N

        txtNombreFamilia.setFont(txtNombreFamilia.getFont().deriveFont(txtNombreFamilia.getFont().getStyle() | java.awt.Font.BOLD, txtNombreFamilia.getFont().getSize()+3));
        txtNombreFamilia.setText(resourceMap.getString("txtNombreFamilia.text")); // NOI18N
        txtNombreFamilia.setName("txtNombreFamilia"); // NOI18N

        dateAlta.setName("dateAlta"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNewPosesion, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(txtNewUtilizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(dateAlta, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(txtNombreFamilia, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addGap(12, 12, 12))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(135, Short.MAX_VALUE)
                .addComponent(btnNewHistorico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelarHistorico)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNombreFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNewPosesion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNewUtilizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(dateAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNewHistorico)
                    .addComponent(btnCancelarHistorico))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dateAlta, txtNewUtilizacion});

        javax.swing.GroupLayout jDNuevoHistoricoLayout = new javax.swing.GroupLayout(jDNuevoHistorico.getContentPane());
        jDNuevoHistorico.getContentPane().setLayout(jDNuevoHistoricoLayout);
        jDNuevoHistoricoLayout.setHorizontalGroup(
            jDNuevoHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDNuevoHistoricoLayout.setVerticalGroup(
            jDNuevoHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        jDialog1.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCancelarHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarHistoricoActionPerformed
        jDNuevoHistorico.dispose();
    }//GEN-LAST:event_btnCancelarHistoricoActionPerformed

    private void jDialog1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDialog1ComponentShown
        if (precioHistoricoSel.getFechaBaja()!=null) {
            if (OpcionPanel.showConfirmDialog(this, "El este precio histórico está dado de baja. "
                    + "Si modifica su valor, alterará los informes realizados anteriormente. ¿Continuar?", 
                    "Advertencia", 
                    OpcionPanel.YES_NO_OPTION, 
                    OpcionPanel.QUESTION_MESSAGE) == OpcionPanel.NO_OPTION) {
                jDialog1.dispose();
            }
        }
    }//GEN-LAST:event_jDialog1ComponentShown

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarHistorico;
    private javax.swing.JButton btnNewHistorico;
    private javax.swing.JButton btnNuevoHistorico;
    private com.toedter.calendar.JDateChooser dateAlta;
    private javax.swing.JDialog jDNuevoHistorico;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtNewPosesion;
    private javax.swing.JTextField txtNewUtilizacion;
    private javax.swing.JLabel txtNombreFamilia;
    private javax.swing.JLabel txtTipo;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables

    @Override
    protected String getTablaName() {
        return "precio_historico";
    }

    @Override
    protected String getNombreEntidad() {
        return "Precio Histórico";
    }

    
    @Override
    protected void limpiarEntidad() {
        precioHistoricoSel = new PrecioHistorico();
    }

    @Override
    protected IAbstractDAO getDao() {
        return new PrecioHistoricoDAO(familia);
    }

    @Override
    protected EntidadAbstracta getEntidadAbstracta() {
        return precioHistoricoSel;
    }

    @Override
    protected void setEntidadAbstracta(EntidadAbstracta ea) {
        precioHistoricoSel = (PrecioHistorico) ea;
    }

    @Override
    protected ZilleAbstractTableModel getTableModel() {
        return tableModel;
    }

    @Override
    protected ZilleAbstractTableModel setNewTableModel() {
        tableModel = new PrecioHistoricoTableModel();
        return tableModel;
    }

    @Override
    protected void setTableModel(ZilleAbstractTableModel ea) {
        tableModel = (PrecioHistoricoTableModel) ea;
    }

    @Override
    protected final void setTitulo() {
        if (familia== null) setTitle("Precios históricos");
        else setTitle("Precios de " + familia.getNombre());
    }

    @Override
    protected JDialog getAMEntidad() {
        return jDialog1;
    }

    @Override
    protected JButton getBtnAceptarEntidadEdit() {
        return btnAceptar;
    }

    @Override
    protected JButton getBtnCancelarEntidadEdit() {
        return btnCancelar;
    }

    @Override
    protected void CargarEntidadAlEditar(EntidadAbstracta entidad) {
        precioHistoricoSel = (PrecioHistorico) entidad;
        if (precioHistoricoSel.getTipo().getTipo() == TipoCosto.POSESION) {
            txtTipo.setText(TipoCosto.POSESION_TXT);    
        } else {
            txtTipo.setText(TipoCosto.UTILIZACION_TXT);           
        }
        txtValor.setText(precioHistoricoSel.getValor()+"");
    }
    
    @Override
    public boolean isNuevoEnabled() {
        return false;
    }

    @Override
    protected boolean GuardarEntidadAlEditar() {
        precioHistoricoSel.setValor(Double.parseDouble(txtValor.getText()));      
        return precioHistoricoSel.validate();
    }

    @Override
    protected Dimension getSizeDialogEdit() {
        return new Dimension(270, 150);
    }

    @Action 
    public void SowNuevoHistorico() {
        txtNombreFamilia.setText(familia.getNombre());
        dateAlta.setDate(new Date());
        jDNuevoHistorico.setLocationRelativeTo(null);
        jDNuevoHistorico.setVisible(true);
    }
    @Action
    public Task NuevoHistorico() {
        if (txtNewPosesion.getText().isEmpty()) {
            Error("Debe ingresar el costo de posesión");
            return null;
        }
        if (txtNewUtilizacion.getText().isEmpty()) {
            Error("Debe ingresar el costo de utilización");
            return null;
        }
        return new NuevoHistoricoTask(org.jdesktop.application.Application.getInstance(zilleprojects.ZilleProjectsApp.class));
    }

    private class NuevoHistoricoTask extends org.jdesktop.application.Task<Object, Void> {
        private PrecioHistorico hist1, hist2;
        NuevoHistoricoTask(org.jdesktop.application.Application app) {
            // Runs on the EDT.  Copy GUI state that
            // doInBackground() depends on from parameters
            // to NuevoHistoricoTask fields, here.
            super(app);
            hist1 = new PrecioHistorico(familia, TipoCosto.getTipoPosesion(), 
                    dateAlta.getDate(), Double.parseDouble(txtNewPosesion.getText()));
            hist2 = new PrecioHistorico(familia, TipoCosto.getTipoUtilizacion(), 
                    dateAlta.getDate(), Double.parseDouble(txtNewUtilizacion.getText()));
            
        }
        @Override protected Object doInBackground() {
            // Your Task's code here.  This method runs
            // on a background thread, so don't reference
            // the Swing GUI from here.
            PrecioHistoricoDAO dao = new PrecioHistoricoDAO(familia);
            return dao.insertNuevoPrecioHistorico(hist1, hist2);
            
        }
        @Override protected void succeeded(Object result) {
            // Runs on the EDT.  Update the GUI based on
            // the result computed by doInBackground().
            if ((Boolean) result) {
                Success("Precios guardados correctamente.");
                jDNuevoHistorico.dispose();
                cargarEntidades().execute();
            }
            else {
                Error("Ocurrió un error al guardar. Intente nuevamente");
            }
            
        }
        
        
        
    }

    
    
    private PrecioHistorico precioHistoricoSel = new PrecioHistorico();
    private PrecioHistoricoTableModel tableModel = new PrecioHistoricoTableModel();
}