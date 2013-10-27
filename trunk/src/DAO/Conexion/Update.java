/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Conexion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;

/**
 *
 * @author matuuar
 */
public class Update {

    public static boolean isLastVersion(LeerXML config) {
        boolean isLast = true;
        try {
            // Function called
            long startTime = System.currentTimeMillis();
            // Open connection
            System.out.println("Connecting...");
            URL url = new URL("http://matiasvarela.com.ar/shared/zille/version.txt");
            url.openConnection();

            // Download routine
            InputStream reader = url.openStream();
            FileOutputStream writer = new FileOutputStream("version.txt");

            byte[] buffer = new byte[1024];
            int totalBytesRead = 0;
            int bytesRead = 0;

            while ((bytesRead = reader.read(buffer)) > 0) {
                writer.write(buffer, 0, bytesRead);
                buffer = new byte[1024];
                totalBytesRead += bytesRead;
            }

            // Download finished
            long endTime = System.currentTimeMillis();

            // Output download information
            System.out.println("Done.");
            System.out.println((new Integer(totalBytesRead).toString()) + " bytes read.");
            System.out.println("It took " + (new Long(endTime - startTime).toString()) + " milliseconds.");

            // Close input and output streams
            writer.close();
            reader.close();
            File f = new File("version.txt");
            if (f.canRead()) {
                String contenido = getArchivo(f.getAbsolutePath());
                try {
                    double _version = Double.parseDouble(contenido);
                    if (zilleprojects.ZilleProjectsApp.VERSION < _version) {
                        isLast = false;
                    }
                } catch (NumberFormatException e) {
                }
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
