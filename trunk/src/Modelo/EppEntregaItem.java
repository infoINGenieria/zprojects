/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author m4tuu
 */
@Entity
@Table(name="epp_entrega_item")
public class EppEntregaItem extends EntidadAbstracta {
    
    private int id;   
//    @Column(name = "epp_id", nullable = false, insertable = false, updatable = false, length = 10, precision = 0)
//    private int eppId;
//    @Column(name = "epp_entrega_id", nullable = false, insertable = false, updatable = false, length = 10, precision = 0)
//    private int eppEntregaId;
    
    private String medida;
    private EppEntrega eppEntrega;
    private EPP epp;

    @ManyToOne
    @JoinColumn(name = "epp_id", nullable = false)
    public EPP getEpp() {
        return epp;
    }

    public void setEpp(EPP epp) {
        this.epp = epp;
    }
    
    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

//    public int getEppEntregaId() {
//        return eppEntregaId;
//    }
//
//    public void setEppEntregaId(int eppEntregaId) {
//        this.eppEntregaId = eppEntregaId;
//    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EppEntregaItem other = (EppEntregaItem) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
        return hash;
    }

    

    

    @ManyToOne
    @JoinColumn(name = "epp_entrega_id",nullable = false)
    public EppEntrega getEppEntrega() {
        return eppEntrega;
    }

    public void setEppEntrega(EppEntrega eppEntrega) {
        this.eppEntrega = eppEntrega;
    }

//    public int getEppEntregaId() {
//        return eppEntregaId;
//    }
//
//    public void setEppEntregaId(int eppEntregaId) {
//        this.eppEntregaId = eppEntregaId;
//    }

//    public int getEppId() {
//        return eppId;
//    }
//
//    public void setEppId(int eppId) {
//        this.eppId = eppId;
//    }

    
    
    @Override
    public boolean validate() {
        if (medida.isEmpty()) {
            error += "El campo MEDIDA es obligatorio;";
        }
//        if (eppEntregaId == 0) {
//            error += "Debe estar asociado a una entrega;";
//        }
        return error.isEmpty();
    }
    
}
