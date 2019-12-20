import java.util.Scanner;

public class Meseros {
  String nombre;
  double ventas = 0;
  Scanner input = new Scanner(System.in);

  public void asignar_mesero(){
    System.out.println("Escriba el nombre del mesero");
    nombre = (String) input.nextLine();
    System.out.println("El mesero " + nombre + " ha sido creado");
  }

//  public void venta_mesero(precio_platillo_precio){
//     ventas = ventas + precio_platillo_precio
//  }


}
