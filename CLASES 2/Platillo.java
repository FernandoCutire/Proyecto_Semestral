import java.util.Scanner;
import java.util.ArrayList;

public class Platillo {
  String nombre_platillo;
  int id;
  double precio_platillo;
  Scanner sc = new Scanner(System.in);

  public void crear_platillo(){
    System.out.println("Inserte el nombre del platillo.");
    nombre_platillo = sc.nextLine();
    System.out.println("Inserte el ID del platillo (1 al 7).");
    id = sc.nextInt();
    System.out.println("Inserte el precio del platillo.");
    precio_platillo = sc.nextDouble();
    mostrar_platillo();
  }


  public void mostrar_platillo(){
    System.out.println("Se ha creado un platillo nuevo: ");
    System.out.println("ID " + id + " Nombre: "+ nombre_platillo+" Precio: " + precio_platillo);
  }

}