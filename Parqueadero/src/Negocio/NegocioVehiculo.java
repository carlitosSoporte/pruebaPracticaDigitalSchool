
package Negocio;

import Modelo.Conductor;
import Modelo.Conexion;
import Modelo.Vehiculo;
import java.util.ArrayList;
import Negocio.ReglasNegocio;

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
        if(this.determinarCuposFijos() < ReglasNegocio.CAPACIDADMAXIMAPARQUEO){
            if(this.buscarVehiculo(vehiculo.getPlacaVehiculo()) != null){
                respuesta = "3";
            }
            else{
                Conductor conductor = new NegocioConductor().buscarConductor(identificacion);
                if(conductor != null){
                    vehiculo.setIdentificacionConductorVehiculo(conductor.getIdConductor());

                    if(vehiculo.getTipoContrato().equals("mensual")){

                        vehiculo.setValorContrato(ReglasNegocio.VALORCONTRATO);
                    }
                    else{
                        vehiculo.setValorContrato(ReglasNegocio.VALORSINCONTRATO);
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
        }
        else{
            respuesta = "4";
        }
        
        return respuesta;
    }
    
    public int contarVehiculosMensuales(){
        return conexion.contarVehiculosMensuales();
    }
    
    public int contarParqueadosOtros(){
        return conexion.contarParqueadosOtros();
    }
    
    public int determinarCuposFijos(){
        return this.contarVehiculosMensuales() + this.contarParqueadosOtros();
    }
}
