package examen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {

	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	static Scanner t = new Scanner(System.in);
	static AccesoBD bd = new AccesoBD();
	static Empleado emLogeado;
	
	public static void main(String[] args) {
			
		//Pedir usuario y ps
		System.out.print("Nº de empleado: ");
		int codigo = t.nextInt(); t.nextLine();
		System.out.print("Contraseña: ");
		String ps = t.nextLine();
			
		//Lamar a la función de login
		int log = bd.login(codigo, ps);
		
		if(log == 0) {
			
			System.out.println("Error, datos incorrectos");
	
		} else {
			
			emLogeado = bd.obtenerEmpleado(codigo);
			
			if(emLogeado != null) {
				
				if(log == 1) {
					
					pintarMenu();
					
				} else {
					
					// Primera vez, cambiar contraseña
					// Pedir la nueva contraseña
					System.out.println("Esta es su primera vez en el sistema. Debe cambiar la contraseña.");
					System.out.print("Introduzca nueva contraseña: ");
					String nuevaC = t.nextLine();
					
					if(bd.modificarClave(emLogeado, nuevaC)) {
						
						System.out.println("Contraseña modificada con éxito.");
						pintarMenu();
						
					} else {
						
						System.out.println("Error al modificar la contraseña.");
						
					}
					
				}
				
			} else {
				
				System.out.println("Error al obtener los datos del empleado.");
				
			}
			
		}
		
		switch(log) {
			case 0: 
				break;
			
			case 1: {
				pintarMenu();
			}
				break;
				
			case 2: 
				break;
		}
		

	}

	private static void pintarMenu() {
		int opcion;

		do {
			System.out.println("MENÚ");
			System.out.println("0-Salir");
			System.out.println("1- Enviar mensaje");
			System.out.println("2- Leer mensajes");
			System.out.println("3- Estadística de mensajes");
			System.out.print("Introduzca una opción: ");
			opcion = t.nextInt(); t.nextLine();			

			switch (opcion) {
			case 1:
				enviarMensaje();
				break;

			case 2:
				leerMensajes();
						break;

			case 3:
				estadisticaMensajes();
				break;

			}		

		} while (opcion != 0);

		System.out.println("\nPROGRAMA FINALIZADO.");
	}

	private static void estadisticaMensajes() {
		
		
		
	}

	private static void leerMensajes() {
		
		ArrayList<Mensaje> mensajes = bd.obtenerMensajesEmp(emLogeado);
		
		for(Mensaje m: mensajes) {
			m.mostrar();
		}
		
		if(bd.leerMensajes(emLogeado, mensajes)) {
			
			System.out.println("Mensajes marcados como leídos.");
			
		} else {
			
			System.out.println("Error al leer los mensajes");
			
		}
		
	}

	private static void enviarMensaje() {
		
		// Mostrar todos los departamentos
		ArrayList<Departamento> departamentos = bd.obtenerDepartamentos();
		for(Departamento d: departamentos) {
			d.mostrar();
		}
		
		// Pedir el número de dep.
		System.out.print("Introduzca el número de departamento: ");
		int numDep = t.nextInt(); t.nextLine();
		
		// Obtenemos el dep. a partir de su núm.
		Departamento dep = bd.obtenerDepartamento(numDep);
		
		// Comprobamos que existe
		if(dep != null) {
			
			// Pedir asunto y texto de mensaje
			System.out.print("Asunto del mensaje: ");
			String asu = t.nextLine();
			
			System.out.print("Texto de mensaje: ");
			String tex = t.nextLine();
			
			// Obtener todos los empleados de un departamento
			ArrayList<Empleado> empleadosDep = bd.obtenerEmpleadosDepa(dep);
			
			Mensaje m = new Mensaje();
			m.setDeEmpleado(emLogeado);
			m.setParaDepartamento(dep);
			m.setAsunto(asu);
			m.setFechaEnvio(new Date(System.currentTimeMillis()));
			m.setMensaje(tex);
			
			// Crear el mensaje en la tabla mensajes
			// + Insertar en tabla "para" los mensajes
			if(bd.enviarMensaje(m, emLogeado, dep, asu, tex, empleadosDep)) {
				
				System.out.println("Mensaje enviado correctamente.");
				System.out.println("ID del mensaje: " + m.getId());
				
			} else {
				
				System.out.println("Error al enviar el mensaje.");
				
			}
			
		} else {
			
			System.out.println("Error, número de departamento no válido.");
			
		}
		
	}

}
