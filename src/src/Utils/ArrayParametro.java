/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Modelo.Parametro;
import java.util.ArrayList;

/**
 *
 * @author m4tuu
 */
public class ArrayParametro extends ArrayList<Parametro> {
    
    public Parametro Find(String clave){
        if(contains(new Parametro(clave))){
            return get(indexOf(new Parametro(clave)));
        }
        return new Parametro();
    }
}
