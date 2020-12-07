
package Negocio;

import Modelo.Factura;
import Modelo.Conexion;
import Modelo.Vehiculo;
import Negocio.NegocioVehiculo;
import Negocio.ReglasNegocio;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NegocioFactura {
    
    public String registrarIngreso(String placa){
        String respuesta;
        int cuposOcupados = new NegocioVehiculo().determinarCuposFijos();
        Factura factura = new Factura();
        Vehiculo vehiculo = new NegocioVehiculo().buscarVehiculo(placa);
        if(vehiculo != null){
            if(vehiculo.getTipoContrato().equals("mensual") || ReglasNegocio.CAPACIDADMAXIMAPARQUEO - cuposOcupados > 0 ){
                if(!this.buscarIngreso(placa)){
                    
                    LocalDateTime horaActual = LocalDateTime.now();
                    factura.setFechaIngreso(horaActual.withNano(0).toString());
                    factura.setEstadoFactura("en proceso");
                    factura.setIdVehiculoFactura(vehiculo.getIdVehiculo());

                    boolean registroIngreso = new Conexion().registrarIngreso(factura);
                    if(registroIngreso){
                        respuesta = "1";
                    }
                    else{
                        respuesta = "0";
                    }
                }
                else{
                    respuesta = "3";
                }
            }
            else{
                respuesta = "4";
            }
           
        }
        else{
            respuesta = "2";
        }
        
        
            
        return respuesta;
    }
    
    public boolean buscarIngreso(String placa){
        
        boolean respuesta = new Conexion().buscarIngreso(placa);
              
        return respuesta;
    }
    
}
