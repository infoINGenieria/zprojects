/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO.Conexion;

import org.jdom2.Element;

/**
 *
 * @author matuuar
 */
public class Datos extends Element{
  public Datos(String name) {
    super("datos");
    addContent(new Element("dbname").setText(""));
    addContent(new Element("dbhost").setText(""));
    addContent(new Element("dbuser").setText(""));
    addContent(new Element("dbpass").setText(""));
  }
}