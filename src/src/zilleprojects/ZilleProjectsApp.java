/*
 * ZilleProjectsApp.java
 */
package zilleprojects;

import Utils.FileManager;
import Vista.OpcionPanel;
import java.awt.Component;
import java.util.EventObject;
import javax.swing.JOptionPane;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;


/**
 * The main class of the application.
 */
public class ZilleProjectsApp extends SingleFrameApplication {

    public static double VERSION = 1.25;
    public static boolean closeDirectlly = false;
    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        addExitListener(new ExitListener() {

            @Override
            public boolean canExit(EventObject e) {
                if (!closeDirectlly) {
                    boolean bOkToExit = false;
                    Component source = (Component) e.getSource();
                    bOkToExit = JOptionPane.showConfirmDialog(null,
                            "¿Realmente desea salir?", "Salir?", 0)
                            == JOptionPane.YES_OPTION;

                    return bOkToExit;
                }
                return true;
            }

            @Override
            public void willExit(EventObject event) {
            }
        });
        
        if(!FileManager.checkUserFolder()) {
            OpcionPanel.showError("Error al crear las carpetas de usuario. \n"
                    + "Es posible que no pueda realizar exportaciones.");
        }
        show(new ZilleProjectsView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of ZilleProjectsApp
     */
    public static ZilleProjectsApp getApplication() {
        return Application.getInstance(ZilleProjectsApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        
        launch(ZilleProjectsApp.class, args); 
    }  
    
}
