/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author m4tuu
 */
public class Parametro {
    
    String clave, claveGrupo, valor;

    public Parametro() {
    }

    public Parametro(String clave, String valor) {
        this.clave = clave;
        this.valor = valor;
    }

    public Parametro(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClaveGrupo() {
        return claveGrupo;
    }

    public void setClaveGrupo(String claveGrupo) {
        this.claveGrupo = claveGrupo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
//    public static int diasAvisaAlarma = 20;
//    public static int diasRango_1 = 14;
//    public static int diasRango_2 = 21;
//    public static int diasRango_3 = 28;
//    public static int diasRango_4 = 45;
//    public static int minDiasRango_1 = 0;
//    public static int maxDiasRango_1 = 4;
//    public static int minDiasRango_2 = 5;
//    public static int maxDiasRango_2 = 9;
//    public static int minDiasRango_3 = 10;
//    public static int maxDiasRango_3 = 14;
//    public static int minDiasRango_4 = 15;
//    public static int maxDiasRango_4 = 99;
//    public static String talle_1 = "S";
//    public static String talle_2 = "M";
//    public static String talle_3 = "L";
//    public static String talle_4 = "XL";
//    public static String talle_5 = "XXL";
//    
//    public static ArrayList<String> getCombo(){
//       ArrayList<String> combo = new ArrayList<String>();
//       combo.add(talle_1);
//       combo.add(talle_2);
//       combo.add(talle_3);
//       combo.add(talle_4);
//       combo.add(talle_5);
//      
//       return combo;
//    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Parametro other = (Parametro) obj;
        if ((this.clave == null) ? (other.clave != null) : !this.clave.equals(other.clave)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.clave != null ? this.clave.hashCode() : 0);
        return hash;
    }
    
    
}
