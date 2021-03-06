/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FuncionDialog.java
 *
 * Created on 13/09/2014, 11:44:31
 */
package zilleprojects.form;

import DAO.AbstractDAO;
import DAO.FuncionDAO;
import Modelo.EntidadAbstracta;
import Modelo.Funcion;
import Modelo.tablemodel.FuncionTableModel;
import Modelo.tablemodel.ZilleAbstractTableModel;
import Vista.PanelAzul;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JDialog;

/**
 *
 * @author m4tuu
 */
public class FuncionDialog extends ZilleDialog {
   
    /** Creates new form FuncionDialog */
    public FuncionDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        initComponents();
        
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
        jPanel1 = new PanelAzul();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(zilleprojects.ZilleProjectsApp.class).getContext().getResourceMap(FuncionDialog.class);
        jDialog1.setTitle(resourceMap.getString("jDialog1.title")); // NOI18N
        jDialog1.setAlwaysOnTop(true);
        jDialog1.setModal(true);
        jDialog1.setName("jDialog1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(3, 2, 10, 10));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel1.add(jLabel2);

        txtId.setText(resourceMap.getString("txtId.text")); // NOI18N
        txtId.setName("txtId"); // NOI18N
        jPanel1.add(txtId);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1);

        txtNombre.setText(resourceMap.getString("txtNombre.text")); // NOI18N
        txtNombre.setName("txtNombre"); // NOI18N
        jPanel1.add(txtNombre);

        btnCancelar.setText(resourceMap.getString("btnCancelar.text")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(zilleprojects.ZilleProjectsApp.class).getContext().getActionMap(FuncionDialog.class, this);
        btnAceptar.setAction(actionMap.get("GuardarEntidad")); // NOI18N
        btnAceptar.setText(resourceMap.getString("btnAceptar.text")); // NOI18N
        btnAceptar.setName("btnAceptar"); // NOI18N
        jPanel1.add(btnAceptar);

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
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
        getAMEntidad().dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txtId;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    @Override
    protected String getTablaName() {
        return "funcion";
    }

    @Override
    protected String getNombreEntidad() {
        return "Función";
    }

    @Override
    protected void limpiarEntidad() {
        func = new Funcion();
    }

    @Override
    protected AbstractDAO getDao() {
        return new FuncionDAO();
    }

    @Override
    protected EntidadAbstracta getEntidadAbstracta() {
        return func;
    }

    @Override
    protected void setEntidadAbstracta(EntidadAbstracta ea) {
        func = (Funcion) ea;
    }

    @Override
    protected ZilleAbstractTableModel getTableModel() {      
        return model;
    }

    @Override
    protected ZilleAbstractTableModel setNewTableModel() {
        model = new FuncionTableModel();
        return model;
    }

    @Override
    protected void setTableModel(ZilleAbstractTableModel ea) {
        model = (FuncionTableModel) ea;
    }

    @Override
    protected void setTitulo() {
        this.setTitle("Administración de funciones");
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
        func = (Funcion) entidad;
        txtNombre.setText(func.getFuncion());
        txtId.setText(func.getId()+"");
    }

    @Override
    protected boolean GuardarEntidadAlEditar() {
        func.setFuncion(txtNombre.getText());
        return func.validate();
    }

    @Override
    protected Dimension getSizeDialogEdit() {
        return new Dimension(350, 180);
    }
    

    @Override
    protected String[] getConfigColumn() {
        String[] conf = new String[] {"0:100"};
        return conf;
    }
    
    Funcion func = new Funcion();
    FuncionTableModel model = new FuncionTableModel();
    
}
