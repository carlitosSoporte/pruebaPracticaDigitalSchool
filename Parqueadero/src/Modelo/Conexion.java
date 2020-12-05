
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    private final String baseDatos = "parqueadero";
    private final String user = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost/"+baseDatos;
    private Connection conexion = null;
    
    public Connection conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(this.url, this.user,this.password);
            System.out.println("funciona");
            conexion.close();
           
        }
        catch(SQLException e){
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexion;
        
    }
    
    
    public void listarConductores(){
        
        try{
            Connection conectado = this.conectar();
            PreparedStatement preparar = conectado.prepareStatement("select * from conductor");
            ResultSet tabla = preparar.executeQuery();
            while(tabla.next()){
                System.out.println(tabla.getString(1));
            }
        }
        catch(Exception e){
            System.out.println("error");
        }
        
    }
    
    
    
}
