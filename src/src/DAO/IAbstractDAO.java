/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.EntidadAbstracta;
import java.util.ArrayList;

/**
 *
 * @author m4tuu
 */
public interface IAbstractDAO extends IBaseDAO {
    
    public ArrayList<EntidadAbstracta> cargarTodos();
    public ArrayList<EntidadAbstracta> filtrarPorTexto(String text);
    public int count(String name);
    
}
