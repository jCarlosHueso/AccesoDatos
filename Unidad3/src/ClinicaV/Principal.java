package ClinicaV;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import Spotifly.AccesoDatos;

public class Principal {
	static Scanner t = new Scanner(System.in);
	static AccesoDatos sf = new AccesoDatos();
	// Definimos el formato con el que vamos
	// a pintar/pedir fechas
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (sf.getBd() != null) {
			int opcion;
			do {
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				System.out.println("1-Crear Cliente");
				System.out.println("2-Crear Mascota");
				System.out.println("3-Crear Tratamiento");
				System.out.println("4-Mostrar tratamiento");
				System.out.println("5-Borrar tratamiento");
				System.out.println("6-Modificar tratamiento");
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearCliente();
					break;
				
				}
			} while (opcion != 0);
			//Cerrar conexión
			sf.cerrar();
		} else {
			System.out.println("Error, no hay conexión con SpotiFly");
		}
	}
	private static void crearCliente() {
		// TODO Auto-generated method stub
		Cliente c =new Cliente();
		
	}
}
