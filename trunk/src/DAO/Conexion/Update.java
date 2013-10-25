/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Conexion;

import Utils.Download;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;


/**
 *
 * @author matuuar
 */
public class Update {
    
    public static boolean isLastVersion(LeerXML config){
        boolean isLast=true;
        try{
            Download down = new Download(new URL("http://matiasvarela.com.ar/shared/zille/version.txt"));
            down.run();   
            File f= new File("version.txt");
            if(f.canRead()){
                String contenido = getArchivo(f.getAbsolutePath());
                try{
                    double _version = Double.parseDouble(contenido);
                    if (zilleprojects.ZilleProjectsApp.VERSION < _version){
//                        Download newExceutable = new Download(new URL(
//                                "http://matiasvarela.com.ar/shared/zille/ZilleProjects.jar"),
//                                "NuevaVersion");
//                        newExceutable.run();
                        isLast = false;
                    }
                }catch (NumberFormatException e){
                }
            }
        }catch(MalformedURLException e){
            return true;
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
