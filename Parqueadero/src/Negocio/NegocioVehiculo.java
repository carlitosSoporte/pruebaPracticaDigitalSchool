
package Negocio;

import Modelo.Conexion;
import Modelo.Vehiculo;
import java.util.ArrayList;

public class NegocioVehiculo {
    
    Conexion conexion = new Conexion();
    
    public ArrayList<Vehiculo> listarVehiculos(){
        return conexion.listarVehiculos();
    }
}
