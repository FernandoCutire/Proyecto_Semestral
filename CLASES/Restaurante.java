import java.lang.reflect.Array;
import java.util.Scanner;

class Restaurante {
	Scanner sc = new Scanner(System.in);
	int eleccionMenuPrincipal, opcionConfiguracion, opcionSemana;
	Platillo plato = new Platillo();
	Platillo comida[] = new Platillo[7];
	//Variables para manejar el arreglo de platillos
	int modificar, espacio = 0;
	double precioPlatillo;
	String nombrePlatillo;
	char oferta, configurarOferta = 's', retornarOferta = 'n';
	//Variables para administrar los meseros
	Mesero meseros[] = new Mesero[3];
	String nombresMeseros[] = new String[3];
	double ventasMeseros[] = new double[3];
	int manejarMeseros = 0;
	char reemplazar;
	//Variables para atencion de clientes
	char atender = 's', producto, jubilado;
	int pedido, cantPedido, orden, mozo;
	int codigoPlatillo[] = new int[15];
	int cantidadPlatillo[] = new int[15];
	//Variables para generar la factura
	double totalCliente, descuentoJubi = 0;
	// Variables para generar reportes
	int opcionreporte;
	double pOferta;


	public void ImprimirMenuPrincipal(){
		System.out.println("\n---------------------------------------");
		System.out.println("           RESTAURANTE ");
		System.out.println("1 - Configuracion");
		System.out.println("2 - Nueva Semana");
		System.out.println("3 - Reportes Semanales");
		System.out.println("4 - Salir");
		System.out.println("---------------------------------------");
	}

	public int ConsultarOpcionPrincipal() {
		System.out.print("Inserte una opción: ");
		eleccionMenuPrincipal = sc.nextInt();
		return eleccionMenuPrincipal;
	}

	public int EvaluarOpcionPrincipal() {
		switch (eleccionMenuPrincipal) {
			case 1:
				// int consultaConfiguracion;
				opcionConfiguracion = ConsultarOpcionConfiguracion();
				while (opcionConfiguracion != 6) {
					EvaluarOpcionConfiguracion();
					opcionConfiguracion = ConsultarOpcionConfiguracion();
				}
				break;
			case 2:
				if(espacio > 0 && manejarMeseros > 0){
					ConsultarOpcionSemana();
					while(opcionSemana != 2){
						EvaluarOpcionSemana();
						ConsultarOpcionSemana();
					}
				}
				else{
					System.out.println("(!) Añade al menos un platillo y un mesero para continuar");
				}
				break;
			case 3:
				break;
			default:
				System.out.println("Por favor, elija una opción válida");
				break;
		}
		ImprimirMenuPrincipal();
		ConsultarOpcionPrincipal();
		return eleccionMenuPrincipal;
	}

	// METODOS DEL MENU DE CONFIGURACION (001)
	public int ConsultarOpcionConfiguracion() {
		System.out.println("\n----------------------------------------------------");
		System.out.println("                  CONFIGURACION ");
		System.out.println("1 - Añadir platillo");
		System.out.println("2 - Modificar platillo");
		System.out.println("3 - Eliminar platillo");
		System.out.println("4 - Añadir mesero");
		System.out.println("5 - Eliminar mesero");
		System.out.println("6 - Regresar");
		System.out.println("----------------------------------------------------");
		System.out.print("Ingrese su opción: ");
		opcionConfiguracion = sc.nextInt();
		return opcionConfiguracion;
	}

	public void EvaluarOpcionConfiguracion() {
		switch (opcionConfiguracion) {
			case 1:
				if (espacio < 7) {
					System.out.print("Ingrese el nombre del platillo: ");
					nombrePlatillo = sc.next();
					sc.nextLine();
					System.out.print("Ingrese el precio del platillo: ");
					precioPlatillo = sc.nextDouble();
					oferta = 'n';
					comida[espacio] = new Platillo(nombrePlatillo, precioPlatillo, oferta);
					espacio++;
				}
				else{
					System.out.println("Ya ocupó todos los espacios del Menú");
				}
				break;
			case 2:
				//pendiente agregar la impresion de los platillos
				ImprimirPlatillos();
				System.out.print("Ingrese el número del platillo a modificar: ");
				modificar = sc.nextInt();
				System.out.print("Ingrese el nombre del platillo: ");
				nombrePlatillo = sc.next();
				System.out.print("Ingrese el precio del platillo: ");
				precioPlatillo = sc.nextDouble();
				comida[modificar] = new Platillo(nombrePlatillo, precioPlatillo, oferta);
				break;
			case 3:
				// MostrarPreguntas();
				System.out.println("Seleccione el platillo a eliminar: ");
				modificar = sc.nextInt();
				System.arraycopy(comida, modificar + 1, comida, modificar, comida.length - 1 - modificar);
				// MostrarPreguntas();
				espacio = espacio - 1;
				break;
			case 4:
				if(manejarMeseros < 3){
					System.out.print("Ingrese el nombre del nuevo mesero: ");
					Mesero mesero = new Mesero();
					nombresMeseros[manejarMeseros] = sc.next();
					mesero.nombre = nombresMeseros[manejarMeseros];
					ventasMeseros[manejarMeseros] = 0;
					manejarMeseros++;
				}
				else{
					System.out.print("Ya se asignaron todos los espacios de meseros, ¿desea reemplazar uno? s/n:  ");
					reemplazar = sc.next().charAt(0);
					if(reemplazar == 's' || reemplazar == 'S'){
						ImprimirMeseros();
						System.out.print("Ingrese el número del mesero a reemplazar: ");
						modificar = sc.nextInt();
						nombresMeseros[manejarMeseros] = sc.next();
						sc.nextLine();
						ventasMeseros[manejarMeseros] = 0;
					}
				}
				break;
			case 5:
				ImprimirMeseros();
				System.out.print("Ingrese el número del mesero a eliminar: ");
				modificar = sc.nextInt();
				nombresMeseros[modificar] = null;
				ventasMeseros[modificar] = 0.0;
				break;
			default:
				System.out.println("Por favor, elija una opción válida");
				break;
		}
	}

	// METODOS DEL MENU DE SEMANA (002)
	public void ConsultarOpcionSemana(){
		System.out.println("\n---------------------------------------");
		System.out.println("           NUEVA SEMANA ");
		System.out.println("1 - Nuevo día");
		System.out.println("2 - Cerrar semana");
		System.out.println("---------------------------------------");
		System.out.print("Ingrese su opción: ");
		opcionSemana = sc.nextInt();
	}


	public void EvaluarOpcionSemana() {
		switch (opcionSemana) {
			case 1:
				atender = 's';
				DefinirPlatilloOferta();
				while(atender == 's'){
					AsignarMesero();
					ImprimirMenu();
					producto = 's';
					totalCliente = 0;
					orden = 0;
					descuentoJubi = 0;
					System.out.print("Jubilado (s/n): ");
					jubilado = sc.next().charAt(0);
					while(producto == 's' || producto == 'S') {
						try{
							System.out.print("\nCódigo del platillo: ");
							codigoPlatillo[orden] = sc.nextInt();
							System.out.print("Cantidad del platillo: ");
							cantidadPlatillo[orden] = sc.nextInt();
							System.out.print("¿Añadirá otro producto? s/n: ");
							producto = sc.next().charAt(0);
							orden++;
						}
						catch(NullPointerException e){}
					}
					GenerarFactura();
					AcumularValores();
					System.out.print("¿Atender otro cliente? s/n: ");
					atender = sc.next().charAt(0);
				}
				RetornarPlatilloOferta();
				break;
			default:
				System.out.println("Por favor, elija una opción válida");
				break;
		}
	}

	public void DefinirPlatilloOferta() {
		ImprimirMenu();
		System.out.print("Ingrese el código del platillo en oferta (-35%): ");
		modificar = sc.nextInt();
		comida[modificar].setOferta(configurarOferta);
	}

	public void RetornarPlatilloOferta() {
		comida[modificar].setOferta(retornarOferta);
	}

	public void AsignarMesero() {
		ImprimirMeseros();
		System.out.print("Código del mesero: ");
		mozo = sc.nextInt();
	}




	public void CalcularTotalCliente(){
		int i = 0;
		while (i < orden) {
			if(comida[codigoPlatillo[i]].getOferta() == 's' && jubilado == 's') {
				totalCliente = totalCliente + (comida[codigoPlatillo[i]].getPrecio() * cantidadPlatillo[i] * 0.65);
			}
			else if(comida[codigoPlatillo[i]].getOferta() == 's' && jubilado != 's') {
				totalCliente = totalCliente + (comida[codigoPlatillo[i]].getPrecio() * cantidadPlatillo[i] * 0.65);
			}
			else if(comida[codigoPlatillo[i]].getOferta() != 's' && jubilado == 's') {
				totalCliente = totalCliente + (comida[codigoPlatillo[i]].getPrecio() * cantidadPlatillo[i] * 0.70);
				descuentoJubi = descuentoJubi + (comida[codigoPlatillo[i]].getPrecio() * cantidadPlatillo[i] * 0.30);
			}
			else{
				totalCliente = totalCliente + (comida[codigoPlatillo[i]].getPrecio() * cantidadPlatillo[i]);
			}
			i++;
		}
	}

	public void GenerarFactura() {
		CalcularTotalCliente();
		int i = 0;
		System.out.println("----------------------------------------------------");
		System.out.println("\n     FACTURA - RESTAURANTE");
		System.out.println("Cantidad     Precio      Platillo");
		while(i < orden) {
			try{
				System.out.printf("%d       %.2f          %s\n", cantidadPlatillo[i], comida[codigoPlatillo[i]].getPrecio(),
						comida[codigoPlatillo[i]].getNombre());
				i++;
			}
			catch(NullPointerException a){}
		}
		if (jubilado == 's' || jubilado == 'S') {
			System.out.println("----------------------------------------------------");

			System.out.printf("DESCUENTO(-30%%): %.2f dólares\n", descuentoJubi);
		}
		System.out.printf("TOTAL: %.2f dólares \n", totalCliente);
		System.out.println("----------------------------------------------------");
		// mesero.add(totalCliente)

	}

	public void AcumularValores(){
		// acumulador de meseros


	}

	// METODOS DE IMPRESIONES
	public void ImprimirMeseros(){
		System.out.println("----------------------------------------------------");
		for (int i = 0; i < nombresMeseros.length; i++) {
			if(nombresMeseros[i] != null){
				System.out.printf("Mesero #%d - %s\n", i, nombresMeseros[i]);
			}
		}
		System.out.println("----------------------------------------------------");
	}

	public void ImprimirPlatillos() {
		System.out.println("----------------------------------------------------");
		for (int i = 0; i < comida.length; i++) {
			try {
				System.out.printf("%d. %s\n", i, comida[i].getNombre());
			}
			catch (NullPointerException e) {
			}
		}
		System.out.println("----------------------------------------------------");
	}

	public void ImprimirMenu() {
		System.out.println("----------------------------------------------------");
		System.out.println("                     MENÚ");
		System.out.println("Platillo               -Precio-");
		for (int i = 0; i < comida.length; i++) {
			try {
				System.out.printf("%d. %s              %.2f\n", i, comida[i].getNombre(), comida[i].getPrecio());
			}
			catch (NullPointerException e) {
			}
		}
		System.out.println("----------------------------------------------------");
	}

	// METODOS DEL MENU DE REPORTES (003)
	public void Consultar_reportes(){
		System.out.println("\n---------------------------------------");
		System.out.println("          Reportes ");
		System.out.println("1 - Venta semanal por platillo");
		System.out.println("2 - Ventas diarias por platillo");
		System.out.println("3 - Ventas diarias por mesero ");
		System.out.println("4 - Distribucion porcentual por semana");
		System.out.println("5 - Platillo más vendido en la semana");
		System.out.println("6 - Platillo menos vendido en la semana");
		System.out.println("7 - Ventas día específico");
		System.out.println("8 - Ventas por platillo específico");
		System.out.println("9 - Monto otorgado a jubilados");
		System.out.println("10 - Monto del platillo en oferta");
		System.out.println("---------------------------------------");
		System.out.print("Ingrese su opción: ");
		opcionreporte = sc.nextInt();
	}

	public void OpcionesReportes() {
		switch (opcionreporte) {
			case 1:
				System.out.println("Venta semanal por platillo");

				break;
			case 2:
				System.out.println("Venta diaria por platillo");
				int i = 0;
				System.out.println("----------------------------------------------------");
				System.out.println("\n     FACTURA - RESTAURANTE");
				System.out.println("Cantidad     Precio      Platillo");
				while(i < orden) {
						System.out.printf("%d       %.2f          %s\n", cantidadPlatillo[i],
								comida[codigoPlatillo[i]].getPrecio(),
								comida[codigoPlatillo[i]].getNombre());
						i++;
				}
				break;
			case 3:
				System.out.println("Ventas diarias por meseros");
				break;
			case 4:
				System.out.println("Distribución porcentual");
			case 5:
				System.out.println("Platillo más vendido");
				break;
			case 6:
				System.out.println("Platillo menos vendido");
				break;
			case 7:
				System.out.println("Ventas para un día específico");
				break;
			case 8:
				System.out.println(" Ventas por platillo específico");
			case 9:
				System.out.println("Monto otorgado a jubilados " + descuentoJubi );
				break;
			case 10:
				System.out.println("Monto del platillo en oferta" + pOferta);


		}
	}

	public static void main(String args[]) {
		Restaurante rest = new Restaurante();
		int opcionPrincipal;
		rest.ImprimirMenuPrincipal();
		opcionPrincipal = rest.ConsultarOpcionPrincipal();
		while (opcionPrincipal != 4) {
			opcionPrincipal = rest.EvaluarOpcionPrincipal();
		}
		System.out.println("Gracias por visitarnos, vuelva pronto");
	}
}