package examenRecup;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Principal {
	
	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	// Definimos el formato con el que vamos
	// a pintar/pedir fechas
	static SimpleDateFormat formato = new SimpleDateFormat("ddMMyyHHmm");
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getEm() != null) {
			int opcion;
			do {
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				System.out.println("1- ");

				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:

					break;
				case 2:
					break;
				
				}
			} while (opcion != 0);
			//Cerrar conexión
			ad.cerrar();
		} else {
			System.out.println("Error, no hay conexión con la base de datos");
		}
	}
}
