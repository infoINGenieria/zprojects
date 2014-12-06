/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Frame;
import java.util.ArrayList;

/**
 *
 * @author matuu
 */
public class Mensajes {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int INFO = 2;
    public static int contador = 0;
    public static ArrayList<MensajeDialogo> mensajesArray= new ArrayList<MensajeDialogo>();
    

    public static void MostrarMensajeModal(final Frame parent, String titulo, String mensaje, int tipo){
        final String tit = titulo;
        final String msj = mensaje;
        final int tip = tipo;
//        MensajeDialogo m = new MensajeDialogo(PayunApp.getApplication().getMainFrame(), false);
//        m.MostrarMensaje(tit, msj, tip);
//        contador++;
        
        Thread mostrarMensaje = new Thread()
        {
            @Override
            public void run()
            {
                MensajeDialogo msg = new MensajeDialogo(parent, false, tit, msj, tip);
                if(mensajesArray.indexOf(msg) == -1){
                    mensajesArray.add(contador, msg);
                    mensajesArray.get(contador).MostrarMensaje();
                    contador++;
                    try
                    {
                        Thread.sleep(5000);
                        Mensajes.contador--;
                    }
                    catch ( InterruptedException e ) {
                        Mensajes.contador--;
                    }
                    mensajesArray.get(contador).dispose();
                    mensajesArray.remove(contador);
                }
            }
        };
        mostrarMensaje.start();
        
    }


}
