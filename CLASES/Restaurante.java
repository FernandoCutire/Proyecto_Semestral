import java.util.Scanner;
import java.lang.reflect.Array;


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

  public void FijarValores(int id, int num, char agrandar, char acaramelar, double total1, int orden, char jubilado) {
    codigo = id;
    cantidad = num;
    size = agrandar;
    caramel = acaramelar;
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
      case 11:
        name = "Popcorn chico";
        precio = 1.25;
        if(caramel == 's' || caramel == 'S') {
          precio = 1.75;
        }
        consultasArreglo[0] = consultasArreglo[0] + (precio * cantidad * calculoJubi);
        break;
      case 12:
        name = "Popcorn mediano";
        precio = 2.00;
        if (caramel == 's' || caramel == 'S') {
          precio = 2.50;
        }
        consultasArreglo[0] = consultasArreglo[0] + (precio * cantidad * calculoJubi);
        break;
      case 13:
        name = "Popcorn grande";
        precio = 3.00;
        if (caramel == 's' || caramel == 'S') {
          precio = 3.50;
        }
        consultasArreglo[0] = consultasArreglo[0] + (precio * cantidad * calculoJubi);
        break;
      case 2:
        name = "Hotdog";
        precio = 2.50;
        consultasArreglo[1] = consultasArreglo[1] + (precio * cantidad * calculoJubi);
        break;
      case 31:
        name = "Refresco chico";
        precio = 1.30;
        consultasArreglo[2] = consultasArreglo[2] + (precio * cantidad * calculoJubi);
        break;
      case 32:
        name = "Refresco mediano";
        precio = 2.00;
        consultasArreglo[2] = consultasArreglo[2] + (precio * cantidad * calculoJubi);
        break;
      case 33:
        name = "Refresco grande";
        precio = 2.75;
        consultasArreglo[2] = consultasArreglo[2] + (precio * cantidad * calculoJubi);
        break;
      case 4:
        name = "Agua";
        precio = 1.50;
        consultasArreglo[3] = consultasArreglo[3] + (precio * cantidad * calculoJubi);
        break;
      case 5:
        name = "Chocolate";
        precio = 1.75;
        consultasArreglo[4] = consultasArreglo[4] + (precio * cantidad * calculoJubi);
        break;
      case 61:
        name = "Combo #1: popcorn mediano y refresco grande";
        precio = 4.50;
        if ((caramel == 's' || caramel == 'S') && (size == 's' || size == 'S')) {
          precio = 6.00;
          name = "Combo #1: popcorn grande acaramelado y refresco grande";
        }
        else if ((caramel != 's' || caramel != 'S') && (size == 's' || size == 'S')) {
          precio = 5.50;
          name = "Combo #1: popcorn grande y refresco grande";
        }
        else if ((caramel == 's' || caramel == 'S') && (size != 's' || size != 'S')) {
          precio = 5.00;
          name = "Combo #1: popcorn mediano acaramelado y refresco grande";
        }
        consultasArreglo[5] = consultasArreglo[5] + (precio * cantidad * calculoJubi);
        break;
      case 62:
        precio = 5.00;
        name = "Combo #2: Hotdog y refresco grande";
        consultasArreglo[5] = consultasArreglo[5] + (precio * cantidad * calculoJubi);
        break;
      case 63:
        precio = 6.80;
        name = "Combo #3: popcorn grande y 2 refrescos medianos";
        if ((caramel == 's' || caramel == 'S') && (size == 's' || size == 'S')) {
          precio = 8.80;
          name = "Combo #3: popcorn grande acaramelado y 2 refrescos grandes";
        }
        else if ((caramel != 's' || caramel != 'S') && (size == 's' || size == 'S')) {
          precio = 8.30;
          name = "Combo #3: popcorn grande y 2 refrescos medianos";
        }
        else if ((caramel == 's' || caramel == 'S') && (size != 's' || size != 'S')) {
          precio = 7.30;
          name = "Combo #3: popcorn grande acaramelado y 2 refrescos medianos";
        }
        consultasArreglo[5] = consultasArreglo[5] + (precio * cantidad * calculoJubi);
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
    System.out.println("\n     FACTURA - CINE UNIVERSITARIO");
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
    int id, num, orden, consulta;
    double total1, recaudado = 0, perdidoJubi = 0;
    double[] totalesProductos;
    char caja, producto, agrandar, acaramelar, jubilado, reporte;
    caja = 's';
    agrandar = ' ';
    acaramelar = ' ';
    jubilado = ' ';
    Scanner sc = new Scanner(System.in);
    Restaurante rest = new Restaurante();
    rest.InicializarArreglo();

    while(caja == 's' || caja == 'S') {
      producto = 's';
      total1 = 0.00;
      orden = 0;
      System.out.println("\n   CAFETERIA CINE UNIVERSITARIO - UTP");
      System.out.println("                   MENÚ \n");
      System.out.println("-Código-        -Producto-               -Precio-");
      System.out.println("   11         Popcorn chico               $1.25");
      System.out.println("   12         Popcorn mediano             $2.00");
      System.out.println("   13         Popcorn grande              $3.00");
      System.out.println("    2             Hotdog                  $2.50");
      System.out.println("   31         Refresco chico              $1.30");
      System.out.println("   32         Refresco mediano            $2.00");
      System.out.println("   33         Refresco grande             $2.75");
      System.out.println("    4               Agua                  $1.50");
      System.out.println("    5            Chocolate                $1.75");
      System.out.println("   61            Combo #1                 $4.50");
      System.out.println("   62            Combo #2                 $5.00");
      System.out.println("   63            Combo #3                 $6.80");

      System.out.print("\nJubilado (S/N): ");
      jubilado = sc.next().charAt(0);

      while(producto == 's' || producto == 'S') {
        System.out.print("\nCódigo del producto: ");
        id = sc.nextInt();
        System.out.print("Cantidad del producto: ");
        num = sc.nextInt();
        switch(id) {
          case 61:
            System.out.print("¿Agrandar el popcorn? ");
            agrandar = sc.next().charAt(0);
            break;
          case 63:
            System.out.print("¿Agrandar sus refrescos? ");
            agrandar = sc.next().charAt(0);
            break;
        }

        if(id == 11 || id == 12 || id == 13 || id == 61 || id == 63){
          System.out.print("¿Acaramelar? ");
          acaramelar = sc.next().charAt(0);
        }
        rest.FijarValores(id, num, agrandar, acaramelar, total1, orden, jubilado);
        rest.AsignarPrecio();
        total1 = rest.AcumularPrecio();
        rest.AsignarArray();
        System.out.print("¿Añadirá otro producto? S/N: ");
        producto = sc.next().charAt(0);
        orden = orden + 1;
      }
      perdidoJubi = rest.AplicarDescuento();
      rest.ImprimirFactura();
      System.out.print("¿Atenderá a otro cliente? S/N: ");
      caja = sc.next().charAt(0);
    }
    System.out.print("¿Desea revisar los reportes del día? (S/N): ");
    reporte = sc.next().charAt(0);
    totalesProductos = rest.RetornarArreglo();
    recaudado = rest.CalcularRecaudado();
    while(reporte == 's' || reporte == 'S') {
      System.out.println("\n1 - Total recaudado por tipo de producto");
      System.out.println("2 - Total recaudado y descuentos a jubilados");
      System.out.println("3 - Porcentaje de aportes a las ventas");
      System.out.print("Ingrese el número correspondiente al reporte que quiere revisar: ");
      consulta = sc.nextInt();
      switch (consulta) {
        case 1:
          System.out.println("\n-----TOTAL RECAUDADO POR TIPO DE PRODUCTO----- \n");
          System.out.printf("- Popcorn: %.2f dólares\n", totalesProductos[0]);
          System.out.printf("- Hotdog: %.2f dólares. \n", totalesProductos[1]);
          System.out.printf("- Refresco: %.2f dólares. \n", totalesProductos[2]);
          System.out.printf("- Agua: %.2f dólares. \n", totalesProductos[3]);
          System.out.printf("- Chocolate: %.2f dólares. \n", totalesProductos[4]);
          System.out.printf("- Combos: %.2f dólares. \n", totalesProductos[5]);
          break;

        case 2:
          System.out.println("\n-----TOTAL RECAUDADO Y DESCUENTO A JUBILADOS----- \n");
          System.out.printf("- Total recaudado: %.2f dólares\n", recaudado);
          System.out.printf("- Total del descuento dado a jubilados: %.2f dólares\n", perdidoJubi);
          break;

        case 3:
          System.out.println("\n-----PORCENTAJE DE APORTES A LAS VENTAS----- \n");
          System.out.printf("- Popcorn: %.2f%% \n", (totalesProductos[0] * 100 / recaudado));
          System.out.printf("- Hotdog: %.2f%% \n", (totalesProductos[1] * 100 / recaudado));
          System.out.printf("- Refresco: %.2f%% \n", (totalesProductos[2] * 100 / recaudado));
          System.out.printf("- Agua: %.2f%% \n", (totalesProductos[3] * 100 / recaudado));
          System.out.printf("- Chocolate: %.2f%% \n", (totalesProductos[4] * 100 / recaudado));
          System.out.printf("- Combos: %.2f%% \n", (totalesProductos[5] * 100 / recaudado));
          break;
      }
      System.out.print("\n¿Desea revisar otro reporte? (S/N):  ");
      reporte = sc.next().charAt(0);
    }
    System.out.println("\nHasta luego...");
    sc.close();
    }
  }


