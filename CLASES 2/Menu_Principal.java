import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu_Principal {

  // ArrayList<Platillo> platillos = new ArrayList<Platillo>();
  // Dias[] semana = new Dias[7];
  ArrayList<Platillo> platillos = new ArrayList<Platillo>();


  public static void main(String[] args) {
    ArrayList<Meseros> meseros = new ArrayList<Meseros>();
    ArrayList<Platillo> platillos = new ArrayList<Platillo>();
    String resp;
    Scanner input = new Scanner(System.in);
    boolean mainLoop = true;

    int choice;
    while (true) {
      System.out.println("MI Restaurante  © 2019 \n");
      System.out.print("1.) ✚ Configurar platillos\n");
      System.out.print("2.)  Configurar meseros\n");
      System.out.print("3.) ▶ Nueva semana.\n");
      System.out.print("4.) ＄ Reportes.\n");
      System.out.print("5.) ☑ Exit\n");
      System.out.print("\n⌨ Eliga una opción para comenzar:  ");

      choice = input.nextInt();

      switch (choice) {
        case 1:
          System.out.println("Bienvenido a configuracion. ✚\n");
          if (platillos.size() == 3) {
            System.out.println("Sus platillos son: \n ");
            for (Platillo p : platillos) {
              System.out.println("ID" +  "   " + "Nombre " + "  " + "Precio");
              System.out.println(p.id + "  " + p.nombre_platillo + "  " + p.precio_platillo);
            }
            break;
          } else {
            System.out.println("¿Desea crear un platillo? [Si/No] \n");
            boolean exit = false;
            resp = (String) input.nextLine();
            if (input.next().equalsIgnoreCase("no")) {
              System.out.println("Regresando... ");
              break;
            }
            while (!exit) {
              Platillo pl = new Platillo();
              pl.crear_platillo();
              platillos.add(pl);
              System.out.println("¿Desea crear otro platillo? [Si/No] \n");
              if (input.next().equalsIgnoreCase("no")) {
                System.out.println("Regresando... ");
                exit = true;
                break;
              }
              if (platillos.size() == 3) {
                System.out.println("Ha alcanzado el máximo de platillos: \n ");
                System.out.println("Sus platillos son: \n ");
                for (Platillo p : platillos) {
                  System.out.println("ID" +  "   " + "Nombre " + "  " + "Precio");
                  System.out.println(p.id + "  " + p.nombre_platillo + "  " + p.precio_platillo);
                }
                break;
              }
            } break;
          }
          case 2:
            if(meseros.size() == 3) {
              System.out.println("Ha excedido el número máximo de meseros");
              break;
            } else {
              System.out.println("¿Desea añadir un mesero? [si/no] ");
              boolean exit = false;
              resp = (String) input.nextLine();
              if (input.next().equalsIgnoreCase("no")) {
                System.out.println("Regresando... ");
                break;
              }
              while (!exit) {
                Meseros mesero = new Meseros();
                mesero.asignar_mesero();
                meseros.add(mesero);
                System.out.println("¿Desea crear otro mesero? [Si/No] \n");
                if (input.next().equalsIgnoreCase("no")) {
                  System.out.println("Regresando... ");
                  exit = true;
                  break;
                }
              }
            }
          case 3:
            if (platillos.size() == 0) {
              System.out.println("No hay platillos, por favor configura el menu");
              break;
            }else{
            System.out.println("Bienvenido a un nuevo semana.");
            System.out.println("1. Nuevo dia");
            System.out.println("2. Terminar semana");
            System.out.println("Vamos a tomar una nueva orden ");


              for (Platillo p : platillos) {
                System.out.println("ID" + "   " + "Nombre " + "  " + "Precio");
                System.out.println(p.id + "  " + p.nombre_platillo + "  " + p.precio_platillo);
              }
            }
            System.out.println("Eliga un platillo");



            break;
            case 5:
              System.out.println("Saliendo del programa...");
              System.exit(0);
              break;
            default:
              System.out.println("¡Formato inválido! Elige otro número");
              break;

          }
      }
    }


  }

