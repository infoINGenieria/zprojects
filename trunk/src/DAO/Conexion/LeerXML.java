/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Conexion;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;


/**
 *
 * @author matuuar
 */
public class LeerXML {

    Conexion conn;
    private Document doc = null;

    public LeerXML() {
        conn = new Conexion();
        //CrearXML n=new CrearXML("db.xml");

    }

    public boolean readXML() {
        SAXBuilder builder = new SAXBuilder();
        try {
            File f = new File("db.xml");
            if (f.canRead()) {
                doc = builder.build(f);
            } else {
                CrearXML n = new CrearXML("db.xml");
                if (f.canRead()) {
                    doc = builder.build(f);
                } else {
                    conn.setResult(3);
                    return false;
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(LeerXML.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (JDOMException ex) {
        }

        return true;

    }

    public Conexion config() {

        conn.setWhere(readXML());
        if (conn.isWhere()) {
            Element raiz = doc.getRootElement();
            // List nodos=raiz.getChildren("datos");
            //Iterator i = raiz.iterator();
            if (raiz.getName().equals("datos")) {
                //Element e= (Element)i.next();
                //Tomo el nodo hijo, y guardo los datos en el objeto conn
                conn.setDbname(raiz.getChild("dbname").getText());
                conn.setHost(raiz.getChild("dbhost").getText());
                conn.setDbuser(raiz.getChild("dbuser").getText());
                conn.setDbpass(raiz.getChild("dbpass").getText());
            }
        } else {
            conn.setResult(3);
        }


        return conn;
    }

    public boolean escribirDatos(String db, String host, String user, String pass) {

        conn.setWhere(readXML());
        if (conn.isWhere()) {
            Element raiz = doc.getRootElement();
            // List nodos=raiz.getChildren("datos");
            //Iterator i = raiz.iterator();
            if (raiz.getName().equals("datos")) {
                //Element e= (Element)i.next();

                //Tomo el nodo hijo, y guardo los datos en el objeto conn
                raiz.getChild("dbname").setText(db);
                raiz.getChild("dbhost").setText(host);
                raiz.getChild("dbuser").setText(user);
                raiz.getChild("dbpass").setText(pass);


            }

            try {
                XMLOutputter out = new XMLOutputter();
                FileOutputStream file = new FileOutputStream("db.xml");
                out.output(doc, file);
                file.flush();
                file.close();
                //out.output(doc, System.out);
            } catch (Exception e) {
                return false;
            }

        } else {
            conn.setResult(3);
            return false;
        }
        return true;
    }
}