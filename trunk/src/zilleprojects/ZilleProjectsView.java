/*
 * ZilleProjectsView.java
 */
package zilleprojects;

import DAO.AlarmasDAO;
import Vista.DialogImgZille;
import DAO.Conexion.Conexion;
import DAO.Conexion.LeerXML;

import DAO.Conexion.Update;
import DAO.EquiposDAO;
import DAO.EstacionServicioDAO;
import DAO.FuncionDAO;
import DAO.ObrasDAO;
import DAO.OperarioDAO;
import DAO.ParteDiarioDAO;
import DAO.UsuarioDAO;
import Modelo.Alarma;
import Modelo.Equipos;
import Modelo.EstacionServicio;
import Modelo.Funcion;
import Modelo.ItemAlarma;
import Modelo.Obras;
import Modelo.Operario;
import Modelo.ParametrosSistema;
import Modelo.ParteDiario;
import Modelo.Perfiles;
import Modelo.TablaAlarmasModel;
import Modelo.UsuarioLogged;
import Utils.FechaUtil;
import Utils.ImageIconTable;
import Utils.Permisos;
import Vista.DialogPanel;
import Vista.OpcionPanel;
import Vista.PanelAlarma;
import Vista.PanelAzul;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import org.jdesktop.application.Task;


import zilleprojects.form.JDAlarmaActividad;
import zilleprojects.form.JDAlarmas;
import zilleprojects.form.JDEmpleadoGestion;
import zilleprojects.form.JDEquipos;
import zilleprojects.form.JDEstacionServicio;
import zilleprojects.form.JDObrasGestion;
import zilleprojects.form.JDOrdenTrabajo;
import zilleprojects.form.JDParteDiario;
import zilleprojects.form.JDRI;
import zilleprojects.form.JDRemoverParte;
import zilleprojects.form.JDReportes;
import zilleprojects.form.JDReportesAnteriores;
import zilleprojects.form.JDSemaforos;
import zilleprojects.form.UsuariosDialog;

/**
 * The application's main frame.
 */
public class ZilleProjectsView extends FrameView {

    Image icono = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/zilleprojects/resources/icono.png"));
    public static boolean isLastVersion = true;

    public ZilleProjectsView(SingleFrameApplication app) {
        super(app);

        initComponents();
        getFrame().setIconImage(icono);
        panelAlarmas.setVisible(false);
        VerificarNuevaVersion().execute();
        
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            @Override
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    
    
        this.getRootPane().setDefaultButton(BotonGrandeLogin);
    }

    
    class OpenUrlAction implements ActionListener {
      @Override public void actionPerformed(ActionEvent e) {
          try{
              final URI uri = new URI("http://matiasvarela.com.ar/shared/zille/ZilleProjects.jar");
              open(uri);
          }catch (Exception ex) {}
      }
    }
    private static void open(URI uri) {
    if (Desktop.isDesktopSupported()) {
      try {
        Desktop.getDesktop().browse(uri);
      } catch (IOException e) { OpcionPanel.showMessageDialog(null, "Por favor, descargue la última versión desde el siguiente link:\nhttp://matiasvarela.com.ar/shared/zille/ZilleProjects.jar"); }
    } else { 
        OpcionPanel.showMessageDialog(null, "Por favor, descargue la última versión desde el siguiente link:\nhttp://matiasvarela.com.ar/shared/zille/ZilleProjects.jar");
    }
  }
    
    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = ZilleProjectsApp.getApplication().getMainFrame();
            aboutBox = new ZilleProjectsAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        ZilleProjectsApp.getApplication().show(aboutBox);
    }
    
    private void resetearParteDiarioMasivo() {
        empleadoText.setText("Seleccione un empleado");
        fechaChoose.setDate(null);
        isHasta.setSelected(false);
        hastaDateChoose.setDate(null);
        observacionesTexto.setText(null);
        txtNumeroParte.setText(null);
        txtNumeroParte2.setText(null);
        comboObrasParteMasivo.setSelectedIndex(0);
        empleadoMasivo= new Operario();
        
    }
    public void backUp() {
        /***
         * @comment: Realiza un backup de la base de datos
         *
         */
    
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            
            @Override
            public void run() {
                String soName = System.getProperty("os.name").toUpperCase();
                System.out.println(soName);
                try {
                    if (soName.equals("LINUX")) { //Si se ejecuta en Linux   
                        File folder = new File("Backups/");
                        if(!folder.exists()) folder.mkdirs();
                        String archivo = folder.getAbsoluteFile()+"/"+ FechaUtil.getDay() + ".sql";
                        File f = new File(archivo);      
                        FileWriter wr= new FileWriter(f);
                        String command = "mysqldump --host=" + conn.getHost() + " --password=" + 
                                conn.getDbpass() + " --user=" + conn.getDbuser() + " " + conn.getDbname() ;
                        Process hijo = Runtime.getRuntime().exec(command);
                        InputStream stdout = hijo.getInputStream();
                        BufferedReader br = new BufferedReader (new InputStreamReader (stdout));              
                        String aux = br.readLine(); 
                        while (aux!=null) 
                        { 
                            wr.write(aux);
                            aux = br.readLine(); 
                        } 
                        wr.close();

                        System.out.println("Se realizó correctamente el backup de la base de datos.");
                        resultBKP = "Se realizó correctamente el backup de la base de datos.";


                    } else if (soName.equals("WINDOWS")|| soName.equals("WINDOWS 7")) {  //si se ejecuta en Windows
                        //String path = "C:\\ZilleProjects\\backups\\";
                        File folder = new File("Backups\\");
                        if(!folder.exists()) folder.mkdirs();
                        String archivo = folder.getAbsoluteFile()+"\\"+ FechaUtil.getDay() + ".sql";

                        File f = new File(archivo);
                        FileWriter wr= new FileWriter(f);
                        String command = "mysqldump --opt -h " + conn.getHost() + " --password=" + 
                                conn.getDbpass() + " --user=" + conn.getDbuser() + " " + conn.getDbname() ;
                        Process hijo = Runtime.getRuntime().exec(command);
                        InputStream stdout = hijo.getInputStream();
                        BufferedReader br = new BufferedReader (new InputStreamReader (stdout));              
                        String aux = br.readLine(); 
                        while (aux!=null) 
                        { 
                            wr.write(aux);
                            aux = br.readLine(); 
                        } 
                        wr.close();
                        System.out.println("Se realizó correctamente el backup de la base de datos.");
                        resultBKP = "Se realizó correctamente el backup de la base de datos.";

                    }
                } catch (IOException ex) {
                    System.err.println("Ocurrio un error al realizar el backup de la base de datos.");
                    resultBKP = "Ocurrio un error al realizar el backup de la base de datos.";
                    System.err.println(ex);

                }
            }
        });
   
    }

    /*******************************************************************
     * Configuración de la base de datos, lectura de la configuracion,
     * inicio de sesion.
     * ******************************************************************
     * 
     * @return 
     */
    @Action
    public Task iniciarSesion() {
        return new IniciarSesionTask(getApplication());
    }

    private class IniciarSesionTask extends org.jdesktop.application.Task<Object, Void> {

        IniciarSesionTask(org.jdesktop.application.Application app) {
            super(app);
        }

        @Override
        protected Object doInBackground()  {
            conexionCheck(false);
            return null;
        }

        @Override
        protected void succeeded(Object result) {

            if (conexionStatus() == 1) {
                
                loginDialogo.setLocationRelativeTo(mainPanel);
                loginDialogo.setIconImage(icono);
                loginDialogo.setVisible(true);
                
            } else {
                OpcionPanel.showMessageDialog(mainPanel, "No se pudo establecer una conexión a la "
                        + "Base de Datos.\nPor favor, revise los datos de conexión en Inicio -> Configurar Conexión.",
                        "Fallo de Conexión", OpcionPanel.INFORMATION_MESSAGE);
            }
        }
    }

    @Action
    public Task loginUser() {
        return new LoginUserTask(org.jdesktop.application.Application.getInstance(zilleprojects.ZilleProjectsApp.class));
    }

    private class LoginUserTask extends org.jdesktop.application.Task<Object, Void> {

        LoginUserTask(org.jdesktop.application.Application app) {
            // Runs on the EDT.  Copy GUI state that
            // doInBackground() depends on from parameters
            // to LoginUserTask fields, here.
            super(app);
            ZilleProjectsView.inicio = false;
            UsuarioLogged.setUser(userText1.getText());
            UsuarioLogged.setPass(String.valueOf(passText1.getPassword()));
            UsuarioLogged.setId_user(0);
            UsuarioLogged.setRol("");
        }

        @Override
        protected Object doInBackground() {
            // Your Task's code here.  This method runs
            // on a background thread, so don't reference
            // the Swing GUI from here.
            UsuarioDAO UDao = new UsuarioDAO();
            UDao.conectar();
            UDao.login();

            return null;  // return your result
        }

        @Override
        protected void succeeded(Object result) {
            if (UsuarioLogged.getId_user() != 0) {

                //OpcionPanel.showMessageDialog(loginDialogo, "Bienvenido " + UsuarioLogged.getUser() + "!", "Sesión iniciada", OpcionPanel.INFORMATION_MESSAGE);
                ZilleProjectsView.inicio = true;
                //loginPanel.setVisible(false);
                BotonGrandeLogin.setEnabled(false);
                
                loginOut.setText("Identificado como " + UsuarioLogged.getUser());
                iniciarSesion.setEnabled(false);
                if(Permisos.verificarCredenciales("Administrador,De Carga")){
                    //luego del login, se realiza el backup de la db
                    backUp();
                    // y se verifican las alarmas
                    //AlarmasTask al = new AlarmasTask();
                    //al.start();
                    verificarAlarmas().execute();
                    CargarParametros().execute();
                }
                loginDialogo.dispose();
                
                
            } else {
                outText.setText("Error: Compruebe los datos ingresados.");
                passText1.setText("");
            }
        }
    }

    void conexionCheck(boolean recheck)  {
        if (recheck) {
            Conexion.setConexion(null);
            conn.setDbname("");
            conn.setDbpass("");
            conn.setDbuser("");
            conn.setHost("");
            conn = configDB.config();
            link2db = Conexion.getConexion();
            
            
        } else {
            if (conn == null || conn.getDbname().equals("none")) {
                conn = configDB.config();
                link2db = Conexion.getConexion();
            }

        }
    }

    int conexionStatus() {
        switch (conn.getResult()) {
            case 0:
                statusMessageLabel.setText("No conectado. Revise la configuración a la Base de Datos.");
                return 0;
            case 1:
                statusMessageLabel.setText("Conexión exitosa.");
                return 1;
            case 2:
                statusMessageLabel.setText("No conectado. Datos de conexión incorrectos.");
                return 2;
            case 3:
                statusMessageLabel.setText("No conectado. No existe la configuración de conexión.");
                String mensaje = "No se pudo realizar la conexión porque el archivo\n de configuración no existe ni pudo ser creado.";
                OpcionPanel.showMessageDialog(mainPanel, mensaje, "Error. Archivos faltantes", busyIconIndex);
                return 3;
            default:
                return -1;
        }
    }

    /**Funcion que devuelve
     * la conexion a la db
     * para ser usada por el resto de las clases.
     * 
     * @return Connection to DB
     * public static Connection link() {
     *   return link2db; }
     */
    

    

    @Action
    public void limpiarDatosConfigDB() {
        dbText.setText("");
        hostText.setText("");
        userText.setText("");
        passText.setText("");
    }
    
    
    ///
@Action
    public final Task VerificarNuevaVersion() {
        return new VerificarNuevaVersionTask(getApplication());
    }

    private class VerificarNuevaVersionTask extends org.jdesktop.application.Task<Object, Void> {
        
        VerificarNuevaVersionTask(org.jdesktop.application.Application app) {

            super(app);
        }

        @Override
        protected Object doInBackground() {
            isLastVersion = Update.isLastVersion(configDB);
            return null;  // return your result
        }

        @Override
        protected void succeeded(Object result) {
            if(isLastVersion){
                btnDescargarVersion.setVisible(false);
                lblVersion.setText("Version: "+ZilleProjectsApp.VERSION+". Esta es la última versión.");
            }else{
                lblVersion.setText("Version: "+ZilleProjectsApp.VERSION+". Hay una nueva versión. ");
                btnDescargarVersion.setVisible(true);
            }

        }
    }
    
    @Action
    public final Task CargarParametros() {
        return new CargarParametrosTask(getApplication());
    }

    private class CargarParametrosTask extends org.jdesktop.application.Task<Object, Void> {
        
        CargarParametrosTask(org.jdesktop.application.Application app) {

            super(app);
        }

        @Override
        protected Object doInBackground() {
            ParametrosSistema.CargarParametros();
            return null;  // return your result
        }

        @Override
        protected void succeeded(Object result) {

        }
    }
        
    
    ///
    @Action
    public Task ConfigurarConnDialog() {
        return new ConfigurarConnDialogTask(getApplication());
    }

    private class ConfigurarConnDialogTask extends org.jdesktop.application.Task<Object, Void> {

        ConfigurarConnDialogTask(org.jdesktop.application.Application app) {

            super(app);
        }

        @Override
        protected Object doInBackground() {
            conexionCheck(false);
            return null;  // return your result
        }

        @Override
        protected void succeeded(Object result) {
            dbText.setText(conn.getDbname());
            hostText.setText(conn.getHost());
            userText.setText(conn.getDbuser());
            passText.setText(conn.getDbpass());
            configurarConnDialog.setLocationRelativeTo(mainPanel);
            configurarConnDialog.getRootPane().setDefaultButton(guardarDatos);
            configurarConnDialog.setIconImage(icono);
            configurarConnDialog.setVisible(true);

        }
    }
    
    @Action
    public Task GuardarDatosConexion() {
        return new GuardarDatosConexionTask(getApplication());
    }

    private class GuardarDatosConexionTask extends org.jdesktop.application.Task<Object, Void> {

        String dato[] = {dbText.getText(), hostText.getText(), userText.getText(), String.valueOf(passText.getPassword())};
        boolean r;

        GuardarDatosConexionTask(org.jdesktop.application.Application app) {

            super(app);
        }

        @Override
        protected Object doInBackground()  {

            r = configDB.escribirDatos(dato[0], dato[1], dato[2], dato[3]);
            conexionCheck(true);
            return null;  // return your result
        }

        @Override
        protected void succeeded(Object result) {

            if (conn.getResult() == 1) {
                OpcionPanel.showMessageDialog(mainPanel, "Se ha establecido una conexión.",
                        "Conexión exitosa", OpcionPanel.INFORMATION_MESSAGE);
                configurarConnDialog.dispose();
            } else {
                OpcionPanel.showMessageDialog(mainPanel, "No se pudo establecer la conexión. "
                        + "Revise los datos.", "Fallo de conexión", OpcionPanel.INFORMATION_MESSAGE);
            }
        }
    }

    @Action
    public Task RespaldarDB() {
        if (!Permisos.verificarCredenciales("Administrador")) {
            return null;
        } else {
            return new RespaldarDBTask(getApplication());
        }
    }

    private class RespaldarDBTask extends org.jdesktop.application.Task<Object, Void> {
        
        RespaldarDBTask(org.jdesktop.application.Application app) {
            super(app);
        }

        @Override
        protected Object doInBackground()  {

            backUp();
            
            return null;  // return your result
        }

        @Override
        protected void succeeded(Object result) {
               statusMessageLabel.setText(resultBKP);
               OpcionPanel.showMessageDialog(null, resultBKP, "Aviso", OpcionPanel.INFORMATION_MESSAGE);
        }
    }
    
    

    /*************************************************************************
     * **************   FIN de Configuracion base de datos  ******************
     * ***********************************************************************
     */
    @Action
    public void mostrarGestionOperario() {
        if (Permisos.verificarCredenciales("Administrador, De Carga")) {
            JFrame mainFrame = ZilleProjectsApp.getApplication().getMainFrame();
            gestionOperario = new JDEmpleadoGestion(mainFrame, true);
            gestionOperario.setLocationRelativeTo(mainFrame);
            ZilleProjectsApp.getApplication().show(gestionOperario);
        } 

    }

    
    @Action
    public void mostrarGenerarInforme() {
        if (Permisos.verificarCredenciales("Administrador")) {
            JFrame mainFrame = ZilleProjectsApp.getApplication().getMainFrame();
            generarInforme = new JDReportes(mainFrame, true);
            generarInforme.setLocationRelativeTo(null);
            ZilleProjectsApp.getApplication().show(generarInforme);
        } 

    }
    @Action
    public void mostrarInformesAnteriores() {
        if (Permisos.verificarCredenciales("Administrador")) {
            JFrame mainFrame = ZilleProjectsApp.getApplication().getMainFrame();
            informesAnteriores = new JDReportesAnteriores(mainFrame, true);
            informesAnteriores.setLocationRelativeTo(null);
            ZilleProjectsApp.getApplication().show(informesAnteriores);
        } 

    }

    @Action
    public void mostrarModificarEquipo() {
        if (Permisos.verificarCredenciales("Administrador,De Carga")) {
            JFrame mainFrame = ZilleProjectsApp.getApplication().getMainFrame();
            modificarEquipo = new JDEquipos(mainFrame, true);
            modificarEquipo.setLocationRelativeTo(mainFrame);
            ZilleProjectsApp.getApplication().show(modificarEquipo);
        } 

    }
    
    @Action
    public void mostrarOrdenDeTrabajo() {
        if (Permisos.verificarCredenciales("Administrador,De Carga")) {
            JFrame mainFrame = ZilleProjectsApp.getApplication().getMainFrame();
            ordenDeTrabajo = new JDOrdenTrabajo(mainFrame, true);
            ordenDeTrabajo.setLocationRelativeTo(mainFrame);   
            ZilleProjectsApp.getApplication().show(ordenDeTrabajo);
        } 

    }
    
    @Action
    public void mostrarAlarmasDialog() {
       
        if (Permisos.verificarCredenciales("Administrador,De Carga")) {
            JFrame mainFrame = ZilleProjectsApp.getApplication().getMainFrame();
            alarmaDialog = new JDAlarmas(mainFrame, true);
            alarmaDialog.setLocationRelativeTo(mainFrame);   
            ZilleProjectsApp.getApplication().show(alarmaDialog);
        } 

    }
    
    @Action
    public void mostrarJDSemaforosDialog() {
        if (Permisos.verificarCredenciales("Administrador,De Carga")) {
            JFrame mainFrame = ZilleProjectsApp.getApplication().getMainFrame();
            semaforosDialog = new JDSemaforos(mainFrame, true);
            semaforosDialog.setLocationRelativeTo(mainFrame);   
            ZilleProjectsApp.getApplication().show(semaforosDialog);
        } 

    }
    
    @Action
    public void MostrarDialogoUsuarios() {
        if (Permisos.verificarCredenciales("Administrador")) {
            JFrame mainFrame = ZilleProjectsApp.getApplication().getMainFrame();
            usuarios = new UsuariosDialog(mainFrame, true);
            usuarios.setLocationRelativeTo(null);
            
            usuarios.setMinimumSize(new Dimension(550, 400));
            ZilleProjectsApp.getApplication().show(usuarios);
        }
    }
    
    @Action
    public void mostrarJDRIDialog() {
       
        if (Permisos.verificarCredenciales("Administrador,De Carga")) {
            JFrame mainFrame = ZilleProjectsApp.getApplication().getMainFrame();
            reqInt = new JDRI(mainFrame, true, this);
            reqInt.setLocationRelativeTo(mainFrame);   
            ZilleProjectsApp.getApplication().show(reqInt);
        } 

    }
    
    
    @Action
    public void mostrarBusquedaEmpleado() {

        buscarEmpleado.setLocationRelativeTo(botonBuscarEmpleado);
        buscarEmpleado.setVisible(true);
    }

    
    @Action
    public Task GuardarParteMasivo() {
        if(empleadoMasivo.getId()!=0 && fechaChoose.getDate()!=null){
            if (OpcionPanel.YES_OPTION==
                OpcionPanel.showConfirmDialog(null, "¿Desea continuar?", "Guardar", OpcionPanel.YES_NO_OPTION)) {
                if(isHasta.isSelected() && fechaChoose.getDate().compareTo(hastaDateChoose.getDate()) >0){
                    OpcionPanel.showMessageDialog(null, "La fecha \"Hasta\" es anterior a \"Desde\"", "Error en las fechas!", OpcionPanel.ERROR_MESSAGE);
                    return null;
                }
                return new GuardarParteMasivoTask(getApplication());
                
        }
        }            
            return null;
        
    }
    private class GuardarParteMasivoTask extends org.jdesktop.application.Task<Object, Void> {
        Date hasta=null;
       ParteDiario pd= new ParteDiario();
       int i=0;
        GuardarParteMasivoTask(org.jdesktop.application.Application app) {

            super(app);
            pd.setFecha(fechaChoose.getDate());
            pd.setIdOperario(empleadoMasivo.getId());
            pd.setIdObra(((Obras)comboObrasParteMasivo.getSelectedItem()).getId());
            //pd.setIdSituacion(((Situacion)comboObrasParteMasivo.getSelectedItem()).getId());
            pd.setObservaciones(observacionesTexto.getText());
            pd.setNumero(txtNumeroParte.getText(), txtNumeroParte2.getText());
            boolean desa = (pd.isDesarraigo()| empleadoMasivo.isDesarraigo());

            //Si la obra tiene Desarraigo y desarraigo es true
            Perfiles p = new Perfiles();
            p.setObra(((Obras)comboObrasParteMasivo.getSelectedItem()));
            pd.calcularVianda(p, null, desa);
            if(isHasta.isSelected()){
                
                hasta=hastaDateChoose.getDate();
            }
        }

        @Override
        protected Object doInBackground()  {
            ParteDiarioDAO pdao= new ParteDiarioDAO();
            pdao.conectar();
            if(hasta!=null){
                i=pdao.guardarPartesEspecialHasta(pd, hasta);
            }else{
                i=pdao.guardarParteEspecial(pd);
            }
            return null;
        }

        @Override
        protected void succeeded(Object result) {
            if(i>=0){
                OpcionPanel.showMessageDialog(null, "Se guardaron los datos correctamente", "Hecho!", OpcionPanel.INFORMATION_MESSAGE);
                //SituacionExtra.dispose();
                resetearParteDiarioMasivo();
                
            }else{
                OpcionPanel.showMessageDialog(null, "Ocurrió un error. \n i="+i, "Error!", OpcionPanel.ERROR_MESSAGE);
                
            }
        }
    }
    

    @Action
    public Task mostrarRemoverParte() {
        if (!Permisos.verificarCredenciales("Administrador,De Carga")) {
            return null;
        } else {
            return new mostrarRemoverParteTask(getApplication());
        }
    }
    private class mostrarRemoverParteTask extends org.jdesktop.application.Task<Object, Void> {

        ArrayList<Operario> ops = new ArrayList<Operario>();
        ArrayList<Obras> obs = new ArrayList<Obras>();

        mostrarRemoverParteTask(org.jdesktop.application.Application app) {

            super(app);
        }

        @Override
        protected Object doInBackground()  {

            OperarioDAO odao = new OperarioDAO();
            ObrasDAO obrasDAO = new ObrasDAO();
            odao.conectar();
            obrasDAO.conectar();
            ops = odao.cargarTodos();
            obs = obrasDAO.cargarTodos();
            return null;
        }

        @Override
        protected void succeeded(Object result) {
            DefaultComboBoxModel aux;

            if (!ops.isEmpty()) {
                aux = new DefaultComboBoxModel();
                for (int i = 0; i < ops.size(); i++) {
                    aux.addElement(ops.get(i));
                }
                JDRemoverParte.operarios = aux;
            }
            if (!obs.isEmpty()) {
                aux = new DefaultComboBoxModel();
                for (int i = 0; i < obs.size(); i++) {
                    aux.addElement(obs.get(i));
                }
                JDRemoverParte.obras = aux;
            }

            JFrame mainFrame = ZilleProjectsApp.getApplication().getMainFrame();
            removerParte = new JDRemoverParte(mainFrame, true);
            removerParte.setLocationRelativeTo(mainFrame);
            removerParte.setMaximumSize(new Dimension(586, 424));
            ZilleProjectsApp.getApplication().show(removerParte);
        }
    }
    
    @Action
    public Task mostrarParteDiarioMasivo() {
        if (!Permisos.verificarCredenciales("Administrador,De Carga")) {
            return null;
        } else {
            return new ParteDiarioMasivoTask(getApplication());
        }
    }

    
    private class ParteDiarioMasivoTask extends org.jdesktop.application.Task<Object, Void> {

        ArrayList<Obras> obras = new ArrayList<Obras>();
       
        ParteDiarioMasivoTask(org.jdesktop.application.Application app) {

            super(app);
        }

        @Override
        protected Object doInBackground() {
            ObrasDAO odao = new ObrasDAO();
            odao.conectar();
            obras=odao.cargarObrasParteDiarioMasivo();
            return null;
        }

        @Override
        protected void succeeded(Object result) {
            
            if (!obras.isEmpty()) {
                centroDeCostoMasivo.removeAllElements();
                Obras s= new Obras();
                for (int i = 0; i < obras.size(); i++) {
                    centroDeCostoMasivo.addElement((Obras)obras.get(i));
                }
            }
            
            
            SituacionExtra.setLocationRelativeTo(null);
            SituacionExtra.setMaximumSize(new Dimension(586, 424));
            ZilleProjectsApp.getApplication().show(SituacionExtra);
        }
    }

    @Action
    public void mostrarObras() {
        if (Permisos.verificarCredenciales("Administrador")) {
            JFrame mainFrame = ZilleProjectsApp.getApplication().getMainFrame();
            obrasDialog = new JDObrasGestion(mainFrame, true);
            obrasDialog.setLocationRelativeTo(mainFrame);
            ObrasDAO odao = new ObrasDAO();
            dcbm = new DefaultComboBoxModel();
            odao.conectar();
            ArrayList<Obras> o = odao.cargarTodos();
            for (int i = 0; i < o.size(); i++) {
                JDObrasGestion.dcbm.addElement((Obras) o.get(i));
            }
            JDObrasGestion.dcbm = dcbm;
            ZilleProjectsApp.getApplication().show(obrasDialog);
        } 

    }

    @Action
    public Task mostrarIngresarParte() {
        if (!Permisos.verificarCredenciales("Administrador,De Carga")) {
            return null;
        }
        return new mostrarIngresarParteTask(getApplication());
    }

    private class mostrarIngresarParteTask extends org.jdesktop.application.Task<Object, Void> {

        ArrayList<Operario> ops = new ArrayList<Operario>();
        ArrayList<Funcion> fcs = new ArrayList<Funcion>();
        ArrayList<Obras> obs = new ArrayList<Obras>();
        ArrayList<Equipos> eqs = new ArrayList<Equipos>();
        ArrayList<EstacionServicio> estaciones = new ArrayList<EstacionServicio>();

        mostrarIngresarParteTask(org.jdesktop.application.Application app) {

            super(app);
        }

        @Override
        protected Object doInBackground()  {
            FuncionDAO fdao = new FuncionDAO();
            OperarioDAO odao = new OperarioDAO();
            ObrasDAO obrasDAO = new ObrasDAO();
            EquiposDAO edao = new EquiposDAO();
            EstacionServicioDAO esDAO = new EstacionServicioDAO();
            fdao.conectar();
            odao.conectar();
            obrasDAO.conectar();
            edao.conectar();
            esDAO.conectar();
            fcs = fdao.cargarTodos();
            ops = odao.cargarTodos();
            obs = obrasDAO.cargarTodos();
            eqs = edao.cargarTodos();
            estaciones = esDAO.cargarTodos2Combo();
            return null;
        }

        @Override
        protected void succeeded(Object result) {
            DefaultComboBoxModel aux;
            if (!fcs.isEmpty()) {
                aux = new DefaultComboBoxModel();
                for (int i = 0; i < fcs.size(); i++) {
                    aux.addElement(fcs.get(i));
                }
                JDParteDiario.funciones = aux;
            }
            if (!ops.isEmpty()) {
                aux = new DefaultComboBoxModel();
                for (int i = 0; i < ops.size(); i++) {
                    aux.addElement(ops.get(i));
                }
                JDParteDiario.operarios = aux;
            }
            if (!obs.isEmpty()) {
                aux = new DefaultComboBoxModel();
                for (int i = 0; i < obs.size(); i++) {
                    aux.addElement(obs.get(i));
                }
                JDParteDiario.obras = aux;
            }
            if (!eqs.isEmpty()) {
                aux = new DefaultComboBoxModel();
                for (int i = 0; i < eqs.size(); i++) {
                    aux.addElement(eqs.get(i));
                }
                JDParteDiario.equipos = aux;
            }
            if (!estaciones.isEmpty()) {
                aux = new DefaultComboBoxModel();
                for (int i = 0; i < estaciones.size(); i++) {
                    aux.addElement(estaciones.get(i));
                }
                JDParteDiario.estaciones = aux;
            }
            JFrame mainFrame = ZilleProjectsApp.getApplication().getMainFrame();
            parteIngreso = new JDParteDiario(mainFrame, true);
            
            ZilleProjectsApp.getApplication().show(parteIngreso);
            parteIngreso.setLocation(100, 100);
        }
    }
    @Action
    public Task buscarEmpleado() {
        if (findEmpleadoText.getText().isEmpty()) {
            return null;
        }
        return new BuscarEmpleadoTask(org.jdesktop.application.Application.getInstance(zilleprojects.ZilleProjectsApp.class));
    }

    private class BuscarEmpleadoTask extends org.jdesktop.application.Task<Object, Void> {

        ArrayList<Operario> opList = new ArrayList<Operario>();
        String query = findEmpleadoText.getText();

        BuscarEmpleadoTask(org.jdesktop.application.Application app) {
            
            super(app);
        }

        @Override
        protected Object doInBackground() {
           
            OperarioDAO odao = new OperarioDAO();
            odao.conectar();
            opList = odao.buscar(query);
            return null;  // return your result
        }

        @Override
        protected void succeeded(Object result) {
            listEmpl.removeAllElements();
            if (opList.isEmpty()) {
                listEmpl.addElement("Sin resultados");
                listaEmpleados.setEnabled(false);
            } else {
                for (int i = 0; i < opList.size(); i++) {
                    listEmpl.addElement((Operario) opList.get(i));
                }
                listaEmpleados.setEnabled(true);
            }
        }
    }
    
    @Action
    public Task mostrarEstacionesServicio() {
        if (!Permisos.verificarCredenciales("Administrador,De Carga")) {
            return null;
        } else {
            return new EstacionesServicioTask(getApplication());
        }
    }

    

    
    private class EstacionesServicioTask extends org.jdesktop.application.Task<Object, Void> {

        ArrayList<EstacionServicio> es = new ArrayList<EstacionServicio>();
       
        EstacionesServicioTask(org.jdesktop.application.Application app) {

            super(app);
        }

        @Override
        protected Object doInBackground()  {
            EstacionServicioDAO esdao = new EstacionServicioDAO();
            esdao.conectar();
            es=esdao.cargarTodos2Combo();
            return null;
        }

        @Override
        protected void succeeded(Object result) {
            
            if (!es.isEmpty()) {
                JDEstacionServicio.estaciones.removeAllElements();                
                for (EstacionServicio s: es) {
                    if(s.getEstacionServicioID()!=0)
                         JDEstacionServicio.estaciones.addElement(s);
                    }
                }
            JFrame mainFrame = ZilleProjectsApp.getApplication().getMainFrame();
            estacionServicio = new JDEstacionServicio(mainFrame, true);
            estacionServicio.setLocationRelativeTo(BotonGrandeLogin);
            ZilleProjectsApp.getApplication().show(estacionServicio);
        }
    }
    
    @Action
    public Task verificarAlarmas() {   
        return new verificarAlarmasTask(getApplication());      
    }

    private class verificarAlarmasTask extends org.jdesktop.application.Task<Object, Void> {
        ArrayList<ItemAlarma> alarmas = new ArrayList<ItemAlarma>();
        ArrayList<Alarma> alarmasList = new ArrayList<Alarma>();
        verificarAlarmasTask(org.jdesktop.application.Application app) {

            super(app);
        }

        @Override
        protected Object doInBackground()  {
            
            Date dia = new Date();
            GregorianCalendar gc = new GregorianCalendar();
            
            gc.setTime(dia);
            /*Seteo las horas, minutos etc en 0 para que al comparar con los date de sql
             * filtren bien las fechas
             */
            gc.set(GregorianCalendar.MINUTE,0);
            gc.set(GregorianCalendar.HOUR_OF_DAY,0);
            gc.set(GregorianCalendar.SECOND,0);
            gc.set(GregorianCalendar.MILLISECOND,0);
            dia = gc.getTime();
            gc.add(GregorianCalendar.DATE, 20);
            Date proximo = gc.getTime();
            EquiposDAO edao=new EquiposDAO();
            edao.conectar();
            OperarioDAO odao = new OperarioDAO();
            odao.conectar();    
            //Buscar las VTO_VT con fecha prximo 20 dias.
            //Buscar las VTO_SEGURO con fecha proximo 20 días.
            alarmas = edao.getAlarmasEquipos(dia, proximo);
            //Buscar las VtO_CARNET con fecha proximo 20 días.
            alarmas.addAll(odao.getAlarmasOperarios(dia, proximo));
            //Luego, buscar las alarmas para hoy, y cuyo fecha previa sea antes o igual a hoy.
            AlarmasDAO adao = new AlarmasDAO();
            adao.conectar();
            alarmasList = adao.findAlarmasActivadas();    
            return null;
        }

        @Override
        protected void succeeded(Object result) {
            if(alarmas.size()>0 || alarmasList.size() > 0){
                //Crear dialogos para cada alarma actividad, y incorporar las alarmas item. 
                for(Alarma al:alarmasList){
                    if(alarmasDialogo){
                        JFrame mainFrame = ZilleProjectsApp.getApplication().getMainFrame();
                        JDAlarmaActividad aAct = new JDAlarmaActividad(mainFrame,  true, al);
                        aAct.setLocationRelativeTo(null);
                        ZilleProjectsApp.getApplication().show(aAct);
                    }
                    ItemAlarma aux = new ItemAlarma();
                    aux.setFecha(al.getFecha());
                    aux.setTipo(0);
                    aux.setMensaje(al.getNombre()+": "+al.getComentario());
                    alarmas.add(aux);
                }
                alarmasDialogo=false;
                modelAlarma.clean();
                //Ordeno la lista segun la fecha
                Collections.sort(alarmas);
                for(ItemAlarma ia: alarmas){
                    modelAlarma.addRegistro(ia);
                }  
                
                jTable1.getColumnModel().getColumn(0).setWidth(28);
                jTable1.getColumnModel().getColumn(0).setMinWidth(26);
                jTable1.getColumnModel().getColumn(0).setMaxWidth(30);
                
                jTable1.getColumnModel().getColumn(1).setWidth(100);
                jTable1.getColumnModel().getColumn(1).setMinWidth(98);
                jTable1.getColumnModel().getColumn(1).setMaxWidth(102);
                
                panelAlarmas.setVisible(true);
            }else {
                panelAlarmas.setVisible(false);
            }
        }
    }

    
    
    
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jPanel6 = new DialogPanel();
        BotonGrandeLogin = new javax.swing.JButton();
        loginOut = new javax.swing.JLabel();
        botonGrandeSalir = new javax.swing.JButton();
        panelAlarmas = new PanelAlarma();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new DialogImgZille();
        lblVersion = new javax.swing.JLabel();
        btnDescargarVersion = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        iniciarSesion = new javax.swing.JMenuItem();
        configurarConexion = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        removerParteMenu = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        EmpleadosMenu = new javax.swing.JMenu();
        gestionarMenu = new javax.swing.JMenuItem();
        gestionarEquipos = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        menuUsuarios = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menuGenerarInforme = new javax.swing.JMenuItem();
        informesAntesMenu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        backupDB = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        configurarConnDialog = new javax.swing.JDialog();
        jPanel7 = new Vista.PanelAzul();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        dbText = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        hostText = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        userText = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        passText = new javax.swing.JPasswordField();
        clearText = new javax.swing.JButton();
        guardarDatos = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        loginDialogo = new javax.swing.JDialog();
        jPanel4 = new Vista.PanelAzul()
        ;
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        userText1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        passText1 = new javax.swing.JPasswordField();
        outText = new javax.swing.JLabel();
        cerrarLogin = new javax.swing.JButton();
        botonLogin = new javax.swing.JButton();
        SituacionExtra = new javax.swing.JDialog();
        jPanel8 = new PanelAzul();
        comboObrasParteMasivo = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        observacionesTexto = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cerrarSituacionDialog = new javax.swing.JButton();
        guardarParteEspecial = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        empleadoText = new javax.swing.JLabel();
        botonBuscarEmpleado = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        fechaChoose = new com.toedter.calendar.JDateChooser();
        isHasta = new javax.swing.JCheckBox();
        hastaDateChoose = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        txtNumeroParte = new javax.swing.JTextField();
        txtNumeroParte2 = new javax.swing.JTextField();
        buscarEmpleado = new javax.swing.JDialog();
        jPanel9 = new PanelAzul();
        exitDialog = new javax.swing.JButton();
        selectRow = new javax.swing.JButton();
        botonQuery = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        listaEmpleados = new javax.swing.JList();
        findEmpleadoText = new javax.swing.JTextField();

        mainPanel.setMinimumSize(new java.awt.Dimension(744, 500));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(996, 500));

        jPanel6.setMinimumSize(new java.awt.Dimension(728, 90));
        jPanel6.setName("jPanel6"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(zilleprojects.ZilleProjectsApp.class).getContext().getActionMap(ZilleProjectsView.class, this);
        BotonGrandeLogin.setAction(actionMap.get("iniciarSesion")); // NOI18N
        BotonGrandeLogin.setFont(BotonGrandeLogin.getFont().deriveFont(BotonGrandeLogin.getFont().getStyle() | java.awt.Font.BOLD, BotonGrandeLogin.getFont().getSize()-1));
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(zilleprojects.ZilleProjectsApp.class).getContext().getResourceMap(ZilleProjectsView.class);
        BotonGrandeLogin.setIcon(resourceMap.getIcon("BotonGrandeLogin.icon")); // NOI18N
        BotonGrandeLogin.setText(resourceMap.getString("BotonGrandeLogin.text")); // NOI18N
        BotonGrandeLogin.setName("BotonGrandeLogin"); // NOI18N

        loginOut.setForeground(resourceMap.getColor("loginOut.foreground")); // NOI18N
        loginOut.setText(resourceMap.getString("loginOut.text")); // NOI18N
        loginOut.setName("loginOut"); // NOI18N

        botonGrandeSalir.setAction(actionMap.get("quit")); // NOI18N
        botonGrandeSalir.setFont(botonGrandeSalir.getFont().deriveFont(botonGrandeSalir.getFont().getStyle() | java.awt.Font.BOLD, botonGrandeSalir.getFont().getSize()-1));
        botonGrandeSalir.setIcon(resourceMap.getIcon("botonGrandeSalir.icon")); // NOI18N
        botonGrandeSalir.setText(resourceMap.getString("botonGrandeSalir.text")); // NOI18N
        botonGrandeSalir.setName("botonGrandeSalir"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BotonGrandeLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonGrandeSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginOut, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addGap(426, 426, 426))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BotonGrandeLogin, botonGrandeSalir});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(loginOut, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BotonGrandeLogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(botonGrandeSalir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelAlarmas.setBackground(resourceMap.getColor("panelAlarmas.background")); // NOI18N
        panelAlarmas.setMinimumSize(new java.awt.Dimension(420, 276));
        panelAlarmas.setName("panelAlarmas"); // NOI18N
        panelAlarmas.setOpaque(false);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable1.setModel(modelAlarma);
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane2.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(new ImageIconTable());

        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAlarmasLayout = new javax.swing.GroupLayout(panelAlarmas);
        panelAlarmas.setLayout(panelAlarmasLayout);
        panelAlarmasLayout.setHorizontalGroup(
            panelAlarmasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAlarmasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAlarmasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)))
        );
        panelAlarmasLayout.setVerticalGroup(
            panelAlarmasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAlarmasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
        );

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(419, 149));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 389, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 149, Short.MAX_VALUE)
        );

        lblVersion.setFont(lblVersion.getFont().deriveFont(lblVersion.getFont().getStyle() | java.awt.Font.BOLD, lblVersion.getFont().getSize()+2));
        lblVersion.setText(resourceMap.getString("lblVersion.text")); // NOI18N
        lblVersion.setName("lblVersion"); // NOI18N

        btnDescargarVersion.setText("<HTML><FONT color=\"#000099\"><U>Descargar la última versión del sistema.</U></FONT></HTML>");
        btnDescargarVersion.setName("btnDescargarVersion"); // NOI18N
        btnDescargarVersion.setVisible(false);
        btnDescargarVersion.setHorizontalAlignment(SwingConstants.CENTER);
        btnDescargarVersion.setBorderPainted(false);
        btnDescargarVersion.setOpaque(false);
        btnDescargarVersion.setBackground(Color.WHITE);
        btnDescargarVersion.setToolTipText("Descargar nueva versión");
        btnDescargarVersion.addActionListener(new OpenUrlAction());

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                    .addComponent(btnDescargarVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAlarmas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 259, Short.MAX_VALUE)
                        .addComponent(btnDescargarVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelAlarmas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        menuBar.setBackground(resourceMap.getColor("menuBar.background")); // NOI18N
        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setMargin(new java.awt.Insets(0, 10, 5, 10));
        fileMenu.setName("fileMenu"); // NOI18N

        iniciarSesion.setAction(actionMap.get("iniciarSesion")); // NOI18N
        iniciarSesion.setText(resourceMap.getString("iniciarSesion.text")); // NOI18N
        iniciarSesion.setName("iniciarSesion"); // NOI18N
        fileMenu.add(iniciarSesion);

        configurarConexion.setAction(actionMap.get("ConfigurarConnDialog")); // NOI18N
        configurarConexion.setIcon(resourceMap.getIcon("configurarConexion.icon")); // NOI18N
        configurarConexion.setText(resourceMap.getString("configurarConexion.text")); // NOI18N
        configurarConexion.setName("configurarConexion"); // NOI18N
        fileMenu.add(configurarConexion);

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        jMenu4.setText(resourceMap.getString("jMenu4.text")); // NOI18N
        jMenu4.setMargin(new java.awt.Insets(0, 10, 5, 10));
        jMenu4.setName("jMenu4"); // NOI18N

        jMenuItem3.setAction(actionMap.get("mostrarIngresarParte")); // NOI18N
        jMenuItem3.setIcon(resourceMap.getIcon("jMenuItem3.icon")); // NOI18N
        jMenuItem3.setText(resourceMap.getString("jMenuItem3.text")); // NOI18N
        jMenuItem3.setName("jMenuItem3"); // NOI18N
        jMenu4.add(jMenuItem3);

        removerParteMenu.setAction(actionMap.get("mostrarRemoverParte")); // NOI18N
        removerParteMenu.setIcon(resourceMap.getIcon("removerParteMenu.icon")); // NOI18N
        removerParteMenu.setText(resourceMap.getString("removerParteMenu.text")); // NOI18N
        removerParteMenu.setName("removerParteMenu"); // NOI18N
        jMenu4.add(removerParteMenu);

        jMenuItem1.setAction(actionMap.get("mostrarParteDiarioMasivo")); // NOI18N
        jMenuItem1.setIcon(resourceMap.getIcon("jMenuItem1.icon")); // NOI18N
        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenu4.add(jMenuItem1);

        menuBar.add(jMenu4);

        EmpleadosMenu.setAction(actionMap.get("mostrarGestionOperario")); // NOI18N
        EmpleadosMenu.setText(resourceMap.getString("EmpleadosMenu.text")); // NOI18N
        EmpleadosMenu.setMargin(new java.awt.Insets(0, 10, 5, 10));
        EmpleadosMenu.setName("EmpleadosMenu"); // NOI18N

        gestionarMenu.setAction(actionMap.get("mostrarGestionOperario")); // NOI18N
        gestionarMenu.setIcon(resourceMap.getIcon("gestionarMenu.icon")); // NOI18N
        gestionarMenu.setText(resourceMap.getString("gestionarMenu.text")); // NOI18N
        gestionarMenu.setName("gestionarMenu"); // NOI18N
        EmpleadosMenu.add(gestionarMenu);

        gestionarEquipos.setAction(actionMap.get("mostrarModificarEquipo")); // NOI18N
        gestionarEquipos.setIcon(resourceMap.getIcon("gestionarEquipos.icon")); // NOI18N
        gestionarEquipos.setText(resourceMap.getString("gestionarEquipos.text")); // NOI18N
        gestionarEquipos.setName("gestionarEquipos"); // NOI18N
        EmpleadosMenu.add(gestionarEquipos);

        jMenuItem2.setAction(actionMap.get("mostrarObras")); // NOI18N
        jMenuItem2.setIcon(resourceMap.getIcon("jMenuItem2.icon")); // NOI18N
        jMenuItem2.setText(resourceMap.getString("jMenuItem2.text")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        EmpleadosMenu.add(jMenuItem2);

        jMenuItem5.setAction(actionMap.get("mostrarOrdenDeTrabajo")); // NOI18N
        jMenuItem5.setIcon(resourceMap.getIcon("jMenuItem5.icon")); // NOI18N
        jMenuItem5.setText(resourceMap.getString("jMenuItem5.text")); // NOI18N
        jMenuItem5.setName("jMenuItem5"); // NOI18N
        EmpleadosMenu.add(jMenuItem5);

        jMenuItem8.setAction(actionMap.get("mostrarJDRIDialog")); // NOI18N
        jMenuItem8.setIcon(resourceMap.getIcon("jMenuItem8.icon")); // NOI18N
        jMenuItem8.setText(resourceMap.getString("jMenuItem8.text")); // NOI18N
        jMenuItem8.setName("jMenuItem8"); // NOI18N
        EmpleadosMenu.add(jMenuItem8);

        jMenuItem4.setAction(actionMap.get("mostrarEstacionesServicio")); // NOI18N
        jMenuItem4.setIcon(resourceMap.getIcon("jMenuItem4.icon")); // NOI18N
        jMenuItem4.setText(resourceMap.getString("jMenuItem4.text")); // NOI18N
        jMenuItem4.setName("jMenuItem4"); // NOI18N
        EmpleadosMenu.add(jMenuItem4);

        jMenuItem9.setAction(actionMap.get("mostrarJDSemaforosDialog")); // NOI18N
        jMenuItem9.setIcon(resourceMap.getIcon("jMenuItem9.icon")); // NOI18N
        jMenuItem9.setText(resourceMap.getString("jMenuItem9.text")); // NOI18N
        jMenuItem9.setName("jMenuItem9"); // NOI18N
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        EmpleadosMenu.add(jMenuItem9);

        menuUsuarios.setAction(actionMap.get("MostrarDialogoUsuarios")); // NOI18N
        menuUsuarios.setIcon(resourceMap.getIcon("menuUsuarios.icon")); // NOI18N
        menuUsuarios.setText(resourceMap.getString("menuUsuarios.text")); // NOI18N
        menuUsuarios.setName("menuUsuarios"); // NOI18N
        EmpleadosMenu.add(menuUsuarios);

        menuBar.add(EmpleadosMenu);

        jMenu5.setText(resourceMap.getString("jMenu5.text")); // NOI18N
        jMenu5.setMargin(new java.awt.Insets(0, 10, 5, 10));
        jMenu5.setName("jMenu5"); // NOI18N

        jMenuItem6.setAction(actionMap.get("verificarAlarmas")); // NOI18N
        jMenuItem6.setIcon(resourceMap.getIcon("jMenuItem6.icon")); // NOI18N
        jMenuItem6.setText(resourceMap.getString("jMenuItem6.text")); // NOI18N
        jMenuItem6.setName("jMenuItem6"); // NOI18N
        jMenu5.add(jMenuItem6);

        jMenuItem7.setAction(actionMap.get("mostrarAlarmasDialog")); // NOI18N
        jMenuItem7.setIcon(resourceMap.getIcon("jMenuItem7.icon")); // NOI18N
        jMenuItem7.setText(resourceMap.getString("jMenuItem7.text")); // NOI18N
        jMenuItem7.setName("jMenuItem7"); // NOI18N
        jMenu5.add(jMenuItem7);

        menuBar.add(jMenu5);

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setMargin(new java.awt.Insets(0, 10, 5, 10));
        jMenu1.setName("jMenu1"); // NOI18N

        menuGenerarInforme.setAction(actionMap.get("mostrarGenerarInforme")); // NOI18N
        menuGenerarInforme.setIcon(resourceMap.getIcon("menuGenerarInforme.icon")); // NOI18N
        menuGenerarInforme.setText(resourceMap.getString("menuGenerarInforme.text")); // NOI18N
        menuGenerarInforme.setName("menuGenerarInforme"); // NOI18N
        jMenu1.add(menuGenerarInforme);

        informesAntesMenu.setAction(actionMap.get("mostrarInformesAnteriores")); // NOI18N
        informesAntesMenu.setIcon(resourceMap.getIcon("informesAntesMenu.icon")); // NOI18N
        informesAntesMenu.setText(resourceMap.getString("informesAntesMenu.text")); // NOI18N
        informesAntesMenu.setName("informesAntesMenu"); // NOI18N
        jMenu1.add(informesAntesMenu);

        menuBar.add(jMenu1);

        jMenu2.setText(resourceMap.getString("jMenu2.text")); // NOI18N
        jMenu2.setMargin(new java.awt.Insets(0, 10, 5, 10));
        jMenu2.setName("jMenu2"); // NOI18N

        backupDB.setAction(actionMap.get("RespaldarDB")); // NOI18N
        backupDB.setIcon(resourceMap.getIcon("backupDB.icon")); // NOI18N
        backupDB.setText(resourceMap.getString("backupDB.text")); // NOI18N
        backupDB.setName("backupDB"); // NOI18N
        jMenu2.add(backupDB);

        menuBar.add(jMenu2);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setMargin(new java.awt.Insets(0, 10, 5, 10));
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setIcon(resourceMap.getIcon("aboutMenuItem.icon")); // NOI18N
        aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setMinimumSize(new java.awt.Dimension(990, 40));
        statusPanel.setName("statusPanel"); // NOI18N
        statusPanel.setPreferredSize(new java.awt.Dimension(990, 40));

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setForeground(resourceMap.getColor("statusMessageLabel.foreground")); // NOI18N
        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 353, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(statusAnimationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(statusMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusAnimationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        configurarConnDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        configurarConnDialog.setTitle(resourceMap.getString("configurarConnDialog.title")); // NOI18N
        configurarConnDialog.setIconImage(null);
        configurarConnDialog.setMinimumSize(new java.awt.Dimension(402, 250));
        configurarConnDialog.setModal(true);
        configurarConnDialog.setName("configurarConnDialog"); // NOI18N
        configurarConnDialog.setResizable(false);
        configurarConnDialog.setUndecorated(true);

        jPanel7.setBorder(new javax.swing.border.LineBorder(resourceMap.getColor("jPanel7.border.lineColor"), 1, true)); // NOI18N
        jPanel7.setMinimumSize(new java.awt.Dimension(333, 246));
        jPanel7.setName("jPanel7"); // NOI18N

        jPanel2.setMinimumSize(new java.awt.Dimension(269, 219));
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        dbText.setMinimumSize(new java.awt.Dimension(20, 26));
        dbText.setName("dbText"); // NOI18N
        dbText.setPreferredSize(new java.awt.Dimension(20, 26));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        hostText.setMinimumSize(new java.awt.Dimension(20, 26));
        hostText.setName("hostText"); // NOI18N
        hostText.setPreferredSize(new java.awt.Dimension(20, 26));

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        userText.setMinimumSize(new java.awt.Dimension(20, 26));
        userText.setName("userText"); // NOI18N
        userText.setPreferredSize(new java.awt.Dimension(20, 26));

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        passText.setMinimumSize(new java.awt.Dimension(20, 26));
        passText.setName("passText"); // NOI18N
        passText.setPreferredSize(new java.awt.Dimension(20, 26));

        clearText.setAction(actionMap.get("limpiarDatosConfigDB")); // NOI18N
        clearText.setIcon(resourceMap.getIcon("clearText.icon")); // NOI18N
        clearText.setText(resourceMap.getString("clearText.text")); // NOI18N
        clearText.setName("clearText"); // NOI18N

        guardarDatos.setAction(actionMap.get("GuardarDatosConexion")); // NOI18N
        guardarDatos.setIcon(resourceMap.getIcon("guardarDatos.icon")); // NOI18N
        guardarDatos.setText(resourceMap.getString("guardarDatos.text")); // NOI18N
        guardarDatos.setName("guardarDatos"); // NOI18N

        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dbText, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(hostText, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(userText, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(passText, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(clearText)
                        .addGap(18, 18, 18)
                        .addComponent(guardarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dbText, hostText, passText, userText});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(dbText, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hostText, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userText, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(passText, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guardarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearText, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout configurarConnDialogLayout = new javax.swing.GroupLayout(configurarConnDialog.getContentPane());
        configurarConnDialog.getContentPane().setLayout(configurarConnDialogLayout);
        configurarConnDialogLayout.setHorizontalGroup(
            configurarConnDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        configurarConnDialogLayout.setVerticalGroup(
            configurarConnDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        loginDialogo.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        loginDialogo.setTitle(resourceMap.getString("loginDialogo.title")); // NOI18N
        loginDialogo.setAlwaysOnTop(true);
        loginDialogo.setMinimumSize(new java.awt.Dimension(320, 185));
        loginDialogo.setModal(true);
        loginDialogo.setName("loginDialogo"); // NOI18N
        loginDialogo.setResizable(false);
        loginDialogo.setUndecorated(true);
        loginDialogo.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                loginDialogoWindowActivated(evt);
            }
        });

        jPanel4.setBackground(resourceMap.getColor("jPanel4.background")); // NOI18N
        jPanel4.setBorder(new javax.swing.border.LineBorder(resourceMap.getColor("jPanel4.border.lineColor"), 1, true)); // NOI18N
        jPanel4.setMinimumSize(new java.awt.Dimension(320, 155));
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(390, 115));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel5.border.title"))); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel5.add(jLabel1);

        userText1.setName("userText1"); // NOI18N
        userText1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                userText1FocusGained(evt);
            }
        });
        jPanel5.add(userText1);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel5.add(jLabel2);

        passText1.setName("passText1"); // NOI18N
        jPanel5.add(passText1);

        outText.setFont(outText.getFont().deriveFont(outText.getFont().getStyle() | java.awt.Font.BOLD));
        outText.setForeground(resourceMap.getColor("outText.foreground")); // NOI18N
        outText.setName("outText"); // NOI18N

        cerrarLogin.setFont(cerrarLogin.getFont().deriveFont(cerrarLogin.getFont().getSize()-1f));
        cerrarLogin.setIcon(resourceMap.getIcon("cerrarLogin.icon")); // NOI18N
        cerrarLogin.setText(resourceMap.getString("cerrarLogin.text")); // NOI18N
        cerrarLogin.setName("cerrarLogin"); // NOI18N
        cerrarLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarLoginActionPerformed(evt);
            }
        });

        botonLogin.setAction(actionMap.get("loginUser")); // NOI18N
        botonLogin.setFont(botonLogin.getFont().deriveFont(botonLogin.getFont().getSize()-1f));
        botonLogin.setIcon(resourceMap.getIcon("botonLogin.icon")); // NOI18N
        botonLogin.setText(resourceMap.getString("botonLogin.text")); // NOI18N
        botonLogin.setName("botonLogin"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cerrarLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(botonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(outText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outText, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(botonLogin)
                    .addComponent(cerrarLogin))
                .addContainerGap())
        );

        javax.swing.GroupLayout loginDialogoLayout = new javax.swing.GroupLayout(loginDialogo.getContentPane());
        loginDialogo.getContentPane().setLayout(loginDialogoLayout);
        loginDialogoLayout.setHorizontalGroup(
            loginDialogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
        );
        loginDialogoLayout.setVerticalGroup(
            loginDialogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
        );

        SituacionExtra.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        SituacionExtra.setTitle(resourceMap.getString("SituacionExtra.title")); // NOI18N
        SituacionExtra.setMinimumSize(new java.awt.Dimension(400, 300));
        SituacionExtra.setModal(true);
        SituacionExtra.setName("SituacionExtra"); // NOI18N
        SituacionExtra.setResizable(false);

        jPanel8.setMinimumSize(new java.awt.Dimension(400, 300));
        jPanel8.setName("jPanel8"); // NOI18N

        comboObrasParteMasivo.setModel(centroDeCostoMasivo);
        comboObrasParteMasivo.setName("comboObrasParteMasivo"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        observacionesTexto.setColumns(20);
        observacionesTexto.setRows(5);
        observacionesTexto.setName("observacionesTexto"); // NOI18N
        jScrollPane1.setViewportView(observacionesTexto);

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        cerrarSituacionDialog.setIcon(resourceMap.getIcon("cerrarSituacionDialog.icon")); // NOI18N
        cerrarSituacionDialog.setText(resourceMap.getString("cerrarSituacionDialog.text")); // NOI18N
        cerrarSituacionDialog.setName("cerrarSituacionDialog"); // NOI18N
        cerrarSituacionDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarSituacionDialogActionPerformed(evt);
            }
        });

        guardarParteEspecial.setAction(actionMap.get("GuardarParteMasivo")); // NOI18N
        guardarParteEspecial.setIcon(resourceMap.getIcon("guardarParteEspecial.icon")); // NOI18N
        guardarParteEspecial.setText(resourceMap.getString("guardarParteEspecial.text")); // NOI18N
        guardarParteEspecial.setName("guardarParteEspecial"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        empleadoText.setFont(resourceMap.getFont("empleadoText.font")); // NOI18N
        empleadoText.setText(resourceMap.getString("empleadoText.text")); // NOI18N
        empleadoText.setName("empleadoText"); // NOI18N

        botonBuscarEmpleado.setAction(actionMap.get("mostrarBusquedaEmpleado")); // NOI18N
        botonBuscarEmpleado.setIcon(resourceMap.getIcon("botonBuscarEmpleado.icon")); // NOI18N
        botonBuscarEmpleado.setText(resourceMap.getString("botonBuscarEmpleado.text")); // NOI18N
        botonBuscarEmpleado.setName("botonBuscarEmpleado"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        fechaChoose.setName("fechaChoose"); // NOI18N

        isHasta.setText(resourceMap.getString("isHasta.text")); // NOI18N
        isHasta.setName("isHasta"); // NOI18N
        isHasta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                isHastaItemStateChanged(evt);
            }
        });

        hastaDateChoose.setEnabled(false);
        hastaDateChoose.setName("hastaDateChoose"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        txtNumeroParte.setText(resourceMap.getString("txtNumeroParte.text")); // NOI18N
        txtNumeroParte.setName("txtNumeroParte"); // NOI18N

        txtNumeroParte2.setText(resourceMap.getString("txtNumeroParte2.text")); // NOI18N
        txtNumeroParte2.setName("txtNumeroParte2"); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(53, 53, 53)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(empleadoText, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonBuscarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(fechaChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                        .addComponent(isHasta)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNumeroParte, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hastaDateChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(comboObrasParteMasivo, 0, 455, Short.MAX_VALUE)))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 378, Short.MAX_VALUE)
                        .addComponent(txtNumeroParte2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(guardarParteEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cerrarSituacionDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cerrarSituacionDialog, guardarParteEspecial});

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {fechaChoose, hastaDateChoose});

        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(botonBuscarEmpleado)
                    .addComponent(empleadoText, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(comboObrasParteMasivo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7)
                    .addComponent(isHasta)
                    .addComponent(hastaDateChoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaChoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNumeroParte2, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel8)
                        .addComponent(txtNumeroParte, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cerrarSituacionDialog)
                    .addComponent(guardarParteEspecial))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {botonBuscarEmpleado, comboObrasParteMasivo, empleadoText, fechaChoose, hastaDateChoose, txtNumeroParte, txtNumeroParte2});

        javax.swing.GroupLayout SituacionExtraLayout = new javax.swing.GroupLayout(SituacionExtra.getContentPane());
        SituacionExtra.getContentPane().setLayout(SituacionExtraLayout);
        SituacionExtraLayout.setHorizontalGroup(
            SituacionExtraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        SituacionExtraLayout.setVerticalGroup(
            SituacionExtraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        buscarEmpleado.setTitle(resourceMap.getString("buscarEmpleado.title")); // NOI18N
        buscarEmpleado.setLocationByPlatform(true);
        buscarEmpleado.setMinimumSize(new java.awt.Dimension(500, 300));
        buscarEmpleado.setModal(true);
        buscarEmpleado.setModalityType(java.awt.Dialog.ModalityType.TOOLKIT_MODAL);
        buscarEmpleado.setName("buscarEmpleado"); // NOI18N
        buscarEmpleado.setResizable(false);
        buscarEmpleado.setUndecorated(true);

        jPanel9.setBorder(new javax.swing.border.LineBorder(resourceMap.getColor("jPanel9.border.lineColor"), 1, true)); // NOI18N
        jPanel9.setName("jPanel9"); // NOI18N

        exitDialog.setIcon(resourceMap.getIcon("exitDialog.icon")); // NOI18N
        exitDialog.setText(resourceMap.getString("exitDialog.text")); // NOI18N
        exitDialog.setName("exitDialog"); // NOI18N
        exitDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitDialogActionPerformed(evt);
            }
        });

        selectRow.setIcon(resourceMap.getIcon("selectRow.icon")); // NOI18N
        selectRow.setText(resourceMap.getString("selectRow.text")); // NOI18N
        selectRow.setName("selectRow"); // NOI18N
        selectRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectRowActionPerformed(evt);
            }
        });

        botonQuery.setAction(actionMap.get("buscarEmpleado")); // NOI18N
        botonQuery.setIcon(resourceMap.getIcon("botonQuery.icon")); // NOI18N
        botonQuery.setToolTipText(resourceMap.getString("botonQuery.toolTipText")); // NOI18N
        botonQuery.setName("botonQuery"); // NOI18N

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        listaEmpleados.setModel(listEmpl);
        listaEmpleados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaEmpleados.setName("listaEmpleados"); // NOI18N
        listaEmpleados.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                listaEmpleadosFocusGained(evt);
            }
        });
        jScrollPane5.setViewportView(listaEmpleados);

        findEmpleadoText.setName("findEmpleadoText"); // NOI18N
        findEmpleadoText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                findEmpleadoTextFocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                    .addComponent(findEmpleadoText, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(botonQuery, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(selectRow))
                    .addComponent(exitDialog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel9Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botonQuery, exitDialog, selectRow});

        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(botonQuery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(selectRow, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(exitDialog)
                        .addGap(94, 94, 94))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(findEmpleadoText, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel9Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {exitDialog, selectRow});

        jPanel9Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {botonQuery, findEmpleadoText});

        javax.swing.GroupLayout buscarEmpleadoLayout = new javax.swing.GroupLayout(buscarEmpleado.getContentPane());
        buscarEmpleado.getContentPane().setLayout(buscarEmpleadoLayout);
        buscarEmpleadoLayout.setHorizontalGroup(
            buscarEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
        );
        buscarEmpleadoLayout.setVerticalGroup(
            buscarEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void cerrarLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarLoginActionPerformed
        loginDialogo.dispose();
}//GEN-LAST:event_cerrarLoginActionPerformed
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        configurarConnDialog.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void findEmpleadoTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_findEmpleadoTextFocusGained

         buscarEmpleado.getRootPane().setDefaultButton(botonQuery);     
}//GEN-LAST:event_findEmpleadoTextFocusGained

    private void listaEmpleadosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_listaEmpleadosFocusGained

         buscarEmpleado.getRootPane().setDefaultButton(selectRow);     
}//GEN-LAST:event_listaEmpleadosFocusGained

    private void exitDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitDialogActionPerformed

         buscarEmpleado.dispose();
         findEmpleadoText.setText("");
         listEmpl.removeAllElements();     

}//GEN-LAST:event_exitDialogActionPerformed

    private void selectRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectRowActionPerformed

         if (listEmpl.isEmpty() || listaEmpleados.isSelectionEmpty()) {
             //nothing
         } else {
             empleadoMasivo = ((Operario) listaEmpleados.getSelectedValue());
             empleadoText.setText(empleadoMasivo.toString());
             buscarEmpleado.dispose();
             findEmpleadoText.setText("");
             listEmpl.removeAllElements();
         }
     }//GEN-LAST:event_selectRowActionPerformed

    private void cerrarSituacionDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarSituacionDialogActionPerformed

        resetearParteDiarioMasivo();
         SituacionExtra.dispose();     
}//GEN-LAST:event_cerrarSituacionDialogActionPerformed

    private void isHastaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_isHastaItemStateChanged
        if(isHasta.isSelected()){
            hastaDateChoose.setEnabled(true);
        }else{
            hastaDateChoose.setEnabled(false);
        }
    }//GEN-LAST:event_isHastaItemStateChanged

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        panelAlarmas.setVisible(false);
}//GEN-LAST:event_jButton2ActionPerformed

    private void userText1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userText1FocusGained
        loginDialogo.getRootPane().setDefaultButton(botonLogin);
    }//GEN-LAST:event_userText1FocusGained

    private void loginDialogoWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_loginDialogoWindowActivated
        userText1.requestFocusInWindow();
    }//GEN-LAST:event_loginDialogoWindowActivated

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonGrandeLogin;
    private javax.swing.JMenu EmpleadosMenu;
    private javax.swing.JDialog SituacionExtra;
    private javax.swing.JMenuItem backupDB;
    private javax.swing.JButton botonBuscarEmpleado;
    private javax.swing.JButton botonGrandeSalir;
    private javax.swing.JButton botonLogin;
    private javax.swing.JButton botonQuery;
    private javax.swing.JButton btnDescargarVersion;
    private javax.swing.JDialog buscarEmpleado;
    private javax.swing.JButton cerrarLogin;
    private javax.swing.JButton cerrarSituacionDialog;
    private javax.swing.JButton clearText;
    private javax.swing.JComboBox comboObrasParteMasivo;
    private javax.swing.JMenuItem configurarConexion;
    private javax.swing.JDialog configurarConnDialog;
    private javax.swing.JTextField dbText;
    private javax.swing.JLabel empleadoText;
    private javax.swing.JButton exitDialog;
    private com.toedter.calendar.JDateChooser fechaChoose;
    private javax.swing.JTextField findEmpleadoText;
    private javax.swing.JMenuItem gestionarEquipos;
    private javax.swing.JMenuItem gestionarMenu;
    private javax.swing.JButton guardarDatos;
    private javax.swing.JButton guardarParteEspecial;
    private com.toedter.calendar.JDateChooser hastaDateChoose;
    private javax.swing.JTextField hostText;
    private javax.swing.JMenuItem informesAntesMenu;
    private javax.swing.JMenuItem iniciarSesion;
    private javax.swing.JCheckBox isHasta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblVersion;
    private javax.swing.JList listaEmpleados;
    private javax.swing.JDialog loginDialogo;
    private javax.swing.JLabel loginOut;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuGenerarInforme;
    private javax.swing.JMenuItem menuUsuarios;
    private javax.swing.JTextArea observacionesTexto;
    private javax.swing.JLabel outText;
    private javax.swing.JPanel panelAlarmas;
    private javax.swing.JPasswordField passText;
    private javax.swing.JPasswordField passText1;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JMenuItem removerParteMenu;
    private javax.swing.JButton selectRow;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTextField txtNumeroParte;
    private javax.swing.JTextField txtNumeroParte2;
    private javax.swing.JTextField userText;
    private javax.swing.JTextField userText1;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
    private JDRemoverParte removerParte;
    //private JDialog ingresarEquipo;
    private JDEquipos modificarEquipo;
    private JDObrasGestion obrasDialog;
    private JDEmpleadoGestion gestionOperario;
    private JDParteDiario parteIngreso;
    private JDReportesAnteriores informesAnteriores;
    private JDEstacionServicio estacionServicio;
    private JDOrdenTrabajo ordenDeTrabajo;
    private JDAlarmas alarmaDialog;
    private JDRI reqInt;
    private JDSemaforos semaforosDialog;
    private UsuariosDialog usuarios;
    LeerXML configDB = new LeerXML();
    static Conexion conn;
    static Connection link2db;
    public static boolean inicio = false;
    static public DefaultComboBoxModel dcbm;
    static public DefaultComboBoxModel centroDeCostoMasivo= new DefaultComboBoxModel();
    private JDReportes generarInforme;
    private Operario empleadoMasivo= new Operario();
    DefaultListModel listEmpl = new DefaultListModel();
    private static TablaAlarmasModel modelAlarma = new TablaAlarmasModel();
    private String resultBKP= "";
    private boolean alarmasDialogo = true;
}
