
package Negocio;

import Modelo.Factura;
import Modelo.Conexion;
import Modelo.Vehiculo;
import Negocio.NegocioVehiculo;
import Negocio.ReglasNegocio;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
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
                if(this.buscarIngreso(placa) == null){
                    
                    LocalDateTime horaActual = LocalDateTime.now();
                    factura.setFechaIngreso(horaActual.withNano(0).toString());
                    factura.setEstadoFactura("en proceso");
                    factura.setIdVehiculoFactura(vehiculo.getIdVehiculo());

                    boolean registroIngreso = new Conexion().registrarIngreso(factura);
                    if(registroIngreso){
                        //ingresa el vehiculo
                        respuesta = "1";
                    }
                    else{
                        //ocurre un error
                        respuesta = "0";
                    }
                }
                else{
                    //ya el auto esta dentro
                    respuesta = "3";
                }
            }
            else{
                //sin espacios para nuevos vehiculos
                respuesta = "4";
            }
           
        }
        else{
            respuesta = "2";
        }
        
        
            
        return respuesta;
    }
    
    public Factura buscarIngreso(String placa){
        
        Factura factura = new Conexion().buscarIngreso(placa);
              
        return factura;
    }
    
    public String registrarSalida(String placa){
        
        String respuesta ;
        Factura factura = this.buscarIngreso(placa);
        
        if(factura != null){
            LocalDateTime ingreso = LocalDateTime.parse(factura.getFechaIngreso());
            LocalDateTime salida = LocalDateTime.now().withNano(0);
            factura.setFechaSalida(salida.withNano(0).toString());
            
            Vehiculo vehiculo = new NegocioVehiculo().buscarVehiculo(placa);
            if(vehiculo.getTipoContrato().equals("mensual")){
                factura.setTotalPagar(0);
            }
            else{
                long diferenciaFechas = salida.toEpochSecond(ZoneOffset.UTC) - ingreso.toEpochSecond(ZoneOffset.UTC);
                long totalPagar =  ((diferenciaFechas / 60)+1) * ReglasNegocio.VALORMINUTOPARQUEO;
                System.out.println(totalPagar);
                factura.setTotalPagar((int) totalPagar);
            }
            factura.setEstadoFactura("pagada");
            factura.setIdVehiculoFactura(vehiculo.getIdVehiculo());
            boolean registroSalida = new Conexion().registrarSalida(factura);
            if(registroSalida){
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
    
    
    public ArrayList<ArrayList> listarParqueados(){
         
        return new Conexion().listarParqueados();
    }
}
