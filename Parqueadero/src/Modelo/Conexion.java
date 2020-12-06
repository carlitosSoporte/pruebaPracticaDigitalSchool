
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            //System.out.println("funciona");
           
        }
        catch(SQLException e){
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conexion;
        
    }
    
    
    public ArrayList<Conductor> listarConductores(){
        ResultSet tabla = null;
        ArrayList<Conductor> listadoConductores = new ArrayList();
        
        
        try{
            Connection conectado = this.conectar();
            PreparedStatement preparar = conectado.prepareStatement("call sp_listarConductor");
            tabla = preparar.executeQuery();
            while(tabla.next()){
                
                Conductor conductor = new Conductor();
                
                conductor.setIdConductor(tabla.getInt(1));
                conductor.setIdentificacionConductor(tabla.getInt(2));
                conductor.setNombreConductor(tabla.getString(3));
                conductor.setApellidoConductor(tabla.getString(4));
                conductor.setTelefonoConductor(tabla.getLong(5));
                conductor.setEmailConductor(tabla.getString(6));
                
                listadoConductores.add(conductor);
                 
            }
            
        }
        catch(SQLException e){
            System.out.println("error en capa de modelo,metodo listarConductores");
        }
        
        return listadoConductores;
    }
    
    public Conductor buscarConductor(int identificacion){
        
        Conductor conductor = new Conductor();
        
        try {
            Connection conectado = this.conectar();
            PreparedStatement preparar = conectado.prepareStatement("call sp_buscarConductor(?)");
            preparar.setInt(1, identificacion);
            ResultSet tabla = preparar.executeQuery();
            if(tabla.first()){
                conductor.setIdConductor(tabla.getInt(1));
                conductor.setIdentificacionConductor(tabla.getInt(2));
                conductor.setNombreConductor(tabla.getString(3));
                conductor.setApellidoConductor(tabla.getString(4));
                conductor.setTelefonoConductor(tabla.getLong(5));
                conductor.setEmailConductor(tabla.getString(6));     
            }
            else{
                conductor = null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conductor;
    }
    
    public boolean registrarConductor(Conductor conductor){
        boolean conductorInsertado = false;
        try{
            Connection conexion = this.conectar();
            PreparedStatement preparar = conexion.prepareStatement("call sp_registrarConductor(?,?,?,?,?)");
            preparar.setInt(1, conductor.getIdentificacionConductor());
            preparar.setString(2, conductor.getNombreConductor());
            preparar.setString(3, conductor.getApellidoConductor());
            preparar.setLong(4, conductor.getTelefonoConductor());
            preparar.setString(5, conductor.getEmailConductor());
            preparar.executeQuery();
            conductorInsertado = true;
            
        }
        catch(Exception e){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return conductorInsertado;
    }
    
    public ArrayList<Vehiculo> listarVehiculos(){
        ArrayList<Vehiculo> vehiculos = new ArrayList();
        
        try {
            conexion = this.conectar();
            PreparedStatement preparar = conexion.prepareStatement("call sp_listarVehiculos");
            ResultSet tabla = preparar.executeQuery();
            while(tabla.next()){
                
                Vehiculo vehiculo = new Vehiculo();
                vehiculo.setIdVehiculo(tabla.getInt(1));
                vehiculo.setPlacaVehiculo(tabla.getString(2));
                vehiculo.setMarcaVehiculo(tabla.getString(3));
                vehiculo.setModeloVehiculo(tabla.getInt(4));
                vehiculo.setColorVehiculo(tabla.getString(5));
                vehiculo.setTipoContrato(tabla.getString(6));
                vehiculo.setValorContrato(tabla.getInt(7));
                vehiculos.add(vehiculo);
            
            }
        } catch (SQLException ex) {
            System.out.println("error");
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return vehiculos;
    }
    
    public boolean registrarVehiculo(Vehiculo vehiculo){
        boolean vehiculoInsertado = false;
        try{
            Connection conexion = this.conectar();
            PreparedStatement preparar = conexion.prepareStatement("call sp_registrarVehiculo(?,?,?,?,?,?,?)");
            preparar.setString(1, vehiculo.getPlacaVehiculo());
            preparar.setString(2, vehiculo.getMarcaVehiculo());
            preparar.setInt(3, vehiculo.getModeloVehiculo());
            preparar.setString(4, vehiculo.getColorVehiculo());
            preparar.setString(5, vehiculo.getTipoContrato());
            preparar.setInt(6, vehiculo.getValorContrato());
            preparar.setInt(7, vehiculo.getIdentificacionConductorVehiculo());
            preparar.executeQuery();
            vehiculoInsertado = true;
            
        }
        catch(Exception e){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return vehiculoInsertado;
    }
    
    public Vehiculo buscarVehiculo(String placa){
        Vehiculo vehiculo = new Vehiculo();
        
        try {
            Connection conectado = this.conectar();
            PreparedStatement preparar = conectado.prepareStatement("call sp_buscarVehiculo(?)");
            preparar.setString(1, placa);
            ResultSet tabla = preparar.executeQuery();
            if(tabla.first()){
                vehiculo.setIdVehiculo(tabla.getInt(1));
                vehiculo.setPlacaVehiculo(tabla.getString(2));
                vehiculo.setMarcaVehiculo(tabla.getString(3));
                vehiculo.setModeloVehiculo(tabla.getInt(4));
                vehiculo.setColorVehiculo(tabla.getString(5));
                vehiculo.setTipoContrato(tabla.getString(6));  
                vehiculo.setValorContrato(tabla.getInt(7));
                vehiculo.setIdentificacionConductorVehiculo(tabla.getInt(8));
            }
            else{
                vehiculo = null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehiculo;
    }
    
    
}
