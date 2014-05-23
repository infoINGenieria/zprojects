/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author m4tuu
 */
public class AbstractDAO {
    
    protected Connection conector;
    
    public void conectar() {
        try {
            conector=ConectorDB.getConector();
        }catch (Exception ex){}
    }
    
    public int count(String tabla){
        int count = 0;
        String query="";
        PreparedStatement ps;
        ResultSet rs;
        try{
            try{
                query="select count(id) as count from " + tabla + " where fechabaja is null";
                ps=conector.prepareStatement(query);
                rs=ps.executeQuery();
                
            }catch(Exception ex){
                query="select count(id) as count from " + tabla;
                ps=conector.prepareStatement(query);
                rs=ps.executeQuery();
            }
            if(rs.next()){
                count = rs.getInt("count");
            }

         rs.close();
         ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los usuarios.\n");
        }
        return count;
    }
}
