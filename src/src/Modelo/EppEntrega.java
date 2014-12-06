/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import DAO.AbstractHibernateDAO;
import Utils.FechaUtil;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author m4tuu
 */
@Entity()
@Table(name="epp_entrega")
public class EppEntrega extends EntidadAbstracta {
    
    private int id;
    private int operarioId;
    private Date fecha;
    private String observaciones;

    private List<EppEntregaItem> items;
    
    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    @OneToMany(mappedBy = "eppEntrega", fetch= FetchType.EAGER, cascade= CascadeType.ALL)
    public List<EppEntregaItem> getItems() {
        return items;
    }

    public void setItems(List<EppEntregaItem> items) {
        this.items = items;
    }

    public EppEntrega() {
    }

    public EppEntrega(int operarioId, Date fecha, String observaciones) {
        this.operarioId = operarioId;
        this.fecha = fecha;
        this.observaciones = observaciones;
    }

    public EppEntrega(int id, int operarioId, Date fecha, String observaciones) {
        this.id = id;
        this.operarioId = operarioId;
        this.fecha = fecha;
        this.observaciones = observaciones;
    }
    
    
    
    

    @Override
    public void setId(int id) {
        this. id = id;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Column(nullable=false)
    public int getOperarioId() {
        return operarioId;
    }

    public void setOperarioId(int operarioId) {
        this.operarioId = operarioId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EppEntrega other = (EppEntrega) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

//    public Operario getOperario() {
//        return operario;
//    }
//
//    public void setOperario(Operario operario) {
//        this.operario = operario;
//    }
//    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.id;
        return hash;
    }
    
    

    @Override
    public boolean validate() {
        return true;
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ZilleProjectsPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    @Override
    public String toString() {
        return AbstractHibernateDAO.getEntidad(operarioId, Operario.class) + " (" + 
                FechaUtil.getFecha(fecha) +")";
    }
}
