/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Conexion;


import java.io.*;
//import org.jdom.*;
//import org.jdom.output.*;
import org.jdom2.*;
import org.jdom2.output.XMLOutputter;
/**
 *
 * @author matuuar
 */
public class CrearXML {

    public CrearXML(String file) {


        
        Element datos = new Datos("default");
        Document doc = new Document(datos);//Creamos el documento
        try {
            XMLOutputter out = new XMLOutputter();
            FileOutputStream salida = new FileOutputStream(file);
            out.output(doc, salida);
            salida.flush();
            salida.close();
            //out.output(doc, System.out);
        } catch (Exception e) {

        }
    }
}

