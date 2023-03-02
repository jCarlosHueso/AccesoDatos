package instituto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Principal {
	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getCnx()!= null) {
			int opcion;
			do {
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				System.out.println("1-Mostrar alumnos"); 
				System.out.println("2-Poner nota");
				System.out.println("3-Mostrar notas alumnos");
				System.out.println("4-Modificar nota");
				System.out.println("5-Modificar direccion de persona");
				
				
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					mostrarAlumnos();
					break;
				case 2:
					ponerNota();
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				
				}
			} while (opcion != 0);
			//Cerrar conexión
			ad.cerrar();
		} else {
			System.out.println("Error, no hay conexión con Clínica");
		}
	}

	private static void ponerNota() {
		// TODO Auto-generated method stub
		try {
			mostrarAlumnos();
			System.out.println("Introduce id:");
			int id = t.nextInt(); t.nextLine();
			Alumno a = ad.obtenerAlumno(id);
			if(a!=null) {
				mostrarAsignaturas();
				System.out.println("Introduce asignatura");
				String asig = t.nextLine();
				Asignatura as = ad.obtenerAsignatura(asig);
				if(as!=null) {
					System.out.println("Fecha (dd/mm/yyyy):");
					Date f = formato.parse(t.nextLine());
					System.out.println("Introduce Nota:");
					float nota = t.nextInt();t.nextLine();
					//Comprobar si el alumnos tiene ya alguna nota
					//en esa asignatura
					//Si no tiene ninguna nota, hacemos insert
					//Si tiene alugna nota hacemos update modificando
					//el array (añadiendo un elemento al array)
					Nota n = ad.obtenerNota(a, as);
					n.mostrar();
					if(n==null) {
					}
					else {
						//Update
					}
				}
				else {
					System.out.println("Error, asignatura no existe");
				}
			}
			else {
				System.out.println("Error, alumno no existe");
			}
		} catch (ParseException e) {
			System.out.println("Fecha Incorrectqa");
		}
		
	}
	private static void mostrarAsignaturas() {
		// TODO Auto-generated method stub
		ArrayList<Asignatura> asig = ad.obtenerAsignaturas();
		for(Asignatura a: asig) {
			a.mostrar();
		}
	}

	private static void mostrarAlumnos() {
		ArrayList<Alumno> alumnos=ad.obtenerAlumnos();
		
		for (Alumno a : alumnos) {
			
			a.mostrar();
			
		}
		
	}
}
