package taller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Principal {

	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getEm() != null) {
			int opcion;
			do {
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				System.out.println("1-Crear Reparación");
				//2 Añade una pieza a una reparación
				//Hay que chequear que hay stock suficiente
				//Si la pieza aún no está en la reparación, se añade
				//Si la pieza ya está en la reparación, se actualiza
				//Hay que actualizar el stock de la pieza 
				System.out.println("2-Añadir Pieza a Reparación");
				System.out.println("3-Borrar Pieza a Reparación");
				System.out.println("4-Imprimir Factura");
				System.out.println("5-Estadística taller");
				
				
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearReparacion();
					break;
				case 2:
					crearPiezaReparacion();
					break;
				case 3:
					borrarPiezaReparacion();
					break;
				case 4:
					imprimirFactura();
					break;
				case 5:
					estadisticaTaller();
					break;
				
				}
			} while (opcion != 0);
			//Cerrar conexión
			ad.cerrar();
		} else {
			System.out.println("Error, no hay conexión con Clínica");
		}
	}


	private static void estadisticaTaller() {
		// TODO Auto-generated method stub
		List<Object[]> datos=ad.obtenerEstadistica();
		for(Object[] o:datos) {
			System.out.println("Nº Piezas:"+ o[0] +
					"\tPrecio Total:"+ o[1] + 
					"\tPrecio Mínimo:"+ o[2] +
					"\tPrecio Máximo:" + o[3]);
		}
	}


	private static void imprimirFactura() {
		// TODO Auto-generated method stub
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		mostrarReparaciones();
		System.out.println("Código de reparación");
		int codigo = t.nextInt();t.nextLine();
		Reparacion r = ad.obtenerReparacion(codigo);
		if(r!=null) {
			System.out.println("Reparación:"+r.getId() + 
					"\tCliente:"+r.getVehiculo().getNombrePropietario()+
					"\tFecha:"+formato.format(r.getFecha()));
			System.out.println("Codigo \t\t Nombre \t\t Cantidad \t\t Precio");
			float total=0;
			for(PiezaReparacion pr:r.getPiezasRep()) {
				System.out.println(pr.getClave().getPieza().getCodigo()+
						"\t\t"+pr.getClave().getPieza().getDescripcion()+
						"\t\t"+pr.getCantidad()+
						"\t\t"+pr.getImporte());
				total+=pr.getImporte();
			}
			System.out.println("Total:"+total);
		}
	}


	private static void borrarPiezaReparacion() {
		// TODO Auto-generated method stub
		mostrarReparaciones();
		System.out.println("Codigo de reparacion");
		int codigo = t.nextInt();t.nextLine();
		Reparacion r = ad.obtenerReparacion(codigo);
		if(r!=null) {
			//Mostrar las piezas de la reparación
			System.out.println("Piezas de la reparación");
			for(PiezaReparacion pr:r.getPiezasRep()) {
				pr.mostrar();
			}
			System.out.println("Código Pieza a borrar");
			String codigoP = t.nextLine();
			//Obtenemos la piezaReparación de la BD
			PiezaReparacion pr = ad.obtenerPR(
					new clavePR(r, ad.obtenerPieza(codigoP) ));
			if(pr!=null) {				
				if(ad.borrarPr(pr)) {
					System.out.println("Pieza borrada");
				}
				else {
					System.out.println("Error, al borrar la pieza");
				}
			}
			else {
				System.out.println("Error, no existe la pieza en la reparación");
			}
		}
		else {
			System.out.println("Error, no existe la reparación");
		}
	}


	private static void crearPiezaReparacion() {
		// TODO Auto-generated method stub
		mostrarReparaciones();
		System.out.println("Introduce id de reparación");
		int id = t.nextInt(); t.nextLine();
		Reparacion r = ad.obtenerReparacion(id);
		if(r!=null && !r.isPagado()) {
			mostrarPiezas();
			System.out.println("Introduce código pieza:");
			String codigo = t.nextLine();
			Pieza p = ad.obtenerPieza(codigo);
			//Chequear que la pieza existe y hay stock
			if(p!=null && p.getStock()>0) {
				System.out.println("Introduce cantidad");
				int cantidad = t.nextInt();t.nextLine();
				//Chequear que hay stock
				if(p.getStock()>=cantidad) {					
					PiezaReparacion pr = ad.obtenerPR(new clavePR(r,p));
					//Ver si ya se ha metido la pieza
					if(pr==null) {
						//Se añade la pieza a la reparación
						pr = new PiezaReparacion(
								new clavePR(r, p), 
								cantidad* p.getPrecio(), cantidad);
						//Actualizar el stock de la pieza
						p.setStock(p.getStock()-cantidad);
					}
					else {
						//Se actualiza el stock de la pieza
						p.setStock(p.getStock()+pr.getCantidad()-cantidad);
						//Acutalizar la cantidad de la pieza en la rep.
						pr.setCantidad(cantidad);
						pr.setImporte(cantidad*p.getPrecio());						
					}
					//Hacer los cambios en la bd
					if(ad.guardarPR(pr)) {
						System.out.println("Pieza guarda");
					}
					else {
						System.out.println("Error, al guardar la pieza en la reparación");
					}
				}
				else {
					System.out.println("Error no hay stock suficiente");
				}
			}
			else {
				System.out.println("No existe pieza o no hay stock");
			}
		}
		else {
			System.out.println("Error, no existe la reparación o está pagada");
		}
	}


	private static void mostrarPiezas() {
		// TODO Auto-generated method stub
		List<Pieza> piezas = ad.obtenerPiezas();
		for(Pieza p:piezas) {
			p.mostrar();
		}
	}


	private static void mostrarReparaciones() {
		// TODO Auto-generated method stub
		List<Reparacion> reparaciones = ad.obtenerReparaciones();
		for(Reparacion r:reparaciones) {
			r.mostrar(false);
		}
	}


	private static void crearReparacion() {
		// TODO Auto-generated method stub
		mostrarVehiculos();
		System.out.println("Codigo Vehículo:");
		int codigo  = t.nextInt();t.nextLine();
		Vehiculo v = ad.obtenerVehiculo(codigo);
		if(v!=null) {
			boolean abierta = false;
			//Vamos a chequear si el vehículo tiene alguna reparación no pagada
			for(Reparacion r:v.getReparaciones()) {
				if(!r.isPagado()) {
					System.out.println("Error, el vehículo tiene una reparación abierta: "+r.getId());
					abierta = true;
					break;
				}
			}
			if(!abierta) {
				Reparacion r = new Reparacion();
				r.setVehiculo(v);
				r.setFecha(new Date());			
				if(ad.crearReparacion(r)) {
					System.out.println("Reparacion creada con código "+r.getId());
				}
				else {
					System.out.println("Error al crear reparación");
				}
			}
		}
		else {
			System.out.println("Error, vehículo no existe");
		}
	}


	private static void mostrarVehiculos() {
		// TODO Auto-generated method stub
		List<Vehiculo> coches = ad.obtenerVehiculos();
		for(Vehiculo v: coches) {
			v.mostrar(true);
		}
	}
	
}
