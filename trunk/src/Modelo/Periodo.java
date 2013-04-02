/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author matuuar
 */
public class Periodo implements Comparable<Periodo> {
    
    String mes, año;
    int mesInt, quincena, añoInt;
    String toString;

    public Periodo(String periodoStr){
        toString = periodoStr;
        String[] arr = periodoStr.split("-");
        año = arr[1];
        añoInt = Integer.parseInt(año);
        mes = arr[0];
        if(mes.endsWith("1") || mes.endsWith("2")){
            int len = mes.length();
            quincena = Integer.parseInt(mes.substring(len-1,len));
            mes = mes.substring(0, len-1);
        }else{
            quincena = 0;
        }
        mesInt=Mes.getMes(mes);
    }
    
    @Override
    public int compareTo(Periodo otro){
        if(this.añoInt < otro.añoInt){
            return -1;
        }else if (this.añoInt > otro.añoInt){
            return 1;
        }else{
            if(this.mesInt < otro.mesInt){
                return -1;
            }else if(this.mesInt > otro.mesInt){
                return 1;
            }else{
                if(this.quincena < otro.quincena){
                    return -1;
                }else if(this.quincena > otro.quincena){
                    return 1;
                }else {
                    return 0;
                }
            
            }
        }
    }

    public String getToString() {
        return toString;
    }

    public void setToString(String toString) {
        this.toString = toString;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Periodo other = (Periodo) obj;
        if ((this.toString == null) ? (other.toString != null) : !this.toString.equals(other.toString)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.toString != null ? this.toString.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        if(quincena== 0){
            return mes+"-"+año;
        }else{
            return mes+quincena+"-"+año;
        }
        
    }
    
    
    
    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public int getAñoInt() {
        return añoInt;
    }

    public void setAñoInt(int añoInt) {
        this.añoInt = añoInt;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getMesInt() {
        return mesInt;
    }

    public void setMesInt(int mesInt) {
        this.mesInt = mesInt;
    }

    public int getQuincena() {
        return quincena;
    }

    public void setQuincena(int quincena) {
        this.quincena = quincena;
    }
    
}
