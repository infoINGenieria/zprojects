/*
 * ZilleProjectsApp.java
 */
package zilleprojects;

import DAO.AbstractHibernateDAO;
import DAO.EppEntregaDAO;
import Modelo.EPP;
import Modelo.EppEntrega;
import Modelo.EppEntregaItem;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.Set;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;


/**
 * The main class of the application.
 */
public class ZilleProjectsApp extends SingleFrameApplication {

    public static double VERSION = 1.09;
    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        addExitListener(new ExitListener() {

            @Override
            public boolean canExit(EventObject e) {
                boolean bOkToExit = false;
                Component source = (Component) e.getSource();
                bOkToExit = JOptionPane.showConfirmDialog(null,
                        "¿Realmente desea salir?", "Salir?", 0)
                        == JOptionPane.YES_OPTION;

                return bOkToExit;
            }

            @Override
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
        
        //creaItems();
        launch(ZilleProjectsApp.class, args); 
    }  
    
//    private static void creaItems()
//    {
//        try
//        {
//            EppEntregaDAO edao = new EppEntregaDAO();
//            for(int i = 0; i < 3; i++) {
//                final EppEntrega eee= new EppEntrega(7, new Date(), "Hola "+ i);
//                EppEntregaItem item = new EppEntregaItem();
//                //item.setEppEntrega(eee);
//                item.setMedida("1123123"); 
//                item.setEpp(new EPP(2));
//                item.setEppEntrega(eee);
//                EppEntregaItem item1 = new EppEntregaItem(); 
//                //item1.setEppEntrega(eee);
//                item1.setMedida("12"); 
//                item1.setEpp(new EPP(2));
//                item1.setEppEntrega(eee);
//                
//                ArrayList<EppEntregaItem> items =  new ArrayList<EppEntregaItem>();
//                items.add(item);
//                items.add(item1);
//                eee.setItems(items);
//                AbstractHibernateDAO.almacenaEntidad(eee);
//                //AbstractHibernateDAO.almacenaEntidad(item);
//                //AbstractHibernateDAO.almacenaEntidad(item1);
//                
//            }
//            
//            for(EppEntrega ee : AbstractHibernateDAO.getListaEntidades(EppEntrega.class)) {
//                System.out.println("Epp: " + ee.getFecha() +" " + ee.getObservaciones());
//                for (EppEntregaItem item : ee.getItems()) {
//                    System.out.println("   Item: " + item.getMedida());
//                }
//            }
//        }
//        catch (HibernateException he)
//        {
//            System.err.println("Ocurrió un error al agregar los usuarios...");
//            System.err.println(he.toString());
//        }
//    }
    
}
