
package Negocio;

import Modelo.Conductor;
import Modelo.Conexion;
import java.util.ArrayList;

public class NegocioConductor {
    Conexion conect = new Conexion();
     ArrayList<Conductor> listadoConductores = new ArrayList();
     
    
    public ArrayList<Conductor> listarConductores(){
        return conect.listarConductores();
    }
    
    public Conductor buscarConductor(int identificacion){
        return conect.buscarConductor(identificacion);
    }
    
    public String registrarConductor(Conductor conductor){
        String respuesta;
        System.out.println(conductor.getIdentificacionConductor());
        Conductor conductorValidador = this.buscarConductor(conductor.getIdentificacionConductor());
        if(conductorValidador == null){
            
            boolean registrado = conect.registrarConductor(conductor);
        
            if(registrado){
                respuesta = "1";
            }
            else{
                respuesta = "0";
            }
        }
        else{
            
            respuesta = "2";
            
        }
        
        return respuesta;
    }
}
