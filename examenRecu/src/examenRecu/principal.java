package examenRecu;

import java.util.ArrayList;
import java.util.Scanner;


public class principal {
	
	static Scanner t = new Scanner(System.in);
	
	static AccesoDatos ad = new AccesoDatos();
	
	

	public static void main(String[] args) {

		int opcion;

		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");
			System.out.println("1-ejercicio 1");
			System.out.println("2-");
			System.out.println("3-");
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
		ArrayList<radar> multas = null;
		
		System.out.println("introduce el fichero que se va a leer");
		String fichero = t.nextLine();
		
		if(fichero.equalsIgnoreCase("a5")||fichero.equalsIgnoreCase("a6")) {
			
			if(fichero.equalsIgnoreCase("a5")) {
			
				multas=ad.obtenerMultas("a5");
				
			}else {
				multas=ad.obtenerMultas("a6");
			}
			
			for (radar radar : multas) {
				
				multaBin multa = ad.obtenerMultasBin(radar.getMulta().getMatricula());
				if(multa==null) {
					if(ad.addMulta(radar.getMulta().getMatricula(),radar.getMulta().getVelocidad())) {
						System.out.println("multa añadida correctamente");
					}else {
						System.out.println("multa no añadida");
					}
				}
				if (ad.actualizarMulta(radar.getMulta().getMatricula(),radar.getMulta().getVelocidad())) {
					System.out.println("multa actualizada correctamente");
				}else {
					System.out.println("multa no no actualizada");
				}
			}
			
		}else {
			System.out.println("introduce el nombre de un fichero que exista");
		}
		
	}

}
