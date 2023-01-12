package ejercicio1;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	static Scanner t = new Scanner(System.in);
	static ADtxt fVentas = new ADtxt();
	static ADobj fVentasObj = new ADobj();

	public static void main(String[] args) {

		int opcion;

		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");
			System.out.println("1- Ejercicio1 (crear fichero objeto)");
			System.out.println("2-");
			System.out.println("3-");
			System.out.println("4-");
			opcion = t.nextInt();
			t.nextLine();
			switch (opcion) {
			case 1:
				ejercicio1();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			}
		} while (opcion != 0);

	}

	private static void ejercicio1() {
		// crear un objeto ventas con el fichero txt

		ArrayList<ventas> v = fVentas.obtenerVentas();

		for (ventas ventas : v) {
			ventas ventaobj = fVentasObj.obtenerventa(ventas.getIdProducto());
			if (ventaobj != null) {

				fVentasObj.actualizarVenta(ventas);

			} else {
				fVentasObj.añadirVenta(ventas);
			}
		}

	}
}
