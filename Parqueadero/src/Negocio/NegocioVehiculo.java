
package Negocio;

import Modelo.Conductor;
import Modelo.Conexion;
import Modelo.Vehiculo;
import java.util.ArrayList;

public class NegocioVehiculo {
    
    Conexion conexion = new Conexion();
    
    public ArrayList<Vehiculo> listarVehiculos(){
        return conexion.listarVehiculos();
    }
    
    public Vehiculo buscarVehiculo(String placa){
        
        return conexion.buscarVehiculo(placa);
        
    }
    public String registrarVehiculo(Vehiculo vehiculo, int identificacion){
        
        String respuesta;
        int valorContratoMensual = 50000;
        int valorSinContrato = 0;
        
        if(this.buscarVehiculo(vehiculo.getPlacaVehiculo()) != null){
            respuesta = "3";
        }
        else{
            Conductor conductor = new NegocioConductor().buscarConductor(identificacion);
            if(conductor != null){
                vehiculo.setIdentificacionConductorVehiculo(conductor.getIdConductor());

                if(vehiculo.getTipoContrato().equals("mensual")){

                    vehiculo.setValorContrato(valorContratoMensual);
                }
                else{
                    vehiculo.setValorContrato(valorSinContrato);
                }
                boolean vehiculoRegistrado = conexion.registrarVehiculo(vehiculo);
                if(vehiculoRegistrado){
                respuesta = "1";
                }
                else{
                    respuesta = "0";
                }
            }
            else{
                respuesta = "2";
            }
        }
        return respuesta;
    }
}
