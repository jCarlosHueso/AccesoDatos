package examen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {
	static Scanner sc = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	static int usLogueado;

	public static void main(String[] args) {

		File f = new File("empleados.obj");

		if (!f.exists()) {

			cargarEmpleados();

			mostrarEmpleadosObj();

		}

		login();

	}

	private static void login() {
		boolean correcto = false;
		do {
			System.out.println("introduce el id de tu usuario");
			int id = sc.nextInt();
			sc.nextLine();
			System.out.println("introduce tu contraseña");
			String pwd = sc.nextLine();

			if (ad.comprobarUsuario(id, pwd)) {
				correcto = true;
				usLogueado = id;
				System.out.println("usuario y contraseña correctos");

				int opcion;
				do {
					System.out.println("Introduce opción:");
					System.out.println("0-Salir");
					System.out.println("1-Enviar mensaje");
					System.out.println("2-Borrar mensajes recibidos");
					System.out.println("3-Ver buzón");

					opcion = sc.nextInt();
					sc.nextLine();
					switch (opcion) {
					case 1:
						enviarMensaje();
						break;
					case 2:
						if (ad.borrarMensajesrevibidos(usLogueado)) {
							System.out.println("mensajes borrados");
						} else {
							System.out.println("mensajes no borrados");
						}
						break;
					case 3:
						break;

					}

				} while (opcion != 0);

			}
		} while (correcto != true);

	}

	private static void enviarMensaje() {

		System.out.println("escribe el departamento al que enviar el mensaje");
		String dpto = sc.nextLine();
		System.out.println("introduce el mensaje a enviar");
		String mensaje = sc.nextLine();
		System.out.println("introduce la fecha");
		long fecha = sc.nextLong();
		ArrayList<empleado> empleados = ad.getEmpleadosDpto(dpto);
		int empleadosEnvi = 0;
		for (empleado empleado : empleados) {
			empleadosEnvi++;
			mensaje msg = new mensaje(usLogueado, empleado.getNumEmp(), fecha, mensaje, false);

			if (ad.enviarMensaje(msg)) {

			} else {
				System.out.println("mensaje no enviado");
			}

		}
		System.out.println("mensaje enviado a " + empleadosEnvi + " del departamento " + dpto);
	}

	private static void mostrarEmpleadosObj() {

		ArrayList<empleado> empleados = new ArrayList<empleado>();

		empleados = ad.obtenerEmpleadosObj();

		for (empleado empleado : empleados) {

			System.out.println(empleado.toString());
		}

	}

	private static void cargarEmpleados() {

		ArrayList<empleado> empleados = new ArrayList<empleado>();

		empleados = ad.obtenerEmpleados();

//		for (empleado empleado : empleados) {
//		System.out.println(empleado.toString());
//		}

		for (empleado emp : empleados) {
			empleado empObj = ad.obtenerEmpleado(emp.getNumEmp());

			if (empObj != null) {

				if (ad.actualizarDept(emp)) {
					System.out.println("empleado actualizado");
				}

			} else {
				if (ad.addEmp(emp)) {
					System.out.println("nuevo empleado añadido");
				}
			}

		}

	}

}
