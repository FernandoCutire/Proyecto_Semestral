public class Mesero {
  String nombre;
  double venta = 0 ;

  Mesero(String nombreMesero, double venta_mesero) {
    nombre = nombreMesero;
    venta = venta_mesero;
  }

  public Mesero(){
    nombre = "";
    venta = 0;
  }

  public String getNombre() {
    return nombre;
  }

  public double getVenta() {
    return venta;
  }



}



