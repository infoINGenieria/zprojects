/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Conexion;

import Utils.FileManager;
import Utils.JsonReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import org.json.JSONObject;

/**
 *
 * @author matuuar
 */
public class Update {
    public static String lastVersionDiscovery="";
    public static boolean isLastVersion(LeerXML config) {
        boolean isLast = true;
        try {
            // Function called
            long startTime = System.currentTimeMillis();
            // Open connection
            System.out.println("Connecting...");
            JSONObject json;
            json = JsonReader.readJsonFromUrl("http://matiasvarela.com.ar/api/last");
            String v = json.getString("version");
            // Download finished
            long endTime = System.currentTimeMillis();

            // Output download information
            System.out.println("Done. Last version: " + v);
            System.out.println("It took " + (new Long(endTime - startTime).toString()) + " milliseconds.");

            
            
            try {
                double _version = Double.parseDouble(v);
                if (zilleprojects.ZilleProjectsApp.VERSION < _version) {
                    isLast = false;
                    lastVersionDiscovery = v;
                }
            } catch (NumberFormatException e) {
            }
            
        } catch (Exception ex) {
        }
        return isLast;
    }

    public static String getArchivo(String ruta) {
        FileReader fr = null;
        BufferedReader br = null;
        //Cadena de texto donde se guardara el contenido del archivo
        String contenido = "";
        try {
            //ruta puede ser de tipo String o tipo File
            fr = new FileReader(ruta);
            br = new BufferedReader(fr);
            String linea;
            //Obtenemos el contenido del archivo linea por linea
            while ((linea = br.readLine()) != null) {
                contenido += linea + "\n";
            }
        } catch (Exception e) {
        } //finally se utiliza para que si todo ocurre correctamente o si ocurre
        //algun error se cierre el archivo que anteriormente abrimos
        finally {
            try {
                br.close();
            } catch (Exception ex) {
            }
        }
        return contenido;
    }
}
