
package parqueadero;

import Modelo.Conductor;
import Modelo.Vehiculo;
import Negocio.NegocioConductor;
import java.util.ArrayList;
import java.util.Scanner;
import Negocio.NegocioVehiculo;
import Negocio.NegocioFactura;
import Negocio.ReglasNegocio;



public class Parqueadero {

    
    public static void main(String[] args) {

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
    
    public static void listarConductores(){
        
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
    
    public static void buscarConductor(){
        
        System.out.println("***BUSCANDO CONDUCTOR***");
        Scanner teclado = new Scanner(System.in);
        try{
            System.out.print("Ingrese la identificacion del conductor a buscar: ");
            int identificacion = teclado.nextInt();
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
                System.out.println("---------------------------------------------------------------------");
                System.out.println("Conductor no encontrado...");
            }
        }
        catch(Exception e){
            System.out.println("---------------------------------------------------------------------");
            System.out.println("Ha ocurrido un error al buscar el conductor");
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
        Scanner teclado = new Scanner(System.in);
        int opcion;
        boolean bandera = true;
        
        while(bandera){
            System.out.println("------------------------------------------------------------------");
            System.out.println("Gestion de Conductores\n");
            System.out.println("                 Menu de opciones");
            System.out.println("1.Registrar conductor");
            System.out.println("2.Buscar Conductor");
            System.out.println("3.listar Conductores");
            System.out.println("4.volver al menu principal\n");
            
            System.out.print("ingrese la opcion requerida: ");
            opcion = teclado.nextInt();
            
            System.out.println("------------------------------------------------------------------");
            switch(opcion){
                case 1:
                    registrarConductor();
                    break;
                case 2:
                    buscarConductor();
                    break;
                case 3:
                    listarConductores();
                    break;
                case 4:
                    bandera = false;
                    System.out.println("\nUsted ha vuelto al menu principal.");
                    break;
            }
        }
        presionarTecla();
    }

    private static void gestionarVehiculos() {
        
        Scanner teclado = new Scanner(System.in);
        int opcion;
        boolean bandera = true;
        
        while(bandera){
            System.out.println("------------------------------------------------------------------");
            System.out.println("Gestion de Vehiculos\n");
            System.out.println("                 Menu de opciones");
            System.out.println("1.Registrar vehiculo");
            System.out.println("2.Buscar vehiculo");
            System.out.println("3.listar vehiculos");
            System.out.println("4.volver al menu principal\n");
            
            System.out.print("ingrese la opcion requerida: ");
            opcion = teclado.nextInt();
            
            System.out.println("------------------------------------------------------------------");
            switch(opcion){
                case 1:
                    registrarVehiculo();
                    break;
                case 2:
                    buscarVehiculo();
                    break;
                case 3:
                    listarVehiculos();
                    break;
                case 4:
                    bandera = false;
                    System.out.println("\nUsted ha vuelto al menu principal.");
                    break;
            }
        }
        presionarTecla();
    }
    
    private static void listarVehiculos(){
        
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
        Scanner teclado = new Scanner(System.in);
        int opcion;
        boolean bandera = true;
        
        while(bandera){
            System.out.println("------------------------------------------------------------------");
            System.out.println("***Gestion de Ingreso y salida de vehiculos***\n");
            System.out.println("                 Menu de opciones");
            System.out.println("1.Registrar ingreso vehiculo");
            System.out.println("2.Registrar salida vehiculo");
            System.out.println("3.Estadisticas Parqueadero");
            System.out.println("4.volver al menu principal\n");
            
            System.out.print("ingrese la opcion requerida: ");
            opcion = teclado.nextInt();
            
            System.out.println("------------------------------------------------------------------");
            switch(opcion){
                case 1:
                    registrarIngresoVehiculo();
                    break;
                case 2:
                    registrarSalidaVehiculo();
                    break;
                case 3:
                    mostrarMenuEstadisticas();
                    break;
                case 4:
                    bandera = false;
                    System.out.println("\nUsted ha vuelto al menu principal.");
                    break;
            }
        }
        presionarTecla();
    }
    
    static public void presionarTecla(){
        String seguir;
        Scanner teclado = new Scanner(System.in);
        System.out.println("\nPresione ENTER para continuar...");
        try
        {
            seguir = teclado.nextLine();
        }
        catch(Exception e)
        {}
    }

    private static void registrarVehiculo() {
        Scanner teclado = new Scanner(System.in);
        Vehiculo vehiculo = new Vehiculo();
        System.out.println("----------------------------------------------------------------");
        System.out.println("REGISTRO DE UN VEHICULO\n");
        
        
        try{
            System.out.print("ingrese la placa del vehiculo: ");
            vehiculo.setPlacaVehiculo(teclado.next());

            System.out.print("\nIngrese la marca del vehiculo: ");
            vehiculo.setMarcaVehiculo(teclado.next());

            System.out.print("\nIngrese el modelo del vehiculo: ");
            vehiculo.setModeloVehiculo(teclado.nextInt());

            System.out.print("\nIngrese el color del vehiculo: ");
            vehiculo.setColorVehiculo(teclado.next());

            System.out.print("\nIngrese 1 para contrato 'menusal' ó 2 para 'sin contrato' : ");
            int opcion = teclado.nextInt();
            if(opcion == 1){
                vehiculo.setTipoContrato("mensual");
            }
            else{
                vehiculo.setTipoContrato("sin contrato");
            }
            
            System.out.print("\nIngrese la identificacion del conductor: ");
            int identificacion = teclado.nextInt();
            
            System.out.println("\nDATOS DEL VEHICULO RECOPILADOS, SE HA TRAMITADO EL REGISTRO.\n\n");
            
            String respuestaRegistro = new NegocioVehiculo().registrarVehiculo(vehiculo,identificacion);
        
            switch(respuestaRegistro){
                case "0":
                    System.out.println("No se puede insertar el registro, Error.");
                    break;
                case "1":
                    System.out.println("Vehiculo registrado correctamente.");
                    break;
                case "2":
                    System.out.println("Conductor referenciado no registrado, valide la informacion");
                    break;
                case "3":
                    System.out.println("Vehiculo con placa "+vehiculo.getPlacaVehiculo()+" ya existe.");
                    break;
                    
                case "4":
                    System.out.println("No quedan cupos disponibles para otro vehiculo.");
                    break;
            }
        }
        catch(Exception e){
            System.out.println("ha ocurrido un error al diligenciar la informacion ");
        }
        
        System.out.println("----------------------------------------------------------------");
    }

    private static void buscarVehiculo() {
        Scanner teclado = new Scanner(System.in);
        Vehiculo vehiculo = new Vehiculo();
        System.out.println("----------------------------------------------------------------");
        System.out.println("***BUSQUEDA DE UN VEHICULO***\n");
        
        System.out.print("ingrese la placa del vehiculo a buscar: ");
        String placa = teclado.next();
        
        vehiculo = new NegocioVehiculo().buscarVehiculo(placa);
        System.out.println("----------------------------------------------------------------");
        if(vehiculo != null){
            System.out.println("\nVehiculo encontrado:");
            System.out.println("ID: "+vehiculo.getIdVehiculo());
            System.out.println("PLACA: "+vehiculo.getPlacaVehiculo());
            System.out.println("MARCA: "+vehiculo.getMarcaVehiculo());
            System.out.println("MODELO: "+vehiculo.getModeloVehiculo());
            System.out.println("COLOR: "+vehiculo.getColorVehiculo());
            System.out.println("TIPO CONTRATO: "+vehiculo.getTipoContrato());
            System.out.println("VALOR CONTRATO: "+vehiculo.getValorContrato());
            System.out.println("ID CONDUCTOR: "+vehiculo.getIdentificacionConductorVehiculo());
        }
        else{
            System.out.println("Vehiculo con numero de placa "+placa+" no fue encontrado.");
        }
        
        System.out.println("----------------------------------------------------------------");
    }

    private static void registrarIngresoVehiculo() {
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------");
        System.out.println("***INGRESO AL PARQUEADERO***\n");
        
        try{
            System.out.print("Ingrese la placa del vehiculo que ingresará: ");
            String placa = teclado.next();
            
            String respuestaIngreso = new NegocioFactura().registrarIngreso(placa);
            switch(respuestaIngreso){
                case "0":
                    System.out.println("\nError ingresando al parqueadero");
                    break;
                case "1":
                    System.out.println("\nIngreso registrado satisfactoriamente.");
                    break;
                case "2":
                    System.out.println("\nVehiculo con numero de placa "+placa+" no registrado, debe registrarlo primero");
                    break;
                case "3":
                    System.out.println("\nEl vehiculo ya se encuentra en el parqueadero.");
                    break; 
                case "4":
                    System.out.println("\nNo quedan espacios disponibles para parquear");
                    break;
            }     
        }
        catch(Exception ex){
            System.out.println("Error diligenciando los datos");
        }
    }

    private static void registrarSalidaVehiculo() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------");
        System.out.println("***SALIDA DEL PARQUEADERO***\n");
        
        try{
            System.out.print("Ingrese la placa del vehiculo que Saldrá: ");
            String placa = teclado.next();
            
            String respuestaSalida = new NegocioFactura().registrarSalida(placa);
            switch(respuestaSalida){
                case "0":
                    System.out.println("\nError saliendo del parqueadero");
                    break;
                case "1":
                    System.out.println("\nSalida registrada satisfactoriamente.");
                    break;
                case "2":
                    System.out.println("\nVehiculo con numero de placa "+placa+" no se encuentra dentro del parqueadero");
                    break;
            }     
        }
        catch(Exception ex){
            System.out.println("Error diligenciando los datos");
        }
    }

    private static void mostrarMenuEstadisticas() {
        Scanner teclado = new Scanner(System.in);
        int opcion;
        boolean bandera = true;
        
        while(bandera){
            System.out.println("------------------------------------------------------------------");
            System.out.println("Estadisticas y listado de parqueados\n");
            System.out.println("                 Menu de opciones");
            System.out.println("1.Listar parqueados");
            System.out.println("2.Estado Parqueadero");
            System.out.println("3.volver al menu principal\n");
            
            System.out.print("ingrese la opcion requerida: ");
            opcion = teclado.nextInt();
            
            System.out.println("------------------------------------------------------------------");
            switch(opcion){
                case 1:
                    listarParqueados();
                    break;
                case 2:
                    mostrarEstadisticas();
                    break;
                case 3:
                    bandera = false;
                    System.out.println("\nUsted ha vuelto al menu principal.");
                    break;
            }
        }
        presionarTecla();
    }

    private static void listarParqueados() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("LISTADO DE AUTOS PARQUEADOS\n");
        
        ArrayList<ArrayList> listado = new ArrayList();
        listado = new NegocioFactura().listarParqueados();
        for (int i = 0; i < listado.size(); i++) {
            System.out.println("PLACA: "+listado.get(i).get(0));
            System.out.println("FECHA Y HORA DE INGRESO: "+listado.get(i).get(1));
            System.out.println("TIPO CONTRATO: "+listado.get(i).get(2));
            System.out.println("\n");
        }
        System.out.println("------------------------------------------------------------------");
    }

    private static void mostrarEstadisticas() {
        System.out.println("------------------------------------------------------------------\n");
        System.out.println("***ESTADO DEL PARQUEADERO***\n");
        System.out.println("CAPACIDAD MAXIMA PARQUEADERO: "+ReglasNegocio.CAPACIDADMAXIMAPARQUEO);
        int cuposUsados = new NegocioVehiculo().determinarCuposFijos();
        int parqueadosOtros = new NegocioVehiculo().contarParqueadosOtros();
        int parqueados = new NegocioFactura().listarParqueados().size();
        int parqueadosContrato = parqueados - parqueadosOtros;
        int contratoMensual = new NegocioVehiculo().contarVehiculosMensuales();
        System.out.println("CUPOS USADOS ENTRE PARQUEADOS Y RESERVADOS POR MENSUALIDAD: "+cuposUsados);
        System.out.println("LA CANTIDAD DE VEHICULOS CON CONTRATO MENSUAL ES DE: "+contratoMensual);
        System.out.println("CUPOS DISPONIBLES EN EL PARQUEADERO: "+(ReglasNegocio.CAPACIDADMAXIMAPARQUEO-cuposUsados));
        System.out.println("CANTIDAD DE PARQUEOS TOTAL: "+parqueados);
        System.out.println("CANTIDAD DE PARQUEOS CON CONTRATO MENSUAL: "+parqueadosContrato);
        System.out.println("CANTIDAD DE PARQUEADOS SIN CONTRATO: "+parqueadosOtros);
        System.out.println("\n------------------------------------------------------------------");
        
        
    }

}
