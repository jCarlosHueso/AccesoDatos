package recuperacionMarzoHibernate;

import java.text.SimpleDateFormat;
import java.util.Scanner;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Principal {
	
	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	static AccesoDatosMongo adm = new AccesoDatosMongo();

	
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getEm() != null) {
			int opcion;
			do {
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				System.out.println("1-crear presupuesto");
				System.out.println("2-crear presupuesto mongo");

		
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearPresupuesto();
					break;
				case 2:
					ej3();
					break;
				
				
				}
			} while (opcion != 0);
			//Cerrar conexión
			ad.cerrar();
		} else {
			System.out.println("Error, no hay conexión");
		}
	}

	private static void ej3() {

		int opcion;
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");
			System.out.println("1-Enviar presupuestos");
			System.out.println("2-Estadisticas cliente");

	
			
			opcion = t.nextInt();
			t.nextLine();
			
			switch (opcion) {
			case 1:
				enviarPresupuestos();
				break;
			case 2:
				estadisticaCliente();
				break;
				
			case 0:
				break;
			}
		}while(opcion!=0);
		
		
	}

	
	
	private static void estadisticaCliente() {

		System.out.println("Introduce el nif del cliente");
		String nif = t.nextLine();
		
		System.out.println(adm.obtenerEstadisticaCliente(nif));
		
		
		
	}

	private static void enviarPresupuestos() {
		
		List<Presupuesto>presupuestos=ad.obtenerPresupuestos();
		
		for (Presupuesto presupuesto : presupuestos) {
			
			if(adm.comprobarCliente(presupuesto.getCliente())) {
		//cliente ya existe
					crearPresupuestoMongo(presupuesto);
			}else {
				if(adm.crearCliente(presupuesto.getCliente())) {
					System.out.println("Cliente creado");
					//cliente ahora existe
					crearPresupuestoMongo(presupuesto);
				}else {
					System.out.println("Error al crear el cliente");
				}	
			}	
		}
	}

	private static void crearPresupuestoMongo(Presupuesto presupuesto) {

		PresupuestoMongo p = new PresupuestoMongo();
		
		p.setCodigo(presupuesto.getCodigo());
		p.setFecha(presupuesto.getFecha());
		
		ArrayList<DetalleMongo>detalleMongo = new ArrayList<DetalleMongo>();
		List<Detalle_Presupuesto>detalle=presupuesto.getDetalles();
		for (Detalle_Presupuesto dp : detalle) {
			DetalleMongo dm = new DetalleMongo();
			dm.setDescripcion(dp.getDescripcion());
			dm.setHoras(dp.getHoras());
			dm.setImporte(dp.getImporte());
			detalleMongo.add(dm);
		}
		
		p.setDetalles(detalleMongo);
		
		if(adm.crearPresupuesto(p)){
			System.out.println("Presupuesto creado");
		}else {
			System.out.println("Presupuesto no creado");
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

	
	Presupuesto presupuesto = new Presupuesto();
		
	presupuesto.setFecha(new Date());
	presupuesto.setCliente(c);
		
		if(ad.addPresupuesto(presupuesto)) {

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
					presupuesto=ad.obtenerIdPresupuesto(presupuesto);
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
		
		for (Detalle_Presupuesto d : presupuesto.getDetalles()) {
			System.out.println("------------------------------------------------------------------------");
			System.out.println("Servicio: "+d.getClave().getServicio().getCodigo()+
					"\tDescripción: "+d.getDescripcion()+"\tHoras: " + d.getHoras()
					+"\tImporte: " + d.getImporte());
			
		}
		System.out.println("\n\n\n\n");
		
	}

	private static void crearDetallePresupuesto(Presupuesto p) {
		
System.out.println("Los servicios que ofrece la empresa son los siguientes:");
		
		List<Servicio> servicios = ad.obtenerServicios();
		for (Servicio servicio : servicios) {
			System.out.println(servicio.toString());
		}
		
		System.out.println("Introduce un codigo de servicio:");
		String codigo = t.nextLine();
		Servicio s = ad.obtenerServicio(codigo);
		if(s!=null) {
			
			Detalle_Presupuesto detalle = new Detalle_Presupuesto();
			detalle.setClave(new ClaveDetalle(p,s));
			
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
