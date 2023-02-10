package monglo;

import java.lang.reflect.UndeclaredThrowableException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import monglo.AccesoDatos;

public class Principal {
//:(
	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	static String empleadoRegistrado;

	public static void main(String[] args) {

		if (ad.getBd() != null) {
			int opcion;
			do {
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				System.out.println("1-Crear empleado(Terminao)");
				System.out.println("2-Enviar mensaje");
				System.out.println("3-Leer mensajes y borrar");
				System.out.println("4-Estadistica mensajes");
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearEmpleado();
					break;
				case 2:
					if (identificarEmpleado()) {
						enviarMensajes();
					}

					break;
				case 3:
					if (identificarEmpleado()) {
						// LeerMensaje();
					}

					break;
				case 4:
					if (identificarEmpleado()) {
						estadisticaMensajes();
					}

					break;
				}
			} while (opcion != 0);
			ad.cerrar();
		} else {
			System.out.println("Error, no hay conexión con Clínica");
		}
	}

	private static void estadisticaMensajes() {

		ad.mostrarInfo(empleadoRegistrado);
		
	}

	private static void enviarMensajes() {
		System.out.println("Introduce el departamento al que va a enviar el mensaje:");
		String dpto = t.nextLine();
		
		ArrayList<Destinatarios> destinatarios=ad.obtenerEmpleadosDpto(dpto);
		//esto tiene el array de los empleados del departamento al que hay que enviarselo
		if(destinatarios!=null) {
			System.out.println("Se han obtenidos los empleados de ese departamento");
			if(ad.crearMensaje(empleadoRegistrado,dpto,destinatarios)) {
				System.out.println("Mensaje creado con Exito");
			}else {
				System.out.println("Error al crear el mensaje");
			}
		}else {
			System.out.println("no hay empleados en ese departamento y/o ese departamento no existe");
		}
	
	}

	private static void crearEmpleado() {

		System.out.println("Introduce el dni del empleado a crear:");
		String dni = t.nextLine();
		if (ad.encontrarEmpleado(dni)) {
			if (ad.crearEmpleado(dni)) {
				System.out.println("Empleado creado con exito");
			} else {
				System.out.println("error en el proceso de creación de usuario");
			}
		} else {
			System.out.println("Eror ese DNI ya está registrado para otro empleado\nPruebe otra vez");
		}

	}

	private static boolean identificarEmpleado() {
		boolean resultado = false;
	//no hay empleado registrado, registrar uno
	if(ad.registrarEmpleado()) {
		resultado=true;
		empleadoRegistrado=ad.recuperarEmpleadoRegistrado();
}else {
	System.out.println("Ese usuario no está registrado");
}
		
		return resultado;
	}

}
