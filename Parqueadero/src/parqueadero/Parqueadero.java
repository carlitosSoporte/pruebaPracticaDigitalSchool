
package parqueadero;

import Modelo.Conductor;
import Modelo.Vehiculo;
import Negocio.NegocioConductor;
import java.util.ArrayList;
import java.util.Scanner;
import Negocio.NegocioVehiculo;


public class Parqueadero {

    
    public static void main(String[] args) {
        
        //listadoConductores();
        //buscarConductor(1111);
        //registrarConductor();
        mostrarMenuPrincipal();
        
    }
    
    public static void mostrarMenuPrincipal(){
        Scanner teclado = new Scanner(System.in);
        int opcion;
        boolean bandera = true;
        
        while(bandera){
            System.out.println("------------------------------------------------------------------");
            System.out.println("BIENVENIDO AL PARQUEADERO DE DIGITAL SCHOOL\n");
            System.out.println("                 Menu de opciones");
            System.out.println("1.Gestion de conductores");
            System.out.println("2.Gestion de vehiculos");
            System.out.println("3.Gestion de ingreso y salida");
            System.out.println("4.Salir\n");
            
            System.out.print("ingrese la opcion requerida: ");
            opcion = teclado.nextInt();
            
            System.out.println("------------------------------------------------------------------");
            switch(opcion){
                case 1:
                    gestionarConductores();
                    break;
                case 2:
                    gestionarVehiculos();
                    break;
                case 3:
                    gestionarIngresoSalida();
                    break;
                case 4:
                    bandera = false;
                    System.out.println("\nUsted ha salido del sistema.");
                    break;
            }
            
        }
    }
    
    public static void listadoConductores(){
        
        ArrayList<Conductor> listado;
        listado = new NegocioConductor().listarConductores();
        
        System.out.println("--------------------------------------------------------");
        System.out.println("LISTADO DE CONDUCTORES REGISTRADOS\n");
        
        
        for (int i = 0; i < listado.size(); i++) {
            System.out.println("ID:"+listado.get(i).getIdConductor());
            System.out.println("IDENTIFICACION:"+listado.get(i).getIdentificacionConductor());
            System.out.println("NOMBRE:"+listado.get(i).getNombreConductor());
            System.out.println("APELLIDO:"+listado.get(i).getApellidoConductor());
            System.out.println("TELEFONO:"+listado.get(i).getTelefonoConductor());
            System.out.println("EMAIL:"+listado.get(i).getEmailConductor());

            System.out.println("\n");
            
        }
        System.out.println("--------------------------------------------------------");
    }
    
    public static void buscarConductor(int identificacion){
        Conductor conductor = new NegocioConductor().buscarConductor(identificacion);
        System.out.println("---------------------------------------------------------------------");
        if(conductor!=null){
            System.out.println("CONDUCTOR ENCONTRADO\n");
            System.out.println("ID: "+conductor.getIdConductor());
            System.out.println("IDENTIFICACION: "+conductor.getIdentificacionConductor());
            System.out.println("NOMBRE: "+conductor.getNombreConductor());
            System.out.println("APELLIDO: "+conductor.getApellidoConductor());
            System.out.println("TELEFONO: "+conductor.getTelefonoConductor());
            System.out.println("EMAIL: "+conductor.getEmailConductor());
        }
        else{
            System.out.println("Conductor no encontrado...");
        }
        
        System.out.println("---------------------------------------------------------------------");
        
    }
    
    public static void registrarConductor(){
        Scanner teclado = new Scanner(System.in);
        Conductor conductor = new Conductor();
        System.out.println("----------------------------------------------------------------");
        System.out.println("REGISTRO DE UN CONDUCTOR\n");
        
        
        try{
            System.out.print("ingrese la identificacion del conductor: ");
            conductor.setIdentificacionConductor(teclado.nextInt());

            System.out.print("\nIngrese el nombre del conductor: ");
            conductor.setNombreConductor(teclado.next());

            System.out.print("\nIngrese el apellido del conductor: ");
            conductor.setApellidoConductor(teclado.next());

            System.out.print("\nIngrese el telefono del conductor: ");
            conductor.setTelefonoConductor(teclado.nextLong());

            System.out.print("\nIngrese el email del conductor: ");
            conductor.setEmailConductor(teclado.next());
            
            System.out.println("\nDATOS DEL CONDUCTOR RECOPILADOS, SE HA TRAMITADO EL REGISTRO.\n\n");
            
            String respuestaRegistro = new NegocioConductor().registrarConductor(conductor);
        
            switch(respuestaRegistro){
                case "0":
                    System.out.println("Error al registrar el conductor.");
                    break;
                case "1":
                    System.out.println("Conductor registrado correctamente.");
                    break;
                case "2":
                    System.out.println("Conductor ya registrado, valide la informacion.");
                    break;
            }
        }
        catch(Exception e){
            System.out.println("ha ocurrido un error al diligenciar la informacion ");
        }
        
        System.out.println("----------------------------------------------------------------");
    }

    private static void gestionarConductores() {
        System.out.println("\ngestionando conductores\n"); 
    }

    private static void gestionarVehiculos() {
        
        ArrayList<Vehiculo> vehiculos = new NegocioVehiculo().listarVehiculos();
        System.out.println("\n***LISTADO DE VEHICULOS REGISTRADOS EN EL TIEMPO***\n");
        for (int i = 0; i < vehiculos.size(); i++) {
            System.out.println("ID: "+vehiculos.get(i).getIdVehiculo());
            System.out.println("PLACA: "+vehiculos.get(i).getPlacaVehiculo());
            System.out.println("MARCA: "+vehiculos.get(i).getMarcaVehiculo());
            System.out.println("MODELO: "+vehiculos.get(i).getModeloVehiculo());
            System.out.println("COLOR: "+vehiculos.get(i).getColorVehiculo());
            System.out.println("TIPO DE CONTRATO: "+vehiculos.get(i).getTipoContrato());
            System.out.println("VALOR CONTRATO: "+vehiculos.get(i).getValorContrato());
            
            System.out.println("\n");
        }
        
        presionarTecla();
    }

    private static void gestionarIngresoSalida() {
        System.out.println("\ngestionando ingreso y salida\n"); 
    }
    
    static public void presionarTecla()
    {
        String seguir;
        Scanner teclado = new Scanner(System.in);
        System.out.println("\nPresione una tecla para continuar...");
        try
        {
            seguir = teclado.nextLine();
        }
        catch(Exception e)
        {}
    }
}
