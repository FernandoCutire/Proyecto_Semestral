import java.lang.reflect.Array;
import java.util.Scanner;

public class Restaurante {
  private int codigo, cantidad, prod, indice;
  private double precio, descuento, total, calculoJubi, acumuladoJubi = 0, ingreso;
  private char size, caramel, old;
  private String name;

  double precioArreglo[] = new double[11];
  int cantArreglo[] = new int[11];
  String nameArreglo[] = new String[11];
  double totalArreglo[] = new double[11];
  double consultasArreglo[] = new double[6];

  public void InicializarArreglo() {
    for(indice=0; indice>=6; indice++) {
      consultasArreglo[indice] = 0;
    }
  }

  public void FijarValores(int id, int num, double total1, int orden, char jubilado) {
    codigo = id;
    cantidad = num;
    total = total1;
    prod = orden;
    old = jubilado;
    calculoJubi = 1;
    if(old == 's' || old == 'S') {
      calculoJubi = 0.80;
    }
  }

  public void AsignarPrecio() {
    switch (codigo) {
      case 1:
        name = "Arroz con pollo";
        precio = 1.25;
        consultasArreglo[0] = consultasArreglo[0] + (precio * cantidad * calculoJubi);
        break;
      case 2:
        name = "Caviar Almas";
        precio = 2.00;
        consultasArreglo[0] = consultasArreglo[0] + (precio * cantidad * calculoJubi);
        break;
      case 3:
        name = "Sancocho";
        precio = 3.00;
        consultasArreglo[0] = consultasArreglo[0] + (precio * cantidad * calculoJubi);
        break;
      case 4:
        name = "Hongos Matsutake";
        precio = 2.50;
        consultasArreglo[1] = consultasArreglo[1] + (precio * cantidad * calculoJubi);
        break;
      case 5:
        name = "Arroz con guandú";
        precio = 1.30;
        consultasArreglo[2] = consultasArreglo[2] + (precio * cantidad * calculoJubi);
        break;
      case 6:
        name = "Trufas blancas";
        precio = 2.00;
        consultasArreglo[2] = consultasArreglo[2] + (precio * cantidad * calculoJubi);
        break;
      case 7:
        name = "Arroz con leche";
        precio = 2.75;
        consultasArreglo[2] = consultasArreglo[2] + (precio * cantidad * calculoJubi);
        break;
      default:
        System.out.println("Error, introduzca opciones correctas");
        break;
    }
  }

  public double AcumularPrecio() {
    //Va actualizando la variable total, con el precio (total) de cada articulo, cada vez que itera el ciclo while de la linea 178-205
    total = total + (precio * cantidad);
    return total;
  }

  public void AsignarArray() {
    //La variable 'prod' se refiere al numero del producto que se esta facturando
    //Al implementarla podemos hacer que, en ese espacio del arreglo, almacenene datos como: nombre, precio por unidad, cantidad y precio total del producto
    precioArreglo[prod] = precio;
    cantArreglo[prod] = cantidad;
    nameArreglo[prod] = name;
    totalArreglo[prod] = precio * cantidad;
  }

  public double AplicarDescuento() {
    if(old == 's' || old == 'S') {
      descuento = total * 0.20;
      total = total * 0.80;
      acumuladoJubi = acumuladoJubi + descuento;
    }
    return acumuladoJubi;
  }

  public void ImprimirFactura() {
    int a =0;
    System.out.println("\n     FACTURA - RESTAURANTE MARK");
    System.out.println("Cantidad     Precio      Producto");
    while((a-1) < prod){
      System.out.printf("%d x %.2f     %.2f      %s\n", cantArreglo[a], precioArreglo[a], totalArreglo[a], nameArreglo[a]);
      a = a +1;
    }
    System.out.println("----------------------------------------");
    if(old == 's' || old == 'S') {
      System.out.printf("DESCUENTO(-20%%): %.2f dólares\n", descuento);
    }
    System.out.printf("TOTAL: %.2f dólares \n\n", total);
  }

  public double[] RetornarArreglo() {
    return consultasArreglo;
  }

  public double CalcularRecaudado() {
    prod = 0;
    while(prod < 6){
      ingreso = ingreso + consultasArreglo[prod];
      prod = prod + 1;
    }
    return ingreso;
  }

  public static void main(String args[]) {
    // Todas las variables de agrandar y acaramelar han sido eliminadas
    int id, num, orden, consulta;
    double total1, recaudado = 0, descJubi = 0;
    double[] totalesProductos;
    char caja, producto, jubilado, reporte;
    caja = 's';
    jubilado = ' ';
    Scanner sc = new Scanner(System.in);
    Restaurante men = new Restaurante();
    men.InicializarArreglo();

    while(caja == 's' || caja == 'S') {
      producto = 's';
      total1 = 0.00;
      orden = 0;
      System.out.println("\n         RESTAURANTE LA MARKA ");
      System.out.println("                   MENÚ \n");
      System.out.println("-Código-        -Producto-               -Precio-");
      System.out.println("   1         Arroz con pollo               $1.25");
      System.out.println("   2         Caviar almas                  $2.00");
      System.out.println("   3         Sancocho                      $3.00");
      System.out.println("   4         Hongos Matsutake              $2.50");
      System.out.println("   5         Arroz con guandú              $1.30");
      System.out.println("   6         Trufas Blancas                $2.00");
      System.out.println("   7         Arroz con leche               $2.75");


      System.out.print("\nJubilado (S/N): ");
      jubilado = sc.next().charAt(0);

      while(producto == 's' || producto == 'S') {
        System.out.print("\nCódigo del producto: ");
        id = sc.nextInt();
        System.out.print("Cantidad del producto: ");
        num = sc.nextInt();

        // Aqui empieza a fijar los valores y pregunta si desea añadir otro producto
        men.FijarValores(id, num, total1, orden, jubilado);
        men.AsignarPrecio();
        total1 = men.AcumularPrecio();
        men.AsignarArray();
        System.out.print("¿Añadirá otro producto? S/N: ");
        producto = sc.next().charAt(0);
        orden = orden + 1;
      }
      // Aquí calcula el descuento al jubilado
      descJubi = men.AplicarDescuento();
      men.ImprimirFactura();
      System.out.print("¿Atenderá a otro cliente? S/N: ");
      caja = sc.next().charAt(0);
    }
    System.out.print("¿Desea revisar los reportes del día? (S/N): ");
    reporte = sc.next().charAt(0);
    totalesProductos = men.RetornarArreglo();
    recaudado = men.CalcularRecaudado();
    while(reporte == 's' || reporte == 'S') {
      System.out.println("\n1 - Total recaudado por tipo de producto");
      System.out.println("2 - Total recaudado y descuentos a jubilados");
      System.out.println("3 - Porcentaje de aportes a las ventas");
      System.out.print("Ingrese el número correspondiente al reporte que quiere revisar: ");
      consulta = sc.nextInt();
      switch (consulta) {
        case 1:
          System.out.println("\n-----TOTAL RECAUDADO POR TIPO DE PRODUCTO----- \n");
          System.out.printf("- Arroz con pollo: %.2f dólares\n", totalesProductos[0]);
          System.out.printf("- Caviar almas: %.2f dólares. \n", totalesProductos[1]);
          System.out.printf("- Hongos Matsutake: %.2f dólares. \n", totalesProductos[2]);
          System.out.printf("- Nuez Macadamia: %.2f dólares. \n", totalesProductos[3]);
          System.out.printf("- Sancocho: %.2f dólares. \n", totalesProductos[4]);
          System.out.printf("- Marañon: %.2f dólares. \n", totalesProductos[5]);
          System.out.printf("- Trufas blancas : %.2f dólares. \n", totalesProductos[6]);
          break;

        case 2:
          System.out.println("\n-----TOTAL RECAUDADO Y DESCUENTO A JUBILADOS----- \n");
          System.out.printf("- Total recaudado: %.2f dólares\n", recaudado);
          System.out.printf("- Total del descuento dado a jubilados: %.2f dólares\n", descJubi);
          break;

        case 3:
          System.out.println("\n-----PORCENTAJE DE APORTES A LAS VENTAS----- \n");
          System.out.printf("- Arroz con pollo: %.2f%% \n", (totalesProductos[0] * 100 / recaudado));
          System.out.printf("- Caviar almas: %.2f%% \n", (totalesProductos[1] * 100 / recaudado));
          System.out.printf("- Sancocho: %.2f%% \n", (totalesProductos[2] * 100 / recaudado));
          System.out.printf("- Hongos Matsutake: %.2f%% \n", (totalesProductos[3] * 100 / recaudado));
          System.out.printf("- Arroz con guandú: %.2f%% \n", (totalesProductos[4] * 100 / recaudado));
          System.out.printf("- Trufas blancas: %.2f%% \n", (totalesProductos[5] * 100 / recaudado));
          System.out.printf("- Arroz con leche: %.2f%% \n", (totalesProductos[6] * 100 / recaudado));

          break;
      }
      System.out.print("\n¿Desea revisar otro reporte? (S/N):  ");
      reporte = sc.next().charAt(0);
    }
    System.out.println("\nHasta luego...");
    sc.close();
  }
}
