/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author m4tuu
 */
public class EPPItem  implements Comparable<EPPItem>{
    EPP epp;
    EPPOperario valores;
    Operario operario;

    public EPP getEpp() {
        return epp;
    }

    public void setEpp(EPP epp) {
        this.epp = epp;
    }

    public Operario getOperario() {
        return operario;
    }

    public void setOperario(Operario operario) {
        this.operario = operario;
    }

    public EPPOperario getValores() {
        return valores;
    }

    public void setValores(EPPOperario valores) {
        this.valores = valores;
    }
   

    public String getMedida() {
        return epp.getMedida();
    }

    public void setMedida(String medida) {
        epp.setMedida(medida);
    }

    public String getNombre() {
        return epp.getNombre();
    }

    public void setNombre(String nombre) {
        epp.setNombre(nombre);
    }

//    public String getValor_combo() {
//        if(epp.tieneTalle()){
//            return valores.getValor();
//        }
//        return "";
//    }
//
//    public void setValor_combo(String valor_combo) {
//         if(epp.tieneTalle()){
//            valores.setValor(valor_combo);
//         }
//    }
//
//    public int getValor_numero() {
//         if(!epp.tieneTalle()){
//             try{
//                return Integer.getInteger(valores.getValor());
//            }
//             catch(Exception ex){
//                 return 0;
//             }
//         }
//         return 0;
//    }
//
//    public void setValor_numero(int valor_numero) {
//        if(!epp.tieneTalle()){
//             valores.setValor(valor_numero+"");
//         }
//    }
    
    public String getValorCombo(){
        if(this.epp.tieneTalle()){
            return this.valores.getValor();
        }
        return "";
    }
    
    public int getValorInt(){
        if(!this.epp.tieneTalle()){
            try{
                String dato = this.valores.getValor();
                return Integer.parseInt(dato);
            }catch(Exception ex){
                return 0;
            }
        }
        return 0;
    }
    

    public EPPItem() {
        valores = new EPPOperario();
        epp = new EPP();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EPPItem other = (EPPItem) obj;
        if ((this.epp.getNombre() == null) ? (other.epp.getNombre() != null) : !this.epp.getNombre().equals(other.epp.getNombre())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (this.epp.getNombre() != null ? this.epp.getNombre().hashCode() : 0);
        return hash;
    }

    @Override
    public int compareTo(EPPItem t) {
        return epp.getNombre().compareTo(t.getEpp().getNombre());
    }
    
    
}
