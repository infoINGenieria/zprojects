/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author m4tuu
 */
public class Usuario extends EntidadAbstracta{
    private int id_user;
    private String user;
    private String pass;
    private String rol;

    @Override
    public int getId() {
        return id_user;
    }

    @Override
    public void setId(int id_user) {
        this.id_user = id_user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Usuario() {
        this.error = this.pass = this.user = "";
    }

    public Usuario(String user, String pass, String rol) {
        this.user = user;
        this.pass = pass;
        this.rol = rol;
    }

    public Usuario(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if ((this.user == null) ? (other.user != null) : !this.user.equals(other.user)) {
            return false;
        }
        
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (this.user != null ? this.user.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean validate(){
        error = "";
        if(this.user.isEmpty()){
            error += "El campo Nombre de usuario no puede dejarse en blanco.;";
        }
        if(this.pass.isEmpty() && this.id_user == 0){
            error += "El campo Contraseña no puede dejarse en blanco.;";
        }
        if(this.rol.isEmpty()){
            error += "El campo Rol no puede dejarse en blanco.;";
        }
        if(error.isEmpty()) return true;
        return false;
    }

    @Override
    public String toString() {
        return user;
    }

    
    
    
}
