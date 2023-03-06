package ejercicioMongoExamen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import clinicaV.AccesoDatos;

public class Principal {
	static Scanner t = new Scanner(System.in);
	static AccesoADatos ad = new AccesoADatos();
	// Definimos el formato con el que vamos
	// a pintar/pedir fechas
	static SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getBd() != null) {
			int opcion;
			do {
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				System.out.println("1-Crear Entrada");
				System.out.println("2-Mostrar entradas por zona");
				System.out.println("3-Modificar precio de zona");
				System.out.println("4-Borrar entrada");
				System.out.println("5-Crear Espectáculo");
				System.out.println("6-Crear Zona");
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearEntrada();
					break;
				case 2:
					mostrarEntradasZona();
					break;
				case 3:
					modificarPrecioZona();
					break;
				case 4:
					borrarEntrada();
					break;
				case 5:
					crearEspectaculo();
					break;
				case 6:
					crearZona();
					break;
				}
			} while (opcion != 0);
			//Cerrar conexión
			ad.cerrar();
		} else {
			System.out.println("Error, no hay conexión con GranTeatro");
		}
	}

	private static void crearZona() {
		// TODO Auto-generated method stub
		System.out.println("Nombre de la zona: ");
		String nombre = t.nextLine();
		Zonas z = ad.obtenerZonas(nombre);
		if(z == null) {
			z = new Zonas();
			z.setNombre(nombre);
			System.out.println("Precio Base: ");
			z.setPrecioBase(t.nextDouble());t.nextLine();
			z.setId(ad.obtenerCodigo("zonas"));
			if(ad.crearZona(z)) {
				System.out.println("Se ha creado la zona con código "+z.getId());
			}
			else {
				System.out.println("Error al crear zona.");
			}
		}
		else {
			System.out.println("Error, el nombre ya existe.");
		}
	}

	private static void crearEspectaculo() {
		// TODO Auto-generated method stub
		System.out.println("Título: ");
		String tit = t.nextLine();
		Espectaculos e = ad.obtenerEspectaculo(tit);
		if(e==null) {
			e = new Espectaculos();
			e.setTitulo(tit);
			e.setFecha(new Date());
			System.out.println("Artista: ");
			e.setArtista(t.nextLine());
			System.out.println("Beneficio: ");
			double bene = t.nextDouble();t.nextLine();
			e.setBeneficio(bene);
			e.setId(ad.obtenerCodigo("espectaculos"));
			if(ad.crearEspectaculo(e)) {
				System.out.println("Espectáculo creado con código: "+e.getId());
			}
			else {
				System.out.println("Error al crear espectáculo.");
			}
		}
		else {
			System.out.println("Error, ya existe el espectáculo.");
		}
	}

	private static void borrarEntrada() {
		// TODO Auto-generated method stub
		mostrarEspectaculos();
		System.out.println("Introduce el id de un espectáculo: ");
		int codEspec = t.nextInt();t.nextLine();
		Espectaculos es = ad.obtenerEspectaculo(codEspec);
		if(es != null) {
			if(ad.borrarEntrada(es)) {
				System.out.println("Entrada borrada");
			}
			else {
				System.out.println("Error al borrar entrada.");
			}
		}
		else {
			System.out.println("No existe un espectáculo con ese código.");
		}
			
	}

	private static void modificarPrecioZona() {
		// TODO Auto-generated method stub
		mostrarZonas();
		System.out.println("Introduce el código de una zona: ");
		int codZon = t.nextInt();t.nextLine();
		Zonas z = ad.obtenerZona(codZon);
		if(z != null) {
			System.out.println("Introduce un nuevo precio base: ");
			z.setPrecioBase(t.nextDouble());t.nextLine();
			if(ad.modificarZona(z)) {
				System.out.println("Zona modificada.");
			}
			else {
				System.out.println("Error al modificar la zona.");
			}
		}
		else {
			System.out.println("Ese código de zona no existe.");
		}
	}

	private static void mostrarEntradasZona() {
		// TODO Auto-generated method stub
		
	}

	private static void crearEntrada() {
		// TODO Auto-generated method stub
		mostrarEspectaculos();
		System.out.println("Introduce un código de espectáculo: ");
		int codigo = t.nextInt();t.nextLine();
		Espectaculos es = ad.obtenerEspectaculo(codigo);
		if(es != null) {
			mostrarZonas();
			System.out.println("Introduce un código de zona: ");
			int codZona = t.nextInt();t.nextLine();
			Zonas z = ad.obtenerZona(codZona);
			if(z != null) {
				Entradas en = new Entradas();
				en.setCodigo(ad.obtenerCodigoEntrada());
				en.setEspectaculo(codigo);
				en.setZonas(codZona);
				en.setPrecio(z.getPrecioBase()*(1+es.getBeneficio()));
				if(ad.crearEntrada(es,z,en)) {
					System.out.println("Se ha creado la entrada con codigo "+en.getCodigo());
				}
				else {
					System.out.println("Error al crear la entrada.");
				}
			}
			else {
				System.out.println("No existe ninguna zona con ese código.");
			}
		}
		else {
			System.out.println("No existe el espectáculo con ese código");
		}
		mostrarEntradas();
	}

	private static void mostrarEntradas() {
		// TODO Auto-generated method stub
		SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		ArrayList<Object[]> entradas = ad.obtenerEntradas();
		for(Object[] o:entradas) {
			System.out.println("Código:"+o[0]+
					"\tEspectaculo:"+o[1]+
					"\tZona"+o[2]+
					"\tPrecio"+o[3]);
		}
	}

	private static void mostrarZonas() {
		// TODO Auto-generated method stub
		ArrayList<Zonas> zonas = ad.obtenerZonas();
		for(Zonas z:zonas) {
			z.mostrar(false);
		}
	}

	private static void mostrarEspectaculos() {
		// TODO Auto-generated method stub
		ArrayList<Espectaculos> espec = ad.obtenerEspectaculos();
		for(Espectaculos es:espec) {
			es.mostrar();
		}
	}

}
