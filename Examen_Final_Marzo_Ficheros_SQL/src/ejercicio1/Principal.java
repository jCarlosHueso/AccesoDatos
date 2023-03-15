package ejercicio1;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Principal {
	
	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	static ADBinario adb = new ADBinario();
	
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getConexion() != null) {
			int opcion;
			do {
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				System.out.println("1-Crear Presupuesto");
				System.out.println("2-Crear fichero presupuestado.bin");
				System.out.println("3-baja servicio");

		
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearPresupuesto();
					break;
				case 2:
					crearFichero();
					break;
				case 3:
					bajaServicio();
					break;
				
				}
			} while (opcion != 0);
			//Cerrar conexión
			ad.cerrar();
		} else {
			System.out.println("Error, no hay conexión");
		}
	}

	private static void bajaServicio() {
		mostrarFichero();

		System.out.println("Introduce el codigo del servicio a dar de baja");
		String codigoS = t.nextLine();
		if(adb.borrarServicio(codigoS)) {
			System.out.println("servicio borrado");
		}else {
			System.out.println("error no existe el servicio");
		}
		
		
	}

	private static void mostrarFichero() {
		ArrayList<Fichero> fichero = adb.obtenerInfoFichero();
		if(fichero.isEmpty()) {
			System.out.println("El fichero esta vacio");
		}
		for (Fichero f : fichero) {
			//guardarlos uno a uno en el fichero
		System.out.println("fichero: "+f.toString());
		}	
			
		
		
	}

	private static void crearFichero() {

		ArrayList<Fichero> fichero = ad.obtenerInfoFichero();
		 
		for (Fichero f : fichero) {
			//guardarlos uno a uno en el fichero
				
			if(adb.guardardatos(f)) {
				System.out.println("servicio guardado");
			}
		}
		
	}

	private static void crearPresupuesto() {

		System.out.println("Introduce el nif de un cliente:");
		String nif = t.nextLine();
		Cliente clienteI = ad.obtenerClienteNif(nif);
		if(clienteI!=null) {
			crearPresupuesto(clienteI);
		}else {
			System.out.println("El cliente no existe, se procederá a su creación"
					+ "\n·····························································");
			System.out.println("Introduce el nombre del cliente");
			String nombre = t.nextLine();
			System.out.println("Introduce el telefono del cliente");
			String telefono = t.nextLine();
			Cliente c = new Cliente(nif,nombre,telefono);
			if(ad.crearCliente(c)) {
				System.out.println("El cliente ha sido creado con éxito");
				crearPresupuesto(c);
			}else {
				System.out.println("El cliente no ha sido creado");
			}
		}
		
	}

	private static void crearPresupuesto(Cliente c) {

		Presupuesto p = new Presupuesto();
		
		p.setFecha(new Date());
		p.setCliente(c);
		
		if(ad.addPresupuesto(p)) {
			Presupuesto presupuesto = ad.obtenerIdPresupuesto(p);

			System.out.println("Elige que hacer");
			int opcion;
			do {
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				System.out.println("1-Crear detalle");

		
				opcion = t.nextInt();
				t.nextLine();
				
				switch (opcion) {
				case 1:
					crearDetallePresupuesto(presupuesto);
					break;
				case 0:
					mostrarPresupuesto(presupuesto);
					break;
				}
			}while(opcion!=0);
			
		}else {
			System.out.println("Error, presupuesto no creado");
		}
		
	}

	private static void mostrarPresupuesto(Presupuesto presupuesto) {

		System.out.println("Presupuesto Nº: " + presupuesto.getCodigo()
		+"\tFecha: "+formato.format(presupuesto.getFecha())+
		"\tCliente: "+ presupuesto.getCliente().getNombre()
		+" - "+presupuesto.getCliente().getNif());
		
		System.out.println("----------Detalle del Presupuesto----------");
		
		ArrayList<Detalle_Presupuesto> Detalles = ad.obtenerDetalles(presupuesto);
		for (Detalle_Presupuesto d : Detalles) {
			System.out.println("------------------------------------------------------------------------");
			System.out.println("Servicio: "+d.getServicio().getCodigo()+
					"\tDescripción: "+d.getDescripcion()+"\tHoras: " + d.getHoras()
					+"\tImporte: " + d.getImporte());
			
		}
		System.out.println("\n\n\n\n");
		
		
	}

	private static void crearDetallePresupuesto(Presupuesto p) {
		System.out.println("Los servicios que ofrece la empresa son los siguientes:");
		
		ArrayList<Servicio> servicios = ad.obtenerServicios();
		for (Servicio servicio : servicios) {
			System.out.println(servicio.toString());
		}
		
		System.out.println("Introduce un codigo de servicio:");
		String codigo = t.nextLine();
		Servicio s = ad.obtenerServicio(codigo);
		if(s!=null) {
			
			Detalle_Presupuesto detalle = new Detalle_Presupuesto();
			detalle.setPresupuesto(p);
			detalle.setServicio(s);
			System.out.println("Introduce la descripcion:");
			detalle.setDescripcion(t.nextLine());
			System.out.println("Introduce la duracion:");
			detalle.setHoras(t.nextInt());t.nextLine();
			float importe = s.getHoraServicio() * detalle.getHoras(); 
			detalle.setImporte(importe);
			
			if(ad.crearDealle(detalle)) {
				
				System.out.println("El detalle ha sido creado con exito");
				
			}else {
				System.out.println("Error, el detalle no ha sido creado");
			}
			
		}else{
			System.out.println("Error, el servicio no existe");
		}
		
	}

}
