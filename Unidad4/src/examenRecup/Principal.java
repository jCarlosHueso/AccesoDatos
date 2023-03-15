package examenRecup;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.Date;


public class Principal {
	
	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	// Definimos el formato con el que vamos
	// a pintar/pedir fechas
	static SimpleDateFormat formato = new SimpleDateFormat("ddMMyyHHmm");
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getEm() != null) {
			int opcion;
			do {
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				System.out.println("1- Crear Prestamo ");
				System.out.println("2- Mostrar prestamos de un socio ");
				System.out.println("3- Devolver un prestamo ");
				System.out.println("4- Borrar libro ");

				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearPrestamo();
					break;
				case 2:
					break;
				
				}
			} while (opcion != 0);
			//Cerrar conexión
			ad.cerrar();
		} else {
			System.out.println("Error, no hay conexión con la base de datos");
		}
	}


	private static void crearPrestamo() {
		
		//crear un prestamo
		
		mostrarSocios();
		
	}


	private static void mostrarSocios() {

		List<Socio> socios = ad.obtenerSocios();
		 
		for (Socio socio : socios) {
			
			System.out.println(socio.toString());
			
		}
		System.out.println("Introduce el id del socio que solicita el libro");
		int idSocio = t.nextInt();t.nextLine();
		
		Socio socio = ad.obtenerSocio(idSocio);
		if(socio!=null) {
			
			if(comprobarsancion(socio)) {
				System.out.println("Socio no sancionado");
				long libros = ad.obtenerLibrosPrestados(socio);

					if(libros<=2) {
						

						List<Libro> l = ad.obtenerLibros();
						 
						for (Libro libro : l) {
							
							System.out.println(l.toString());
							
							
							System.out.println("Introduce el isbn del libro a prestar");
							int isbn = t.nextInt();t.nextLine();
							
							Libro li = ad.obtenerLibro(isbn);
							if(li!=null) {
								
								
							}
							
							
						}
						
						
						
						
					}else {
						System.out.println("Este socio ya tiene el máximo permitido de libro prestados");
					}

					
			}else {
				System.out.println("Lo lamento, el socio está sancionado");
			}
		
		}else {
			System.out.println("Error, el socio indicado no existe");
		}
		
	}


	private static boolean comprobarsancion(Socio socio) {
		boolean resultado = false;
		
		
		if(socio.getFechaSancion()!=null) {
			
			if(socio.getFechaSancion().getTime() >= new Date().getTime() ) {
				
				//sancion mayor que fecha actual 
				
			}else {
				//como la sancion no es mayor  que la fecha actualno esta sancionado
				resultado=true;
			}
			
		}else {
			//como la fecha de sancion es nula el resultado es true 
			resultado=true;
		}
		
		
		
		return resultado;
	}
}
