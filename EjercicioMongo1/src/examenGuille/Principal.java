package examenGuille;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {

	private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	private static AccesoDatos ad = new AccesoDatos();
	private static Scanner t = new Scanner(System.in);
	private static Empleado empIdentificado = null;

	public static void main(String[] args) {

		if (ad.getBd() != null) {

			int opcion;

			do {
				System.out.println("MENÚ");
				System.out.println("0 - Salir");
				System.out.println("1 - Crear Empleado");
				System.out.println("2 - Enviar Mensaje");
				System.out.println("3 - Leer Mensajes y Borrar");
				System.out.println("4 - Estadística de Mensajes");
				System.out.print("Introduzca opción: ");
				opcion = t.nextInt();
				t.nextLine();

				switch (opcion) {
				case 1:
					crearEmpleado();
					break;
				case 2:
					enviarMensaje();
					break;
				case 3:
					leerBorrarMensajes();
					break;
				case 4:
					estadisticaMensajes();
					break;
				}

			} while (opcion != 0);
			// Cerrar conexión
			ad.cerrar();
		} else {
			System.out.println("Error, no hay conexión con la base de datos");
		}
	}
	
	private static void estadisticaMensajes() {
		System.out.println();
		if(identificarEmpleado()) {
			ArrayList<Object[]> mensajes = ad.obtenerMensajesEnviados(empIdentificado);
			if(!mensajes.isEmpty()) {
				System.out.println("ESTADÍSTICA DE MENSAJES:");
				for(Object[] o: mensajes) {
					System.out.println("Departamento: " + o[0] +
							"\tNº de mensajes enviados: " + o[1] + 
							"\tFecha primer mensaje: " + formato.format(o[2]) + 
							"\tFecha último mensaje: " + formato.format(o[3]));
				}
				
			} else {
				System.out.println("El empleado logeado no ha enviado mensajes.");
			}
		} else {
			System.out.println("Error, no existe ningún empleado en la empresa con ese DNI.");
		}
		System.out.println();
	}

	private static void leerBorrarMensajes() {
		System.out.println();
		if(identificarEmpleado()) {
			ArrayList<Object[]> mensajes = ad.obtenerMensajesEmp(empIdentificado);
			if(!mensajes.isEmpty()) {
				System.out.println("MENSAJES:");
				for(Object[] o: mensajes) {
					System.out.println("Emisor: " + o[0] +
							"\tAsunto: " + o[1] + 
							"\tMensaje: " + o[2]);
				}
				if(ad.borrarMensajes(empIdentificado)) {
					System.out.println("Mensajes borrados con éxito");
				} 
				else {
					System.out.println("Error al intentar borrar los mensajes.");
				}
				
			} else {
				System.out.println("El empleado logeado no tiene mensajes.");
			}
		} else {		
			System.out.println("Error, no existe ningún empleado en la empresa con ese DNI.");		
		}
		System.out.println();
	}

	private static void enviarMensaje() {
		System.out.println();
		if(identificarEmpleado()) {
			System.out.print("Indique el departamento al que lo quiere enviar: ");
			String dep = t.nextLine();
			
			ArrayList<Destinatario> destinatariosDep = ad.obtenerDestinatariosDep(dep);
			if(!destinatariosDep.isEmpty()) {
				Mensaje m = new Mensaje();
				m.setDeEmpleado(empIdentificado.getDni());
				m.setFecha(new Date());
				m.setParaDepartamento(dep);
				m.setDestinatarios(destinatariosDep);
				System.out.print("Indique el asunto: ");
				m.setAsunto(t.nextLine());
				System.out.print("Indique mensaje: ");
				m.setMensaje(t.nextLine());
		
				if(ad.enviarMensaje(m)) {
					
					System.out.println("Mensaje enviado correctamente.");
					System.out.println("Código de mensaje: " + m.getCodigo());
					
				} else {	
					System.out.println("Error al intentar enviar el mensaje.");	
				}
			} else {
				System.out.println("No hay ningún empleado para el departamento que ha indicado.");	
			}
		} else {
			System.out.println("Error, no existe ningún empleado en la empresa con ese DNI.");
		}
		System.out.println();
	}

	private static boolean identificarEmpleado() {
		if(empIdentificado == null) {
			System.out.print("Para identificarte como empleado, introduce DNI: ");
			String dni = t.nextLine();
			Empleado em = ad.obtenerEmpleado(dni);
			if(em != null) {
				empIdentificado = em;
				return true;
			} else {
				return false;
			}	
		} else {
			return true;
		}
		
	}
	
	private static void crearEmpleado() {
		System.out.println();
		System.out.print("Introduzca el DNI del empleado: ");
		String dni = t.nextLine();
		
		Empleado em = ad.obtenerEmpleado(dni);
		
		if(em == null) {
			em = new Empleado();
			em.setDni(dni);
			
			System.out.print("Introduzca el nombre del empleado: ");
			em.setNombre(t.nextLine());
			
			System.out.print("Introduzca el departamento del empleado: ");
			em.setDepartamento(t.nextLine());
			
			if(ad.crearEmpleado(em)) {
				System.out.println("Empleado creado con éxito.");
			} else {
				System.out.println("Error al intentar crear el empleado.");
			}
		} else {
			System.out.println("Error, ya existe otro empleado con ese DNI.");
		}
		System.out.println();
	}
	
	

}
