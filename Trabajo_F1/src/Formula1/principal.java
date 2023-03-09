package Formula1;

import java.text.SimpleDateFormat;
import java.util.Scanner;


public class principal {
	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void main(String[] args) {

		if (ad.getCnx()!= null) {
			int opcion;
			do {
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				System.out.println("1-Mostrar Pilotos"); 
				System.out.println("2-Poner nota");
				System.out.println("3-Mostrar notas alumnos");
				System.out.println("4-Modificar nota");
				System.out.println("5-Modificar direccion de persona");
				//quereseres
				
				
				//no errores
				
				//operacion sobre tablas heredadas
				
				//realizar insercion y consulta sobre el tipo de datos creado por el ususrio
				
				//insercion // modificacion // consulta sobre el array bidimensional
				
				//entregar enunciado con añalisis del trabajo
				
				// entregar a tiempo
				
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				
				}
			} while (opcion != 0);
			//Cerrar conexión
			ad.cerrar();
		} else {
			System.out.println("Error, no hay conexión con Clínica");
		}
	}
}
