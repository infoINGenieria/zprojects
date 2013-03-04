/*
 * ZilleProjectsApp.java
 */
package zilleprojects;

import java.awt.Component;
import java.util.EventObject;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;


/**
 * The main class of the application.
 */
public class ZilleProjectsApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        addExitListener(new ExitListener() {

            public boolean canExit(EventObject e) {
                boolean bOkToExit = false;
                Component source = (Component) e.getSource();
                bOkToExit = JOptionPane.showConfirmDialog(source,
                        "¿Realmente desea salir?", "Salir?", 0)
                        == JOptionPane.YES_OPTION;

                return bOkToExit;
            }

            public void willExit(EventObject event) {
            }
        });

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
