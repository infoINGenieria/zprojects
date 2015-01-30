/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author m4tuu
 */
@Entity
@Table(name = "precio_historico")
public class PrecioHistorico extends EntidadAbstracta {

    private int id;
    private Date fechaAlta, fechaBaja;
    private double valor;
    private TipoCosto tipo;
    private FamiliaEquipo familia;

    public PrecioHistorico() {
    }

    public PrecioHistorico(FamiliaEquipo familia, TipoCosto tipo, Date fechaAlta, double valor) {
        this.familia = familia;
        this.tipo = tipo;
        this.fechaAlta = fechaAlta;
        this.valor = valor;
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

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @ManyToOne
    public FamiliaEquipo getFamilia() {
        return familia;
    }

    public void setFamilia(FamiliaEquipo familia) {
        this.familia = familia;
    }

    @ManyToOne
    public TipoCosto getTipo() {
        return tipo;
    }

    public void setTipo(TipoCosto tipo) {
        this.tipo = tipo;
    }

    
    @Override
    public boolean validate() {
        if(fechaAlta == null)
            addError("La fecha de alta debe ser establecida.");
        if (tipo == null) 
            addError("El tipo debe ser establecido.");
        if (valor <= 0)
            addError("El valor debe ser un valor mayor o igual a 0.");
        return error.isEmpty();
    }

    @Override
    public String toString() {
        return "PrecioHistorico{" + "tipo=" + tipo.getTipoLabel() + ", familia=" + familia + '}';
    }
    
    
}
