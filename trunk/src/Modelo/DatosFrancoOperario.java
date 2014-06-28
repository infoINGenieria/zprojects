/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author m4tuu
 */
public class DatosFrancoOperario {
    int id, operarioId, ajuste, pagados, solicitados1, solicitados2;
    Date entra1, entra2, sale1, sale2;

    public int getAjuste() {
        return ajuste;
    }

    public void setAjuste(int ajuste) {
        this.ajuste = ajuste;
    }

    public Date getEntra1() {
        return entra1;
    }

    public void setEntra1(Date entra1) {
        this.entra1 = entra1;
    }

    public Date getEntra2() {
        return entra2;
    }

    public void setEntra2(Date entra2) {
        this.entra2 = entra2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOperarioId() {
        return operarioId;
    }

    public void setOperarioId(int operarioId) {
        this.operarioId = operarioId;
    }

    public int getPagados() {
        return pagados;
    }

    public void setPagados(int pagados) {
        this.pagados = pagados;
    }

    public Date getSale1() {
        return sale1;
    }

    public void setSale1(Date sale1) {
        this.sale1 = sale1;
    }

    public Date getSale2() {
        return sale2;
    }

    public void setSale2(Date sale2) {
        this.sale2 = sale2;
    }

    public int getSolicitados1() {
        return solicitados1;
    }

    public void setSolicitados1(int solicitados1) {
        this.solicitados1 = solicitados1;
    }

    public int getSolicitados2() {
        return solicitados2;
    }

    public void setSolicitados2(int solicitados2) {
        this.solicitados2 = solicitados2;
    }
    
    public DatosFrancoOperario(){
        ajuste = pagados = solicitados1 = solicitados2 = 0;
    }
    
}
