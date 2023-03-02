package saneamiento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Principal {

	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getEm() != null) {
			int opcion;
			do {
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				System.out.println("1-Crear Saneamiento");
				//2-AÃ±ade una pieza a una reparaciÃ³n 
				//Hay que chequear que hay stock suficiente
				//Si la pieza aÃºn no estÃ¡ en la reparaciÃ³n, se aÃ±ade
				//Si la pieza ya estÃ¡ en la reparaciÃ³n, se actualiza
				//Hay que actualizar el stock de la pieza
				System.out.println("2-Estadística por animal");
				System.out.println("3-Introducir datos saneamientos");
				System.out.println("4-Borrar saneamiento");
				System.out.println("5-Datos veterinario");
				
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearSaneamiento();
					break;
				case 2:
					estadisticaAnimal();
					break;
				case 3:
					introducirDatosSaneamiento();
					break;
				case 4:
					borrarSaneamiento();
					break;
				case 5:
					datosVeterinario();
					break;
				}
				
			} while (opcion != 0);
			//Cerrar conexiÃ³n
			ad.cerrar();
		} else {
			System.out.println("Error, no hay conexión con Saneamientos");
		}
	}

	private static void datosVeterinario() {
		// TODO Auto-generated method stub
		mostrarVeterinarios();
		System.out.print("Introduce el nº de colegiado del veterinario: ");
		int col = t.nextInt(); t.nextLine();
		Veterinario v = ad.obtenerVeterinario(col);
		if(v != null) {
			int tub = 0;
			int bru = 0;
			
			for(Saneamiento s: v.getSaneamiento()) {
				for(ResultadoSaneamiento rs: s.getResSan()) {
					if(rs.isTuberculosis()) {
						//Esto es mejor que lo de Guillermo
						tub++;
					}
					if(rs.isBrucelosis()) {
						//Esto es mejor que lo de Guillermo
						bru++;
					}
				}
			}
			System.out.println("Este veterinario ha realizado un total de " + v.getSaneamiento().size() + " saneamientos.");
			System.out.println("Casos totales detectados de tuberculosis: " + tub);
			System.out.println("Casos totales detectados de brucelosis: " + bru);
		} else {
			System.out.println("Error, no existe ningún veterinario con ese nº de colegiado.");
		}
	}

	private static void borrarSaneamiento() {
		// TODO Auto-generated method stub
		mostrarSaneamientos();
		System.out.println("Introduce id saneamiento: ");
		String codigo = t.nextLine();
		Saneamiento s = ad.obtenerSaneamiento(codigo);
		if(s != null) {
			if(ad.borrarSaneamiento(s)) {
				System.out.println("Saneamiento borrado");
			}
			else {
				System.out.println("No se ha podido borrar el saneamiento");
			}
		}
		else {
			System.out.println("No existe el saneamiento");
		}
	}

	private static void introducirDatosSaneamiento() {
		// TODO Auto-generated method stub
		mostrarSaneamientos();
		System.out.println("Introduce id saneamiento: ");
		String codigo = t.nextLine();
		Saneamiento s = ad.obtenerSaneamiento(codigo);
		if(s != null) {
			for(ResultadoSaneamiento rs: s.getResSan()) {
				System.out.println("Animal para saneamiento: "+rs.getClave().getAnimal().getCodigo());
				System.out.println("Tuberculosis -> TRUE/FALSE: ");
				rs.setTuberculosis(Boolean.parseBoolean(t.nextLine()));
				System.out.println("Brucelosis -> TRUE/FALSE: ");
				rs.setBrucelosis(Boolean.parseBoolean(t.nextLine()));
				
				rs.setFechaResultado(new Date());
				
				if(rs.isTuberculosis() || rs.isBrucelosis()) {
					rs.getClave().getAnimal().setBloqueado(true);
				}
			}
			if(ad.crearDatosSaneamiento(s)) {
				System.out.println("Código Saneamiento: "+s.getId()+
						"\tVeterinario: "+s.getVeterinario().getNombre());
				for(ResultadoSaneamiento rs: s.getResSan()) {
					rs.getClave().getAnimal().mostrar(false);
					System.out.println("Sus resultados en tuberculosis son: " + rs.isTuberculosis() + 
							"\t|\tSus resultados en brucelosis son: " + rs.isBrucelosis());
				}
			}
		}
		else {
			System.out.println("No existe el saneamiento");
		}
	}

	private static void estadisticaAnimal() {
		// TODO Auto-generated method stub
		List<Object[]> estadistica = ad.obtenerEstadisticaPorAnimales();
		for(Object[]o:estadistica) {
			System.out.println("Animal:"+o[0] + 
					"\tNº Saneamientos:" + o[1] + 
					"\tPrimer Saneamiento:" + o[2]+
					"\tÚltimo Saneamiento:" + o[3]);
		}
	}

	private static void crearSaneamiento() {
		// TODO Auto-generated method stub
		try {
			mostrarVeterinarios();
			System.out.println("Número de colegiado: ");
			int colegiado = t.nextInt();t.nextLine();
			Veterinario v = ad.obtenerVeterinario(colegiado);
			if(v != null) {
				mostrarSaneamientos();
				System.out.println("Introduce código del saneamiento: ");
				String codigoSan = t.nextLine();
				Saneamiento s = ad.obtenerSaneamiento(codigoSan);
				if(s == null) {
					s = new Saneamiento();
					s.setId(codigoSan);
					System.out.println("Introduce una fecha para el saneamiento: (dd/MM/yyyy");
					Date fechaSan=formato.parse(t.nextLine());
					s.setFecha(fechaSan);
					s.setVeterinario(v);	
					List<Animal> animales = ad.obtenerAnimales();
					for(Animal a: animales) {
						s.getResSan().add(new ResultadoSaneamiento(new claveAS(a, s), false, false, null));
					}
					if(ad.crearSaneamiento(s)) {
							System.out.println("Saneamiento creado con código "+codigoSan);
					}
					else {
						System.out.println("No se ha podido crear el saneamiento");
					}
				}
				else {
					System.out.println("Ya existe saneamiento");
				}
			}
			else {
				System.out.println("No se ha encontrado un veterinario con el código introducido");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void mostrarSaneamientos() {
		// TODO Auto-generated method stub
		List<Saneamiento> sanea = ad.obtenerSaneamientos();
		for(Saneamiento s: sanea) {
			s.mostrar(false);
		}
	}

	private static void mostrarVeterinarios() {
		// TODO Auto-generated method stub
		List<Veterinario> veterinario = ad.obtenerVeterinarios();
		for(Veterinario v: veterinario) {
			v.mostrar(false);
		}
	}
		

}
