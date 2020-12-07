
package Modelo;

public class Factura {
    
    private int idFactura;
    private String fechaIngreso;
    private String fechaSalida;
    private String estadoFactura;
    private int totalPagar;
    private int idVehiculoFactura;

    public Factura() {
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(String estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public int getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(int totalPagar) {
        this.totalPagar = totalPagar;
    }

    public int getIdVehiculoFactura() {
        return idVehiculoFactura;
    }

    public void setIdVehiculoFactura(int idVehiculoFactura) {
        this.idVehiculoFactura = idVehiculoFactura;
    }
    
}
