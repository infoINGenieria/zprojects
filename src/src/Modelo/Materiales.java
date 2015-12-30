/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author matuu
 */
public class Materiales extends EntidadAbstracta {
    int id, idParteDiario;
    String material, cantidad, distancia, viajes,cantera_cargadero;
    boolean ok;

    public Materiales() {
        ok=false;
    }

    public Materiales(String material) {
        this.material = material;
        ok=false;
    }
    

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
    
    public String getCantera_cargadero() {
        return cantera_cargadero;
    }

    public void setCantera_cargadero(String cantera_cargadero) {
        this.cantera_cargadero = cantera_cargadero;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getIdParteDiario() {
        return idParteDiario;
    }

    public void setIdParteDiario(int idParteDiario) {
        this.idParteDiario = idParteDiario;
    }

    

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getViajes() {
        return viajes;
    }

    public void setViajes(String viajes) {
        this.viajes = viajes;
    }

    @Override
    public boolean validate() {
        if (idParteDiario == 0) {
            error += "Un material debe estar asociado a un ParteDiario;";
        }
        return error.isEmpty();
    }
    
    
    
}
