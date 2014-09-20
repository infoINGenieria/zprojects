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
public interface IAbstractDAO {
    public void conectar();
    public int guardar(EntidadAbstracta entidad);
    public int modificar(EntidadAbstracta entidad);
    public boolean eliminar(EntidadAbstracta entidad);
    public ArrayList<EntidadAbstracta> cargarTodos();
    public ArrayList<EntidadAbstracta> filtrarPorTexto(String text);
}
