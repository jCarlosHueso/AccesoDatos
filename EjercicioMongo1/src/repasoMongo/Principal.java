package repasoMongo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import ejercicioMongoExamen.AccesoADatos;

public class Principal {

	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	// Definimos el formato con el que vamos
	// a pintar/pedir fechas
	static SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getBd() != null) {
			int opcion;
			do {
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				System.out.println("1-Crear Espectáculo");
				System.out.println("2-Crear Zona");
				System.out.println("3-Crear Entrada");
				System.out.println("4-Mostrar entradas por zona");
				System.out.println("5-Modificar precio de zona");
				System.out.println("6-Borrar entrada");
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearEspectaculo();
						break;
				case 2:
					crearZona();
						break;
				case 3:
					crearEntrada();
						break;
				case 4:
					mostrarEntradasZona();
						break;
				case 5:
					modificarPrecio();
						break;
				case 6:
					borrarEntrada();
						break;
				}
			} while (opcion != 0);
			//Cerrar conexión
			ad.cerrar();
		} else {
			System.out.println("Error, no hay conexión con GranTeatro");
		}
	}

	private static void borrarEntrada() {
		// TODO Auto-generated method stub
		mostrarEspectaculos();
		System.out.println("Introduce id de espectáculo: ");
		int id = t.nextInt();t.nextLine();
		Espectaculos es = ad.obtenerEspectaculo(id);
		if(es != null) {
			if(ad.borrarEntrada(es)) {
				System.out.println("Entrada borrada");
			}
			else {
				System.out.println("Error al borrar entradas");
			}
		}
		else {
			System.out.println("No existe el espectáculo");
		}
	}

	private static void modificarPrecio() {
		// TODO Auto-generated method stub
		mostrarZonas();
		System.out.println("Introduce id: ");
		int id = t.nextInt();t.nextLine();
		Zonas z = ad.obtenerZona(id);
		if(z != null) {
			System.out.println("Introduce el nuevo precio base: ");
			z.setPrecioBase(t.nextFloat());t.nextLine();
			if(ad.actualizarZona(z)) {
				System.out.println("Precio base actualizado");
			}
			else {
				System.out.println("Error al actualizar precio base");
			}
		}
		else {
			System.out.println("No existe la zona");
		}
		mostrarZonas();
	}

	private static void mostrarEntradasZona() {
		// TODO Auto-generated method stub
		mostrarZonas();
		System.out.println("Introduce id de la zona: ");
		int id = t.nextInt();t.nextLine();
		Zonas z = ad.obtenerZona(id);
		if(z != null) {
			for(Entradas en: z.getEntradas()) {
				System.out.println("Código Entrada: "+en.getId()+
						"\tZona: "+z.getNombre()+"\tEspectaculo: "+en.getEspectaculo().getTitulo()+
						"\tFecha: "+en.getEspectaculo().getFecha()+"\tPrecio: "+en.getPrecio());
			}
		}
		else {
			System.out.println("No existe la zona con ese id");
		}
	}

	private static void crearZona() {
		// TODO Auto-generated method stub
		mostrarZonas();
		System.out.println("Introduce zona");
		int codZona = t.nextInt();t.nextLine();
		Zonas z = ad.obtenerZona(codZona);
		if(z == null) {
			z = new Zonas();
			z.setId(codZona);
			System.out.println("Introduce nombre de zona: ");
			z.setNombre(t.nextLine());
			System.out.println("Introduce precio base: ");
			z.setPrecioBase(t.nextFloat());t.nextLine();
			z.setEntradas(null);
			if(ad.crearZona(z)) {
				System.out.println("Zona creada");
			}
			else {
				System.out.println("Error al crear zona");
			}
		}
		else {
			System.out.println("La zona ya existe");
		}
	}

	private static void crearEspectaculo() {
		// TODO Auto-generated method stub
		mostrarEspectaculos();
		System.out.println("Introduce espectáculo");
		int codEspec = t.nextInt();t.nextLine();
		Espectaculos e = ad.obtenerEspectaculo(codEspec);
		if(e == null) {
			e = new Espectaculos();
			e.setId(codEspec);
			System.out.println("Introduce título: ");
			e.setTitulo(t.nextLine());
			e.setFecha(new Date());
			System.out.println("Introduce nombre artista: ");
			e.setArtista(t.nextLine());
			System.out.println("Introduce beneficio: ");
			e.setBeneficio(t.nextFloat());t.nextLine();
			
			if(ad.crearEspectaculo(e)) {
				System.out.println("Espectaculo creado");
			}
			else {
				System.out.println("Error al crear espectaculo");
			}
		}
		else {
			System.out.println("El espectaculo ya existe");
		}
	}

	private static void crearEntrada() {
		// TODO Auto-generated method stub
		mostrarEspectaculos();
		System.out.println("Introduce espectaculo: ");
		int codEspec = t.nextInt();t.nextLine();
		Espectaculos es = ad.obtenerEspectaculo(codEspec);
		if(es != null) {
			mostrarZonas();
			System.out.println("Introduce zona: ");
			int codZona = t.nextInt();t.nextLine();
			Zonas z = ad.obtenerZonas(codZona);
			if(z != null) {
				Entradas en = new Entradas();
				en.setId(ad.obtenerId());
				en.setEspectaculo(es);
				en.setZona(z);
				en.setPrecio(z.getPrecioBase()*(1+es.getBeneficio()));
				if(ad.crearEntrada(en)) {
					System.out.println("Entrada creada");
				}
				else {
					System.out.println("Error al crear Entrada");
				}
			}
			else {
				System.out.println("Error no existe la zona");
			}
		}
		else {
			System.out.println("Error no existe el espectaculo");
		}
	}

	private static void mostrarZonas() {
		// TODO Auto-generated method stub
		ArrayList<Zonas>zonas = ad.obtenerZonas();
		for(Zonas z:zonas) {
			z.mostrar(false);
		}
	}

	private static void mostrarEspectaculos() {
		// TODO Auto-generated method stub
		ArrayList<Espectaculos>espectaculos = ad.obtenerEspectaculos();
		for(Espectaculos e:espectaculos) {
			e.mostrar();
		}
	}

}
