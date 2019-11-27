import java.util.Scanner;

public class PROYECTO_SEMESTRAL {


  // METODOS DEL MENU PRINCIPAL (000)
  public void ImprimirPantallaPrincipal() {
    System.out.println("\n---------------------------------------");
    System.out.println("           JUEGO DE TRIVIA");
    System.out.println("1 - Menu");
    System.out.println("2 - Atender a Cliente");
    System.out.println("3 - Reportes");
    System.out.println("4 - Salir del día");
    System.out.println("---------------------------------------");
  }



  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    PROYECTO_SEMESTRAL proyecto_semestral = new PROYECTO_SEMESTRAL();


    proyecto_semestral.ImprimirPantallaPrincipal();

  }

}
