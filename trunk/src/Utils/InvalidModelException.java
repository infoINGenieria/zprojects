/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author matuu
 */
public class InvalidModelException extends Exception{

    @Override
    public String getMessage() {
        return "El modelo no es válido. No se guardaron los cambios.";
    }


}
